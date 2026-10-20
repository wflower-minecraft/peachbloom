package com.zako.peachbloom.registry;

import com.zako.peachbloom.PeachBloom;
import com.zako.peachbloom.content.item.DriedBlossomItem;
import com.zako.peachbloom.content.item.PeachMilkItem;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

public final class ModItems {
	private ModItems() {
	}

	public static final ToolMaterial PEACH_TOOL_MATERIAL = new ToolMaterial(
		BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 32, 8.0F, 3.0F, 22, ItemTags.PLANKS
	);

	public static final ArmorMaterial PEACH_ARMOR_MATERIAL = new ArmorMaterial(
		7,
		Map.of(
			ArmorType.HELMET, 3,
			ArmorType.CHESTPLATE, 8,
			ArmorType.LEGGINGS, 6,
			ArmorType.BOOTS, 3,
			ArmorType.BODY, 11
		),
		22,
		SoundEvents.ARMOR_EQUIP_GOLD,
		0.0F,
		0.0F,
		ItemTags.PLANKS,
		EquipmentAssets.GOLD
	);

	public static final Item PEACH_LOG = blockItem("peach_log", ModBlocks.PEACH_LOG);
	public static final Item STRIPPED_PEACH_LOG = blockItem("stripped_peach_log", ModBlocks.STRIPPED_PEACH_LOG);
	public static final Item PEACH_WOOD = blockItem("peach_wood", ModBlocks.PEACH_WOOD);
	public static final Item STRIPPED_PEACH_WOOD = blockItem("stripped_peach_wood", ModBlocks.STRIPPED_PEACH_WOOD);
	public static final Item PEACH_PLANKS = blockItem("peach_planks", ModBlocks.PEACH_PLANKS);
	public static final Item PEACH_LEAVES = blockItem("peach_leaves", ModBlocks.PEACH_LEAVES);
	public static final Item PEACH_SAPLING = blockItem("peach_sapling", ModBlocks.PEACH_SAPLING);
	public static final Item PEACH_GUM_BLOCK = blockItem("peach_gum_block", ModBlocks.PEACH_GUM_BLOCK);
	public static final Item PEACH_GUM_SLIME_BLOCK = blockItem("peach_gum_slime_block", ModBlocks.PEACH_GUM_SLIME_BLOCK);

