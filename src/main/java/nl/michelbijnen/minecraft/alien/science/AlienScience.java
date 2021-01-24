package nl.michelbijnen.minecraft.alien.science;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AlienScience implements ModInitializer {

	public static final Item ALIEN_FINDER = new Item(new Item.Settings().group(ItemGroup.REDSTONE));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("alienscience", "alien_finder"), ALIEN_FINDER);
	}
}
