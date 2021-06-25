package union.cubeground.mod.common.utils;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import union.cubeground.mod.client.ClientProxy;

public class Utils {
    public static Random rand = new Random();
    
    public static NBTTagCompound getCompound(ItemStack i) {
        if (i.hasTagCompound()) return i.getTagCompound();
        else {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            i.setTagCompound(nbtTagCompound);
            return i.getTagCompound();
        }
    }

    public static int getPercentOf(int par1Percent, int par2Low, int par3High) {
        return (int)((double)par2Low / ((double)par3High / par1Percent));
    }

    public static double getPercentOf(double par1Percent, double par2Low, double par3High) {
        return (par2Low / (par3High / par1Percent));
    }

    public static boolean getRandomOf(double max, double yours) {
        double randomValue = rand.nextDouble() * max;
        if ((-randomValue + yours) > 0.0D) {
            return true;
        }
        return false;
    }

    public static boolean getRandom2(double max, double yours) {
        double randomValue = rand.nextDouble() * max;
        return !(randomValue > yours);
    }

    public static String getLoreID(EntityPlayer p, ItemStack item) {
        return (p.capabilities.isCreativeMode ? "<" + item.getItem().itemID + ":" + item.getItemDamage() + ">" : "");
    }

    public static String getLoreID(ItemStack item) {
        return "<" + item.getItem().itemID + ":" + item.getItemDamage() + ">";
    }

    public static String getLoreID(Item item) {
        return "<" + item.itemID + ">";
    }

    public static String getLoreID(Block block) {
        return "<" + block.blockID + ">";
    }
    
    public static int rangedRandom(int from, int to) {
        return rand.nextInt((to - from) + 1) + from;
    }

    @SideOnly(Side.CLIENT)
    public static void drawTexturedRect(int par1, int par2, int par3, int par4, int par5, int par6, double zLevel) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }

    @SideOnly(Side.CLIENT)
    public static void drawTexturedRectFromIcon(int par1, int par2, Icon par3Icon, int par4, int par5, double zLevel) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par5), zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + par5), zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + 0), zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMinV());
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMinV());
        tessellator.draw();
    }

    //TODO: FIXME
    @SideOnly(Side.CLIENT)
    public static void renderEnchantEffect(Tessellator tessellator, ItemStack item, int iconwidth, int iconheight, float thick00625F, ResourceLocation loc) {
        if (item != null && item.hasEffect(0)) {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            ResourceLocation ENCHANTMENT_GLINT = new ResourceLocation("minecraft", "textures/misc/enchanted_item_glint.png");
            ClientProxy.mc.renderEngine.bindTexture(ENCHANTMENT_GLINT);
            GL11.glEnable(GL11.GL_BLEND);
            //OpenGlHelper.glBlendFunc(768, 1, 1, 0);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, iconwidth, iconheight, thick00625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ClientProxy.mc.renderEngine.bindTexture(loc);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, iconwidth, iconheight, thick00625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void useBrightLight() {
        float fl = 240.0F;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, fl, fl);
    }

    @SideOnly(Side.CLIENT)
    public static void useWorldLight() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void useWorldLightExtra(float givenJ, float givenK) {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, givenJ, givenK);
    }

    @SideOnly(Side.CLIENT)
    public static void usePublicLight(World givenWorld, double givenX, double givenY, double givenZ) {
        int i4 = givenWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(givenX), MathHelper.floor_double(givenY), MathHelper.floor_double(givenZ), 0);
        int j4 = i4 % 65536;
        int k4 = i4 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j4 / 1.0F, (float)k4 / 1.0F);
    }
}
