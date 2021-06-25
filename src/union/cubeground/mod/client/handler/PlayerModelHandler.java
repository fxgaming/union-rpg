package union.cubeground.mod.client.handler;

import org.lwjgl.opengl.GL11;

import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import union.cubeground.mod.client.ClientProxy;
import union.cubeground.mod.common.items.ItemAccessories;
import union.cubeground.mod.common.player.ExtendedPlayer;
import union.cubeground.mod.common.utils.EnumDoSkill;
import union.cubeground.mod.common.utils.ISpecModel;
import union.cubeground.mod.common.utils.ModelSpec;

public class PlayerModelHandler extends ModelPlayerBase {
    public PlayerModelHandler(ModelPlayerAPI var1) {
        super(var1);
    }

    @Override
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        EntityPlayer player = (EntityPlayer)var1;
        ExtendedPlayer exp = ExtendedPlayer.get(player);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
//        for (ExtendedPlayer.Effect effect : exp.effectList) {
//            if (effect != null) {
//                if (effect.effect.doSkill == EnumDoSkill.MICRO_SIZE) {
//                    GL11.glScalef(0.65F, 0.65F, 0.65F);
//                    GL11.glTranslatef(0.0F, 0.85F, 0.0F);
//                }
//                if (effect.effect.doSkill == EnumDoSkill.INVISIBLE) {
//                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.11F);
//                }
//            }
//        }
        super.render(var1, var2, var3, var4, var5, var6, var7);
        for (ItemStack stack : exp.invAccs.inventory) {
            if (stack != null && stack.getItem() instanceof ItemAccessories && ClientProxy.accessoriesModels.get(stack.getItemDamage()) instanceof ModelSpec) {
            	ModelSpec model = ClientProxy.accessoriesModels.get(stack.getItemDamage());
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(((ModelSpec)model).getTexture(stack)));
                model.isRiding = this.modelPlayer.isSneak;
                model.render(var1, var2, var3, var4, var5, var6, var7);
            }
        }
        GL11.glPopMatrix();
    }
}
