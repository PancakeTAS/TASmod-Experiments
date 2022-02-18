package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.GuiClickableScrolledSelectionListProxy;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GuiClickableScrolledSelectionListProxy.class)
public class MixinGuiClickableScrolledSelectionListProxyVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouseInput_getEventDWheel() {
		return VirtualMouse.getEventButtonState();
	}

}
