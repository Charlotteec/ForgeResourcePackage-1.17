package com.idtech.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;

public class WorldMod {

    // For some reason you need to create this resource key to register biomes idk
    private static ResourceKey<Biome> BASIC_TESTER = ResourceKey.create(Registry.BIOME_REGISTRY, CustomBiomes.BASIC_TESTER.getRegistryName());

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
        //register the biome itself
        event.getRegistry().register(CustomBiomes.BASIC_TESTER);

    }

    public static void setupBiomes(){
//add the biome to the biome dictionary
        BiomeDictionary.addTypes(BASIC_TESTER, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        // Adding a biome to the manager and making sure it spawns regularly (weight is how likely it is to show up)
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BASIC_TESTER, 9000));

    }
}