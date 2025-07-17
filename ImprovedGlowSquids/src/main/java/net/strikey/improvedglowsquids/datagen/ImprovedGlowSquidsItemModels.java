package net.strikey.improvedglowsquids.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.strikey.improvedglowsquids.block.ModBlocks;

public class ImprovedGlowSquidsItemModels extends FabricModelProvider {

    public ImprovedGlowSquidsItemModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTorch(ModBlocks.GLOW_TORCH, ModBlocks.GLOW_TORCH_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // No item models to generate manually — torch handled automatically
    }
}
