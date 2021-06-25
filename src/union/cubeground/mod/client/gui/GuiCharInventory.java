package union.cubeground.mod.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import union.cubeground.mod.client.ClientProxy;
import union.cubeground.mod.client.handler.OverlayHandler;
import union.cubeground.mod.common.container.ContainerCharInventory;
import union.cubeground.mod.common.items.ItemBAxe;
import union.cubeground.mod.common.items.ItemBSword;
import union.cubeground.mod.common.player.ExtendedPlayer;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.Utils;

public class GuiCharInventory extends GuiContainer {
    public final ResourceLocation res = new ResourceLocation("cubeground:gui/inventory.png");
    public ContainerCharInventory container;
    private float xSizeFloat;
    private float ySizeFloat;
    private boolean isRotated = false;

    public GuiCharInventory(EntityPlayer player) {
        super(new ContainerCharInventory(player));
        this.container = (ContainerCharInventory)this.inventorySlots;
        this.xSize = 172;
        this.ySize = 182;
    }

    public void updateScreen() {
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {
    }

    public void drawScreen(int x, int y, float any) {
        super.drawScreen(x, y, any);
        this.xSizeFloat = (float)x;
        this.ySizeFloat = (float)y;
        Tessellator t = Tessellator.instance;
        GL11.glTranslatef(0.0F, 0.0F, 900.0F);
        GL11.glPushMatrix();
        super.fontRenderer.drawString("MAX", this.guiLeft + 135, this.guiTop - 3, Color.white.getRGB(), true);
        String stats = "" + (OverlayHandler.maxHP > 999 ? (OverlayHandler.maxHP / 1000.0D) + "K" : OverlayHandler.maxHP);
        super.fontRenderer.drawString(stats, this.guiLeft + 130, this.guiTop + 8, Color.white.getRGB());
        stats = "" + (OverlayHandler.maxMana > 999 ? (OverlayHandler.maxMana / 1000.0D) + "K" : OverlayHandler.maxMana);
        super.fontRenderer.drawString(stats, this.guiLeft + 130, this.guiTop + 21, Color.white.getRGB());
        super.fontRenderer.drawString(OverlayHandler.armory + "", this.guiLeft + 130, this.guiTop + 34, Color.white.getRGB());
        stats = (ExtendedPlayer.get(ClientProxy.mc.thePlayer).gold > 999999 ? (ExtendedPlayer.get(ClientProxy.mc.thePlayer).gold / 1000000.0D) + "KK" : ExtendedPlayer.get(ClientProxy.mc.thePlayer).gold + "");
        super.fontRenderer.drawString(stats, this.guiLeft + 130, this.guiTop + 60, Color.white.getRGB());
        GL11.glPopMatrix();
        if (this.getSlotAtPosition(x, y) != null && this.getSlotAtPosition(x, y).getStack() != null && this.getSlotAtPosition(x, y).getStack().getItem() instanceof ICustomInfo) {
            int xx = x + 8, yy = y - 8, maxTextLen = 0, height = 4;
            List<String> list = new ArrayList<String>();
            ItemStack itemStack = this.getSlotAtPosition(x, y).getStack();
            NBTTagCompound compound = this.getSlotAtPosition(x, y).getStack().getTagCompound();
            this.getSlotAtPosition(x, y).getStack().getItem().addInformation(this.getSlotAtPosition(x, y).getStack(), ClientProxy.mc.thePlayer, list, false);
            for (String str : list) maxTextLen = super.fontRenderer.getStringWidth(str) > maxTextLen ? super.fontRenderer.getStringWidth(str) : maxTextLen;
            height += (list.size() * 10);
            if (itemStack.getItem() instanceof ItemBSword && compound.getCompoundTag("sword").getBoolean("isDamaging")) height += 10;
            if (itemStack.getItem() instanceof ItemBAxe && compound.getCompoundTag("axe").getBoolean("isDamaging")) height += 10;
            Gui.drawRect(xx, yy, xx + maxTextLen + 4, yy + height, Color.darkGray.getRGB());
            if (itemStack.getItem() instanceof ItemBSword && compound.getCompoundTag("sword").getBoolean("isDamaging")) {
                int minD = compound.getCompoundTag("sword").getInteger("damaging");
                int maxD = compound.getCompoundTag("sword").getInteger("damagingMax");
                Gui.drawRect(xx + 2,yy + list.size() * 10 + 4, xx + maxTextLen + 2, yy + height - 2, Color.gray.getRGB());
                Gui.drawRect(xx + 3,yy + list.size() * 10 + 5, xx + Utils.getPercentOf(maxTextLen + 1, maxD - minD, maxD), yy + height - 3, Color.GREEN.getRGB());
            } else if (itemStack.getItem() instanceof ItemBAxe && compound.getCompoundTag("axe").getBoolean("isDamaging")) {
                int minD = compound.getCompoundTag("axe").getInteger("damaging");
                int maxD = compound.getCompoundTag("axe").getInteger("damagingMax");
                Gui.drawRect(xx + 2,yy + list.size() * 10 + 4, xx + maxTextLen + 2, yy + height - 2, Color.gray.getRGB());
                Gui.drawRect(xx + 3,yy + list.size() * 10 + 5, xx + Utils.getPercentOf(maxTextLen + 1, maxD - minD, maxD), yy + height - 3, Color.GREEN.getRGB());
            }
            for (int i = 0; i != list.size(); i++) {
                super.fontRenderer.drawString(list.get(i), xx + 2, yy + (i * 10) + 2, Color.white.getRGB());
            }
        }
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int zero1, int zero2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(res);
        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(this.guiLeft + 127, this.guiTop + 45, 172, 0, Utils.getPercentOf(38, OverlayHandler.nowXP, OverlayHandler.maxXP), 11);
        drawplayer(this.guiLeft + 51, this.guiTop + 72, 30, (float)(this.guiLeft + 51) - this.xSizeFloat, (float)(this.guiTop + 72 - 50) - this.ySizeFloat, this.mc.thePlayer);
    }

    protected void actionPerformed(GuiButton button) {
    }

    protected void mouseClicked(int x, int y, int type) {
        super.mouseClicked(x, y, type);
        if (type == 0) {
            if (x >= this.guiLeft + 25 && x <= this.guiLeft + 78) {
                if (y >= this.guiTop + 7 && y <= this.guiTop + 78) {
                    this.isRotated = !this.isRotated;
                }
            }
        }
    }

    private void drawplayer(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
        GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        if (this.isRotated) GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        float f2 = p_147046_5_.renderYawOffset;
        float f3 = p_147046_5_.rotationYaw;
        float f4 = p_147046_5_.rotationPitch;
        float f5 = p_147046_5_.prevRotationYawHead;
        float f6 = p_147046_5_.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
        p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
        p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
        p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
        p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
        GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        p_147046_5_.renderYawOffset = f2;
        p_147046_5_.rotationYaw = f3;
        p_147046_5_.rotationPitch = f4;
        p_147046_5_.prevRotationYawHead = f5;
        p_147046_5_.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public Slot getSlotAtPosition(int par1, int par2) {
        for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k) {
            Slot slot = (Slot) this.inventorySlots.inventorySlots.get(k);
            if (this.isMouseOverSlot(slot, par1, par2))
                return slot;
        }
        return null;
    }

    private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
        return this.isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
    }

    protected boolean isPointInRegion(int par1, int par2, int par3, int par4, int par5, int par6) {
        int k1 = this.guiLeft;
        int l1 = this.guiTop;
        par5 -= k1;
        par6 -= l1;
        return par5 >= par1 - 1 && par5 < par1 + par3 + 1 && par6 >= par2 - 1 && par6 < par2 + par4 + 1;
    }
}
