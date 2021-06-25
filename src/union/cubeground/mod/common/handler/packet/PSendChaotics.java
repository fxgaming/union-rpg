package union.cubeground.mod.common.handler.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import union.cubeground.mod.common.player.ExtendedPlayer;

public class PSendChaotics extends PacketBase {
	public int id;
	public int slot;
	public ItemStack stack;
	public PSendChaotics() {}
	public PSendChaotics(ExtendedPlayer extendedPlayer, int slot) {
		this.id = extendedPlayer.player.entityId;
		this.slot = slot;
		this.stack = extendedPlayer.invAccs.inventory[slot];
	}
	
	public void write(ByteArrayDataOutput var1) {
		var1.writeInt(this.id);
		var1.writeInt(this.slot);
		this.writeItemStack(this.stack, var1);
	}

	public void read(ByteArrayDataInput var1) throws ProtocolException {
		this.id = var1.readInt();
		this.slot = var1.readInt();
		this.stack = this.readItemStack(var1);
	}

	public void execute(EntityPlayer var1, Side var2) throws ProtocolException {
		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().theWorld.getEntityByID(this.id);
		ExtendedPlayer ep = ExtendedPlayer.get(player);
		ep.invChar.inventory[this.slot] = this.stack;
		
	}
}