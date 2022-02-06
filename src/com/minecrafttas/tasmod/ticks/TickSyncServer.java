package com.minecrafttas.tasmod.ticks;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import com.minecrafttas.tasmod.networking.Server;
import com.minecrafttas.tasmod.networking.packets.ClientTickPacket;

/**
 * This class manages tick sync
 * German: https://1drv.ms/p/s!Av_ysXerhm5CphLvLvguvL5QYe1A?e=MHPldP
 * English: https://1drv.ms/p/s!Av_ysXerhm5Cpha7Qq2tiVebd4DY?e=pzxOva
 * 
 * @author Pancake
 */
public class TickSyncServer {

	/**
	 * This is the tick the server is currently on.
	 */
	private static int tick;
	
	/**
	 * A multithreadable boolean that tells the MixinMinecraftServer to tick the server or not.
	 */
	public static AtomicBoolean shouldTick = new AtomicBoolean(true);
	
	/**
	 * Handles incoming tick packets from the client to the server
	 * This will put the uuid into a list of ticked clients and once every client
	 * is in that list, tick the server.
	 * 
	 * @param uuid Player UUID
	 * @param tick Current tick of the player
	 */
	public static void onPacket(UUID uuid, int tick) {
//		if ((tick-1) != TickSyncServer.tick)
//			return;
		shouldTick.set(true);
		tick++;
		
		// TODO: Wait for all clients not just any
	}
	
	/**
	 * Called after a server tick. This will send a packet
	 * to all clients making them tick
	 */
	public static void serverPostTick() {
		Server.sendPacket(new ClientTickPacket(UUID.randomUUID(), TickSyncServer.tick));
	}
	
}
