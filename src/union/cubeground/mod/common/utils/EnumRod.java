package union.cubeground.mod.common.utils;

public class EnumRod {
	public static enum Coil {
		
		;
		
		public String name;
		public DMinMax resistance;
		public DMinMax diameter;
		public DMinMax stretchability;
		public DMinMax grinding;
		public DMinMax maxKG;
		
		Coil(String name, DMinMax resistance, DMinMax diameter, DMinMax stretchability, DMinMax grinding, DMinMax maxKG) {
			this.name = name;
			this.resistance = resistance;
			this.diameter = diameter;
			this.stretchability = stretchability;
			this.grinding = grinding;
			this.maxKG = maxKG;
		}
	}
	
	public static enum Rod {
		BERKLEY("Berkley", DMinMax.get(1.0D, 10.0D)),
		ENTSPORT_E("Entsport E-Series", DMinMax.get(1.0D, 11.0D)),
		ABU_GARCIA_VENGEANCE("Abu Garcia Vengeance", DMinMax.get(2.0D, 12.0D)),
		ZEBCO("Zebco", DMinMax.get(2.0D, 13.0D)),
		UGLY_STICK("Ugly Stick", DMinMax.get(3.0D, 14.0D)),
		FIBLINK("Fiblink", DMinMax.get(4.0D, 15.0D)),
		WAKEMAN_STRIKE("Wakeman Strike", DMinMax.get(5.0D, 15.0D)),
		DENDALY_PRIME("Dendaly Prime", DMinMax.get(5.0D, 16.0D)),
		TEMPLE_FORK("Temple Fork", DMinMax.get(6.0D, 17.0D)),
		G_LOOMIS_GLX_ESCAPE("G.Loomis GLX Escape", DMinMax.get(6.5D, 17.0D)),
		LAMIGLAS_SUPERSURF2G("Lamiglas Supersurf 2G", DMinMax.get(7.5D, 20.0D))
		;
		
		public String name;
		public DMinMax flexLength;
		
		Rod(String name, DMinMax flexLength) {
			this.name = name;
			this.flexLength = flexLength;
		}
	}
	
	public static enum Bobber {
		W1_1(DMinMax.get(0.75D, 1.25D), DMinMax.get(0.75, 1.25D)),
		W1_3(DMinMax.get(0.75D, 1.25D), DMinMax.get(2.75D, 3.25D)),
		W1_5(DMinMax.get(0.75D, 1.25D), DMinMax.get(4.75, 5.25D)),
		W2_1(DMinMax.get(1.75D, 2.25D), DMinMax.get(0.75, 1.25D)),
		W2_3(DMinMax.get(1.75D, 2.25D), DMinMax.get(2.75D, 3.25D)),
		W2_5(DMinMax.get(1.75D, 2.25D), DMinMax.get(4.75, 5.25D)),
		;
		
		public DMinMax velocity;
		public DMinMax sensitivity;
		
		Bobber(DMinMax velocity, DMinMax sensitivity) {
			this.velocity = velocity;
			this.sensitivity = sensitivity;
		}
	}
	
	public static enum Hook {
		
		;
		
		public DMinMax width;
		public DMinMax length;
		public DMinMax pivotThickness;
		
		
		Hook(DMinMax width, DMinMax length, DMinMax pivotThickness) {
			this.width = width;
			this.length = length;
			this.pivotThickness = pivotThickness;
		}
	}
	
	public static enum Sinker {
		L05(DMinMax.get(0.04, 0.06)),
		L1(DMinMax.get(0.08, 0.12)),
		L15(DMinMax.get(0.14, 0.16)),
		L2(DMinMax.get(0.18, 0.22)),
		L25(DMinMax.get(0.24, 0.26)),
		L3(DMinMax.get(0.28, 0.32)),
		L35(DMinMax.get(0.34, 0.36)),
		L4(DMinMax.get(0.38, 0.42)),
		L45(DMinMax.get(0.44, 0.46)),
		L5(DMinMax.get(0.48, 0.52)),
		L6(DMinMax.get(0.58, 0.62)),
		L7(DMinMax.get(0.68, 0.72)),
		L8(DMinMax.get(0.78, 0.82)),
		L9(DMinMax.get(0.88, 0.92)),
		L100(DMinMax.get(0.95, 1.05)),
		L150(DMinMax.get(1.45, 1.55)),
		L200(DMinMax.get(1.95, 2.05)),
		L250(DMinMax.get(2.45, 2.55)),
		L300(DMinMax.get(2.95, 3.05)),
		;
		
		public DMinMax weight;
		
		Sinker(DMinMax weight) {
			this.weight = weight;
		}
	}
}
