package com.lunazstudios.heavyweapons.item;

import com.lunazstudios.heavyweapons.Heavyweapons;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HSItemGroup {
    public static final ItemGroup HYPESQUAD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Heavyweapons.MOD_ID, "heavyweapons"),
            FabricItemGroup.builder().displayName(Text.translatable("heavyweapons.itemgroup.heavyweapons"))
                    .icon(() -> new ItemStack(HSItems.SKILLET))
                    .entries((displayContext, entries) -> {
                        entries.add(HSItems.SKILLET);
                        entries.add(HSItems.HEAVY_BOOTS);
                        entries.add(HSItems.HEAVY_CROSSBOW);
                        entries.add(HSItems.EXPLOSIVE_ARROW);
                        entries.add(HSItems.HEAVY_UPGRADE_SMITHING_TEMPLATE);
                    }).build());

    public static void registerHSItemGroups() {
        Heavyweapons.LOGGER.info("Registering Item Groups for " + Heavyweapons.MOD_NAME);
    }
}
