package com.minecrafttas.tasmod.networking;

import java.io.EOFException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.exceptions.ServerAlreadyRunningException;
import com.minecrafttas.tasmod.networking.packets.TASmodPacket;

/**
 * The TASmod itself has a custom connection running next to the minecraft one. 
 * It's necessary since the integrated packet connection is tick-based and therefore cannot communicate inbetween ticks.
 * 
 * IMPLEMENTATION NOTICE:
 * The server creates a separate thread to run off so that it's non-blocking. Therefore it uses a queue for outgoing packets.
 * @author Pancake
 */
public class CustomTASmodServer {

	/**
	 * This is the thread that runs the server. It will exit once the server has disconnected.
	 * Interrupting it will always close the connection and end the thread.
	 */
	private static Thread instance;
	
	/**
	 * This queue of packets is going to be sent by another thread.
	 */
	private static LinkedBlockingQueue<BlockingQueue<TASmodPacket>> packetsToSend = new LinkedBlockingQueue<>(); // Initialize with something so this cannot cause a npe
	
	/**
	 * Adds a packet to the queue of packets to send to all clients
	 * @param packet Packet to send
	 */
	public static void sendPacket(TASmodPacket packet) {
		if (CustomTASmodServer.instance == null)
			return;
		if (!CustomTASmodServer.instance.isAlive())
			return;
		CustomTASmodServer.packetsToSend.forEach(queue -> queue.add(packet));
	}
	
	/**
	 * Once the server enters launch phase a separate server thread is created.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Called from CommonTASmod.
	 * 
	 * @throws If the last server wasn't succesfully shut down it will throw an exception and forcefully shut down the server
	 */
	public static void createServer() throws ServerAlreadyRunningException {
		boolean isRunning = CustomTASmodServer.instance == null ? false : CustomTASmodServer.instance.isAlive();
		// Cancel the currently running server
//		if (isRunning)
//			CustomTASmodServer.instance.interrupt();
		
		// Clear the list of packets to send
		CustomTASmodServer.packetsToSend = new LinkedBlockingQueue<>();
		// Start a server
		CustomTASmodServer.instance = new Thread(() -> {
			try(ServerSocket serverSocket = new ServerSocket(3111)) {
				// Wait until new clients are there and then handle them.
				while (true) {
					Socket clientSocket = serverSocket.accept();
					final LinkedBlockingQueue<TASmodPacket> queue = new LinkedBlockingQueue<>();
					packetsToSend.add(queue);
					Thread handler = new Thread(() -> {
						try {
							CustomTASmodCommon.handleSocket(clientSocket, queue); // this will create a new thread for outstream and use the current thread for instream
						} catch (EOFException exception) {
							// The custom TASmod client connection was closed and the end of stream was reached. The socket was shut down properly.
							TASmod.LOGGER.debug("Custom TASmod client connection was shutdown");
						} catch (Exception exception) {
							TASmod.LOGGER.fatal("Custom TASmod client connection was unexpectedly shutdown", exception);
						}
					});
					handler.setDaemon(true);
					handler.start();
				}
			} catch (EOFException exception) {
				// The custom TASmod server was closed and the end of stream was reached. The socket was shut down properly.
				TASmod.LOGGER.debug("Custom TASmod server was shutdown");
			} catch (Exception exception) {
				TASmod.LOGGER.fatal("Custom TASmod server was unexpectedly shutdown", exception);
			}
		});
		CustomTASmodServer.instance.setDaemon(true); // If daemon is set, the jvm will quit without waiting for this thread to finish
		CustomTASmodServer.instance.start();
		
		// Make sure to throw an exception if the server was running
		if (isRunning)
			throw new ServerAlreadyRunningException();
	}
	
	/**
	 * Kills the custom TASmod server if is running
	 */
	public static void killServer() {
		if (CustomTASmodServer.instance != null)
			CustomTASmodServer.instance.interrupt();
	}
	
}
