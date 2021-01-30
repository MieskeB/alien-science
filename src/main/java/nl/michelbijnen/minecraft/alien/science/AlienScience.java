package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import nl.michelbijnen.minecraft.alien.science.block.AlienScienceBlock;
import nl.michelbijnen.minecraft.alien.science.group.AlienScienceGroup;
import nl.michelbijnen.minecraft.alien.science.item.AlienScienceItem;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterItems;
import nl.michelbijnen.minecraft.alien.science.server.command.AlienScienceCommands;
import nl.michelbijnen.minecraft.alien.science.world.biome.AlienScienceBiomes;
import nl.michelbijnen.minecraft.alien.science.world.dimension.AlienScienceDimensions;

public class AlienScience implements ModInitializer {

    @Override
    public void onInitialize() {
        AlienScienceGroup.register();
        AlienScienceBlock.register();
        AlienScienceItem.register();

        //TODO move this in future to structure file
        RegisterItems.register();

        AlienScienceBiomes.register();
        AlienScienceDimensions.register();
        AlienScienceCommands.register();
    }
}
