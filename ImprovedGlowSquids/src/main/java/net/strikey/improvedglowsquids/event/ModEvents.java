package net.strikey.improvedglowsquids.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.strikey.improvedglowsquids.util.GlowingStateTracker;

public class ModEvents {

    public static void registerEvents() {
        // Banner glowing toggle
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
                    if (blockEntity instanceof GlowingStateTracker glowingStateTracker) {
                        if (glowingStateTracker.improvedGlowSquids$isGlowing()) return ActionResult.PASS;
                        glowingStateTracker.improvedGlowSquids$setGlowing(true);
                        bannerEntity.markDirty();
                        world.updateListeners(pos, state, state, 3);
                    }
                    if (!player.isCreative()) heldItem.decrement(1);
                }
                return ActionResult.SUCCESS;
            }

            if (heldItem.getItem() == Items.INK_SAC) {
                if (!world.isClient) {
                    if (blockEntity instanceof GlowingStateTracker glowingStateTracker) {
                        if (!glowingStateTracker.improvedGlowSquids$isGlowing()) return ActionResult.PASS;
                        glowingStateTracker.improvedGlowSquids$setGlowing(false);
                        bannerEntity.markDirty();
                        world.updateListeners(pos, state, state, 3);
                    }
                    if (!player.isCreative()) heldItem.decrement(1);
                }
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });

        // Sheep glowing toggle
        UseEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
            if (!(entity instanceof SheepEntity sheep)) {
                return ActionResult.PASS;
            }

            if (!(sheep instanceof GlowingStateTracker glowTracker)) {
                return ActionResult.PASS;
            }

            ItemStack heldItem = player.getStackInHand(hand);

            if (heldItem.getItem() == Items.GLOW_INK_SAC) {
                if (!world.isClient) {
                    if (glowTracker.improvedGlowSquids$isGlowing()) return ActionResult.PASS;

                    glowTracker.improvedGlowSquids$setGlowing(true);

                    if (!player.isCreative()) heldItem.decrement(1);
                }
                return ActionResult.SUCCESS;
            }

            if (heldItem.getItem() == Items.INK_SAC) {
                if (!world.isClient) {
                    if (!glowTracker.improvedGlowSquids$isGlowing()) return ActionResult.PASS;

                    glowTracker.improvedGlowSquids$setGlowing(false);

                    if (!player.isCreative()) heldItem.decrement(1);
                }
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }
}
