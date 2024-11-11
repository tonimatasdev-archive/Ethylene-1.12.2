package dev.tonimatas.ethylene;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "ethylene", dependencies = "required-after:mixinbooter")
public class Ethylene {
    public static final Logger LOGGER = LogManager.getLogger("Ethylene");

    public Ethylene() {
        LOGGER.info("Ethylene has been initialized successfully.");
    }
}
