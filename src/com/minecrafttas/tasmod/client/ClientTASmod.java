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
	public static ClientTASmod instance;
	
	/**
	 * Prepares the singleton when the SidedProxy annotation in com.minecrafttas.tasmod.TASmod creates an instance of this object
	 */
	public ClientTASmod() {
		ClientTASmod.instance = this;
		TASmod.LOGGER.debug("ClientTASmod singleton ready");
	}
	
	/**
	 * Pre-Initialize the TASmod clientside
	 */
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		TASmod.LOGGER.debug("Client TASmod Preinit Phase");
		super.onPreInit(e);
	}
	
	/**
	 * Initialize the TASmod clientside
	 */
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		TASmod.LOGGER.debug("Client TASmod Init Phase");
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
		TASmod.LOGGER.debug("Client Tick");
	}
	
}
