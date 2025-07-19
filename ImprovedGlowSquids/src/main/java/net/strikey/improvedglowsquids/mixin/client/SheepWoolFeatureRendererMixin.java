package net.strikey.improvedglowsquids.mixin.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper.Argb;
import net.strikey.improvedglowsquids.util.GlowingStateTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepWoolFeatureRenderer.class)
public abstract class SheepWoolFeatureRendererMixin {

    @Unique
    private static final Identifier WOOL_TEXTURE = Identifier.ofVanilla("textures/entity/sheep/sheep_fur.png");

    @Inject(method = "render*", at = @At("TAIL"))
    private void improvedGlowSquids$injectGlowLayer(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                                                    SheepEntity sheep, float limbAngle, float limbDistance, float tickDelta,
                                                    float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if (!(sheep instanceof GlowingStateTracker glow)) return;
        if (!glow.improvedGlowSquids$isGlowing()) return;
        if (sheep.isSheared() || sheep.isInvisible()) return;

        int color;
        if (sheep.hasCustomName() && "jeb_".equals(sheep.getName().getString())) {
            int interval = 25;
            int cycle = sheep.age / interval + sheep.getId();
            float mix = ((float) (sheep.age % interval) + tickDelta) / interval;
            int rgb1 = SheepEntity.getRgbColor(DyeColor.byId(cycle % DyeColor.values().length));
            int rgb2 = SheepEntity.getRgbColor(DyeColor.byId((cycle + 1) % DyeColor.values().length));
            color = Argb.lerp(mix, rgb1, rgb2);
        } else {
            color = SheepEntity.getRgbColor(sheep.getColor());
        }

        SheepWoolFeatureRenderer self = (SheepWoolFeatureRenderer) (Object) this;
        SheepWoolEntityModel<SheepEntity> model = ((AccessorSheepWoolFeatureRenderer) self).improvedGlowSquids$getWoolModel();

        model.animateModel(sheep, limbAngle, limbDistance, tickDelta);
        model.setAngles(sheep, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        model.render(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEyes(WOOL_TEXTURE)), // allows color
                15728880, // full-bright
                LivingEntityRenderer.getOverlay(sheep, 0.0F),
                color
        );

    }
}
