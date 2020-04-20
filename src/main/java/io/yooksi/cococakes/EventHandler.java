package io.yooksi.cococakes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


public class EventHandler {

	public void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {

		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
	}
}
