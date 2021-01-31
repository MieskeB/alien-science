package nl.michelbijnen.minecraft.alien.science.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;

public class AlienEntity extends HostileEntity {

    protected AlienEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.initCustomGoals();
    }

    protected void initCustomGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false) {
            private int ticks;

            public void start() {
                super.start();
                this.ticks = 0;
            }

            public void stop() {
                super.stop();
                AlienEntity.this.setAttacking(false);
            }

            public void tick() {
                super.tick();
                ++this.ticks;
                AlienEntity.this.setAttacking(this.ticks >= 5 && this.method_28348() < this.method_28349() / 2);
            }
        });
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[]{ZombifiedPiglinEntity.class}));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, MerchantEntity.class, false));
    }

    @Override
    protected int getCurrentExperience(PlayerEntity player) {
        return super.getCurrentExperience(player);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!super.damage(source, amount)) {
            return false;
        } else if (!(this.world instanceof ServerWorld)) {
            return false;
        } else {
            ServerWorld serverWorld = (ServerWorld)this.world;
            LivingEntity livingEntity = this.getTarget();
            if (livingEntity == null && source.getAttacker() instanceof LivingEntity) {
                livingEntity = (LivingEntity)source.getAttacker();
            }

            if (livingEntity != null && this.world.getDifficulty() == Difficulty.HARD && (double)this.random.nextFloat() < this.getAttributeValue(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS) && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                int i = MathHelper.floor(this.getX());
                int j = MathHelper.floor(this.getY());
                int k = MathHelper.floor(this.getZ());
                ZombieEntity zombieEntity = new ZombieEntity(this.world);

                for(int l = 0; l < 50; ++l) {
                    int m = i + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int n = j + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int o = k + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    BlockPos blockPos = new BlockPos(m, n, o);
                    EntityType<?> entityType = zombieEntity.getType();
                    SpawnRestriction.Location location = SpawnRestriction.getLocation(entityType);
                    if (SpawnHelper.canSpawn(location, this.world, blockPos, entityType) && SpawnRestriction.canSpawn(entityType, serverWorld, SpawnReason.REINFORCEMENT, blockPos, this.world.random)) {
                        zombieEntity.updatePosition((double)m, (double)n, (double)o);
                        if (!this.world.isPlayerInRange((double)m, (double)n, (double)o, 7.0D) && this.world.intersectsEntities(zombieEntity) && this.world.isSpaceEmpty(zombieEntity) && !this.world.containsFluid(zombieEntity.getBoundingBox())) {
                            zombieEntity.setTarget(livingEntity);
                            zombieEntity.initialize(serverWorld, this.world.getLocalDifficulty(zombieEntity.getBlockPos()), SpawnReason.REINFORCEMENT, (EntityData)null, (CompoundTag)null);
                            serverWorld.spawnEntityAndPassengers(zombieEntity);
                            this.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS).addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, EntityAttributeModifier.Operation.ADDITION));
                            zombieEntity.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS).addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, EntityAttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl) {
            float f = this.world.getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            if (this.getMainHandStack().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setOnFireFor(2 * (int)f);
            }
        }

        return bl;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        // TODO alien
        return SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        // TODO alien
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        // TODO alien
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getStepSound() {
        // TODO alien
        return SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        super.initEquipment(difficulty);
        if (this.random.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.random.nextInt(3);
            if (i == 0) {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
            }
        }

    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? 0.93F : 1.74F;
    }

    @Override
    public boolean canPickupItem(ItemStack stack) {
        return super.canPickupItem(stack);
    }

    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
    }
}
