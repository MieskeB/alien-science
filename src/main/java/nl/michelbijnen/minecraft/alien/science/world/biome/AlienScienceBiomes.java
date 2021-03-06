package nl.michelbijnen.minecraft.alien.science.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import nl.michelbijnen.minecraft.alien.science.AlienScience;
import nl.michelbijnen.minecraft.alien.science.Constants;

public class AlienScienceBiomes {

    public static class Moon {
        public static final RegistryKey<Biome> MOON_PLAINS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Constants.MODID, Constants.Dimension.Moon.PLAINS));
        public static final RegistryKey<Biome> MOON_PLAINS_VALLEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Constants.MODID, Constants.Dimension.Moon.PLAINS_VALLEY));

        private static void init() {
        }
    }

    public static void register() {
        Moon.init();
    }
}
