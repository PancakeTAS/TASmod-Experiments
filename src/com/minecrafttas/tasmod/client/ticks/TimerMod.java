package com.minecrafttas.tasmod.client.ticks;

import java.util.concurrent.atomic.AtomicBoolean;

import com.minecrafttas.tasmod.TASmod;
import com.minecrafttas.tasmod.mixin.client.accessor.AccessMinecraft;
import com.minecrafttas.tasmod.mixin.client.accessor.AccessTimer;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

/**
 * The Minecraft client uses a timer to schedule ticks. It bases of the system clock and calculates the amount of ticks to run through by dividing it by the 
 * tick length, which is 1000.0 / tps. TPS is ticks per second and is 20 in minecraft. Therefore a tick is 50.0 milliseconds long. Now we don't care about any of that.
 * This timer mod removes everything that has to do with scheduling (while being connected to a server) since this will be handled by a separate thread on the integrated server and tick sync.
 * 
 * @author Pancake
 */
public class TimerMod extends Timer {
	private TimerMod(float tps) { super(tps); }
	
	static AtomicBoolean shouldTick = new AtomicBoolean(true);
	
	// Required variables for fixing interpolation
	private long millisSinceEnable;
	private long lastMs;
	private float ticksSinceEnable;
	
	/**
	 * Disable scheduling if the client is connected to a server by not calling the original method
	 */
	@Override 
	public void updateTimer() {
		if (Minecraft.getMinecraft().getConnection() != null) {
			((AccessTimer) this).setLastSyncSysClock(Minecraft.getSystemTime()); // update the timer so that after returning to scheduling the client won't catch up all ticks (max 10)
			this.elapsedTicks = 0; // do not do any ticks
			long newMs = System.currentTimeMillis();
			if (TimerMod.shouldTick.compareAndSet(true, false)) {
				this.elapsedTicks++;
				this.ticksSinceEnable++;
				TASmod.LOGGER.debug(String.format("Average Tickrate: %.2f", 1000.0f / ((Minecraft.getSystemTime()-this.millisSinceEnable) / this.ticksSinceEnable)));
			}
			// Interpolating
			this.elapsedPartialTicks = (float) (newMs - this.lastMs) / 50.0f;
			this.renderPartialTicks += this.elapsedPartialTicks;
			this.renderPartialTicks -= (int) this.renderPartialTicks;
			this.lastMs = newMs;
			return;
		}
		this.millisSinceEnable = Minecraft.getSystemTime();
		this.ticksSinceEnable = 0;
		this.lastMs = Minecraft.getSystemTime();
		TimerMod.shouldTick.set(true); // The client should always tick if it once thrown out of the vanilla scheduling part, to make the server tick, etc.
		super.updateTimer();
	}
	
	/**
	 * This applies the timer mod to the minecraft timer instance
	 */
	public static void applyTimerMod() {
		((AccessMinecraft) Minecraft.getMinecraft()).setTimer(new TimerMod(20.0f));
	}
}
