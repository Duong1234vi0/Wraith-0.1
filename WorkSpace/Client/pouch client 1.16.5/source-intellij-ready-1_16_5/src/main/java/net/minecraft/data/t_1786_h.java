/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.data;

import java.nio.file.Path;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.EntityTypeTags;
import lightning.product.t_5_h;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.z_4693_k;

public class t_1786_h
extends z_4693_k<t_5_h<?>> {
    public t_1786_h(Q_4569_t p_i50784_1_) {
        super(p_i50784_1_, V_3137_a.g_221_o);
    }

    @Override
    protected void J_1907_R() {
        this.n_1700_B(EntityTypeTags.J_1907_R).n_1700_B(t_5_h.V_1446_Y, t_5_h.M_1641_O, t_5_h.RowButton);
        this.n_1700_B(EntityTypeTags.R_4764_Y).n_1700_B(t_5_h.C_2741_M, t_5_h.p_178_J, t_5_h.e_1992_r, t_5_h.y_1700_S, t_5_h.z_1737_N, t_5_h.RetryCallException);
        this.n_1700_B(EntityTypeTags.G_564_y).n_1700_B(t_5_h.P_1922_E);
        this.n_1700_B(EntityTypeTags.P_1922_E).n_1700_B(t_5_h.R_4764_Y, t_5_h.w_612_n);
        this.n_1700_B(EntityTypeTags.u_1723_Y).n_1700_B(EntityTypeTags.P_1922_E).n_1700_B(t_5_h.dtoRealmsServerAddress, t_5_h.T_2506_i, t_5_h.U_1241_n, t_5_h.RealmsWorldResetDto, t_5_h.ValueObject, t_5_h.M_182_A, t_5_h.LongRunningTask);
    }

    @Override
    protected Path n_1700_B(g_2336_b id) {
        return this.J_1907_R.J_1907_R().resolve("data/" + id.R_4764_Y() + "/tags/entity_types/" + id.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Entity Type Tags";
    }
}


