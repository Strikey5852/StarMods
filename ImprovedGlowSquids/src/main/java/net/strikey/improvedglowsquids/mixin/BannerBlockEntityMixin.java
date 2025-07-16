package net.strikey.improvedglowsquids.mixin;

import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.strikey.improvedglowsquids.util.GlowingBanner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BannerBlockEntity.class)
public abstract class BannerBlockEntityMixin implements GlowingBanner {

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

	@Inject(method = "writeNbt", at = @At("RETURN"))
	private void saveGlowingFlag(NbtCompound nbt, RegistryWrapper.WrapperLookup registryManager, CallbackInfo ci) {
		nbt.putBoolean("ImprovedGlowSquidsGlowing", improvedGlowSquids$glowing);
	}

	@Inject(method = "readNbt", at = @At("RETURN"))
	private void loadGlowingFlag(NbtCompound nbt, RegistryWrapper.WrapperLookup registryManager, CallbackInfo ci) {
		this.improvedGlowSquids$glowing = nbt.getBoolean("ImprovedGlowSquidsGlowing");
	}
}
