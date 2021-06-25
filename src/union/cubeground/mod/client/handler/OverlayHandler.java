package union.cubeground.mod.client.handler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Throwables;

import by.fxg.garbageapi.GL;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import union.cubeground.mod.client.ClientProxy;
import union.cubeground.mod.client.gui.GuiCharInventory;
import union.cubeground.mod.common.handler.packet.POpenGui;
import union.cubeground.mod.common.items.ItemBAxe;
import union.cubeground.mod.common.player.ExtendedPlayer;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.Utils;

public class OverlayHandler {
    public static int visHP = 0, nowHP = 0, maxHP = 0;
    public static int visMana = 0, nowMana = 0, maxMana = 0;
    public static int visXP = 0, nowXP = 0, maxXP = 0;
    public static int armory = 0, level = 0;

    @ForgeSubscribe
    public void onGui(GuiOpenEvent event) {
        if (ClientProxy.mc.theWorld != null && event.gui != null && !ClientProxy.mc.thePlayer.capabilities.isCreativeMode && event.gui instanceof GuiInventory) {
        	PacketDispatcher.sendPacketToServer(new POpenGui(0).makePacket());
        	event.setCanceled(true);
        }
//        if (event.gui != null && event.gui instanceof GuiIngameMenu) {
//            event.setCanceled(true);
//            Minecraft.getMinecraft().displayGuiScreen(new GuiPause());
//        }
        if (event.gui != null && ClientProxy.mc.thePlayer != null && event.gui instanceof GuiGameOver) {
            ClientProxy.mc.thePlayer.respawnPlayer();
            event.setCanceled(true);
        }
    }

