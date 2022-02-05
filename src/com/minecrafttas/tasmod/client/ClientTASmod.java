package com.minecrafttas.tasmod.client;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.TASmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Client Proxy for TASmod events
 * @author Pancake
 */
@SideOnly(Side.CLIENT)
public class ClientTASmod extends CommonTASmod {

	// Clientside TASmod singleton
	public static ClientTASmod instance = new ClientTASmod();
	
	/**
	 * Pre-Initialize the TASmod clientside
	 */
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		TASmod.LOGGER.info("Client TASmod Preinit Phase");
		super.onPreInit(e);
	}
	
	/**
	 * Initialize the TASmod clientside
	 */
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		TASmod.LOGGER.info("Client TASmod Init Phase");
		super.onInit(e);
	}

	/**
	 * Updates the TASmod at the start of a client tick
	 * 
	 * IMPLEMENTATION NOTICE:
	 * net.minecraft.client.Minecraft.runTick()V
	 * 
	 * @param mc Instance of Minecraft
	 */
	public void onClientTick(Minecraft mc) {
		TASmod.LOGGER.info("Client Tick");
	}
	
}
