package com.santi;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * The Three-Body Coordinate Broadcaster item.
 * <p>
 * Right-click to broadcast your current coordinates (X, Y, Z and dimension)
 * to the chat for every player on the server.
 */
public class CoordBroadcasterItem extends Item {

    public CoordBroadcasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            double x = serverPlayer.getX();
            double y = serverPlayer.getY();
            double z = serverPlayer.getZ();
            String dimension = level.dimension().location().toString();
            String playerName = serverPlayer.getName().getString();
            String itemName = stack.getHoverName().getString();

            String message = String.format(Locale.ROOT,
                    "[%s] %s 的坐标: X=%.1f  Y=%.1f  Z=%.1f  (维度: %s)",
                    itemName, playerName, x, y, z, dimension);

            // Broadcast to the whole server (appears in everyone's chat, including the sender).
            serverPlayer.server.getPlayerList().broadcastSystemMessage(Component.literal(message), false);

            // Extra feedback to the player using the item.
            serverPlayer.displayClientMessage(Component.literal("[三体坐标广播器] 坐标已广播！"), true);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
