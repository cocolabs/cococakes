package io.yooksi.cococakes.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;

public class ModBlock extends Block {

	public ModBlock(Properties properties) {
		super(properties);
	}
	public ModBlock(Material material) {
		super(Block.Properties.create(material));
	}
	public ModBlock(Material material, DyeColor color) {
		super(Block.Properties.create(material, color));
	}
	public ModBlock(Material material, MaterialColor materialColor) {
		super(Block.Properties.create(material, materialColor));
	}
}
