package union.cubeground.mod.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import union.cubeground.mod.common.utils.ICharItem;

public class SlotInv extends ESlot {
    public int type;
    public SlotInv(IInventory inv, int type, int id, int x, int y) {
        super(Access.IO, inv, id, x, y);
        this.type = type;
    }

    public boolean accept(ItemStack var1) {
        return var1.getItem() instanceof ICharItem && ((ICharItem)var1.getItem()).getSlot(var1) == this.type;
    }
}
