package com.zako.peachbloom.content.item;

import java.util.List;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PeachMilkItem extends Item {
	public PeachMilkItem(Properties properties) {
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

		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.8F, 1.0F);
		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
			ItemStack bucket = new ItemStack(Items.BUCKET);
			if (stack.isEmpty()) {
				player.setItemInHand(hand, bucket);
			} else {
				player.getInventory().placeItemBackInInventory(bucket, net.minecraft.util.Prediction.SERVER_ONLY);
			}
		}
		return InteractionResult.SUCCESS;
	}
}
