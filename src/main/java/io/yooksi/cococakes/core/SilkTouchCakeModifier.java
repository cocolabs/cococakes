package io.yooksi.cococakes.core;

import com.google.gson.JsonObject;
import io.yooksi.cococakes.CocoCakes;
import io.yooksi.cococakes.config.CocoCakesConfig;
import io.yooksi.cococakes.lang.ModGenericException;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * This class represents a {@code LootModifier} that enables us to harvest both
 * vanilla and modded cakes as any other item if we had silk touch. Note that
 * this doesn't work like magic and cake block entries still need to be defined
 * in {@code data/cococakes/loot_modifier/silk_touch_cake.json}.
 *
 * @see <a href="https://git.io/JfIA8">Forge Docs: Global Loot Modifiers</a>
 */
@SuppressWarnings("unused")
public class SilkTouchCakeModifier extends LootModifier {

	public SilkTouchCakeModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	/**
	 * Applies the modifier to the generated loot (all loot conditions have already
	 * been checked and have returned true). This method gets called when a loot table
	 * is being generated before an item drop, and only if the conditions specified
	 * in associated loot modifier return true.
	 */
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

		BlockState state = context.get(LootParameters.BLOCK_STATE);
		if (state != null)
		{
			Item selfDrop = state.getBlock().asItem();
			ItemStack cakeLoot = getItemFromLoot(generatedLoot, selfDrop);

			if (CocoCakesConfig.canRecollectCakes()) {
				if (cakeLoot.isEmpty())
					generatedLoot.add(new ItemStack(selfDrop));
			}
			else if (!cakeLoot.isEmpty()) {
				generatedLoot.remove(cakeLoot);
			}
		}
		else {
			new ModGenericException("Unexpected null BlockState").printStackTrace();
		}
		return generatedLoot;
	}

	/**
	 * @return first {@code ItemStack} found in the given list that corresponds to the given
	 * 			{@code Item} or an empty {@code ItemStack} if none was found
	 */
	@Contract(pure = true)
	private static ItemStack getItemFromLoot(List<ItemStack> loot, Item item) {

		for (ItemStack itemStack : loot) {
			if (itemStack.getItem() == item)
				return itemStack;
		}
		return ItemStack.EMPTY;
	}

	/**
	 * Prepare a new {@code Serializer} instance ready to be registered with Forge.
	 * @return new {@code Serializer} instance with set registry name
	 */
	public static GlobalLootModifierSerializer<SilkTouchCakeModifier> getNewSerializer() {
		return new Serializer().setRegistryName(CocoCakes.location("silk_touch_cake"));
	}

	static class Serializer extends GlobalLootModifierSerializer<SilkTouchCakeModifier> {

		@Override
		public SilkTouchCakeModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
			return new SilkTouchCakeModifier(conditionsIn);
		}
	}
}