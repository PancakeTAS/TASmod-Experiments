package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.MinecraftClient;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(MinecraftClient.class)
public class MixinMinecraftClientVirtualInputs {
	
	/**
	 * 
	 * Hooks for
	 * handleKeyInput()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyInput() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "handleKeyInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_handleKeyInput_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyInput() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "handleKeyInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_handleKeyInput_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * handleKeyInput() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "handleKeyInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_handleKeyInput_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}

	/**
	 * 
	 * Hooks for
	 * openScreen()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * openScreen() -> Keyboard.next() redirects to VirtualKeyboard.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "openScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z", remap = false))
	public boolean redirect_openScreen_next() {
		return VirtualKeyboard.next();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * openScreen() -> Mouse.next() redirects to VirtualMouse.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "openScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;next()Z", remap = false))
	public boolean redirect_openScreen_next2() {
		return VirtualMouse.next();
	}
	
	/**
	 * 
	 * Hooks for
	 * method_12145()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12145() -> Keyboard.next() redirects to VirtualKeyboard.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "method_12145", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z", remap = false))
	public boolean redirect_method_12145_next() {
		return VirtualKeyboard.next();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12145() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "method_12145", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	public boolean redirect_method_12145_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12145() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "method_12145", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_method_12145_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12145() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "method_12145", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_method_12145_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12145() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "method_12145", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_method_12145_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}
	
	/**
	 * 
	 * Hooks for
	 * method_12141()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12141() -> Mouse.next() redirects to VirtualMouse.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "method_12141", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;next()Z", remap = false))
	public boolean redirect_method_12141_next() {
		return VirtualMouse.next();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12141() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "method_12141", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_method_12141_getEventButton() {
		return VirtualMouse.getEventButton();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12141() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "method_12141", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_method_12141_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * method_12141() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual event button state
	 */
	@Redirect(method = "method_12141", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_method_12141_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}
	
}
