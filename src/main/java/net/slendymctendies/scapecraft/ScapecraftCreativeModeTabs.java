package net.slendymctendies.scapecraft;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.slendymctendies.scapecraft.item.ScapecraftItems;

public class ScapecraftCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Scapecraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RUNES_TAB = CREATIVE_MODE_TABS.register("runes_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ScapecraftItems.BLANKRUNE.get()))
                    .title(Component.translatable("creativetab.runes_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ScapecraftItems.BLANKRUNE.get());
                        output.accept(ScapecraftItems.FIRERUNE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
