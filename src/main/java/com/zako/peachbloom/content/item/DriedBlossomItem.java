package com.zako.peachbloom.content.item;

import com.zako.peachbloom.content.combat.UndeadRules;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DriedBlossomItem extends Item {
	public DriedBlossomItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		ServerLevel serverLevel = (ServerLevel) level;
		Vec3 center = player.position();
		for (LivingEntity undead : serverLevel.getEntitiesOfClass(
			LivingEntity.class,
			player.getBoundingBox().inflate(5.0),
			entity -> UndeadRules.isUndead(entity)
		)) {
			undead.hurtServer(serverLevel, serverLevel.damageSources().magic(), 2.0F);
			Vec3 push = undead.position().subtract(center);
			if (push.lengthSqr() > 1.0E-4) {
				push = push.normalize().scale(0.3);
				undead.push(push.x, 0.2, push.z);
			}
		}

		for (int i = 0; i < 24; i++) {
			double angle = serverLevel.getRandom().nextDouble() * Math.PI * 2.0;
			double radius = 0.5 + serverLevel.getRandom().nextDouble() * 4.5;
			serverLevel.sendParticles(
				ParticleTypes.CHERRY_LEAVES,
				center.x + Math.cos(angle) * radius,
				center.y + 0.8 + serverLevel.getRandom().nextDouble(),
				center.z + Math.sin(angle) * radius,
				1, 0.0, 0.0, 0.0, 0.01
			);
		}

		serverLevel.playSound(null, center.x, center.y, center.z, SoundEvents.CHERRY_LEAVES_PLACE, SoundSource.PLAYERS, 1.0F, 1.2F);
		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
		}
		return InteractionResult.SUCCESS;
	}
}
