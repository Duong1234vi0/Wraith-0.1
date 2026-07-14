/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4114_x;
import lightning.product.D_3925_G;
import lightning.product.G_1066_I;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public abstract class Fireball
extends D_3925_G
implements G_1066_I {
    private static final h_256_u<Z_1993_T> G_564_y = C_4114_x.n_1700_B(Fireball.class, EntityDataSerializers.v_4262_N);

    public Fireball(t_5_h<? extends Fireball> p_i50166_1_, b_4507_u p_i50166_2_) {
        super((t_5_h<? extends D_3925_G>)p_i50166_1_, p_i50166_2_);
    }

    public Fireball(t_5_h<? extends Fireball> p_i50167_1_, double p_i50167_2_, double p_i50167_4_, double p_i50167_6_, double p_i50167_8_, double p_i50167_10_, double p_i50167_12_, b_4507_u p_i50167_14_) {
        super(p_i50167_1_, p_i50167_2_, p_i50167_4_, p_i50167_6_, p_i50167_8_, p_i50167_10_, p_i50167_12_, p_i50167_14_);
    }

    public Fireball(t_5_h<? extends Fireball> p_i50168_1_, r_4811_B p_i50168_2_, double p_i50168_3_, double p_i50168_5_, double p_i50168_7_, b_4507_u p_i50168_9_) {
        super(p_i50168_1_, p_i50168_2_, p_i50168_3_, p_i50168_5_, p_i50168_7_, p_i50168_9_);
    }

    public void J_1907_R(Z_1993_T p_213898_1_) {
        if (p_213898_1_.J_1907_R() != Items.CraftingTableBlock || p_213898_1_.h_1847_R()) {
            this.D_60_a().J_1907_R(G_564_y, j_3341_s.n_1700_B(p_213898_1_.t_148_a(), p_213897_0_ -> p_213897_0_.P_1922_E(1)));
        }
    }

    protected Z_1993_T P_1922_E() {
        return this.D_60_a().n_1700_B(G_564_y);
    }

    @Override
    public Z_1993_T n_1700_B() {
        Z_1993_T itemstack = this.P_1922_E();
        return itemstack.n_1700_B() ? new Z_1993_T(Items.CraftingTableBlock) : itemstack;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(G_564_y, Z_1993_T.J_1907_R);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        Z_1993_T itemstack = this.P_1922_E();
        if (!itemstack.n_1700_B()) {
            compound.n_1700_B("Item", itemstack.J_1907_R(new U_2912_j()));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        Z_1993_T itemstack = Z_1993_T.n_1700_B(compound.M_182_A("Item"));
        this.J_1907_R(itemstack);
    }
}


