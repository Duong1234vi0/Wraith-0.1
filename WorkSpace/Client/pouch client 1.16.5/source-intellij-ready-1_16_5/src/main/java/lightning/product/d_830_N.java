/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;
import lightning.product.c_1514_x;
import lightning.product.n_3832_I;

public class d_830_N {
    private final c_1514_x n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;

    public d_830_N(c_1514_x pos, int rotation, int entityId) {
        this.n_1700_B = pos;
        this.J_1907_R = rotation;
        this.R_4764_Y = entityId;
    }

    public static d_830_N n_1700_B(U_2912_j nbt) {
        c_1514_x blockpos = n_3832_I.J_1907_R(nbt.M_182_A("Pos"));
        int i = nbt.w_1484_f("Rotation");
        int j = nbt.w_1484_f("EntityId");
        return new d_830_N(blockpos, i, j);
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Pos", n_3832_I.n_1700_B(this.n_1700_B));
        compoundnbt.J_1907_R("Rotation", this.J_1907_R);
        compoundnbt.J_1907_R("EntityId", this.R_4764_Y);
        return compoundnbt;
    }

    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    public int R_4764_Y() {
        return this.J_1907_R;
    }

    public int G_564_y() {
        return this.R_4764_Y;
    }

    public String P_1922_E() {
        return d_830_N.n_1700_B(this.n_1700_B);
    }

    public static String n_1700_B(c_1514_x pos) {
        return "frame-" + pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }
}

