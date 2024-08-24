package com.lunazstudios.heavyweapons.registry;

import com.lunazstudios.heavyweapons.Heavyweapons;
import com.lunazstudios.heavyweapons.entities.ExplosiveArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HSEntities {
    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Heavyweapons.MOD_ID, "explosive_arrow"),
            FabricEntityTypeBuilder.<ExplosiveArrowEntity>create(SpawnGroup.MISC, ExplosiveArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static void registerEntities() {
    }
}
