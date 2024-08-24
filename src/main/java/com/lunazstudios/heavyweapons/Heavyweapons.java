package com.lunazstudios.heavyweapons;

import com.lunazstudios.heavyweapons.item.HSItemGroup;
import com.lunazstudios.heavyweapons.item.HSItems;
import com.lunazstudios.heavyweapons.registry.HSEntities;
import com.lunazstudios.heavyweapons.registry.HSSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Heavyweapons implements ModInitializer {
	public static final String MOD_ID = "heavyweapons";
	public static final String MOD_NAME = "HypeSquad Heavy Weapons";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_NAME + "!");

		HSItems.registerHSItems();
		HSItemGroup.registerHSItemGroups();
		HSSounds.registerSounds();
		HSEntities.registerEntities();
	}
}