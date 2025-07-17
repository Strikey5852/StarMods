package net.strikey.improvedglowsquids.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.strikey.improvedglowsquids.ImprovedGlowSquids;
import net.strikey.improvedglowsquids.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ImprovedGlowSquidsRecipes extends FabricRecipeProvider {

    public ImprovedGlowSquidsRecipes(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        generateGlowTorchRecipe(exporter);
    }

    private void generateGlowTorchRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.GLOW_TORCH.asItem(), 4)
                .pattern("I")
                .pattern("S")
                .input('I', Items.GLOW_INK_SAC)
                .input('S', Items.STICK)
                .criterion("has_glow_ink", conditionsFromItem(Items.GLOW_INK_SAC))
                .offerTo(exporter, Identifier.of(ImprovedGlowSquids.MOD_ID, "glow_torch"));
    }
}
