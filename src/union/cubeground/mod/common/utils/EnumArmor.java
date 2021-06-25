package union.cubeground.mod.common.utils;

public enum EnumArmor {
    COW0(0, 0, "cow", "Cow", 15.0D, 1, MinMax.get(1, 2), false, MinMax.get(1, 1), null, null),
    COW1(0, 1, "cow", "Cow", 15.0D, 1, MinMax.get(1, 2), false, MinMax.get(1, 1), null, null),
    COW2(0, 2, "cow", "Cow", 15.0D, 1, MinMax.get(1, 2), false, MinMax.get(1, 1), null, null),
    COW3(0, 3, "cow", "Cow", 15.0D, 1, MinMax.get(1, 2), false, MinMax.get(1, 1), null, null),
    WOOD0(1, 0, "wood", "деревянный", 15.0D, 1, MinMax.get(1, 3), true, MinMax.get(1000, 2000), null, null),
    WOOD1(1, 1, "wood", "деревянный", 15.0D, 1, MinMax.get(1, 3), true, MinMax.get(1000, 2000), null, null),
    WOOD2(1, 2, "wood", "деревянные", 15.0D, 1, MinMax.get(1, 3), true, MinMax.get(1000, 2000), null, null),
    WOOD3(1, 3, "wood", "деревянные", 15.0D, 1, MinMax.get(1, 3), true, MinMax.get(1000, 2000), null, null),
    WIZARD0(2, 0, "wizard", "мага", 10.0D, 3, MinMax.get(1, 4), true, MinMax.get(2000, 4000), EnumArmorStats.getStats(7), new int[]{3}),
    WIZARD1(2, 1, "wizard", "мага", 10.0D, 3, MinMax.get(1, 4), true, MinMax.get(2000, 4000), EnumArmorStats.getStats(6), new int[]{3}),
    WIZARD2(2, 2, "wizard", "мага", 10.0D, 3, MinMax.get(1, 4), true, MinMax.get(2000, 4000), EnumArmorStats.getStats(7), new int[]{3}),
    //WIZARD3(2, 3, "wizard", "мага", 10.0D, 3, MinMax.get(1, 4), true, MinMax.get(2000, 4000), EnumArmorStats.getStats(6), new int[]{3}),
    BRONZE0(3, 0, "bronze", "бронзовый", 10.0D, 5, MinMax.get(1, 5), true, MinMax.get(3000, 4000), null, null),
    BRONZE1(3, 1, "bronze", "бронзовый", 10.0D, 5, MinMax.get(1, 5), true, MinMax.get(3000, 4000), null, null),
    BRONZE2(3, 2, "bronze", "бронзовые", 10.0D, 5, MinMax.get(1, 5), true, MinMax.get(3000, 4000), null, null),
    BRONZE3(3, 3, "bronze", "бронзовые", 10.0D, 5, MinMax.get(1, 5), true, MinMax.get(3000, 4000), null, null),
    EMERALD0(4, 0, "emerald", "изумрудный", 5.0D, 9, MinMax.get(1, 6), true, MinMax.get(3000, 5000), null, null),
    EMERALD1(4, 1, "emerald", "изумрудные", 5.0D, 9, MinMax.get(1, 6), true, MinMax.get(3000, 5000), null, null),
    EMERALD2(4, 2, "emerald", "изумрудные", 5.0D, 9, MinMax.get(1, 6), true, MinMax.get(3000, 5000), null, null),
    EMERALD3(4, 3, "emerald", "изумрудные", 5.0D, 9, MinMax.get(1, 6), true, MinMax.get(3000, 5000), null, null),
    COMMISSAR0(5, 0, "commissar", "комиссара", 4.0D, 12, MinMax.get(1, 8), true, MinMax.get(5000, 10000), null, null),
    COMMISSAR1(5, 1, "commissar", "комиссара", 4.0D, 12, MinMax.get(1, 8), true, MinMax.get(5000, 10000), null, null),
    COMMISSAR2(5, 2, "commissar", "комиссара", 4.0D, 12, MinMax.get(1, 8), true, MinMax.get(5000, 10000), null, null),
    COMMISSAR3(5, 3, "commissar", "комиссара", 4.0D, 12, MinMax.get(1, 8), true, MinMax.get(5000, 10000), null, null),
    NANO0(6, 0, "nano", "нано", 3.0D, 14, MinMax.get(1, 10), true, MinMax.get(10000, 50000), EnumArmorStats.getStats(4), new int[]{3}),
    NANO1(6, 1, "nano", "нано", 3.0D, 14, MinMax.get(1, 10), true, MinMax.get(10000, 50000), EnumArmorStats.getStats(4), new int[]{3}),
    NANO2(6, 2, "nano", "нано", 3.0D, 14, MinMax.get(1, 10), true, MinMax.get(10000, 50000), EnumArmorStats.getStats(4), new int[]{3}),
    NANO3(6, 3, "nano", "нано", 3.0D, 14, MinMax.get(1, 10), true, MinMax.get(10000, 50000), EnumArmorStats.getStats(4), new int[]{3}),
    SOLDIER0(7, 0, "soldier", "солдата", 2.0D, 17, MinMax.get(1, 11), true, MinMax.get(5000, 7500), null, null),
    SOLDIER1(7, 1, "soldier", "солдата", 2.0D, 17, MinMax.get(1, 11), true, MinMax.get(5000, 7500), null, null),
    SOLDIER2(7, 2, "soldier", "солдата", 2.0D, 17, MinMax.get(1, 11), true, MinMax.get(5000, 7500), null, null),
    SOLDIER3(7, 3, "soldier", "солдата", 2.0D, 17, MinMax.get(1, 11), true, MinMax.get(5000, 7500), null, null),
    X4070(8, 0, "x407", "X407", 1.0D, 19, MinMax.get(10, 12), true, MinMax.get(7500, 10000), EnumArmorStats.getStats(4), new int[]{5}),
    X4071(8, 1, "x407", "X407", 1.0D, 19, MinMax.get(10, 12), true, MinMax.get(7500, 10000), EnumArmorStats.getStats(5), new int[]{5}),
    X4072(8, 2, "x407", "X407", 1.0D, 19, MinMax.get(10, 12), true, MinMax.get(7500, 10000), EnumArmorStats.getStats(6), new int[]{5}),
    X4073(8, 3, "x407", "X407", 1.0D, 19, MinMax.get(10, 12), true, MinMax.get(7500, 10000), EnumArmorStats.getStats(7), new int[]{5}),
    ASSASIN0(9, 0, "assasin", "ассасина", 0.75D, 21, MinMax.get(1, 13), true, MinMax.get(9500, 15000), EnumArmorStats.getStats(2), new int[]{1}),
    ASSASIN1(9, 1, "assasin", "ассасина", 0.75D, 21, MinMax.get(1, 13), true, MinMax.get(9500, 15000), EnumArmorStats.getStats(2), new int[]{2}),
    ASSASIN2(9, 2, "assasin", "ассасина", 0.75D, 21, MinMax.get(1, 13), true, MinMax.get(9500, 15000), EnumArmorStats.getStats(2), new int[]{2}),
    ASSASIN3(9, 3, "assasin", "ассасина", 0.75D, 21, MinMax.get(1, 13), true, MinMax.get(9500, 15000), EnumArmorStats.getStats(2), new int[]{1}),
    NINJA0(10, 0, "ninja", "ниндзи", 0.5D, 23, MinMax.get(1, 14), true, MinMax.get(12500, 15000), EnumArmorStats.getStats(2), new int[]{2}),
    NINJA1(10, 1, "ninja", "ниндзи", 0.5D, 23, MinMax.get(1, 14), true, MinMax.get(12500, 15000), EnumArmorStats.getStats(2), new int[]{3}),
    NINJA2(10, 2, "ninja", "ниндзи", 0.5D, 23, MinMax.get(1, 14), true, MinMax.get(12500, 15000), EnumArmorStats.getStats(2), new int[]{3}),
    //NINJA3(10, 3, "ninja", "ниндзи", 0.5D, 23, MinMax.get(1, 14), true, MinMax.get(12500, 15000), EnumArmorStats.getStats(2), new int[]{2}),
    //TUXEDO0(11, 0, "tuxedo", "Tuxedo", 0.25D, 25, MinMax.get(1, 15), true, MinMax.get(1500, 15000), null, null),
    TUXEDO1(11, 1, "tuxedo", "Tuxedo", 0.25D, 25, MinMax.get(1, 15), true, MinMax.get(1500, 15000), null, null),
    TUXEDO2(11, 2, "tuxedo", "Tuxedo", 0.25D, 25, MinMax.get(1, 15), true, MinMax.get(1500, 15000), null, null),
    TUXEDO3(11, 3, "tuxedo", "Tuxedo", 0.25D, 25, MinMax.get(1, 15), true, MinMax.get(1500, 15000), null, null),
    GALOKIN0(12, 0, "galokin", "Galokin", 0.125D, 30, MinMax.get(1, 17), true, MinMax.get(1500, 15000), EnumArmorStats.getStats(5), new int[]{5}),
    GALOKIN1(12, 1, "galokin", "Galokin", 0.125D, 30, MinMax.get(1, 17), true, MinMax.get(1500, 15000), EnumArmorStats.getStats(4), new int[]{5}),
    GALOKIN2(12, 2, "galokin", "Galokin", 0.125D, 30, MinMax.get(1, 17), true, MinMax.get(1500, 15000), EnumArmorStats.getStats(5), new int[]{5}),
    GALOKIN3(12, 3, "galokin", "Galokin", 0.125D, 30, MinMax.get(1, 17), true, MinMax.get(1500, 15000), EnumArmorStats.getStats(4), new int[]{5});

    public static final int TOTAL = 13;
    public int id, type, minLevel;
    public boolean isDamaging;
    public String skin, name;
    public double chance;
    public MinMax armory, damaging;
    public EnumArmorStats[] stats;
    public int[] statsdata;

    EnumArmor(int id, int type, String skin, String name, double chance, int minLevel, MinMax armory, boolean isDamaging, MinMax damaging, EnumArmorStats[] stats, int[] data) {
        this.id = id;
        this.type = type;
        this.skin = skin;
        this.name = name;
        this.chance = chance;
        this.minLevel = minLevel;
        this.armory = armory;
        this.isDamaging = isDamaging;
        this.damaging = damaging;
        this.stats = stats == null ? new EnumArmorStats[0] : stats;
        this.statsdata = data == null ? new int[0] : data;
    }

    public static EnumArmor getById(int id, int type) {
        for (EnumArmor armor : values()) {
            if (armor.id == id && armor.type == type) return armor;
        }
        return null;
    }
}
