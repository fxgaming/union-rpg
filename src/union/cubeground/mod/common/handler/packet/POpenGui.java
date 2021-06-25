package union.cubeground.mod.common.handler.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import union.cubeground.mod.AMod;

public class POpenGui extends PacketBase {
	public int id;
	public POpenGui() {}
	public POpenGui(int id) {
		this.id = id;
	}
	
	public void write(ByteArrayDataOutput var1) {
		var1.writeInt(this.id);
	}

	public void read(ByteArrayDataInput var1) throws ProtocolException {
		this.id = var1.readInt();
	}

	public void execute(EntityPlayer var1, Side var2) throws ProtocolException {
		var1.openGui(AMod.INSTANCE, this.id, var1.worldObj, 0, 0, 0);
	}
}
