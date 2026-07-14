/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import lightning.product.C_4114_x;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.Potions;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.y_528_b;

public class Arrow
extends h_384_L {
    private static final h_256_u<Integer> P_1922_E = C_4114_x.n_1700_B(Arrow.class, EntityDataSerializers.J_1907_R);
    private y_528_b u_1723_Y = Potions.n_1700_B;
    private final Set<k_2610_C> v_4262_N = Sets.newHashSet();
    private boolean w_1484_f;

    public Arrow(t_5_h<? extends Arrow> type, b_4507_u worldIn) {
        super((t_5_h<? extends h_384_L>)type, worldIn);
    }

    public Arrow(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.R_4764_Y, x, y, z, worldIn);
    }

    public Arrow(b_4507_u worldIn, r_4811_B shooter) {
        super(t_5_h.R_4764_Y, shooter, worldIn);
    }

    public void J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() == Items.NetherWartBlock) {
            int i;
            this.u_1723_Y = L_1875_m.G_564_y(stack);
            List<k_2610_C> collection = L_1875_m.J_1907_R(stack);
            if (!collection.isEmpty()) {
                for (k_2610_C effectinstance : collection) {
                    this.v_4262_N.add(new k_2610_C(effectinstance));
                }
            }
            if ((i = Arrow.R_4764_Y(stack)) == -1) {
                this.Q_2552_b();
            } else {
                this.R_4764_Y(i);
            }
        } else if (stack.J_1907_R() == Items.g_24_p) {
            this.u_1723_Y = Potions.n_1700_B;
            this.v_4262_N.clear();
            this.l_4537_E.J_1907_R(P_1922_E, -1);
        }
    }

    public static int R_4764_Y(Z_1993_T p_191508_0_) {
        U_2912_j compoundnbt = p_191508_0_.Q_4569_t();
        return compoundnbt != null && compoundnbt.R_4764_Y("CustomPotionColor", 99) ? compoundnbt.w_1484_f("CustomPotionColor") : -1;
    }

    private void Q_2552_b() {
        this.w_1484_f = false;
        if (this.u_1723_Y == Potions.n_1700_B && this.v_4262_N.isEmpty()) {
            this.l_4537_E.J_1907_R(P_1922_E, -1);
        } else {
            this.l_4537_E.J_1907_R(P_1922_E, L_1875_m.n_1700_B(L_1875_m.n_1700_B(this.u_1723_Y, this.v_4262_N)));
        }
    }

    public void n_1700_B(k_2610_C effect) {
        this.v_4262_N.add(effect);
        this.D_60_a().J_1907_R(P_1922_E, L_1875_m.n_1700_B(L_1875_m.n_1700_B(this.u_1723_Y, this.v_4262_N)));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(P_1922_E, -1);
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p) {
            if (this.n_1700_B) {
                if (this.J_1907_R % 5 == 0) {
                    this.J_1907_R(1);
                }
            } else {
                this.J_1907_R(2);
            }
        } else if (this.n_1700_B && this.J_1907_R != 0 && !this.v_4262_N.isEmpty() && this.J_1907_R >= 600) {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)0);
            this.u_1723_Y = Potions.n_1700_B;
            this.v_4262_N.clear();
            this.l_4537_E.J_1907_R(P_1922_E, -1);
        }
    }

    private void J_1907_R(int particleCount) {
        int i = this.w_1457_N();
        if (i != -1 && particleCount > 0) {
            double d0 = (double)(i >> 16 & 0xFF) / 255.0;
            double d1 = (double)(i >> 8 & 0xFF) / 255.0;
            double d2 = (double)(i >> 0 & 0xFF) / 255.0;
            for (int j = 0; j < particleCount; ++j) {
                this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, this.G_564_y(0.5), this.M_766_z(), this.v_4262_N(0.5), d0, d1, d2);
            }
        }
    }

    public int w_1457_N() {
        return this.l_4537_E.n_1700_B(P_1922_E);
    }

    private void R_4764_Y(int p_191507_1_) {
        this.w_1484_f = true;
        this.l_4537_E.J_1907_R(P_1922_E, p_191507_1_);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.u_1723_Y != Potions.n_1700_B && this.u_1723_Y != null) {
            compound.n_1700_B("Potion", V_3137_a.B_1668_F.J_1907_R(this.u_1723_Y).toString());
        }
        if (this.w_1484_f) {
            compound.J_1907_R("Color", this.w_1457_N());
        }
        if (!this.v_4262_N.isEmpty()) {
            q_2896_o listnbt = new q_2896_o();
            for (k_2610_C effectinstance : this.v_4262_N) {
                listnbt.add(effectinstance.n_1700_B(new U_2912_j()));
            }
            compound.n_1700_B("CustomPotionEffects", listnbt);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("Potion", 8)) {
            this.u_1723_Y = L_1875_m.R_4764_Y(compound);
        }
        for (k_2610_C effectinstance : L_1875_m.J_1907_R(compound)) {
            this.n_1700_B(effectinstance);
        }
        if (compound.R_4764_Y("Color", 99)) {
            this.R_4764_Y(compound.w_1484_f("Color"));
        } else {
            this.Q_2552_b();
        }
    }

    @Override
    protected void n_1700_B(r_4811_B living) {
        super.n_1700_B(living);
        for (k_2610_C effectinstance : this.u_1723_Y.n_1700_B()) {
            living.n_1700_B(new k_2610_C(effectinstance.n_1700_B(), Math.max(effectinstance.J_1907_R() / 8, 1), effectinstance.R_4764_Y(), effectinstance.G_564_y(), effectinstance.P_1922_E()));
        }
        if (!this.v_4262_N.isEmpty()) {
            for (k_2610_C effectinstance1 : this.v_4262_N) {
                living.n_1700_B(effectinstance1);
            }
        }
    }

    @Override
    protected Z_1993_T w_1484_f() {
        if (this.v_4262_N.isEmpty() && this.u_1723_Y == Potions.n_1700_B) {
            return new Z_1993_T(Items.g_24_p);
        }
        Z_1993_T itemstack = new Z_1993_T(Items.NetherWartBlock);
        L_1875_m.n_1700_B(itemstack, this.u_1723_Y);
        L_1875_m.n_1700_B(itemstack, this.v_4262_N);
        if (this.w_1484_f) {
            itemstack.M_182_A().J_1907_R("CustomPotionColor", this.w_1457_N());
        }
        return itemstack;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 0) {
            int i = this.w_1457_N();
            if (i != -1) {
                double d0 = (double)(i >> 16 & 0xFF) / 255.0;
                double d1 = (double)(i >> 8 & 0xFF) / 255.0;
                double d2 = (double)(i >> 0 & 0xFF) / 255.0;
                for (int j = 0; j < 20; ++j) {
                    this.O_508_d.n_1700_B(ParticleTypes.Y_259_p, this.G_564_y(0.5), this.M_766_z(), this.v_4262_N(0.5), d0, d1, d2);
                }
            }
        } else {
            super.n_1700_B(id);
        }
    }
}


