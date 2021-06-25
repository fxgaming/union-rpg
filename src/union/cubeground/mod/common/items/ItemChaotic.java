package union.cubeground.mod.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.utils.EnumActivity;
import union.cubeground.mod.common.utils.EnumChaotic;
import union.cubeground.mod.common.utils.EnumDoSkill;
import union.cubeground.mod.common.utils.EnumPassive;
import union.cubeground.mod.common.utils.ICharItem;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.IStatsExtension;
import union.cubeground.mod.common.utils.Utils;

public class ItemChaotic extends Item implements ICharItem, IStatsExtension, ICustomInfo {
    public ItemChaotic(int id) {
    	super(id);
        this.setUnlocalizedName("itemChaotic");
        this.setCreativeTab(ItemHandler.tabItems);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
        if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("chaotic");
            if (l.size() != 0) l.set(0, compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add(compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add((compound.getInteger("activity") == 0 ? "Активный" : compound.getInteger("activity") == 1 ? "Пассивный" : "Полу-активный") + " хаотик");
            if (compound.getInteger("activity") == 0) l.add(EnumDoSkill.getString(EnumDoSkill.getById(compound.getInteger("active"))));
            else if (compound.getInteger("activity") == 1) l.add(EnumPassive.getString(EnumPassive.getById(compound.getInteger("passive"))) + compound.getIntArray("stats")[0] + "%");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный хаотик" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный хаотик" + " " + Utils.getLoreID(p, i));
            l.add("§5Хаотик не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
    }
    
	public void getInfo(ItemStack i, EntityPlayer p, List l) {
		if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("chaotic");
            if (l.size() != 0) l.set(0, compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add(compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add((compound.getInteger("activity") == 0 ? "Активный" : compound.getInteger("activity") == 1 ? "Пассивный" : "Полу-активный") + " хаотик");
            if (compound.getInteger("activity") == 0) l.add(EnumDoSkill.getString(EnumDoSkill.getById(compound.getInteger("active"))));
            else if (compound.getInteger("activity") == 1) l.add(EnumPassive.getString(EnumPassive.getById(compound.getInteger("passive"))) + compound.getIntArray("stats")[0] + "%");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный хаотик" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный хаотик" + " " + Utils.getLoreID(p, i));
            l.add("§5Хаотик не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
	}

    public static void useChaotic(EntityPlayer p, int cnum) {
//        if (AMod.isServer) {
//            ExtendedPlayer ep = ExtendedPlayer.get(p);
//            if (ep.invChar.inventory[cnum] != null) {
//                ItemStack chaotic = ep.invChar.inventory[cnum];
//                if (Utils.getCompound(chaotic).getBoolean("hasStats") && Utils.getCompound(chaotic).getInteger("cdtick") <= 0) {
//                    NBTTagCompound nbt = Utils.getCompound(chaotic).getCompoundTag("chaotic");
//                    EnumChaotic enumChaotic = EnumChaotic.getById(nbt.getInteger("id"));
//                    if (enumChaotic.type == EnumActivity.ACTIVE) {
//                        if (ep.setEffect(cnum, ExtendedPlayer.Effect.create(enumChaotic, (double) (enumChaotic.passiveStats[1] * 50 / 1000)))) {
//                            Utils.getCompound(ep.invChar.inventory[cnum]).setInteger("cdtick", enumChaotic.passiveStats[0]);
//                        }
//                    }
//                }
//            }
//        }
    }

    public void onUpdate(ItemStack i, World w, Entity e, int int0, boolean boolean0) {
        if (AMod.isServer) {
            if (!w.isRemote) {
                if (i.getItemDamage() == 0 && !Utils.getCompound(i).hasKey("hasStats")) {
                    this.onCreated(i, w, null);
                }
            }
        }
    }

    public void onCreated(ItemStack i, World w, EntityPlayer p) {
        if (AMod.isServer) {
            NBTTagCompound nbt = Utils.getCompound(i);
            if (!w.isRemote && !nbt.hasKey("hasStats")) {
                EnumChaotic enumChaotic = null;
                boolean isActive = Utils.getRandomOf(100.0D, 30.0D);
                while (enumChaotic == null) {
                    EnumChaotic enumChaotic1 = EnumChaotic.getById(Utils.rand.nextInt(isActive ? EnumChaotic.ACTIVE : EnumChaotic.PASSIVE) + (isActive ? 0 : 50));
                    if (Utils.getRandomOf(100.0D, enumChaotic1.rarity + 300)) {
                        enumChaotic = enumChaotic1;
                        break;
                    }
                }
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInteger("id", enumChaotic.id);
                compound.setString("name", enumChaotic.toString());
                compound.setInteger("skin", enumChaotic.skin);
                compound.setInteger("activity", enumChaotic.type.ordinal());
                compound.setInteger("active", enumChaotic.doSkill == null ? -1 : enumChaotic.doSkill.ordinal());
                compound.setInteger("passive", enumChaotic.passive == null ? -1 : enumChaotic.passive.ordinal());
                compound.setIntArray("stats", enumChaotic.passiveStats);
                nbt.setTag("chaotic", compound);
                nbt.setBoolean("hasStats", true);
            }
        }
    }

    public int getAdditionHP(ItemStack arg0, int max) {
        if (AMod.isServer) {
            if (Utils.getCompound(arg0).getBoolean("hasStats")) {
                NBTTagCompound nbtTagCompound = Utils.getCompound(arg0).getCompoundTag("chaotic");
                EnumChaotic enumChaotic = EnumChaotic.getById(nbtTagCompound.getInteger("id"));
                if (enumChaotic.type == EnumActivity.PASSIVE && enumChaotic.passive == EnumPassive.HEAL) {
                    return (int) ((double) max / 100.0D * enumChaotic.passiveStats[0]);
                }
            }
        }
        return 0;
    }

    public int getAdditionMana(ItemStack arg0, int max) {
        if (AMod.isServer) {
            if (Utils.getCompound(arg0).getBoolean("hasStats")) {
                NBTTagCompound nbtTagCompound = Utils.getCompound(arg0).getCompoundTag("chaotic");
                EnumChaotic enumChaotic = EnumChaotic.getById(nbtTagCompound.getInteger("id"));
                if (enumChaotic.type == EnumActivity.PASSIVE && enumChaotic.passive == EnumPassive.MANA) {
                    return (int) ((double) max / 100.0D * enumChaotic.passiveStats[0]);
                }
            }
        }
        return 0;
    }

    public int getAdditionArmory(ItemStack arg0, int max) {
        if (AMod.isServer) {
            if (Utils.getCompound(arg0).getBoolean("hasStats")) {
                NBTTagCompound nbtTagCompound = Utils.getCompound(arg0).getCompoundTag("chaotic");
                EnumChaotic enumChaotic = EnumChaotic.getById(nbtTagCompound.getInteger("id"));
                if (enumChaotic.type == EnumActivity.PASSIVE && enumChaotic.passive == EnumPassive.ARMORY) {
                    return (int) ((double) max / 100.0D * enumChaotic.passiveStats[0]);
                }
            }
        }
        return 0;
    }

    public int getSlot(ItemStack stack) {
        return 0;
    }
}
