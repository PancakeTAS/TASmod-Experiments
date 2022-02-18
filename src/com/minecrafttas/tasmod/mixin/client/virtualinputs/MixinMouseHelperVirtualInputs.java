package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.util.MouseHelper;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(MouseHelper.class)
public class MixinMouseHelperVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * mouseXYChange() -> Mouse.getDX() redirects to VirtualMouse.getDX()
	 * @return Virtual mouse event delta x
	 */
	@Redirect(method = "mouseXYChange", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getDX()I", remap = false))
	public int redirect_mouseXYChange_getDX() {
		return VirtualMouse.getDX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * mouseXYChange() -> Mouse.getDY() redirects to VirtualMouse.getDY()
	 * @return Virtual mouse event delta y
	 */
	@Redirect(method = "mouseXYChange", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getDY()I", remap = false))
	public int redirect_mouseXYChange_getDY() {
		return VirtualMouse.getDY();
	}
	
}
