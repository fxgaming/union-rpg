package union.cubeground.mod.common.handler.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import union.cubeground.mod.common.player.ExtendedPlayer;

public class PUpdateStats extends PacketBase {
	public int id;
	public int[] integers;
	public PUpdateStats() {}
	public PUpdateStats(ExtendedPlayer extendedPlayer) {
		this.id = extendedPlayer.player.entityId;
		this.integers = new int[]{
			extendedPlayer.stats.hp, 
			extendedPlayer.stats.maxHP, 
			extendedPlayer.stats.mana, 
			extendedPlayer.stats.maxMana, 
			extendedPlayer.stats.armory,
			extendedPlayer.stats.xp,
			extendedPlayer.stats.level,
			extendedPlayer.stats.needXP,
			extendedPlayer.gold
		};
	}
	
	public void write(ByteArrayDataOutput var1) {
		var1.writeInt(this.id);
		for (int var : this.integers) var1.writeInt(var);
	}

	public void read(ByteArrayDataInput var1) throws ProtocolException {
		this.id = var1.readInt();
		this.integers = new int[9];
		for (int i = 0; i != 9; i++) {
			this.integers[i] = var1.readInt();
		}
	}

	public void execute(EntityPlayer var1, Side var2) throws ProtocolException {
		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().theWorld.getEntityByID(id);
		if (player != null && !player.isDead) {
			ExtendedPlayer ep =  ExtendedPlayer.get(player);
			ep.stats.hp = this.integers[0];
            ep.stats.maxHP = this.integers[1];
            ep.stats.mana = this.integers[2];
            ep.stats.maxMana = this.integers[3];
            ep.stats.armory = this.integers[4];
            ep.stats.xp = this.integers[5];
            ep.stats.level = this.integers[6];
            ep.stats.needXP = this.integers[7];
            ep.gold = this.integers[8];
        }
	}
}