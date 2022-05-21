package com.minecrafttas.tasmod.mixin.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.TASmod;

import net.minecraft.server.MinecraftServer;

/**
 * This mixin is purely responsible for the hooking up the events in {@link TASmod}.
 * @author Pancake
 */
@Mixin(MinecraftServer.class)
public class HookMinecraftServer {

	/**
	 * Triggers an Event in {@link CommonTASmod#onServerLaunch()} when the server starts
	 * @param ci Callback Info
	 */
	@Inject(method = "run", at = @At("HEAD"))
	public void hookRunEvent(CallbackInfo ci) {
		CommonTASmod.instance.onServerLaunch();
	}

	/**
	 * Triggers an Event in {@link CommonTASmod#onServerStop()} when the server shuts down
	 * @param ci Callback Info
	 */
	@Inject(method = "run", at = @At("RETURN"))
	public void hookRunEndEvent(CallbackInfo ci) {
		CommonTASmod.instance.onServerStop();
	}

}
