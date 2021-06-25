package union.cubeground.mod.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import union.cubeground.mod.client.ClientProxy;
import union.cubeground.mod.common.utils.ISpecModel;
import union.cubeground.mod.common.utils.ModelSpec;

public class RenderAccessoriesItem implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(((ModelSpec)ClientProxy.accessoriesModels.get(stack.getItemDamage())).getTexture(stack)));
        ((ISpecModel)ClientProxy.accessoriesModels.get(stack.getItemDamage())).renderInventory(stack);
        GL11.glPopMatrix();
    }
}
