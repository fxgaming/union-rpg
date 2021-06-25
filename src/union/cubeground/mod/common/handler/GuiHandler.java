package union.cubeground.mod.common.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import union.cubeground.mod.client.gui.GuiCharInventory;
import union.cubeground.mod.common.container.ContainerCharInventory;

public class GuiHandler implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: return new ContainerCharInventory(player);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: return new GuiCharInventory(player);
        }
        return null;
    }
}
