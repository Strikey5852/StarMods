package net.strikey.improvedglowsquids.mixin.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BannerBlockEntityRenderer.class)
public interface AccessorBannerBlockEntityRenderer {
    @Accessor("banner") ModelPart getBanner();
}
