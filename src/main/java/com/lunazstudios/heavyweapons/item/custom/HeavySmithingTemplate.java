package com.lunazstudios.heavyweapons.item.custom;

import com.lunazstudios.heavyweapons.Heavyweapons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class HeavySmithingTemplate extends SmithingTemplateItem {

    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;
    private static final Text INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.ingredients"))).formatted(TITLE_FORMATTING);
    private static final Text APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.applies_to"))).formatted(TITLE_FORMATTING);
    private static final Text HEAVY_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(Heavyweapons.MOD_ID,"heavy_upgrade"))).formatted(TITLE_FORMATTING);
    private static final Text HEAVY_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Heavyweapons.MOD_ID, "smithing_template.heavy_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text HEAVY_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Heavyweapons.MOD_ID, "smithing_template.heavy_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text HEAVY_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Heavyweapons.MOD_ID, "smithing_template.heavy_upgrade.base_slot_description")));
    private static final Text HEAVY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Heavyweapons.MOD_ID, "smithing_template.heavy_upgrade.additions_slot_description")));
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_CROSSBOW_TEXTURE = Identifier.of(Heavyweapons.MOD_ID,"item/empty_slot_crossbow");
    private static final Identifier EMPTY_SLOT_MACE_TEXTURE = Identifier.of(Heavyweapons.MOD_ID,"item/empty_slot_mace");
    private static final Identifier EMPTY_SLOT_HEAVY_CORE_TEXTURE = Identifier.of(Heavyweapons.MOD_ID,"item/empty_slot_heavy_core");
    private final Text appliesToText;
    private final Text ingredientsText;
    private final Text titleText;

    public HeavySmithingTemplate(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, FeatureFlag... requiredFeatures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, requiredFeatures);
        this.appliesToText = appliesToText;
        this.ingredientsText = ingredientsText;
        this.titleText = titleText;
    }

    public static SmithingTemplateItem createHeavyUpgrade() {
        return new SmithingTemplateItem(HEAVY_UPGRADE_APPLIES_TO_TEXT, HEAVY_UPGRADE_INGREDIENTS_TEXT, HEAVY_UPGRADE_TEXT, HEAVY_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, HEAVY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, getNetheriteUpgradeEmptyBaseSlotTextures(), getNetheriteUpgradeEmptyAdditionsSlotTextures());
    }
    private static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_SLOT_MACE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_CROSSBOW_TEXTURE);
    }

    private static List<Identifier> getNetheriteUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_HEAVY_CORE_TEXTURE);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(this.titleText);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(APPLIES_TO_TEXT);
        tooltip.add(ScreenTexts.space().append(this.appliesToText));
        tooltip.add(INGREDIENTS_TEXT);
        tooltip.add(ScreenTexts.space().append(this.ingredientsText));
    }
}
