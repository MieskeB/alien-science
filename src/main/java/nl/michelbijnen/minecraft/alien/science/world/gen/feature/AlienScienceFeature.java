package nl.michelbijnen.minecraft.alien.science.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.mixin.BlockStateProviderTypeAccessor;
import nl.michelbijnen.minecraft.alien.science.world.gen.stateprovider.MoonPlantBlockStateProvider;

public class AlienScienceFeature {

    public static final BlockStateProviderType<MoonPlantBlockStateProvider> MOON_PLANT_PROVIDER = BlockStateProviderTypeAccessor.callRegister(new Identifier(Constants.MODID, Constants.Feature.MOON_PLANT_PROVIDER).toString(), MoonPlantBlockStateProvider.CODEC);

    public static void register() {
    }
}
