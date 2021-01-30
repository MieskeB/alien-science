package nl.michelbijnen.minecraft.alien.science.world.gen.stateprovider;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import nl.michelbijnen.minecraft.alien.science.block.AlienScienceBlock;
import nl.michelbijnen.minecraft.alien.science.world.gen.feature.AlienScienceFeature;

import java.util.Random;

public class MoonPlantBlockStateProvider extends BlockStateProvider {
    public static final Codec<MoonPlantBlockStateProvider> CODEC = Codec.unit(() -> MoonPlantBlockStateProvider.INSTANCE);
    public static final MoonPlantBlockStateProvider INSTANCE = new MoonPlantBlockStateProvider();

    public static final BlockState[] mix1 = new BlockState[]{AlienScienceBlock.MOON_PLANT.getDefaultState()};
    public static final BlockState[] mix2 = new BlockState[]{AlienScienceBlock.MOON_PLANT.getDefaultState()};

    @Override
    protected BlockStateProviderType<MoonPlantBlockStateProvider> getType() {
        return AlienScienceFeature.MOON_PLANT_PROVIDER;
    }

    @Override
    public BlockState getBlockState(Random random, BlockPos pos) {
        double d = Biome.FOLIAGE_NOISE.sample((double) pos.getX() / 200.0D, (double) pos.getZ() / 200.0D, false);
        if (d < -0.8D) {
            return Util.getRandom(mix1, random);
        } else {
            return random.nextInt(3) > 0 ? Util.getRandom(mix2, random) : AlienScienceBlock.MOON_PLANT.getDefaultState();
        }
    }
}
