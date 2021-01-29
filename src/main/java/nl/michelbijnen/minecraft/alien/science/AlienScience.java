package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterItems;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterMoon;

public class AlienScience implements ModInitializer {
    public static String MODID = "alienscience";

    @Override
    public void onInitialize() {
        RegisterItems.register();
        RegisterMoon.register();
    }
}
