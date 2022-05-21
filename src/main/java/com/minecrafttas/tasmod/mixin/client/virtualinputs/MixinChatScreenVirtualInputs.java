package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.screen.ChatScreen;

/**
 * This mixin hooks the mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(ChatScreen.class)
public class MixinChatScreenVirtualInputs {

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

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual event mouse wheel
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_handleMouse_getEventDWheel() {
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
