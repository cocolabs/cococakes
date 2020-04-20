package io.yooksi.cococakes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.Random;

public class EventHandler {

	private static final Random RNG = new Random();

	public void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {

		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		boolean isBlockCake = block instanceof net.minecraft.block.CakeBlock;
		if (!isBlockCake || !player.canEat(false)) {
			return;
		}
		ItemStack stack = block.getPickBlock(state, null, world, pos, player);
		player.playSound(eatSound, 0.5F + 0.5F * (float)RNG.nextInt(2),
				(RNG.nextFloat() - RNG.nextFloat()) * 0.2F + 1.0F);
	}
}
