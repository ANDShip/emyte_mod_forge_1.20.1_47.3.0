package net.andship.emyte_mod.datagen;

import net.andship.emyte_mod.Emyte_Mod;
import net.andship.emyte_mod.block.ModBlocks;
import net.andship.emyte_mod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> REDSTONE_SMELTABLES = List.of(ModItems.REDSTONE_FLOWER.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, REDSTONE_SMELTABLES, RecipeCategory.MISC, Items.REDSTONE, 0.25f, 100, "redstone");
        oreSmelting(pWriter, REDSTONE_SMELTABLES, RecipeCategory.MISC, Items.REDSTONE, 0.25f, 100, "redstone");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.REDSTONE_FLOWER_SEEDS.get())
                .unlockedBy(getHasName(ModItems.REDSTONE_FLOWER_SEEDS.get()), has(ModItems.REDSTONE_FLOWER_SEEDS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REDSTONE_FLOWER_SEEDS.get(), 9)
                .requires(ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get()), has(ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Emyte_Mod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
