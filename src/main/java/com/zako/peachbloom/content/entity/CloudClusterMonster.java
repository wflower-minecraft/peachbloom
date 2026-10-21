package com.zako.peachbloom.content.entity;

import com.zako.peachbloom.registry.ModBlocks;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

/**
 * 云簇怪：桃花云端上的敌对生物，移动缓慢，攻击附带漂浮 3s 与缓慢 1s，击杀掉落桃花云。
 */
public class CloudClusterMonster extends Monster {
	public CloudClusterMonster(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.xpReward = 5;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH, 20.0)
			.add(Attributes.ATTACK_DAMAGE, 3.0)
			.add(Attributes.MOVEMENT_SPEED, 0.20)
			.add(Attributes.FOLLOW_RANGE, 24.0)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.6, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.4));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public boolean doHurtTarget(ServerLevel level, Entity target) {
		boolean hurt = super.doHurtTarget(level, target);
		if (hurt && target instanceof LivingEntity living) {
			List.of(
				new MobEffectInstance(MobEffects.LEVITATION, 60, 0),
				new MobEffectInstance(MobEffects.SLOWNESS, 20, 0)
			).forEach(effect -> living.addEffect(effect, this));
		}
		return hurt;
	}

	/** 只在 y>70 的桃花云上方刷新。 */
	public static boolean checkCloudSpawnRules(EntityType<? extends Monster> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return pos.getY() > 70
			&& level.getBlockState(pos.below()).is(ModBlocks.PEACH_CLOUD)
			&& Monster.checkAnyLightMonsterSpawnRules(type, level, reason, pos, random);
	}
}
