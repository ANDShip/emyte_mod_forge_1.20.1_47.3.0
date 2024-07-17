package net.andship.emyte_mod.datagen;

import net.andship.emyte_mod.Emyte_Mod;
import net.andship.emyte_mod.block.ModBlocks;
import net.andship.emyte_mod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Emyte_Mod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.BLOCK_DETECTOR_VALUABLES)
                .add(ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get()).add(Blocks.MOSS_BLOCK);

    }
}
