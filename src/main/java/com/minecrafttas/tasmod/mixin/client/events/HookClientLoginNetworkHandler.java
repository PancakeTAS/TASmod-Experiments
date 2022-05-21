package com.minecrafttas.tasmod.mixin.client.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.client.ClientTASmod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientLoginNetworkHandler;

/**
 * This mixin is purely responsible for the hooking up the events in {@link ClientTASmod}.
 * @author Pancake
 */
@Mixin(ClientLoginNetworkHandler.class)
@Environment(EnvType.CLIENT)
public class HookClientLoginNetworkHandler {

	/**
	 * Triggers an Event in {@link ClientTASmod#onClientConnect()} after the connection handler has been created
	 * @param ci Callback Info
	 */
	@Inject(method = "<init>", at = @At("RETURN"))
	public void hookInitEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientConnect();
	}

}