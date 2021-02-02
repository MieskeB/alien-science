package nl.michelbijnen.minecraft.alien.science.world.gen.carver;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import nl.michelbijnen.minecraft.alien.science.Constants;

public class AlienScienceCarver {
    public static final Carver<ProbabilityConfig> MOON_CAVE = Registry.register(Registry.CARVER, new Identifier(Constants.MODID, Constants.Carver.MOON_CAVE), new MoonCaveCarver(ProbabilityConfig.CODEC, 128));

    public static void register() {
    }
}
