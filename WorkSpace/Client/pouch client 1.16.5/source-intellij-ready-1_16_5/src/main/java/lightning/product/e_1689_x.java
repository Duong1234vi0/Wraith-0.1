/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_1907_R;
import lightning.product.MobEffects;
import lightning.product.M_1336_P;
import lightning.product.M_660_m;
import lightning.product.T_1114_L;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_3005_b;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.k_4690_i;
import lightning.product.n_1700_B;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.shaders.Shaders;

public class e_1689_x
implements AutoCloseable {
    private final T_1114_L J_1907_R;
    private final i_2518_W R_4764_Y;
    private final g_2336_b G_564_y;
    private boolean P_1922_E;
    private float u_1723_Y;
    private final M_660_m v_4262_N;
    private final MinecraftClient w_1484_f;
    private boolean t_148_a = true;
    private boolean s_956_w = false;
    private M_1336_P u_2550_I = new M_1336_P();
    public static final int n_1700_B = e_1689_x.n_1700_B(15, 15);

    public e_1689_x(M_660_m entityRendererIn, MinecraftClient mcIn) {
        this.v_4262_N = entityRendererIn;
        this.w_1484_f = mcIn;
        this.J_1907_R = new T_1114_L(16, 16, false);
        this.G_564_y = this.w_1484_f.G_624_v().n_1700_B("light_map", this.J_1907_R);
        this.R_4764_Y = this.J_1907_R.J_1907_R();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                this.R_4764_Y.n_1700_B(j, i, -1);
            }
        }
        this.J_1907_R.n_1700_B();
    }

    @Override
    public void close() {
        this.J_1907_R.close();
    }

    public void n_1700_B() {
        this.u_1723_Y = (float)((double)this.u_1723_Y + (Math.random() - Math.random()) * Math.random() * Math.random() * 0.1);
        this.u_1723_Y = (float)((double)this.u_1723_Y * 0.9);
        this.P_1922_E = true;
    }

    public void J_1907_R() {
        c_4037_x.P_1922_E(33986);
        c_4037_x.e_4240_b();
        c_4037_x.P_1922_E(33984);
        if (Config.isShaders()) {
            Shaders.disableLightmap();
        }
    }

    public void R_4764_Y() {
        if (this.t_148_a) {
            c_4037_x.P_1922_E(33986);
            c_4037_x.u_2550_I(5890);
            c_4037_x.z_1737_N();
            float f = 0.00390625f;
            c_4037_x.J_1907_R(0.00390625f, 0.00390625f, 0.00390625f);
            c_4037_x.R_4764_Y(8.0f, 8.0f, 8.0f);
            c_4037_x.u_2550_I(5888);
            this.w_1484_f.G_624_v().n_1700_B(this.G_564_y);
            c_4037_x.n_1700_B(3553, 10241, 9729);
            c_4037_x.n_1700_B(3553, 10240, 9729);
            c_4037_x.n_1700_B(3553, 10242, 33071);
            c_4037_x.n_1700_B(3553, 10243, 33071);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.x_607_J();
            c_4037_x.P_1922_E(33984);
            if (Config.isShaders()) {
                Shaders.enableLightmap();
            }
        }
    }

    public void n_1700_B(float partialTicks) {
        n_1700_B bot1 = null;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.w_1484_f.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (this.P_1922_E) {
            b_4507_u w;
            int i1;
            int l;
            int k;
            k_4690_i clientworld;
            this.P_1922_E = false;
            this.w_1484_f.PlayerInfo().n_1700_B("lightTex");
            b_4507_u world = bot1 != null ? bot1.P_1922_E.G_564_y() : this.w_1484_f.Y_601_j;
            k_4690_i k_4690_i2 = clientworld = world instanceof k_4690_i ? (k_4690_i)world : this.w_1484_f.Y_601_j;
            if (clientworld != null) {
                this.s_956_w = false;
                if (Config.isCustomColors()) {
                    boolean flag;
                    boolean bl = flag = this.w_1484_f.Y_259_p.J_1907_R(MobEffects.M_182_A) || this.w_1484_f.Y_259_p.J_1907_R(MobEffects.A_4115_X);
                    if (CustomColors.updateLightmap(clientworld, this.u_1723_Y, this.R_4764_Y, flag, partialTicks)) {
                        this.J_1907_R.n_1700_B();
                        this.P_1922_E = false;
                        this.w_1484_f.PlayerInfo().R_4764_Y();
                        this.s_956_w = true;
                        return;
                    }
                }
                float f9 = clientworld.n_1700_B(1.0f);
                float f = clientworld.P_4830_p() > 0 ? 1.0f : f9 * 0.95f + 0.05f;
                float f1 = this.w_1484_f.Y_259_p.n_3318_d();
                float f2 = this.w_1484_f.Y_259_p.J_1907_R(MobEffects.M_182_A) ? M_660_m.n_1700_B(this.w_1484_f.Y_259_p, partialTicks) : (f1 > 0.0f && this.w_1484_f.Y_259_p.J_1907_R(MobEffects.A_4115_X) ? f1 : 0.0f);
                M_1336_P vector3f = new M_1336_P(f9, f9, 1.0f);
                vector3f.n_1700_B(new M_1336_P(1.0f, 1.0f, 1.0f), 0.35f);
                float f3 = this.u_1723_Y + 1.5f;
                M_1336_P vector3f1 = new M_1336_P();
                for (int i = 0; i < 16; ++i) {
                    for (int j = 0; j < 16; ++j) {
                        float f10;
                        float f4 = this.n_1700_B(clientworld, i) * f;
                        float f5 = this.n_1700_B(clientworld, j) * f3;
                        float f6 = f5 * ((f5 * 0.6f + 0.4f) * 0.6f + 0.4f);
                        float f7 = f5 * (f5 * f5 * 0.6f + 0.4f);
                        vector3f1.J_1907_R(f5, f6, f7);
                        if (clientworld.n_1700_B().G_564_y()) {
                            vector3f1.n_1700_B(this.n_1700_B(0.99f, 1.12f, 1.0f), 0.25f);
                        } else {
                            M_1336_P vector3f2 = this.n_1700_B(vector3f);
                            vector3f2.n_1700_B(f4);
                            vector3f1.n_1700_B(vector3f2);
                            vector3f1.n_1700_B(this.n_1700_B(0.75f, 0.75f, 0.75f), 0.04f);
                            if (this.v_4262_N.G_564_y(partialTicks) > 0.0f) {
                                float f8 = this.v_4262_N.G_564_y(partialTicks);
                                M_1336_P vector3f3 = this.n_1700_B(vector3f1);
                                vector3f3.n_1700_B(0.7f, 0.6f, 0.6f);
                                vector3f1.n_1700_B(vector3f3, f8);
                            }
                        }
                        vector3f1.n_1700_B(0.0f, 1.0f);
                        if (f2 > 0.0f && (f10 = Math.max(vector3f1.n_1700_B(), Math.max(vector3f1.J_1907_R(), vector3f1.R_4764_Y()))) < 1.0f) {
                            float f12 = 1.0f / f10;
                            M_1336_P vector3f5 = this.n_1700_B(vector3f1);
                            vector3f5.n_1700_B(f12);
                            vector3f1.n_1700_B(vector3f5, f2);
                        }
                        float f11 = (float)this.w_1484_f.P_4830_p.c_132_F;
                        M_1336_P vector3f4 = this.n_1700_B(vector3f1);
                        vector3f4.n_1700_B(this::J_1907_R);
                        vector3f1.n_1700_B(vector3f4, f11);
                        vector3f1.n_1700_B(this.n_1700_B(0.75f, 0.75f, 0.75f), 0.04f);
                        vector3f1.n_1700_B(0.0f, 1.0f);
                        vector3f1.n_1700_B(255.0f);
                        int j1 = 255;
                        k = (int)vector3f1.n_1700_B();
                        l = (int)vector3f1.J_1907_R();
                        i1 = (int)vector3f1.R_4764_Y();
                        this.R_4764_Y.n_1700_B(j, i, 0xFF000000 | i1 << 16 | l << 8 | k);
                    }
                }
                this.J_1907_R.n_1700_B();
                this.w_1484_f.PlayerInfo().R_4764_Y();
            }
            if (bot1 != null && world instanceof c_3005_b && (w = world) != null) {
                this.s_956_w = false;
                if (Config.isCustomColors()) {
                    // empty if block
                }
                float f9 = ((c_3005_b)w).n_1700_B(1.0f);
                float f = ((c_3005_b)w).C_2741_M() > 0 ? 1.0f : f9 * 0.95f + 0.05f;
                float f1 = bot1.P_1922_E.Q_2552_b.d_2427_y();
                float f2 = bot1.P_1922_E.Q_2552_b.J_1907_R(MobEffects.M_182_A) ? M_660_m.n_1700_B(bot1.P_1922_E.Q_2552_b, partialTicks) : (f1 > 0.0f && bot1.P_1922_E.Q_2552_b.J_1907_R(MobEffects.A_4115_X) ? f1 : 0.0f);
                M_1336_P vector3f = new M_1336_P(f9, f9, 1.0f);
                vector3f.n_1700_B(new M_1336_P(1.0f, 1.0f, 1.0f), 0.35f);
                float f3 = this.u_1723_Y + 1.5f;
                M_1336_P vector3f1 = new M_1336_P();
                for (int i = 0; i < 16; ++i) {
                    for (int j = 0; j < 16; ++j) {
                        float f10;
                        float f4 = this.n_1700_B(w, i) * f;
                        float f5 = this.n_1700_B(w, j) * f3;
                        float f6 = f5 * ((f5 * 0.6f + 0.4f) * 0.6f + 0.4f);
                        float f7 = f5 * (f5 * f5 * 0.6f + 0.4f);
                        vector3f1.J_1907_R(f5, f6, f7);
                        if (((c_3005_b)w).n_1700_B().G_564_y()) {
                            vector3f1.n_1700_B(this.n_1700_B(0.99f, 1.12f, 1.0f), 0.25f);
                        } else {
                            M_1336_P vector3f2 = this.n_1700_B(vector3f);
                            vector3f2.n_1700_B(f4);
                            vector3f1.n_1700_B(vector3f2);
                            vector3f1.n_1700_B(this.n_1700_B(0.75f, 0.75f, 0.75f), 0.04f);
                            if (this.v_4262_N.G_564_y(partialTicks) > 0.0f) {
                                float f12 = this.v_4262_N.G_564_y(partialTicks);
                                M_1336_P vector3f5 = this.n_1700_B(vector3f1);
                                vector3f5.n_1700_B(0.7f, 0.6f, 0.6f);
                                vector3f1.n_1700_B(vector3f5, f12);
                            }
                        }
                        vector3f1.n_1700_B(0.0f, 1.0f);
                        if (f2 > 0.0f && (f10 = Math.max(vector3f1.n_1700_B(), Math.max(vector3f1.J_1907_R(), vector3f1.R_4764_Y()))) < 1.0f) {
                            float f12 = 1.0f / f10;
                            M_1336_P vector3f5 = this.n_1700_B(vector3f1);
                            vector3f5.n_1700_B(f12);
                            vector3f1.n_1700_B(vector3f5, f2);
                        }
                        float f102 = (float)this.w_1484_f.P_4830_p.c_132_F;
                        M_1336_P vector3f4 = this.n_1700_B(vector3f1);
                        vector3f4.n_1700_B(this::J_1907_R);
                        vector3f1.n_1700_B(vector3f4, f102);
                        vector3f1.n_1700_B(this.n_1700_B(0.75f, 0.75f, 0.75f), 0.04f);
                        vector3f1.n_1700_B(0.0f, 1.0f);
                        vector3f1.n_1700_B(255.0f);
                        k = (int)vector3f1.n_1700_B();
                        l = (int)vector3f1.J_1907_R();
                        i1 = (int)vector3f1.R_4764_Y();
                        this.R_4764_Y.n_1700_B(j, i, 0xFF000000 | i1 << 16 | l << 8 | k);
                    }
                }
                this.J_1907_R.n_1700_B();
                this.w_1484_f.PlayerInfo().R_4764_Y();
            }
        }
    }

    private float J_1907_R(float valueIn) {
        float f = 1.0f - valueIn;
        return 1.0f - f * f * f * f;
    }

    private float n_1700_B(b_4507_u worldIn, int lightLevelIn) {
        return worldIn.G_624_v().n_1700_B(lightLevelIn);
    }

    public static int n_1700_B(int blockLightIn, int skyLightIn) {
        return blockLightIn << 4 | skyLightIn << 20;
    }

    public static int n_1700_B(int packedLightIn) {
        return (packedLightIn & 0xFFFF) >> 4;
    }

    public static int J_1907_R(int packedLightIn) {
        return packedLightIn >> 20 & 0xFFFF;
    }

    private M_1336_P n_1700_B(float p_getTempVector3f_1_, float p_getTempVector3f_2_, float p_getTempVector3f_3_) {
        this.u_2550_I.J_1907_R(p_getTempVector3f_1_, p_getTempVector3f_2_, p_getTempVector3f_3_);
        return this.u_2550_I;
    }

    private M_1336_P n_1700_B(M_1336_P p_getTempCopy_1_) {
        this.u_2550_I.J_1907_R(p_getTempCopy_1_.n_1700_B(), p_getTempCopy_1_.J_1907_R(), p_getTempCopy_1_.R_4764_Y());
        return this.u_2550_I;
    }

    public boolean G_564_y() {
        return this.t_148_a;
    }

    public void n_1700_B(boolean p_setAllowed_1_) {
        this.t_148_a = p_setAllowed_1_;
    }

    public boolean P_1922_E() {
        return this.s_956_w;
    }
}



