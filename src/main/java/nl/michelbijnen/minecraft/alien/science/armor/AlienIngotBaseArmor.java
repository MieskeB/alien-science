package nl.michelbijnen.minecraft.alien.science.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nl.michelbijnen.minecraft.alien.science.RegisterItems;

public class AlienIngotBaseArmor extends ArmorItem {
    public AlienIngotBaseArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(RegisterItems.ALIEN_SCIENCE_ITEM_GROUP));
    }
}
