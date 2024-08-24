package com.lunazstudios.heavyweapons.registry;

import com.lunazstudios.heavyweapons.item.HSItems;
import com.lunazstudios.heavyweapons.item.custom.HeavyCrossbow;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModelPredicateProvider {

    public static void registerModModels() {
        registerCrossbow(HSItems.HEAVY_CROSSBOW);
    }

    private static void registerCrossbow(Item crossbow) {
        ModelPredicateProviderRegistry.register(crossbow, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (HeavyCrossbow.isCharged(stack)) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / (float)HeavyCrossbow.getPullTime(stack, entity);
        });
        ModelPredicateProviderRegistry.register(crossbow, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !HeavyCrossbow.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(crossbow, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> HeavyCrossbow.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(crossbow, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0f : 0.0f;
        });
        ModelPredicateProviderRegistry.register(crossbow, Identifier.ofVanilla("explosive_arrow"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(HSItems.EXPLOSIVE_ARROW) ? 1.0f : 0.0f;
        });
    }
}
