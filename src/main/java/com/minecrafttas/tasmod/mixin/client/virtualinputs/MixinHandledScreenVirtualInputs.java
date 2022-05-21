package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;

import net.minecraft.client.gui.screen.ingame.HandledScreen;

/**
 * This mixin hooks the keyboard of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(HandledScreen.class)
public class MixinHandledScreenVirtualInputs {

	/**
	 * IMPLEMENTATION NOTICE:
	 * mouseClicked() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	public boolean redirect_mouseClicked_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * mouseReleased() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "mouseReleased", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	public boolean redirect_mouseReleased_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

}
