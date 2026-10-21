package com.zako.peachbloom;

import com.zako.peachbloom.registry.ModBlocks;
import com.zako.peachbloom.registry.ModEffects;
import com.zako.peachbloom.registry.ModEntities;
import com.zako.peachbloom.registry.ModFeatures;
import com.zako.peachbloom.registry.ModItems;
import com.zako.peachbloom.registry.ModTabs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeachBloom implements ModInitializer {
	public static final String MOD_ID = "peachbloom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.init();
		ModItems.init();
		ModEffects.init();
		ModEntities.init();
		ModFeatures.init();
		ModTabs.init();

		registerFlammable();
		registerStripping();

		LOGGER.info("[桃花运] 桃树发芽完成喵~");
	}

	private static void registerFlammable() {
		FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();
		flammable.add(ModBlocks.PEACH_LOG, 5, 5);
		flammable.add(ModBlocks.STRIPPED_PEACH_LOG, 5, 5);
		flammable.add(ModBlocks.PEACH_WOOD, 5, 5);
		flammable.add(ModBlocks.STRIPPED_PEACH_WOOD, 5, 5);
		flammable.add(ModBlocks.PEACH_PLANKS, 5, 20);
		flammable.add(ModBlocks.PEACH_STAIRS, 5, 20);
		flammable.add(ModBlocks.PEACH_SLAB, 5, 20);
		flammable.add(ModBlocks.PEACH_FENCE, 5, 20);
		flammable.add(ModBlocks.PEACH_FENCE_GATE, 5, 20);
		flammable.add(ModBlocks.PEACH_DOOR, 5, 20);
		flammable.add(ModBlocks.PEACH_TRAPDOOR, 5, 20);
		flammable.add(ModBlocks.PEACH_PRESSURE_PLATE, 5, 20);
		flammable.add(ModBlocks.PEACH_BUTTON, 5, 20);
		flammable.add(ModBlocks.PEACH_LEAVES, 30, 60);
		flammable.add(ModBlocks.PEACH_GUM_SLIME_BLOCK, 30, 60);
	}

	private static void registerStripping() {
		net.fabricmc.fabric.api.event.player.UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
			net.minecraft.world.level.block.state.BlockState state = level.getBlockState(hit.getBlockPos());
			net.minecraft.world.level.block.Block result;
			if (state.is(ModBlocks.PEACH_LOG)) {
				result = ModBlocks.STRIPPED_PEACH_LOG;
			} else if (state.is(ModBlocks.PEACH_WOOD)) {
				result = ModBlocks.STRIPPED_PEACH_WOOD;
			} else {
				return net.minecraft.world.InteractionResult.PASS;
			}

			net.minecraft.world.item.ItemStack stack = player.getItemInHand(hand);
			if (!stack.getItem().builtInRegistryHolder().is(net.minecraft.tags.ItemTags.AXES)) {
				return net.minecraft.world.InteractionResult.PASS;
			}

			if (!level.isClientSide()) {
				net.minecraft.world.level.block.state.BlockState stripped = result.defaultBlockState();
				if (state.hasProperty(net.minecraft.world.level.block.RotatedPillarBlock.AXIS)
					&& stripped.hasProperty(net.minecraft.world.level.block.RotatedPillarBlock.AXIS)) {
					stripped = stripped.setValue(
						net.minecraft.world.level.block.RotatedPillarBlock.AXIS,
						state.getValue(net.minecraft.world.level.block.RotatedPillarBlock.AXIS)
					);
				}
				level.setBlock(hit.getBlockPos(), stripped, 11);
				level.playSound(
					null, hit.getBlockPos(),
					net.minecraft.sounds.SoundEvents.AXE_STRIP.value(),
					net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F
				);
				if (!player.getAbilities().instabuild) {
					stack.hurtAndBreak(1, player, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
				}
			}
			return net.minecraft.world.InteractionResult.SUCCESS;
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
