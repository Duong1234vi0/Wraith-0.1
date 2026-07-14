/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.Hashing
 *  com.mojang.authlib.GameProfile
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.hash.Hashing;
import com.mojang.authlib.GameProfile;
import javax.annotation.Nullable;
import lightning.product.A_2226_Q;
import lightning.product.C_3240_x;
import lightning.product.H_1468_N;
import lightning.product.Attributes;
import lightning.product.I_14_v;
import lightning.product.BowItem;
import lightning.product.V_772_m;
import lightning.product.Z_875_P;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_3005_b;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.k_4690_i;
import lightning.product.ShoulderRidingEntity;
import lightning.product.r_1020_F;
import lightning.product.s_2614_w;
import lightning.product.u_530_F;
import mods.cape.CapeHolder;
import mods.cape.StickSimulation;
import net.optifine.Config;
import net.optifine.player.CapeUtils;
import net.optifine.player.PlayerConfigurations;
import net.optifine.reflect.Reflector;

public abstract class X_4340_E
extends a_3913_L
implements CapeHolder {
    private final StickSimulation n_1700_B;
    private A_2226_Q J_1907_R;
    public float G_624_v;
    public float T_2506_i;
    public float q_4610_l;
    public final b_4507_u z_4693_k;
    public float g_221_o;
    public float e_2887_G;
    public float B_1668_F;
    public float g_164_R;
    public boolean X_933_l;
    private g_2336_b R_4764_Y = null;
    private long G_564_y = 0L;
    private boolean P_1922_E = false;
    private String u_1723_Y = null;
    public ShoulderRidingEntity Z_976_R;
    public ShoulderRidingEntity H_1990_U;
    public float N_2525_X;
    public float c_4037_x;
    public float g_2268_R;
    private static final g_2336_b v_4262_N = new g_2336_b("textures/entity/elytra.png");
    private static final g_2336_b w_1484_f = new g_2336_b("Pouch/cape/cape.png");

    public X_4340_E(k_4690_i world, GameProfile profile) {
        super(world, world.w_1457_N(), world.Y_601_j(), profile);
        this.z_4693_k = world;
        this.u_1723_Y = profile.getName();
        if (this.u_1723_Y != null && !this.u_1723_Y.isEmpty()) {
            this.u_1723_Y = H_1468_N.n_1700_B(this.u_1723_Y);
        }
        CapeUtils.downloadCape(this);
        PlayerConfigurations.getPlayerConfiguration(this);
        this.n_1700_B = new StickSimulation();
    }

    public X_4340_E(c_3005_b world, GameProfile profile) {
        super(world, world.w_1457_N(), world.Y_601_j(), profile);
        this.z_4693_k = world;
        this.u_1723_Y = profile.getName();
        if (this.u_1723_Y != null && !this.u_1723_Y.isEmpty()) {
            this.u_1723_Y = H_1468_N.n_1700_B(this.u_1723_Y);
        }
        CapeUtils.downloadCape(this);
        PlayerConfigurations.getPlayerConfiguration(this);
        this.n_1700_B = new StickSimulation();
    }

    @Override
    public boolean d_2461_k() {
        A_2226_Q var10000;
        if (MinecraftClient.A_4115_X().k_2293_S() == null) {
            return false;
        }
        if (this instanceof Z_875_P) {
            Z_875_P b = (Z_875_P)this;
            var10000 = b.n_1700_B.n_1700_B(this.y_4642_Y().getId());
        } else {
            var10000 = MinecraftClient.A_4115_X().k_2293_S().n_1700_B(this.y_4642_Y().getId());
        }
        A_2226_Q networkplayerinfo = var10000;
        return networkplayerinfo != null && networkplayerinfo.J_1907_R() == I_14_v.P_1922_E;
    }

    @Override
    public boolean G_624_v() {
        A_2226_Q var10000;
        if (MinecraftClient.A_4115_X().k_2293_S() == null) {
            return false;
        }
        if (this instanceof Z_875_P) {
            Z_875_P b = (Z_875_P)this;
            var10000 = b.n_1700_B.n_1700_B(this.y_4642_Y().getId());
        } else {
            var10000 = MinecraftClient.A_4115_X().k_2293_S().n_1700_B(this.y_4642_Y().getId());
        }
        A_2226_Q networkplayerinfo = var10000;
        return networkplayerinfo != null && networkplayerinfo.J_1907_R() == I_14_v.R_4764_Y;
    }

    public boolean T_2506_i() {
        return this.q_4610_l() != null;
    }

    @Nullable
    protected A_2226_Q q_4610_l() {
        if (this.J_1907_R == null) {
            this.J_1907_R = MinecraftClient.A_4115_X().k_2293_S().n_1700_B(this.w_2705_t());
        }
        return this.J_1907_R;
    }

    public boolean z_4693_k() {
        A_2226_Q networkplayerinfo = this.q_4610_l();
        return networkplayerinfo != null && networkplayerinfo.G_564_y();
    }

    public g_2336_b g_221_o() {
        A_2226_Q networkplayerinfo = this.q_4610_l();
        return networkplayerinfo == null ? s_2614_w.n_1700_B(this.w_2705_t()) : networkplayerinfo.u_1723_Y();
    }

    @Nullable
    public g_2336_b e_2887_G() {
        if (this.g_164_R()) {
            return w_1484_f;
        }
        if (!Config.isShowCapes()) {
            return null;
        }
        if (this.G_564_y != 0L && System.currentTimeMillis() > this.G_564_y) {
            CapeUtils.reloadCape(this);
            this.G_564_y = 0L;
        }
        if (this.R_4764_Y != null) {
            return this.R_4764_Y;
        }
        A_2226_Q networkplayerinfo = this.q_4610_l();
        return networkplayerinfo == null ? null : networkplayerinfo.v_4262_N();
    }

    @Override
    protected void B_1668_F() {
        if (this.g_164_R()) {
            this.simulate(this);
        }
        super.B_1668_F();
    }

    public boolean g_164_R() {
        return this instanceof V_772_m;
    }

    public boolean X_933_l() {
        return this.q_4610_l() != null;
    }

    @Nullable
    public g_2336_b Z_976_R() {
        A_2226_Q networkplayerinfo = this.q_4610_l();
        return networkplayerinfo == null ? null : networkplayerinfo.w_1484_f();
    }

    public static r_1020_F n_1700_B(g_2336_b resourceLocationIn, String username) {
        C_3240_x texturemanager = MinecraftClient.A_4115_X().G_624_v();
        c_4477_a texture = texturemanager.J_1907_R(resourceLocationIn);
        if (texture == null) {
            texture = new r_1020_F(null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", H_1468_N.n_1700_B(username)), s_2614_w.n_1700_B(X_4340_E.u_1723_Y(username)), true, null);
            texturemanager.n_1700_B(resourceLocationIn, texture);
        }
        return (r_1020_F)texture;
    }

    public static g_2336_b R_4764_Y(String username) {
        return new g_2336_b("skins/" + String.valueOf(Hashing.sha1().hashUnencodedChars((CharSequence)H_1468_N.n_1700_B(username))));
    }

    public String H_1990_U() {
        A_2226_Q networkplayerinfo = this.q_4610_l();
        return networkplayerinfo == null ? s_2614_w.J_1907_R(this.w_2705_t()) : networkplayerinfo.P_1922_E();
    }

    public float N_2525_X() {
        float f = 1.0f;
        if (this.C_415_h.J_1907_R) {
            f *= 1.1f;
        }
        f = (float)((double)f * ((this.J_1907_R(Attributes.G_564_y) / (double)this.C_415_h.J_1907_R() + 1.0) / 2.0));
        if (this.C_415_h.J_1907_R() == 0.0f || Float.isNaN(f) || Float.isInfinite(f)) {
            f = 1.0f;
        }
        if (this.Y_601_j() && this.B_2580_P().J_1907_R() instanceof BowItem) {
            int i = this.g_1031_K();
            float f1 = (float)i / 20.0f;
            f1 = f1 > 1.0f ? 1.0f : (f1 *= f1);
            f *= 1.0f - f1 * 0.15f;
        }
        return Reflector.ForgeHooksClient_getOffsetFOV.exists() ? Reflector.callFloat(Reflector.ForgeHooksClient_getOffsetFOV, this, Float.valueOf(f)) : u_530_F.v_4262_N(MinecraftClient.A_4115_X().P_4830_p.M_2677_i, 1.0f, f);
    }

    public String c_4037_x() {
        return this.u_1723_Y;
    }

    public g_2336_b g_2268_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(g_2336_b p_setLocationOfCape_1_) {
        this.R_4764_Y = p_setLocationOfCape_1_;
    }

    public boolean T_3594_S() {
        g_2336_b resourcelocation = this.e_2887_G();
        if (resourcelocation == null) {
            return false;
        }
        return resourcelocation == this.R_4764_Y ? this.P_1922_E : true;
    }

    @Override
    public StickSimulation getSimulation() {
        return this.n_1700_B;
    }

    public void G_564_y(boolean p_setElytraOfCape_1_) {
        this.P_1922_E = p_setElytraOfCape_1_;
    }

    public boolean D_4792_h() {
        return this.P_1922_E;
    }

    public long s_2632_s() {
        return this.G_564_y;
    }

    public void n_1700_B(long p_setReloadCapeTimeMs_1_) {
        this.G_564_y = p_setReloadCapeTimeMs_1_;
    }

    public boolean e_4240_b() {
        return this.X_933_l;
    }
}



