package com.minecrafttas.tasmod.networking.packets;

import com.minecrafttas.tasmod.TASmod;

import net.minecraft.network.PacketBuffer;

/**
 * This is an example packet created in order to test the custom TASmod server and client.
 * @author Pancake
 */
public class ExamplePacket implements Packet {

	@Override
	public void handle() {
		TASmod.LOGGER.info("Received example packet!");
	}

	@Override
	public PacketBuffer serialize(PacketBuffer buf) {
		// Don't write any specific data...
		return buf;
	}

	@Override
	public void deserialize(PacketBuffer buf) {
		// Don't read any specific data
	}

}
