package com.minecrafttas.tasmod.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.Timer;

/**
 * In order to access some fields of the timer class, an accessor is required.
 * @author Pancake
 */
@Mixin(Timer.class)
public interface AccessTimer {

	@Accessor
	public void setLastSyncSysClock(long i);
	
}
