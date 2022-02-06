package com.minecrafttas.tasmod.networking.packets;

import java.util.UUID;

import com.minecrafttas.tasmod.client.ticks.TickSyncClient;

/**
 * This packet is sent after every tick to let the other server know when to tick.
 * This is part of Tick Sync
 * German: https://1drv.ms/p/s!Av_ysXerhm5CphLvLvguvL5QYe1A?e=MHPldP
 * English: https://1drv.ms/p/s!Av_ysXerhm5Cpha7Qq2tiVebd4DY?e=pzxOva
 * 
 * @author Pancake
 */
public class ClientTickPacket extends ServerTickPacket {
	public ClientTickPacket(UUID uuid, int tick) { super(uuid, tick); }

	@Override
	public void handle() {
		TickSyncClient.onPacket(uuid, tick);
	}

}
