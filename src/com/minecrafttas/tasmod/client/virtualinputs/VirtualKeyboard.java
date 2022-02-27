package com.minecrafttas.tasmod.client.virtualinputs;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.TASmod.Tool;

import net.minecraft.client.Minecraft;
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
	 * These are all overrides for rendering keybinds to the screen. THIS IS PURELY RENDERING!
	 */
	@Tool
	private static HashMap<String, String> aliases = new HashMap<String, String>() {{
		put("ESCAPE", "X");
		put("F10", "10");
		put("F11", "11");
		put("F12", "12");
		put("UP", "^");
		put("LEFT", "<");
		put("RIGHT", ">");
		put("DOWN", "v");
		put("ADD", "+");
		put("SUBTRACT", "-");
		put("DIVIDE", "/");
		put("MULTIPLY", "*");
		put("DECIMAL", ".");
		put("RCONTROL", "RC");
		put("LCONTROL", "LC");
		put("RMENU", "RM");
		put("LMENU", "LM");
		put("SPACE", "-");
		put("RETURN", ">");
		put("BACK", "<");
		put("LSHIFT", "LS");
		put("RSHIFT", "RS");
		put("CAPITAL", "CP");
		put("GRAVE", "`");
		put("TAB", "TB");
		put("PRIOR", "^");
		put("NEXT", "v");
		put("INSERT", "L1");
		put("DELETE", "L4");
		put("END", "L5");
		put("MINUS", "-");
		put("EQUALS", "=");
		put("END", "L5");
		put("LBRACKET", "[");
		put("RBRACKET", "]");
		put("PAUSE", "=");
		put("SCROLL", "|");
		put("SEMICOLON", ";");
		put("COLON", ":");
		put("APOSTROPHE", "'");
		put("COMMA", ",");
		put("PERIOD", ".");
		put("SLASH", "/");
	}};
	
	/**
	 * This method is a reimplementation of LWJGL's next() with additional input sources.
	 * It fetches inputs from the keyboard and loads the next event packet, so that getEventXXX returns data of that specific packet.
	 * In the case of this virtual keyboard, it also updates the internal keyboard replica which is being used by isKeyDown
	 */
	public static boolean next() {
		TASmod.LOGGER.trace("Processing next keyboard event...");
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
		
		TASmod.LOGGER.trace("Processed next keyboard event");
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
	@Tool
	public static void render(int width, int height) {
		/* Render main keys */		
		// ~ to =
		int x = 5;
		for (int i = 0; i < 13; i++) {
			VirtualKeyboard.renderKeyBox(x, 25, i+Keyboard.KEY_ESCAPE == Keyboard.KEY_ESCAPE ? Keyboard.KEY_GRAVE : i+Keyboard.KEY_ESCAPE);
			x += 15;
		}
		// Q to |
		x = 10+15;
		for (int i = 0; i < 13; i++) {
			VirtualKeyboard.renderKeyBox(x, 40, i+Keyboard.KEY_Q == Keyboard.KEY_RETURN ? 255 : i+Keyboard.KEY_Q);
			x += 15;
		}
		// A to "
		x = 12+15;
		for (int i = 0; i < 11; i++) {
			VirtualKeyboard.renderKeyBox(x, 55, i+Keyboard.KEY_A);
			x += 15;
		}
		// Z to ?
		x = 17+15;
		for (int i = 0; i < 10; i++) {
			VirtualKeyboard.renderKeyBox(x, 70, i+Keyboard.KEY_Z);
			x += 15;
		}
		
		/* Render modifying keys */
		VirtualKeyboard.renderKeyBox(5, 40, 17, 12, Keyboard.KEY_TAB);
		VirtualKeyboard.renderKeyBox(5, 55, 19, 12, Keyboard.KEY_CAPITAL);
		VirtualKeyboard.renderKeyBox(5, 70, 24, 12, Keyboard.KEY_LSHIFT);
		VirtualKeyboard.renderKeyBox(5, 85, 17, 12, Keyboard.KEY_LCONTROL);
		VirtualKeyboard.renderKeyBox(25, 85, 255);
		VirtualKeyboard.renderKeyBox(40, 85, 17, 12, Keyboard.KEY_LMENU);
		VirtualKeyboard.renderKeyBox(60, 85, 87, 12, Keyboard.KEY_SPACE);
		VirtualKeyboard.renderKeyBox(150, 85, 17, 12, Keyboard.KEY_RMENU);
		VirtualKeyboard.renderKeyBox(170, 85, 255);
		VirtualKeyboard.renderKeyBox(185, 85, 255);
		VirtualKeyboard.renderKeyBox(200, 85, 17, 12, Keyboard.KEY_RCONTROL);
		VirtualKeyboard.renderKeyBox(182, 70, 35, 12, Keyboard.KEY_RSHIFT);
		VirtualKeyboard.renderKeyBox(192, 55, 25, 12, Keyboard.KEY_RETURN);
		VirtualKeyboard.renderKeyBox(200, 25, 17, 12, Keyboard.KEY_BACK);
		
		/* Render ESC to Pause */
		VirtualKeyboard.renderKeyBox(5, 5, Keyboard.KEY_ESCAPE);
		// F1 to F4
		x = 30;
		for (int i = 0; i < 4; i++) {
			VirtualKeyboard.renderKeyBox(x, 5, i+Keyboard.KEY_F1);
			x += 15;
		}
		// F5 to F8
		x = 95;
		for (int i = 0; i < 4; i++) {
			VirtualKeyboard.renderKeyBox(x, 5, i+Keyboard.KEY_F5);
			x += 15;
		}
		// F9 to F12
		x = 160;
		for (int i = 0; i < 4; i++) {
			VirtualKeyboard.renderKeyBox(x, 5, i + (i < 2 ? Keyboard.KEY_F9 : 0x55));
			x += 15;
		}
		// Print to Pause
		VirtualKeyboard.renderKeyBox(222, 5, 255);
		VirtualKeyboard.renderKeyBox(237, 5, Keyboard.KEY_SCROLL);
		VirtualKeyboard.renderKeyBox(252, 5, Keyboard.KEY_PAUSE);
		// Insert to Page up
		VirtualKeyboard.renderKeyBox(222, 25, Keyboard.KEY_INSERT);
		VirtualKeyboard.renderKeyBox(237, 25, 255);
		VirtualKeyboard.renderKeyBox(252, 25, Keyboard.KEY_PRIOR);
		// Insert to Page up
		VirtualKeyboard.renderKeyBox(222, 40, Keyboard.KEY_DELETE);
		VirtualKeyboard.renderKeyBox(237, 40, Keyboard.KEY_END);
		VirtualKeyboard.renderKeyBox(252, 40, Keyboard.KEY_NEXT);
		
		// Arrow keys
		VirtualKeyboard.renderKeyBox(222, 85, Keyboard.KEY_LEFT);
		VirtualKeyboard.renderKeyBox(237, 85, Keyboard.KEY_DOWN);
		VirtualKeyboard.renderKeyBox(252, 85, Keyboard.KEY_RIGHT);
		VirtualKeyboard.renderKeyBox(237, 70, Keyboard.KEY_UP);
		
		// Numpad
		VirtualKeyboard.renderKeyBox(267, 70, Keyboard.KEY_NUMPAD1);
		VirtualKeyboard.renderKeyBox(282, 70, Keyboard.KEY_NUMPAD2);
		VirtualKeyboard.renderKeyBox(297, 70, Keyboard.KEY_NUMPAD3);
		VirtualKeyboard.renderKeyBox(267, 55, Keyboard.KEY_NUMPAD4);
		VirtualKeyboard.renderKeyBox(282, 55, Keyboard.KEY_NUMPAD5);
		VirtualKeyboard.renderKeyBox(297, 55, Keyboard.KEY_NUMPAD6);
		VirtualKeyboard.renderKeyBox(267, 40, Keyboard.KEY_NUMPAD7);
		VirtualKeyboard.renderKeyBox(282, 40, Keyboard.KEY_NUMPAD8);
		VirtualKeyboard.renderKeyBox(297, 40, Keyboard.KEY_NUMPAD9);
		VirtualKeyboard.renderKeyBox(267, 25, 255);
		VirtualKeyboard.renderKeyBox(282, 25, Keyboard.KEY_DIVIDE);
		VirtualKeyboard.renderKeyBox(297, 25, Keyboard.KEY_MULTIPLY);
		VirtualKeyboard.renderKeyBox(312, 25, Keyboard.KEY_SUBTRACT);
		VirtualKeyboard.renderKeyBox(297, 85, Keyboard.KEY_DECIMAL);
		VirtualKeyboard.renderKeyBox(267, 85, 27, 12, Keyboard.KEY_NUMPAD0);
		VirtualKeyboard.renderKeyBox(312, 40, 12, 27, Keyboard.KEY_ADD);
		VirtualKeyboard.renderKeyBox(312, 70, 12, 27, 255);
	}

	/**
	 * Renders a key box with a custom size to the screen.
	 * 
	 * @param x X position of the box
	 * @param y Y position of the box
	 * @param width Width of the box
	 * @param height Height of the box
	 * @param keycode Keycode of the box
	 */
	@Tool
	public static void renderKeyBox(int x, int y, int width, int height, int keycode) {
		Gui.drawRect(x, y, x+width+2, y+height+2, VirtualKeyboard.keyStates[keycode] ? 0x80FFFFFF : 0x40000000);
		String kchar = Keyboard.getKeyName(keycode);
		if (kchar == null)
			return;
		kchar = kchar.replaceFirst("NUMPAD", "");
		if (VirtualKeyboard.aliases.containsKey(kchar))
			kchar = VirtualKeyboard.aliases.get(kchar);
		Minecraft.getMinecraft().fontRenderer.drawString(kchar, 1+x+width/2-Minecraft.getMinecraft().fontRenderer.getStringWidth(kchar)/2, 1+y+height/2-Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT/2,  (!VirtualKeyboard.keyStates[keycode] ? Keyboard.isKeyDown(keycode) ? 0x00AA00 : 0xFFFFFF : 0));
	}
	
	/**
	 * Renders a key box to the screen.
	 * 
	 * @param x X position of the box
	 * @param y Y position of the box
	 * @param keycode Keycode of the box
	 */
	@Tool
	public static void renderKeyBox(int x, int y, int keycode) {
		VirtualKeyboard.renderKeyBox(x, y, 12, 12, keycode);
	}
	
}