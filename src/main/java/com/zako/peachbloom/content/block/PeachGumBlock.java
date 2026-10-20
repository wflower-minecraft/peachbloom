package com.zako.peachbloom.content.block;

import com.zako.peachbloom.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class PeachGumBlock extends Block {
	public PeachGumBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		for (Direction direction : Direction.values()) {
			if (level.getBlockState(pos.relative(direction)).is(ModTags.PEACH_LOGS)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected BlockState updateShape(
		BlockState state,
		LevelReader level,
		ScheduledTickAccess ticks,
		BlockPos pos,
		Direction direction,
		BlockPos neighbourPos,
		BlockState neighbourState,
		RandomSource random
	) {
		return state.canSurvive(level, pos)
			? super.updateShape(state, level, ticks, pos, direction, neighbourPos, neighbourState, random)
			: Blocks.AIR.defaultBlockState();
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
}
