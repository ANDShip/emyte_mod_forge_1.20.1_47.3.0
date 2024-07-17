package net.andship.emyte_mod.datagen;

import net.andship.emyte_mod.Emyte_Mod;
import net.andship.emyte_mod.block.ModBlocks;
import net.andship.emyte_mod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Emyte_Mod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BLOCK_DETECTOR);
        simpleItem(ModItems.REDSTONE_FLOWER);
        simpleItem(ModItems.REDSTONE_FLOWER_SEEDS);
        simpleItem(ModItems.REDSTONE_FUEL);
        simpleItem(ModItems.REDSTONEBERRY);
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Emyte_Mod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
