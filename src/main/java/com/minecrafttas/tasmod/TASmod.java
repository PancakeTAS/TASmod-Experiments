package com.minecrafttas.tasmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Loader and basic data holder for TASmod
 * @author Pancake
 */
public class TASmod {

	/**
	 * This shall be annotated whereever a piece of code is purely used for the Tool side of TASmod.
	 * It does not have any effect yet, howeever helps separating between Light and Full version.
	 * @author Pancake
	 */
	public static @interface Tool {

		/**
		 * This value decides whether the full or the light TASmod will load.
		 * Currently there is no way to update it ingame.
		 */
		public static boolean TOOLS = true;

	}


	// TASmod Logger for printing debug lines into the console.
	public static final Logger LOGGER = LogManager.getLogger("TASmod");

}
