package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.GuiSlot;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GuiSlot.class)
public class MixinGuiSlotVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_handleMouseInput_getEventButton() {
		return VirtualMouse.getEventButton();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouseInput_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual mouse event mouse wheel
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_handleMouseInput_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_handleMouseInput_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
}
