package io.yooksi.cococakes.item;

import io.yooksi.cococakes.config.CocoCakesConfig;
import net.minecraft.block.CakeBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;

public class ModItem extends Item {

	public static final Item.Properties FILLED_BUCKET = new Item.Properties()
		.containerItem(net.minecraft.item.Items.BUCKET).maxStackSize(1);

	public ModItem() {
		super(new Item.Properties().group(ModItemGroup.MAIN));
	}
	public ModItem(Properties properties) {
		super(properties.group(ModItemGroup.MAIN));
	}

	/**
	 * Update configurable properties for all items that have property values that are defined or
	 * dependent on properties from {@link io.yooksi.cococakes.config.CocoCakesConfig CocoCakesConfig}.
	 * This is intended to be called after <code>ModConfig</code> is updated, don't call before the
	 * config properties had chance to bake (load from file) or we will just get default values.
	 */
	public static void onUpdateModConfig() {

		int cakeMaxStackSize = CocoCakesConfig.getCakeMaxStackSize();
		IForgeRegistry<Item> registeredItems = ForgeRegistries.ITEMS;

		for (Map.Entry<ResourceLocation, Item> entry : registeredItems.getEntries()) {
			Item item = entry.getValue();
			/*
			 * Set custom maximum stack size for all registered cakes
			 */
			if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof CakeBlock) {
				item.maxStackSize = cakeMaxStackSize;
			}
		}
	}
}
