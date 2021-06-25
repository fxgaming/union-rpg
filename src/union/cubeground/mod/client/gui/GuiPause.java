package union.cubeground.mod.client.gui;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiPause extends GuiScreen {
    private int field_146445_a;
    private int field_146444_f;

    public void initGui() {
        this.field_146445_a = 0;
        this.buttonList.clear();
        byte b0 = -16;
        boolean flag = true;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 86 + b0, 98, 20, I18n.getString("menu.options")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 108 + b0, I18n.getString("menu.disconnect")));
        //this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 86 + b0, 98, 20, "HUD"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 42 + b0, I18n.getString("menu.returnToGame")));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 64 + b0, 98, 20, I18n.getString("gui.achievements")));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 64 + b0, 98, 20, I18n.getString("gui.stats")));
    }

    protected void actionPerformed(GuiButton p_146284_1_) {
        switch (p_146284_1_.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                this.mc.displayGuiScreen(new GuiMainMenu());
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiSelectHUD());
                break;
            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;
            case 5:
                if (this.mc.thePlayer != null) this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
                break;
            case 6:
                if (this.mc.thePlayer != null) this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
                break;
        }
    }

    public void updateScreen() {
        super.updateScreen();
        ++this.field_146444_f;
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.getString("menu.game"), this.width / 2, 50, 16777215);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}
