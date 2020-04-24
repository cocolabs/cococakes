package io.yooksi.cococakes.block;

import io.yooksi.cococakes.CocoCakes;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;

public enum ModBlocks {

	CHOCOLATE_CAKE(new ModCake(0.5f, SoundType.CLOTH), "choco_cake"),
	GOLDEN_CAKE(new GoldenCake(0.5f, SoundType.CLOTH), "golden_cake");

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
