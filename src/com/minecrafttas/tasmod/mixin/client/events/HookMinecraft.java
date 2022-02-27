package com.minecrafttas.tasmod.mixin.client.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.client.ClientTASmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This mixin is purely responsible for the hooking up the events in {@link ClientTASmod}.
 * @author Pancake
 */
@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class HookMinecraft {
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientTick(Minecraft)} before every tick
	 * @param ci Callback Info
	 */
	@Inject(method = "runTick", at = @At("HEAD"))
	public void hookRunTickEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientTick((Minecraft) (Object) this);
	}
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientPostTick(Minecraft)} after every tick
	 * @param ci Callback Info
	 */
	@Inject(method = "runTick", at = @At("RETURN"))
	public void hookRunTickPostEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientPostTick((Minecraft) (Object) this);
	}
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientShutdown()} when the Minecraft Client is shutting down
	 * @param ci Callback Info
	 */
	@Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
	public void hookShutdownMinecraftApplet(CallbackInfo ci) {
		ClientTASmod.instance.onClientShutdown();
	}
}
