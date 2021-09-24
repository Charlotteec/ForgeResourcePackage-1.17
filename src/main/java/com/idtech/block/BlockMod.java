package com.idtech.block;


import com.idtech.BaseMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
//import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockMod {

    //Basic Block

    public static final Block CASTLE_WALL = BlockUtils.createBasicBlock("castlewall", Material.STONE);
    public static final Item CASTLE_WALL_ITEM = BlockUtils.createBlockItem(CASTLE_WALL, CreativeModeTab.TAB_MISC);

    public static final Block GEL_ORE_BLOCK =
            new OreBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F))
                    .setRegistryName(BaseMod.MODID, "geloreblock");
//    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)

    //public static final Block GEL_ORE = BlockUtils.createBasicBlock("geloreblock", Material.STONE);
    public static final Item GEL_ORE_ITEM = BlockUtils.createBlockItem(GEL_ORE_BLOCK, CreativeModeTab.TAB_MISC);

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(CASTLE_WALL_ITEM);
        event.getRegistry().register(GEL_ORE_ITEM);

        event.getRegistry().register(TNTCannonBlock.ITEM);
        event.getRegistry().register(RubberBlock.ITEM);
        event.getRegistry().register(CreeperSurpriseBlock.ITEM);
        event.getRegistry().register(CreepingMoldBlock.ITEM);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().register(CASTLE_WALL);
        event.getRegistry().register(GEL_ORE_BLOCK);

        event.getRegistry().register(TNTCannonBlock.INSTANCE);
        event.getRegistry().register(RubberBlock.INSTANCE);
        event.getRegistry().register(CreeperSurpriseBlock.INSTANCE);
        event.getRegistry().register(CreepingMoldBlock.INSTANCE);

    }
}





