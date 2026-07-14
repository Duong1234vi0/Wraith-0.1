/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.U_2912_j;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import net.minecraft.server.n_1700_B;

public class J_1907_R {
    private final Map<g_2336_b, n_1700_B> n_1700_B = Maps.newHashMap();

    @Nullable
    public n_1700_B n_1700_B(g_2336_b id) {
        return this.n_1700_B.get(id);
    }

    public n_1700_B n_1700_B(g_2336_b id, x_282_a p_201379_2_) {
        n_1700_B customserverbossinfo = new n_1700_B(id, p_201379_2_);
        this.n_1700_B.put(id, customserverbossinfo);
        return customserverbossinfo;
    }

    public void n_1700_B(n_1700_B bossbar) {
        this.n_1700_B.remove(bossbar.J_1907_R());
    }

    public Collection<g_2336_b> n_1700_B() {
        return this.n_1700_B.keySet();
    }

    public Collection<n_1700_B> J_1907_R() {
        return this.n_1700_B.values();
    }

    public U_2912_j R_4764_Y() {
        U_2912_j compoundnbt = new U_2912_j();
        for (n_1700_B customserverbossinfo : this.n_1700_B.values()) {
            compoundnbt.n_1700_B(customserverbossinfo.J_1907_R().toString(), customserverbossinfo.v_4262_N());
        }
        return compoundnbt;
    }

    public void n_1700_B(U_2912_j p_201381_1_) {
        for (String s : p_201381_1_.G_564_y()) {
            g_2336_b resourcelocation = new g_2336_b(s);
            this.n_1700_B.put(resourcelocation, net.minecraft.server.n_1700_B.n_1700_B(p_201381_1_.M_182_A(s), resourcelocation));
        }
    }

    public void n_1700_B(B_4088_l player) {
        for (n_1700_B customserverbossinfo : this.n_1700_B.values()) {
            customserverbossinfo.R_4764_Y(player);
        }
    }

    public void J_1907_R(B_4088_l player) {
        for (n_1700_B customserverbossinfo : this.n_1700_B.values()) {
            customserverbossinfo.G_564_y(player);
        }
    }
}

