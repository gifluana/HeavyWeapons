package com.lunazstudios.heavyweapons.item.custom;

import com.lunazstudios.heavyweapons.entities.ExplosiveArrowEntity;
import com.lunazstudios.heavyweapons.registry.HSEntities;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class HeavyCrossbow extends CrossbowItem {
    public HeavyCrossbow(Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getHeldProjectiles() {
        return stack -> stack.getItem() instanceof ArrowItem || stack.getItem() instanceof FireworkRocketItem || stack.getItem() instanceof ExplosiveArrowItem;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return this.getHeldProjectiles();
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack crossbow, ItemStack arrow, boolean creative) {
        if (arrow.getItem() instanceof ExplosiveArrowItem) {
            ExplosiveArrowEntity explosiveArrowEntity = new ExplosiveArrowEntity(HSEntities.EXPLOSIVE_ARROW_ENTITY, world);
            explosiveArrowEntity.setPos(shooter.getX(), shooter.getEyeY() - 0.15, shooter.getZ());
            explosiveArrowEntity.setOwner(shooter);
            return explosiveArrowEntity;
        } else if (arrow.getItem() instanceof FireworkRocketItem) {
            return new FireworkRocketEntity(world, arrow, shooter, shooter.getX(), shooter.getEyeY() - 0.15, shooter.getZ(), true);
        } else {
            return super.createArrowEntity(world, shooter, crossbow, arrow, creative);
        }
    }
}
