package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.gui.GuiScreen;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(GuiScreen.class)
public class MixinGuiScreenVirtualInputs {
	
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
	 * handleMouseInput()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_handleMouseInput_getEventButton() {
		return VirtualMouse.getEventButton();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_handleMouseInput_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventX() redirects to VirtualMouse.getEventX()
	 * @return Virtual mouse event x
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventX()I", remap = false))
	public int redirect_handleMouseInput_getEventX() {
		return VirtualMouse.getEventX();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleMouseInput() -> Mouse.getEventY() redirects to VirtualMouse.getEventY()
	 * @return Virtual mouse event y
	 */
	@Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventY()I", remap = false))
	public int redirect_handleMouseInput_getEventY() {
		return VirtualMouse.getEventY();
	}
	
	/**
	 * 
	 * Hooks for
	 * handleKeyboardInput()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyboardInput() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "handleKeyboardInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_handleKeyboardInput_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyboardInput() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "handleKeyboardInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_handleKeyboardInput_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyboardInput() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "handleKeyboardInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_handleKeyboardInput_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}

	/**
	 * 
	 * Hooks for
	 * isAltKeyDown()
	 * isCtrlKeyDown()
	 * isShiftKeyDown()
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isAltKeyDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "isAltKeyDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_isAltKeyDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isCtrlKeyDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "isCtrlKeyDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_isCtrlKeyDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * isShiftKeyDown() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "isShiftKeyDown", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	private static boolean redirect_isShiftKeyDown_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
}
