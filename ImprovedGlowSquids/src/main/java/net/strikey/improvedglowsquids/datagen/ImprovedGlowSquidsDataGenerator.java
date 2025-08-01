package net.strikey.improvedglowsquids.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ImprovedGlowSquidsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();

        pack.addProvider((output, registries) -> new ImprovedGlowSquidsItemModels(output));
        pack.addProvider(ImprovedGlowSquidsLootTables::new);
        pack.addProvider(ImprovedGlowSquidsRecipes::new);
        pack.addProvider(ImprovedGlowSquidsEnglishLangProvider::new);
    }
}
