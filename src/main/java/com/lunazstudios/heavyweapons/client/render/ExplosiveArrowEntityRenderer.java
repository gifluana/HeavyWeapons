package com.lunazstudios.heavyweapons.client.render;

import com.lunazstudios.heavyweapons.Heavyweapons;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ExplosiveArrowEntityRenderer extends ArrowEntityRenderer {
    private static final Identifier TEXTURE = Identifier.of(Heavyweapons.MOD_ID, "textures/entity/projectile/explosive_arrow.png");

    public ExplosiveArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return TEXTURE;
    }
}