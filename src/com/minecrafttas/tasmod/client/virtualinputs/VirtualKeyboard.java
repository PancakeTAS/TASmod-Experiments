package com.minecrafttas.tasmod.client.virtualinputs;

import org.lwjgl.input.Keyboard;

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
		
		VirtualKeyboard.keyStates[VirtualKeyboard.eventKeyCode] = VirtualKeyboard.eventKeyState; // Update the keyboard replica
		return hasNext;
	}
	
	/**
	 * This method returns the event key of the current packet 
	 * @return Event Key Code 
	 */
	public static int getEventKey() {
		return VirtualKeyboard.eventKeyCode;
	}
	
	/**
	 * This method returns the event key of the current packet as a charater
	 * @return Event Key Character
	 */
	public static char getEventCharacter() {
		return VirtualKeyboard.eventKeyCharacter;
	}
	
	/**
	 * This method returns the event key state of the current packet
	 * @return Event Key State
	 */
	public static boolean getEventKeyState() {
		return VirtualKeyboard.eventKeyState;
	}
	
	/**
	 * This method is a custom reimplementation of LWJGL's isKeyDown. Instead of checking the actual keyboard, this will check the virtual keyboard replica.
	 * @param keycode Key Code+
	 * 
	 * @return Whether given Key Code is pressed or not
	 */
	public static boolean isKeyDown(int keycode) {
		return VirtualKeyboard.keyStates[keycode];
	}
	
}
