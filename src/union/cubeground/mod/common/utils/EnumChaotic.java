package union.cubeground.mod.common.utils;

public enum EnumChaotic {
    //                                                                                                 kd, deisvtie, boosting
//    WIRN(0, 0, 5.1D, EnumActivity.ACTIVE, EnumDoSkill.HYPER_JUMP, null, 60, 5),
//    QADROKOND(1, 1, 3.7D, EnumActivity.ACTIVE, EnumDoSkill.ARMORY_BOOST, null, 300, 30, 30),
//    TERKOLOND(2, 2, 2.5D, EnumActivity.ACTIVE, EnumDoSkill.DAMAGE_BOOST, null, 300, 30, 30),
//    XUNN(3, 3, 0.1D, EnumActivity.ACTIVE, EnumDoSkill.INVINCIBLE, null, 600, 10),
//    NOLON(4, 4, 4.9D, EnumActivity.ACTIVE, EnumDoSkill.SHOOT_SPEED_BOOST, null, 300, 30, 50),
//    IWIGID(5, 5, 2.1D, EnumActivity.ACTIVE, EnumDoSkill.INVISIBLE, null, 300, 10),
//    IBROZ(6, 6, 0.07D, EnumActivity.ACTIVE, EnumDoSkill.MICRO_SIZE, null, 600, 20),

    REDE(50, 1, 42.8D, EnumActivity.PASSIVE, null, EnumPassive.DAMAGE, 1),
    EARCOR(51, 1, 35.6D, EnumActivity.PASSIVE, null, EnumPassive.DAMAGE, 2),
    UNSITH(52, 1, 24.2D, EnumActivity.PASSIVE, null, EnumPassive.DAMAGE, 3),
    CYNE(53, 1, 1.1D, EnumActivity.PASSIVE, null, EnumPassive.DAMAGE, 5),
    AERER(54, 4, 46.9D, EnumActivity.PASSIVE, null, EnumPassive.ARMORY, 1),
    BALDA(55, 4, 33.2D, EnumActivity.PASSIVE, null, EnumPassive.ARMORY, 2),
    EWULF(56, 4, 29.7D, EnumActivity.PASSIVE, null, EnumPassive.ARMORY, 3),
    ABEOF(57, 4, 1.8D, EnumActivity.PASSIVE, null, EnumPassive.ARMORY, 5),
    CATUN(58, 3, 51.9D, EnumActivity.PASSIVE, null, EnumPassive.HEAL, 2),
    PAHAM(59, 3, 49.7D, EnumActivity.PASSIVE, null, EnumPassive.HEAL, 3),
    DABURH(60, 3, 22.8D, EnumActivity.PASSIVE, null, EnumPassive.HEAL, 4),
    WAMOR(61, 3, 2.7D, EnumActivity.PASSIVE, null, EnumPassive.HEAL, 5),
    WITHA(62, 2, 34.7D, EnumActivity.PASSIVE, null, EnumPassive.MANA, 2),
    ELITH(63, 2, 30.9D, EnumActivity.PASSIVE, null, EnumPassive.MANA, 3),
    BURGA(64, 2, 17.8D, EnumActivity.PASSIVE, null, EnumPassive.MANA, 4),
    GIFU(65, 2, 2.8D, EnumActivity.PASSIVE, null, EnumPassive.MANA, 5);

    public static final int ACTIVE = 0;//7;
    public static final int PASSIVE = 16;

    public int id;
    public int skin;
    public double rarity;
    public EnumActivity type;
    public EnumDoSkill doSkill;
    public EnumPassive passive;
    public int[] passiveStats;

    EnumChaotic(int id, int skin, double rarity, EnumActivity type, EnumDoSkill doSkill, EnumPassive passive, int... stats) {
        this.id = id;
        this.skin = skin;
        this.rarity = rarity;
        this.type = type;
        this.doSkill = doSkill;
        this.passive = passive;
        this.passiveStats = stats;
    }

    public static EnumChaotic getById(int id) {
        for (EnumChaotic value : values()) {
            if (value.id == id) return value;
        }
        return null;
    }

    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
