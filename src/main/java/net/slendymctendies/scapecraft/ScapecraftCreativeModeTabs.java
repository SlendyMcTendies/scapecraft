package net.slendymctendies.scapecraft;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.slendymctendies.scapecraft.block.ScapecraftBlocks;
import net.slendymctendies.scapecraft.item.ScapecraftItems;

public class ScapecraftCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Scapecraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RUNES_TAB = CREATIVE_MODE_TABS.register("runes_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ScapecraftItems.BLANKRUNE.get()))
                    .title(Component.translatable("creativetab.runes_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //RUNES
                        output.accept(ScapecraftItems.BLANKRUNE.get());
                        output.accept(ScapecraftItems.AIRRUNE.get());
                        output.accept(ScapecraftItems.ASTRALRUNE.get());
                        output.accept(ScapecraftItems.BLOODRUNE.get());
                        output.accept(ScapecraftItems.BODYRUNE.get());
                        output.accept(ScapecraftItems.CHAOSRUNE.get());
                        output.accept(ScapecraftItems.COSMICRUNE.get());
                        output.accept(ScapecraftItems.DEATHRUNE.get());
                        output.accept(ScapecraftItems.EARTHRUNE.get());
                        output.accept(ScapecraftItems.FIRERUNE.get());
                        output.accept(ScapecraftItems.LAWRUNE.get());
                        output.accept(ScapecraftItems.MINDRUNE.get());
                        output.accept(ScapecraftItems.NATURERUNE.get());
                        output.accept(ScapecraftItems.SOULRUNE.get());
                        output.accept(ScapecraftItems.WATERRUNE.get());
                        output.accept(ScapecraftItems.WRATHRUNE.get());

                        //TALISMANS
                        output.accept(ScapecraftItems.AIRTALISMAN.get());
                        output.accept(ScapecraftItems.ASTRALTALISMAN.get());
                        output.accept(ScapecraftItems.BLOODTALISMAN.get());
                        output.accept(ScapecraftItems.BODYTALISMAN.get());
                        output.accept(ScapecraftItems.CHAOSTALISMAN.get());
                        output.accept(ScapecraftItems.COSMICTALISMAN.get());
                        output.accept(ScapecraftItems.DEATHTALISMAN.get());
                        output.accept(ScapecraftItems.EARTHTALISMAN.get());
                        output.accept(ScapecraftItems.FIRETALISMAN.get());
                        output.accept(ScapecraftItems.LAWTALISMAN.get());
                        output.accept(ScapecraftItems.MINDTALISMAN.get());
                        output.accept(ScapecraftItems.NATURETALISMAN.get());
                        output.accept(ScapecraftItems.SOULTALISMAN.get());
                        output.accept(ScapecraftItems.WATERTALISMAN.get());
                        output.accept(ScapecraftItems.WRATHTALISMAN.get());

                        output.accept(ScapecraftBlocks.RUNE_ESSENCE_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
