package com.zako.peachbloom.content.effect;

import com.zako.peachbloom.content.combat.UndeadRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 辟邪：亡灵生物攻击时返还其攻击伤害的 10%，不造成击退。
 */
public class PeachReflectEffect extends MobEffect {
	public PeachReflectEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xFFB7D5);
	}

	@Override
	public void onMobHurt(ServerLevel level, LivingEntity mob, int amplifier, DamageSource source, float damage) {
		if (source.getEntity() instanceof LivingEntity attacker && attacker != mob && UndeadRules.isUndead(attacker)) {
			float reflect = damage * 0.10F;
			if (reflect > 0.0F) {
				attacker.hurtServer(level, level.damageSources().magic(), reflect);
			}
		}
	}
}
