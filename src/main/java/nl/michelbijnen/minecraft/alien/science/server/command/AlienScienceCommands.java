package nl.michelbijnen.minecraft.alien.science.server.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.command.argument.DimensionArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Collection;
import java.util.function.Consumer;

public class AlienScienceCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) -> {
            commandDispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("dimensiontp")
                    .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                    .then(CommandManager.argument("dimension", DimensionArgumentType.dimension())
                            .executes(AlienScienceCommands::teleport)
                            .then(CommandManager.argument("entities", EntityArgumentType.entities())
                                    .executes(((AlienScienceCommands::teleportMultiple))))));
            commandDispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("gcr_listbodies")
                    .executes(context -> {
                        context.getSource().sendFeedback(new LiteralText("Appeltjes zijn lekker"), true);
                        return 1;
                    }));
        });
    }

    private static int teleport(CommandContext<ServerCommandSource> context) {
        context.getSource().getMinecraftServer().execute(() -> {
            try {
                ServerPlayerEntity player = context.getSource().getPlayer();
                ServerWorld serverWorld = DimensionArgumentType.getDimensionArgument(context, "dimension");
                if (serverWorld == null) {
                    context.getSource().sendError(new TranslatableText("commands.alienscience.dimensiontp.failure.dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    return;
                }
                player.teleport(serverWorld,
                        player.getX(),
                        getTopBlockY(serverWorld, player),
                        player.getZ(),
                        player.yaw,
                        player.pitch);
                context.getSource().sendFeedback(new TranslatableText("commands.alienscience.dimensiontp.success.single", serverWorld.getRegistryKey().getValue()), true);

            } catch (CommandSyntaxException ignore) {
                context.getSource().sendError(new TranslatableText("commands.alienscience.dimensiontp.failure.entity").setStyle(Style.EMPTY.withColor(Formatting.RED)));
            }
        });
        return -1;
    }

    private static int teleportMultiple(CommandContext<ServerCommandSource> context) {
        context.getSource().getMinecraftServer().execute(() -> {

            try {
                ServerWorld serverWorld = DimensionArgumentType.getDimensionArgument(context, "dimension");
                if (serverWorld == null) {
                    context.getSource().sendError(new TranslatableText("commands.alienscience.dimensiontp.failure.dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    return;
                }

                Collection<? extends Entity> entities = EntityArgumentType.getEntities(context, "entities");
                entities.forEach((Consumer<Entity>) entity -> {
                    entity.moveToWorld(serverWorld);
                    context.getSource().sendFeedback(new TranslatableText("commands.alienscience.dimensiontp.success.multiple", entities.size(), serverWorld.getRegistryKey().getValue()), true);
                });
            } catch (CommandSyntaxException ignore) {
                context.getSource().sendError(new TranslatableText("commands.alienscience.dimensiontp.failure.entity").setStyle(Style.EMPTY.withColor(Formatting.RED)));
            }
        });
        return -1;
    }

    // Feel free to move this method to a utils class. Perhaps this could be cleaned up to return BlockPos.class or Vec3i.class
    /**
     * Gets the top y position in the world. Essentially a /top Y coord method
     * @param world Takes in the world
     * @param player Takes in the player
     * @return The top Y value of the world.
     */
    private static double getTopBlockY(ServerWorld world, ServerPlayerEntity player) {
        int playerX = (int) player.getX();
        int playerZ = (int) player.getZ();

        for (int i = world.getHeight(); i > 0; i-- ) {
            BlockPos pos = new BlockPos(new Vec3d(playerX, i, playerZ));
            Block currentBlock = world.getBlockState(pos).getBlock();
            if (!currentBlock.getDefaultState().isAir()) {
                return pos.getY() + 1;
            }
        }
        return player.getY(); // This SHOULD NOT happen! However if it does, player gets teleported to where they were before.
    }
}
