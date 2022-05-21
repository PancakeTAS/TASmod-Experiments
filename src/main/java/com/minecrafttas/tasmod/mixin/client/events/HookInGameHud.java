package com.minecrafttas.tasmod.mixin.client.events;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecrafttas.tasmod.client.ClientTASmod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.Window;

/**
 * This mixin is purely responsible for the hooking up the events in {@link ClientTASmod}.
 * @author Pancake
 */
@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public class HookInGameHud {
	
	/**
	 * Triggers an Event in {@link ClientTASmod#onClientRender(Window)} after hud has been rendered
	 * @param ci Callback Info
	 */
	@Inject(method = "render", at = @At(value = "INVOKE", target = "method_12176"))
	public void hookRenderEvent(CallbackInfo ci) {
		ClientTASmod.instance.onClientRender(new Window(client));
	}
	
	@Shadow @Final
	public MinecraftClient client;
	
}