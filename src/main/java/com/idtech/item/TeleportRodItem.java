package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TeleportRodItem extends Item {

    //static instance for registration
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);
    public static Item INSTANCE = new TeleportRodItem(properties).setRegistryName("teleportrod");

    //constructor
    public TeleportRodItem(Properties properties) {
        super(properties);

    }

    //onrightclick
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        BlockPos blockPos = Utils.getBlockAtCursor(playerIn, 1000.0d, true);
//        if (playerIn instanceof ServerPlayer) {
//            ServerPlayer serverplayer = (ServerPlayer) playerIn;
           if (blockPos != null){//&& serverplayer.connection.getConnection().isConnected() && serverplayer.level == level && !serverplayer.isSleeping()) {
                playerIn.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                playerIn.fallDistance = 0.0F;
          }

//        }
        return InteractionResultHolder.pass(itemstack);
    }
}