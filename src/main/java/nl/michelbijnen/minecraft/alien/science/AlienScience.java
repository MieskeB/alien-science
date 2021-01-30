package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterItems;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterMoon;
import nl.michelbijnen.minecraft.alien.science.server.command.AlienScienceCommands;
import nl.michelbijnen.minecraft.alien.science.world.biome.AlienScienceBiomes;
import nl.michelbijnen.minecraft.alien.science.world.dimension.AlienScienceDimensions;

public class AlienScience implements ModInitializer {
    public static String MODID = "alienscience";

    @Override
    public void onInitialize() {
        RegisterItems.register();
        RegisterMoon.register();

        AlienScienceBiomes.register();
        AlienScienceDimensions.register();
        AlienScienceCommands.register();
    }
}
