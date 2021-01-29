package nl.michelbijnen.minecraft.alien.science.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import nl.michelbijnen.minecraft.alien.science.registers.RegisterItems;

public class AlienFinder extends Item {
    public AlienFinder() {
        super(new Item.Settings().group(RegisterItems.ALIEN_SCIENCE_DEFAULT_ITEM_GROUP).maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        System.out.println(world.getDimension());
        return ActionResult.FAIL;
    }
}
