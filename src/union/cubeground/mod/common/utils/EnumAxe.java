package union.cubeground.mod.common.utils;

public enum EnumAxe {
    MEDUMA(0, 0, "Meduma", 19.0D, 1.3D, 1, MinMax.get(5, 10), false, MinMax.get(-1, -1)),
    UALURNAN(1, 1, "Ualurnan", 16.0D, 1.5D, 1, MinMax.get(7, 15), false, MinMax.get(-1, -1)),
    BIARTIZKI(2, 2, "Biartizki", 15.0D, 2.0D, 3, MinMax.get(10, 20), true, MinMax.get(5000, 15000)),
    MERKITU(3, 3, "Merkitu", 15.0D, 3.0D, 5, MinMax.get(8, 30), true, MinMax.get(5000, 15000)),
    ENMELUAR(4, 4, "Enmeluar", 15.0D, 1.5D, 4, MinMax.get(10, 30), true, MinMax.get(5000, 150000)),
    ANAMUN(5, 5, "Anamun", 15.0D, 1.95D, 5, MinMax.get(15, 40), true, MinMax.get(7500, 175000)),
    UMUNGAR(6, 6, "Umungar", 13.0D, 2.1D, 7, MinMax.get(20, 35), true, MinMax.get(7500, 17500)),
    ENDUDRAR(7, 7, "Endudrar", 5.0D, 2.85D, 10, MinMax.get(30, 50), true, MinMax.get(10000, 20000)),
    USHSIP(8, 8, "Ushsip", 4.0D, 2.1D, 9, MinMax.get(25, 45), true, MinMax.get(10000, 20000)),
    GORATO(9, 9, "Gorato", 3.0D, 2.5D, 10, MinMax.get(30, 55), true, MinMax.get(15000, 20000)),
    EULOS(10, 10, "Eulos", 1.0D, 5.0D, 15, MinMax.get(40, 80), true, MinMax.get(15000, 25000)),
    ARYROS(11, 11, "Aryros", 0.1D, 4.25D, 25, MinMax.get(50, 120), true, MinMax.get(25000, 50000)),
    LUSAMO(12, 12, "Lusamo", 0.5D, 4.5D, 20, MinMax.get(45, 75), true, MinMax.get(15000, 50000)),
    HILESOP(13, 13, "Hilesop", 0.1D, 12.5D, 19, MinMax.get(100, 250), true, MinMax.get(50000, 100000));

    public static final int TOTAL = 14;
    public int id, skin, minLevel;
    public boolean isDamaging;
    public String name;
    public double chance, cooldown;
    public MinMax damage, damaging;
    EnumAxe(int id, int skin, String name, double chance, double cooldown, int minLevel, MinMax damage, boolean isDamaging, MinMax damaging) {
        this.id = id;
        this.skin = skin;
        this.name = name;
        this.chance = chance;
        this.cooldown = cooldown;
        this.minLevel = minLevel;
        this.damage = damage;
        this.isDamaging = isDamaging;
        this.damaging = damaging;
    }

    public static EnumAxe getById(int id) {
        for (EnumAxe axe : values()) {
            if (axe.id == id) return axe;
        }
        return null;
    }
}
