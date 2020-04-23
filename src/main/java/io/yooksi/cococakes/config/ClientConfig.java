package io.yooksi.cococakes.config;

import io.yooksi.cococakes.CocoCakes;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

	// Declare config values here
	public final ForgeConfigSpec.BooleanValue canHarvestCakes;
	public final ForgeConfigSpec.IntValue cakeMaxStackSize;

	public ClientConfig(ForgeConfigSpec.Builder builder) {
		// Use builder to define configs
		canHarvestCakes = builder
				.comment("Can cake blocks be harvested when broken?")
				.translation(CocoCakes.MODID + ".config." + "canRecollectCakes")
				.define("canHarvestCakes", true);
		
		cakeMaxStackSize = builder
				.comment("Maximum size of cake item stack")
				.translation(CocoCakes.MODID + ".config." + "cakeMaxStackSize")
				.defineInRange("cakeMaxStackSize", 1, 0, 64);

		builder.build();
	}
}

