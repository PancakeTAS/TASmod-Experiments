package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.screen.Screen;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(Screen.class)
public class MixinScreenVirtualInputs {

	/**
	 *
	 * Hooks for
	 * handleInput()
	 *
	 *
	 */

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleInput() -> Keyboard.next() redirects to VirtualKeyboard.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "handleInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z", remap = false))
	public boolean redirect_handleInput_next() {
		return VirtualKeyboard.next();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleInput() -> Mouse.next() redirects to VirtualMouse.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "handleInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;next()Z", remap = false))
	public boolean redirect_handleInput_next2() {
		return VirtualMouse.next();
	}

	/**
	 *
	 * Hooks for
	 * handleMouse()
	 *
	 *
	 */

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_handleMouse_getEventButton() {
		return VirtualMouse.getEventButton();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouse_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventX() redirects to VirtualMouse.getEventX()
	 * @return Virtual mouse event x
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventX()I", remap = false))
	public int redirect_handleMouse_getEventX() {
		return VirtualMouse.getEventX();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleMouse() -> Mouse.getEventY() redirects to VirtualMouse.getEventY()
	 * @return Virtual mouse event y
	 */
	@Redirect(method = "handleMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventY()I", remap = false))
	public int redirect_handleMouse_getEventY() {
		return VirtualMouse.getEventY();
	}

	/**
	 *
	 * Hooks for
	 * handleKeyboard()
	 *
	 *
	 */

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleKeyboard() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "handleKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_handleKeyboard_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleKeyboard() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "handleKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_handleKeyboard_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * handleKeyboard() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "handleKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_handleKeyboard_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}

	/**
	 *
	 * Hooks for
	 * hasAltDown()
	 * hasControlDown()
	 * hasShiftDown()
	 *
	 */

	/**
	 * IMPLEMENTATION NOTICE:
	 * hasAltDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "hasAltDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_hasAltDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * hasControlDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "hasControlDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_hasControlDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

	/**
	 * IMPLEMENTATION NOTICE:
	 * hasShiftDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "hasShiftDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_hasShiftDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}

}
