package io.yooksi.cococakes.item;

import io.yooksi.cococakes.CocoCakes;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public enum ModItems {

	COCOA_POWDER(new ModItem(), "cocoa_powder"),
	CHOCOLATE_MILK(new ModItem(ModItem.FILLED_BUCKET), "choco_milk_bucket");

	private final Item item;

	ModItems(Item item, String name) {
		this.item = item.setRegistryName(CocoCakes.location(name));
	}

	public static Item[] getAll() {

		final ModItems[] values = ModItems.values();
		Item[] items = new Item[values.length];

		for (int i = 0; i < values.length; i++) {
			items[i] = values[i].item;
		}
		return items;
	}
	public Item get() {
		return item;
	}
}
