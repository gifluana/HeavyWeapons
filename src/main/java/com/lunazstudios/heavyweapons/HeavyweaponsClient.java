package com.lunazstudios.heavyweapons;

import com.lunazstudios.heavyweapons.client.render.ExplosiveArrowEntityRenderer;
import com.lunazstudios.heavyweapons.registry.HSEntities;
import com.lunazstudios.heavyweapons.registry.ModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HeavyweaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(HSEntities.EXPLOSIVE_ARROW_ENTITY, ExplosiveArrowEntityRenderer::new);
        ModelPredicateProvider.registerModModels();
    }
}
