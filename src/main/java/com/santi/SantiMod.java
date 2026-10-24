package com.santi;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

/**
 * Main entry point of the mod.
 */
@Mod(SantiMod.MODID)
public final class SantiMod {

    public static final String MODID = "santi";

    public SantiMod(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
    }
}
