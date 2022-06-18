package me.sarim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.sarim.config.ClothConfigScreenFactory;
import me.sarim.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;
import net.minecraft.world.dimension.DimensionTypes;

public class FogCustomizer implements ClientModInitializer {
    public static final String MOD_ID = "fog-customizer";
    public static final String MOD_NAME = "Fog Customizer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Config CONFIG = null;

	private static final KeyBindingHandler toggleFogOverrideKeyBinding = new KeyBindingHandler(
		"key." + FogCustomizer.MOD_ID + ".toggle_fog_override",
		"key.categories.misc",
		(e) -> {
            if (e.player.clientWorld.getRegistryKey().getValue().equals(DimensionTypes.OVERWORLD_ID)) {
                CONFIG.Generic.override = !CONFIG.Generic.override;
                e.player.sendMessage(Text.literal("Fog Customizer Override: " + CONFIG.Generic.override), true);
            } else {
                CONFIG.Thick.override = !CONFIG.Thick.override;
                e.player.sendMessage(Text.literal("Fog Customizer Override: " + CONFIG.Thick.override), true);
            }
        }
	);

    private static final KeyBindingHandler openFogConfigKeyBinding = new KeyBindingHandler(
		"key." + FogCustomizer.MOD_ID + ".open_fog_config",
		"key.categories.misc",
		(e) -> {
            e.setScreenAndRender(
                ClothConfigScreenFactory.genConfig(e.currentScreen)
            );
        }
	);

    @Override
    public void onInitializeClient()
    {
        CONFIG = Config.load();
		toggleFogOverrideKeyBinding.register();
        openFogConfigKeyBinding.register();
    }
}