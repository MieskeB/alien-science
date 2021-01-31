package nl.michelbijnen.minecraft.alien.science.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.world.gen.feature.TestingTentFeature;

public class AlienScienceStructure {
    public static final Codec<StructurePoolFeatureConfig> STRUCTURE_POOL_CONFIG_CODEC_UNCAPPED_SIZE = RecordCodecBuilder.create((instance) -> instance.group(StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(StructurePoolFeatureConfig::getStartPool), Codec.INT.fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)).apply(instance, StructurePoolFeatureConfig::new));

    public static final TestingTentFeature TESTING_TENT_FEATURE = new TestingTentFeature(STRUCTURE_POOL_CONFIG_CODEC_UNCAPPED_SIZE);

    public static void register() {
        FabricStructureBuilder.create(new Identifier(Constants.MODID, Constants.Structures.TestingTent.NAME), TESTING_TENT_FEATURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 16, 23789482).adjustsSurface()
                .register();
    }
}
