package com.idtech.world;

import com.idtech.BaseMod;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;


public class CustomBiomes {

    public static Biome BASIC_TESTER = basicTesterBiome(new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder()).setRegistryName(BaseMod.MODID, "basic_tester");

    public static Biome basicTesterBiome(MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder) {

        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 500, 1, 5));
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BEE, 500, 1, 5));
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 500, 1, 5));

        BiomeDefaultFeatures.addBerryBushes(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addForestFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addFerns(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addMountainTrees(biomegenerationsettings$builder);

        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FOREST_FLOWER_TREES);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FLOWER_FOREST);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.DARK_FOREST_VEGETATION_RED);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Features.GLOWSTONE_EXTRA);
        biomegenerationsettings$builder.addStructureStart(StructureFeatures.MINESHAFT);

        return WorldUtils.baseBiome(Biome.BiomeCategory.FOREST,
                Blocks.GRASS_BLOCK.defaultBlockState(),
                Blocks.PINK_WOOL.defaultBlockState(),
                Blocks.PINK_WOOL.defaultBlockState(),
                mobspawnsettings$builder, biomegenerationsettings$builder,
                0.8f, 1.0f, 0.7f, 0.6f, 550204, 0.7F);
    }
}

