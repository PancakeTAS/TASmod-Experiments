package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(targets = "net.minecraft.client.gui.screen.StatsScreen$CustomStatsListWidget")
public class MixinCustomStatsListWidgetVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE: 
	 * renderHeader() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "renderHeader", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_renderHeader_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
}
