package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import static nl.michelbijnen.minecraft.alien.science.RegisterItems.*;

public class AlienScience implements ModInitializer {

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("alienscience", "moon_dust"), MOON_DUST);
        Registry.register(Registry.ITEM, new Identifier("alienscience", "moon_dust"), new BlockItem(MOON_DUST, new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("alienscience", "moon_stone"), MOON_STONE);
        Registry.register(Registry.ITEM, new Identifier("alienscience", "moon_stone"), new BlockItem(MOON_STONE, new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("alienscience", "mob_tester"), MOB_TESTER);
        Registry.register(Registry.ITEM, new Identifier("alienscience", "mob_tester"), new BlockItem(MOB_TESTER, new Item.Settings().group(ALIEN_SCIENCE_ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier("alienscience", "alien_finder"), ALIEN_FINDER);
        Registry.register(Registry.ITEM, new Identifier("alienscience", "edible_alien_finder"), EDIBLE_ALIEN_FINDER);

        Registry.register(Registry.ITEM, new Identifier("alienscience", "alien_head"), ALIEN_HEAD);


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
