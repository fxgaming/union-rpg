package union.cubeground.mod.common.utils;

import api.player.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISpecModel {
    public abstract void render();
    public abstract void renderInventory(ItemStack arg0);
    public abstract String getTexture(ItemStack arg0);
    //public abstract void renderPlayer(EntityPlayer player, ModelPlayer modelPlayer, float[] sixFloats);
    //public abstract void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5);
}
