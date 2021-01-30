package nl.michelbijnen.minecraft.alien.science.world.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.*;
import nl.michelbijnen.minecraft.alien.science.AlienScience;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.block.AlienScienceBlock;
import nl.michelbijnen.minecraft.alien.science.world.gen.chunk.MoonChunkGenerator;

public class AlienScienceDimensions {
    public static final RegistryKey<World> MOON = RegistryKey.of(Registry.DIMENSION, new Identifier(Constants.MODID, Constants.Dimension.Moon.NAME));

    public static void register() {
        BuiltinRegistries.add(BuiltinRegistries.CHUNK_GENERATOR_SETTINGS, new Identifier(Constants.MODID, Constants.Dimension.Moon.NAME), new ChunkGeneratorSettings(
                new StructuresConfig(false),
                new GenerationShapeConfig(
                        256, new NoiseSamplingConfig(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D),
                        new SlideConfig(-10, 3, 0), new SlideConfig(-30, 0, 0),
                        1, 2, 1.0D, -0.46875D, true,
                        true, false, false),
                AlienScienceBlock.MOON_STONE.getDefaultState(), Blocks.AIR.getDefaultState(), -10, 0, 63, false)
        );

        Registry.register(Registry.CHUNK_GENERATOR, new Identifier(Constants.MODID, Constants.Dimension.Moon.NAME), MoonChunkGenerator.CODEC);
    }
}
