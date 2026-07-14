/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.bridge.game.Language
 */
package lightning.product;

import com.mojang.bridge.game.Language;

public class b_164_E
implements Language,
Comparable<b_164_E> {
    private final String n_1700_B;
    private final String J_1907_R;
    private final String R_4764_Y;
    private final boolean G_564_y;

    public b_164_E(String languageCodeIn, String regionIn, String nameIn, boolean bidirectionalIn) {
        this.n_1700_B = languageCodeIn;
        this.J_1907_R = regionIn;
        this.R_4764_Y = nameIn;
        this.G_564_y = bidirectionalIn;
    }

    public String getCode() {
        return this.n_1700_B;
    }

    public String getName() {
        return this.R_4764_Y;
    }

    public String getRegion() {
        return this.J_1907_R;
    }

    public boolean n_1700_B() {
        return this.G_564_y;
    }

    public String toString() {
        return String.format("%s (%s)", this.R_4764_Y, this.J_1907_R);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        return !(p_equals_1_ instanceof b_164_E) ? false : this.n_1700_B.equals(((b_164_E)p_equals_1_).n_1700_B);
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    public int n_1700_B(b_164_E p_compareTo_1_) {
        return this.n_1700_B.compareTo(p_compareTo_1_.n_1700_B);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((b_164_E)object);
    }
}

