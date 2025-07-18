package net.strikey.improvedglowsquids.mixin.client;

import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.util.DyeColor;
import net.strikey.improvedglowsquids.util.GlowingStateTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BannerBlockEntityRenderer.class)
public abstract class BannerBlockEntityRendererMixin {

    @Inject(
            method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0)
    )
    private void injectGlowLayer(
            BannerBlockEntity bannerBlockEntity,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            CallbackInfo ci
    ) {
        if (!(bannerBlockEntity instanceof GlowingStateTracker glowingStateTracker)) return;
        if (!glowingStateTracker.improvedGlowSquids$isGlowing()) return;

        BannerPatternsComponent patterns = bannerBlockEntity.getPatterns();
        DyeColor baseColor = bannerBlockEntity.getColorForState();

        // Get banner model part
        ModelPart canvas = ((AccessorBannerBlockEntityRenderer) this).getBanner();

        // Render glowing full-bright banner using existing transform stack
        net.minecraft.client.render.block.entity.BannerBlockEntityRenderer.renderCanvas(
                matrices,
                vertexConsumers,
                15728880, // full-bright light value
                overlay,
                canvas,
                TexturedRenderLayers.BANNER_BASE,
                true,
                baseColor,
                patterns
        );
    }
}
