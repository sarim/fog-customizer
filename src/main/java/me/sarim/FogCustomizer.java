package me.sarim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;

public class FogCustomizer implements ClientModInitializer {
    public static final String MOD_ID = "fog-customizer";
    public static final String MOD_NAME = "Fog Customizer";
    public static final String MOD_CONF = "fog-customizer.json";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient()
    {
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
    }

}