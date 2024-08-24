package com.lunazstudios.heavyweapons.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends ArrowEntity {

    public ExplosiveArrowEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte)0);
            this.setStack(new ItemStack(Items.ARROW));
        }
    }

    private void spawnParticles(int amount) {
        for (int j = 0; j < amount; ++j) {
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        explode();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        explode();
    }

    private void explode() {
        if (!this.getWorld().isClient()) {
            // Calculate the direction vector
            Vec3d direction = this.getVelocity().normalize();

            // Create the first explosion at the hit location
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 5.0F, true, World.ExplosionSourceType.TNT);

            // Create subsequent explosions along the direction vector
            for (int i = 1; i <= 10; i++) {
                Vec3d explosionPosition = this.getPos().add(direction.multiply(i * 5));
                this.getWorld().createExplosion(this, explosionPosition.x, explosionPosition.y, explosionPosition.z, 5.0F, false, World.ExplosionSourceType.TNT);
            }

            this.discard();
        }
    }
}
