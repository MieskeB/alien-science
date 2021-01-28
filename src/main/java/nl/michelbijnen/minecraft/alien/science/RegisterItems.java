package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import nl.michelbijnen.minecraft.alien.science.armor.AlienArmorMaterial;
import nl.michelbijnen.minecraft.alien.science.food.AlienFinderFoodComponent;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentFeature;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentGenerator;

public class RegisterItems {

    public static final ItemGroup ALIEN_SCIENCE_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier("alienscience", "alienscience"))
            .icon(() -> new ItemStack(RegisterItems.ALIEN_HEAD))
            .build();

    public static final Block MOON_DUST = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MOON_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final Block MOB_TESTER = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 6F).sounds(BlockSoundGroup.GLASS).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static final Item ALIEN_FINDER = new Item(new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP));
    public static final Item EDIBLE_ALIEN_FINDER = new Item(new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP).food(AlienFinderFoodComponent.EDIBLE_ALIEN_FINDER));

    public static final ArmorMaterial alienArmorMaterial = new AlienArmorMaterial();
    public static final Item ALIEN_HEAD = new ArmorItem(alienArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP));


    public static final StructurePieceType TESTING_TENT_PIECE = TestingTentGenerator.TestingTentPiece::new;
    public static final StructureFeature<DefaultFeatureConfig> TESTING_TENT_STRUCTURE = new TestingTentFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> TESTING_TENT_CONFIGURED = TESTING_TENT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
}
