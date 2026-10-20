package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.block.PeachGumBlock;
import com.zako.peachbloom.content.block.PeachGumSlimeBlock;
import com.zako.peachbloom.content.block.PeachSaplingBlock;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class ModBlocks {
	private ModBlocks() {
	}

	public static final Block PEACH_LOG = register("peach_log", RotatedPillarBlock::new, wood(MapColor.WOOD, MapColor.PODZOL));
	public static final Block STRIPPED_PEACH_LOG = register("stripped_peach_log", RotatedPillarBlock::new, wood(MapColor.WOOD, MapColor.WOOD));
	public static final Block PEACH_WOOD = register("peach_wood", RotatedPillarBlock::new, wood(MapColor.PODZOL, MapColor.PODZOL));
	public static final Block STRIPPED_PEACH_WOOD = register("stripped_peach_wood", RotatedPillarBlock::new, wood(MapColor.WOOD, MapColor.WOOD));
	public static final Block PEACH_PLANKS = register("peach_planks", Block::new, planks());
	public static final Block PEACH_LEAVES = register(
		"peach_leaves",
		p -> new LeavesBlock(AmbientLeavesBlockSoundPlayer.noAmbientSound(), p),
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_PINK)
			.strength(0.2F)
			.randomTicks()
			.sound(SoundType.GRASS)
			.noOcclusion()
			.ignitedByLava()
	);
	public static final Block PEACH_SAPLING = register(
		"peach_sapling",
		PeachSaplingBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.PLANT)
			.noCollision()
			.randomTicks()
			.instabreak()
			.sound(SoundType.GRASS)
			.pushReaction(PushReaction.POPPED)
	);
	public static final Block PEACH_GUM_BLOCK = register(
		"peach_gum_block",
		PeachGumBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_ORANGE)
			.noCollision()
			.instabreak()
			.sound(SoundType.SLIME_BLOCK)
			.noOcclusion()
	);
	public static final Block PEACH_GUM_SLIME_BLOCK = register(
		"peach_gum_slime_block",
		PeachGumSlimeBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_ORANGE)
			.friction(0.8F)
			.bounceRestitution(1.0F)
			.sound(SoundType.SLIME_BLOCK)
			.noOcclusion()
			.ignitedByLava()
	);

	private static BlockBehaviour.Properties wood(MapColor top, MapColor side) {
		return BlockBehaviour.Properties.of().mapColor(side).strength(2.0F).sound(SoundType.WOOD).ignitedByLava();
	}

	private static BlockBehaviour.Properties planks() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
	}

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, PeachBloom.id(name));
		Block block = factory.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	public static void init() {
	}
}
