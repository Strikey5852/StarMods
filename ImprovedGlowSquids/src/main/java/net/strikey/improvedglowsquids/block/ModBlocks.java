package net.strikey.improvedglowsquids.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.strikey.improvedglowsquids.ImprovedGlowSquids;

public class ModBlocks {

    public static final Block GLOW_TORCH = Registry.register(
            Registries.BLOCK,
            Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch"),
            new UnderwaterTorchBlock(
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .luminance(state -> 14)
                            .sounds(BlockSoundGroup.WOOD)
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque(),
                    ParticleTypes.END_ROD));

    public static final Block GLOW_TORCH_WALL = Registry.register(
            Registries.BLOCK,
            Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch_wall"),
            new UnderwaterWallTorchBlock(
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .luminance(state -> 14)
                            .sounds(BlockSoundGroup.WOOD)
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque(),
                    ParticleTypes.END_ROD));

    public static final Item GLOW_TORCH_ITEM = Registry.register(
            Registries.ITEM,
            Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch"),
            new VerticallyAttachableBlockItem(GLOW_TORCH, GLOW_TORCH_WALL, new Item.Settings(), Direction.DOWN)
    );

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(GLOW_TORCH_ITEM));
    }
}
