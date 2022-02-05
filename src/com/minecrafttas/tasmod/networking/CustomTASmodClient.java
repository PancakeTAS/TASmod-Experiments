package com.minecrafttas.tasmod.networking;

import com.minecrafttas.tasmod.exceptions.ClientAlreadyRunningException;

import scala.reflect.internal.Trees.If;

/**
 * The TASmod itself has a custom connection running next to the minecraft one. 
 * It's necessary since the integrated packet connection is tick-based and therefore cannot communicate inbetween ticks.
 * 
 * IMPLEMENTATION NOTICE:
 * The client creates a separate thread to run off so that it's non-blocking. Therefore it uses a queue for outgoing packets.
 * @author Pancake
 */
public class CustomTASmodClient {

	/**
	 * Once the client enters connect phase a separate client thread is created.
	 * 
	 * IMPLEMENTATION NOTICE:
	 * Called from ClientTASmod.
	 * 
	 * @throws If the last client wasn't succesfully shut down it will throw an exception and forcefully shut down the client
	 */
	public static void createClient() throws ClientAlreadyRunningException {
		
	}
	
}
