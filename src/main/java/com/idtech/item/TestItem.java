package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class TestItem extends Item {

    //THIS Is just a dummy item idk where i was going and i dont think its registered

    //static instance for registration
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new TestItem(properties).setRegistryName("testitem");

    //constructor
    public TestItem(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {

//       level.playSound();
        return super.use(level, player, p_41434_);
    }
}
