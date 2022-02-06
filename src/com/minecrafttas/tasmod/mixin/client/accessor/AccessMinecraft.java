package com.minecrafttas.tasmod.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

/**
 * In order to access some fields of the minecraft class, an accessor is required.
 * @author Pancake
 */
@Mixin(Minecraft.class)
public interface AccessMinecraft {

	@Accessor
	public void setTimer(Timer t);
	
}
