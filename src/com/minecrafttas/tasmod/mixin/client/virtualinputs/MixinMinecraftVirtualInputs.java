package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;

import net.minecraft.client.Minecraft;

/**
 * This mixin hooks the keyboard of minecraft to the virtual one
 * @author Pancake
 */
@Mixin(Minecraft.class)
public class MixinMinecraftVirtualInputs {
	
	/**
	 * 
	 * Hooks for
	 * dispatchKeypresses()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * dispatchKeypresses() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_dispatchKeypresses_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * dispatchKeypresses() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_dispatchKeypresses_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * dispatchKeypresses() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_dispatchKeypresses_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}

	/**
	 * 
	 * Hooks for
	 * displayGuiScreen()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * displayGuiScreen() -> Keyboard.next() redirects to VirtualKeyboard.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "displayGuiScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z", remap = false))
	public boolean redirect_displayGuiScreen_next() {
		return VirtualKeyboard.next();
	}
	
	/**
	 * 
	 * Hooks for
	 * runTickKeyboard()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickKeyboard() -> Keyboard.next() redirects to VirtualKeyboard.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z", remap = false))
	public boolean redirect_runTickKeyboard_next() {
		return VirtualKeyboard.next();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickKeyboard() -> Keyboard.isKeyDown() redirects to VirtualKeyboard.isKeyDown()
	 * @return Virtual key state
	 */
	@Redirect(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z", remap = false))
	public boolean redirect_runTickKeyboard_isKeyDown(int code) {
		return VirtualKeyboard.isKeyDown(code);
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickKeyboard() -> Keyboard.getEventKey() redirects to VirtualKeyboard.getEventKey()
	 * @return Virtual event key
	 */
	@Redirect(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", remap = false))
	public int redirect_runTickKeyboard_getEventKey() {
		return VirtualKeyboard.getEventKey();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickKeyboard() -> Keyboard.getEventCharacter() redirects to VirtualKeyboard.getEventCharacter()
	 * @return Virtual event character
	 */
	@Redirect(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventCharacter()C", remap = false))
	public char redirect_runTickKeyboard_getEventCharacter() {
		return VirtualKeyboard.getEventCharacter();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickKeyboard() -> Keyboard.getEventKeyState() redirects to VirtualKeyboard.getEventKeyState()
	 * @return Virtual event key state
	 */
	@Redirect(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
	public boolean redirect_runTickKeyboard_getEventKeyState() {
		return VirtualKeyboard.getEventKeyState();
	}
	
}
