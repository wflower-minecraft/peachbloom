package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.entity.CloudClusterMonster;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public final class ModEntities {
	private ModEntities() {
	}

	public static final EntityType<CloudClusterMonster> CLOUD_MONSTER = register(
		"cloud_monster",
		EntityType.Builder.of(CloudClusterMonster::new, MobCategory.MONSTER)
			.sized(0.8F, 1.2F)
			.fireImmune()
	);

	private static EntityType<CloudClusterMonster> register(String name, EntityType.Builder<CloudClusterMonster> builder) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, PeachBloom.id(name));
		EntityType<CloudClusterMonster> type = builder.build(key);
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
	}

	public static void init() {
		FabricDefaultAttributeRegistry.register(CLOUD_MONSTER, CloudClusterMonster.createAttributes());
		SpawnPlacements.register(
			CLOUD_MONSTER,
			SpawnPlacementTypes.ON_GROUND,
			Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
			CloudClusterMonster::checkCloudSpawnRules
		);
	}
}
