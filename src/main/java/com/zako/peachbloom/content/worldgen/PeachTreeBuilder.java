package com.zako.peachbloom.content.worldgen;

import com.zako.peachbloom.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public final class PeachTreeBuilder {
	private PeachTreeBuilder() {
	}

	public static boolean grow(LevelAccessor level, BlockPos pos, RandomSource random) {
		int height = 4 + random.nextInt(3);
		BlockState log = ModBlocks.PEACH_LOG.defaultBlockState();
		BlockState stripped = ModBlocks.STRIPPED_PEACH_LOG.defaultBlockState();
		BlockState leaves = ModBlocks.PEACH_LEAVES.defaultBlockState();

		for (int i = 0; i < height; i++) {
			level.setBlock(pos.above(i), log, 3);
		}

		int top = height - 1;
		for (int dy = -2; dy <= 1; dy++) {
			int radius = dy <= -1 ? 2 : 1;
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					if (Math.abs(dx) == radius && Math.abs(dz) == radius && random.nextBoolean()) {
						continue;
					}
					BlockPos leafPos = pos.above(top + dy).offset(dx, 0, dz);
					if (level.isEmptyBlock(leafPos)) {
						level.setBlock(leafPos, leaves, 3);
					}
				}
			}
		}

		int strippedIndex = 1 + random.nextInt(Math.max(1, height - 2));
		BlockPos strippedPos = pos.above(strippedIndex);
		level.setBlock(strippedPos, stripped, 3);

		Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
		Direction gumSide = directions[random.nextInt(directions.length)];
		BlockPos gumPos = strippedPos.relative(gumSide);
		if (level.isEmptyBlock(gumPos)) {
			level.setBlock(gumPos, ModBlocks.PEACH_GUM_BLOCK.defaultBlockState(), 3);
		}

		return true;
	}
}
