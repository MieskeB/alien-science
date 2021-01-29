package nl.michelbijnen.minecraft.alien.science.registers;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import nl.michelbijnen.minecraft.alien.science.AlienScience;
import nl.michelbijnen.minecraft.alien.science.block.MoonPlant;

public class RegisterMoon {

    public static final ItemGroup ALIEN_SCIENCE_MOON_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(AlienScience.MODID, "alienscience_moon_group"))
            .icon(() -> new ItemStack(RegisterMoon.MOON_STONE))
            .build();

    public static final Block MOON_DUST = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MOON_GRAVEL = new FallingBlock(FabricBlockSettings.of(Material.SOIL).strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));
    public static final Block MOON_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final Block MOON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 3F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static final MoonPlant MOON_PLANT = new MoonPlant();


    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> MOON_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    MOON_DUST.getDefaultState(),
                    MOON_GRAVEL.getDefaultState(),
                    MOON_STONE.getDefaultState()));

    private static final Biome MOON = createMoon();

    public static final RegistryKey<Biome> MOON_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(AlienScience.MODID, "moon"));

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "moon_dust"), MOON_DUST);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "moon_dust"), new BlockItem(MOON_DUST, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "moon_gravel"), MOON_GRAVEL);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "moon_gravel"), new BlockItem(MOON_GRAVEL, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "moon_stone"), MOON_STONE);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "moon_stone"), new BlockItem(MOON_STONE, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "moon_ore"), MOON_ORE);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "moon_ore"), new BlockItem(MOON_ORE, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));
        BlockRenderLayerMap.INSTANCE.putBlock(MOON_PLANT, RenderLayer.getCutout());
        Registry.register(Registry.BLOCK, new Identifier(AlienScience.MODID, "moon_plant"), MOON_PLANT);
        Registry.register(Registry.ITEM, new Identifier(AlienScience.MODID, "moon_plant"), new BlockItem(MOON_PLANT, new Item.Settings().group(ALIEN_SCIENCE_MOON_ITEM_GROUP)));

        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(AlienScience.MODID, "moonland"), MOON_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, MOON_KEY.getValue(), MOON);

        OverworldBiomes.addContinentalBiome(MOON_KEY, OverworldClimate.TEMPERATE, 2D);
        OverworldBiomes.addContinentalBiome(MOON_KEY, OverworldClimate.COOL, 2D);
    }

    private static Biome createMoon() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(MOON_SURFACE_BUILDER);
//        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
//        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
//        DefaultBiomeFeatures.addMineables(generationSettings);
//        DefaultBiomeFeatures.addDefaultOres(generationSettings);
//        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
//        DefaultBiomeFeatures.addSprings(generationSettings);
//        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.NONE)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(-200F)
                .downfall(0F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x000000)
                        .waterFogColor(0x473b3a)
                        .fogColor(0x474040)
                        .skyColor(0x474040)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
