package com.santi;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registers the mod's items.
 */
public final class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SantiMod.MODID);

    /** The Three-Body Coordinate Broadcaster. */
    public static final DeferredItem<Item> COORD_BROADCASTER = ITEMS.register("coord_broadcaster",
            () -> new CoordBroadcasterItem(new Item.Properties()));

    private ModItems() {
    }
}
