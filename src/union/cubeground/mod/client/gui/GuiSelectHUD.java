package union.cubeground.mod.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiSelectHUD extends GuiScreen {
    protected void actionPerformed(GuiButton button) {

    }

    public void drawScreen(int x, int y, float none) {
        this.drawCenteredString(this.fontRenderer, I18n.getString("menu.game"), this.width / 2, 50, 16777215);
        super.drawScreen(x, y, none);
    }

    protected void mouseClicked(int x, int y, int type) {
        //25,7 53,71
        if (type == 0) {
        }
    }
}
