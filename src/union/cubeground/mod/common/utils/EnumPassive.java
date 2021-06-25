package union.cubeground.mod.common.utils;

public enum EnumPassive {
    DAMAGE,
    HEAL,
    ARMORY,
    MANA;

    public static String getString(EnumPassive enums) {
        switch (enums) {
            case DAMAGE: return "Увеличивает урон на ";
            case HEAL: return "Увеличивает кол-во хп на ";
            case ARMORY: return "Увеличивает защиту на ";
            case MANA: return "Увеличивает кол-во маны на ";
            default: return "Сломанный хаотик.";
        }
    }

    public static EnumPassive getById(int id) {
        for (EnumPassive value : values()) {
            if (value.ordinal() == id) return value;
        }
        return null;
    }
}
