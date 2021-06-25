package union.cubeground.mod.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import union.cubeground.mod.common.utils.EnumArmor;
import union.cubeground.mod.common.utils.Utils;

public class RenderBArmor implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type != ItemRenderType.FIRST_PERSON_MAP;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == type.ENTITY;
    }

    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        GL11.glPushMatrix();
        Tessellator t = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        if (type == type.INVENTORY) {
            GL11.glScalef(16.0F, 16.0F, 16.0F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.0125F, -1.0F, 0.0F);
        }
        EnumArmor enumArmor = EnumArmor.getById(Utils.getCompound(stack).getCompoundTag("armor").getInteger("id"), Utils.getCompound(stack).getCompoundTag("armor").getInteger("type"));
        ResourceLocation loc = new ResourceLocation("cubeground:textures/items/armor/" + enumArmor.skin + enumArmor.type + ".png");
        Minecraft.getMinecraft().renderEngine.bindTexture(loc);
        ItemRenderer.renderItemIn2D(t, 0.0F, 0.0F, 1.0F, 1.0F, 32, 32, 0.0625F);
        GL11.glPopMatrix();
    }
}
