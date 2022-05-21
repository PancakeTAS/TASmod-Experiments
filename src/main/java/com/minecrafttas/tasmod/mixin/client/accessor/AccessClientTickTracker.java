package com.minecrafttas.tasmod.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.ClientTickTracker;

/**
 * In order to access some fields of the client tick tracker class, an accessor is required.
 * @author Pancake
 */
@Mixin(ClientTickTracker.class)
public interface AccessClientTickTracker {

	@Accessor(value = "field_1042")
	public void setLastSyncSysClock(long i);
	
}
