package com.minecrafttas.tasmod.networking;

import com.minecrafttas.tasmod.exceptions.ServerAlreadyRunningException;

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
	 * Once the server enters launch phase a separate server thread is created.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Called from CommonTASmod.
	 * 
	 * @throws If the last server wasn't succesfully shut down it will throw an exception and forcefully shut down the server
	 */
	public static void createServer() throws ServerAlreadyRunningException {
		
	}
	
}
