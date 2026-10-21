package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.block.PeachCarrotBlock;
import com.zako.peachbloom.content.block.PeachCloudBlock;
import com.zako.peachbloom.content.block.PeachGumBlock;
import com.zako.peachbloom.content.block.PeachGumSlimeBlock;
import com.zako.peachbloom.content.block.PeachSaplingBlock;
import com.zako.peachbloom.content.block.StrippedPeachLogBlock;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class ModBlocks {
	private ModBlocks() {
	}

	public static final Block PEACH_LOG = register("peach_log", RotatedPillarBlock::new, wood(MapColor.WOOD, MapColor.PODZOL));
	public static final Block STRIPPED_PEACH_LOG = register("stripped_peach_log", StrippedPeachLogBlock::new, wood(MapColor.WOOD, MapColor.WOOD).randomTicks());
	public static final Block PEACH_WOOD = register("peach_wood", RotatedPillarBlock::new, wood(MapColor.PODZOL, MapColor.PODZOL));
	public static final Block STRIPPED_PEACH_WOOD = register("stripped_peach_wood", StrippedPeachLogBlock::new, wood(MapColor.WOOD, MapColor.WOOD).randomTicks());
	public static final Block PEACH_PLANKS = register("peach_planks", Block::new, planks());
	public static final Block PEACH_STAIRS = register("peach_stairs", p -> new StairBlock(PEACH_PLANKS.defaultBlockState(), p), stairs());
	public static final Block PEACH_SLAB = register("peach_slab", SlabBlock::new, slab());
	public static final Block PEACH_FENCE = register("peach_fence", FenceBlock::new, fence());
	public static final Block PEACH_FENCE_GATE = register("peach_fence_gate", p -> new FenceGateBlock(WoodType.OAK, p), fence());
	public static final Block PEACH_DOOR = register("peach_door", p -> new DoorBlock(BlockSetType.OAK, p), door());
	public static final Block PEACH_TRAPDOOR = register("peach_trapdoor", p -> new TrapDoorBlock(BlockSetType.OAK, p), trapdoor());
	public static final Block PEACH_PRESSURE_PLATE = register("peach_pressure_plate", p -> new PressurePlateBlock(BlockSetType.OAK, p), plate());
	public static final Block PEACH_BUTTON = register("peach_button", p -> new ButtonBlock(BlockSetType.OAK, 30, p), button());
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

	// —— 桃花云维度方块 ——
	public static final Block PEACH_GRASS_BLOCK = register(
		"peach_grass_block",
		Block::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_PINK)
			.strength(0.6F)
			.sound(SoundType.GRASS)
	);
	public static final Block PEACH_DIRT = register(
		"peach_dirt",
		Block::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_PINK)
			.strength(0.5F)
			.sound(SoundType.GRAVEL)
	);
	public static final Block PEACH_STONE = register(
		"peach_stone",
		Block::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_PINK)
			.requiresCorrectToolForDrops()
			.strength(1.5F, 6.0F)
			.sound(SoundType.STONE)
	);
	public static final Block PEACH_CLOUD = register(
		"peach_cloud",
		PeachCloudBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_PINK)
			.strength(0.3F)
			.sound(SoundType.WOOL)
			.noOcclusion()
			.isValidSpawn((state, level, pos, entityType) -> entityType == ModEntities.CLOUD_MONSTER)
	);
	public static final Block PEACH_CARROT_CROP = register(
		"peach_carrot_crop",
		PeachCarrotBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_ORANGE)
			.noCollision()
			.randomTicks()
			.instabreak()
			.sound(SoundType.CROP)
			.pushReaction(PushReaction.POPPED)
	);

	private static BlockBehaviour.Properties wood(MapColor top, MapColor side) {
		return BlockBehaviour.Properties.of().mapColor(side).strength(2.0F).sound(SoundType.WOOD).ignitedByLava();
	}

	private static BlockBehaviour.Properties planks() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
	}

	private static BlockBehaviour.Properties stairs() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noOcclusion();
	}

	private static BlockBehaviour.Properties slab() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS);
	}

	private static BlockBehaviour.Properties fence() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noOcclusion();
	}

	private static BlockBehaviour.Properties door() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noOcclusion();
	}

	private static BlockBehaviour.Properties trapdoor() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noOcclusion();
	}

	private static BlockBehaviour.Properties plate() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noCollision().strength(0.5F).pushReaction(PushReaction.POPPED);
	}

	private static BlockBehaviour.Properties button() {
		return BlockBehaviour.Properties.ofFullCopy(PEACH_PLANKS).noCollision().strength(0.5F).pushReaction(PushReaction.POPPED);
	}

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, PeachBloom.id(name));
		Block block = factory.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	public static void init() {
	}
}
