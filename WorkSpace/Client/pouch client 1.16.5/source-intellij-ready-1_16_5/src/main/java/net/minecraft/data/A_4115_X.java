/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.data;

import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;

public class A_4115_X {
    @Deprecated
    public static g_2336_b n_1700_B(String p_240223_0_) {
        return new g_2336_b("minecraft", "block/" + p_240223_0_);
    }

    public static g_2336_b J_1907_R(String p_240224_0_) {
        return new g_2336_b("minecraft", "item/" + p_240224_0_);
    }

    public static g_2336_b n_1700_B(T_2915_h p_240222_0_, String p_240222_1_) {
        g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(p_240222_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "block/" + resourcelocation.J_1907_R() + p_240222_1_);
    }

    public static g_2336_b n_1700_B(T_2915_h p_240221_0_) {
        g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(p_240221_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "block/" + resourcelocation.J_1907_R());
    }

    public static g_2336_b n_1700_B(q_1613_l p_240219_0_) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(p_240219_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "item/" + resourcelocation.J_1907_R());
    }

    public static g_2336_b n_1700_B(q_1613_l p_240220_0_, String p_240220_1_) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(p_240220_0_);
        return new g_2336_b(resourcelocation.R_4764_Y(), "item/" + resourcelocation.J_1907_R() + p_240220_1_);
    }
}

