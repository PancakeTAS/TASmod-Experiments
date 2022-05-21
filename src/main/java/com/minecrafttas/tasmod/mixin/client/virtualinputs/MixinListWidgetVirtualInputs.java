package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.widget.ListWidget;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(ListWidget.class)
public class MixinListWidgetVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_handleMouse_getEventButton() {
		return VirtualMouse.getEventButton();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouse_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual mouse event mouse wheel
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_handleMouse_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_handleMouse_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}

}
