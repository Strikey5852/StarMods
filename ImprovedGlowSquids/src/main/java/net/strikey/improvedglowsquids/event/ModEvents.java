package net.strikey.improvedglowsquids.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.strikey.improvedglowsquids.util.GlowingBanner;  // Import the interface

public class ModEvents {

    public static void registerEvents() {
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (!(state.getBlock() instanceof BannerBlock)) {
                return ActionResult.PASS;
            }

            ItemStack heldItem = player.getStackInHand(hand);

            if (heldItem.getItem() == Items.GLOW_INK_SAC) {
                if (!world.isClient) {
                    // Get the block entity at the banner position
                    var blockEntity = world.getBlockEntity(pos);
                    if (blockEntity instanceof BannerBlockEntity bannerEntity && blockEntity instanceof GlowingBanner glowingBanner) {

                        // Toggle glowing state
                        boolean currentlyGlowing = glowingBanner.improvedGlowSquids$isGlowing();
                        if (currentlyGlowing) {
                            return ActionResult.PASS;
                        }
                        glowingBanner.improvedGlowSquids$setGlowing(true);

                        // Mark block entity dirty so data saves
                        bannerEntity.markDirty();

                        // Notify clients of block update to refresh rendering
                        world.updateListeners(pos, state, state, 3);
                    }
                    // Consume one glow ink sac from player hand
                    if (!player.isCreative()) {
                        heldItem.decrement(1);
                    }
                }
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
