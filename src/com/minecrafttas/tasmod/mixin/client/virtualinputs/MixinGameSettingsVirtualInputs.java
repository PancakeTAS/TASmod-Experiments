package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.settings.GameSettings;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GameSettings.class)
public class MixinGameSettingsVirtualInputs {
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isKeyDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "isKeyDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_isKeyDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isKeyDown() -> Mouse.isButtonDown() redirects to VirtualMouse.isButtonDown()
	 * @return Virtual button state
	 */
	@Redirect(method = "isKeyDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false))
	private static boolean redirect_isKeyDown_isButtonDown(int code) {
		return VirtualMouse.isButtonDown(code);
	}
	
}
