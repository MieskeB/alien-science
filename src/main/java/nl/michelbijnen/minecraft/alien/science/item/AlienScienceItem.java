package nl.michelbijnen.minecraft.alien.science.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nl.michelbijnen.minecraft.alien.science.Constants;
import nl.michelbijnen.minecraft.alien.science.item.armor.*;
import nl.michelbijnen.minecraft.alien.science.item.food.*;
import nl.michelbijnen.minecraft.alien.science.group.AlienScienceGroup;

public class AlienScienceItem {

    public static final Item ALIEN_FINDER = registerItem(new Item(new Item.Settings().group(AlienScienceGroup.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP).maxCount(1)), Constants.Item.ALIEN_FINDER);

    public static final Item ALIEN_INGOT = registerItem(new Item(new Item.Settings().group(AlienScienceGroup.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP)), Constants.Item.ALIEN_INGOT);

    // ARMOR
    public static final Item ALIEN_INGOT_HELMET = registerItem(new AlienIngotBaseArmor(new AlienIngotArmorMaterial(), EquipmentSlot.HEAD), Constants.Item.Armor.ALIEN_INGOT_HELMET);
    public static final Item ALIEN_INGOT_CHESTPLATE = registerItem(new AlienIngotBaseArmor(new AlienIngotArmorMaterial(), EquipmentSlot.CHEST), Constants.Item.Armor.ALIEN_INGOT_CHESTPLATE);
    public static final Item ALIEN_INGOT_LEGGINGS = registerItem(new AlienIngotBaseArmor(new AlienIngotArmorMaterial(), EquipmentSlot.LEGS), Constants.Item.Armor.ALIEN_INGOT_LEGGINGS);
    public static final Item ALIEN_INGOT_BOOTS = registerItem(new AlienIngotBaseArmor(new AlienIngotArmorMaterial(), EquipmentSlot.FEET), Constants.Item.Armor.ALIEN_INGOT_BOOTS);

    // FOOD
    public static final Item EDIBLE_ALIEN_FINDER = registerItem(new Item(new Item.Settings().group(AlienScienceGroup.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP).food(AlienFinderFoodComponent.EDIBLE_ALIEN_FINDER)), Constants.Item.Food.EDIBLE_ALIEN_FINDER);


    public static void register() {
    }

    private static <T extends Item> T registerItem(T item, String id) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MODID, id), item);
    }
}
