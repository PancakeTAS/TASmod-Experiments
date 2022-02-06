package com.minecrafttas.tasmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.tasmod.CommonTASmod;
import com.minecrafttas.tasmod.ticks.TickSyncServer;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;

/**
 * This mixin does various changes to the minecraft server class.
 * 
 * @author Pancake
 */
@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
	
	/**
	 * TICK SYNC:
	 * This mixin cancels the minecraft server ticking entirely. The server cannot tick anymore. The ticking will be done
	 * by another redirect below
	 * 
	 * @param server The minecraft server that tick() was called on
	 */
	@Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tick()V"))
	public void cancelDefaultTick(MinecraftServer server) {
		// just.. don't run it?
	}
	
	/**
	 * TICK SYNC:
	 * This mixin cancels the Thread.sleep removing the server tick scheduling.
	 * Then it adds a custom tick scheduler from the TickSyncServer class.
	 * While ticking it also triggers the pre and post tick events in CommonTASmod
	 * 
	 * @param something This is the time it should normally sleep, but since this messing with the scheduling it will return some random value
	 * @throws Throwable Thrown when the server thread is interrupted.
	 */
	@Redirect(method = "run", at = @At(value = "INVOKE", target = "Ljava/lang/Thread;sleep(J)V"))
	public void customTick(long something) throws Throwable {
		if (TickSyncServer.shouldTick.compareAndSet(true, false) || playerList.getCurrentPlayerCount() == 0) {
			// Calculate the time a tick took
			long time = System.currentTimeMillis();
			// Tick the server
			CommonTASmod.instance.onServerTick((MinecraftServer) (Object) this);
			this.tick();
			CommonTASmod.instance.onServerPostTick((MinecraftServer) (Object) this);
			// Finish calculating the time a tick took
			long tickTime = System.currentTimeMillis() - time;
			Thread.sleep(Math.max(1, 50 - tickTime)); // wait AT LEAST 50 milliseconds before processing the next tick
		} else {
			Thread.sleep(1); // Let the cpu rest, while also capping tickrate at 100 coincidentally
		}
	}
	
	@Shadow
	protected abstract void tick();
	@Shadow
	public PlayerList playerList;
	
}
