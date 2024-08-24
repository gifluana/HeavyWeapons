package com.lunazstudios.heavyweapons.registry;

import com.lunazstudios.heavyweapons.Heavyweapons;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class HSSounds {

    public static final SoundEvent SKILLET_ATTACK_STRONG = registerSoundEvent("skillet_attack_strong");
    public static final SoundEvent SKILLET_ATTACK_WEAK = registerSoundEvent("skillet_attack_weak");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier identifier = Identifier.of(Heavyweapons.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {

    }
}
