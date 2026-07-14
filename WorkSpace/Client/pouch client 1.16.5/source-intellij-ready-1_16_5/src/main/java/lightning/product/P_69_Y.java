/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.H_3330_w;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Recipe;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;

public class P_69_Y {
    private Recipe<?> n_1700_B;
    private final List<n_1700_B> J_1907_R = Lists.newArrayList();
    private float R_4764_Y;

    public void n_1700_B() {
        this.n_1700_B = null;
        this.J_1907_R.clear();
        this.R_4764_Y = 0.0f;
    }

    public void n_1700_B(b_3278_X p_194187_1_, int p_194187_2_, int p_194187_3_) {
        this.J_1907_R.add(new n_1700_B(p_194187_1_, p_194187_2_, p_194187_3_));
    }

    public n_1700_B n_1700_B(int p_192681_1_) {
        return this.J_1907_R.get(p_192681_1_);
    }

    public int J_1907_R() {
        return this.J_1907_R.size();
    }

    @Nullable
    public Recipe<?> R_4764_Y() {
        return this.n_1700_B;
    }

    public void n_1700_B(Recipe<?> p_192685_1_) {
        this.n_1700_B = p_192685_1_;
    }

    public void n_1700_B(g_221_o p_238922_1_, MinecraftClient p_238922_2_, int p_238922_3_, int p_238922_4_, boolean p_238922_5_, float p_238922_6_) {
        if (!k_2603_m.hasControlDown()) {
            this.R_4764_Y += p_238922_6_;
        }
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            n_1700_B ghostrecipe$ghostingredient = this.J_1907_R.get(i);
            int j = ghostrecipe$ghostingredient.n_1700_B() + p_238922_3_;
            int k = ghostrecipe$ghostingredient.J_1907_R() + p_238922_4_;
            if (i == 0 && p_238922_5_) {
                C_2701_A.fill(p_238922_1_, j - 4, k - 4, j + 20, k + 20, 0x30FF0000);
            } else {
                C_2701_A.fill(p_238922_1_, j, k, j + 16, k + 16, 0x30FF0000);
            }
            Z_1993_T itemstack = ghostrecipe$ghostingredient.R_4764_Y();
            H_3330_w itemrenderer = p_238922_2_.r_715_M();
            itemrenderer.R_4764_Y(itemstack, j, k);
            c_4037_x.J_1907_R(516);
            C_2701_A.fill(p_238922_1_, j, k, j + 16, k + 16, 0x30FFFFFF);
            c_4037_x.J_1907_R(515);
            if (i != 0) continue;
            itemrenderer.n_1700_B(p_238922_2_.t_148_a, itemstack, j, k);
        }
    }

    public class n_1700_B {
        private final b_3278_X J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(b_3278_X p_i47604_2_, int p_i47604_3_, int p_i47604_4_) {
            this.J_1907_R = p_i47604_2_;
            this.R_4764_Y = p_i47604_3_;
            this.G_564_y = p_i47604_4_;
        }

        public int n_1700_B() {
            return this.R_4764_Y;
        }

        public int J_1907_R() {
            return this.G_564_y;
        }

        public Z_1993_T R_4764_Y() {
            Z_1993_T[] aitemstack = this.J_1907_R.n_1700_B();
            return aitemstack[u_530_F.G_564_y(P_69_Y.this.R_4764_Y / 30.0f) % aitemstack.length];
        }
    }
}



