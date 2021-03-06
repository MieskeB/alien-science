package nl.michelbijnen.minecraft.alien.science.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nl.michelbijnen.minecraft.alien.science.item.AlienScienceItem;

public class AlienIngotArmorMaterial implements ArmorMaterial {
    // helmet, chestplate, leggings, boots
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {1, 2, 3, 1};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 25;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 25;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AlienScienceItem.ALIEN_INGOT);
    }

    @Override
    public String getName() {
        return "alien_ingot";
    }

    @Override
    public float getToughness() {
        return 2F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
