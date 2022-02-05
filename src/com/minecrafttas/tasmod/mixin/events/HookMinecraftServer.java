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
	 * 
	 * IMPLEMENTATION NOTICE:
	 * The run() method is the run method for the entire server. This mixin catches when tick() is called and then triggers an event. It is very important
	 * not to inject into the head of tick() since it can be cancelled by the integrated server while the server is paused (eg. the escape menu is open).
	 * @param ci Callback Info
	 */
	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tick()V"))
	public void hookRunTickEvent(CallbackInfo ci) {
		CommonTASmod.instance.onServerTick((MinecraftServer) (Object) this);
	}
	
}
