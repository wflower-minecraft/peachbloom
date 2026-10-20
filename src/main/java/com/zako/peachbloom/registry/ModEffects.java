package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.effect.PeachReflectEffect;
import com.zako.peachbloom.content.effect.UndeadWardEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

public final class ModEffects {
	private ModEffects() {
	}

	public static final MobEffect UNDEAD_WARD = register("undead_ward", new UndeadWardEffect());
	public static final MobEffect PEACH_REFLECT = register("peach_reflect", new PeachReflectEffect());

	public static Holder<MobEffect> ward() {
		return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(UNDEAD_WARD);
	}

	public static Holder<MobEffect> reflect() {
		return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(PEACH_REFLECT);
	}

	private static MobEffect register(String name, MobEffect effect) {
		ResourceKey<MobEffect> key = ResourceKey.create(Registries.MOB_EFFECT, PeachBloom.id(name));
		return Registry.register(BuiltInRegistries.MOB_EFFECT, key, effect);
	}

	public static void init() {
	}
}
