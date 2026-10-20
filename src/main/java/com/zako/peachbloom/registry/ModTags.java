package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public final class ModTags {
	private ModTags() {
	}

	public static final TagKey<Block> PEACH_LOGS = block("peach_logs");
	public static final TagKey<Item> PEACH_LOGS_ITEM = item("peach_logs");
	public static final TagKey<EntityType<?>> UNDEAD = TagKey.create(Registries.ENTITY_TYPE, PeachBloom.id("undead"));

	public static final TagKey<Biome> TREE_COMMON = TagKey.create(Registries.BIOME, PeachBloom.id("has_peach_tree/common"));
	public static final TagKey<Biome> TREE_UNCOMMON = TagKey.create(Registries.BIOME, PeachBloom.id("has_peach_tree/uncommon"));
	public static final TagKey<Biome> TREE_RARE = TagKey.create(Registries.BIOME, PeachBloom.id("has_peach_tree/rare"));
	public static final TagKey<Biome> NO_TREE = TagKey.create(Registries.BIOME, PeachBloom.id("no_peach_tree"));

	private static TagKey<Block> block(String name) {
		return TagKey.create(Registries.BLOCK, PeachBloom.id(name));
	}

	private static TagKey<Item> item(String name) {
		return TagKey.create(Registries.ITEM, PeachBloom.id(name));
	}
}
