package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
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
import nl.michelbijnen.minecraft.alien.science.armor.AlienIngotArmorMaterial;
import nl.michelbijnen.minecraft.alien.science.armor.AlienIngotBaseArmor;
import nl.michelbijnen.minecraft.alien.science.block.MoonPlant;
import nl.michelbijnen.minecraft.alien.science.food.AlienFinderFoodComponent;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentFeature;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentGenerator;

public class RegisterItems {

    public static String MODID = "alienscience";

    public static final ItemGroup ALIEN_SCIENCE_DEFAULT_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MODID, "alienscience_default_group"))
            .icon(() -> new ItemStack(RegisterItems.ALIEN_FINDER))
            .build();

    public static final ItemGroup ALIEN_SCIENCE_MOON_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MODID, "alienscience_moon_group"))
            .icon(() -> new ItemStack(RegisterItems.MOON_STONE))
            .build();

    public static final Block MOON_DUST = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MOON_GRAVEL = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MOON_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final Block MOON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 3F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final MoonPlant MOON_PLANT = new MoonPlant();

    public static final Block MOB_TESTER = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 6F).sounds(BlockSoundGroup.GLASS).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final Block OXYGEN_GENERATOR = new Block(FabricBlockSettings.of(Material.METAL).strength(5F, 6F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static final Item ALIEN_FINDER = new Item(new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP).maxCount(1));
    public static final Item EDIBLE_ALIEN_FINDER = new Item(new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP).food(AlienFinderFoodComponent.EDIBLE_ALIEN_FINDER));

    public static final Item ALIEN_INGOT = new Item(new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP));

    public static final ArmorMaterial ALIEN_INGOT_ARMOR = new AlienIngotArmorMaterial();

    public static final Block ALIEN_CRATE = new Block(FabricBlockSettings.of(Material.METAL).strength(5F, 6F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES));


    public static final StructurePieceType TESTING_TENT_PIECE = TestingTentGenerator.TestingTentPiece::new;
    public static final StructureFeature<DefaultFeatureConfig> TESTING_TENT_STRUCTURE = new TestingTentFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> TESTING_TENT_CONFIGURED = TESTING_TENT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void register() {
        registerMoon();

        registerAlienIngot();

        Registry.register(Registry.BLOCK, new Identifier(MODID, "mob_tester"), MOB_TESTER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "mob_tester"), new BlockItem(MOB_TESTER, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "oxygen_generator"), OXYGEN_GENERATOR);
        Registry.register(Registry.ITEM, new Identifier(MODID, "oxygen_generator"), new BlockItem(OXYGEN_GENERATOR, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_finder"), ALIEN_FINDER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "edible_alien_finder"), EDIBLE_ALIEN_FINDER);


        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, "testing_tent_piece"), TESTING_TENT_PIECE);
        FabricStructureBuilder.create(new Identifier(MODID, "testing_tent"), TESTING_TENT_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier(MODID, "testing_tent"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), TESTING_TENT_CONFIGURED);

        BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);
    }

    private static void registerMoon() {
        Registry.register(Registry.BLOCK, new Identifier(MODID, "moon_dust"), MOON_DUST);
        Registry.register(Registry.ITEM, new Identifier(MODID, "moon_dust"), new BlockItem(MOON_DUST, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "moon_gravel"), MOON_GRAVEL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "moon_gravel"), new BlockItem(MOON_GRAVEL, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "moon_stone"), MOON_STONE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "moon_stone"), new BlockItem(MOON_STONE, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "moon_ore"), MOON_ORE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "moon_ore"), new BlockItem(MOON_ORE, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterItems.MOON_PLANT, RenderLayer.getCutout());
        Registry.register(Registry.BLOCK, new Identifier(MODID, "moon_plant"), MOON_PLANT);
        Registry.register(Registry.ITEM, new Identifier(MODID, "moon_plant"), new BlockItem(MOON_PLANT, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
    }

    private static void registerAlienIngot() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_ingot"), ALIEN_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_ingot_helmet"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_ingot_chestplate"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_ingot_leggings"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_ingot_boots"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.FEET));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "alien_crate"), ALIEN_CRATE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "alien_crate"), new BlockItem(ALIEN_CRATE, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));
    }
}
