package nl.michelbijnen.minecraft.alien.science.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import nl.michelbijnen.minecraft.alien.science.entity.AlienScienceEntityTypes;

import java.util.List;

public class TestingTentFeature extends JigsawFeature {
    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = ImmutableList.<SpawnSettings.SpawnEntry>builder().add(new SpawnSettings.SpawnEntry(AlienScienceEntityTypes.ALIEN, 1, 1, 2)).build();

    public TestingTentFeature(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, 0, true, true);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int i, int j, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig structurePoolFeatureConfig) {
        int k = i >> 4;
        int m = j >> 4;
        chunkRandom.setSeed((long)(k ^ m << 4) ^ l);
        chunkRandom.nextInt();
        if (chunkRandom.nextInt(5) != 0) {
            return false;
        } else {
            return !this.ensureNoVillage(chunkGenerator, l, chunkRandom, i, j);
        }
    }

    private boolean ensureNoVillage(ChunkGenerator chunkGenerator, long l, ChunkRandom chunkRandom, int i, int j) {
        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(StructureFeature.VILLAGE);
        if (structureConfig != null) {
            for (int k = i - 10; k <= i + 10; ++k) {
                for (int m = j - 10; m <= j + 10; ++m) {
                    ChunkPos chunkPos = StructureFeature.VILLAGE.getStartChunk(structureConfig, l, chunkRandom, k, m);
                    if (k == chunkPos.x && m == chunkPos.z) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }
}
