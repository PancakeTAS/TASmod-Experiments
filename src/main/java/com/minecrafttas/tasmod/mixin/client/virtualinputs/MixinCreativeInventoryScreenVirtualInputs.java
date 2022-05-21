package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(CreativeInventoryScreen.class)
public class MixinCreativeInventoryScreenVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouse() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_handleMouse_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * render() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_render_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
}
