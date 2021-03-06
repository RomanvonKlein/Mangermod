package com.romanvonklein.manger.tileentities;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class MangerTileEntity extends TileEntity implements ITickable {

    private int delay = 0;

    public static final int INV_SIZE = 5;

    // This item handler will hold our inventory slots
    private MangerItemStackHandler itemStackHandler = new MangerItemStackHandler(INV_SIZE);

    public MangerTileEntity() {}

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        delay++;
        if (delay == 20) {
            delay = 0;

            List<EntityAnimal> animalsInRange = world.getEntitiesWithinAABB(EntityAnimal.class,
                    new AxisAlignedBB(this.pos.getX() - 4, this.pos.getY() - 2, this.pos.getZ() - 4,
                            this.pos.getX() + 4, this.pos.getY() + 2, this.pos.getZ() + 4));
            HashMap<Class<? extends EntityAnimal>, Integer> have = new HashMap<>();

            for (int i = 0; i < animalsInRange.size(); i++) {
                EntityAnimal animal = animalsInRange.get(i);

                if (!animal.isInLove() && !animal.isChild()) {
                    int foodSlot = -1;
                    for (int foodSlotIndex = 0; foodSlotIndex < INV_SIZE; foodSlotIndex++) {

                        ItemStack stackToTest = this.itemStackHandler.getStackInSlot(foodSlotIndex);
                        if (animal.isBreedingItem(stackToTest) && stackToTest.getCount() >= 2) {
                            foodSlot = foodSlotIndex;
                            break;
                        }
                    }

                    if (foodSlot != -1) {
                        if (have.containsKey(animal.getClass())) {
                            if (have.get(animal.getClass()) != -1) {
                                if (this.itemStackHandler.getStackInSlot(foodSlot).getCount() >= 2) {
                                    // found second of a kind - set map value to -1, remove feed and breed animals
                                    //check if both of them can mate
                                    animal.setInLove(null);
                                    animalsInRange.get(have.get(animal.getClass())).setInLove(null);
                                    this.itemStackHandler.getStackInSlot(foodSlot).shrink(2);
                                    have.put(animal.getClass(), -1);
                                }
                            } else {
                                // last couple has been served, save a new single into map
                                have.put(animal.getClass(), i);
                            }
                        } else {
                            // first animal of that kind - add it to map
                            have.put(animal.getClass(), i);
                        }
                    }
                }
            }
        }
    }
}