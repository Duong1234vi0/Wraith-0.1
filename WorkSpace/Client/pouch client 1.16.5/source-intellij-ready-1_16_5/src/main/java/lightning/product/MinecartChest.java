/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.ChestMenu;
import lightning.product.W_3491_f;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.s_3548_s;
import lightning.product.t_5_h;
import lightning.product.v_3445_Z;
import lightning.product.y_4319_k;

public class MinecartChest
extends s_3548_s {
    public MinecartChest(t_5_h<? extends MinecartChest> type, b_4507_u world) {
        super(type, world);
    }

    public MinecartChest(b_4507_u worldIn, double x, double y, double z) {
        super(t_5_h.X_933_l, x, y, z, worldIn);
    }

    @Override
    public void J_1907_R(P_11_z source) {
        super.J_1907_R(source);
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
            this.n_1700_B(a_3742_W.L_1362_X);
        }
    }

    @Override
    public int Y_259_p() {
        return 27;
    }

    @Override
    public y_4319_k.n_1700_B h_1847_R() {
        return y_4319_k.n_1700_B.J_1907_R;
    }

    @Override
    public K_4074_S M_182_A() {
        return (K_4074_S)a_3742_W.L_1362_X.multiplayerClientSuggestionProvider().n_1700_B(v_3445_Z.h_1847_R, b_257_Y.R_4764_Y);
    }

    @Override
    public int w_1457_N() {
        return 8;
    }

    @Override
    public a_2900_S n_1700_B(int id, W_3491_f playerInventoryIn) {
        return ChestMenu.n_1700_B(id, playerInventoryIn, this);
    }
}


