package nl.michelbijnen.minecraft.alien.science.world.biome.source;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nl.michelbijnen.minecraft.alien.science.AlienScience;

public class AlienScienceSources {
    public static void register() {
        Registry.register(Registry.BIOME_SOURCE, new Identifier(AlienScience.MODID, "moon"), MoonBiomeSource.CODEC);
    }
}
