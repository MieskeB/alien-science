package nl.michelbijnen.minecraft.alien.science.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.world.gen.feature.MoonBaseFeature;
import nl.michelbijnen.minecraft.alien.science.world.gen.feature.TestingTentFeature;

public class AlienScienceStructure {
    public static final Codec<StructurePoolFeatureConfig> STRUCTURE_POOL_CONFIG_CODEC_UNCAPPED_SIZE = RecordCodecBuilder.create((instance) -> instance.group(StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(StructurePoolFeatureConfig::getStartPool), Codec.INT.fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)).apply(instance, StructurePoolFeatureConfig::new));

    public static final TestingTentFeature TESTING_TENT_FEATURE = new TestingTentFeature(STRUCTURE_POOL_CONFIG_CODEC_UNCAPPED_SIZE);

    public static final MoonBaseFeature MOON_BASE_FEATURE = new MoonBaseFeature(STRUCTURE_POOL_CONFIG_CODEC_UNCAPPED_SIZE);

    public static void register() {
        FabricStructureBuilder.create(new Identifier(Constants.MODID, Constants.Structures.TestingTent.NAME), TESTING_TENT_FEATURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 16, 23789482).adjustsSurface()
                .register();
        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier(Constants.MODID, Constants.Structures.TestingTent.NAME));
        BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);

        FabricStructureBuilder.create(new Identifier(Constants.MODID, Constants.Structures.MoonBase.NAME), MOON_BASE_FEATURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 16, 23789482).adjustsSurface()
                .register();
    }
}
