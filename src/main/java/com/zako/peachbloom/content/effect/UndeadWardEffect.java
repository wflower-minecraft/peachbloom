package com.zako.peachbloom.content.effect;

import com.zako.peachbloom.content.combat.UndeadRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class UndeadWardEffect extends MobEffect {
	public UndeadWardEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xF7A8C4);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
		return tickCount % 20 == 0;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplifier) {
		double radius = 4.0 + amplifier;
		for (LivingEntity undead : level.getEntitiesOfClass(
			LivingEntity.class,
			mob.getBoundingBox().inflate(radius),
			entity -> entity != mob && UndeadRules.isUndead(entity)
		)) {
			undead.hurtServer(level, level.damageSources().magic(), 1.0F + amplifier);
			Vec3 push = undead.position().subtract(mob.position());
			if (push.lengthSqr() > 1.0E-4) {
				push = push.normalize().scale(0.35);
				undead.push(push.x, 0.15, push.z);
			}
		}
		return true;
	}
}
