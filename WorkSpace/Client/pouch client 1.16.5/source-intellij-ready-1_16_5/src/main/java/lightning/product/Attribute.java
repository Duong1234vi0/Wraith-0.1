/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class Attribute {
    private final double n_1700_B;
    private boolean J_1907_R;
    private final String R_4764_Y;

    protected Attribute(String attributeName, double defaultValue) {
        this.n_1700_B = defaultValue;
        this.R_4764_Y = attributeName;
    }

    public double n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public Attribute n_1700_B(boolean watch) {
        this.J_1907_R = watch;
        return this;
    }

    public double n_1700_B(double value) {
        return value;
    }

    public String R_4764_Y() {
        return this.R_4764_Y;
    }
}


