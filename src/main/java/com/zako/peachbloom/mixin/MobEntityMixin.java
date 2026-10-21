package com.zako.peachbloom.mixin;

import com.zako.peachbloom.content.combat.UndeadRules;
import com.zako.peachbloom.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class MobEntityMixin {
	@Inject(method = "equipItemIfPossible", at = @At("RETURN"))
	private void peachbloom$igniteUndead(ServerLevel level, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		Mob self = (Mob) (Object) this;
		ItemStack equipped = cir.getReturnValue();
		if (equipped != null && !equipped.isEmpty() && equipped.getItem() == ModItems.PEACH_LOG && UndeadRules.isUndead(self)) {
			// 桃花原木让亡灵持续燃烧直至死亡（1 小时，远超其存活时间）
			self.igniteForSeconds(3600.0F);
		}
	}
}
