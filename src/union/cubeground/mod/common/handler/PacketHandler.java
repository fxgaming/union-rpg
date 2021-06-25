package union.cubeground.mod.common.handler;

import java.nio.charset.Charset;
import java.util.logging.Logger;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import union.cubeground.mod.common.handler.packet.PacketBase;

public class PacketHandler implements IPacketHandler {
	private static final Charset charset = Charset.forName("UTF-8");
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet != null && packet.channel.equals("arpg")) {
			try {
				EntityPlayer ePlayer = (EntityPlayer)player;
				ByteArrayDataInput bytein = ByteStreams.newDataInput(packet.data);
				int pakId = bytein.readUnsignedByte();
				PacketBase paket = PacketBase.constructPacket(pakId);
				paket.read(bytein);
				paket.execute(ePlayer, ePlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
			} catch (PacketBase.ProtocolException var8) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
				}
			} catch (InstantiationException var9) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var9);
			} catch (IllegalAccessException var10) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var10);
			}
		}
	}
}
