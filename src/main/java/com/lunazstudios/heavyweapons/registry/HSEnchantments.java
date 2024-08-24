package com.lunazstudios.heavyweapons.registry;

import com.lunazstudios.heavyweapons.Heavyweapons;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.entity.IgniteEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.*;
import net.minecraft.util.Identifier;

public class HSEnchantments {
    public static final RegistryKey<Enchantment> REBOUND = HSEnchantments.of("rebound");

    public static void bootstrap(Registerable<Enchantment> registry) {
        RegistryEntryLookup<Enchantment> registryEntryLookup2 = registry.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> registryEntryLookup3 = registry.getRegistryLookup(RegistryKeys.ITEM);
        register(registry, REBOUND, Enchantment.builder(Enchantment.definition(registryEntryLookup3.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), 5, 5, Enchantment.leveledCost(5, 8), Enchantment.leveledCost(25, 8), 2, AttributeModifierSlot.MAINHAND)).exclusiveSet(registryEntryLookup2.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET)).addEffect(EnchantmentEffectComponentTypes.SMASH_DAMAGE_PER_FALLEN_BLOCK, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.5f))));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Heavyweapons.MOD_ID,id));
    }
}