package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;

import net.minecraft.client.options.KeyBinding;

/**
 * This mixin hooks the keyboard of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(KeyBinding.class)
public class MixinKeyBindingVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE:
	 * method_12137() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "method_12137", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_method_12137_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

}
