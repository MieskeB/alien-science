package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;

public class AlienScience implements ModInitializer {

    @Override
    public void onInitialize() {
        RegisterItems.register();
    }
}
