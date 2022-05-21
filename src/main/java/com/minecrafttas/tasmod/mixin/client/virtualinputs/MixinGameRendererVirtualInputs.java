package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.render.GameRenderer;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GameRenderer.class)
public class MixinGameRendererVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE: 
	 * render() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_render_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * render() -> Mouse.getX() redirects to VirtualMouse.getX()
	 * @return Virtual mouse x
	 */
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getX()I", remap = false))
	public int redirect_render_getX() {
		return VirtualMouse.getX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * render() -> Mouse.getY() redirects to VirtualMouse.getY()
	 * @return Virtual mouse y
	 */
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getY()I", remap = false))
	public int redirect_render_getY() {
		return VirtualMouse.getY();
	}
	
}
