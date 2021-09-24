package com.idtech.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;

public class WorldMod {

    private static ResourceKey<Biome> BASIC_TESTER = ResourceKey.create(Registry.BIOME_REGISTRY, CustomBiomes.BASIC_TESTER.getRegistryName());

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){

        event.getRegistry().register(CustomBiomes.BASIC_TESTER);

    }

    public static void setupBiomes(){

        BiomeDictionary.addTypes(BASIC_TESTER, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BASIC_TESTER, 9000));

    }
}