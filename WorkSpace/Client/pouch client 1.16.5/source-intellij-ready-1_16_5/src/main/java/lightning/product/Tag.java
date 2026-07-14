/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataOutput;
import java.io.IOException;
import lightning.product.D_4024_W;
import lightning.product.TagType;
import lightning.product.x_282_a;

public interface Tag {
    public static final D_4024_W G_564_y = D_4024_W.M_588_G;
    public static final D_4024_W P_1922_E = D_4024_W.u_2550_I;
    public static final D_4024_W u_1723_Y = D_4024_W.v_4262_N;
    public static final D_4024_W v_4262_N = D_4024_W.P_4830_p;

    public void n_1700_B(DataOutput var1) throws IOException;

    public String toString();

    public byte n_1700_B();

    public TagType<?> J_1907_R();

    public Tag R_4764_Y();

    default public String M_588_G() {
        return this.toString();
    }

    default public x_282_a P_4830_p() {
        return this.n_1700_B("", 0);
    }

    public x_282_a n_1700_B(String var1, int var2);
}


