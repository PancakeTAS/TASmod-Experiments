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
	 * Triggers an Event in {@link CommonTASmod#onServerTick(Minecraft)} before every tick
	 * @param ci Callback Info
	 */
	@Inject(method = "tick", at = @At("HEAD"))
	public void hookRunTickEvent(CallbackInfo ci) {
		CommonTASmod.instance.onServerTick((MinecraftServer) (Object) this);
	}
	
}
