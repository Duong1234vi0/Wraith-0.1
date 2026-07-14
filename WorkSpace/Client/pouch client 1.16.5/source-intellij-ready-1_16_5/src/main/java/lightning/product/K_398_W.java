/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.CustomSpawner;
import lightning.product.BlockGetter;
import lightning.product.F_4023_g;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.T_426_Y;
import lightning.product.biomeBiomes;
import lightning.product.a_3160_D;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelData;
import lightning.product.e_3591_l;
import lightning.product.TraderLlama;
import lightning.product.q_2232_A;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.z_2963_s;

public class K_398_W
implements CustomSpawner {
    private final Random n_1700_B = new Random();
    private final ServerLevelData J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;

    public K_398_W(ServerLevelData p_i231576_1_) {
        this.J_1907_R = p_i231576_1_;
        this.R_4764_Y = 1200;
        this.G_564_y = p_i231576_1_.Q_2552_b();
        this.P_1922_E = p_i231576_1_.C_2741_M();
        if (this.G_564_y == 0 && this.P_1922_E == 0) {
            this.G_564_y = 24000;
            p_i231576_1_.v_4262_N(this.G_564_y);
            this.P_1922_E = 25;
            p_i231576_1_.w_1484_f(this.P_1922_E);
        }
    }

    @Override
    public int n_1700_B(e_3591_l p_230253_1_, boolean p_230253_2_, boolean p_230253_3_) {
        if (!p_230253_1_.H_1990_U().J_1907_R(A_2352_Z.t_4043_B)) {
            return 0;
        }
        if (--this.R_4764_Y > 0) {
            return 0;
        }
        this.R_4764_Y = 1200;
        this.G_564_y -= 1200;
        this.J_1907_R.v_4262_N(this.G_564_y);
        if (this.G_564_y > 0) {
            return 0;
        }
        this.G_564_y = 24000;
        if (!p_230253_1_.H_1990_U().J_1907_R(A_2352_Z.G_564_y)) {
            return 0;
        }
        int i = this.P_1922_E;
        this.P_1922_E = u_530_F.n_1700_B(this.P_1922_E + 25, 25, 75);
        this.J_1907_R.w_1484_f(this.P_1922_E);
        if (this.n_1700_B.nextInt(100) > i) {
            return 0;
        }
        if (this.n_1700_B(p_230253_1_)) {
            this.P_1922_E = 25;
            return 1;
        }
        return 0;
    }

    private boolean n_1700_B(e_3591_l p_234562_1_) {
        B_4088_l playerentity = p_234562_1_.Y_601_j();
        if (playerentity == null) {
            return true;
        }
        if (this.n_1700_B.nextInt(10) != 0) {
            return false;
        }
        c_1514_x blockpos = playerentity.b_2312_j();
        int i = 48;
        b_4946_z pointofinterestmanager = p_234562_1_.p_178_J();
        Optional<c_1514_x> optional = pointofinterestmanager.R_4764_Y(q_2232_A.w_1457_N.J_1907_R(), p_221241_0_ -> true, blockpos, 48, b_4946_z.J_1907_R.R_4764_Y);
        c_1514_x blockpos1 = optional.orElse(blockpos);
        c_1514_x blockpos2 = this.n_1700_B((T_1316_M)p_234562_1_, blockpos1, 48);
        if (blockpos2 != null && this.n_1700_B(p_234562_1_, blockpos2)) {
            if (p_234562_1_.n_1700_B(blockpos2).equals(Optional.of(biomeBiomes.g_2268_R))) {
                return false;
            }
            T_426_Y wanderingtraderentity = t_5_h.u_744_e.n_1700_B(p_234562_1_, null, null, null, blockpos2, a_3160_D.w_1484_f, false, false);
            if (wanderingtraderentity != null) {
                for (int j = 0; j < 2; ++j) {
                    this.n_1700_B(p_234562_1_, wanderingtraderentity, 4);
                }
                this.J_1907_R.n_1700_B(wanderingtraderentity.w_2705_t());
                wanderingtraderentity.Y_601_j(48000);
                wanderingtraderentity.v_4262_N(blockpos1);
                wanderingtraderentity.n_1700_B(blockpos1, 16);
                return true;
            }
        }
        return false;
    }

    private void n_1700_B(e_3591_l p_242373_1_, T_426_Y p_242373_2_, int p_242373_3_) {
        TraderLlama traderllamaentity;
        c_1514_x blockpos = this.n_1700_B((T_1316_M)p_242373_1_, p_242373_2_.b_2312_j(), p_242373_3_);
        if (blockpos != null && (traderllamaentity = t_5_h.F_1410_V.n_1700_B(p_242373_1_, null, null, null, blockpos, a_3160_D.w_1484_f, false, false)) != null) {
            traderllamaentity.J_1907_R((N_4263_v)p_242373_2_, true);
        }
    }

    @Nullable
    private c_1514_x n_1700_B(T_1316_M p_234561_1_, c_1514_x p_234561_2_, int p_234561_3_) {
        c_1514_x blockpos = null;
        for (int i = 0; i < 10; ++i) {
            int k;
            int l;
            int j = p_234561_2_.getX() + this.n_1700_B.nextInt(p_234561_3_ * 2) - p_234561_3_;
            c_1514_x blockpos1 = new c_1514_x(j, l = p_234561_1_.n_1700_B(z_2963_s.n_1700_B.J_1907_R, j, k = p_234561_2_.getZ() + this.n_1700_B.nextInt(p_234561_3_ * 2) - p_234561_3_), k);
            if (!u_743_i.n_1700_B(F_4023_g.R_4764_Y.n_1700_B, p_234561_1_, blockpos1, t_5_h.u_744_e)) continue;
            blockpos = blockpos1;
            break;
        }
        return blockpos;
    }

    private boolean n_1700_B(BlockGetter p_234560_1_, c_1514_x p_234560_2_) {
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_234560_2_, p_234560_2_.add(1, 2, 1))) {
            if (p_234560_1_.getBlockState(blockpos).u_2550_I(p_234560_1_, blockpos).J_1907_R()) continue;
            return false;
        }
        return true;
    }
}


