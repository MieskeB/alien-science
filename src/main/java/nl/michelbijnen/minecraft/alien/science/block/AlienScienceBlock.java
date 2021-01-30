package nl.michelbijnen.minecraft.alien.science.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.group.AlienScienceGroup;

public class AlienScienceBlock {

    // ITEM GROUPS

    // MACHINES
    public static final Block MOB_TESTER = registerDefaultBlock(new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 6F).sounds(BlockSoundGroup.GLASS).breakByTool(FabricToolTags.PICKAXES).requiresTool()), Constants.Block.MOB_TESTER);
    public static final Block OXYGEN_GENERATOR = registerDefaultBlock(new Block(FabricBlockSettings.of(Material.METAL).strength(5F, 6F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES).requiresTool()), Constants.Block.OXYGEN_GENERATOR);

    // MOON NATURAL
    public static final Block MOON_DUST = registerMoonBlock(new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS)), Constants.Block.Moon.MOON_DUST);
    public static final Block MOON_GRAVEL = registerMoonBlock(new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS)), Constants.Block.Moon.MOON_GRAVEL);
    public static final Block MOON_STONE = registerMoonBlock(new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool()), Constants.Block.Moon.MOON_STONE);
    public static final MoonPlant MOON_PLANT = registerMoonBlock(new MoonPlant(), Constants.Block.Moon.MOON_PLANT);

    // ORES
    public static final Block MOON_ORE = registerMoonBlock(new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 3F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool()), Constants.Block.Moon.MOON_ORE);

    // ALIEN BUILDINGS
    public static final Block ALIEN_CRATE = registerDefaultBlock(new Block(FabricBlockSettings.of(Material.METAL).strength(5F, 6F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES)), Constants.Block.ALIEN_CRATE);


    public static void register() {

    }

    private static <T extends Block> T registerDefaultBlock(T block, String id) {
        return registerBlock(block, id, AlienScienceGroup.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP);
    }

    private static <T extends Block> T registerMoonBlock(T block, String id) {
        return registerBlock(block, id, AlienScienceGroup.ALIEN_SCIENCE_MOON_ITEM_GROUP);
    }

    private static <T extends Block> T registerBlock(T block, String id, ItemGroup group) {
        Identifier identifier = new Identifier(Constants.MODID, id);
        Registry.register(Registry.BLOCK, identifier, block);
        BlockItem item = Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(group)));
        item.appendBlocks(Item.BLOCK_ITEMS, item);
        return block;
    }

    private static <T extends Block> T registerBlockWithoutItem(T block, String id) {
        return Registry.register(Registry.BLOCK, new Identifier(Constants.MODID, id), block);
    }
}
