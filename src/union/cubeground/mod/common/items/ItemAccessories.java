package union.cubeground.mod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.utils.IAccessories;

import java.util.List;

public class ItemAccessories extends Item implements IAccessories {
    public int count;
    public ItemAccessories(int id, int count) {
    	super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("itemAccessories");
        this.setCreativeTab(ItemHandler.tabAccessories);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setTextureName("cubeground:itemAccessories");
        this.count = count;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2, List par3) {for (int x = 0; x < this.count; ++x) par3.add(new ItemStack(this, 1, x));}
    public String getUnlocalizedName(ItemStack is) {return this.getUnlocalizedName() + "_" + is.getItemDamage();}

    public int getSlot(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0: return 2;
        }
        return -1;
    }
}
