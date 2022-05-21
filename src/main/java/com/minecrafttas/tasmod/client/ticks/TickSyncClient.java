package com.minecrafttas.tasmod.client.ticks;

import com.minecrafttas.tasmod.networking.Client;
import com.minecrafttas.tasmod.networking.packets.ServerTickPacket;

import net.minecraft.client.MinecraftClient;

/**
 * This class manages tick sync
 * German: https://1drv.ms/p/s!Av_ysXerhm5CphLvLvguvL5QYe1A?e=MHPldP
 * English: https://1drv.ms/p/s!Av_ysXerhm5Cpha7Qq2tiVebd4DY?e=pzxOva
 * 
 * @author Pancake
 */
public class TickSyncClient {
	
	/**
	 * Handles incoming tick packets from the server to the client
	 * This will simply tick the client as long as the tick is correct
	 * 
	 * @param uuid Server UUID, null
	 * @param tick Current tick of the server
	 */
	public static void onPacket() {
		TimerMod.shouldTick.set(true);
	}

	/**
	 * Called after a client tick. This will send a packet
	 * to the server making it tick
	 * 
	 * @param mc Instance of Minecraft
	 */
	public static void clientPostTick(MinecraftClient mc) {
		if (mc.player == null)
			return;
		Client.sendPacket(new ServerTickPacket(mc.player.getGameProfile().getId()));
	}
	
}
