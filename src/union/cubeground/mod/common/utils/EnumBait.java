package union.cubeground.mod.common.utils;

public enum EnumBait {
	RAIN_WORM("Дождевой червь", 0),
	MAGGOT_WORM("Опарыш", 1),
	BLOODWORM("Мотыль", 0),
	COCKCHAFER("Майский жук", 2),
	RHINOCEROS_BEETLE("Жук носорог", 2),
	SEMOLINA("Манная каша", 3),
	BREAD("Хлеб", 4),
	PORRIDGE("Каша", 3),
	PEAS("Горох", 5),
	HERCULES("Геркулес", 3),
	GREENS("Зелень", 5),
	CORN("Кукуруза", 6),
	
	SPINNER("Блесна", 7),
	POPPER("Поппер", 8),
	PROPPER("Проппер", 8),
	JERKBAIT_GLIDER("Джеркбейт-глайдер", 8),
	JERKBAIT_DIVER("Джеркбейт-дайвер", 8),
	STICKBAIT("Стикбейт", 9),
	RATTLIN("Раттлин", 8),
	WOBBLER("Воблер", 8),
	VIBROTAIL("Виброхвост", 10)
	;
	
	public String name;
	public int color;
	
	EnumBait(String name, int color) {
		this.name = name;
		this.color = color;
	}
	
	public static EnumBait getById(int id) {
		for (EnumBait bait : values()) {
			if (bait.ordinal() == id) return bait;
		}
		return null;
	}
}
