package io.yooksi.cococakes.core;

import io.yooksi.cococakes.CCLogger;
import io.yooksi.cococakes.CocoCakes;
import io.yooksi.cococakes.block.ModBlocks;
import io.yooksi.cococakes.item.ModItemGroup;
import io.yooksi.cococakes.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = CocoCakes.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {

		CCLogger.info("Registering mod Blocks...");
		event.getRegistry().registerAll(ModBlocks.getAll());
	}

	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

		final IForgeRegistry<Item> registry = event.getRegistry();

		CCLogger.info("Registering mod Items...");
		registry.registerAll(ModItems.getAll());

		CCLogger.info("Registering mod BlockItems...");
		for (Block block : ModBlocks.getAll()) {

			final Item.Properties properties = new Item.Properties().group(ModItemGroup.MAIN);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(java.util.Objects.requireNonNull(block.getRegistryName()));
			registry.register(blockItem);
		}
	}

	@SubscribeEvent
	public static void registerModifierSerializers(RegistryEvent.Register
			<net.minecraftforge.common.loot.GlobalLootModifierSerializer<?>> event) {

		LootConditionManager.registerCondition(new BlockTagCondition.Serializer());
		event.getRegistry().register(SilkTouchCakeModifier.getNewSerializer());
	}
}