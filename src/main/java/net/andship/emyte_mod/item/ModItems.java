package net.andship.emyte_mod.item;

import net.andship.emyte_mod.Emyte_Mod;
import net.andship.emyte_mod.item.custom.BlockDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Emyte_Mod.MOD_ID);

    public static final RegistryObject<Item> REDSTONE_FLOWER = ITEMS.register("redstone_flower",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_FLOWER_SEEDS = ITEMS.register("redstone_flower_seeds",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOCK_DETECTOR = ITEMS.register("block_detector",
            () -> new BlockDetectorItem(new Item.Properties().durability(100)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
