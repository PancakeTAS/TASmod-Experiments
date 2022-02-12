package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;

import net.minecraft.client.gui.GuiScreen;

/**
 * This mixin hooks the keyboard of minecraft to the virtual one
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
