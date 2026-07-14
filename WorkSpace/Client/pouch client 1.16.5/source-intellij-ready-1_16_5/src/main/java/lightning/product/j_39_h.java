/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.BiomeManager;
import lightning.product.MobEffects;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.M_660_m;
import lightning.product.N_4263_v;
import lightning.product.CubicSampler;
import lightning.product.V_772_m;
import lightning.product.X_933_l;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_3270_j;
import lightning.product.h_3572_K;
import lightning.product.j_3341_s;
import lightning.product.Ambience;
import lightning.product.k_4690_i;
import lightning.product.k_594_Q;
import lightning.product.ClientBootstrap;
import lightning.product.q_3148_R;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;

public class j_39_h {
    public static float n_1700_B;
    public static float J_1907_R;
    public static float R_4764_Y;
    private static int P_1922_E;
    private static int u_1723_Y;
    private static long v_4262_N;
    public static boolean G_564_y;

    private static boolean R_4764_Y() {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.Y_259_p);
        A_4115_X.n_1700_B(event);
        return event.n_1700_B();
    }

    private static float[] n_1700_B(float fogStart, float fogEnd, float farPlaneDistance, h_3572_K activeRenderInfo, n_1700_B fogType, boolean nearFog, float partialTicks) {
        float weatherFactor;
        Ambience ambience;
        if (fogType == lightning.product.j_39_h$n_1700_B.n_1700_B) {
            return new float[]{fogStart, fogEnd};
        }
        Ambience j_8_l2 = ambience = ClientBootstrap.Y_601_j() != null && ClientBootstrap.Y_601_j().J_1907_R() != null ? ClientBootstrap.Y_601_j().J_1907_R().v_4262_N : null;
        if (ambience == null || !ambience.w_1484_f() || !Ambience.w_1484_f.J_1907_R("Adaptive")) {
            return new float[]{fogStart, fogEnd};
        }
        N_4263_v viewEntity = activeRenderInfo.v_4262_N();
        if (viewEntity == null || viewEntity.O_508_d == null) {
            return new float[]{fogStart, fogEnd};
        }
        float rainStrength = viewEntity.O_508_d.w_1484_f(partialTicks);
        if (Ambience.Q_4569_t.t_148_a().booleanValue()) {
            float minRainStrength = ((Float)Ambience.M_182_A.J_1907_R()).floatValue();
            if (rainStrength <= minRainStrength) {
                return new float[]{fogStart, fogEnd};
            }
            weatherFactor = u_530_F.n_1700_B((rainStrength - minRainStrength) / Math.max(0.001f, 1.0f - minRainStrength), 0.0f, 1.0f);
        } else {
            weatherFactor = u_530_F.n_1700_B(0.35f + rainStrength * 0.65f, 0.35f, 1.0f);
        }
        float radius = ((Float)Ambience.P_4830_p.J_1907_R()).floatValue();
        float strength = ((Float)Ambience.h_1847_R.J_1907_R()).floatValue();
        float factor = u_530_F.n_1700_B(strength * weatherFactor, 0.0f, 1.5f);
        float targetEnd = Math.max(24.0f, radius * (1.15f + rainStrength * 0.85f));
        float targetStart = Math.max(0.0f, targetEnd * 0.32f);
        float newStart = u_530_F.v_4262_N(factor, fogStart, targetStart);
        float newEnd = u_530_F.v_4262_N(factor, fogEnd, targetEnd);
        if (newEnd < newStart + 6.0f) {
            newEnd = newStart + 6.0f;
        }
        return new float[]{newStart, newEnd};
    }

    public static void n_1700_B(h_3572_K activeRenderInfoIn, float partialTicks, k_4690_i worldIn, int renderDistanceChunks, float bossColorModifier) {
        N_4263_v entity1;
        e_2866_D vector3d2;
        boolean lavaHidden;
        Ambience ambience = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
        FluidState fluidstate = activeRenderInfoIn.s_956_w();
        boolean bl = lavaHidden = fluidstate.n_1700_B(FluidTags.R_4764_Y) && j_39_h.R_4764_Y();
        if (!(ambience == null || !ambience.w_1484_f() || !Ambience.w_1484_f.J_1907_R("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c") || fluidstate.n_1700_B(FluidTags.J_1907_R) || fluidstate.n_1700_B(FluidTags.R_4764_Y) && !lavaHidden || activeRenderInfoIn.v_4262_N() instanceof r_4811_B && ((r_4811_B)activeRenderInfoIn.v_4262_N()).J_1907_R(MobEffects.Q_4569_t))) {
            int fogColorValue = Ambience.t_148_a.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? q_3148_R.n_1700_B(K_1200_E.J_1907_R) : (Integer)Ambience.s_956_w.J_1907_R();
            Color fogColor = new Color(fogColorValue);
            n_1700_B = (float)fogColor.getRed() / 255.0f;
            J_1907_R = (float)fogColor.getGreen() / 255.0f;
            R_4764_Y = (float)fogColor.getBlue() / 255.0f;
            v_4262_N = -1L;
        } else if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
            long i = j_3341_s.J_1907_R();
            int j = worldIn.P_1922_E(new c_1514_x(activeRenderInfoIn.J_1907_R())).h_1847_R();
            if (v_4262_N < 0L) {
                P_1922_E = j;
                u_1723_Y = j;
                v_4262_N = i;
            }
            int k = P_1922_E >> 16 & 0xFF;
            int l = P_1922_E >> 8 & 0xFF;
            int i1 = P_1922_E & 0xFF;
            int j1 = u_1723_Y >> 16 & 0xFF;
            int k1 = u_1723_Y >> 8 & 0xFF;
            int l1 = u_1723_Y & 0xFF;
            float f = u_530_F.n_1700_B((float)(i - v_4262_N) / 5000.0f, 0.0f, 1.0f);
            float f1 = u_530_F.v_4262_N(f, j1, k);
            float f2 = u_530_F.v_4262_N(f, k1, l);
            float f3 = u_530_F.v_4262_N(f, l1, i1);
            n_1700_B = f1 / 255.0f;
            J_1907_R = f2 / 255.0f;
            R_4764_Y = f3 / 255.0f;
            if (P_1922_E != j) {
                P_1922_E = j;
                u_1723_Y = u_530_F.G_564_y(f1) << 16 | u_530_F.G_564_y(f2) << 8 | u_530_F.G_564_y(f3);
                v_4262_N = i;
            }
        } else if (fluidstate.n_1700_B(FluidTags.R_4764_Y) && !lavaHidden) {
            n_1700_B = 0.6f;
            J_1907_R = 0.1f;
            R_4764_Y = 0.0f;
            v_4262_N = -1L;
        } else {
            float f16;
            float f4 = 0.25f + 0.75f * (float)renderDistanceChunks / 32.0f;
            f4 = 1.0f - (float)Math.pow(f4, 0.25);
            e_2866_D vector3d = worldIn.n_1700_B(activeRenderInfoIn.R_4764_Y(), partialTicks);
            vector3d = CustomColors.getWorldSkyColor(vector3d, worldIn, activeRenderInfoIn.v_4262_N(), partialTicks);
            float f5 = (float)vector3d.J_1907_R;
            float f8 = (float)vector3d.R_4764_Y;
            float f11 = (float)vector3d.G_564_y;
            float f12 = u_530_F.n_1700_B(u_530_F.J_1907_R(worldIn.G_564_y(partialTicks) * ((float)Math.PI * 2)) * 2.0f + 0.5f, 0.0f, 1.0f);
            BiomeManager biomemanager = worldIn.z_1737_N();
            e_2866_D vector3d3 = activeRenderInfoIn.J_1907_R().n_1700_B(2.0, 2.0, 2.0).n_1700_B(0.25);
            e_2866_D vector3d4 = CubicSampler.n_1700_B(vector3d3, (p_lambda$updateFogColor$0_3_, p_lambda$updateFogColor$0_4_, p_lambda$updateFogColor$0_5_) -> worldIn.n_1700_B().n_1700_B(e_2866_D.n_1700_B(biomemanager.n_1700_B(p_lambda$updateFogColor$0_3_, p_lambda$updateFogColor$0_4_, p_lambda$updateFogColor$0_5_).u_1723_Y()), f12));
            vector3d4 = CustomColors.getWorldFogColor(vector3d4, worldIn, activeRenderInfoIn.v_4262_N(), partialTicks);
            n_1700_B = (float)vector3d4.J_1907_R;
            J_1907_R = (float)vector3d4.R_4764_Y;
            R_4764_Y = (float)vector3d4.G_564_y;
            if (renderDistanceChunks >= 4) {
                float[] afloat;
                float f13 = u_530_F.n_1700_B(worldIn.P_1922_E(partialTicks)) > 0.0f ? -1.0f : 1.0f;
                M_1336_P vector3f = new M_1336_P(f13, 0.0f, 0.0f);
                float f17 = activeRenderInfoIn.P_4830_p().R_4764_Y(vector3f);
                if (f17 < 0.0f) {
                    f17 = 0.0f;
                }
                if (f17 > 0.0f && (afloat = worldIn.n_1700_B().n_1700_B(worldIn.G_564_y(partialTicks), partialTicks)) != null) {
                    n_1700_B = n_1700_B * (1.0f - (f17 *= afloat[3])) + afloat[0] * f17;
                    J_1907_R = J_1907_R * (1.0f - f17) + afloat[1] * f17;
                    R_4764_Y = R_4764_Y * (1.0f - f17) + afloat[2] * f17;
                }
            }
            n_1700_B += (f5 - n_1700_B) * f4;
            J_1907_R += (f8 - J_1907_R) * f4;
            R_4764_Y += (f11 - R_4764_Y) * f4;
            float f14 = worldIn.w_1484_f(partialTicks);
            if (f14 > 0.0f) {
                float f15 = 1.0f - f14 * 0.5f;
                float f18 = 1.0f - f14 * 0.4f;
                n_1700_B *= f15;
                J_1907_R *= f15;
                R_4764_Y *= f18;
            }
            if ((f16 = worldIn.u_1723_Y(partialTicks)) > 0.0f) {
                float f19 = 1.0f - f16 * 0.5f;
                n_1700_B *= f19;
                J_1907_R *= f19;
                R_4764_Y *= f19;
            }
            v_4262_N = -1L;
        }
        double d0 = activeRenderInfoIn.J_1907_R().R_4764_Y * worldIn.Y_259_p().h_1847_R();
        if (activeRenderInfoIn.v_4262_N() instanceof r_4811_B && ((r_4811_B)activeRenderInfoIn.v_4262_N()).J_1907_R(MobEffects.Q_4569_t)) {
            int i2 = ((r_4811_B)activeRenderInfoIn.v_4262_N()).R_4764_Y(MobEffects.Q_4569_t).J_1907_R();
            d0 = i2 < 20 ? (d0 *= (double)(1.0f - (float)i2 / 20.0f)) : 0.0;
        }
        if (d0 < 1.0 && (!fluidstate.n_1700_B(FluidTags.R_4764_Y) || lavaHidden)) {
            if (d0 < 0.0) {
                d0 = 0.0;
            }
            d0 *= d0;
            n_1700_B = (float)((double)n_1700_B * d0);
            J_1907_R = (float)((double)J_1907_R * d0);
            R_4764_Y = (float)((double)R_4764_Y * d0);
        }
        if (bossColorModifier > 0.0f) {
            n_1700_B = n_1700_B * (1.0f - bossColorModifier) + n_1700_B * 0.7f * bossColorModifier;
            J_1907_R = J_1907_R * (1.0f - bossColorModifier) + J_1907_R * 0.6f * bossColorModifier;
            R_4764_Y = R_4764_Y * (1.0f - bossColorModifier) + R_4764_Y * 0.6f * bossColorModifier;
        }
        if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
            float f9;
            float f6 = 0.0f;
            if (activeRenderInfoIn.v_4262_N() instanceof V_772_m) {
                V_772_m clientplayerentity = (V_772_m)activeRenderInfoIn.v_4262_N();
                f6 = clientplayerentity.n_3318_d();
            }
            if (Float.isInfinite(f9 = Math.min(1.0f / n_1700_B, Math.min(1.0f / J_1907_R, 1.0f / R_4764_Y)))) {
                f9 = Math.nextAfter(f9, 0.0);
            }
            n_1700_B = n_1700_B * (1.0f - f6) + n_1700_B * f9 * f6;
            J_1907_R = J_1907_R * (1.0f - f6) + J_1907_R * f9 * f6;
            R_4764_Y = R_4764_Y * (1.0f - f6) + R_4764_Y * f9 * f6;
        } else if (activeRenderInfoIn.v_4262_N() instanceof r_4811_B && ((r_4811_B)activeRenderInfoIn.v_4262_N()).J_1907_R(MobEffects.M_182_A)) {
            float f7 = M_660_m.n_1700_B((r_4811_B)activeRenderInfoIn.v_4262_N(), partialTicks);
            float f10 = Math.min(1.0f / n_1700_B, Math.min(1.0f / J_1907_R, 1.0f / R_4764_Y));
            if (Float.isInfinite(f10)) {
                f10 = Math.nextAfter(f10, 0.0);
            }
            n_1700_B = n_1700_B * (1.0f - f7) + n_1700_B * f10 * f7;
            J_1907_R = J_1907_R * (1.0f - f7) + J_1907_R * f10 * f7;
            R_4764_Y = R_4764_Y * (1.0f - f7) + R_4764_Y * f10 * f7;
        }
        if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
            N_4263_v entity = activeRenderInfoIn.v_4262_N();
            e_2866_D vector3d1 = CustomColors.getUnderwaterColor(worldIn, entity.O_3598_v(), entity.X_2960_b() + 1.0, entity.l_2647_k());
            if (vector3d1 != null) {
                n_1700_B = (float)vector3d1.J_1907_R;
                J_1907_R = (float)vector3d1.R_4764_Y;
                R_4764_Y = (float)vector3d1.G_564_y;
            }
        } else if (fluidstate.n_1700_B(FluidTags.R_4764_Y) && !lavaHidden && (vector3d2 = CustomColors.getUnderlavaColor(worldIn, (entity1 = activeRenderInfoIn.v_4262_N()).O_3598_v(), entity1.X_2960_b() + 1.0, entity1.l_2647_k())) != null) {
            n_1700_B = (float)vector3d2.J_1907_R;
            J_1907_R = (float)vector3d2.R_4764_Y;
            R_4764_Y = (float)vector3d2.G_564_y;
        }
        if (Reflector.EntityViewRenderEvent_FogColors_Constructor.exists()) {
            Object object = Reflector.newInstance(Reflector.EntityViewRenderEvent_FogColors_Constructor, activeRenderInfoIn, Float.valueOf(partialTicks), Float.valueOf(n_1700_B), Float.valueOf(J_1907_R), Float.valueOf(R_4764_Y));
            Reflector.postForgeBusEvent(object);
            n_1700_B = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_FogColors_getRed, new Object[0]);
            J_1907_R = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_FogColors_getGreen, new Object[0]);
            R_4764_Y = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_FogColors_getBlue, new Object[0]);
        }
        Shaders.setClearColor(n_1700_B, J_1907_R, R_4764_Y, 0.0f);
        c_4037_x.J_1907_R(n_1700_B, J_1907_R, R_4764_Y, 0.0f);
    }

    public static void n_1700_B() {
        c_4037_x.n_1700_B(0.0f);
        c_4037_x.n_1700_B(X_933_l.u_2550_I.R_4764_Y);
    }

    public static void n_1700_B(h_3572_K activeRenderInfoIn, n_1700_B fogTypeIn, float farPlaneDistance, boolean nearFog) {
        j_39_h.n_1700_B(activeRenderInfoIn, fogTypeIn, farPlaneDistance, nearFog, 0.0f);
    }

    public static void n_1700_B(h_3572_K p_setupFog_0_, n_1700_B p_setupFog_1_, float p_setupFog_2_, boolean p_setupFog_3_, float p_setupFog_4_) {
        G_564_y = false;
        Ambience ambience = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
        FluidState fluidstate = p_setupFog_0_.s_956_w();
        boolean lavaHidden = fluidstate.n_1700_B(FluidTags.R_4764_Y) && j_39_h.R_4764_Y();
        N_4263_v entity = p_setupFog_0_.v_4262_N();
        float f = -1.0f;
        if (Reflector.ForgeHooksClient_getFogDensity.exists()) {
            f = Reflector.callFloat(Reflector.ForgeHooksClient_getFogDensity, new Object[]{p_setupFog_1_, p_setupFog_0_, Float.valueOf(p_setupFog_4_), Float.valueOf(0.1f)});
        }
        if (f >= 0.0f) {
            X_933_l.n_1700_B(f);
            c_4037_x.n_1700_B(X_933_l.u_2550_I.R_4764_Y);
        } else if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
            float f1 = 0.05f;
            if (entity instanceof V_772_m) {
                V_772_m clientplayerentity = (V_772_m)entity;
                f1 -= clientplayerentity.n_3318_d() * clientplayerentity.n_3318_d() * 0.03f;
                k_594_Q biome = clientplayerentity.O_508_d.P_1922_E(clientplayerentity.b_2312_j());
                if (biome.Y_601_j() == k_594_Q.R_4764_Y.Q_4569_t) {
                    f1 += 0.005f;
                }
            }
            c_4037_x.n_1700_B(f1);
            c_4037_x.n_1700_B(X_933_l.u_2550_I.R_4764_Y);
        } else if (fluidstate.n_1700_B(FluidTags.R_4764_Y) && !lavaHidden) {
            float f4;
            float f3;
            if (entity instanceof r_4811_B && ((r_4811_B)entity).J_1907_R(MobEffects.M_588_G)) {
                f3 = 0.0f;
                f4 = 3.0f;
            } else {
                f3 = 0.25f;
                f4 = 1.0f;
            }
            c_4037_x.J_1907_R(f3);
            c_4037_x.R_4764_Y(f4);
            c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
            c_4037_x.g_221_o();
        } else {
            float f4;
            if (entity instanceof r_4811_B && ((r_4811_B)entity).J_1907_R(MobEffects.Q_4569_t)) {
                float f3;
                h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.multiplayerClientSuggestionProvider);
                A_4115_X.n_1700_B(event);
                if (!event.n_1700_B()) {
                    int i = ((r_4811_B)entity).R_4764_Y(MobEffects.Q_4569_t).J_1907_R();
                    float f2 = u_530_F.v_4262_N(Math.min(1.0f, (float)i / 20.0f), p_setupFog_2_, 5.0f);
                    if (p_setupFog_1_ == lightning.product.j_39_h$n_1700_B.n_1700_B) {
                        f3 = 0.0f;
                        f4 = f2 * 0.8f;
                    } else {
                        f3 = f2 * 0.25f;
                        f4 = f2;
                    }
                } else {
                    G_564_y = true;
                    f3 = p_setupFog_1_ == lightning.product.j_39_h$n_1700_B.n_1700_B ? 0.0f : p_setupFog_2_ * Config.getFogStart();
                    f4 = p_setupFog_2_;
                }
                c_4037_x.J_1907_R(f3);
                c_4037_x.R_4764_Y(f4);
                c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
            } else if (p_setupFog_3_) {
                G_564_y = true;
                float f3 = p_setupFog_2_ * 0.05f;
                f4 = Math.min(p_setupFog_2_, 192.0f) * 0.5f;
                float[] adjustedFog = j_39_h.n_1700_B(f3, f4, p_setupFog_2_, p_setupFog_0_, p_setupFog_1_, p_setupFog_3_, p_setupFog_4_);
                f3 = adjustedFog[0];
                f4 = adjustedFog[1];
                c_4037_x.J_1907_R(f3);
                c_4037_x.R_4764_Y(f4);
                c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
            } else if (p_setupFog_1_ == lightning.product.j_39_h$n_1700_B.n_1700_B) {
                G_564_y = true;
                float f3 = 0.0f;
                f4 = p_setupFog_2_;
                c_4037_x.J_1907_R(f3);
                c_4037_x.R_4764_Y(f4);
                c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
            } else {
                G_564_y = true;
                if (ambience != null && ambience.w_1484_f()) {
                    if (Ambience.w_1484_f.J_1907_R("\u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c")) {
                        c_4037_x.n_1700_B(0.0f);
                        c_4037_x.n_1700_B(X_933_l.u_2550_I.R_4764_Y);
                        return;
                    }
                    if (Ambience.w_1484_f.J_1907_R("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c")) {
                        float f3 = ((Float)Ambience.M_588_G.J_1907_R()).floatValue() * 42.67f;
                        f4 = ((Float)Ambience.u_2550_I.J_1907_R()).floatValue() * 42.67f;
                        f4 = Math.max(f4, f3 + 5.0f);
                        float[] adjustedFog = j_39_h.n_1700_B(f3, f4, p_setupFog_2_, p_setupFog_0_, p_setupFog_1_, p_setupFog_3_, p_setupFog_4_);
                        f3 = adjustedFog[0];
                        f4 = adjustedFog[1];
                        c_4037_x.J_1907_R(f3);
                        c_4037_x.R_4764_Y(f4);
                        c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
                    } else {
                        float f3 = p_setupFog_2_ * Config.getFogStart();
                        f4 = p_setupFog_2_;
                        float[] adjustedFog = j_39_h.n_1700_B(f3, f4, p_setupFog_2_, p_setupFog_0_, p_setupFog_1_, p_setupFog_3_, p_setupFog_4_);
                        f3 = adjustedFog[0];
                        f4 = adjustedFog[1];
                        c_4037_x.J_1907_R(f3);
                        c_4037_x.R_4764_Y(f4);
                        c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
                    }
                } else {
                    float f3 = p_setupFog_2_ * Config.getFogStart();
                    f4 = p_setupFog_2_;
                    float[] adjustedFog = j_39_h.n_1700_B(f3, f4, p_setupFog_2_, p_setupFog_0_, p_setupFog_1_, p_setupFog_3_, p_setupFog_4_);
                    f3 = adjustedFog[0];
                    f4 = adjustedFog[1];
                    c_4037_x.J_1907_R(f3);
                    c_4037_x.R_4764_Y(f4);
                    c_4037_x.n_1700_B(X_933_l.u_2550_I.n_1700_B);
                }
            }
            c_4037_x.g_221_o();
            if (Reflector.ForgeHooksClient_onFogRender.exists()) {
                Reflector.callVoid(Reflector.ForgeHooksClient_onFogRender, new Object[]{p_setupFog_1_, p_setupFog_0_, Float.valueOf(p_setupFog_4_), Float.valueOf(f4)});
            }
        }
    }

    public static void J_1907_R() {
        c_4037_x.n_1700_B(2918, n_1700_B, J_1907_R, R_4764_Y, 1.0f);
        if (Config.isShaders()) {
            Shaders.setFogColor(n_1700_B, J_1907_R, R_4764_Y);
        }
    }

    static {
        P_1922_E = -1;
        u_1723_Y = -1;
        v_4262_N = -1L;
        G_564_y = false;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.j_39_h$n_1700_B.n_1700_B();
        }
    }
}



