package com.minecrafttas.tasmod.client.ticks;

import java.util.concurrent.atomic.AtomicBoolean;

import com.minecrafttas.tasmod.mixin.client.accessor.AccessMinecraftClient;
import com.minecrafttas.tasmod.mixin.client.accessor.AccessClientTickTracker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.ClientTickTracker;

/**
 * The Minecraft client uses a timer to schedule ticks. It bases of the system clock and calculates the amount of ticks to run through by dividing it by the 
 * tick length, which is 1000.0 / tps. TPS is ticks per second and is 20 in minecraft. Therefore a tick is 50.0 milliseconds long. Now we don't care about any of that.
 * This timer mod removes everything that has to do with scheduling (while being connected to a server) since this will be handled by a separate thread on the integrated server and tick sync.
 * 
 * @author Pancake
 */
public class TimerMod extends ClientTickTracker {
	private TimerMod(float tps) { super(tps); }
	
	static AtomicBoolean shouldTick = new AtomicBoolean(true);
	
	// Required variables for fixing interpolation
	private long lastMs;
	private long millisSinceTick;
	private float lastTickLength;
	
	/**
	 * Disable scheduling if the client is connected to a server by not calling the original method
	 */
	@Override 
	public void tick() {
		if (MinecraftClient.getInstance().getNetworkHandler() != null) {
			((AccessClientTickTracker) this).setLastSyncSysClock(MinecraftClient.getTime()); // update the timer so that after returning to scheduling the client won't catch up all ticks (max 10)
			this.ticksThisFrame = 0; // do not do any ticks
			long newMs = System.currentTimeMillis();
			if (TimerMod.shouldTick.compareAndSet(true, false)) {
				this.ticksThisFrame++;
				this.lastTickLength = newMs - this.millisSinceTick;
				this.millisSinceTick = newMs;
				this.lastFrameDuration = 0; // Reset after the tick
			}
			// Interpolating
			this.tickDelta = (float) (newMs - this.lastMs) / this.lastTickLength;
			float newPartialTicks = this.lastFrameDuration;
			newPartialTicks += this.tickDelta;
			newPartialTicks -= (int) this.lastFrameDuration;
			if (newPartialTicks > this.lastFrameDuration) {
				this.lastFrameDuration = newPartialTicks;
			}
			this.lastMs = newMs;
			return;
		}
		this.millisSinceTick = MinecraftClient.getTime();
		this.lastMs = MinecraftClient.getTime();
		TimerMod.shouldTick.set(true); // The client should always tick if it once thrown out of the vanilla scheduling part, to make the server tick, etc.
		super.tick();
	}
	
	/**
	 * This applies the timer mod to the minecraft timer instance
	 */
	public static void applyTimerMod() {
		((AccessMinecraftClient) MinecraftClient.getInstance()).setTricker(new TimerMod(20.0f));
	}
}
