package nl.michelbijnen.minecraft.alien.science.item.food;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class AlienFinderFoodComponent {
    public static final FoodComponent EDIBLE_ALIEN_FINDER = (new FoodComponent.Builder()).hunger(2).saturationModifier(.3F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.8F).snack().alwaysEdible().build();
}
