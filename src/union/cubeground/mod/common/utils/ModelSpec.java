package union.cubeground.mod.common.utils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

public abstract class ModelSpec extends ModelBase implements ISpecModel {
	public String getTexture(ItemStack arg0) {
		return "cubeground:accessories/" + arg0.getItemDamage() + ".png";
	}
}
