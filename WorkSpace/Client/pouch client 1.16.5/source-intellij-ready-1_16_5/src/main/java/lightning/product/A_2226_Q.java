/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.I_14_v;
import lightning.product.MinecraftClient;
import lightning.product.PlayerTeam;
import lightning.product.d_338_B;
import lightning.product.g_2336_b;
import lightning.product.n_1700_B;
import lightning.product.s_2614_w;
import lightning.product.x_282_a;

public class A_2226_Q {
    private final GameProfile n_1700_B;
    private final Map<MinecraftProfileTexture.Type, g_2336_b> J_1907_R = Maps.newEnumMap(MinecraftProfileTexture.Type.class);
    private I_14_v R_4764_Y;
    private int G_564_y;
    private boolean P_1922_E;
    @Nullable
    private String u_1723_Y;
    @Nullable
    private x_282_a v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private long s_956_w;
    private long u_2550_I;
    private long M_588_G;

    public A_2226_Q(d_338_B.J_1907_R entry) {
        this.n_1700_B = entry.n_1700_B();
        this.R_4764_Y = entry.R_4764_Y();
        this.G_564_y = entry.J_1907_R();
        this.v_4262_N = entry.G_564_y();
    }

    public GameProfile n_1700_B() {
        return this.n_1700_B;
    }

    @Nullable
    public I_14_v J_1907_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(I_14_v gameMode) {
        this.R_4764_Y = gameMode;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public void n_1700_B(int latency) {
        this.G_564_y = latency;
    }

    public boolean G_564_y() {
        return this.u_1723_Y() != null;
    }

    public String P_1922_E() {
        return this.u_1723_Y == null ? s_2614_w.J_1907_R(this.n_1700_B.getId()) : this.u_1723_Y;
    }

    public g_2336_b u_1723_Y() {
        this.s_956_w();
        return (g_2336_b)MoreObjects.firstNonNull((Object)this.J_1907_R.get(MinecraftProfileTexture.Type.SKIN), (Object)s_2614_w.n_1700_B(this.n_1700_B.getId()));
    }

    @Nullable
    public g_2336_b v_4262_N() {
        this.s_956_w();
        return this.J_1907_R.get(MinecraftProfileTexture.Type.CAPE);
    }

    @Nullable
    public g_2336_b w_1484_f() {
        this.s_956_w();
        return this.J_1907_R.get(MinecraftProfileTexture.Type.ELYTRA);
    }

    @Nullable
    public PlayerTeam t_148_a() {
        MinecraftClient mc = MinecraftClient.A_4115_X();
        n_1700_B activeBot = null;
        if (mc.k_2293_S != null && mc.C_2741_M == mc.k_2293_S.P_1922_E.Q_2552_b) {
            activeBot = mc.k_2293_S;
        }
        return activeBot != null ? activeBot.P_1922_E.G_564_y().Q_4569_t().w_1484_f(this.n_1700_B().getName()) : mc.Y_601_j.Q_4569_t().w_1484_f(this.n_1700_B().getName());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void s_956_w() {
        A_2226_Q a_2226_Q = this;
        synchronized (a_2226_Q) {
            if (!this.P_1922_E) {
                this.P_1922_E = true;
                MinecraftClient.A_4115_X().c_4037_x().n_1700_B(this.n_1700_B, (MinecraftProfileTexture.Type p_210250_1_, g_2336_b p_210250_2_, MinecraftProfileTexture p_210250_3_) -> {
                    this.J_1907_R.put(p_210250_1_, p_210250_2_);
                    if (p_210250_1_ == MinecraftProfileTexture.Type.SKIN) {
                        this.u_1723_Y = p_210250_3_.getMetadata("model");
                        if (this.u_1723_Y == null) {
                            this.u_1723_Y = "default";
                        }
                    }
                }, true);
            }
        }
    }

    public void n_1700_B(@Nullable x_282_a displayNameIn) {
        this.v_4262_N = displayNameIn;
    }

    @Nullable
    public x_282_a u_2550_I() {
        return this.v_4262_N;
    }

    public int M_588_G() {
        return this.w_1484_f;
    }

    public void J_1907_R(int p_178836_1_) {
        this.w_1484_f = p_178836_1_;
    }

    public int P_4830_p() {
        return this.t_148_a;
    }

    public void R_4764_Y(int p_178857_1_) {
        this.t_148_a = p_178857_1_;
    }

    public long h_1847_R() {
        return this.s_956_w;
    }

    public void n_1700_B(long p_178846_1_) {
        this.s_956_w = p_178846_1_;
    }

    public long Q_4569_t() {
        return this.u_2550_I;
    }

    public void J_1907_R(long p_178844_1_) {
        this.u_2550_I = p_178844_1_;
    }

    public long M_182_A() {
        return this.M_588_G;
    }

    public void R_4764_Y(long p_178843_1_) {
        this.M_588_G = p_178843_1_;
    }
}



