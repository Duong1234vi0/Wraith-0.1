/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_1132_Q;
import lightning.product.C_4998_y;
import lightning.product.G_1066_I;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.Potions;
import lightning.product.U_2912_j;
import lightning.product.ThrowableItemProjectile;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.k_3129_Y;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.EntityHitResult;
import lightning.product.y_528_b;

public class F_666_T
extends ThrowableItemProjectile
implements G_1066_I {
    public static final Predicate<r_4811_B> n_1700_B = r_4811_B::e_1231_S;

    public F_666_T(t_5_h<? extends F_666_T> typeIn, b_4507_u worldIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)typeIn, worldIn);
    }

    public F_666_T(b_4507_u worldIn, r_4811_B livingEntityIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.R_3908_n, livingEntityIn, worldIn);
    }

    public F_666_T(b_4507_u worldIn, double x, double y, double z) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.R_3908_n, x, y, z, worldIn);
    }

    @Override
    protected q_1613_l P_1922_E() {
        return Items.g_2492_v;
    }

    @Override
    protected float u_1723_Y() {
        return 0.05f;
    }

    @Override
    protected void n_1700_B(BlockHitResult p_230299_1_) {
        super.n_1700_B(p_230299_1_);
        if (!this.O_508_d.Y_259_p) {
            Z_1993_T itemstack = this.n_1700_B();
            y_528_b potion = L_1875_m.G_564_y(itemstack);
            List<k_2610_C> list = L_1875_m.n_1700_B(itemstack);
            boolean flag = potion == Potions.J_1907_R && list.isEmpty();
            b_257_Y direction = p_230299_1_.J_1907_R();
            c_1514_x blockpos = p_230299_1_.n_1700_B();
            c_1514_x blockpos1 = blockpos.offset(direction);
            if (flag) {
                this.n_1700_B(blockpos1, direction);
                this.n_1700_B(blockpos1.offset(direction.u_1723_Y()), direction);
                for (b_257_Y direction1 : b_257_Y.R_4764_Y.n_1700_B) {
                    this.n_1700_B(blockpos1.offset(direction1), direction1);
                }
            }
        }
    }

    @Override
    protected void n_1700_B(HitResult result) {
        boolean flag;
        super.n_1700_B(result);
        Z_1993_T itemstack = this.n_1700_B();
        y_528_b potion = L_1875_m.G_564_y(itemstack);
        List<k_2610_C> list = L_1875_m.n_1700_B(itemstack);
        boolean bl = flag = potion == Potions.J_1907_R && list.isEmpty();
        if (flag) {
            this.w_1484_f();
        } else if (!list.isEmpty()) {
            if (this.t_148_a()) {
                this.n_1700_B(itemstack, potion);
            } else {
                this.n_1700_B(list, result.R_4764_Y() == HitResult.n_1700_B.R_4764_Y ? ((EntityHitResult)result).n_1700_B() : null);
            }
        }
        int i = potion.J_1907_R() ? 2007 : 2002;
        this.O_508_d.R_4764_Y(i, this.b_2312_j(), L_1875_m.R_4764_Y(itemstack));
        this.Ops();
    }

    private void w_1484_f() {
        I_4817_s axisalignedbb = this.i_601_W().grow(4.0, 2.0, 4.0);
        List<r_4811_B> list = this.O_508_d.n_1700_B(r_4811_B.class, axisalignedbb, n_1700_B);
        if (!list.isEmpty()) {
            for (r_4811_B livingentity : list) {
                double d0 = this.G_564_y(livingentity);
                if (!(d0 < 16.0) || !livingentity.e_1231_S()) continue;
                livingentity.n_1700_B(P_11_z.R_4764_Y(livingentity, this.Y_601_j()), 1.0f);
            }
        }
    }

    private void n_1700_B(List<k_2610_C> p_213888_1_, @Nullable N_4263_v p_213888_2_) {
        I_4817_s axisalignedbb = this.i_601_W().grow(4.0, 2.0, 4.0);
        List<r_4811_B> list = this.O_508_d.n_1700_B(r_4811_B.class, axisalignedbb);
        if (!list.isEmpty()) {
            for (r_4811_B livingentity : list) {
                double d0;
                if (!livingentity.F_1446_q() || !((d0 = this.G_564_y(livingentity)) < 16.0)) continue;
                double d1 = 1.0 - Math.sqrt(d0) / 4.0;
                if (livingentity == p_213888_2_) {
                    d1 = 1.0;
                }
                for (k_2610_C effectinstance : p_213888_1_) {
                    g_422_i effect = effectinstance.n_1700_B();
                    if (effect.n_1700_B()) {
                        effect.n_1700_B(this, this.Y_601_j(), livingentity, effectinstance.R_4764_Y(), d1);
                        continue;
                    }
                    int i = (int)(d1 * (double)effectinstance.J_1907_R() + 0.5);
                    if (i <= 20) continue;
                    livingentity.n_1700_B(new k_2610_C(effect, i, effectinstance.R_4764_Y(), effectinstance.G_564_y(), effectinstance.P_1922_E()));
                }
                k_3129_Y eventThrowPotion = new k_3129_Y(livingentity, this.n_1700_B(), d1, p_213888_1_);
                A_4115_X.n_1700_B(eventThrowPotion);
            }
        }
    }

    private void n_1700_B(Z_1993_T p_190542_1_, y_528_b p_190542_2_) {
        B_1132_Q areaeffectcloudentity = new B_1132_Q(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        N_4263_v entity = this.Y_601_j();
        if (entity instanceof r_4811_B) {
            areaeffectcloudentity.n_1700_B((r_4811_B)entity);
        }
        areaeffectcloudentity.n_1700_B(3.0f);
        areaeffectcloudentity.G_564_y(-0.5f);
        areaeffectcloudentity.R_4764_Y(10);
        areaeffectcloudentity.u_1723_Y(-areaeffectcloudentity.P_1922_E() / (float)areaeffectcloudentity.t_148_a());
        areaeffectcloudentity.n_1700_B(p_190542_2_);
        for (k_2610_C effectinstance : L_1875_m.J_1907_R(p_190542_1_)) {
            areaeffectcloudentity.n_1700_B(new k_2610_C(effectinstance));
        }
        U_2912_j compoundnbt = p_190542_1_.Q_4569_t();
        if (compoundnbt != null && compoundnbt.R_4764_Y("CustomPotionColor", 99)) {
            areaeffectcloudentity.n_1700_B(compoundnbt.w_1484_f("CustomPotionColor"));
        }
        this.O_508_d.a_(areaeffectcloudentity);
    }

    private boolean t_148_a() {
        return this.n_1700_B().J_1907_R() == Items.NetherrackBlock;
    }

    private void n_1700_B(c_1514_x pos, b_257_Y p_184542_2_) {
        K_4074_S blockstate = this.O_508_d.getBlockState(pos);
        if (blockstate.n_1700_B(BlockTags.j_276_v)) {
            this.O_508_d.n_1700_B(pos, false);
        } else if (C_4998_y.w_1484_f(blockstate)) {
            this.O_508_d.n_1700_B((a_3913_L)null, 1009, pos, 0);
            C_4998_y.R_4764_Y(this.O_508_d, pos, blockstate);
            this.O_508_d.J_1907_R(pos, (K_4074_S)blockstate.n_1700_B(C_4998_y.h_1847_R, false));
        }
    }
}


