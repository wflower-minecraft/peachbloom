package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.worldgen.PeachTreeFeature;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

public final class ModFeatures {
	private ModFeatures() {
	}

	public static final ResourceKey<PlacedFeature> PEACH_TREE_COMMON = placed("peach_tree_common");
	public static final ResourceKey<PlacedFeature> PEACH_TREE_UNCOMMON = placed("peach_tree_uncommon");
	public static final ResourceKey<PlacedFeature> PEACH_TREE_RARE = placed("peach_tree_rare");

	private static ResourceKey<PlacedFeature> placed(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, PeachBloom.id(name));
	}

	public static void init() {
		Registry.register(BuiltInRegistries.FEATURE_TYPE, PeachBloom.id("peach_tree"), (MapCodec<? extends Feature>) (MapCodec<?>) PeachTreeFeature.CODEC);

		BiomeModifications.addFeature(notExcluded(ModTags.TREE_COMMON), GenerationStep.Decoration.VEGETAL_DECORATION, PEACH_TREE_COMMON);
		BiomeModifications.addFeature(notExcluded(ModTags.TREE_UNCOMMON), GenerationStep.Decoration.VEGETAL_DECORATION, PEACH_TREE_UNCOMMON);
		BiomeModifications.addFeature(notExcluded(ModTags.TREE_RARE), GenerationStep.Decoration.VEGETAL_DECORATION, PEACH_TREE_RARE);
	}

	private static Predicate<BiomeSelectionContext> notExcluded(net.minecraft.tags.TagKey<net.minecraft.world.level.biome.Biome> tag) {
		return context -> context.hasTag(tag) && !context.hasTag(ModTags.NO_TREE);
	}
}
