package nl.michelbijnen.minecraft.alien.science.group;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.block.AlienScienceBlock;
import nl.michelbijnen.minecraft.alien.science.item.AlienScienceItem;

public class AlienScienceGroup {
    public static final ItemGroup ALIEN_SCIENCE_DEFAULT_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(Constants.MODID, Constants.ItemGroup.DEFAULT))
            .icon(() -> new ItemStack(AlienScienceItem.ALIEN_FINDER))
            .build();
    public static final ItemGroup ALIEN_SCIENCE_MOON_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(Constants.MODID, Constants.ItemGroup.MOON))
            .icon(() -> new ItemStack(AlienScienceBlock.MOON_STONE))
            .build();

    public static void register() {
    }
}
