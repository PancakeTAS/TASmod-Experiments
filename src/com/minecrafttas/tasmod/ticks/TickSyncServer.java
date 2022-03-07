package com.minecrafttas.tasmod.ticks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import com.minecrafttas.tasmod.networking.Server;
import com.minecrafttas.tasmod.networking.packets.ClientTickPacket;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * This class manages tick sync
 * German: https://1drv.ms/p/s!Av_ysXerhm5CphLvLvguvL5QYe1A?e=MHPldP
 * English: https://1drv.ms/p/s!Av_ysXerhm5Cpha7Qq2tiVebd4DY?e=pzxOva
 * 
 * @author Pancake
 */
public class TickSyncServer {
	
	/**
	 * A multithreadable boolean that tells the MixinMinecraftServer to tick the server or not.
	 */
	public static AtomicBoolean shouldTick = new AtomicBoolean(true);
	
	private static final List<UUID> playerList=new ArrayList<UUID>();
	
	/**
	 * Handles incoming tick packets from the client to the server
	 * This will put the uuid into a list of ticked clients and once every client
	 * is in that list, tick the server.
	 * 
	 * @param uuid Player UUID
	 * @param tick Current tick of the player
	 */
	public static void onPacket(UUID uuid) {
		addPlayer(uuid);
		shouldTick.set(allPlayersDone());
	}
	
	/**
	 * Called after a server tick. This will send a packet
	 * to all clients making them tick
	 */
	public static void serverPostTick() {
		Server.sendPacket(new ClientTickPacket());
	}
	
	/**
	 * Adds a player uuid to the playerlist
	 * @param uuid
	 */
	private static void addPlayer(UUID uuid) {
		if (!playerList.contains(uuid)) { // Checks if the player is already in the list
			playerList.add(uuid);
		}
	}

	/**
	 * Checks if all players are in {@link #playerList}
	 * 
	 * @return True, if all players are in {@link #playerList}
	 */
	private static boolean allPlayersDone() {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		for (EntityPlayerMP player : server.getPlayerList().getPlayers()) {
			if (!playerList.contains(player.getGameProfile().getId())) {
				return false;
			}
		}
		playerList.clear();
		return true;
	}

}
