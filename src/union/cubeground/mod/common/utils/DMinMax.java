package union.cubeground.mod.common.utils;

public class DMinMax {
    public double min;
    public double max;

    private DMinMax(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public static DMinMax get(double min, double max) {
    	if (min > max) throw new UnsupportedOperationException("Minimal value bigger than maximum.");
        return new DMinMax(min, max);
    }
}
