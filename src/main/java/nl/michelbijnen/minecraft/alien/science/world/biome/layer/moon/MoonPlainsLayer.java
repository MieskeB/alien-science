package nl.michelbijnen.minecraft.alien.science.world.biome.layer.moon;

import net.minecraft.world.biome.layer.type.InitLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import nl.michelbijnen.minecraft.alien.science.world.biome.layer.MoonBiomeLayers;

public enum MoonPlainsLayer implements InitLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int x, int y) {
        int i = context.nextInt(6);
        if (i <= 4) {
            return MoonBiomeLayers.MOON_PLAINS_ID;
        } else {
            return MoonBiomeLayers.MOON_PLAINS_VALLEY_ID;
        }
    }
}
