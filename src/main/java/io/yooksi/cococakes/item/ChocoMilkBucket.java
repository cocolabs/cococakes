package io.yooksi.cococakes.item;

import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;

public class ChocoMilkBucket extends MilkBucketItem {

	public ChocoMilkBucket() {
		super((new Item.Properties()).containerItem(net.minecraft.item.Items.BUCKET)
				.maxStackSize(1).group(ModItemGroup.MAIN));
	}
}
