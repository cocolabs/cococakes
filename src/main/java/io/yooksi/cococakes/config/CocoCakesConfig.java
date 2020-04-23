package io.yooksi.cococakes.config;

import io.yooksi.cococakes.CocoCakes;
import io.yooksi.cococakes.item.ModItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = CocoCakes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CocoCakesConfig {

	public static final ClientConfig CLIENT;
	public static final ForgeConfigSpec CLIENT_SPEC;
	static {
		final Pair<ClientConfig, ForgeConfigSpec> specPair =
				new ForgeConfigSpec.Builder().configure(ClientConfig::new);

		CLIENT_SPEC = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	private static boolean canHarvestCakes;
	private static int cakeMaxStackSize;

	public static void bakeConfig() {
		canHarvestCakes = CLIENT.canHarvestCakes.get();
		cakeMaxStackSize = CLIENT.cakeMaxStackSize.get();
	}

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {

		if (configEvent.getConfig().getSpec() == CocoCakesConfig.CLIENT_SPEC) {
			bakeConfig();
			ModItem.onUpdateModConfig();
		}
	}

	public static boolean canRecollectCakes() {
		return canHarvestCakes;
	}

	public static int getCakeMaxStackSize() {
		return cakeMaxStackSize;
	}
}
