package com.zako.peachbloom.content.combat;

import com.zako.peachbloom.registry.ModTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;

public final class UndeadRules {
	private UndeadRules() {
	}

	public static boolean isUndead(Entity entity) {
		return entity.getType().builtInRegistryHolder().is(EntityTypeTags.UNDEAD)
			|| entity.getType().builtInRegistryHolder().is(ModTags.UNDEAD);
	}
}
