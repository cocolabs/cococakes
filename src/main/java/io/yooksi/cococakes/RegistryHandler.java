package io.yooksi.cococakes;

import io.yooksi.cococakes.block.ModBlocks;
import io.yooksi.cococakes.item.ModItemGroup;
import io.yooksi.cococakes.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid="cococakes")
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
			blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
			registry.register(blockItem);
		}
	}
}