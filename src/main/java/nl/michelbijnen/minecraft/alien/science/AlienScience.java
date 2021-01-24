package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AlienScience implements ModInitializer {

	public static final Item ALIEN_FINDER = new Item(new Item.Settings().group(ItemGroup.REDSTONE));

	public static final Block MOON_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());

	public static final Item EDIBLE_ALIEN_FINDER = new Item(new Item.Settings().group(ItemGroup.FOOD).food(AlienFinderFoodComponent.EDIBLE_ALIEN_FINDER));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("alienscience", "alien_finder"), ALIEN_FINDER);
		Registry.register(Registry.BLOCK, new Identifier("alienscience", "moon_stone"), MOON_STONE);
		Registry.register(Registry.ITEM, new Identifier("alienscience", "moon_stone"), new BlockItem(MOON_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("alienscience", "edible_alien_finder"), EDIBLE_ALIEN_FINDER);
	}
}
