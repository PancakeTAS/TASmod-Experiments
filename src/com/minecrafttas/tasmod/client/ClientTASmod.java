package com.minecrafttas.tasmod.client;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.exceptions.ClientAlreadyRunningException;
import com.minecrafttas.tasmod.networking.CustomTASmodClient;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
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
		TASmod.LOGGER.debug("Client TASmod Preinit Phase");
		super.onPreInit(e);
	}
	
	/**
	 * Initialize the TASmod clientside.
	 * This will register this class to the event bus from Forge, so that future Forge events will be triggered in this class
	 */
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		TASmod.LOGGER.debug("Client TASmod Init Phase");
		/* Register the current class to the event bus from forge */
		TASmod.LOGGER.debug("Registering the ClientTASmod to the event bus");
		MinecraftForge.EVENT_BUS.register(this); // this will add the current class to the event bus from forge
		TASmod.LOGGER.debug("ClientTASmod was registered to the event bus");
		super.onInit(e);
	}

	/**
	 * Initialize the TASmod when connecting to a world.
	 * This will launch the custom TASmod client on a separate thread.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called after the client is loaded, but before it launches
	 * 
	 * @param e Connected to the server event
	 */
	@SubscribeEvent
	public void onClientConnect(ClientConnectedToServerEvent e) {
		TASmod.LOGGER.debug("TASmod Connect Server Phase");
		/* Launch the custom client thread */
		try {
			TASmod.LOGGER.debug("Trying to create the custom tasmod client");
			CustomTASmodClient.createClient(); // this will create a new thread in which it launches the client. It also throws an exception if the last client wasn't successfully shut down.
			TASmod.LOGGER.debug("Successfully created the custom tasmod client without any unexpected issues");
		} catch (ClientAlreadyRunningException exception) {
			// Note: The loglevel is only 'warn', since this exception is not fatal and the client was still started.
			TASmod.LOGGER.warn("Exception thrown trying to launch the custom TASmod client!");
			TASmod.LOGGER.warn(exception);
		}
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
