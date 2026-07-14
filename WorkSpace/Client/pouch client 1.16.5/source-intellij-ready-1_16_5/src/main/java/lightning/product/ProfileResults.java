/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.util.List;
import lightning.product.ResultField;

public interface ProfileResults {
    public List<ResultField> n_1700_B(String var1);

    public boolean n_1700_B(File var1);

    public long n_1700_B();

    public int J_1907_R();

    public long R_4764_Y();

    public int G_564_y();

    default public long u_1723_Y() {
        return this.R_4764_Y() - this.n_1700_B();
    }

    default public int P_1922_E() {
        return this.G_564_y() - this.J_1907_R();
    }

    public static String J_1907_R(String p_225434_0_) {
        return p_225434_0_.replace('\u001e', '.');
    }
}


