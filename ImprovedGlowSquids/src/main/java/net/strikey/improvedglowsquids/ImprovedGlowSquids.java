package net.strikey.improvedglowsquids;

import net.fabricmc.api.ModInitializer;
import net.strikey.improvedglowsquids.block.ModBlocks;
import net.strikey.improvedglowsquids.event.ModEvents;

public class ImprovedGlowSquids implements ModInitializer {
	public static final String MOD_ID = "improvedglowsquids";

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModEvents.registerEvents();
	}
}