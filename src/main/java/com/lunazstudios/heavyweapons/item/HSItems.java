package com.lunazstudios.heavyweapons.item;

import com.lunazstudios.heavyweapons.Heavyweapons;
import com.lunazstudios.heavyweapons.item.custom.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class HSItems {

    public static final Item SKILLET = registerItem("skillet",
            new Skillet(new Item.Settings().rarity(Rarity.EPIC).maxDamage(500).component(DataComponentTypes.TOOL, Skillet.createToolComponent()).attributeModifiers(Skillet.createAttributeModifiers())));

    public static final Item HEAVY_BOOTS = registerItem("heavy_boots",
            new HeavyArmor(HSArmorMaterials.HEAVY, ArmorItem.Type.BOOTS, new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(33))));

    public static final Item HEAVY_CROSSBOW = registerItem("heavy_crossbow",
            new HeavyCrossbow(new Item.Settings().rarity(Rarity.EPIC).maxCount(1).maxDamage(500).component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT)));

    public static final Item EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            new ExplosiveArrowItem(new Item.Settings()));

    public static final Item HEAVY_UPGRADE_SMITHING_TEMPLATE = registerItem("heavy_upgrade_smithing_template",
            HeavySmithingTemplate.createHeavyUpgrade());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Heavyweapons.MOD_ID, name), item);
    }

    public static void registerHSItems() {
        Heavyweapons.LOGGER.info("Registering Items for " + Heavyweapons.MOD_NAME);
    }
}
