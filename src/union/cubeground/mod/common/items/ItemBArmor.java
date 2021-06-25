package union.cubeground.mod.common.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.handler.ItemHandler;
import union.cubeground.mod.common.utils.EnumArmor;
import union.cubeground.mod.common.utils.EnumArmorStats;
import union.cubeground.mod.common.utils.ICustomInfo;
import union.cubeground.mod.common.utils.Utils;

public class ItemBArmor extends ItemArmor implements ISpecialArmor, ICustomInfo {
    public int type;
    public ItemBArmor(int id, int type) {
        super(id, EnumArmorMaterial.DIAMOND, type, type);
        this.type = type;
        this.setCreativeTab(ItemHandler.tabItems);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setUnlocalizedName("item" + (type == 0 ? "Helmet" : type == 1 ? "Chestplate" : type == 2 ? "Leggins" : "Boots"));
    }

    public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
        String type = this.type == 0 ? "Шлем" : this.type == 1 ? "Нагрудник" : this.type == 2 ? "Поножи" : "Ботинки";
        if (Utils.getCompound(i).hasKey("hasStats")) {
            NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("armor");
            if (l.size() != 0) l.set(0, type + " \'" + compound.getString("name") + "\'" + " " + Utils.getLoreID(p, i));
            else l.add(type + " \'" + compound.getString("name") + "\'" + " " + Utils.getLoreID(p, i));
            l.add("Защита: " + compound.getInteger("armory"));
            l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
            if (compound.getIntArray("stats").length > 0 && !GuiScreen.isShiftKeyDown()) l.add("Нажмите SHIFT для просмотра эффектов");
            else if (compound.getIntArray("stats").length > 0 && GuiScreen.isShiftKeyDown()) {
                int[] stats = compound.getIntArray("stats");
                int[] datas = compound.getIntArray("statsdata");
                for (int j = 0; j != stats.length; j++) l.add(EnumArmorStats.getString(stats[j]) + " " + datas[j] + "ед.");
            }
            if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
            else l.add("Предмет не имеет износа!");
        } else {
            if (l.size() != 0) l.set(0, "Неопознанный(ая) " + type + " " + Utils.getLoreID(p, i));
            else l.add("Неопознанный(ая) " + type + " " + Utils.getLoreID(p, i));
            l.add("§5Броня не приняла финальный вид!");
            l.add("§5Возьмите её для превращения!");
        }
    }
    
    public void getInfo(ItemStack i, EntityPlayer p, List l) {
    	 String type = this.type == 0 ? "Шлем" : this.type == 1 ? "Нагрудник" : this.type == 2 ? "Поножи" : "Ботинки";
         if (Utils.getCompound(i).hasKey("hasStats")) {
             NBTTagCompound compound = Utils.getCompound(i).getCompoundTag("armor");
             if (l.size() != 0) l.set(0, type + " \'" + compound.getString("name") + "\'" + " " + Utils.getLoreID(p, i));
             else l.add(type + " \'" + compound.getString("name") + "\'" + " " + Utils.getLoreID(p, i));
             l.add("Защита: " + compound.getInteger("armory"));
             l.add("Для мастеров " + compound.getInteger("levelMin") + " уровня");
             if (compound.getIntArray("stats").length > 0 && !GuiScreen.isShiftKeyDown()) l.add("Нажмите SHIFT для просмотра эффектов");
             else if (compound.getIntArray("stats").length > 0 && GuiScreen.isShiftKeyDown()) {
                 int[] stats = compound.getIntArray("stats");
                 int[] datas = compound.getIntArray("statsdata");
                 for (int j = 0; j != stats.length; j++) l.add(EnumArmorStats.getString(stats[j]) + " " + datas[j] + "ед.");
             }
             if (compound.getBoolean("isDamaging")) l.add("Прочность: " + (compound.getInteger("damagingMax") - compound.getInteger("damaging")) + " ед.");
             else l.add("Предмет не имеет износа!");
         } else {
             if (l.size() != 0) l.set(0, "Неопознанный(ая) " + type + " " + Utils.getLoreID(p, i));
             else l.add("Неопознанный(ая) " + type + " " + Utils.getLoreID(p, i));
             l.add("§5Броня не приняла финальный вид!");
             l.add("§5Возьмите её для превращения!");
         }
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        NBTTagCompound compound = Utils.getCompound(stack);
        if (compound.getBoolean("hasStats") && compound.hasKey("armor")) {
            NBTTagCompound nbt = compound.getCompoundTag("armor");
            EnumArmor enumArmor = EnumArmor.getById(nbt.getInteger("id"), nbt.getInteger("type"));
            int type1 = enumArmor.type == 2 ? 2 : 1;
            return "cubeground:textures/items/armor/" + enumArmor.skin + "_" + type1 + ".png";
        }
        return null;
    }

    public static void causeDamage(EntityPlayer p, ItemStack stack, int damage) {
        if (AMod.isServer) {
            if (!p.worldObj.isRemote) {
                NBTTagCompound compound = Utils.getCompound(stack);
                if (compound.getBoolean("hasStats") && compound.getCompoundTag("armor").getBoolean("isDamaging")) {
                    compound.getCompoundTag("armor").setInteger("damaging", compound.getCompoundTag("armor").getInteger("damaging") + damage);
                    if (compound.getCompoundTag("armor").getInteger("damaging") >= compound.getCompoundTag("armor").getInteger("damagingMax")) {
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
                EnumArmor enumArmor = null;
                while (enumArmor == null) {
                    EnumArmor enumArmor1 = EnumArmor.getById(Utils.rand.nextInt(EnumArmor.TOTAL), this.type);
                    if (enumArmor1 != null) {
                        if (Utils.getRandomOf(100.0D, enumArmor1.chance)) {
                            enumArmor = enumArmor1;
                            break;
                        }
                    }
                }
                nbt.setString("UUID", UUID.randomUUID().toString().toUpperCase());
                NBTTagCompound compound = new NBTTagCompound();
                compound.setInteger("id", enumArmor.id);
                compound.setInteger("type", enumArmor.type);
                compound.setString("skin", enumArmor.skin);
                compound.setString("name", enumArmor.name);
                compound.setInteger("levelMin", enumArmor.minLevel);
                compound.setInteger("armory", Utils.rangedRandom(enumArmor.armory.min, enumArmor.armory.max));
                if (enumArmor.isDamaging) compound.setBoolean("isDamaging", !Utils.getRandom2(350.0D, enumArmor.chance / 2.0D));
                else compound.setBoolean("isDamaging", false);
                compound.setInteger("damagingMax", Utils.rangedRandom(enumArmor.damaging.min, enumArmor.damaging.max));
                int[] ordinals = new int[enumArmor.stats.length];
                int[] dataOrdinals = new int[enumArmor.statsdata.length];
                for (int j = 0; j != enumArmor.stats.length; j++) ordinals[j] = enumArmor.stats[j].ordinal();
                for (int j = 0; j != enumArmor.statsdata.length; j++) dataOrdinals[j] = enumArmor.statsdata[j];
                nbt.setIntArray("stats", ordinals);
                nbt.setIntArray("statsdata", dataOrdinals);
                nbt.setTag("armor", compound);
                nbt.setBoolean("hasStats", true);
            }
        }
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

    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {return new ArmorProperties(0, 0.0D, 0);}
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {return 0;}
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}
}
