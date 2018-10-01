package com.romanvonklein.manger.util;

import com.romanvonklein.manger.MangerMod;
import com.romanvonklein.manger.blocks.BlockManger;
import com.romanvonklein.manger.init.MangerBlocks;
import com.romanvonklein.manger.tileentities.MangerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler{

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        final Block[] blocks = {
            new BlockManger(Material.WOOD, "mangerBlock", "block_manger")
        };
        GameRegistry.registerTileEntity(MangerTileEntity.class, MangerMod.MODID + "_testcontainerblock");
        event.getRegistry().registerAll(blocks);
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final Item[] items = {
                //new ItemBasic("itemBasic", "basic_item")
        };
 
        final Item[] itemBlocks = {
            new ItemBlock(MangerBlocks.BLOCK_MANGER).setRegistryName(MangerBlocks.BLOCK_MANGER.getRegistryName())
        };
 
        //event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }
}