package com.minecrafttas.tasmod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Common Proxy for TASmod events
 * @author Pancake
 */
public class CommonTASmod {

	// TASmod singleton
	public static CommonTASmod instance = new CommonTASmod();
	
	/**
	 * Pre-Initialize the TASmod
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called before the game launch method is called
	 * 
	 * @param e Pre Initialization Event
	 */
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		TASmod.LOGGER.debug("Common TASmod Init Phase");
	}
	
	/**
	 * Initialize the TASmod
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called before the game launch method is called x2
	 * 
	 * @param e Initialization Event
	 */
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		TASmod.LOGGER.debug("Common TASmod Init Phase");
	}
	
	/**
	 * Updates the TASmod at the start of a tick
	 * 
	 * IMPLEMENTATION NOTICE:
	 * net.minecraft.server.MinecraftServer.tick()V
	 * 
	 * @param mcserver Instance of Minecraft Server
	 */
	public void onServerTick(MinecraftServer mcserver) {
		TASmod.LOGGER.debug("TASmod Server Tick Handle");
	}
	
}
