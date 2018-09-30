package com.romanvonklein.manger.blocks;

import com.romanvonklein.manger.MangerMod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockManger extends BlockContainer {
    public BlockManger(Material material, String unlocalizedName, String registryName){
        this(material, SoundType.WOOD, unlocalizedName, registryName);
    }

    public BlockManger(Material material, SoundType soundType, String unlocalizedName, String registryName) {
        super (material);
        setUnlocalizedName(MangerMod.MODID + "." +  unlocalizedName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        setSoundType(soundType);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
}