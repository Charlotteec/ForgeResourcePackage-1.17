package com.idtech.world;

import com.google.common.collect.ImmutableList;
import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

import java.sql.Struct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WorldUtils {

    /**
     * Function copy-pasted from VanillaBiomes to help calculate sky color of biomes.
     * @param skyColor a float indicating the skycolor
     * @return a rgb sky color
     */
    public static int calculateSkyColor(float skyColor) {
        float f = skyColor / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    /**
     * Function to help create biomes more easily. Look into the VanillaBiomes class for more information on how all this works and examples of how things are done.
     * @param category the category of the biome to be created
     * @param topLayer the top layer of the biome (usually just one block tall)
     * @param secondLayer the second layer of the biome (usually a few blocks tall)
     * @param thirdLayer the third layer of the biome
     * @param mobspawnsettings$builder a mob spawn settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
     * @param biomegenerationsettings$builder a biom generation settings builder used to add spawners. You will instantiate this when you create the biome instance in WorldMod
     * @param depth the depth of the biome (how tall it is)
     * @param scale the scale of the biome (how big mountains can be)
     * @param temp the temperature (has to do with what can grow etc)
     * @param downfall what kind of weather the biome will have
     * @param waterColor the color of the water in the biome.
     * @return A built biome with all the parameters set and any additional mandatory parameters taken care of
     */

    public static Biome baseBiome(Biome.BiomeCategory category, BlockState topLayer, BlockState secondLayer, BlockState thirdLayer, MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder, float depth, float scale, float temp, float downfall, int waterColor) {
        //in 1.17 you have to create this surface configuration to set the blocks but its sort of nedlessly complicated so we will take care of that here.
        SurfaceBuilderBaseConfiguration surface = new SurfaceBuilderBaseConfiguration(topLayer, secondLayer, thirdLayer);
        biomegenerationsettings$builder.surfaceBuilder(SurfaceBuilder.DEFAULT.configured(surface));
        //you also have to use a builder, so this is a builder set up with the basics of all the essential stuff - if students really
        // wanna customize past this they can by copy pasting this whole thing and just manually setting everything in here (but its a pain)
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
                .downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(waterColor)
                        .waterFogColor(waterColor)
                        .fogColor(waterColor)
                        .skyColor(WorldUtils.calculateSkyColor(0.5f))
                        .build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    /**
     * Method to be used in biome loading event to generate an ores in all biomes
     * @param category category of biome from loading event
     * @param builder builder of biome from loading event
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     */
    public static void genOreInAllBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count) {

        if (category != Biome.BiomeCategory.THEEND && category != Biome.BiomeCategory.NETHER) {
            genOre(builder, blockstate, veinSize, bottom, top, count);
        }

    }

    /**
     * Method to be used in biome loading event to generate an ore in a list of biomes
     * @param category category of biome from loading event
     * @param builder builder of biome from loading event
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     * @param biomeCategories categories of biomes to be generated in
     */
    public static void genOreInBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, Biome.BiomeCategory...biomeCategories) {
        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
        genOreInBiomes(category, builder, blockstate, veinSize, bottom, top, count, biomeList);
    }

    /**
     * Private method used internally to help generate an ore in a list of biomes
     * @param category the category of the biome being added
     * @param builder the builder of the biome
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     * @param biomeCategories categories of biomes to be generated in
     */
    private static void genOreInBiomes(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, List<Biome.BiomeCategory> biomeCategories) {

        biomeCategories.forEach(cat -> {
            if(category == cat) {
                genOre(builder, blockstate, veinSize, bottom, top, count);
            }
        });
    }
    /**
     * Method to be used in a biome loading event to generate an ore in all biomes except a list of biomes
     * @param category the category of the biome being added
     * @param builder the builder of the biome
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     * @param biomeCategories categories of biomes to NOT be generated in
     */
    public static void genOreInBiomesExcept(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, Biome.BiomeCategory...biomeCategories) {
        List<Biome.BiomeCategory> biomeList = Arrays.asList(biomeCategories);
        genOreInBiomesExcept(category, builder, blockstate, veinSize, bottom, top, count, biomeList);
    }

    /**
     * Private method used internally to help generate an ore in all biomes except a list of biomes
     * @param category the category of the biome being added
     * @param builder the builder of the biome
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     * @param biomeCategories categories of biomes to NOT be generated in
     */
    private static void genOreInBiomesExcept(Biome.BiomeCategory category, BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count, List<Biome.BiomeCategory> biomeCategories) {
            if (!biomeCategories.contains(category)) {     //conditional only permits overworld spawning
                genOre(builder, blockstate, veinSize, bottom, top, count);
            }
//        }
    }

    /**
     * Method used to generate an ore in a biome.
     * @param builder the builder of the biome
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     */
    private static void genOre(BiomeGenerationSettings.Builder builder, BlockState blockstate, int veinSize, int bottom, int top, int count) {
        ConfiguredFeature<?,?> feature = makeFeature(blockstate, veinSize, bottom, top, count);
        GenerationStep.Decoration genStep = GenerationStep.Decoration.UNDERGROUND_ORES;
        builder.addFeature(genStep, feature);
    }

    /**
     * Method to create a custom ore feature to be used in ore generation
     * @param blockstate the block/ore that is being added
     * @param veinSize how many blocks should be generated at a time
     * @param bottom lowest block to start vein at
     * @param top highest block to start vein at
     * @param count amount of total blocks in area (??)
     * @return the ConfiguredFeature ore configuration so that it can be used in other methods.
     */
    private static ConfiguredFeature<?,?> makeFeature(BlockState blockstate, int veinSize, int bottom, int top, int count){
        ConfiguredFeature<?, ?> ORE_CANDIANITE = (Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, blockstate, veinSize))).rangeUniform(VerticalAnchor.absolute(bottom), VerticalAnchor.absolute(top)).squared().count(count);
        return ORE_CANDIANITE;
    }
}

