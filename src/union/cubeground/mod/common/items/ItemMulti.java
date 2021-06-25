package union.cubeground.mod.common.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import union.cubeground.mod.common.handler.ItemHandler;

public class ItemMulti extends Item {
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	public String texture;
    public int count;

    public ItemMulti(int id, int count, String unlocalizedName) {
    	super(id);
    	this.count = count;
    	this.setHasSubtypes(true);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(ItemHandler.tabItems);
        this.setMaxStackSize(99);
        this.setMaxDamage(0);
        this.texture = unlocalizedName;
    }

    public String getUnlocalizedName(ItemStack is) {return super.getUnlocalizedName(is) + "_" + is.getItemDamage();}
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2, List par3) {for (int x = 0; x < this.count; ++x) par3.add(new ItemStack(this, 1, x));}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1) {this.icons = new Icon[this.count]; for (int i = 0; i < this.count; ++i) this.icons[i] = par1.registerIcon("cubeground:" + this.texture + "_" + i);}
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {return par1 > this.icons.length ? this.icons[0] : this.icons[par1];}
}
