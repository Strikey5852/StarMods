package net.strikey.improvedglowsquids;

import net.fabricmc.api.ModInitializer;
import net.strikey.improvedglowsquids.block.ModBlocks;

public class ImprovedGlowSquids implements ModInitializer {
	public static final String MOD_ID = "improvedglowsquids";

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
	}
}