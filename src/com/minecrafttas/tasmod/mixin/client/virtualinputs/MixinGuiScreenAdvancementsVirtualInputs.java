package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.advancements.GuiScreenAdvancements;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GuiScreenAdvancements.class)
public class MixinGuiScreenAdvancementsVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE: 
	 * drawScreen() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_drawScreen_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
}
