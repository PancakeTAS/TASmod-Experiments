package com.minecrafttas.tasmod.client.virtualinputs;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.TASmod.Tool;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

/**
 * Virtual mouse replacing the LWJGL mouse. This mouse manages buttons and positions from LWJGL and custom sources.
 * @author Pancake
 */
public class VirtualMouse {

	/**
	 * This is the mouse button replica. 
	 */
	private static boolean[] buttonStates = new boolean[512];
	
	/**
	 * This is the button code of the button in the current event
	 */
	private static int eventButton;
	
	/**
	 * This is the button state of the button in the current event
	 */
	private static boolean eventButtonState;
	
	/**
	 * This is the mouse wheel progress
	 */
	private static int eventDWheel;
	
	/**
	 * This is the x coordinate of the mouse
	 */
	private static int x;
	
	/**
	 * This is the y coordinate of the mouse
	 */
	private static int y;
	
	/**
	 * This is the delta x coordinate of the mouse updating every tick
	 */
	private static int dx;
	
	/**
	 * This is the delta y coordinate of the mouse updating every tick
	 */
	private static int dy;
	
	/**
	 * This is the delta x coordinate that is being reset after catched once and actually returned in getDX
	 */
	private static int rdx;
	
	/**
	 * This is the delta y coordinate that is being reset after catched once and actually returned in getDY
	 */
	private static int rdy;
	
	/**
	 * This method updates getDX and getDY and is being called on tick
	 */
	public static void poll() {
		VirtualMouse.dx = VirtualMouse.rdx = Mouse.getDX();
		VirtualMouse.dy = VirtualMouse.rdy = Mouse.getDY();
	}
	
