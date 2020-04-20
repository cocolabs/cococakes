package io.yooksi.cococakes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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

		// Initialize mod logger
		CCLogger.init(LogManager.getLogger());
	}

	private void setup(final FMLCommonSetupEvent event) {
		CCLogger.info("Pre-initialization phase");
	}
}
