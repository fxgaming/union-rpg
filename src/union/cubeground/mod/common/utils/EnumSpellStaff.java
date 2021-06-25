package union.cubeground.mod.common.utils;

public enum EnumSpellStaff {
	;

	public static final int TOTAL = 0;
	public int id, color, minLevel, manaUse, manaPerSec;
	public boolean isDamaging;
	public String name;
	public double chance;
	public MinMax damage, damaging;

	EnumSpellStaff(int id, String name, int color, double chance, int minLevel, int manaUse, int manaPerSec, MinMax damage, boolean isDamaging, MinMax damaging) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.chance = chance;
		this.manaUse = manaUse;
		this.manaPerSec = manaPerSec;
		this.minLevel = minLevel;
		this.damage = damage;
		this.isDamaging = isDamaging;
		this.damaging = damaging;
	}

	public static EnumSpellStaff getById(int id) {
		for (EnumSpellStaff staff : values()) {
			if (staff.id == id)
				return staff;
		}
		return null;
	}

	public static enum Effects {
		PLANT(0.5D, 0.0D, true),
		SUN(1.0D, 180.0D, true),
		WATER(2.5D, 0.0D, true),
		RING(0.5D, 0.0D, false),
		CRYSTAL(1.0D, 45.0D, false),
		BONE(0.0D, 0.0D, true),
		
		;
		
		public double knockback, rotateDeg;
		public boolean isConnecting;

		Effects(double knockback, double rotateDeg, boolean isConnecting) {

		}
		
		public static Effects getById(int id) {
			for (Effects effect : values()) {
				if (effect.ordinal() == id)
					return effect;
			}
			return null;
		}
	}
}
