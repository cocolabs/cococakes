package io.yooksi.cococakes.block;

import io.yooksi.cococakes.CocoCakes;
import net.minecraft.block.Block;

public enum ModBlocks {

	CHOCOLATE_CAKE(new ModCakeBlock(), "choco_cake"),
	GOLDEN_CAKE(new ModCakeBlock(ModCakeBlock.GOLDEN_CAKE_PROPERTIES,
			4, 0.2f), "golden_cake");

	private final Block block;

	ModBlocks(Block block, String name) {
		this.block = block.setRegistryName(CocoCakes.location(name));
	}

	public static Block[] getAll() {

		final ModBlocks[] values = ModBlocks.values();
		Block[] blocks = new Block[values.length];

		for (int i = 0; i < values.length; i++) {
			blocks[i] = values[i].block;
		}
		return blocks;
	}
	public Block get() {
		return block;
	}
}
