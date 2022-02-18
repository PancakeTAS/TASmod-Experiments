package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.renderer.EntityRenderer;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(EntityRenderer.class)
public class MixinEntityRendererVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE: 
	 * updateCameraAndRender() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual mouse buttoncheck
	 */
	@Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	public boolean redirect_updateCameraAndRender_isButtonDown(int key) {
		return VirtualMouse.isButtonDown(key);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * updateCameraAndRender() -> Mouse.getX() redirects to VirtualMouse.getX()
	 * @return Virtual mouse x
	 */
	@Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getX()I", remap = false))
	public int redirect_updateCameraAndRender_getX() {
		return VirtualMouse.getX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * updateCameraAndRender() -> Mouse.getY() redirects to VirtualMouse.getY()
	 * @return Virtual mouse y
	 */
	@Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getY()I", remap = false))
	public int redirect_updateCameraAndRender_getY() {
		return VirtualMouse.getY();
	}
	
}
