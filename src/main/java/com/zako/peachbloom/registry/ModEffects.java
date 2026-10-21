package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.effect.PeachReflectEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;

public final class ModEffects {
	private ModEffects() {
	}

	public static final MobEffect PEACH_REFLECT = register("peach_reflect", new PeachReflectEffect());
	public static final Potion PEACH_WARD = registerPotion("peach_ward");

	public static Holder<MobEffect> reflect() {
		return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(PEACH_REFLECT);
	}

	private static MobEffect register(String name, MobEffect effect) {
		ResourceKey<MobEffect> key = ResourceKey.create(Registries.MOB_EFFECT, PeachBloom.id(name));
		return Registry.register(BuiltInRegistries.MOB_EFFECT, key, effect);
	}

	private static Potion registerPotion(String name) {
		ResourceKey<Potion> key = ResourceKey.create(Registries.POTION, PeachBloom.id(name));
		return Registry.register(BuiltInRegistries.POTION, key, new Potion(name, new net.minecraft.world.effect.MobEffectInstance(reflect(), 3600, 0)));
	}

	public static void init() {
	}
}
