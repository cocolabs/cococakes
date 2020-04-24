package io.yooksi.cococakes.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

@SuppressWarnings("unused")
public class ModCakeBlock extends CakeBlock {

	public static final Block.Properties DEFAULT_PROPERTIES = Block.Properties
			.create(Material.CAKE).hardnessAndResistance(0.5f).sound(SoundType.CLOTH);

	public static final Block.Properties GOLDEN_CAKE_PROPERTIES = Block.Properties
			.create(Material.CAKE, MaterialColor.GOLD).hardnessAndResistance(0.5f).sound(SoundType.CLOTH);

	// TODO: Perhaps move these values to mod config
	private final int foodValue;
	private final float foodSaturation;

	/**
	 * {@code CakeBlock} with given property values and default food value and saturation
	 *
	 * @param foodValue amount of hunger restored when cake slice is eaten
	 * @param foodSaturation amount of food saturation gained when cake slice is eaten
	 */
	public ModCakeBlock(Properties properties, int foodValue, float foodSaturation) {
		super(properties);
		this.foodValue = foodValue;
		this.foodSaturation = foodSaturation;
	}
	/**
	 * {@code CakeBlock} with given property values and default food value and saturation
	 * @see #ModCakeBlock(Properties, int, float)
	 */
	public ModCakeBlock(float hardness, SoundType sound) {
		this(Block.Properties.create(Material.CAKE)
				.hardnessAndResistance(hardness).sound(sound), 2, 0.1f);
	}
	/**
	 * {@code CakeBlock} with given property values and default food value and saturation
	 * @see #ModCakeBlock(Properties, int, float)
	 */
	public ModCakeBlock(float hardness, SoundType sound, MaterialColor color) {
		this(Block.Properties.create(Material.CAKE, color)
				.hardnessAndResistance(hardness).sound(sound), 2, 0.1f);
	}
	/**
	 * {@code CakeBlock} with given food value and saturation and default properties
	 * @see #ModCakeBlock(Properties, int, float)
	 */
	public ModCakeBlock(int foodValue, float foodSaturation) {
		this(DEFAULT_PROPERTIES, foodValue, foodSaturation);
	}
	/**
	 * {@code CakeBlock} with default properties, food value and saturation
	 * @see #ModCakeBlock(Properties, int, float)
	 */
	public ModCakeBlock() {
		this(DEFAULT_PROPERTIES, 2, 0.1f);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
											 PlayerEntity player, Hand handIn,
											 BlockRayTraceResult rayTraceResult) {
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
			player.getFoodStats().addStats(foodValue, foodSaturation);
			int i = state.get(BITES);
			if (i < 6) {
				world.setBlockState(blockPos, state.with(BITES, i + 1), 3);
			}
			else world.removeBlock(blockPos, false);
			return ActionResultType.SUCCESS;
		}
	}
}
