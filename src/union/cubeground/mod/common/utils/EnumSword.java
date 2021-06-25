package union.cubeground.mod.common.utils;

import com.google.common.base.Enums;

public enum EnumSword {
    HEARTSEEKER(0, 9, "Heart Seeker", 16.0D, 1, MinMax.get(1, 10), false, MinMax.get(1, 1)),
    PERUSUASION(1, 11, "Perusuasion", 20.0D, 2, MinMax.get(2, 15), true, MinMax.get(250, 750)),
    MASSACRE(2, 21, "Massacre", 1.7D, 10, MinMax.get(10, 50), true, MinMax.get(500, 1750)),
    FERALCRUSADER(3, 18, "Feral Crusader", 0.5D, 10, MinMax.get(10, 60), true, MinMax.get(300, 1600)),
    CAPTAINSSABRE(4, 1, "Captain's Sabre", 20.0D, 3, MinMax.get(6, 20), false, MinMax.get(1, 1)),
    MITHRILGUARDIAN(5, 23, "Mithril Guardian", 1.6D, 5, MinMax.get(5, 30), true, MinMax.get(500, 1750)),
    BRONZESKEWER(6, 7, "Bronze Skewer", 15.0D, 2, MinMax.get(4, 20), true, MinMax.get(500, 1650)),
    STORMBREAKER(7, 2, "Storm Breaker", 20.0D, 4, MinMax.get(8, 20), true, MinMax.get(100, 1200)),
    SHORTSWORD(8, 0, "Shortsword", 20.0D, 1, MinMax.get(2, 10), false, MinMax.get(1, 1)),
    DESTROYER(9, 15, "Destroyer", 4.7D, 4, MinMax.get(10, 30), true, MinMax.get(200, 1400)),
    COMETFELL(10, 10, "Cometfell", 12.0D, 2, MinMax.get(4, 15), true, MinMax.get(500, 1000)),
    PRICK(11, 8, "Prick", 20.0D, 3, MinMax.get(6, 20), true, MinMax.get(750, 1950)),
    SPELLBLADE(12, 19, "Spellblade", 4.8D, 6, MinMax.get(12, 30), true, MinMax.get(200, 1500)),
    GLASSRAZOR(13, 20, "Glassrazor", 3.1D, 14, MinMax.get(28, 60), true, MinMax.get(500, 1700)),
    SEVERANCE(14, 4, "Severance", 20.0D, 2, MinMax.get(4, 20), true, MinMax.get(300, 1500)),
    WARBLADE(15, 5, "Warblade", 20.0D, 6, MinMax.get(12, 20), false, MinMax.get(1, 1)),
    SUNSTRIKE(16, 3, "Sun Strike", 20.0D, 3, MinMax.get(6, 20), true, MinMax.get(1400, 1450)),
    DRAGONETCHER(17, 16, "Dragon Etcher", 6.2D, 12, MinMax.get(24, 40), true, MinMax.get(100, 2000)),
    ESPADA(18, 17, "Espada", 0.4D, 22, MinMax.get(44, 80), true, MinMax.get(1500, 2700)),
    DEFENDER(19, 14, "Defender", 1.1D, 17, MinMax.get(34, 50), true, MinMax.get(1500, 2600)),
    SOULRAPIER(20, 13, "Soul Rapier", 1.9D, 11, MinMax.get(22, 40), true, MinMax.get(1000, 2000)),
    ARONDITE(21, 25, "Arondite", 3.1D, 15, MinMax.get(30, 50), true, MinMax.get(1000, 3000)),
    WARMONGER(22, 24, "Warmonger", 2.2D, 14, MinMax.get(28, 40), true, MinMax.get(1000, 2500)),
    VALKYRIE(23, 17, "Valkyrie", 0.02D, 25, MinMax.get(50, 200), true, MinMax.get(2500, 7500)),
    THUNDER(24, 18, "Thunder", 0.03D, 25, MinMax.get(50, 190), true, MinMax.get(1500, 5500)),
    DEFLECTOR(25, 14, "Deflector", 0.8D, 7, MinMax.get(21, 50), true, MinMax.get(1500, 3000)),
    BROADSWORD(26, 6, "Broadsword", 18.0D, 5, MinMax.get(5, 25), true, MinMax.get(1000, 2000)),
    STING(27, 22, "Sting", 4.05D, 9, MinMax.get(18, 40), true, MinMax.get(1000, 3000)),
    VENGEANCE(28, 12, "Vengeance", 0.95D, 23, MinMax.get(46, 120), true, MinMax.get(2500, 6500)),
    FLESHRENDER(29, 9, "Fleshrender", 15.5D, 9, MinMax.get(18, 30), false, MinMax.get(1, 1)),
    KATANA(30, 6, "Katana", 2.7D, 6, MinMax.get(30, 60), true, MinMax.get(2000, 3000)),
    INTERROGATOR(31, 21, "Interrogator", 0.45D, 17, MinMax.get(34, 80), true, MinMax.get(1000, 3000)),
    MALICE(32, 11, "Malice", 6.5D, 6, MinMax.get(12, 85), true, MinMax.get(500, 4000)),
    ANNIHILATOR(33, 17, "Annihilator", 0.1D, 24, MinMax.get(50, 170), true, MinMax.get(1000, 5000)),
    LAMENT(34, 19, "Lament", 1.2D, 13, MinMax.get(30, 80), true, MinMax.get(2000, 5000)),
    AIRDENT(35, 18, "Airdent", 0.1D, 22, MinMax.get(44, 160), true, MinMax.get(1000, 3000)),
    FIREFLAIL(36, 13, "Fireflail", 0.5D, 14, MinMax.get(30, 90), true, MinMax.get(3000, 5000)),
    DEMITASER(37, 24, "Demitaser", 1.0D, 19, MinMax.get(30, 50), false, MinMax.get(1, 1)),
    TOXISMASHER(38, 22, "Toxismasher", 1.8D, 14, MinMax.get(30, 60), true, MinMax.get(2000, 6500)),
    LONGPIKE(39, 25, "Longpike", 1.5D, 16, MinMax.get(35, 70), true, MinMax.get(1500, 2500));

    public static final int TOTAL = 40;
    public int id, skin, minLevel;
    public boolean isDamaging;
    public String name;
    public double chance;
    public MinMax damage, damaging;

    EnumSword(int id, int skin, String name, double chance, int minLevel, MinMax damage, boolean isDamaging, MinMax damaging) {
        this.id = id;
        this.skin = skin;
        this.name = name;
        this.chance = chance;
        this.minLevel = minLevel;
        this.damage = damage;
        this.isDamaging = isDamaging;
        this.damaging = damaging;
    }

    public static EnumSword getById(int id) {
        for (EnumSword sword : values()) {
            if (sword.id == id) return sword;
        }
        return null;
    }
}
