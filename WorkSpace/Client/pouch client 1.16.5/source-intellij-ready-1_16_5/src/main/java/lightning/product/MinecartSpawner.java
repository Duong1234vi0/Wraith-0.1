/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Q_584_o;
import lightning.product.U_2912_j;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.t_5_h;
import lightning.product.y_4319_k;

public class MinecartSpawner
extends y_4319_k {
    private final Q_584_o n_1700_B = new Q_584_o(){

        @Override
        public void n_1700_B(int id) {
            MinecartSpawner.this.O_508_d.n_1700_B((N_4263_v)MinecartSpawner.this, (byte)id);
        }

        @Override
        public b_4507_u n_1700_B() {
            return MinecartSpawner.this.O_508_d;
        }

        @Override
        public c_1514_x J_1907_R() {
            return MinecartSpawner.this.b_2312_j();
        }
    };

    public MinecartSpawner(t_5_h<? extends MinecartSpawner> type, b_4507_u world) {
        super(type, world);
    }

    public MinecartSpawner(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.c_4037_x, worldIn, x, y, z);
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.P_1922_E;
    }

    @Override
    public K_4074_S M_182_A() {
        return a_3742_W.j_306_t.multiplayerClientSuggestionProvider();
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B.n_1700_B(compound);
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.n_1700_B.J_1907_R(compound);
    }

    @Override
    public void n_1700_B(byte id) {
        this.n_1700_B.J_1907_R(id);
    }

    @Override
    public void v_() {
        super.v_();
        this.n_1700_B.R_4764_Y();
    }

    @Override
    public boolean J_303_C() {
        return true;
    }
}


