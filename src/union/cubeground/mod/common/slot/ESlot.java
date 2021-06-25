package union.cubeground.mod.common.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ESlot extends Slot {
    public Access accessType;

    public ESlot(Access access, IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
        this.accessType = access;
    }

    public abstract boolean accept(ItemStack var1);

    public boolean isItemValid(ItemStack stack) {
        return this.canInput() && this.accept(stack);
    }

    public boolean canTakeStack(EntityPlayer player) {
        return this.canOutput();
    }

    public boolean canInput() {
        return this.accessType == Access.I || this.accessType == Access.IO;
    }

    public static boolean canInput(Access access) {
        return access == Access.I || access == Access.IO;
    }

    public boolean canOutput() {
        return this.accessType == Access.O || this.accessType == Access.IO;
    }

    public static enum Access {
        NONE, I, IO, O;
    }
}

