package com.romanvonklein.manger.proxy;

import com.romanvonklein.manger.gui.MangerGui;
import com.romanvonklein.manger.tileentities.MangerContainer;
import com.romanvonklein.manger.tileentities.MangerTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof MangerTileEntity) {
            return new MangerContainer(player.inventory, (MangerTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof MangerTileEntity) {
            MangerTileEntity mangerTileEntity = (MangerTileEntity) te;
            return new MangerGui(new MangerContainer(player.inventory, mangerTileEntity));
        }
        return null;
    }
}