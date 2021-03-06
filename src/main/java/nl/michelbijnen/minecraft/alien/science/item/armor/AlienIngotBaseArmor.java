package nl.michelbijnen.minecraft.alien.science.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nl.michelbijnen.minecraft.alien.science.group.AlienScienceGroup;

public class AlienIngotBaseArmor extends ArmorItem {
    public AlienIngotBaseArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(AlienScienceGroup.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP));
    }
}
