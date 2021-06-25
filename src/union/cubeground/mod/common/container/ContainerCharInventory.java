package union.cubeground.mod.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import union.cubeground.mod.common.player.ExtendedPlayer;
import union.cubeground.mod.common.slot.ESlot;
import union.cubeground.mod.common.slot.ISlot;
import union.cubeground.mod.common.slot.SlotAccs;
import union.cubeground.mod.common.slot.SlotInv;

public class ContainerCharInventory extends Container {
    public EntityPlayer thePlayer;
    public ExtendedPlayer exp;

    public ContainerCharInventory(EntityPlayer p) {
        ExtendedPlayer ex = ExtendedPlayer.get(p);
        this.exp = ex;
        for (int i = 1; i != 4; i++) for (int j = 0; j != 9; j++) this.addSlotToContainer(new ISlot(ESlot.Access.IO, p.inventory, (i * 9) + j, 6 + (j * 18), 101 + ((i - 1) * 18)));
        for (int i = 0; i != 9; i++) this.addSlotToContainer(new ISlot(ESlot.Access.IO, p.inventory, i, 6 + (i * 18), 159));
        for (int i = 0; i < 4; ++i) {
            final int k = i;
            this.addSlotToContainer(new Slot(p.inventory, p.inventory.getSizeInventory() - 1 - i, 8, 8 + i * 18) {
                public int getSlotStackLimit() {
                    return 1;
                }
                public boolean isItemValid(ItemStack p_75214_1_) {
                    if (p_75214_1_ == null) return false;
                    return p_75214_1_.getItem().isValidArmor(p_75214_1_, k, thePlayer);
                }
            });
        }
        for (int i = 0; i != 5; i++) this.addSlotToContainer(new SlotInv(ex.invChar, 0, i, 8 + (i * 18), 80));
        for (int i = 5; i != 9; i++) this.addSlotToContainer(new SlotInv(ex.invChar, i - 4, i, 80, 8 + ((i - 5) * 18)));
        for (int i = 0; i != 5; i++) this.addSlotToContainer(new SlotAccs(ex.invAccs, i, i, 98, 8 + (i * 18)));
    }

    public boolean canInteractWith(EntityPlayer p) {
        return true;
    }
    public ItemStack transferStackInSlot(EntityPlayer a, int b) {
        return null;
    }
}
