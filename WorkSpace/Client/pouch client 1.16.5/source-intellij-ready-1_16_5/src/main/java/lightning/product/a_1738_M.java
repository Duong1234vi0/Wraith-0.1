/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.CustomSpawner;
import lightning.product.F_4023_g;
import lightning.product.StructureFeature;
import lightning.product.I_4817_s;
import lightning.product.K_550_M;
import lightning.product.N_4263_v;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_2232_A;
import lightning.product.t_5_h;
import lightning.product.u_743_i;

public class a_1738_M
implements CustomSpawner {
    private int n_1700_B;

    @Override
    public int n_1700_B(e_3591_l p_230253_1_, boolean p_230253_2_, boolean p_230253_3_) {
        if (p_230253_3_ && p_230253_1_.H_1990_U().J_1907_R(A_2352_Z.G_564_y)) {
            --this.n_1700_B;
            if (this.n_1700_B > 0) {
                return 0;
            }
            this.n_1700_B = 1200;
            B_4088_l playerentity = p_230253_1_.Y_601_j();
            if (playerentity == null) {
                return 0;
            }
            Random random = p_230253_1_.w_1457_N;
            int i = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
            int j = (8 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
            c_1514_x blockpos = playerentity.b_2312_j().add(i, 0, j);
            if (!p_230253_1_.n_1700_B(blockpos.getX() - 10, blockpos.getY() - 10, blockpos.getZ() - 10, blockpos.getX() + 10, blockpos.getY() + 10, blockpos.getZ() + 10)) {
                return 0;
            }
            if (u_743_i.n_1700_B(F_4023_g.R_4764_Y.n_1700_B, p_230253_1_, blockpos, t_5_h.w_1484_f)) {
                if (p_230253_1_.R_4764_Y(blockpos, 2)) {
                    return this.n_1700_B(p_230253_1_, blockpos);
                }
                if (p_230253_1_.R_4764_Y().n_1700_B(blockpos, true, StructureFeature.s_956_w).P_1922_E()) {
                    return this.J_1907_R(p_230253_1_, blockpos);
                }
            }
            return 0;
        }
        return 0;
    }

    private int n_1700_B(e_3591_l worldIn, c_1514_x p_221121_2_) {
        List<K_550_M> list;
        int i = 48;
        if (worldIn.p_178_J().n_1700_B(q_2232_A.multiplayerClientSuggestionProvider.J_1907_R(), p_221121_2_, 48, b_4946_z.J_1907_R.J_1907_R) > 4L && (list = worldIn.n_1700_B(K_550_M.class, new I_4817_s(p_221121_2_).grow(48.0, 8.0, 48.0))).size() < 5) {
            return this.n_1700_B(p_221121_2_, worldIn);
        }
        return 0;
    }

    private int J_1907_R(e_3591_l worldIn, c_1514_x pos) {
        int i = 16;
        List<K_550_M> list = worldIn.n_1700_B(K_550_M.class, new I_4817_s(pos).grow(16.0, 8.0, 16.0));
        return list.size() < 1 ? this.n_1700_B(pos, worldIn) : 0;
    }

    private int n_1700_B(c_1514_x pos, e_3591_l worldIn) {
        K_550_M catentity = t_5_h.w_1484_f.n_1700_B(worldIn);
        if (catentity == null) {
            return 0;
        }
        catentity.n_1700_B(worldIn, worldIn.J_1907_R(pos), a_3160_D.n_1700_B, (V_3157_k)null, null);
        catentity.n_1700_B(pos, 0.0f, 0.0f);
        worldIn.n_1700_B((N_4263_v)catentity);
        return 1;
    }
}


