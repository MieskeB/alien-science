package nl.michelbijnen.minecraft.alien.science.loot;

import net.minecraft.util.Identifier;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.mixin.LootTablesAccessor;

public class AlienScienceLootTable {
    public static final Identifier MOON_BASE_BASIC_CHEST = LootTablesAccessor.callRegisterLootTable(new Identifier(Constants.MODID, Constants.LootTables.MOON_BASE_BASIC_CHEST));

    public static void register() {

    }
}
