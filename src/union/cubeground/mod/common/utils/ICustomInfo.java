package union.cubeground.mod.common.utils;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICustomInfo {
	public void getInfo(ItemStack i, EntityPlayer p, List l);
}
