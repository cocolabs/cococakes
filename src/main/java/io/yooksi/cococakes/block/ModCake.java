package io.yooksi.cococakes.block;

import net.minecraft.block.Block;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

@SuppressWarnings("unused")
public class ModCake extends CakeBlock {

	protected ModCake(float hardness, SoundType sound) {
		super(Block.Properties.create(Material.CAKE)
				.hardnessAndResistance(hardness).sound(sound));
	}
	protected ModCake(float hardness, SoundType sound, MaterialColor color) {
		super(Block.Properties.create(Material.CAKE, color)
				.hardnessAndResistance(hardness).sound(sound));
	}
	protected ModCake(Properties properties) {
		super(properties);
	}
}
