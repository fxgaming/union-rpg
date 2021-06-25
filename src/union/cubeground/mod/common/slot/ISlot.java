package union.cubeground.mod.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ISlot extends ESlot {
    public ISlot(Access access, IInventory inv, int id, int x, int y) {
        super(access, inv, id, x, y);
    }

    public boolean accept(ItemStack var1) {
        return true;
    }
}
