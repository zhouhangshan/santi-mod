package com.santi;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * Registers a custom creative tab holding the mod's items.
 */
public final class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SantiMod.MODID);

    public static final Supplier<CreativeModeTab> SANTI_TAB = TABS.register("santi_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.santi.santi_tab"))
                    .icon(() -> new ItemStack(ModItems.COORD_BROADCASTER.get()))
                    .displayItems((params, output) -> output.accept(ModItems.COORD_BROADCASTER.get()))
                    .build());

    private ModCreativeTabs() {
    }
}
