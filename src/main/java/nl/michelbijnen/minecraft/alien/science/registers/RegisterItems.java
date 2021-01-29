package nl.michelbijnen.minecraft.alien.science.registers;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
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
import nl.michelbijnen.minecraft.alien.science.AlienScience;
import nl.michelbijnen.minecraft.alien.science.armor.AlienIngotArmorMaterial;
import nl.michelbijnen.minecraft.alien.science.armor.AlienIngotBaseArmor;
import nl.michelbijnen.minecraft.alien.science.food.AlienFinderFoodComponent;
import nl.michelbijnen.minecraft.alien.science.item.AlienFinder;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentFeature;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentGenerator;

public class RegisterItems {

    public static final ItemGroup ALIEN_SCIENCE_DEFAULT_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(AlienScience.MODID, "alienscience_default_group"))
            .icon(() -> new ItemStack(RegisterItems.ALIEN_FINDER))
            .build();

    public static final Block MOB_TESTER = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 6F).sounds(BlockSoundGroup.GLASS).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final Block OXYGEN_GENERATOR = new Block(FabricBlockSettings.of(Material.METAL).strength(5F, 6F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static final Item ALIEN_FINDER = new AlienFinder();
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

        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "mob_tester"), MOB_TESTER);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "mob_tester"), new BlockItem(MOB_TESTER, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "oxygen_generator"), OXYGEN_GENERATOR);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "oxygen_generator"), new BlockItem(OXYGEN_GENERATOR, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_finder"), ALIEN_FINDER);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "edible_alien_finder"), EDIBLE_ALIEN_FINDER);


        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(AlienScience.MODID, "testing_tent_piece"), TESTING_TENT_PIECE);
        FabricStructureBuilder.create(new Identifier(AlienScience.MODID, "testing_tent"), TESTING_TENT_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier(AlienScience.MODID, "testing_tent"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), TESTING_TENT_CONFIGURED);

        BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);
    }

    private static void registerMoon() {

    }

    private static void registerAlienIngot() {
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_ingot"), ALIEN_INGOT);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_ingot_helmet"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_ingot_chestplate"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_ingot_leggings"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_ingot_boots"), new AlienIngotBaseArmor(ALIEN_INGOT_ARMOR, EquipmentSlot.FEET));
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "alien_crate"), ALIEN_CRATE);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "alien_crate"), new BlockItem(ALIEN_CRATE, new Item.Settings().group(ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)));
    }
}
