package com.romanvonklein.manger.tileentities;

import javax.annotation.Nonnull;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class MangerItemStackHandler extends ItemStackHandler{


    public MangerItemStackHandler(int size)
    {    
        super(size);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if(!isItemValidForSlot(stack, slot)){
            return stack;
        }

        //everything under this comment is as in the original ItemStackHandler
        
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;

    }
    
    /**
     * This methods checks whether the items given are of the correct type.
     * That said, this is what items go where:
     * 
     * 1    Wheat
     * 2    Seeds
     * 3    Carrots
     * 4    Meat(s)
     * 5    Fish
     */
    public boolean isItemValidForSlot(ItemStack itemInQuestion, int slot){
        Item item = itemInQuestion.getItem();
        switch (slot) {
            case 0:
                return(item == Items.WHEAT);
            case 2:
                return(item == Items.WHEAT_SEEDS);
            case 3:
                return(item == Items.CARROT);
            case 4:
                return(item == Items.MUTTON || item == Items.BEEF || item == Items.PORKCHOP || item == Items.RABBIT);
            case 5:
                return(item == Items.FISH);
            default:
                return false;
        }
    }
}