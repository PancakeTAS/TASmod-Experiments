package com.minecrafttas.tasmod.mixin.client.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.client.ClientTASmod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

/**
 * This mixin is purely responsible for the hooking up the events in {@link ClientTASmod}.
 * @author Pancake
 */
@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class HookMinecraftClient {
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientTick(Minecraft)} before every tick
	 * @param ci Callback Info
	 */
	@Inject(method = "tick", at = @At("HEAD"))
	public void hookTickEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientTick((MinecraftClient) (Object) this);
	}
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientPostTick(Minecraft)} after every tick
	 * @param ci Callback Info
	 */
	@Inject(method = "tick", at = @At("RETURN"))
	public void hookTickPostEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientPostTick((MinecraftClient) (Object) this);
	}
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientShutdown()} when the minecraft client is shutting down
	 * @param ci Callback Info
	 */
	@Inject(method = "stop", at = @At("HEAD"))
	public void hookStopEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientShutdown();
	}
	
}
