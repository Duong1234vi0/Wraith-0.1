/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.R_2450_T;
import lightning.product.CrashReportCategory;

public interface LevelData {
    public int J_1907_R();

    public int R_4764_Y();

    public int G_564_y();

    public float w_1484_f();

    public long P_1922_E();

    public long u_1723_Y();

    public boolean t_148_a();

    public boolean v_4262_N();

    public void n_1700_B(boolean var1);

    public boolean n_1700_B();

    public A_2352_Z s_956_w();

    public R_2450_T u_2550_I();

    public boolean M_588_G();

    default public void n_1700_B(CrashReportCategory category) {
        category.n_1700_B("Level spawn location", () -> CrashReportCategory.n_1700_B(this.J_1907_R(), this.R_4764_Y(), this.G_564_y()));
        category.n_1700_B("Level time", () -> String.format("%d game time, %d day time", this.P_1922_E(), this.u_1723_Y()));
    }
}


