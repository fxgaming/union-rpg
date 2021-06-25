package union.cubeground.mod.common.utils;

public enum EnumFish {
	ABBOTINE("Абботина", DMinMax.get(0.07, 0.13), DMinMax.get(0.010, 0.030), null, null, 0.0D, null, false),
	AGAMIXIS("Агамиксис", DMinMax.get(0.08, 0.15), DMinMax.get(0.010, 0.030), null, null, 0.0D, null, false),
	
	
	;
	
	public String name;
	public DMinMax length;
	public DMinMax weight;
	public DMinMax price;
	public DMinMax depth;
	public double chance;
	public EnumBait[] baits;
	public boolean enabled;
	
	EnumFish(String name, DMinMax length, DMinMax weight, DMinMax price, DMinMax depth, double chance, EnumBait[] baits, boolean enabled) {
		this.name = name;
		this.length = length;
		this.weight = weight;
		this.price = price;
		this.depth = depth;
		this.chance = chance;
		this.baits = baits;
		this.enabled = enabled;
	}
	
	EnumBait[] baits(int... ids) {
		EnumBait[] baits = new EnumBait[ids.length];
		for (int i = 0; i != ids.length; i++) {
			baits[i] = EnumBait.getById(ids[i]);
		}
		return baits;
	}
}
