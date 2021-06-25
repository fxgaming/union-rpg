package union.cubeground.mod.common.handler.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.items.ItemChaotic;

public class PUseChaotic extends PacketBase {
	public int slot;
	public PUseChaotic() {}
	public PUseChaotic(int arg0) {
		this.slot = arg0;
	}
	
	public void write(ByteArrayDataOutput var1) {
		var1.writeInt(this.slot);
	}

	public void read(ByteArrayDataInput var1) throws ProtocolException {
		this.slot = var1.readInt();
	}

	public void execute(EntityPlayer var1, Side var2) throws ProtocolException {
		if (AMod.isServer) {
            if (var1 != null && !var1.isDead) {
                ItemChaotic.useChaotic(var1, this.slot);
            }
        } 
	}
}