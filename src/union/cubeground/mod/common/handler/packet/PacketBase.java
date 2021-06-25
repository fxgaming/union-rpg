package union.cubeground.mod.common.handler.packet;

import java.io.IOException;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public abstract class PacketBase {
	public static final String CHANNEL = "arpg";
	private static final BiMap idMap;
	public static int cout = 0;

	public static PacketBase constructPacket(int packetId) throws PacketBase.ProtocolException, IllegalAccessException, InstantiationException {
		Class clazz = (Class) idMap.get(Integer.valueOf(packetId));
		if (clazz == null) {
			throw new PacketBase.ProtocolException("Unknown Packet Id!");
		} else {
			return (PacketBase) clazz.newInstance();
		}
	}

	public final int getPacketId() {
		if (idMap.inverse().containsKey(this.getClass())) {
			return ((Integer)idMap.inverse().get(this.getClass())).intValue();
		} else {
			throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
		}
	}

	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(this.getPacketId());
		this.write(out);
		return PacketDispatcher.getPacket(this.CHANNEL, out.toByteArray());
	}

	public abstract void write(ByteArrayDataOutput out);
	public abstract void read(ByteArrayDataInput in) throws PacketBase.ProtocolException;
	public abstract void execute(EntityPlayer var1, Side var2) throws PacketBase.ProtocolException;
	
	static {
		Builder builder = ImmutableBiMap.builder();
		builder.put(nextId(), POpenGui.class);
		builder.put(nextId(), PSendAccessories.class);
		builder.put(nextId(), PSendChaotics.class);
		builder.put(nextId(), PUpdateStats.class);
		builder.put(nextId(), PUseChaotic.class);
		idMap = builder.build();
	}

	public static class ProtocolException extends Exception {
		public ProtocolException() {}
		public ProtocolException(String message, Throwable cause) {super(message, cause);}
		public ProtocolException(String message) {super(message);}
		public ProtocolException(Throwable cause) {super(cause);}
	}
	
	public static NBTTagCompound readNBTTagCompound(ByteArrayDataInput par0DataInputStream){
		short short1 = par0DataInputStream.readShort();
		if (short1 < 0) {
			return null;
		} else {
			byte[] abyte = new byte[short1];
			par0DataInputStream.readFully(abyte);
			try {
				return CompressedStreamTools.decompress((byte[]) abyte);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, ByteArrayDataOutput par1DataOutputStream) {
		if (par0NBTTagCompound == null) {
			par1DataOutputStream.writeShort(-1);
		} else {
			byte[] abyte;
			try {
				abyte = CompressedStreamTools.compress(par0NBTTagCompound);
				par1DataOutputStream.writeShort((short) abyte.length);
				par1DataOutputStream.write(abyte);
			} catch (IOException e) {
				par1DataOutputStream.writeShort(-1);
			}
		}
	}

	public static ItemStack readItemStack(ByteArrayDataInput par0DataInputStream) {
		ItemStack itemstack = null;
		short short1 = par0DataInputStream.readShort();
		if (short1 >= 0) {
			byte b0 = par0DataInputStream.readByte();
			short short2 = par0DataInputStream.readShort();
			itemstack = new ItemStack(short1, b0, short2);
			itemstack.stackTagCompound = readNBTTagCompound(par0DataInputStream);
		}
		return itemstack;
	}

	public static void writeItemStack(ItemStack par0ItemStack, ByteArrayDataOutput par1DataOutputStream) {
		if (par0ItemStack != null && par0ItemStack.getItem() != null) {
			par1DataOutputStream.writeShort(par0ItemStack.itemID);
			par1DataOutputStream.writeByte(par0ItemStack.stackSize);
			par1DataOutputStream.writeShort(par0ItemStack.getItemDamage());
			NBTTagCompound nbttagcompound = null;
			if (par0ItemStack.getItem().isDamageable() || par0ItemStack.getItem().getShareTag()) {
				nbttagcompound = par0ItemStack.stackTagCompound;
			}
			writeNBTTagCompound(nbttagcompound, par1DataOutputStream);
		} else {
			par1DataOutputStream.writeShort(-1);
		}
	}
	
	public static int nextId() {
		return cout++;
	}
}

