package com.romanvonklein.manger.blocks;

import com.romanvonklein.manger.MangerMod;
import com.romanvonklein.manger.gui.MangerGui;
import com.romanvonklein.manger.tileentities.MangerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockManger extends Block implements ITileEntityProvider {
    public BlockManger(Material material, String unlocalizedName, String registryName) {
        this(material, SoundType.WOOD, unlocalizedName, registryName);
    }

    public BlockManger(Material material, SoundType soundType, String unlocalizedName, String registryName) {
        super(material);
        setUnlocalizedName(MangerMod.MODID + "." + unlocalizedName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        setSoundType(soundType);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MangerTileEntity();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
                new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
            EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof MangerTileEntity)) {
            return false;
        }
        player.openGui(MangerMod.instance, MangerGui.GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}