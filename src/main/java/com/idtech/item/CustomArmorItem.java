package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;

public class CustomArmorItem extends ArmorItem {

    private static ArmorMaterial customMaterial = ItemUtils.buildArmorMaterial("gelore", 22, new int[]{10,10,10,10} ,5,
            SoundEvents.ARMOR_EQUIP_IRON, 4.0f, 0.3f,"examplemod:gelore");

    public static final Item CUSTOM_HELM = new CustomArmorItem(customMaterial, EquipmentSlot.HEAD, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)).setRegistryName(BaseMod.MODID, "custom_helm");
    public static final Item CUSTOM_CHEST = new CustomArmorItem(customMaterial, EquipmentSlot.CHEST, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)).setRegistryName(BaseMod.MODID, "custom_chest");
    public static final Item CUSTOM_LEGS = new CustomArmorItem(customMaterial, EquipmentSlot.LEGS, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)).setRegistryName(BaseMod.MODID, "custom_legs");
    public static final Item CUSTOM_BOOTS = new CustomArmorItem(customMaterial, EquipmentSlot.FEET, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)).setRegistryName(BaseMod.MODID, "custom_boots");


    public CustomArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.LEGS){
            return "examplemod:textures/models/armor/custom_armor_layer_2.png";
        }else{
            return "examplemod:textures/models/armor/custom_armor_layer_1.png";
        }
    }
}
