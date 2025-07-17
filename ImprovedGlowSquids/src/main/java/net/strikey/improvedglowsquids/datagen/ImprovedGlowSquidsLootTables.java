package net.strikey.improvedglowsquids.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.strikey.improvedglowsquids.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ImprovedGlowSquidsLootTables extends FabricBlockLootTableProvider {

    public ImprovedGlowSquidsLootTables(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.GLOW_TORCH);
        addDrop(ModBlocks.GLOW_TORCH_WALL);
    }
}
