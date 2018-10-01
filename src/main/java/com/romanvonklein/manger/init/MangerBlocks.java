package com.romanvonklein.manger.init;

import com.romanvonklein.manger.MangerMod;
import com.romanvonklein.manger.blocks.BlockManger;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ObjectHolder(MangerMod.MODID)
public class MangerBlocks{
    public static final Block BLOCK_MANGER = null;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ((BlockManger)BLOCK_MANGER).initModel();
    }
}