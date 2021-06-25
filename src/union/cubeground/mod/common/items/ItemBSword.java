package union.cubeground.mod.common.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.utils.EnumSword;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.Utils;

public class ItemBSword extends ItemSword implements ICustomInfo {
    public ItemBSword(int id) {
        super(id, EnumToolMaterial.EMERALD);
        this.setUnlocalizedName("itemBSword");
        this.setCreativeTab(ItemHandler.tabItems);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
        if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("sword");
            if (l.size() != 0) l.set(0, "Меч " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add("Меч " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add("Урон: " + compound.getInteger("damage"));
            l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
            if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
            else l.add("Предмет не имеет износа!");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный меч" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный меч" + " " + Utils.getLoreID(p, i));
            l.add("§5Меч не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
    }
    
    public void getInfo(ItemStack i, EntityPlayer p, List l) {
    	if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("sword");
            if (l.size() != 0) l.set(0, "Меч " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            else l.add("Меч " + compound.getString("name") + " " + Utils.getLoreID(p, i));
            l.add("Урон: " + compound.getInteger("damage"));
            l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
            if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
            else l.add("Предмет не имеет износа!");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный меч" + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный меч" + " " + Utils.getLoreID(p, i));
            l.add("§5Меч не принял финальный вид!");
            l.add("§5Возьмите его для превращения!");
        }
    }
    
    public void onUpdate(ItemStack i, World w, Entity e, int int0, boolean boolean0) {
        if (AMod.isServer) {
            if (!w.isRemote) {
                if (i.getItemDamage() == 0 && !Utils.getCompound(i).hasKey("hasStats")) this.onCreated(i, w, null);
            }
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (AMod.isServer) {
            if (!player.worldObj.isRemote && Utils.getCompound(stack).hasKey("hasStats")) {
                if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), Utils.getCompound(stack).getCompoundTag("sword").getInteger("damage"))) {
                    causeDamage(player, stack, 1);
                    return true;
                }
            }
        }
        return false;
    }

    public static void causeDamage(EntityPlayer p, ItemStack stack, int damage) {
        if (AMod.isServer) {
            if (!p.worldObj.isRemote) {
                NBTTagCompound compound = Utils.getCompound(stack);
                if (compound.hasKey("hasStats") && compound.getCompoundTag("sword").getBoolean("isDamaging")) {
                    compound.getCompoundTag("sword").setInteger("damaging", compound.getCompoundTag("sword").getInteger("damaging") + damage);
                    if (compound.getCompoundTag("sword").getInteger("damaging") >= compound.getCompoundTag("sword").getInteger("damagingMax")) {
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
                EnumSword enumSword = null;
                while (enumSword == null) {
                    EnumSword enumSword1 = EnumSword.getById(Utils.rand.nextInt(EnumSword.TOTAL));
                    if (Utils.getRandomOf(100.0D, enumSword1.chance)) {
                        enumSword = enumSword1;
                        break;
                    }
                }
                nbt.setString("UUID", UUID.randomUUID().toString().toUpperCase());
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInteger("id", enumSword.id);
                compound.setString("name", enumSword.name);
                compound.setInteger("skin", enumSword.skin);
                compound.setInteger("levelMin", enumSword.minLevel);
                compound.setInteger("damage", Utils.rangedRandom(enumSword.damage.min, enumSword.damage.max));
                if (enumSword.isDamaging) compound.setBoolean("isDamaging", !Utils.getRandomOf(100.0D, enumSword.chance));
                else compound.setBoolean("isDamaging", false);
                compound.setInteger("damagingMax", Utils.rangedRandom(enumSword.damaging.min, enumSword.damaging.max));
                nbt.setTag("sword", compound);
                nbt.setBoolean("hasStats", true);
            }
        }
    }
}
