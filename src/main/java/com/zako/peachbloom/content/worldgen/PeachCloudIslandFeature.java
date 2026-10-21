package com.zako.peachbloom.content.worldgen;

import com.mojang.serialization.MapCodec;
import com.zako.peachbloom.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

/**
 * 桃花云空岛：在桃花云端群系（y>70）随机生成一片片成团的扁云。
 */
public class PeachCloudIslandFeature implements Feature {
	public static final MapCodec<PeachCloudIslandFeature> CODEC = MapCodec.unit(PeachCloudIslandFeature::new);

	@Override
	public MapCodec<? extends Feature> codec() {
		return CODEC;
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
		int rx = 6 + random.nextInt(9);
		int rz = 6 + random.nextInt(9);
		int ry = 1 + random.nextInt(2);
		boolean placed = false;

		for (int dx = -rx; dx <= rx; dx++) {
			for (int dz = -rz; dz <= rz; dz++) {
				for (int dy = -ry; dy <= ry; dy++) {
					double fx = (double) dx / rx;
					double fz = (double) dz / rz;
					double fy = (double) dy / ry;
					if (fx * fx + fz * fz + fy * fy > 1.0) {
						continue;
					}
					BlockPos pos = origin.offset(dx, dy, dz);
					if (pos.getY() < level.getMinY() || pos.getY() >= level.getMaxY()) {
						continue;
					}
					if (level.getBlockState(pos).isAir()) {
						level.setBlock(pos, ModBlocks.PEACH_CLOUD.defaultBlockState(), 2);
						placed = true;
					}
				}
			}
		}
		return placed;
	}
}
