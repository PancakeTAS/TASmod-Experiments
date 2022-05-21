package com.minecrafttas.tasmod.networking.packets;

import com.minecrafttas.tasmod.TASmod;

import io.netty.buffer.Unpooled;
import net.minecraft.util.PacketByteBuf;

/**
 * This class helps serializing and deserializing packets
 * @author Pancake
 */
public class PacketSerializer {

	/**
	 * Deserialize a TASmod packet from a packet buffer. The packet class is prefixed with an id and read here.
	 * 
	 * @param buf Serialized byte buffer with id prefix
	 * @return Deserialized packet
	 */
	public static Packet deserialize(PacketByteBuf buf) {
		// Read packet id and deserialize the correct packet
		int packetId = buf.readInt();
		Packet packet = null;
		switch (packetId) {
			case 0:
				packet = new ExamplePacket();
				break;
			case 1:
				packet = new ClientTickPacket();
				break;
			case 2:
				packet = new ServerTickPacket(null);
				break;
			default:
				TASmod.LOGGER.warn("Unregistered packet received! Packet Id: " + packetId);
				return null;
		}
		packet.deserialize(buf);
		return packet;
	}
	
	/**
	 * Serialize a TASmod packet to a packet buffer. The packet class is read and a id prefixed packet buffer is returned
	 * 
	 * @param packet Non-serialized packet
	 * @return Serialized packet buffer with id prefix
	 */
	public static PacketByteBuf serialize(Packet packet) {
		// Figure out packet class and prefix the correct id
		String clazz = packet.getClass().getSimpleName();
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		switch (clazz) {
			case "ExampleTASmodPacket":
				buf.writeInt(0);
				break;
			case "ClientTickPacket":
				buf.writeInt(1);
				break;
			case "ServerTickPacket":
				buf.writeInt(2);
				break;
			default:
				TASmod.LOGGER.warn("Unregistered packet was trying to be serialized! Packet Class: " + clazz);
				return null;
		}
		packet.serialize(buf);
		return buf;
	}
	
}
