package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import nl.michelbijnen.minecraft.alien.science.block.AlienScienceBlock;
import nl.michelbijnen.minecraft.alien.science.entity.AlienScienceEntityTypes;
import nl.michelbijnen.minecraft.alien.science.group.AlienScienceGroup;
import nl.michelbijnen.minecraft.alien.science.item.AlienScienceItem;
import nl.michelbijnen.minecraft.alien.science.loot.AlienScienceLootTable;
import nl.michelbijnen.minecraft.alien.science.server.command.AlienScienceCommands;
import nl.michelbijnen.minecraft.alien.science.sound.AlienScienceSound;
import nl.michelbijnen.minecraft.alien.science.structure.AlienScienceStructure;
import nl.michelbijnen.minecraft.alien.science.world.biome.AlienScienceBiomes;
import nl.michelbijnen.minecraft.alien.science.world.dimension.AlienScienceDimensions;
import nl.michelbijnen.minecraft.alien.science.world.gen.carver.AlienScienceCarver;
import nl.michelbijnen.minecraft.alien.science.world.gen.feature.AlienScienceFeature;

public class AlienScience implements ModInitializer {

    @Override
    public void onInitialize() {
        AlienScienceGroup.register();
        AlienScienceBlock.register();
        AlienScienceItem.register();
        AlienScienceSound.register();
        AlienScienceBiomes.register();
        AlienScienceDimensions.register();
        AlienScienceCommands.register();
        AlienScienceFeature.register();
        AlienScienceEntityTypes.register();
        AlienScienceStructure.register();
        AlienScienceCarver.register();
        AlienScienceLootTable.register();
    }
}
