package union.cubeground.mod.common.utils;

public enum EnumArmorStats {
    WATER_BREATH,        //0
    RESISTANCE,          //1
    DURABILITY,          //2
    FIRE_RESISTANCE,     //3
    HP_REGENERATION,     //4
    MANA_REGENERATION,   //5
    HP_UP,               //6
    MANA_UP;             //7

    public static String getString(EnumArmorStats stats) {
        return getString(stats.ordinal());
    }

    public static String getString(int ord) {
        switch (ord) {
            case 0: return "Подводное дыхание";
            case 1: return "Стойкость к урону";
            case 2: return "Прочность";
            case 3: return "Стойкость к огню";
            case 4: return "Регенерация здоровья";
            case 5: return "Регенерация маны";
            case 6: return "Увеличение здоровья";
            case 7: return "Увеличение маны";
            default: return "Нулевой эффект";
        }
    }

    public static EnumArmorStats getById(int id) {
        for (EnumArmorStats stats : values()) {
            if (stats.ordinal() == id) return stats;
        }
        return null;
    }

    public static EnumArmorStats[] getStats(int... ordinals) {
        EnumArmorStats[] stats = new EnumArmorStats[ordinals.length];
        for (int i = 0; i != ordinals.length; i++) {
            stats[i] = getById(ordinals[i]);
        }
        return stats;
    }
}
