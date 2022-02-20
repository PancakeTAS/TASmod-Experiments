package com.minecrafttas.tasmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Loader and basic data holder for TASmod
 * @author Pancake
 */
@Mod(modid = "tasmod")
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
	
	/**
	 * This proxy is used once for the preinitialization and initialization events.
	 */
	@SidedProxy(serverSide = "com.minecrafttas.tasmod.CommonTASmod", clientSide = "com.minecrafttas.tasmod.client.ClientTASmod")
	private static CommonTASmod proxy;
	
	/**
	 * IMPLEMENTATION NOTICE:
	 * #proxy is CommonTASmod on server side and ClientTASmod on the client side
	 * 
	 * @param e The Pre initialization event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.onPreInit(e);
	}
	
	/**
	 * IMPLEMENTATION NOTICE:
	 * #proxy is CommonTASmod on server side and ClientTASmod on the client side
	 * 
	 * @param e The Pre initialization event
	 */
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.onInit(e);
	}
	
}