	public static final Item PEACH = item("peach", Item::new, new Item.Properties().food(food(4, 0.3F)));
	public static final Item DRIED_PEACH = item("dried_peach", Item::new, new Item.Properties().food(food(6, 0.8F)));
	public static final Item CANNED_PEACH = item(
		"canned_peach",
		Item::new,
		new Item.Properties().food(food(8, 1.0F), Consumable.builder()
			.consumeSeconds(1.6F)
			.animation(net.minecraft.world.item.ItemUseAnimation.EAT)
			.sound(SoundEvents.GENERIC_EAT)
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.ward(), 200, 0)))
			.build())
	);
	public static final Item GOLDEN_PEACH = item(
		"golden_peach",
		Item::new,
		new Item.Properties().rarity(Rarity.RARE).food(food(4, 1.2F), Consumable.builder()
			.consumeSeconds(1.6F)
			.animation(net.minecraft.world.item.ItemUseAnimation.EAT)
			.sound(SoundEvents.GENERIC_EAT)
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
				new MobEffectInstance(MobEffects.REGENERATION, 100, 1),
				new MobEffectInstance(MobEffects.SPEED, 600, 0)
			)))
			.build())
	);
	public static final Item PEACH_GUM = item(
		"peach_gum",
		Item::new,
		new Item.Properties().food(food(2, 0.4F), Consumable.builder()
			.consumeSeconds(1.6F)
			.animation(net.minecraft.world.item.ItemUseAnimation.EAT)
			.sound(SoundEvents.GENERIC_EAT)
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
				new MobEffectInstance(MobEffects.REGENERATION, 100, 0),
				new MobEffectInstance(ModEffects.ward(), 160, 0)
			)))
			.build())
	);
	public static final Item DRIED_PEACH_BLOSSOM = item("dried_peach_blossom", DriedBlossomItem::new, new Item.Properties());
	public static final Item PEACH_GUM_PORRIDGE_BUCKET = item(
		"peach_gum_porridge_bucket",
		Item::new,
		new Item.Properties()
			.stacksTo(1)
			.craftRemainder(net.minecraft.world.item.Items.BUCKET)
			.food(food(6, 0.6F), Consumable.builder()
				.consumeSeconds(2.0F)
				.animation(net.minecraft.world.item.ItemUseAnimation.DRINK)
				.sound(SoundEvents.GENERIC_DRINK)
				.hasConsumeParticles(false)
				.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.REGENERATION, 200, 0),
					new MobEffectInstance(ModEffects.ward(), 300, 0)
				)))
				.build())
	);
	public static final Item PEACH_MILK_BUCKET = item(
		"peach_milk_bucket",
		PeachMilkItem::new,
		new Item.Properties().stacksTo(1).craftRemainder(net.minecraft.world.item.Items.BUCKET)
	);
	public static final Item PEACH_HONEY_BOTTLE = item(
		"peach_honey_bottle",
		Item::new,
		new Item.Properties()
			.stacksTo(16)
			.craftRemainder(net.minecraft.world.item.Items.GLASS_BOTTLE)
			.food(food(0, 0.0F), Consumable.builder()
				.consumeSeconds(2.0F)
				.animation(net.minecraft.world.item.ItemUseAnimation.DRINK)
				.sound(SoundEvents.HONEY_DRINK)
				.hasConsumeParticles(false)
				.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.reflect(), 100, 0)))
				.build())
	);
	public static final Item PEACH_BRANCH = item(
		"peach_branch",
		Item::new,
		new Item.Properties().rarity(Rarity.RARE).food(food(1, 0.1F), Consumable.builder()
			.consumeSeconds(1.6F)
			.animation(net.minecraft.world.item.ItemUseAnimation.EAT)
			.sound(SoundEvents.GENERIC_EAT)
			.onConsume(ClearAllStatusEffectsConsumeEffect.INSTANCE)
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
				new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
				new MobEffectInstance(MobEffects.STRENGTH, 200, 1),
				new MobEffectInstance(MobEffects.RESISTANCE, 200, 1),
				new MobEffectInstance(MobEffects.LUCK, 2400, 4)
			)))
			.build())
	);
	public static final Item PEACH_INGOT = item("peach_ingot", Item::new, new Item.Properties());

	public static final Item PEACH_SWORD = item(
		"peach_sword",
		Item::new,
		withEnchantments(new Item.Properties().sword(PEACH_TOOL_MATERIAL, 3.0F, -2.4F), Map.of(Enchantments.SMITE, 1))
	);
	public static final Item PEACH_PICKAXE = item(
		"peach_pickaxe",
		Item::new,
		withEnchantments(new Item.Properties().pickaxe(PEACH_TOOL_MATERIAL, 1.0F, -2.8F), Map.of(Enchantments.FORTUNE, 2))
	);
	public static final Item PEACH_AXE = item(
		"peach_axe",
		Item::new,
		withEnchantments(new Item.Properties().axe(PEACH_TOOL_MATERIAL, 5.0F, -3.0F), Map.of(Enchantments.FORTUNE, 2))
	);
	public static final Item PEACH_SHOVEL = item(
		"peach_shovel",
		Item::new,
		withEnchantments(new Item.Properties().shovel(PEACH_TOOL_MATERIAL, 1.5F, -3.0F), Map.of(Enchantments.FORTUNE, 2))
	);
	public static final Item PEACH_HOE = item(
		"peach_hoe",
		Item::new,
		withEnchantments(new Item.Properties().hoe(PEACH_TOOL_MATERIAL, -3.0F, 0.0F), Map.of(Enchantments.FORTUNE, 2))
	);

	public static final Item PEACH_HELMET = item(
		"peach_helmet",
		Item::new,
		withEnchantments(new Item.Properties().humanoidArmor(PEACH_ARMOR_MATERIAL, ArmorType.HELMET), Map.of(Enchantments.PROTECTION, 2, Enchantments.SMITE, 2))
	);
	public static final Item PEACH_CHESTPLATE = item(
		"peach_chestplate",
		Item::new,
		withEnchantments(new Item.Properties().humanoidArmor(PEACH_ARMOR_MATERIAL, ArmorType.CHESTPLATE), Map.of(Enchantments.PROTECTION, 2, Enchantments.SMITE, 2))
	);
	public static final Item PEACH_LEGGINGS = item(
		"peach_leggings",
		Item::new,
		withEnchantments(new Item.Properties().humanoidArmor(PEACH_ARMOR_MATERIAL, ArmorType.LEGGINGS), Map.of(Enchantments.PROTECTION, 2, Enchantments.SMITE, 2))
	);
	public static final Item PEACH_BOOTS = item(
		"peach_boots",
		Item::new,
		withEnchantments(new Item.Properties().humanoidArmor(PEACH_ARMOR_MATERIAL, ArmorType.BOOTS), Map.of(Enchantments.PROTECTION, 2, Enchantments.SMITE, 2))
	);

	private static FoodProperties food(int nutrition, float saturation) {
		return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build();
	}

	private static Item.Properties withEnchantments(Item.Properties properties, Map<ResourceKey<net.minecraft.world.item.enchantment.Enchantment>, Integer> enchantments) {
		return properties.delayedComponent(DataComponents.ENCHANTMENTS, context -> {
			ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
			// Some lightweight/custom servers (e.g. Limbo) do not send the
			// minecraft:enchantment registry during configuration. Treat missing
			// entries as "no default enchantment" instead of crashing the client.
			enchantments.forEach((key, level) -> context.get(key).ifPresent(holder -> mutable.set(holder, level)));
			return mutable.toImmutable();
		});
	}

	private static Item blockItem(String name, Block block) {
		return item(name, p -> new BlockItem(block, p), new Item.Properties());
	}

	private static Item item(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, PeachBloom.id(name));
		Item item = factory.apply(properties.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	public static void init() {
	}
}
