package com.minecrafttas.tasmod.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.ClientTickTracker;

/**
 * In order to access some fields of the minecraft client class, an accessor is required.
 * @author Pancake
 */
@Mixin(MinecraftClient.class)
public interface AccessMinecraftClient {

	@Accessor
	public void setTricker(ClientTickTracker t);
	
}
