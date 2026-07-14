/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.I_4817_s;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.R_2450_T;
import lightning.product.BaseFireBlock;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Packet;
import lightning.product.t_5_h;

public class LightningBolt
extends N_4263_v {
    private int J_1907_R;
    public long n_1700_B;
    private int R_4764_Y;
    private boolean G_564_y;
    @Nullable
    private B_4088_l P_1922_E;

    public LightningBolt(t_5_h<? extends LightningBolt> p_i231491_1_, b_4507_u world) {
        super(p_i231491_1_, world);
        this.RowButton = true;
        this.J_1907_R = 2;
        this.n_1700_B = this.RealmsWorldOptions.nextLong();
        this.R_4764_Y = this.RealmsWorldOptions.nextInt(3) + 1;
    }

    public void n_1700_B(boolean effectOnly) {
        this.G_564_y = effectOnly;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.G_564_y;
    }

    public void G_564_y(@Nullable B_4088_l casterIn) {
        this.P_1922_E = casterIn;
    }

    @Override
    public void v_() {
        super.v_();
        if (this.J_1907_R == 2) {
            R_2450_T difficulty = this.O_508_d.x_607_J();
            if (difficulty == R_2450_T.R_4764_Y || difficulty == R_2450_T.G_564_y) {
                this.n_1700_B(4);
            }
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.ScoreboardHealth, D_38_f.G_564_y, 10000.0f, 0.8f + this.RealmsWorldOptions.nextFloat() * 0.2f);
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.SRPSpoof, D_38_f.G_564_y, 2.0f, 0.5f + this.RealmsWorldOptions.nextFloat() * 0.2f);
        }
        --this.J_1907_R;
        if (this.J_1907_R < 0) {
            if (this.R_4764_Y == 0) {
                this.Ops();
            } else if (this.J_1907_R < -this.RealmsWorldOptions.nextInt(10)) {
                --this.R_4764_Y;
                this.J_1907_R = 1;
                this.n_1700_B = this.RealmsWorldOptions.nextLong();
                this.n_1700_B(0);
            }
        }
        if (this.J_1907_R >= 0) {
            if (!(this.O_508_d instanceof e_3591_l)) {
                this.O_508_d.R_4764_Y(2);
            } else if (!this.G_564_y) {
                double d0 = 3.0;
                List<N_4263_v> list = this.O_508_d.J_1907_R((N_4263_v)this, new I_4817_s(this.O_3598_v() - 3.0, this.X_2960_b() - 3.0, this.l_2647_k() - 3.0, this.O_3598_v() + 3.0, this.X_2960_b() + 6.0 + 3.0, this.l_2647_k() + 3.0), N_4263_v::RealmsLongRunningMcoTaskScreen);
                for (N_4263_v entity : list) {
                    entity.n_1700_B((e_3591_l)this.O_508_d, this);
                }
                if (this.P_1922_E != null) {
                    U_3554_Q.t_4043_B.n_1700_B(this.P_1922_E, list);
                }
            }
        }
    }

    private void n_1700_B(int extraIgnitions) {
        if (!this.G_564_y && !this.O_508_d.Y_259_p && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.n_1700_B)) {
            c_1514_x blockpos = this.b_2312_j();
            K_4074_S blockstate = BaseFireBlock.n_1700_B(this.O_508_d, blockpos);
            if (this.O_508_d.getBlockState(blockpos).v_4262_N() && blockstate.n_1700_B((T_1316_M)this.O_508_d, blockpos)) {
                this.O_508_d.J_1907_R(blockpos, blockstate);
            }
            for (int i = 0; i < extraIgnitions; ++i) {
                c_1514_x blockpos1 = blockpos.add(this.RealmsWorldOptions.nextInt(3) - 1, this.RealmsWorldOptions.nextInt(3) - 1, this.RealmsWorldOptions.nextInt(3) - 1);
                blockstate = BaseFireBlock.n_1700_B(this.O_508_d, blockpos1);
                if (!this.O_508_d.getBlockState(blockpos1).v_4262_N() || !blockstate.n_1700_B((T_1316_M)this.O_508_d, blockpos1)) continue;
                this.O_508_d.J_1907_R(blockpos1, blockstate);
            }
        }
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = 64.0 * LightningBolt.S_3139_t();
        return distance < d0 * d0;
    }

    @Override
    protected void a_() {
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }
}



