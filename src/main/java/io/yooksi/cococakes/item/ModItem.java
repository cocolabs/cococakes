package io.yooksi.cococakes.item;

import net.minecraft.item.Item;

public class ModItem extends Item {

	public ModItem() {
		super(new Item.Properties().group(ModItemGroup.MAIN));
	}
	public ModItem(Properties properties) {
		super(properties.group(ModItemGroup.MAIN));
	}
}
