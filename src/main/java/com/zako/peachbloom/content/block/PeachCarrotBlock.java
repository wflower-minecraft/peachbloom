package com.zako.peachbloom.content.block;

import com.zako.peachbloom.registry.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 桃花萝卜作物：桃花村庄的唯一农作物。
 */
public class PeachCarrotBlock extends CropBlock {
	public PeachCarrotBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return ModItems.PEACH_CARROT;
	}
}
