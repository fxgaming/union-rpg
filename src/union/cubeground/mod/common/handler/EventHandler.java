package union.cubeground.mod.common.handler;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.handler.packet.PSendAccessories;
import union.cubeground.mod.common.handler.packet.PSendChaotics;
import union.cubeground.mod.common.handler.packet.PUpdateStats;
import union.cubeground.mod.common.items.ItemBSword;
import union.cubeground.mod.common.player.ExtendedPlayer;
import union.cubeground.mod.common.utils.Utils;

public class EventHandler {
    @ForgeSubscribe
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null) {
            ExtendedPlayer.register((EntityPlayer) event.entity);
        }
        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("rpg") == null) {
            event.entity.registerExtendedProperties("rpg", new ExtendedPlayer((EntityPlayer) event.entity));
        }
    }
    
    @ForgeSubscribe
    public void onHurt(LivingHurtEvent event) {
        if (AMod.isServer) {
            if (event.source != null && event.entityLiving != null && !event.entityLiving.worldObj.isRemote) {
                EntityLivingBase living = event.entityLiving;
                Entity source = event.source.getEntity();
                if (event.entityLiving instanceof EntityPlayer) {
                    EntityPlayer attacked = (EntityPlayer) living;
                    if (attacked.getHeldItem() != null && attacked.getHeldItem().getItem() instanceof ItemBSword && attacked.isUsingItem()) {
                        int rand = Utils.rangedRandom(1, 10);
                        event.ammount = event.ammount - (event.ammount / 100.0F * (float) rand);
                        if (event.ammount < 0) event.ammount = 0;
                        ItemBSword.causeDamage(attacked, attacked.getItemInUse(), Utils.rangedRandom(1, 5));
                    }
                    ExtendedPlayer extendedPlayer = ExtendedPlayer.get(attacked);
                    extendedPlayer.stats.hp = extendedPlayer.stats.hp - (int) event.ammount > 0 ? extendedPlayer.stats.hp - (int) event.ammount : 0;
                    if (extendedPlayer.stats.hp <= 0) {
                        attacked.setHealth(0.0F);
                    }
                    event.ammount = 0;
                }
            }
        }
    }

    @ForgeSubscribe
    public void onRespawn(LivingDeathEvent event) {
//        if (event.entityLiving instanceof EntityPlayer) {
//        	EntityPlayer player = (EntityPlayer)event.entityLiving;
//            NBTTagCompound compound = new NBTTagCompound();
//            ExtendedPlayer.get(player).resetData();
//        }
    }

    @ForgeSubscribe
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (AMod.isServer) {
            if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {
                EntityPlayerMP p = (EntityPlayerMP) event.entity;
                ExtendedPlayer extendedPlayer = ExtendedPlayer.get(p);
                extendedPlayer.stats.update(extendedPlayer);
                try {
                	if (p.ticksExisted % 20 == 0) {
                		PacketDispatcher.sendPacketToAllAround(p.posX, p.posY, p.posZ, 40.0D, p.dimension, new PUpdateStats(extendedPlayer).makePacket());
                	}
                    if (p.ticksExisted % 50 == 0) {
                    	if (extendedPlayer.invAccs != null) {
	                        for (int i = 0; i < 5; ++i) {
	                        	PacketDispatcher.sendPacketToPlayer(new PSendAccessories(extendedPlayer, i).makePacket(), (Player)p);
	                        }
                    	}
//                        if (extendedPlayer.invChar != null) {
//	                        for (int i = 0; i < 5; ++i) {
//	                            PacketDispatcher.sendPacketToPlayer(new PSendChaotics(extendedPlayer, i).makePacket(), (Player)p);
//	                        }
//                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
