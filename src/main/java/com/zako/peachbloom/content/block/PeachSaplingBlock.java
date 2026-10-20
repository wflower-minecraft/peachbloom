package com.zako.peachbloom.content.block;

import com.zako.peachbloom.content.worldgen.PeachTreeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PeachSaplingBlock extends VegetationBlock implements BonemealableBlock {
	public static final int MIN_LIGHT = 5;

	public PeachSaplingBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.getMaxLocalRawBrightness(pos.above()) >= MIN_LIGHT && random.nextInt(7) == 0) {
			this.grow(level, pos, random);
		}
	}

	private void grow(ServerLevel level, BlockPos pos, RandomSource random) {
		PeachTreeBuilder.grow(level, pos, random);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source) {
		return level.getMaxLocalRawBrightness(pos.above()) >= MIN_LIGHT;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		return level.getMaxLocalRawBrightness(pos.above()) >= MIN_LIGHT;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {
		this.grow(level, pos, random);
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
}
