/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public interface PackSource {
    public static final PackSource n_1700_B = PackSource.n_1700_B();
    public static final PackSource J_1907_R = PackSource.n_1700_B("pack.source.builtin");
    public static final PackSource R_4764_Y = PackSource.n_1700_B("pack.source.world");
    public static final PackSource G_564_y = PackSource.n_1700_B("pack.source.server");

    public x_282_a decorate(x_282_a var1);

    public static PackSource n_1700_B() {
        return name -> name;
    }

    public static PackSource n_1700_B(String source) {
        F_2904_S itextcomponent = new F_2904_S(source);
        return name -> new F_2904_S("pack.nameAndSource", name, itextcomponent).n_1700_B(D_4024_W.w_1484_f);
    }
}


