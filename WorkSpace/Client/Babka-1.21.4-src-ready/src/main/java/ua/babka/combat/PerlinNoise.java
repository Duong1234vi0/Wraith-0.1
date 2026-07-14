package ua.babka.combat;

public class PerlinNoise {
    private final long seed = System.nanoTime();

    public double noise(double x2) {
        int xi = (int)Math.floor(x2);
        double xf = x2 - (double)xi;
        xf = this.smoothstep(xf);
        return this.lerp(this.hash(xi, 0), this.hash(xi + 1, 0), xf);
    }

    public double noise(double x2, double y2) {
        int xi = (int)Math.floor(x2);
        int yi = (int)Math.floor(y2);
        double xf = x2 - (double)xi;
        double yf = y2 - (double)yi;
        xf = this.smoothstep(xf);
        yf = this.smoothstep(yf);
        double v1 = this.hash(xi, yi);
        double v2 = this.hash(xi + 1, yi);
        double v3 = this.hash(xi, yi + 1);
        double v4 = this.hash(xi + 1, yi + 1);
        return this.lerp(this.lerp(v1, v2, xf), this.lerp(v3, v4, xf), yf);
    }

    private double hash(int x2, int y2) {
        long n2 = this.seed + (long)x2 * 374761393L + (long)y2 * 668265263L;
        n2 = (n2 ^ n2 >> 13) * 1274126177L;
        n2 ^= n2 >> 16;
        return (double)((n2 & Long.MAX_VALUE) % 2000000L) / 1000000.0 - 1.0;
    }

    private double smoothstep(double t2) {
        return t2 * t2 * (3.0 - 2.0 * t2);
    }

    private double lerp(double a2, double b2, double t2) {
        return a2 + (b2 - a2) * t2;
    }
}

