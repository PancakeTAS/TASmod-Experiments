package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.MouseInput;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(MouseInput.class)
public class MixinMouseInputVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * updateMouse() -> Mouse.getDX() redirects to VirtualMouse.getDX()
	 * @return Virtual mouse event delta x
	 */
	@Redirect(method = "updateMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getDX()I", remap = false))
	public int redirect_updateMouse_getDX() {
		return VirtualMouse.getDX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * updateMouse() -> Mouse.getDY() redirects to VirtualMouse.getDY()
	 * @return Virtual mouse event delta y
	 */
	@Redirect(method = "updateMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getDY()I", remap = false))
	public int redirect_updateMouse_getDY() {
		return VirtualMouse.getDY();
	}
	
}