    @ForgeSubscribe
    public void onPost(RenderGameOverlayEvent.Pre event) {
        ScaledResolution sr = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
        ExtendedPlayer ep = ExtendedPlayer.get(ClientProxy.mc.thePlayer);
        FontRenderer fr = ClientProxy.mc.fontRenderer;
        if (event.type == event.type.FOOD || event.type == event.type.ARMOR || event.type == event.type.EXPERIENCE || event.type == event.type.HEALTHMOUNT || event.type == event.type.HEALTH) {
            event.setCanceled(true);
        } else if (event.type == event.type.HOTBAR) {
            nowHP = ep.stats.hp;
            maxHP = ep.stats.maxHP;
            nowMana = ep.stats.mana;
            maxMana = ep.stats.maxMana;
            nowXP = ep.stats.xp;
            maxXP = ep.stats.needXP;
            level = ep.stats.level;
            GL11.glPushMatrix();
            if (nowHP != visHP) visHP += Math.round((double)(nowHP - visHP) / 2.0D);
            if (nowMana != visMana) visMana += Math.round((double)(nowMana - visMana) / 2.0D);
            if (nowXP != visXP) visXP += Math.round((double)(nowXP - visXP) / 2.0D);
            fr.drawStringWithShadow("Gold: " + ep.gold, 43, 1, Color.white.getRGB());
            fr.drawStringWithShadow("LVL: " + level, 40, 40, Color.white.getRGB());
            fr.drawStringWithShadow("" + level, 21, 23 - fr.getStringWidth("" + level) / 2, Color.white.getRGB());
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            ClientProxy.mc.renderEngine.bindTexture(new ResourceLocation("cubeground:gui/hud.png"));
            Utils.drawTexturedRect(10, 10, 0, 0, 230, 74, 0.0D);
            Utils.drawTexturedRect(88, 26, 0, 74, Utils.getPercentOf(146, visHP, maxHP), 8, 0.0D);
            Utils.drawTexturedRect(88, 46, 0, 82, Utils.getPercentOf(130, visMana, maxMana), 8, 0.0D);
            Utils.drawTexturedRect(88, 66, 0, 90, Utils.getPercentOf(116, visXP, maxXP), 6, 0.0D);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        } else if (event.type == event.type.CROSSHAIRS) {
            if (ClientProxy.mc.thePlayer != null && ClientProxy.mc.thePlayer.getHeldItem() != null && ClientProxy.mc.thePlayer.getHeldItem().getItem() instanceof ItemBAxe) {
                if ((double)Utils.getCompound(ClientProxy.mc.thePlayer.getHeldItem()).getInteger("cdtick") > 0) {
                    event.setCanceled(true);
                    String cooldown = "" + ((double) Utils.getCompound(ClientProxy.mc.thePlayer.getHeldItem()).getInteger("cdtick") / 20.0D);
                    fr.drawStringWithShadow(cooldown.substring(0, 3), sr.getScaledWidth() / 2 - fr.getStringWidth(cooldown.substring(0, 3)) / 2, sr.getScaledHeight() / 2, Color.white.getRGB());
                }
            }
//            GL11.glPushMatrix();
//            GL11.glEnable(GL11.GL_BLEND);
//            GL11.glBlendFunc(770, 771);
//            int[] list = {-60, -36, -12, 12, 36};
//            for (int i = 0; i != 5; i++) {
//                ClientProxy.mc.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/gui/widgets.png"));
//                GL11.glColor4f(1.0F, 0.25F, 0.25F, 1.0F);
//                Utils.drawTexturedRect(sr.getScaledWidth() / 2 + list[i], sr.getScaledHeight() - 50, 0, 22, 24, 24, 0.0D);
//                if (ep.invChar.inventory[i] != null) {
//                    RenderHelper.enableGUIStandardItemLighting();
//                    GL11.glTranslatef(0.0F, 0.0F, -150.0F);
//                    ClientProxy.renderItem.renderItemAndEffectIntoGUI(fr, ClientProxy.mc.getTextureManager(), ep.invChar.inventory[i], sr.getScaledWidth() / 2 + list[i] + 4, sr.getScaledHeight() - 46);
//                    RenderHelper.disableStandardItemLighting();
//                    if (Utils.getCompound(ep.invChar.inventory[i]).getCompoundTag("chaotic").getInteger("activity") == 0) {
//                        fr.drawString(KeyLoader.chaotics[i].getKeyButton(), sr.getScaledWidth() / 2 + list[i] + 12 - fr.getStringWidth(KeyLoader.chaotics[i].getKeyButton()) / 2, sr.getScaledHeight() - 60, Color.white.getRGB());
//                    }
//                } else {
//                    fr.drawString(KeyLoader.chaotics[i].getKeyButton(), sr.getScaledWidth() / 2 + list[i] + 12 - fr.getStringWidth(KeyLoader.chaotics[i].getKeyButton()) / 2, sr.getScaledHeight() - 42, Color.white.getRGB());
//                }
//            }
//            GL11.glDisable(GL11.GL_BLEND);
//            GL11.glPopMatrix();
        }
    }

    @ForgeSubscribe
    public void onToolTip(ItemTooltipEvent event) {
        if (event.itemStack != null && ClientProxy.mc.currentScreen instanceof GuiCharInventory && event.itemStack.getItem() instanceof ICustomInfo) {
            event.toolTip.clear();
        }
    }

    @ForgeSubscribe
    public void onNameTagRender(PlayerEvent.NameFormat event) {
        //event.
    }

