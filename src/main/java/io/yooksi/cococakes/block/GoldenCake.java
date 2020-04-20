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
			player.getFoodStats().addStats(2, 0.1F);
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
