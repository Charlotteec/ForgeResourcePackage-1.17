package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class GelAxeItem extends AxeItem {


    public static Tier tier = new ForgeTier(5, 1000, 25.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});
    public static Item INSTANCE = new GelAxeItem(tier,100, 100, new Properties().tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(BaseMod.MODID,"gelaxe");

    public GelAxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);

    }
}
