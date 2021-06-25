package union.cubeground.mod.common.utils;

public class MinMax {
    public int min;
    public int max;

    private MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static MinMax get(int min, int max) {
    	if (min > max) throw new UnsupportedOperationException("Minimal value bigger than maximum.");
        return new MinMax(min, max);
    }
}
