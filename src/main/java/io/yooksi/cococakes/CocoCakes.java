package io.yooksi.cococakes;

import io.yooksi.cococakes.config.CocoCakesConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;

@Mod("cococakes")
public class CocoCakes
{
	public static final String MODID = "cococakes";
	private static final EventHandler EVENTS = new EventHandler();

	public CocoCakes() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		// Register events we're interested in
		MinecraftForge.EVENT_BUS.addListener(EVENTS::onBlockRightClick);

		// Register Mod configuration
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CocoCakesConfig.CLIENT_SPEC);

		// Initialize mod logger
		CCLogger.init(LogManager.getLogger());
	}

	private void setup(final FMLCommonSetupEvent event) {
		CCLogger.info("Pre-initialization phase");
	}
}
