package com.zako.peachbloom.content.portal;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

/**
 * 门户逻辑：在桃花树下撒干桃花进入桃花云维度，再撒一次返回主世界。
 */
public final class PeachPortal {
	private PeachPortal() {
	}

	public static final ResourceKey<Level> PEACH_BLOOM = ResourceKey.create(Registries.DIMENSION, PeachBloom.id("peach_bloom"));

	/** 是否站在桃花树下（±1 列向上 12 格内出现桃花原木或树叶）。 */
	public static boolean isUnderPeachTree(Level level, BlockPos pos) {
		for (int dx = -1; dx <= 1; dx++) {
			for (int dz = -1; dz <= 1; dz++) {
				for (int dy = 0; dy <= 12; dy++) {
					BlockState state = level.getBlockState(pos.offset(dx, dy, dz));
					if (state.is(ModBlocks.PEACH_LOG) || state.is(ModBlocks.PEACH_LEAVES)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void teleport(ServerLevel from, Player player) {
		ResourceKey<Level> targetKey = from.dimension() == PEACH_BLOOM ? Level.OVERWORLD : PEACH_BLOOM;
		ServerLevel target = from.getServer().getLevel(targetKey);
		if (target == null) {
			return;
		}

		BlockPos destination = findSafeSpot(target, player.blockPosition());
		Vec3 pos = new Vec3(destination.getX() + 0.5, destination.getY(), destination.getZ() + 0.5);
		TeleportTransition transition = new TeleportTransition(
			target, pos, Vec3.ZERO, player.getYRot(), player.getXRot(), TeleportTransition.DO_NOTHING
		);
		player.teleport(transition);
	}

	/** 螺旋向外搜索落点：站在实心地上、头顶无遮挡、避开水面。 */
	private static BlockPos findSafeSpot(ServerLevel level, BlockPos center) {
		int minX = level.getMinY() + 2;
		int maxY = Math.min(level.getMaxY() - 2, 148);
		for (int radius = 0; radius <= 64; radius += 4) {
			for (int dx = -radius; dx <= radius; dx += 4) {
				for (int dz = -radius; dz <= radius; dz += 4) {
					if (Math.max(Math.abs(dx), Math.abs(dz)) != radius) {
						continue;
					}
					int x = center.getX() + dx;
					int z = center.getZ() + dz;
					int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
					if (y < minX) {
						y = minX;
					}
					if (y > maxY) {
						y = maxY;
					}
					BlockPos top = new BlockPos(x, y, z);
					if (isSafe(level, top)) {
						return top;
					}
				}
			}
		}
		// 找不到就压在出生列顶部，至少不会卡进方块里
		int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, center.getX(), center.getZ());
		return new BlockPos(center.getX(), Math.min(Math.max(y, minX), maxY), center.getZ());
	}

	private static boolean isSafe(ServerLevel level, BlockPos top) {
		BlockPos below = top.below();
		if (level.getFluidState(below).is(FluidTags.WATER) || level.getFluidState(top).is(FluidTags.WATER)) {
			return false;
		}
		return level.getBlockState(top).isAir() && level.getBlockState(top.above()).isAir()
			&& level.getBlockState(below).isSolid();
	}
}
