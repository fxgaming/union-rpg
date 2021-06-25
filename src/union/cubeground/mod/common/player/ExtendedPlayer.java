package union.cubeground.mod.common.player;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import union.cubeground.mod.AMod;
import union.cubeground.mod.common.inventory.InventoryAccessories;
import union.cubeground.mod.common.inventory.InventoryChar;
import union.cubeground.mod.common.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExtendedPlayer implements IExtendedEntityProperties {
    public EntityPlayer player;
    public InventoryChar invChar;
    public InventoryAccessories invAccs;
    public Stats stats;
    public int tick;
    public int gold;

    public ExtendedPlayer(EntityPlayer player) {
        this.player = player;
        this.invChar = new InventoryChar();
        this.invAccs = new InventoryAccessories();
        this.stats = new Stats(this);
        this.tick = 0;
        this.gold = 0;
    }

    public static final ExtendedPlayer register(EntityPlayer player) {
        player.registerExtendedProperties("rpg", new ExtendedPlayer(player));
        return (ExtendedPlayer)player.getExtendedProperties("rpg");
    }

    public static final ExtendedPlayer get(EntityPlayer player) {
        if (player != null) return (ExtendedPlayer)player.getExtendedProperties("rpg");
        return null;
    }

    public void saveNBTData(NBTTagCompound tag) {
        NBTTagCompound data = new NBTTagCompound();
        this.invChar.save(data);
        this.invAccs.save(data);
        this.stats.save(data);
        data.setInteger("tick", this.tick);
        data.setInteger("gold", this.gold);
        tag.setTag("rpgPlayer", data);
    }

    public void loadNBTData(NBTTagCompound tag) {
        NBTTagCompound data = tag.getCompoundTag("rpgPlayer");
        this.invChar.read(data);
        this.invAccs.read(data);
        this.stats.load(data);
        this.tick = data.getInteger("tick");
        this.gold = data.getInteger("gold");
    }

    public void cloneFrom(ExtendedPlayer ep) {
        this.invChar = ep.invChar;
        this.invAccs = ep.invAccs;
    }

    public void resetData() {
        this.stats.drop(this);
    }

    public void init(Entity entity, World world) {
        this.stats = new Stats(this);
    }

    public class Stats {
        public int hp;
        public int maxHP;
        public int mana;
        public int maxMana;
        public int armory;
        public int xp;
        public int needXP;
        public int level;

        public Stats(ExtendedPlayer ep) {
            this.hp = this.maxHP = this.maxMana = 100;
            this.mana = this.armory = this.xp = 0;
            this.level = 1;
            this.update(ep);
        }

        public Stats drop(ExtendedPlayer ep) {
            this.hp = this.maxHP / 2;
            this.maxHP = this.maxMana = 100;
            this.mana = this.armory = 0;
            this.update(ep);
            return this;
        }

        public Stats update(ExtendedPlayer ep) {
            if (AMod.isServer) {
                InventoryChar ich = ep.invChar;
                int neededExperience = (int) (6.0F * (this.level + 1) * (this.level + 2) * 25.0F * 2.0);
                if (this.xp >= (this.needXP = neededExperience)) {
                    this.xp -= neededExperience;
                    this.level += 1;
                    this.needXP = (int) (6.0F * (this.level + 1) * (this.level + 2) * 25.0F * 2.0);
                }
                int maxHP = 100 + (int) ((4.5F * (this.level + 1) * (this.level + 2) / 2.5F));
                int maxMana = 95 + (this.level * 5);
                int armory = 0;
                for (ItemStack i : ich.inventory) {
                    if (i != null && i.getItem() instanceof IStatsExtension) {
                        IStatsExtension ise = (IStatsExtension) i.getItem();
                        maxHP += ise.getAdditionHP(i, maxHP);
                        maxMana += ise.getAdditionMana(i, maxMana);
                        armory += ise.getAdditionArmory(i, armory);
                    }
                }

                this.maxHP = maxHP;
                this.maxMana = maxMana;
                this.armory = armory;
                if (this.hp > this.maxHP) this.hp = this.maxHP;
                if (this.mana > this.maxMana) this.mana = this.maxMana;
            }
            return this;
        }

        public void save(NBTTagCompound nbtTagCompound) {
            nbtTagCompound.setIntArray("stats", new int[]{this.hp, this.maxHP, this.mana, this.maxMana, this.armory, this.xp, this.level, this.needXP});
        }

        public void load(NBTTagCompound nbtTagCompound) {
            this.hp = nbtTagCompound.getIntArray("stats")[0];
            this.maxHP = nbtTagCompound.getIntArray("stats")[1];
            this.mana = nbtTagCompound.getIntArray("stats")[2];
            this.maxMana = nbtTagCompound.getIntArray("stats")[3];
            this.armory = nbtTagCompound.getIntArray("stats")[4];
            this.xp = nbtTagCompound.getIntArray("stats")[5];
            this.level = nbtTagCompound.getIntArray("stats")[6];
            this.needXP = nbtTagCompound.getIntArray("stats")[7];
        }
    }

    public static class Effect {
        public EnumChaotic effect;
        public Long timeTo;
        public String uuid;

        Effect(EnumChaotic a, double b) {
            this.effect = a;
            this.timeTo = System.currentTimeMillis() + (long)(b * 1000.0D);
            this.uuid = UUID.randomUUID().toString();
        }

        public static Effect create(EnumChaotic skill, double seconds) {
            return new Effect(skill, seconds);
        }

        public void save(NBTTagCompound nbt) {
            nbt.setInteger("effectid", this.effect.ordinal());
            nbt.setLong("timeto", this.timeTo);
            nbt.setString("uuid", this.uuid);
        }

        public boolean load(NBTTagCompound nbt) {
            this.effect = EnumChaotic.getById(nbt.getInteger("effectid"));
            this.timeTo = nbt.getLong("timeto");
            this.uuid = nbt.getString("uuid");
            if (this.timeTo <= System.currentTimeMillis()) return false;
            return true;
        }

        public boolean isActive() {
            return this.timeTo <= System.currentTimeMillis();
        }
    }
}