	/**
	 * This method is a reimplementation of LWJGL's next() with additional input sources.
	 * It fetches inputs from the mouse and loads the next event packet, so that getEventXXX returns data of that specific packet.
	 * In the case of this virtual mouse, it also updates the internal mouse replica which is being used by isButtonDown and getX,getY
	 */
	public static boolean next() {
		TASmod.LOGGER.debug("Processing next mouse event...");
		boolean hasNext;
		
		/* Input Source 1: The actual mouse */
		hasNext = Mouse.next(); // Fetch the actual mouse for future events
		// If the actual mouse has any events, fetch them.
		if (hasNext) {
			VirtualMouse.eventButton = Mouse.getEventButton();
			VirtualMouse.eventButtonState = Mouse.getEventButtonState();
			VirtualMouse.eventDWheel = Mouse.getEventDWheel();
			VirtualMouse.x = Mouse.getX();
			VirtualMouse.y = Mouse.getY();
		}
		/* Input Source 2 (example): The playback file. Add custom input packet sources here into an else block and update hasNext for future input sources. */
		
		TASmod.LOGGER.debug("Processed next mouse event");
		if (hasNext) VirtualMouse.buttonStates[VirtualMouse.eventButton+256] = VirtualMouse.eventButtonState; // Update the keyboard replica
		return hasNext;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventButton()
	 * This method returns the event button of the current packet 
	 * @return Event button code
	 */
	public static int getEventButton() {
		return VirtualMouse.eventButton;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventButtonState()
	 * This method returns the event button state of the current packet
	 * @return Event button state
	 */
	public static boolean getEventButtonState() {
		return VirtualMouse.eventButtonState;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventKeyState()
	 * This method returns the event key state of the current packet
	 * @return Event scroll wheel
	 */
	public static int getEventDWheel() {
		return VirtualMouse.eventDWheel;
	}
	
	/**
	 * This method is a custom reimplementation of LWJGL's isButtonDown. Instead of checking the actual mouse, this will check the virtual mouse replica.
	 * @param buttoncode Button Code
	 * 
	 * @return Whether given Button Code is pressed or not
	 */
	public static boolean isButtonDown(int buttoncode) {
		return VirtualMouse.buttonStates[buttoncode+256];
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventX()
	 * This method returns the current x position of the virtual mouse
	 * @return X pos
	 */
	public static int getEventX() {
		return VirtualMouse.x;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getEventY()
	 * This method returns the current y position of the virtual mouse
	 * @return Y pos
	 */
	public static int getEventY() {
		return VirtualMouse.y;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getX()
	 * Custom implemented, does the same as getEventX()
	 * @return X pos
	 */
	public static int getX() {
		return VirtualMouse.x;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getY()
	 * @return Y pos
	 */
	public static int getY() {
		return VirtualMouse.y;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getDX()
	 * It returns the delta position of the mouse x coordinate
	 * @return Delta x pos
	 */
	public static int getDX() {
		int ret = VirtualMouse.rdx;
		VirtualMouse.rdx = 0;
		return ret;
	}
	
	/**
	 * This method is a reimplementation of LWJGL's getDY()
	 * It returns the delta position of the mouse y coordinate
	 * @return Delta y pos
	 */
	public static int getDY() {
		int ret = VirtualMouse.rdy;
		VirtualMouse.rdy = 0;
		return ret;
	}
	
	/**
	 * Renders the current keyStates to the screen
	 * 
	 * @param width Width of the screen (scaled)
	 * @param height Height of the screen (scaled)
	 */
	@Tool
	public static void render(int width, int height) {
		// Main buttons
		VirtualMouse.renderButtonBox(330, 50, 20, 20, 0);
		VirtualMouse.renderButtonBox(355, 50, 20, 20, 2);
		VirtualMouse.renderButtonBox(380, 50, 20, 20, 1);
		
		// Mouse wheel up and down
		Gui.drawRect(355, 25, 355+20+2, 25+20+2, VirtualMouse.getEventDWheel() > 0 ? 0x80FFFFFF : 0x40000000);
		Minecraft.getMinecraft().fontRenderer.drawString("^", 1+355+20/2-Minecraft.getMinecraft().fontRenderer.getStringWidth("^")/2, 1+25+20/2-Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT/2, VirtualMouse.getEventDWheel() > 0 ? 0 : 0xFFFFFF);
		Gui.drawRect(355, 75, 355+20+2, 75+20+2, VirtualMouse.getEventDWheel() < 0 ? 0x80FFFFFF : 0x40000000);
		Minecraft.getMinecraft().fontRenderer.drawString("v", 1+355+20/2-Minecraft.getMinecraft().fontRenderer.getStringWidth("v")/2, 1+75+20/2-Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT/2, VirtualMouse.getEventDWheel() < 0 ? 0 : 0xFFFFFF);
	
		// Mouse delta position
		Gui.drawRect(410, 35, 410+50, 35+50, 0x40000000);
		Vector2f vec = new Vector2f(VirtualMouse.dx, VirtualMouse.dy);
		if (VirtualMouse.dx != 0 && VirtualMouse.dy != 0)
			vec.normalise(vec);
		int x = (int) (vec.getX()*25+25);
		int y = (int) (vec.getY()*-25+25);
		Gui.drawRect(410+x, 35+y, 410+x+2, 35+y+2, 0x80FFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawString(VirtualMouse.dx + " " + VirtualMouse.dy, 410, 25, 0xFFFFFF);
	}
	
	/**
	 * Renders a button box with a custom size to the screen.
	 * 
	 * @param x X position of the box
	 * @param y Y position of the box
	 * @param width Width of the box
	 * @param height Height of the box
	 * @param buttoncode Button code of the box
	 */
	@Tool
	public static void renderButtonBox(int x, int y, int width, int height, int buttoncode) {
		Gui.drawRect(x, y, x+width+2, y+height+2, VirtualMouse.buttonStates[buttoncode+256] ? 0x80FFFFFF : 0x40000000);
		String bchar = "";
		switch (buttoncode) {
			case 0:
				bchar = "LC";
				break;
			case 1:
				bchar = "RC";
				break;
			case 2:
				bchar = "MC";
				break;
		}
		Minecraft.getMinecraft().fontRenderer.drawString(bchar, 1+x+width/2-Minecraft.getMinecraft().fontRenderer.getStringWidth(bchar)/2, 1+y+height/2-Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT/2, (!VirtualMouse.buttonStates[buttoncode+256] ? Mouse.isButtonDown(buttoncode) ? 0x00AA00 : 0xFFFFFF : 0x000000));
	}
	
}
