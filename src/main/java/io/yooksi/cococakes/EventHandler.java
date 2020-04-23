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

		final World world = event.getWorld();
		final BlockPos pos = event.getPos();
		final PlayerEntity player = event.getPlayer();
		final BlockState state = world.getBlockState(pos);
		final Block block = state.getBlock();

		boolean isBlockCake = block instanceof net.minecraft.block.CakeBlock;
		if (!isBlockCake || !player.canEat(false)) {
			return;
		}
		ItemStack stack = block.getPickBlock(state, null, world, pos, player);
		player.addItemParticles(stack, 2);

		net.minecraft.util.SoundEvent eatSound = player.getEatSound(stack);
		player.playSound(eatSound, 0.15F + 0.05F * (float)RNG.nextInt(2),
				(RNG.nextFloat() - RNG.nextFloat()) * 0.04F + 0.25F);
	}
}
