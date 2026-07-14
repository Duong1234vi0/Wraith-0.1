/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.D_38_f;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.h_384_L;
import lightning.product.BlockTags;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.y_4319_k;

public class r_1637_F
extends y_4319_k {
    private int n_1700_B = -1;

    public r_1637_F(t_5_h<? extends r_1637_F> type, b_4507_u world) {
        super(type, world);
    }

    public r_1637_F(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.g_2268_R, worldIn, x, y, z);
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.G_564_y;
    }

    @Override
    public K_4074_S M_182_A() {
        return a_3742_W.TextRenderingUtils.multiplayerClientSuggestionProvider();
    }

    @Override
    public void v_() {
        double d0;
        super.v_();
        if (this.n_1700_B > 0) {
            --this.n_1700_B;
            this.O_508_d.n_1700_B(ParticleTypes.B_1668_F, this.O_3598_v(), this.X_2960_b() + 0.5, this.l_2647_k(), 0.0, 0.0, 0.0);
        } else if (this.n_1700_B == 0) {
            this.w_1484_f(r_1637_F.R_4764_Y(this.I_4348_c()));
        }
        if (this.D_60_a && (d0 = r_1637_F.R_4764_Y(this.I_4348_c())) >= (double)0.01f) {
            this.w_1484_f(d0);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        h_384_L abstractarrowentity;
        N_4263_v entity = source.s_956_w();
        if (entity instanceof h_384_L && (abstractarrowentity = (h_384_L)entity).RealmsPersistence()) {
            this.w_1484_f(abstractarrowentity.I_4348_c().v_4262_N());
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public void J_1907_R(P_11_z source) {
        double d0 = r_1637_F.R_4764_Y(this.I_4348_c());
        if (!(source.M_182_A() || source.G_564_y() || d0 >= (double)0.01f)) {
            super.J_1907_R(source);
            if (!source.G_564_y() && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                this.n_1700_B(a_3742_W.TextRenderingUtils);
            }
        } else if (this.n_1700_B < 0) {
            this.Y_259_p();
            this.n_1700_B = this.RealmsWorldOptions.nextInt(20) + this.RealmsWorldOptions.nextInt(20);
        }
    }

    protected void w_1484_f(double radiusModifier) {
        if (!this.O_508_d.Y_259_p) {
            double d0 = Math.sqrt(radiusModifier);
            if (d0 > 5.0) {
                d0 = 5.0;
            }
            this.O_508_d.n_1700_B(this, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), (float)(4.0 + this.RealmsWorldOptions.nextDouble() * 1.5 * d0), F_1241_B.n_1700_B.J_1907_R);
            this.Ops();
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        if (distance >= 3.0f) {
            float f = distance / 10.0f;
            this.w_1484_f((double)(f * f));
        }
        return super.R_4764_Y(distance, damageMultiplier);
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean receivingPower) {
        if (receivingPower && this.n_1700_B < 0) {
            this.Y_259_p();
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 10) {
            this.Y_259_p();
        } else {
            super.n_1700_B(id);
        }
    }

    public void Y_259_p() {
        this.n_1700_B = 80;
        if (!this.O_508_d.Y_259_p) {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)10);
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.S_3458_C, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
        }
    }

    public int Q_2552_b() {
        return this.n_1700_B;
    }

    public boolean C_2741_M() {
        return this.n_1700_B > -1;
    }

    @Override
    public float n_1700_B(F_1241_B explosionIn, BlockGetter worldIn, c_1514_x pos, K_4074_S blockStateIn, FluidState fluidState, float explosionPower) {
        return !this.C_2741_M() || !blockStateIn.n_1700_B(BlockTags.n_3318_d) && !worldIn.getBlockState(pos.up()).n_1700_B(BlockTags.n_3318_d) ? super.n_1700_B(explosionIn, worldIn, pos, blockStateIn, fluidState, explosionPower) : 0.0f;
    }

    @Override
    public boolean n_1700_B(F_1241_B explosionIn, BlockGetter worldIn, c_1514_x pos, K_4074_S blockStateIn, float explosionPower) {
        return !this.C_2741_M() || !blockStateIn.n_1700_B(BlockTags.n_3318_d) && !worldIn.getBlockState(pos.up()).n_1700_B(BlockTags.n_3318_d) ? super.n_1700_B(explosionIn, worldIn, pos, blockStateIn, explosionPower) : false;
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("TNTFuse", 99)) {
            this.n_1700_B = compound.w_1484_f("TNTFuse");
        }
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("TNTFuse", this.n_1700_B);
    }
}