    @ForgeSubscribe
    public void nameTagRender(RenderLivingEvent.Specials.Pre event) {
        EntityPlayer thePlayer = ClientProxy.mc.thePlayer;
        if (!ClientProxy.mc.gameSettings.hideGUI && !event.entity.getEntityName().equals(thePlayer.getEntityName())) {
            Tessellator t = Tessellator.instance;
            RenderManager renderManager = RenderManager.instance;
            int distance = 25;
            boolean doDisable = false;
            if (thePlayer != null) {
                int height;
                int y;
                if (event.entity instanceof EntityPlayer) {
                    event.setCanceled(true);
                    GL11.glAlphaFunc(516, 0.1F);
                    EntityPlayer givenPlayer = (EntityPlayer)event.entity;
                    try {
                        if ((renderManager.livingPlayer != null)) {
                            double d3 = event.entity.getDistanceSqToEntity(renderManager.livingPlayer);
                            float alpha = 1.0F - (float)(d3 / 250.0D);
                            double d0 = event.entity.lastTickPosX + (event.entity.posX - event.entity.lastTickPosX);
                            double d1 = event.entity.lastTickPosY + (event.entity.posY - event.entity.lastTickPosY);
                            double d2 = event.entity.lastTickPosZ + (event.entity.posZ - event.entity.lastTickPosZ);
                            d0 -= RenderManager.renderPosX;
                            d1 -= RenderManager.renderPosY;
                            d2 -= RenderManager.renderPosZ;
                            if (d3 <= (double)(distance * distance)) {
                                String name = event.entity.getEntityName();
                                List<Tuple> tags = new ArrayList<Tuple>();
                                tags.add(new Tuple(name, Color.white));
                                tags.add(new Tuple("Уровень " + ExtendedPlayer.get((EntityPlayer)event.entity).stats.level, Color.white));
                                FontRenderer fr = renderManager.getFontRenderer();
                                GL11.glPushMatrix();
                                GL11.glTranslated(d0, d1 + (double)event.entity.height + 0.30000001192092896D, d2);
                                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                                GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                                GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                                GL11.glScalef(-0.015F, -0.015F, 0.015F);
                                GL11.glDisable(2896);
                                GL11.glDepthMask(false);
                                GL11.glEnable(3042);
                                GL.pushBlendAlpha();
                                height = tags.size() * 9;
                                y = -height + 5;
                                int halfMaxTextWidth = 0;
                                for (Tuple pair : tags) {
                                    int halfWidth = fr.getStringWidth((String)pair.getFirst()) / 2;
                                    if (halfWidth > halfMaxTextWidth) {
                                        halfMaxTextWidth = halfWidth;
                                    }
                                }
                                GL11.glDisable(3553);
                                t.startDrawingQuads();
                                t.setColorRGBA_F(0.0F, 0.0F, 0.0F, alpha / 2.0F);
                                t.addVertex((double)(-halfMaxTextWidth - 1), (double)(-1 + y), 0.0D);
                                t.addVertex((double)(-halfMaxTextWidth - 1), (double)(height + y), 0.0D);
                                t.addVertex((double)(halfMaxTextWidth + 1), (double)(height + y), 0.0D);
                                t.addVertex((double)(halfMaxTextWidth + 1), (double)(-1 + y), 0.0D);
                                t.draw();
                                GL11.glEnable(3553);
                                GL11.glDepthMask(true);
                                for (Tuple pair : tags) {
                                    if (alpha > 0.0F) {
                                        if (doDisable) {
                                            GL11.glPushMatrix();
                                            GL11.glDisable(2929);
                                            Utils.useBrightLight();
                                            GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
                                            Color c = new Color((float)((Color)pair.getSecond()).getRed() / 255.0F, (float)((Color)pair.getSecond()).getGreen() / 255.0F, (float)((Color)pair.getSecond()).getBlue() / 255.0F, alpha);
                                            fr.drawString((String)pair.getFirst(), -fr.getStringWidth((String)pair.getFirst()) / 2, y, c.getRGB());
                                            GL11.glEnable(2929);
                                            GL11.glPopMatrix();
                                        } else {
                                            Color c = new Color((float)((Color)pair.getSecond()).getRed() / 255.0F, (float)((Color)pair.getSecond()).getGreen() / 255.0F, (float)((Color)pair.getSecond()).getBlue() / 255.0F, alpha);
                                            fr.drawString((String)pair.getFirst(), -fr.getStringWidth((String)pair.getFirst()) / 2, y, c.getRGB());
                                        }
                                    }
                                    y += 9;
                                }
                                GL11.glEnable(2896);
                                GL11.glDisable(3042);
                                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                                GL.popBlend();
                                GL11.glPopMatrix();
                            }
                        }
                    } catch (Throwable var25) {
                        throw Throwables.propagate(var25);
                    }
                } else {
                    event.setCanceled(false);
                }
            }
        }
    }

    @ForgeSubscribe
    public void onWorldRenderLast(RenderWorldLastEvent event) {
    }
}
