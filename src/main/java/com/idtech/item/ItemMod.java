package com.idtech.item;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", CreativeModeTab.TAB_MISC);
    public static final Item GEL_ORE = ItemUtils.buildBasicItem("gelore", CreativeModeTab.TAB_MISC);

    //FOODS
    public static FoodProperties yummyFood = (new FoodProperties.Builder().nutrition(5).saturationMod(1.4f).effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 500, 1), 1.0f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1), 1.0F).alwaysEat().build());
    public static Item yummyFoodItem = ItemUtils.buildFoodItem("yummyfood", yummyFood);


//    public static Tier GEL_TIER = new ForgeTier(4, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);
        event.getRegistry().register(GEL_ORE);

        // ITEMS
        event.getRegistry().register(LightningHammerItem.INSTANCE);
        event.getRegistry().register(TeleportRodItem.INSTANCE);

        // TOOLS
        event.getRegistry().register(GelPickaxeItem.INSTANCE);
        event.getRegistry().register(GelSwordItem.INSTANCE);
        event.getRegistry().register(GelAxeItem.INSTANCE);

        // FOOD
        event.getRegistry().register(yummyFoodItem);

        // ARMOR
        event.getRegistry().register(CustomArmorItem.CUSTOM_HELM);
        event.getRegistry().register(CustomArmorItem.CUSTOM_CHEST);
        event.getRegistry().register(CustomArmorItem.CUSTOM_LEGS);
        event.getRegistry().register(CustomArmorItem.CUSTOM_BOOTS);

        //PROJECTILES
        event.getRegistry().register(BombArrowItem.INSTANCE);


    }
}
