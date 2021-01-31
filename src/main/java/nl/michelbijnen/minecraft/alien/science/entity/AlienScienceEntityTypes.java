package nl.michelbijnen.minecraft.alien.science.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nl.michelbijnen.minecraft.alien.science.Constants;

public class AlienScienceEntityTypes {
    public static final EntityType<AlienEntity> ALIEN = Registry.register(Registry.ENTITY_TYPE, new Identifier(Constants.MODID, Constants.Entities.Alien.NAME), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AlienEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build());

    public static void register() {
        FabricDefaultAttributeRegistry.register(ALIEN, AlienEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D).add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D));
    }
}
