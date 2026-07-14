/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4114_x;
import lightning.product.G_1066_I;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.ThrowableProjectile;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public abstract class ThrowableItemProjectile
extends ThrowableProjectile
implements G_1066_I {
    private static final h_256_u<Z_1993_T> n_1700_B = C_4114_x.n_1700_B(ThrowableItemProjectile.class, EntityDataSerializers.v_4262_N);

    public ThrowableItemProjectile(t_5_h<? extends ThrowableItemProjectile> type, b_4507_u worldIn) {
        super((t_5_h<? extends ThrowableProjectile>)type, worldIn);
    }

    public ThrowableItemProjectile(t_5_h<? extends ThrowableItemProjectile> type, double x, double y, double z, b_4507_u worldIn) {
        super(type, x, y, z, worldIn);
    }

    public ThrowableItemProjectile(t_5_h<? extends ThrowableItemProjectile> type, r_4811_B livingEntityIn, b_4507_u worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public void J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() != this.P_1922_E() || stack.h_1847_R()) {
            this.D_60_a().J_1907_R(n_1700_B, j_3341_s.n_1700_B(stack.t_148_a(), p_213883_0_ -> p_213883_0_.P_1922_E(1)));
        }
    }

    protected abstract q_1613_l P_1922_E();

    protected Z_1993_T v_4262_N() {
        return this.D_60_a().n_1700_B(n_1700_B);
    }

    @Override
    public Z_1993_T n_1700_B() {
        Z_1993_T itemstack = this.v_4262_N();
        return itemstack.n_1700_B() ? new Z_1993_T(this.P_1922_E()) : itemstack;
    }

    @Override
    protected void a_() {
        this.D_60_a().n_1700_B(n_1700_B, Z_1993_T.J_1907_R);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        Z_1993_T itemstack = this.v_4262_N();
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


