package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.GuiChat;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GuiChat.class)
public class MixinGuiChatVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE: 
	 * drawScreen() -> Mouse.getX() redirects to VirtualMouse.getX()
	 * @return Virtual mouse x
	 */
	@Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getX()I", remap = false))
	public int redirect_drawScreen_getX() {
		return VirtualMouse.getX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * drawScreen() -> Mouse.getY() redirects to VirtualMouse.getY()
	 * @return Virtual mouse y
	 */
	@Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getY()I", remap = false))
	public int redirect_drawScreen_getY() {
		return VirtualMouse.getY();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual event mouse wheel
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_handleMouseInput_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}

	/**
	 * IMPLEMENTATION NOTICE: 
	 * mouseClicked() -> Mouse.getX() redirects to VirtualMouse.getX()
	 * @return Virtual mouse x
	 */
	@Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getX()I", remap = false))
	public int redirect_mouseClicked_getX() {
		return VirtualMouse.getX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * mouseClicked() -> Mouse.getY() redirects to VirtualMouse.getY()
	 * @return Virtual mouse y
	 */
	@Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getY()I", remap = false))
	public int redirect_mouseClicked_getY() {
		return VirtualMouse.getY();
	}
}
