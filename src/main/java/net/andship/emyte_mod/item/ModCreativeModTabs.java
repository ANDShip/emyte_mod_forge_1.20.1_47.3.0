package net.andship.emyte_mod.item;

import net.andship.emyte_mod.Emyte_Mod;
import net.andship.emyte_mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Emyte_Mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EMYTE_TAB = CREATIVE_MODE_TABS.register("emyte_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REDSTONE_FLOWER.get()))
                    .title(Component.translatable("creativetab.emyte_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.REDSTONE_FLOWER.get());
                        output.accept(ModItems.REDSTONE_FLOWER_SEEDS.get());

                        output.accept(ModBlocks.REDSTONE_FLOWER_SEEDS_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
