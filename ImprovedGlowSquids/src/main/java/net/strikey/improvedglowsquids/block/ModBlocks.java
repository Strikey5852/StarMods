package net.strikey.improvedglowsquids.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.strikey.improvedglowsquids.ImprovedGlowSquids;

public class ModBlocks {

    public static final Block GLOW_TORCH = registerBlock(
            new TorchBlock(ParticleTypes.END_ROD,
                    AbstractBlock.Settings.create()
                                .noCollision()
                                .breakInstantly()
                                .luminance(state -> 14)
                                .sounds(BlockSoundGroup.WOOD)
                                .pistonBehavior(PistonBehavior.DESTROY)
                                .nonOpaque()));

    private static Block registerBlock(Block block) {
        registerBlockItem(block);
        return Registry.register(Registries.BLOCK, Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch"), block);
    }

    private static void registerBlockItem(Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch"),
                new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(GLOW_TORCH));

    }
}
