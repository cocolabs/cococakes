package io.yooksi.cococakes.block;

import io.yooksi.cococakes.config.CocoCakesConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

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

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		player.addStat(Stats.BLOCK_MINED.get(this));
		player.addExhaustion(0.005F);
		if (CocoCakesConfig.canRecollectCakes()) {
			spawnDrops(state, worldIn, pos, te, player, stack);
		}
	}
}
