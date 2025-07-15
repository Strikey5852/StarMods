package net.strikey.improvedglowsquids;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.strikey.improvedglowsquids.block.ModBlocks;

public class ImprovedGlowSquidsClient implements ClientModInitializer {
    public static final String MOD_ID = "improvedglowsquids";

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_TORCH_WALL, RenderLayer.getCutout());
    }

}
