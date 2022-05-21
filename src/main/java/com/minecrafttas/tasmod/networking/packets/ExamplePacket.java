package com.minecrafttas.tasmod.networking.packets;

import com.minecrafttas.tasmod.TASmod;

import net.minecraft.util.PacketByteBuf;

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
	public PacketByteBuf serialize(PacketByteBuf buf) {
		// Don't write any specific data...
		return buf;
	}

	@Override
	public void deserialize(PacketByteBuf buf) {
		// Don't read any specific data
	}

}
