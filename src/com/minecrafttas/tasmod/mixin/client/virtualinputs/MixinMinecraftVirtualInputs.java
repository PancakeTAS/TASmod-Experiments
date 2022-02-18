package com.minecrafttas.tasmod.mixin.client.virtualinputs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.client.virtualinputs.VirtualKeyboard;
import com.minecrafttas.tasmod.client.virtualinputs.VirtualMouse;

import net.minecraft.client.Minecraft;

/**
 * This mixin hooks the keyboard and mouse of minecraft to the virtual one
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
	 * IMPLEMENTATION NOTICE: 
	 * displayGuiScreen() -> Mouse.next() redirects to VirtualMouse.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "displayGuiScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;next()Z", remap = false))
	public boolean redirect_displayGuiScreen_next2() {
		return VirtualMouse.next();
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
	
	/**
	 * 
	 * Hooks for
	 * runTickMouse()
	 * 
	 * 
	 */
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickMouse() -> Mouse.next() redirects to VirtualMouse.next()
	 * @return Virtual next packet state
	 */
	@Redirect(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;next()Z", remap = false))
	public boolean redirect_runTickMouse_next() {
		return VirtualMouse.next();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickMouse() -> Mouse.getEventButton() redirects to VirtualMouse.getEventButton()
	 * @return Virtual event button
	 */
	@Redirect(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
	public int redirect_runTickMouse_getEventButton() {
		return VirtualMouse.getEventButton();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickMouse() -> Mouse.getEventButtonState() redirects to VirtualMouse.getEventButtonState()
	 * @return Virtual event button state
	 */
	@Redirect(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
	public boolean redirect_runTickMouse_getEventButtonState() {
		return VirtualMouse.getEventButtonState();
	}
	
	/**
	 * IMPLEMENTATION NOTICE: 
	 * runTickMouse() -> Mouse.getEventDWheel() redirects to VirtualMouse.getEventDWheel()
	 * @return Virtual event button state
	 */
	@Redirect(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventDWheel()I", remap = false))
	public int redirect_runTickMouse_getEventDWheel() {
		return VirtualMouse.getEventDWheel();
	}
	
}
