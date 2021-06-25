package union.cubeground.mod.common.utils;

import net.minecraft.item.ItemStack;

public interface IStatsExtension {
    public abstract int getAdditionHP(ItemStack arg0, int maxHP);
    public abstract int getAdditionMana(ItemStack arg0, int maxMana);
    public abstract int getAdditionArmory(ItemStack arg0, int maxArmory);
}
