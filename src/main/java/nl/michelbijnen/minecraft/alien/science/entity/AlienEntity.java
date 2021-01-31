package nl.michelbijnen.minecraft.alien.science.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class AlienEntity extends HostileEntity {
    protected AlienEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
}
