package nl.michelbijnen.minecraft.alien.science.registers;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentFeature;
import nl.michelbijnen.minecraft.alien.science.structures.TestingTentGenerator;

public class RegisterItems {

    public static final StructurePieceType TESTING_TENT_PIECE = TestingTentGenerator.TestingTentPiece::new;
    public static final StructureFeature<DefaultFeatureConfig> TESTING_TENT_STRUCTURE = new TestingTentFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> TESTING_TENT_CONFIGURED = TESTING_TENT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void register() {
        // TODO move to proper location
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Constants.MODID, Constants.Structures.TestingTent.PIECE), TESTING_TENT_PIECE);
        FabricStructureBuilder.create(new Identifier(Constants.MODID, Constants.Structures.TestingTent.NAME), TESTING_TENT_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier(Constants.MODID, Constants.Structures.TestingTent.NAME));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), TESTING_TENT_CONFIGURED);

        BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);
    }
}
