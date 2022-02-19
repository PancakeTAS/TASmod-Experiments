package com.minecrafttas.tasmod.client;

import java.io.IOException;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.client.ticks.TickSyncClient;
import com.minecrafttas.tasmod.client.ticks.TimerMod;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;
import com.minecrafttas.tasmod.exceptions.ClientAlreadyRunningException;
import com.minecrafttas.tasmod.networking.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
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
		/* Install the client timer mod */
		TASmod.LOGGER.debug("Installing the custom timer mod");
		TimerMod.applyTimerMod();
		TASmod.LOGGER.debug("Custom timer mod was installed");
		super.onInit(e);
	}

	/**
	 * Initialize the TASmod when connecting to a world.
	 * This will launch the custom TASmod client on a separate thread.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called after the client is connected to the server
	 * 
	 * @param e Connected to the server event
	 */
	@SubscribeEvent
	public void onClientConnect(ClientConnectedToServerEvent e) {
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
	 * This is a forge event called after the client is disconnected from the server
	 * 
	 * @param e Disconnected to the server event
	 */
	@SubscribeEvent
	public void onClientDisconnect(ClientDisconnectionFromServerEvent e) {
		TASmod.LOGGER.debug("TASmod Disconnect Server Phase");
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
	 * Trace: net.minecraft.client.Minecraft.runTick()V
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookMinecraft.runTickEvent(CallbackInfo)V
	 * 
	 * @param mc Instance of Minecraft
	 */
	public void onClientTick(Minecraft mc) {
		TASmod.LOGGER.debug("Client Tick");
		/* Update virtual mouse */
		TASmod.LOGGER.debug("Updating virtual mouse DX and DY");
		VirtualMouse.poll();
		TASmod.LOGGER.debug("Virtual mouse updated pre tick");
	}
	
	/**
	 * Updates the TASmod at the end of a client tick
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Trace: net.minecraft.client.Minecraft.runTick()V at RETURN
	 * Mixin: com.minecrafttas.tasmod.mixin.client.events.HookMinecraft.runTickPostEvent(CallbackInfo)V
	 * 
	 * @param mc Instance of Minecraft
	 */
	public void onClientPostTick(Minecraft mc) {
		TASmod.LOGGER.debug("Client Post Tick");
		/* Update tick sync */
		TASmod.LOGGER.debug("Updating tick sync post tick");
		TickSyncClient.clientPostTick(mc);
		TASmod.LOGGER.debug("Tick sync was updated post tick");
	}
	
	/**
	 * Renders overlays every frame
	 * 
	 * IMPLEMENTATION NOTICE:
	 * This is a forge event called while the HUD is rendered
	 * 
	 * @param mc Instance of Minecraft
	 */
	@SubscribeEvent
	public void onClientRender(RenderGameOverlayEvent e) {
		if (e.getType() != ElementType.TEXT) return; // Only render on this certain event
		
		TASmod.LOGGER.debug("Client Render Loop");
		/* Render mouse and keyboard */
		TASmod.LOGGER.debug("Rendering keyboard and mouse");
		final ScaledResolution res = e.getResolution();
		VirtualKeyboard.render(res.getScaledWidth(), res.getScaledHeight());
		VirtualMouse.render(res.getScaledWidth(), res.getScaledHeight());
		TASmod.LOGGER.debug("Keyboard and mouse were rendered");
	}
	
}
