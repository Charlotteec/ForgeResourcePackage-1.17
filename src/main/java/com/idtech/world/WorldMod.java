package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {


    public static void registerBiomes(final RegistryEvent.Register<Biome> event){

    }

    public static void setupBiomes(){

    }



    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        Biome.BiomeCategory biomeCategory = event.getCategory();
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        MobSpawnInfoBuilder spawner = event.getSpawns();


    }
}