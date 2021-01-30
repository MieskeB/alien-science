package nl.michelbijnen.minecraft.alien.science.world.biome.source;

import net.minecraft.world.biome.BuiltinBiomes;
import nl.michelbijnen.minecraft.alien.science.mixin.BuiltinBiomesAccessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;
import nl.michelbijnen.minecraft.alien.science.world.biome.AlienScienceBiomes;
import nl.michelbijnen.minecraft.alien.science.world.biome.layer.MoonBiomeLayers;

import java.util.ArrayList;

public class MoonBiomeSource extends BiomeSource {
    public static final Codec<MoonBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").stable().forGetter((moonBiomeSource) -> moonBiomeSource.seed), Codec.INT.optionalFieldOf("biome_size", 4, Lifecycle.stable()).forGetter((moonBiomeSource) -> moonBiomeSource.biomeSize), RegistryLookupCodec.of(Registry.BIOME_KEY).forGetter((source) -> source.registry)).apply(instance, instance.stable(MoonBiomeSource::new)));

    private final BiomeLayerSampler sampler;
    private final long seed;
    private final int biomeSize;
    private final Registry<Biome> registry;
    private boolean initialized = false;

    public MoonBiomeSource(long seed, int biomeSize, Registry<Biome> registry) {
        super(new ArrayList<>());

        this.biomeSize = biomeSize;
        this.seed = seed;
        this.registry = registry;
        this.sampler = MoonBiomeLayers.build(seed, biomeSize, registry);

        if (!BuiltinBiomesAccessor.getBY_RAW_ID().containsValue(AlienScienceBiomes.Moon.MOON_PLAINS)) {
            BuiltinBiomesAccessor.getBY_RAW_ID().put(registry.getRawId(registry.get(AlienScienceBiomes.Moon.MOON_PLAINS)), AlienScienceBiomes.Moon.MOON_PLAINS);
        }
    }

    @Override
    protected Codec<? extends BiomeSource> getCodec() {
        return CODEC;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BiomeSource withSeed(long seed) {
        return new MoonBiomeSource(seed, this.biomeSize, registry);
    }

    @Override
    public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
        if (!this.initialized) {
            if (registry.getRawId(registry.get(AlienScienceBiomes.Moon.MOON_PLAINS)) != -1) {
                BuiltinBiomes.BY_RAW_ID.put(registry.getRawId(registry.get(AlienScienceBiomes.Moon.MOON_PLAINS)), AlienScienceBiomes.Moon.MOON_PLAINS);

                this.biomes.clear();
                this.biomes.add(registry.get(AlienScienceBiomes.Moon.MOON_PLAINS));
                this.structureFeatures.clear();
                this.topMaterials.clear();
                this.initialized = true;
            }
        }
        return this.sampler.sample(registry, biomeX, biomeZ);
    }
}
