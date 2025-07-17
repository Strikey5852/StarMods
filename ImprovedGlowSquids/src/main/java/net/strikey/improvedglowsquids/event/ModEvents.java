package net.strikey.improvedglowsquids.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.strikey.improvedglowsquids.util.GlowingBanner;

public class ModEvents {

    public static void registerEvents() {
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            var blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof BannerBlockEntity bannerEntity)) {
                return ActionResult.PASS;
            }

            ItemStack heldItem = player.getStackInHand(hand);

            if (heldItem.getItem() == Items.GLOW_INK_SAC) {
                if (!world.isClient) {
                    if (blockEntity instanceof GlowingBanner glowingBanner) {

                        if (glowingBanner.improvedGlowSquids$isGlowing()) {
                            return ActionResult.PASS;
                        }

                        glowingBanner.improvedGlowSquids$setGlowing(true);
                        bannerEntity.markDirty();
                        world.updateListeners(pos, state, state, 3);
                    }
                    if (!player.isCreative()) {
                        heldItem.decrement(1);
                    }
                }
                return ActionResult.SUCCESS;
            }

            if (heldItem.getItem() == Items.INK_SAC) {
                if (!world.isClient) {
                    if (blockEntity instanceof GlowingBanner glowingBanner) {

                        if (!glowingBanner.improvedGlowSquids$isGlowing()) {
                            return ActionResult.PASS;
                        }

                        glowingBanner.improvedGlowSquids$setGlowing(false);
                        bannerEntity.markDirty();
                        world.updateListeners(pos, state, state, 3);
                    }
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
