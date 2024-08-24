package com.lunazstudios.heavyweapons.item.custom;

import com.lunazstudios.heavyweapons.item.HSItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.List;
import java.util.function.Predicate;

public class HeavyArmor extends ArmorItem {
    private static final float DAMAGE_RADIUS = 3.5f;
    private static final float BASE_DAMAGE_AMOUNT = 1.5f;

    private boolean wasFalling = false;
    private float previousFallDistance = 0.0f;

    public HeavyArmor(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            ItemStack bootsStack = player.getInventory().getArmorStack(0);
            if (bootsStack.isOf(HSItems.HEAVY_BOOTS)) {
                if (player.fallDistance > 0) {
                    wasFalling = true;
                    previousFallDistance = player.fallDistance;
                } else if (wasFalling && player.isOnGround()) {
                    applyAreaDamage(player, world, previousFallDistance);
                    wasFalling = false;
                    previousFallDistance = 0.0f;
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.postHit(stack, target, attacker);
    }

    private void applyAreaDamage(PlayerEntity player, World world, float fallDistance) {
        if (fallDistance <= 5) {
            return;
        }

        Box area = new Box(player.getPos().subtract(DAMAGE_RADIUS, DAMAGE_RADIUS, DAMAGE_RADIUS), player.getPos().add(DAMAGE_RADIUS, DAMAGE_RADIUS, DAMAGE_RADIUS));
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, e -> e != player && !e.isSpectator());

        if (entities.isEmpty()) {
            return;
        }


        ServerPlayerEntity serverPlayerEntity;
        serverPlayerEntity = (ServerPlayerEntity) player;
        serverPlayerEntity.setSpawnExtraParticlesOnFall(true);

        float damageAmount = BASE_DAMAGE_AMOUNT * fallDistance;

        float enchantmentDamage = EnchantmentHelper.getSmashDamagePerFallenBlock((ServerWorld)world, player.getInventory().getArmorStack(0), player, player.getDamageSources().fall(), 0.0f) * fallDistance;
        damageAmount += enchantmentDamage;

        knockbackNearbyEntities(world, player, player);

        for (LivingEntity entity : entities) {
            entity.damage(player.getDamageSources().playerAttack(player), damageAmount);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, SoundCategory.PLAYERS, 1.0f, 1.0f);
        world.syncWorldEvent(WorldEvents.SMASH_ATTACK, player.getSteppingPos(), 750);
        serverPlayerEntity.setSpawnExtraParticlesOnFall(false);
    }

    private static double getKnockback(PlayerEntity player, LivingEntity attacked, Vec3d distance) {
        return (3.5 - distance.length()) * (double)0.7f * (double)(player.fallDistance > 5.0f ? 2 : 1) * (1.0 - attacked.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return entity -> {
            TameableEntity tameableEntity;
            boolean bl = !entity.isSpectator();
            boolean bl2 = entity != player && entity != attacked;
            boolean bl3 = !player.isTeammate(entity);
            boolean bl4 = !(entity instanceof TameableEntity && (tameableEntity = (TameableEntity)entity).isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid()));
            boolean bl5 = !(entity instanceof ArmorStandEntity) || !((ArmorStandEntity)entity).isMarker();
            boolean bl6 = attacked.squaredDistanceTo(entity) <= Math.pow(3.5, 2.0);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(WorldEvents.SMASH_ATTACK, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(3.5), getKnockbackPredicate(player, attacked)).forEach(entity -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            double d = getKnockback(player, entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > 0.0) {
                entity.addVelocity(vec3d2.x, 0.7f, vec3d2.z);
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                }
            }
        });
    }
}
