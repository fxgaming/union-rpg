package union.cubeground.mod.client;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import union.cubeground.mod.client.handler.KeyLoader;
import union.cubeground.mod.common.handler.packet.POpenGui;

public class ClientTickHandler implements ITickHandler {
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (KeyLoader.cherryPedia.isPressed() && ClientProxy.mc.theWorld != null && ClientProxy.mc.currentScreen == null) {
			//mc.displayGuiScreen(new CherryMainPage());
		}
//		for (int i = 0; i != 5; i++) {
//            if (KeyLoader.chaotics[i].isPressed() && ClientProxy.mc.thePlayer != null) {
//                //PacketHandler.sendToServer(new PUseChaotic(ClientProxy.mc.thePlayer, i));
//            }
//        }
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	public String getLabel() {
		return "CTH";
	}
}
