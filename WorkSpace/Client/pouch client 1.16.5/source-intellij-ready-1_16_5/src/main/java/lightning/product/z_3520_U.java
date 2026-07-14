/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.CustomSpawner;
import lightning.product.F_4355_q;
import lightning.product.N_4263_v;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Monster;
import lightning.product.k_594_Q;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class z_3520_U
implements CustomSpawner {
    private static final Logger n_1700_B = LogManager.getLogger();
    private boolean J_1907_R;
    private n_1700_B R_4764_Y = lightning.product.z_3520_U$n_1700_B.R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;

    @Override
    public int n_1700_B(e_3591_l p_230253_1_, boolean p_230253_2_, boolean p_230253_3_) {
        if (!p_230253_1_.q_4610_l() && p_230253_2_) {
            float f = p_230253_1_.G_564_y(0.0f);
            if ((double)f == 0.5) {
                n_1700_B n_1700_B2 = this.R_4764_Y = p_230253_1_.w_1457_N.nextInt(10) == 0 ? lightning.product.z_3520_U$n_1700_B.J_1907_R : lightning.product.z_3520_U$n_1700_B.R_4764_Y;
            }
            if (this.R_4764_Y == lightning.product.z_3520_U$n_1700_B.R_4764_Y) {
                return 0;
            }
            if (!this.J_1907_R) {
                if (!this.n_1700_B(p_230253_1_)) {
                    return 0;
                }
                this.J_1907_R = true;
            }
            if (this.P_1922_E > 0) {
                --this.P_1922_E;
                return 0;
            }
            this.P_1922_E = 2;
            if (this.G_564_y > 0) {
                this.J_1907_R(p_230253_1_);
                --this.G_564_y;
            } else {
                this.R_4764_Y = lightning.product.z_3520_U$n_1700_B.R_4764_Y;
            }
            return 1;
        }
        this.R_4764_Y = lightning.product.z_3520_U$n_1700_B.R_4764_Y;
        this.J_1907_R = false;
        return 0;
    }

    private boolean n_1700_B(e_3591_l world) {
        for (a_3913_L a_3913_L2 : world.multiplayerClientSuggestionProvider()) {
            c_1514_x blockpos;
            if (a_3913_L2.d_2461_k() || !world.q_2307_F(blockpos = a_3913_L2.b_2312_j()) || world.P_1922_E(blockpos).Y_601_j() == k_594_Q.R_4764_Y.M_182_A) continue;
            for (int i = 0; i < 10; ++i) {
                float f = world.w_1457_N.nextFloat() * ((float)Math.PI * 2);
                this.u_1723_Y = blockpos.getX() + u_530_F.G_564_y(u_530_F.J_1907_R(f) * 32.0f);
                this.v_4262_N = blockpos.getY();
                this.w_1484_f = blockpos.getZ() + u_530_F.G_564_y(u_530_F.n_1700_B(f) * 32.0f);
                if (this.n_1700_B(world, new c_1514_x(this.u_1723_Y, this.v_4262_N, this.w_1484_f)) == null) continue;
                this.P_1922_E = 0;
                this.G_564_y = 20;
                break;
            }
            return true;
        }
        return false;
    }

    private void J_1907_R(e_3591_l world) {
        e_2866_D vector3d = this.n_1700_B(world, new c_1514_x(this.u_1723_Y, this.v_4262_N, this.w_1484_f));
        if (vector3d != null) {
            F_4355_q zombieentity;
            try {
                zombieentity = new F_4355_q(world);
                zombieentity.n_1700_B(world, world.J_1907_R(zombieentity.b_2312_j()), a_3160_D.w_1484_f, (V_3157_k)null, null);
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to create zombie for village siege at {}", (Object)vector3d, (Object)exception);
                return;
            }
            zombieentity.J_1907_R(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, world.w_1457_N.nextFloat() * 360.0f, 0.0f);
            world.n_1700_B((N_4263_v)zombieentity);
        }
    }

    @Nullable
    private e_2866_D n_1700_B(e_3591_l world, c_1514_x pos) {
        for (int i = 0; i < 10; ++i) {
            int k;
            int l;
            int j = pos.getX() + world.w_1457_N.nextInt(16) - 8;
            c_1514_x blockpos = new c_1514_x(j, l = world.n_1700_B(z_2963_s.n_1700_B.J_1907_R, j, k = pos.getZ() + world.w_1457_N.nextInt(16) - 8), k);
            if (!world.q_2307_F(blockpos) || !Monster.J_1907_R(t_5_h.R_3077_Z, world, a_3160_D.w_1484_f, blockpos, world.w_1457_N)) continue;
            return e_2866_D.R_4764_Y(blockpos);
        }
        return null;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.z_3520_U$n_1700_B.n_1700_B();
        }
    }
}


