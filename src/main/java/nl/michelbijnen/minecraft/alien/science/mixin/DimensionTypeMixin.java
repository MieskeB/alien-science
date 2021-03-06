package nl.michelbijnen.minecraft.alien.science.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.world.biome.source.MoonBiomeSource;
import nl.michelbijnen.minecraft.alien.science.world.gen.chunk.MoonChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
    @Inject(method = "createDefaultDimensionOptions", at = @At(value = "RETURN", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addGCDimOptions(Registry<DimensionType> registry, Registry<Biome> registry2, Registry<ChunkGeneratorSettings> registry3, long l, CallbackInfoReturnable<SimpleRegistry<DimensionOptions>> cir, SimpleRegistry<DimensionOptions> simpleRegistry) {
        simpleRegistry.add(RegistryKey.of(Registry.DIMENSION_OPTIONS, new Identifier(Constants.MODID, Constants.Dimension.Moon.NAME)), new DimensionOptions(() -> registry.get(new Identifier(Constants.MODID, Constants.Dimension.Moon.NAME)), new MoonChunkGenerator(new MoonBiomeSource(l, 4, registry2), l)), Lifecycle.stable());
    }
}
