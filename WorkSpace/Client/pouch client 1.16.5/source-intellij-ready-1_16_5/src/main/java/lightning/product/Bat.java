/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.K_4074_S;
import lightning.product.AmbientCreature;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;

public class Bat
extends AmbientCreature {
    private static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(Bat.class, EntityDataSerializers.n_1700_B);
    private static final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(4.0).J_1907_R();
    private c_1514_x R_4764_Y;

    public Bat(t_5_h<? extends Bat> type, b_4507_u worldIn) {
        super((t_5_h<? extends AmbientCreature>)type, worldIn);
        this.w_1457_N(true);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
    }

    @Override
    protected float d_4500_Q() {
        return 0.1f;
    }

    @Override
    protected float O_2761_o() {
        return super.O_2761_o() * 0.95f;
    }

    @Override
    @Nullable
    public SoundEvent z_4693_k() {
        return this.w_1484_f() && this.RealmsWorldOptions.nextInt(4) != 0 ? null : SoundEvents.e_1992_r;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.k_3961_g;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.D_60_a;
    }

    @Override
    public boolean w_728_N() {
        return false;
    }

    @Override
    protected void Z_875_P(N_4263_v entityIn) {
    }

    @Override
    protected void F_391_H() {
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 6.0);
    }

    public boolean w_1484_f() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 1) != 0;
    }

    public void w_1457_N(boolean isHanging) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        if (isHanging) {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 | 1));
        } else {
            this.l_4537_E.J_1907_R(n_1700_B, (byte)(b0 & 0xFFFFFFFE));
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.w_1484_f()) {
            this.v_4262_N(e_2866_D.n_1700_B);
            this.Q_4569_t(this.O_3598_v(), (double)u_530_F.R_4764_Y(this.X_2960_b()) + 1.0 - (double)this.v_165_F(), this.l_2647_k());
        } else {
            this.v_4262_N(this.I_4348_c().G_564_y(1.0, 0.6, 1.0));
        }
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
        c_1514_x blockpos = this.b_2312_j();
        c_1514_x blockpos1 = blockpos.up();
        if (this.w_1484_f()) {
            boolean flag = this.y_1700_S();
            if (this.O_508_d.getBlockState(blockpos1).v_4262_N(this.O_508_d, blockpos)) {
                if (this.RealmsWorldOptions.nextInt(200) == 0) {
                    this.f_3449_S = this.RealmsWorldOptions.nextInt(360);
                }
                if (this.O_508_d.n_1700_B(J_1907_R, this) != null) {
                    this.w_1457_N(false);
                    if (!flag) {
                        this.O_508_d.n_1700_B((a_3913_L)null, 1025, blockpos, 0);
                    }
                }
            } else {
                this.w_1457_N(false);
                if (!flag) {
                    this.O_508_d.n_1700_B((a_3913_L)null, 1025, blockpos, 0);
                }
            }
        } else {
            if (!(this.R_4764_Y == null || this.O_508_d.u_1723_Y(this.R_4764_Y) && this.R_4764_Y.getY() >= 1)) {
                this.R_4764_Y = null;
            }
            if (this.R_4764_Y == null || this.RealmsWorldOptions.nextInt(30) == 0 || this.R_4764_Y.withinDistance(this.s_4990_V(), 2.0)) {
                this.R_4764_Y = new c_1514_x(this.O_3598_v() + (double)this.RealmsWorldOptions.nextInt(7) - (double)this.RealmsWorldOptions.nextInt(7), this.X_2960_b() + (double)this.RealmsWorldOptions.nextInt(6) - 2.0, this.l_2647_k() + (double)this.RealmsWorldOptions.nextInt(7) - (double)this.RealmsWorldOptions.nextInt(7));
            }
            double d2 = (double)this.R_4764_Y.getX() + 0.5 - this.O_3598_v();
            double d0 = (double)this.R_4764_Y.getY() + 0.1 - this.X_2960_b();
            double d1 = (double)this.R_4764_Y.getZ() + 0.5 - this.l_2647_k();
            e_2866_D vector3d = this.I_4348_c();
            e_2866_D vector3d1 = vector3d.J_1907_R((Math.signum(d2) * 0.5 - vector3d.J_1907_R) * (double)0.1f, (Math.signum(d0) * (double)0.7f - vector3d.R_4764_Y) * (double)0.1f, (Math.signum(d1) * 0.5 - vector3d.G_564_y) * (double)0.1f);
            this.v_4262_N(vector3d1);
            float f = (float)(u_530_F.G_564_y(vector3d1.G_564_y, vector3d1.J_1907_R) * 57.2957763671875) - 90.0f;
            float f1 = u_530_F.v_4262_N(f - this.p_178_J);
            this.L_4248_u = 0.5f;
            this.p_178_J += f1;
            if (this.RealmsWorldOptions.nextInt(100) == 0 && this.O_508_d.getBlockState(blockpos1).v_4262_N(this.O_508_d, blockpos1)) {
                this.w_1457_N(true);
            }
        }
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
    }

    @Override
    public boolean P_2947_S() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (!this.O_508_d.Y_259_p && this.w_1484_f()) {
            this.w_1457_N(false);
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.l_4537_E.J_1907_R(n_1700_B, compound.u_1723_Y("BatFlags"));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("BatFlags", this.l_4537_E.n_1700_B(n_1700_B));
    }

    public static boolean J_1907_R(t_5_h<Bat> batIn, LevelAccessor worldIn, a_3160_D reason, c_1514_x pos, Random randomIn) {
        if (pos.getY() >= worldIn.d_2461_k()) {
            return false;
        }
        int i = worldIn.u_2550_I(pos);
        int j = 4;
        if (Bat.Q_4569_t()) {
            j = 7;
        } else if (randomIn.nextBoolean()) {
            return false;
        }
        return i > randomIn.nextInt(j) ? false : Bat.n_1700_B(batIn, worldIn, reason, pos, randomIn);
    }

    private static boolean Q_4569_t() {
        LocalDate localdate = LocalDate.now();
        int i = localdate.get(ChronoField.DAY_OF_MONTH);
        int j = localdate.get(ChronoField.MONTH_OF_YEAR);
        return j == 10 && i >= 20 || j == 11 && i <= 3;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R / 2.0f;
    }
}


