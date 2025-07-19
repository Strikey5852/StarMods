package net.strikey.improvedglowsquids.mixin;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
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
    private static final TrackedData<Boolean> GLOWING =
            DataTracker.registerData(SheepEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("RETURN"))
    private void registerGlowFlag(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(GLOWING, false);
    }

    @Override
    @Unique
    public boolean improvedGlowSquids$isGlowing() {
        return ((SheepEntity) (Object) this).getDataTracker().get(GLOWING);
    }

    @Override
    @Unique
    public void improvedGlowSquids$setGlowing(boolean glowing) {
        ((SheepEntity) (Object) this).getDataTracker().set(GLOWING, glowing);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void saveGlowingFlag(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("Glow", improvedGlowSquids$isGlowing());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void loadGlowingFlag(NbtCompound nbt, CallbackInfo ci) {
        improvedGlowSquids$setGlowing(nbt.getBoolean("Glow"));
    }
}
