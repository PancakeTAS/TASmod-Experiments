package com.minecrafttas.tasmod;

import java.io.IOException;

import com.minecrafttas.tasmod.exceptions.ServerAlreadyRunningException;
import com.minecrafttas.tasmod.networking.Server;
import com.minecrafttas.tasmod.ticks.TickSyncServer;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;

/**
 * Common Proxy for TASmod events
 * @author Pancake
 */
public class CommonTASmod implements ModInitializer {

	// TASmod singleton
	public static CommonTASmod instance;
	
	/**
	 * Prepares the singleton when the fabric modloader loads the mod
	 */
	public CommonTASmod() {
		CommonTASmod.instance = this;
		TASmod.LOGGER.debug("CommonTASmod singleton ready");
	}
	
	/**
	 * Initialize the TASmod
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a fabric event called before the game launch method is called
	 * 
	 * @param e Initialization Event
	 */
	@Override
	public void onInitialize() {
		TASmod.LOGGER.debug("Common TASmod Init Phase");
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
	 * Trace: net.minecraft.server.MinecraftServer.run()V at custom net.minecraft.server.MinecraftServer.setupWorld()V
	 * Mixin: com.minecrafttas.tasmod.mixin.MixinMinecraftServer.customTick()V
	 * @param mcserver Instance of Minecraft Server
	 */
	public void onServerTick(MinecraftServer mcserver) {
		TASmod.LOGGER.trace("Server Tick");
	}

	/**
	 * Updates the TASmod at the end of a tick
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.server.MinecraftServer.run()V after custom net.minecraft.server.MinecraftServer.setupWorld()V
	 * Mixin: com.minecrafttas.tasmod.mixin.MixinMinecraftServer.customTick()V
	 * @param mcserver Instance of Minecraft Server
	 */
	public void onServerPostTick(MinecraftServer mcserver) {
		TASmod.LOGGER.trace("Server Post Tick");
		/* Update tick sync */
		TASmod.LOGGER.trace("Updating tick sync post tick");
		TickSyncServer.serverPostTick();
		TASmod.LOGGER.trace("Tick sync was updated post tick");
	}
	
}
