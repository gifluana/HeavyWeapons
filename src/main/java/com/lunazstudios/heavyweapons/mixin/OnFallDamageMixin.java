package com.lunazstudios.heavyweapons.mixin;

import com.lunazstudios.heavyweapons.item.HSItems;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class OnFallDamageMixin {

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void onApplyDamage(DamageSource source, float amount, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        World world = player.getWorld();

        if (source == player.getDamageSources().fall()) {
            ItemStack bootsStack = player.getInventory().getArmorStack(0); // Get the boots slot item
            if (bootsStack.isOf(HSItems.HEAVY_BOOTS)) {
                ci.cancel();
            }
        }
    }
}
