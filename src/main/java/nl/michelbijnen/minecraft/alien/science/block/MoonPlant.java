package nl.michelbijnen.minecraft.alien.science.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

import java.util.Random;

public class MoonPlant extends Block {
    public static final BooleanProperty DECAYED = BooleanProperty.of("decayed");

    public MoonPlant() {
        super(Settings.of(Material.PLANT).breakInstantly().nonOpaque().noCollision().strength(0F, 0F).sounds(BlockSoundGroup.GRASS));
        setDefaultState(getStateManager().getDefaultState().with(DECAYED, new Random().nextBoolean()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(DECAYED);
    }
}
