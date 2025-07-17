package net.strikey.improvedglowsquids.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.strikey.improvedglowsquids.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ImprovedGlowSquidsEnglishLangProvider extends FabricLanguageProvider {

    public ImprovedGlowSquidsEnglishLangProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, "en_us", registries);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModBlocks.GLOW_TORCH, "Glow Torch");
    }
}
