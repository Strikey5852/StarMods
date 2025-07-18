package net.strikey.improvedglowsquids.mixin.client;

import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SheepWoolFeatureRenderer.class)
public interface AccessorSheepWoolFeatureRenderer {
    @Accessor("model")
    SheepWoolEntityModel<SheepEntity> improvedGlowSquids$getWoolModel();
}
