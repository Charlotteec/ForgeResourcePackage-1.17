package com.idtech.world;

import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class WorldUtils {
    //
    public static int calculateSkyColor(float skyColor) {
        float f = skyColor / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    /*
    This is a function to help students create a biome
    You will need to create a mobspawnsettings builder and a biomegenerationsettings builder
    These will be used to add decorations and spawn creatures in your biome.
    The rest of the biome settings can just be arguments in this function.
    Look into the VanillaBiomes class for more information on how all this works and examples of how things work.
     */
    public static Biome baseBiome(Biome.BiomeCategory category, BlockState topLayer, BlockState secondLayer, BlockState thirdLayer, MobSpawnSettings.Builder mobspawnsettings$builder, BiomeGenerationSettings.Builder biomegenerationsettings$builder, float depth, float scale, float temp, float downfall, int waterColor, float skyColor) {
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
                        .skyColor(WorldUtils.calculateSkyColor(skyColor))
                        .build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }


    private static Collection<Biome> biomes;

    private static void loadAllBiomes() {
        biomes = ForgeRegistries.BIOMES.getValues();
    }
//    public static final ConfiguredFeature<?, ?> ORE_CANDIANITE = register("ore_candianite", Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockStates.CANDIANITE_ORE, 9)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63))).squared().count(20);

    public static void generateOres(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {    //for all biomes, including modded biomes

        }
    }

//    public static void addOres(BiomeGenerationSettings.Builder builder, Biome.BiomeCategory biomeCategory) {
//        int genOreStep = GenerationStep.Decoration.UNDERGROUND_ORES.ordinal();
//        if (biomeCategory == Biome.BiomeCategory.PLAINS) {
//            builder.addFeature(genOreStep, () -> );
//        }
//        builder.addFeature(genOreStep, () -> PA_ConfiguredFeatures.ORE_RAINBOW);
//
//    }
   /* private static void genOre(Biome biome, CountRangeConfig cfg, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
        OreFeature feature = new OreFeature(filler, defaultBlockstate, size);   //sets vein as a feature to add
        ConfiguredFeature config =      ;             //configures feature placement using CountRangeConfig values
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));    // adds configured feature to biome
    }
*/
}


//    public static void generateOres(FMLLoadCompleteEvent event) {
//        for (Biome biome : ForgeRegistries.BIOMES) {    //for all biomes, including modded biomes
//
//        }
//    }
//
//    /**
//     * Generates ore in all biomes
//     * @param block     The ore
//     * @param rangeCfg  CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param veinSize  Size of vein
//     */
//    private static void genOreInAllBiomes(BlockState block, CountRangeConfig rangeCfg, int veinSize) {
//        for (Biome biome : ForgeRegistries.BIOMES) {
//            // No end/nether generation
//            if (biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NETHER) {     //conditional only permits overworld spawning
//                genOre(biome, rangeCfg, OreFeatureConfig.FillerBlockType.NATURAL_STONE, block, veinSize);
//            }
//        }
//    }
//
//    /**
//     * Generates ore in select biomes
//     * @param block        The ore
//     * @param rangeConfig  CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param veinSize     Size of vein
//     * @param biomes       List of biomes to generate ore in. This is a parameter list, each biome is a seperate parameter (ex: genOreInBiomes(..., Biomes.HILLS, Biomes.GRASSLANDS))
//     */
//    private static void genOreInBiomes(BlockState block, CountRangeConfig rangeConfig, int veinSize, Biome... biomes) {
//        List<Biome> biomeList = Arrays.asList(biomes);
//        genOreInBiomes(block, rangeConfig, veinSize, biomeList);
//    }
//
//    /**
//     * Generates ore in select biomes
//     * @param block        The ore
//     * @param rangeConfig  CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param veinSize     Size of vein
//     * @param biomes       List of biomes to generate ore in.
//     */
//    private static void genOreInBiomes(BlockState block, CountRangeConfig rangeConfig, int veinSize, List<Biome> biomes) {
//        biomes.forEach(biome -> {
//            genOre(biome, rangeConfig, OreFeatureConfig.FillerBlockType.NATURAL_STONE, block, veinSize);
//        });
//    }
//
//    /**
//     * Generates ore except in select biomes
//     * @param block        The ore
//     * @param rangeConfig  CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param veinSize     Size of vein
//     * @param biomes       List of biomes to generate ore in. This is a parameter list, each biome is a seperate parameter (ex: genOreInBiomes(..., Biomes.HILLS, Biomes.GRASSLANDS))
//     */
//    private static void genOreInBiomesExcept(BlockState block, CountRangeConfig rangeConfig, int veinSize, Biome... biomes) {
//        List<Biome> biomeList = Arrays.asList(biomes);
//        genOreInBiomesExcept(block, rangeConfig, veinSize, biomeList);
//    }
//
//    /**
//     * Generates ore except in select biomes
//     * @param block        The ore
//     * @param rangeConfig  CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param veinSize     Size of vein
//     * @param biomes       List of biomes to generate ore in.
//     */
//    private static void genOreInBiomesExcept(BlockState block, CountRangeConfig rangeConfig, int veinSize, List<Biome> biomes) {
//        for (Biome biome : ForgeRegistries.BIOMES) {
//            // No end/nether generation
//            if (!biomes.contains(biome)) {     //conditional only permits overworld spawning
//                genOre(biome, rangeConfig, OreFeatureConfig.FillerBlockType.NATURAL_STONE, block, veinSize);
//            }
//        }
//    }
//
//    /** genOre Method creates ore chunks that are added to a biome
//     * @param biome                 The biome to add ore to
//     * @param cfg                   CountRangeConfig object that has: number of veins, offset from bottom of biome (y=0), offset from top of biome, and max height above bottomOffset
//     * @param filler                Block type for all blocks surrounding vein
//     * @param defaultBlockstate     Block that is being generated
//     * @param size                  Size of vein
//     */
//    private static void genOre(Biome biome, CountRangeConfig cfg, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
//        OreFeature feature = new OreFeature(filler, defaultBlockstate, size);   //sets vein as a feature to add
//        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(cfg);                  //configures feature placement using CountRangeConfig values
//        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));    // adds configured feature to biome
//    }
//}