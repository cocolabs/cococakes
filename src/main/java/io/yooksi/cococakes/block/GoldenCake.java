package io.yooksi.cococakes.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GoldenCake extends ModCake {

	// TODO: Perhaps move these values to mod config

	private final int FOOD_VALUE = 4;
	private final float FOOD_SATURATION = 0.2f;

	protected GoldenCake(float hardness, SoundType sound) {
		super(hardness, sound, MaterialColor.GOLD);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {

		if (worldIn.isRemote) {
			ItemStack itemstack = player.getHeldItem(handIn);
			if (eatCake(worldIn, pos, state, player) == ActionResultType.SUCCESS) {
				return ActionResultType.SUCCESS;
			}
			else if (itemstack.isEmpty()) {
				return ActionResultType.CONSUME;
			}
		}
		return eatCake(worldIn, pos, state, player);
	}

	private ActionResultType eatCake(IWorld world, BlockPos blockPos, BlockState state, PlayerEntity player) {

		if (!player.canEat(false)) {
			return ActionResultType.PASS;
		}
		else {
			player.addStat(Stats.EAT_CAKE_SLICE);
			player.getFoodStats().addStats(FOOD_VALUE, FOOD_SATURATION);
			int i = state.get(BITES);
			if (i < 6) {
				world.setBlockState(blockPos, state.with(BITES, i + 1), 3);
			}
			else {
				world.removeBlock(blockPos, false);
			}
			return ActionResultType.SUCCESS;
		}
	}
}
