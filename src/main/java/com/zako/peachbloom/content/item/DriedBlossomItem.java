package com.zako.peachbloom.content.item;

import com.zako.peachbloom.content.portal.PeachPortal;
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
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * 干桃花：堆叠 16，可燃，非面对生物时可食用。
 * 优先级：桃花树下 → 传送维度；面对生物 → 洒出（亡灵受少量伤害）；否则进食。
 */
public class DriedBlossomItem extends Item {
	private static final double REACH = 6.0;

	public DriedBlossomItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level instanceof ServerLevel serverLevel) {
			// 1. 在桃花树下：进入/退出桃花云维度
			if (PeachPortal.isUnderPeachTree(level, player.blockPosition())) {
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				PeachPortal.teleport(serverLevel, player);
				return InteractionResult.SUCCESS;
			}

			// 2. 面对生物：洒出干桃花
			HitResult hit = player.pick(REACH, 1.0F, false);
			if (hit instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity target) {
				sprinkle(serverLevel, player, target);
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		}

		// 3. 没有面对生物：直接食用
		return super.use(level, player, hand);
	}

	private static void sprinkle(ServerLevel level, Player player, LivingEntity target) {
		target.hurtServer(level, level.damageSources().magic(), 2.0F);

		Vec3 center = target.position();
		for (int i = 0; i < 16; i++) {
			level.sendParticles(
				ParticleTypes.CHERRY_LEAVES,
				center.x + (level.getRandom().nextDouble() - 0.5) * 1.2,
				center.y + target.getBbHeight() * 0.7 + level.getRandom().nextDouble() * 0.4,
				center.z + (level.getRandom().nextDouble() - 0.5) * 1.2,
				1, 0.0, 0.0, 0.0, 0.01
			);
		}
		level.playSound(null, center.x, center.y, center.z, SoundEvents.CHERRY_LEAVES_PLACE, SoundSource.PLAYERS, 1.0F, 1.2F);
	}
}
