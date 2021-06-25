package union.cubeground.mod.common.utils;

public enum EnumDoSkill {
    DAMAGE_BOOST,
    HEAL_BOOST,
    SPEED_BOOST,
    ARMORY_BOOST,
    SHOOT_SPEED_BOOST,
    KNOCK_BACK_BOOST,
    FIRE_EXTINGUISH,
    ITEM_REFRESH,
    INVINCIBLE,
    INVISIBLE,
    MICRO_SIZE,
    HYPER_JUMP;

    public static String getString(EnumDoSkill enums) {
        switch (enums) {
            case DAMAGE_BOOST: return "Добавляет урон";
            case HEAL_BOOST: return "Добавляет хп";
            case SPEED_BOOST: return "Добавляет скорость";
            case ARMORY_BOOST: return "Добавляет защиту";
            case SHOOT_SPEED_BOOST: return "Увеличивает скоростельность";
            case KNOCK_BACK_BOOST: return "Усиливает откидывание";
            case FIRE_EXTINGUISH: return "Тушит огонь";
            case ITEM_REFRESH: return "Сбивает перезарядку";
            case INVINCIBLE: return "Временная неуязвимость";
            case INVISIBLE: return "Временная невидимость";
            case MICRO_SIZE: return "Уменьшает вас";
            case HYPER_JUMP: return "Дает супер-прыжок";
            default: return "Сломанный хаотик.";
        }
    }

    public static EnumDoSkill getById(int id) {
        for (EnumDoSkill value : values()) {
            if (value.ordinal() == id) return value;
        }
        return null;
    }
}
