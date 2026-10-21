package com.zako.peachbloom.content.block;

import com.zako.peachbloom.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 去皮桃花原木/木头：静置时会像树脂一样渗出桃胶。
 */
public class StrippedPeachLogBlock extends RotatedPillarBlock {
	public StrippedPeachLogBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void randomTick(BlockState state, net.minecraft.server.level.ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(24) != 0) {
			return;
		}
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		BlockPos target = pos.relative(direction);
		if (level.isEmptyBlock(target)) {
			BlockState gum = ModBlocks.PEACH_GUM_BLOCK.defaultBlockState();
			if (gum.canSurvive(level, target)) {
				level.setBlock(target, gum, 3);
			}
		}
	}
}
