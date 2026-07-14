/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import lightning.product.g_2336_b;

public class d_1062_x
extends g_2336_b {
    private final String G_564_y;

    protected d_1062_x(String[] resourceParts) {
        super(resourceParts);
        this.G_564_y = resourceParts[2].toLowerCase(Locale.ROOT);
    }

    public d_1062_x(String pathIn) {
        this(d_1062_x.n_1700_B(pathIn));
    }

    public d_1062_x(g_2336_b location, String variantIn) {
        this(location.toString(), variantIn);
    }

    public d_1062_x(String location, String variantIn) {
        this(d_1062_x.n_1700_B(location + "#" + variantIn));
    }

    protected static String[] n_1700_B(String pathIn) {
        String[] astring = new String[]{null, pathIn, ""};
        int i = pathIn.indexOf(35);
        String s = pathIn;
        if (i >= 0) {
            astring[2] = pathIn.substring(i + 1, pathIn.length());
            if (i > 1) {
                s = pathIn.substring(0, i);
            }
        }
        System.arraycopy(g_2336_b.J_1907_R(s, ':'), 0, astring, 0, 2);
        return astring;
    }

    public String n_1700_B() {
        return this.G_564_y;
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof d_1062_x && super.equals(p_equals_1_)) {
            d_1062_x modelresourcelocation = (d_1062_x)p_equals_1_;
            return this.G_564_y.equals(modelresourcelocation.G_564_y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + this.G_564_y.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + "#" + this.G_564_y;
    }
}

