package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class AlienScience implements ModInitializer {

	public static final Item ALIEN_FINDER = new Item(new Item.Settings().group(ItemGroup.REDSTONE));

	public static final Block MOON_DUST = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS));
	public static final Block MOON_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());

	public static final Item EDIBLE_ALIEN_FINDER = new Item(new Item.Settings().group(ItemGroup.FOOD).food(AlienFinderFoodComponent.EDIBLE_ALIEN_FINDER));

	public static final Block MOB_TESTER = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 6F).sounds(BlockSoundGroup.GLASS).breakByTool(FabricToolTags.PICKAXES).requiresTool());

	public static final StructurePieceType TESTING_TENT_PIECE = TestingTentGenerator.TestingTentPiece::new;
	private static final StructureFeature<DefaultFeatureConfig> TESTING_TENT_STRUCTURE = new TestingTentFeature(DefaultFeatureConfig.CODEC);
	private static final ConfiguredStructureFeature<?, ?> TESTING_TENT_CONFIGURED = TESTING_TENT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("alienscience", "alien_finder"), ALIEN_FINDER);
		Registry.register(Registry.BLOCK, new Identifier("alienscience", "moon_dust"), MOON_DUST);
		Registry.register(Registry.ITEM, new Identifier("alienscience", "moon_dust"), new BlockItem(MOON_DUST, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier("alienscience", "moon_stone"), MOON_STONE);
		Registry.register(Registry.ITEM, new Identifier("alienscience", "moon_stone"), new BlockItem(MOON_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("alienscience", "edible_alien_finder"), EDIBLE_ALIEN_FINDER);
		Registry.register(Registry.BLOCK, new Identifier("alienscience", "mob_tester"), MOB_TESTER);
		Registry.register(Registry.ITEM, new Identifier("alienscience", "mob_tester"), new BlockItem(MOB_TESTER, new Item.Settings().group(ItemGroup.REDSTONE)));

		Registry.register(Registry.STRUCTURE_PIECE, new Identifier("alienscience", "testing_tent_piece"), TESTING_TENT_PIECE);
		FabricStructureBuilder.create(new Identifier("alienscience", "testing_tent"), TESTING_TENT_STRUCTURE)
				.step(GenerationStep.Feature.SURFACE_STRUCTURES)
				.defaultConfig(32, 8, 12345)
				.adjustsSurface()
				.register();

		RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
				new Identifier("alienscience", "testing_tent"));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), TESTING_TENT_CONFIGURED);

		BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);
	}
}
