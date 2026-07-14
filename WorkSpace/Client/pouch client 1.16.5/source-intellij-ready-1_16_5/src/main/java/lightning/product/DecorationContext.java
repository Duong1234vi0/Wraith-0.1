/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.BitSet;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_3975_o;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.n_1254_X;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class DecorationContext {
    private final WorldGenLevel n_1700_B;
    private final z_1753_f J_1907_R;

    public DecorationContext(WorldGenLevel p_i242021_1_, z_1753_f p_i242021_2_) {
        this.n_1700_B = p_i242021_1_;
        this.J_1907_R = p_i242021_2_;
    }

    public int n_1700_B(z_2963_s.n_1700_B p_242893_1_, int p_242893_2_, int p_242893_3_) {
        return this.n_1700_B.n_1700_B(p_242893_1_, p_242893_2_, p_242893_3_);
    }

    public int n_1700_B() {
        return this.J_1907_R.P_1922_E();
    }

    public int J_1907_R() {
        return this.J_1907_R.u_1723_Y();
    }

    public BitSet n_1700_B(Y_1387_d p_242892_1_, T_3975_o.n_1700_B p_242892_2_) {
        return ((n_1254_X)this.n_1700_B.P_1922_E(p_242892_1_.J_1907_R, p_242892_1_.R_4764_Y)).J_1907_R(p_242892_2_);
    }

    public K_4074_S n_1700_B(c_1514_x p_242894_1_) {
        return this.n_1700_B.getBlockState(p_242894_1_);
    }
}


