package com.minecrafttas.tasmod.client.virtualinputs;

import org.lwjgl.input.Keyboard;

import com.minecrafttas.tasmod.TASmod;

import net.minecraft.client.gui.Gui;

/**
 * Virtual keyboard replacing the LWJGL keyboard. This keyboard manages keys from LWJGL and custom sources.
 * @author Pancake
 */
public class VirtualKeyboard {

	/**
	 * This is the keyboard replica. 
	 */
	private static boolean[] keyStates = new boolean[256];
	
	/**
	 * This is the key code of the key in the current event
	 */
	private static int eventKeyCode;
	
	/**
	 * This is the key state of the key in the current event
	 */
	private static boolean eventKeyState;
	
	/**
	 * This is the key character of the key in the current event as long as one exists
	 */
	private static char eventKeyCharacter;
	
	/**
	 * This method is a reimplementation of LWJGL's next() with additional input sources.
	 * It fetches inputs from the keyboard and loads the next event packet, so that getEventXXX returns data of that specific packet.
	 * In the case of this virtual keyboard, it also updates the internal keyboard replica which is being used by isKeyDown
	 */
	public static boolean next() {
		TASmod.LOGGER.debug("Processing next keyboard event...");
		boolean hasNext;
		
		/* Input Source 1: The actual keyboard */
		hasNext = Keyboard.next(); // Fetch the actual keyboard for future events
		// If the actual keyboard has any events, fetch them.
		if (hasNext) {
			VirtualKeyboard.eventKeyCode = Keyboard.getEventKey();
			VirtualKeyboard.eventKeyState = Keyboard.getEventKeyState();
			VirtualKeyboard.eventKeyCharacter = Keyboard.getEventCharacter();
		}
		
		/* Input Source 2 (example): The playback file. Add custom input packet sources here into an else block and update hasNext for future input sources. */
		
		TASmod.LOGGER.debug("Processed next keyboard event");
		if (hasNext && /* Fix F11 */ VirtualKeyboard.eventKeyCode != Keyboard.KEY_F11) VirtualKeyboard.keyStates[VirtualKeyboard.eventKeyCode] = VirtualKeyboard.eventKeyState; // Update the keyboard replica
		return hasNext && /* Fix F11 */ VirtualKeyboard.eventKeyCode != Keyboard.KEY_F11;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventKey()
	 * This method returns the event key of the current packet 
	 * @return Event Key Code 
	 */
	public static int getEventKey() {
		return VirtualKeyboard.eventKeyCode;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventCharacter()
	 * This method returns the event key of the current packet as a charater
	 * @return Event Key Character
	 */
	public static char getEventCharacter() {
		return VirtualKeyboard.eventKeyCharacter;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventKeyState()
	 * This method returns the event key state of the current packet
	 * @return Event Key State
	 */
	public static boolean getEventKeyState() {
		return VirtualKeyboard.eventKeyState;
	}
	
	/**
	 * This method is a custom reimplementation of LWJGL's isKeyDown. Instead of checking the actual keyboard, this will check the virtual keyboard replica.
	 * @param keycode Key Code
	 * 
	 * @return Whether given Key Code is pressed or not
	 */
	public static boolean isKeyDown(int keycode) {
		return VirtualKeyboard.keyStates[keycode];
	}
	
	/**
	 * Renders the current keyStates to the screen
	 * 
	 * @param width Width of the screen (scaled)
	 * @param height Height of the screen (scaled)
	 */
	public static void render(int width, int height) {
		/* Render main keys */		
		// ~ to =
		int x = 5;
		for (int i = 0; i < 13; i++) {
			Gui.drawRect(x, 25, x+10, 35, keyStates[i+1 == 1 ? 0x29 : i+1] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// Q to |
		x = 10+15;
		for (int i = 0; i < 13; i++) {
			Gui.drawRect(x, 40, x+10, 50, keyStates[i+0x10 == 0x1C ? 255 : i+0x10] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// A to "
		x = 12+15;
		for (int i = 0; i < 11; i++) {
			Gui.drawRect(x, 55, x+10, 65, keyStates[i+0x1E] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// Z to ?
		x = 17+15;
		for (int i = 0; i < 10; i++) {
			Gui.drawRect(x, 70, x+10, 80, keyStates[i+0x2C] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		
		/* Render modifying keys */
		Gui.drawRect(5, 40, 20, 50, keyStates[0x0F] ? 0xFFFF0000 : 0xFFFFFFFF); // Tab
		Gui.drawRect(5, 55, 22, 65, keyStates[0x3A] ? 0xFFFF0000 : 0xFFFFFFFF); // Capslock
		Gui.drawRect(5, 70, 27, 80, keyStates[0x2A] ? 0xFFFF0000 : 0xFFFFFFFF); // LShift
		Gui.drawRect(5, 85, 20, 95, keyStates[0x1D] ? 0xFFFF0000 : 0xFFFFFFFF); // LCtrl
		Gui.drawRect(25, 85, 35, 95, 0xFFFFFFFF); // LWin
		Gui.drawRect(40, 85, 55, 95, keyStates[0x38] ? 0xFFFF0000 : 0xFFFFFFFF); // LAlt
		Gui.drawRect(60, 85, 145, 95, keyStates[0x39] ? 0xFFFF0000 : 0xFFFFFFFF); // Spacebar
		Gui.drawRect(150, 85, 165, 95, keyStates[0xB8] ? 0xFFFF0000 : 0xFFFFFFFF); // RAlt
		Gui.drawRect(170, 85, 180, 95, 0xFFFFFFFF); // RWin
		Gui.drawRect(185, 85, 195, 95, 0xFFFFFFFF); // RMenu
		Gui.drawRect(200, 85, 215, 95, keyStates[0x9D] ? 0xFFFF0000 : 0xFFFFFFFF); // RCtrl
		Gui.drawRect(182, 70, 215, 80, keyStates[0x36] ? 0xFFFF0000 : 0xFFFFFFFF); // RShift
		Gui.drawRect(192, 55, 215, 65, keyStates[0x1C] ? 0xFFFF0000 : 0xFFFFFFFF); // Enter
		Gui.drawRect(200, 25, 215, 35, keyStates[0x0E] ? 0xFFFF0000 : 0xFFFFFFFF); // Backspace
		
		/* Render ESC to Pause */
		Gui.drawRect(5, 5, 15, 15, keyStates[0x1] ? 0xFFFF0000 : 0xFFFFFFFF); // Escape
		// F1 to F4
		x = 30;
		for (int i = 0; i < 4; i++) {
			Gui.drawRect(x, 5, x+10, 15, keyStates[i+0x3B] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// F5 to F8
		x = 95;
		for (int i = 0; i < 4; i++) {
			Gui.drawRect(x, 5, x+10, 15, keyStates[i+0x3F] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// F9 to F12
		x = 160;
		for (int i = 0; i < 4; i++) {
			Gui.drawRect(x, 5, x+10, 15, keyStates[i + (i < 2 ? 0x43 : 0x55)] ? 0xFFFF0000 : 0xFFFFFFFF);
			x += 15;
		}
		// Print to Pause
		Gui.drawRect(222, 5, 232, 15, 0xFFFFFFFF);
		Gui.drawRect(237, 5, 247, 15, keyStates[0x46] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(252, 5, 262, 15, keyStates[0xC5] ? 0xFFFF0000 : 0xFFFFFFFF);
		// Insert to Page up
		Gui.drawRect(222, 25, 232, 35, keyStates[0xD2] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(237, 25, 247, 35, 0xFFFFFFFF);
		Gui.drawRect(252, 25, 262, 35, keyStates[0xC9] ? 0xFFFF0000 : 0xFFFFFFFF);
		// Insert to Page up
		Gui.drawRect(222, 40, 232, 50, keyStates[0xD3] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(237, 40, 247, 50, keyStates[0xCF] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(252, 40, 262, 50, keyStates[0xD1] ? 0xFFFF0000 : 0xFFFFFFFF);
		
		// Arrow keys
		Gui.drawRect(222, 85, 232, 95, keyStates[0xCB] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(237, 85, 247, 95, keyStates[0xD0] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(252, 85, 262, 95, keyStates[0xCD] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(237, 70, 247, 80, keyStates[0xC8] ? 0xFFFF0000 : 0xFFFFFFFF);
		
		// Numpad
		Gui.drawRect(267, 70, 277, 80, keyStates[0x4F] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(282, 70, 292, 80, keyStates[0x50] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(297, 70, 307, 80, keyStates[0x51] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(267, 55, 277, 65, keyStates[0x4B] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(282, 55, 292, 65, keyStates[0x4C] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(297, 55, 307, 65, keyStates[0x4D] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(267, 40, 277, 50, keyStates[0x47] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(282, 40, 292, 50, keyStates[0x48] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(297, 40, 307, 50, keyStates[0x49] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(267, 25, 277, 35, 0xFFFFFFFF);
		Gui.drawRect(282, 25, 292, 35, keyStates[0xB5] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(297, 25, 307, 35, keyStates[0x37] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(312, 25, 322, 35, keyStates[0x4A] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(297, 85, 307, 95, keyStates[0x53] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(267, 85, 292, 95, keyStates[0x52] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(312, 40, 322, 65, keyStates[0x4E] ? 0xFFFF0000 : 0xFFFFFFFF);
		Gui.drawRect(312, 70, 322, 95, 0xFFFFFFFF);
	}
	
}
