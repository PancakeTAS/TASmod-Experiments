package com.minecrafttas.tasmod;

import java.io.IOException;

import com.minecrafttas.tasmod.exceptions.ServerAlreadyRunningException;
import com.minecrafttas.tasmod.networking.Server;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
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
		TASmod.LOGGER.debug("Common TASmod Preinit Phase");
	}
	
	/**
	 * Initialize the TASmod
	 * This will register this class to the event bus from Forge, so that future Forge events will be triggered in this class
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called before the game launch method is called x2
	 * 
	 * @param e Initialization Event
	 */
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		TASmod.LOGGER.debug("Common TASmod Init Phase");
		/* Register the current class to the event bus from forge */
		TASmod.LOGGER.debug("Registering the CommonTASmod to the event bus");
		MinecraftForge.EVENT_BUS.register(this); // this will add the current class to the event bus from forge
		TASmod.LOGGER.debug("CommonTASmod was registered to the event bus");
	}
	
	/**
	 * Initialize the TASmod on the integrated or dedicated server, that is about to launch.
	 * This will launch the custom TASmod server on a separate thread.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.server.MinecraftServer.run()V
	 * Mixin: com.minecrafttas.tasmod.mixin.events.HookMinecraftServer.hookRunEvent(CallbackInfo)V
	 */
	public void onServerLaunch() {
		TASmod.LOGGER.debug("Common TASmod Server Launch Phase");
		/* Launch the custom server thread */
		try {
			TASmod.LOGGER.debug("Trying to create the custom tasmod server");
			Server.createServer(); // this will create a new thread in which it launches the server. It also throws an exception if the last server wasn't successfully shut down.
			TASmod.LOGGER.debug("Successfully created the custom tasmod server without any unexpected issues");
		} catch (ServerAlreadyRunningException exception) {
			// Note: The loglevel is only 'warn', since this exception is not fatal and the server was still started.
			TASmod.LOGGER.warn("Exception thrown trying to launch the custom TASmod server! {}", exception);
		} catch (IOException exception) {
			TASmod.LOGGER.fatal("Exception thrown trying to kill the previous custom TASmod server! {}", exception);
		}
	}
	
	/**
	 * Uninitialize part of the TASmod after the server has stopped.
	 * This will kill the custom TASmod server.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.server.MinecraftServer.run()V at return
	 * Mixin: com.minecrafttas.tasmod.mixin.events.HookMinecraftServer.hookRunEndEvent(CallbackInfo)V
	 */
	public void onServerStop() {
		TASmod.LOGGER.debug("Common TASmod Server Stop Phase");
		/* Kill the custom server thread */
		try {
			TASmod.LOGGER.debug("Trying to kill the custom tasmod server");
			Server.killServer(); // this will kill the server if it is running
			TASmod.LOGGER.debug("Successfully killed the custom tasmod server without any unexpected issues");
		} catch (IOException exception) {
			TASmod.LOGGER.error("Exception thrown trying to kill the custom TASmod server!");
			TASmod.LOGGER.error(exception);
		}
	}
	
	/**
	 * Updates the TASmod at the start of a tick
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.server.MinecraftServer.run()V at net.minecraft.server.MinecraftServer.tick()V
	 * Mixin: com.minecrafttas.tasmod.mixin.events.HookMinecraftServer.hookRunTickEvent(CallbackInfo)V
	 * @param mcserver Instance of Minecraft Server
	 */
	public void onServerTick(MinecraftServer mcserver) {
		TASmod.LOGGER.debug("Server Tick");
	}
	
}
