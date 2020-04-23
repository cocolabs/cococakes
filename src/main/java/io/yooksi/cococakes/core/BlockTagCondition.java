package io.yooksi.cococakes.core;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.yooksi.cococakes.CocoCakes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

/**
 * This class represents a custom loot modifier condition that is able to use
 * both vanilla and user created block tags to validate {@code BlockState}
 * found in {@code LootContext}.<br><br>
 *
 * Here is an example of how to use the condition:
 * <pre>
 *   "conditions": [
 *     {
 *       "condition": "mymodid:block_tag",
 *       "tag": "minecraft:beehives"
 *     }
 *   ]
 * </pre>
 * This condition would be satisfied if the block dropping the loot was either
 * a bee nest or a beehive which are both tag entries in {@code minecraft:beehives}.
 *
 * @author gigaherz
 * @see <a href="https://git.io/JfIAR">Survivalist - BlockTagCondition on Github</a>
 */
@SuppressWarnings("unused")
public class BlockTagCondition implements ILootCondition {

	private final Tag<Block> blockTag;

	public BlockTagCondition(Tag<Block> blockTag) {
		this.blockTag = blockTag;
	}

	/**
	 * @return {@code true} if {@code Block} for this lootContext is found in {@link #blockTag}
	 */
	@Override
	public boolean test(LootContext lootContext) {

		BlockState state = lootContext.get(LootParameters.BLOCK_STATE);
		return state != null && blockTag.contains(state.getBlock());
	}

	public static class Serializer extends ILootCondition.AbstractSerializer<BlockTagCondition> {

		public Serializer() {
			super(CocoCakes.location("block_tag"), BlockTagCondition.class);
		}

		@Override
		public void serialize(JsonObject json, BlockTagCondition value, JsonSerializationContext context) {
			json.addProperty("tag", value.blockTag.getId().toString());
		}

		@Override
		public BlockTagCondition deserialize(JsonObject json, JsonDeserializationContext context) {

			ResourceLocation tagName = new ResourceLocation(JSONUtils.getString(json, "tag"));
			return new BlockTagCondition(new BlockTags.Wrapper(tagName));
		}
	}
}