package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.widget.DelegatingRealmsClickableScrolledSelectionListWidget;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(DelegatingRealmsClickableScrolledSelectionListWidget.class)
public class MixinDelegatingRealmsClickableScrolledSelectionListWidgetVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouse_getEventDWheel() {
		return VirtualMouse.getEventButtonState();
	}

}
