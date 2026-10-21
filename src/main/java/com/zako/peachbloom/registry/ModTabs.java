package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModTabs {
	private ModTabs() {
	}

	public static final ResourceKey<CreativeModeTab> PEACH_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, PeachBloom.id("peach"));

	public static void init() {
		Registry.register(
			BuiltInRegistries.CREATIVE_MODE_TAB,
			PEACH_TAB,
			CreativeModeTab.builder(CreativeModeTab.Row.TOP, 7)
				.title(Component.translatable("itemGroup.peachbloom.peach"))
				.icon(() -> new ItemStack(ModItems.PEACH))
				.displayItems((parameters, output) -> {
					output.accept(ModItems.PEACH_LOG);
					output.accept(ModItems.STRIPPED_PEACH_LOG);
					output.accept(ModItems.PEACH_WOOD);
					output.accept(ModItems.STRIPPED_PEACH_WOOD);
					output.accept(ModItems.PEACH_PLANKS);
					output.accept(ModItems.PEACH_STAIRS);
					output.accept(ModItems.PEACH_SLAB);
					output.accept(ModItems.PEACH_FENCE);
					output.accept(ModItems.PEACH_FENCE_GATE);
					output.accept(ModItems.PEACH_DOOR);
					output.accept(ModItems.PEACH_TRAPDOOR);
					output.accept(ModItems.PEACH_PRESSURE_PLATE);
					output.accept(ModItems.PEACH_BUTTON);
					output.accept(ModItems.PEACH_LEAVES);
					output.accept(ModItems.PEACH_SAPLING);
					output.accept(ModItems.PEACH_GUM_BLOCK);
					output.accept(ModItems.PEACH_GUM_SLIME_BLOCK);
					output.accept(ModItems.PEACH_GRASS_BLOCK);
					output.accept(ModItems.PEACH_DIRT);
					output.accept(ModItems.PEACH_STONE);
					output.accept(ModItems.PEACH_CLOUD);
					output.accept(ModItems.PEACH_CARROT);
					output.accept(ModItems.GOLDEN_PEACH_CARROT);
					output.accept(ModItems.PEACH);
					output.accept(ModItems.DRIED_PEACH);
					output.accept(ModItems.CANNED_PEACH);
					output.accept(ModItems.GOLDEN_PEACH);
					output.accept(ModItems.PEACH_GUM);
					output.accept(ModItems.PEACH_GUM_PORRIDGE_BUCKET);
					output.accept(ModItems.PEACH_MILK_BUCKET);
					output.accept(ModItems.PEACH_HONEY_BOTTLE);
					output.accept(ModItems.DRIED_PEACH_BLOSSOM);
					output.accept(ModItems.PEACH_BRANCH);
					output.accept(ModItems.PEACH_INGOT);
					output.accept(ModItems.PEACH_SWORD);
					output.accept(ModItems.PEACH_PICKAXE);
					output.accept(ModItems.PEACH_AXE);
					output.accept(ModItems.PEACH_SHOVEL);
					output.accept(ModItems.PEACH_HOE);
					output.accept(ModItems.PEACH_HELMET);
					output.accept(ModItems.PEACH_CHESTPLATE);
					output.accept(ModItems.PEACH_LEGGINGS);
					output.accept(ModItems.PEACH_BOOTS);
				})
				.build()
		);
	}
}
