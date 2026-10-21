package com.zako.peachbloom.mixin;

import com.zako.peachbloom.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 用桃花萝卜喂猪后，繁殖一次会生下 2~3 只小猪。
 */
@Mixin(Animal.class)
public class AnimalMixin {
	@Unique
	private boolean peachbloom$atePeachCarrot;

	@Inject(method = "mobInteract", at = @At("TAIL"))
	private void peachbloom$markPeachCarrot(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
		ItemStack stack = player.getItemInHand(hand);
		Animal self = (Animal) (Object) this;
		if (stack.is(ModItems.PEACH_CARROT) && self.isFood(stack)) {
			this.peachbloom$atePeachCarrot = true;
		}
	}

	@Inject(method = "spawnChildFromBreeding", at = @At("TAIL"))
	private void peachbloom$extraBabies(ServerLevel level, Animal partner, CallbackInfo ci) {
		if (!this.peachbloom$atePeachCarrot) {
			return;
		}
		this.peachbloom$atePeachCarrot = false;
		Animal self = (Animal) (Object) this;
		// 原 1 只 + 额外 1~2 只 = 一次生下 2~3 只
		int extra = 1 + level.getRandom().nextInt(2);
		for (int i = 0; i < extra; i++) {
			AgeableMob baby = self.getBreedOffspring(level, self);
			baby.setAge(-24000);
			baby.snapTo(self.getX(), self.getY(), self.getZ(), self.getYRot(), self.getXRot());
			level.addFreshEntity(baby);
		}
	}
}
