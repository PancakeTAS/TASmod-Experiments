package com.minecrafttas.tasmod.client;

import java.io.IOException;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.TASmod.Tool;
import com.minecrafttas.tasmod.client.ticks.TickSyncClient;
import com.minecrafttas.tasmod.client.ticks.TimerMod;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;
import com.minecrafttas.tasmod.exceptions.ClientAlreadyRunningException;
import com.minecrafttas.tasmod.networking.Client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

/**
 * Client Proxy for TASmod events
 * @author Pancake
 */
@Environment(EnvType.CLIENT)
public class ClientTASmod extends CommonTASmod implements ClientModInitializer {

	// Clientside TASmod singleton
	public static ClientTASmod instance;

	/**
	 * Prepares the singleton when the fabric modloader loads the mod
	 */
	public ClientTASmod() {
		ClientTASmod.instance = this;
		TASmod.LOGGER.debug("ClientTASmod singleton ready");
	}

	/**
	 * Initialize the TASmod clientside.
	 */
	@Override
	public void onInitializeClient() {
		TASmod.LOGGER.debug("Client TASmod Init Phase");
		/* Install the client timer mod */
		TASmod.LOGGER.debug("Installing the custom timer mod");
		TimerMod.applyTimerMod();
		TASmod.LOGGER.debug("Custom timer mod was installed");
	}

	/**
	 * Initialize the TASmod when connecting to a world.
	 * This will launch the custom TASmod client on a separate thread.
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.network.ClientLoginNetworkHandler.<init>()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookClientLoginNetworkHandler.hookInitEvent(CallbackInfo)V
	 */
	public void onClientConnect() {
		TASmod.LOGGER.debug("TASmod Connect Server Phase");
		/* Launch the custom client thread */
		try {
			TASmod.LOGGER.debug("Trying to create the custom tasmod client");
			Client.createClient(); // this will create a new thread in which it launches the client. It also throws an exception if the last client wasn't successfully shut down.
			TASmod.LOGGER.debug("Successfully created the custom tasmod client without any unexpected issues");
		} catch (ClientAlreadyRunningException exception) {
			// Note: The loglevel is only 'warn', since this exception is not fatal and the client was still started.
			TASmod.LOGGER.warn("Exception thrown trying to launch the custom TASmod client! {}", exception);
		} catch (IOException exception) {
			TASmod.LOGGER.fatal("Exception thrown trying to kill the previous custom TASmod client! {}", exception);
		}
	}

	/**
	 * Uninitialize the TASmod when disconnecting from a world.
	 * This will kill the custom TASmod client on a separate thread.
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.network.ClientConnection.disconnect()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookClientConnection.hookDisconnectEvent(CallbackInfo)V
	 */
	public void onClientDisconnect() {
		TASmod.LOGGER.debug("TASmod Disconnect Server Phase");
		this.shutdownClient();
	}

	/**
	 * Uninitialize the TASmod when shutting down the Minecraft application.
	 * This will kill the custom TASmod client on a seperate thread
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.MinecraftClient.stop()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookMinecraftClient.hookStopEvent(CallbackInfo)V
	 */
	public void onClientShutdown() {
		TASmod.LOGGER.debug("TASmod Shutdown Client Phase");
		this.shutdownClient();
	}

	/**
	 * Attempts to shutdown the TASmod client
	 */
	private void shutdownClient() {
		/* Kill the custom client thread */
		try {
			TASmod.LOGGER.debug("Trying to kill the custom tasmod client");
			Client.killClient(); // this will kill the client if it is running
			TASmod.LOGGER.debug("Successfully killed the custom tasmod client without any unexpected issues");
		} catch (IOException exception) {
			TASmod.LOGGER.error("Exception thrown trying to kill the custom TASmod client!");
			TASmod.LOGGER.error(exception);
		}
	}

	/**
	 * Updates the TASmod at the start of a client tick
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.MinecraftClient.tick()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookMinecraftClient.hookTickEvent(CallbackInfo)V
	 *
	 * @param mc Instance of Minecraft
	 */
	public void onClientTick(MinecraftClient mc) {
		TASmod.LOGGER.trace("Client Tick");
		/* Update virtual mouse */
		TASmod.LOGGER.trace("Updating virtual mouse DX and DY");
		VirtualMouse.poll();
		TASmod.LOGGER.trace("Virtual mouse updated pre tick");
	}

	/**
	 * Updates the TASmod at the end of a client tick
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.MinecraftClient.tick()V at RETURN
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookMinecraftClient.hookTickPostEvent(CallbackInfo)V
	 *
	 * @param mc Instance of Minecraft
	 */
	public void onClientPostTick(MinecraftClient mc) {
		TASmod.LOGGER.trace("Client Post Tick");
		/* Update tick sync */
		TASmod.LOGGER.trace("Updating tick sync post tick");
		TickSyncClient.clientPostTick(mc);
		TASmod.LOGGER.trace("Tick sync was updated post tick");
	}

	/**
	 * Renders overlays every frame
	 *
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.gui.hud.InGameHud.render()V at INVOKE method_12176()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookInGameHud.hookRenderEvent(CallbackInfo)V
	 *
	 * @param res Resolution of the screen
	 */
	public void onClientRender(Window res) {
		TASmod.LOGGER.trace("Client Render Loop");
		if (Tool.TOOLS /* Whether tools should load or not */) {
			/* Render mouse and keyboard */
			TASmod.LOGGER.trace("Rendering keyboard and mouse");
			VirtualKeyboard.render(res.getWidth(), res.getHeight());
			VirtualMouse.render(res.getWidth(), res.getHeight());
			TASmod.LOGGER.trace("Keyboard and mouse were rendered");
		}
	}

}
