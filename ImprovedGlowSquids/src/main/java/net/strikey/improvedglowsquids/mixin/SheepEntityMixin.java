package net.strikey.improvedglowsquids.mixin;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.nbt.NbtCompound;
import net.strikey.improvedglowsquids.util.GlowingStateTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin implements GlowingStateTracker {

    @Unique
    private boolean improvedGlowSquids$glowing = false;

    @Override
    @Unique
    public boolean improvedGlowSquids$isGlowing() {
        return improvedGlowSquids$glowing;
    }

    @Override
    @Unique
    public void improvedGlowSquids$setGlowing(boolean glowing) {
        this.improvedGlowSquids$glowing = glowing;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void saveGlowingFlag(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("Glow", improvedGlowSquids$glowing);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void loadGlowingFlag(NbtCompound nbt, CallbackInfo ci) {
        this.improvedGlowSquids$glowing = nbt.getBoolean("Glow");
    }
}
