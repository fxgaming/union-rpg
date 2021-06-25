package union.cubeground.mod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryAccessories implements IInventory {
    public ItemStack[] inventory = new ItemStack[5];

    public int getSizeInventory() { return this.inventory.length; }
    
    public ItemStack getStackInSlot(int arg0) {
        return this.inventory[arg0];
    }

    public ItemStack decrStackSize(int slot, int quantity) {
        if (this.inventory[slot] != null) {
            ItemStack split;
            if (this.inventory[slot].stackSize <= quantity) {
                split = this.inventory[slot];
                this.inventory[slot] = null;
                return split;
            } else {
                split = this.inventory[slot].splitStack(quantity);
                if (this.inventory[slot].stackSize == 0) this.inventory[slot] = null;
                return split;
            }
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        this.inventory[slot] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) itemstack.stackSize = this.getInventoryStackLimit();
    }

    public void save(NBTTagCompound tags) {
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(slot);
                tagList.appendTag(slot);
            }
        }
        tags.setTag("Accesories.Inventory", tagList);
    }

    public void read(NBTTagCompound tags) {
        NBTTagList tagList = (NBTTagList)tags.getTag("Accesories.Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound)tagList.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
            if (itemstack != null) {
                this.inventory[j] = itemstack;
            }
        }
    }
    
    public ItemStack getStackInSlotOnClosing(int slot) {return null;}
    public int getInventoryStackLimit() {return 1;}
    public boolean isUseableByPlayer(EntityPlayer arg0) {return false;}
    public boolean isItemValidForSlot(int arg0, ItemStack arg1) {return false;}
	public String getInvName() {return "CharInventory";}
	public boolean isInvNameLocalized() {return false;}
	public void onInventoryChanged() {}
	public void openChest() {}
	public void closeChest() {}
}
