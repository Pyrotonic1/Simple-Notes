package org.pyrotonic.simplenotes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simplenotes implements ModInitializer {
    public static final String MOD_ID = "simplenotes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {

        LOGGER.info("Simple Notes Initialized!");


    }
}
