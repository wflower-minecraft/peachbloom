package com.zako.peachbloom.content.item;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 桃树枝：去除所有负面效果，并附加生命回复/力量/抗性/幸运。
 */
public class PeachBranchItem extends Item {
	public PeachBranchItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		for (MobEffectInstance instance : List.copyOf(player.getActiveEffects())) {
			if (instance.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
				player.removeEffect(instance.getEffect());
			}
		}

		player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
		player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 200, 1));
		player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 200, 1));
		player.addEffect(new MobEffectInstance(MobEffects.LUCK, 2400, 4));

		ServerLevel serverLevel = (ServerLevel) level;
		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 1.0F);
		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
		}
		return InteractionResult.SUCCESS;
	}
}
