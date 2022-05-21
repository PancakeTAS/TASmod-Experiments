package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.options.GameOptions;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GameOptions.class)
public class MixinGameOptionsVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isPressed() -> Keyboard.isPressed() redirects to VirtualKeyboard.isPressed()
	 * @return Virtual key state
	 */
	@Redirect(method = "isPressed", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isPressed(I)Z", remap = false))
	private static boolean redirect_isPressed_isPressed(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isPressed() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual button state
	 */
	@Redirect(method = "isPressed", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	private static boolean redirect_isPressed_isButtonDown(int code) {
		return VirtualMouse.isButtonDown(code);
	}
	
}
