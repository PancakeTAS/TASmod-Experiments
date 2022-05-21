package com.minecrafttas.tasmod.mixin.client.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.client.ClientTASmod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.ClientConnection;

/**
 * This mixin is purely responsible for the hooking up the events in {@link ClientTASmod}.
 * @author Pancake
 */
@Mixin(ClientConnection.class)
@Environment(EnvType.CLIENT)
public class HookClientConnection {

	/**
	 * Triggers an Event in {@link ClientTASmod#onClientDisconnect()} after the connection has been closed
	 * @param ci Callback Info
	 */
	@Inject(method = "disconnect", at = @At("HEAD"))
	public void hookDisconnectEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientDisconnect();
	}

}