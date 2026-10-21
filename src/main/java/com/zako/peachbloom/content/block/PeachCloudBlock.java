package com.zako.peachbloom.content.block;

import com.zako.peachbloom.registry.ModEntities;
import com.zako.peachbloom.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 桃花云：只有穿戴桃花靴子的玩家（以及云簇怪自己）可以站立，
 * 其他实体直接坠落。采集后不掉落任何物品。
 */
public class PeachCloudBlock extends Block {
	private static final VoxelShape SOLID = Shapes.block();
	private static final VoxelShape EMPTY = Shapes.empty();

	public PeachCloudBlock(Properties properties) {
		super(properties);
	}

	public static boolean canStandOn(Entity entity) {
		if (entity instanceof LivingEntity living) {
			if (living.getType() == ModEntities.CLOUD_MONSTER) {
				return true;
			}
			ItemStack boots = living.getItemBySlot(EquipmentSlot.FEET);
			return boots.is(ModItems.PEACH_BOOTS);
		}
		return false;
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (context instanceof EntityCollisionContext entityContext) {
			Entity entity = entityContext.getEntity();
			if (entity != null && canStandOn(entity)) {
				return SOLID;
			}
		}
		return EMPTY;
	}
}
