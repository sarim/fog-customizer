package me.sarim.config;

import me.sarim.FogCustomizer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.FogShape;
import net.minecraft.text.Text;

public final class ClothConfigScreenFactory {
    static Text localized(String domain, String path) {
        return Text.translatable(domain + "." + FogCustomizer.MOD_ID + "." + path);
    }

    public static Screen genConfig(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localized("config", "title"))
                .setSavingRunnable(FogCustomizer.CONFIG::save);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // general
        builder.getOrCreateCategory(localized("config", "category.general"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localized("config", "override"),
                                FogCustomizer.CONFIG.Generic.override)
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> FogCustomizer.CONFIG.Generic.override = value)
                        .build())
                .addEntry(entryBuilder
                        .startFloatField(
                                localized("config", "fog_start_multiplier"),
                                FogCustomizer.CONFIG.Generic.fogStartMultiplier)
                        .setDefaultValue(1F)
                        .setSaveConsumer(
                                value -> FogCustomizer.CONFIG.Generic.fogStartMultiplier = value)
                        .build())
                .addEntry(entryBuilder
                        .startFloatField(
                                localized("config", "fog_end_multiplier"),
                                FogCustomizer.CONFIG.Generic.fogEndMultiplier)
                        .setDefaultValue(1F)
                        .setSaveConsumer(
                                value -> FogCustomizer.CONFIG.Generic.fogEndMultiplier = value)
                        .build())
                .addEntry(entryBuilder
                        .startEnumSelector(
                                localized("config", "fog_shape"),
                                FogShape.class,
                                FogCustomizer.CONFIG.Generic.fogShape)
                        .setDefaultValue(FogShape.CYLINDER)
                        .setSaveConsumer(value -> FogCustomizer.CONFIG.Generic.fogShape = value)
                        .build());

        // thick
        builder.getOrCreateCategory(localized("config", "category.thick"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localized("config", "override"),
                                FogCustomizer.CONFIG.Thick.override)
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> FogCustomizer.CONFIG.Thick.override = value)
                        .build())
                .addEntry(entryBuilder
                        .startFloatField(
                                localized("config", "fog_start_multiplier"),
                                FogCustomizer.CONFIG.Thick.fogStartMultiplier)
                        .setDefaultValue(1F)
                        .setSaveConsumer(
                                value -> FogCustomizer.CONFIG.Thick.fogStartMultiplier = value)
                        .build())
                .addEntry(entryBuilder
                        .startFloatField(
                                localized("config", "fog_end_multiplier"),
                                FogCustomizer.CONFIG.Thick.fogEndMultiplier)
                        .setDefaultValue(1F)
                        .setSaveConsumer(
                                value -> FogCustomizer.CONFIG.Thick.fogEndMultiplier = value)
                        .build())
                .addEntry(entryBuilder
                        .startEnumSelector(
                                localized("config", "fog_shape"),
                                FogShape.class,
                                FogCustomizer.CONFIG.Thick.fogShape)
                        .setDefaultValue(FogShape.SPHERE)
                        .setSaveConsumer(value -> FogCustomizer.CONFIG.Thick.fogShape = value)
                        .build());

        builder.transparentBackground();
        return builder.build();
    }
}
