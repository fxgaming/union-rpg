package union.cubeground.mod.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.utils.EnumAxe;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.Utils;

import java.util.List;
import java.util.UUID;

public class ItemBAxe extends Item implements ICustomInfo {
    public ItemBAxe(int id) {
    	super(id);
        this.setUnlocalizedName("itemBAxe");
        this.setCreativeTab(ItemHandler.tabItems);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setFull3D();
    }

    public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
        if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("axe");
            if (l.size() != 0) l.set(0, "Топор " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add("Топор " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add("Урон: " + compound.getInteger("damage"));
            l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
            l.add("КД: " + compound.getDouble("cooldown") + " сек.");
            if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
            else l.add("Предмет не имеет износа!");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный топор" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный топор" + " " + Utils.getLoreID(p, i));
            l.add("§5Топор не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
    }
    
    public void getInfo(ItemStack i, EntityPlayer p, List l) {
    	if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("axe");
            if (l.size() != 0) l.set(0, "Топор " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add("Топор " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add("Урон: " + compound.getInteger("damage"));
            l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
            l.add("КД: " + compound.getDouble("cooldown") + " сек.");
            if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
            else l.add("Предмет не имеет износа!");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный топор" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный топор" + " " + Utils.getLoreID(p, i));
            l.add("§5Топор не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
    }
    
    public void onUpdate(ItemStack i, World w, Entity e, int int0, boolean boolean0) {
        if (AMod.isServer) {
            if (!w.isRemote) {
                if (i.getItemDamage() == 0 && !Utils.getCompound(i).hasKey("hasStats")) this.onCreated(i, w, null);
                if (e instanceof EntityPlayer && ((EntityPlayer) e).getHeldItem() != null) {
                    ItemStack stack = ((EntityPlayer) e).getHeldItem();
                    if (Utils.getCompound(stack).getString("UUID").equals(Utils.getCompound(i).getString("UUID"))) {
                        if (Utils.getCompound(i).getBoolean("hasStats") && Utils.getCompound(i).getInteger("cdtick") > 0) {
                            Utils.getCompound(i).setInteger("cdtick", Utils.getCompound(i).getInteger("cdtick") - 1);
                        }
                    }
                }
            }
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (AMod.isServer) {
            if (!player.worldObj.isRemote && Utils.getCompound(stack).hasKey("hasStats")) {
                if (Utils.getCompound(stack).getInteger("cdtick") <= 0) {
                    if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), Utils.getCompound(stack).getCompoundTag("axe").getInteger("damage"))) {
                        causeDamage(player, stack, Utils.getCompound(stack).getCompoundTag("axe").getInteger("damage"));
                        Utils.getCompound(stack).setInteger("cdtick", (int) Utils.getCompound(stack).getCompoundTag("axe").getDouble("cooldown") * 1000 / 50);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void causeDamage(EntityPlayer p, ItemStack stack, int damage) {
        if (AMod.isServer) {
            if (!p.worldObj.isRemote) {
                NBTTagCompound compound = Utils.getCompound(stack);
                if (compound.hasKey("hasStats") && compound.getCompoundTag("axe").getBoolean("isDamaging")) {
                    compound.getCompoundTag("axe").setInteger("damaging", compound.getCompoundTag("axe").getInteger("damaging") + damage);
                    if (compound.getCompoundTag("axe").getInteger("damaging") >= compound.getCompoundTag("axe").getInteger("damagingMax")) {
                        p.setCurrentItemOrArmor(0, null);
                        p.playSound("entity.item.break", 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    public void onCreated(ItemStack i, World w, EntityPlayer p) {
        if (AMod.isServer) {
            NBTTagCompound nbt = Utils.getCompound(i);
            if (!w.isRemote && !nbt.hasKey("hasStats")) {
                EnumAxe enumAxe = null;
                while (enumAxe == null) {
                    EnumAxe enumAxe1 = EnumAxe.getById(Utils.rand.nextInt(EnumAxe.TOTAL));
                    if (Utils.getRandomOf(100.0D, enumAxe1.chance)) {
                        enumAxe = enumAxe1;
                        break;
                    }
                }
                nbt.setString("UUID", UUID.randomUUID().toString().toUpperCase());
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInteger("id", enumAxe.id);
                compound.setString("name", enumAxe.name);
                compound.setInteger("skin", enumAxe.skin);
                compound.setDouble("cooldown", enumAxe.cooldown);
                compound.setInteger("levelMin", enumAxe.minLevel);
                compound.setInteger("damage", Utils.rangedRandom(enumAxe.damage.min, enumAxe.damage.max));
                if (enumAxe.isDamaging) compound.setBoolean("isDamaging", !Utils.getRandom2(350.0D, enumAxe.chance));
                else compound.setBoolean("isDamaging", false);
                compound.setInteger("damaging", 0);
                compound.setInteger("damagingMax", Utils.rangedRandom(enumAxe.damaging.min, enumAxe.damaging.max));
                nbt.setTag("axe", compound);
                nbt.setBoolean("hasStats", true);
            }
        }
    }
}
