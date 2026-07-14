/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AmbientParticleSettings;
import lightning.product.AmbientMoodSettings;
import lightning.product.StructureFeature;
import lightning.product.Carvers;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.T_3975_o;
import lightning.product.Features;
import lightning.product.SoundEvents;
import lightning.product.OceanRuinConfiguration;
import lightning.product.MobSpawnSettings;
import lightning.product.StructureFeatures;
import lightning.product.Z_749_F;
import lightning.product.BiomeGenerationSettings;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.Musics;
import lightning.product.AmbientAdditionsSettings;
import lightning.product.BiomeSpecialEffects;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.BiomeDefaultFeatures;
import lightning.product.u_530_F;
import lightning.product.SurfaceBuilders;

public class j_154_J {
    private static int n_1700_B(float temperature) {
        float lvt_1_1_ = temperature / 3.0f;
        lvt_1_1_ = u_530_F.n_1700_B(lvt_1_1_, -1.0f, 1.0f);
        return u_530_F.u_1723_Y(0.62222224f - lvt_1_1_ * 0.05f, 0.5f + lvt_1_1_ * 0.1f, 1.0f);
    }

    public static k_594_Q n_1700_B(float depth, float scale, float temperature, boolean isSpruceVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.j_2266_I, 8, 4, 4));
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.UploadStatus, 4, 2, 3));
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.A_4115_X, 8, 2, 4));
        if (isSpruceVariant) {
            BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        } else {
            BiomeDefaultFeatures.J_1907_R(mobspawninfo$builder);
            BiomeDefaultFeatures.J_1907_R(mobspawninfo$builder, 100, 25, 100);
        }
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.t_148_a);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.M_182_A(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_1786_h(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, isSpruceVariant ? Features.l_697_B : Features.d_3244_b);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_164_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1457_N(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.J_1907_R).n_1700_B(depth).J_1907_R(scale).R_4764_Y(temperature).G_564_y(0.8f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(temperature)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, boolean isTallVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_2506_i(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (isTallVariant) {
            BiomeDefaultFeatures.Z_875_P(biomegenerationsettings$builder);
        } else {
            BiomeDefaultFeatures.k_2293_S(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.q_4610_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.u_2550_I).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.6f).G_564_y(0.6f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.6f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B() {
        return j_154_J.n_1700_B(0.1f, 0.2f, 40, 2, 3);
    }

    public static k_594_Q J_1907_R() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.w_1484_f(mobspawninfo$builder);
        return j_154_J.n_1700_B(0.1f, 0.2f, 0.8f, false, true, false, mobspawninfo$builder);
    }

    public static k_594_Q R_4764_Y() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.w_1484_f(mobspawninfo$builder);
        return j_154_J.n_1700_B(0.2f, 0.4f, 0.8f, false, true, true, mobspawninfo$builder);
    }

    public static k_594_Q G_564_y() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.w_1484_f(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.O_508_d, 10, 1, 1)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.s_2632_s, 2, 1, 1));
        return j_154_J.n_1700_B(0.2f, 0.4f, 0.9f, false, false, true, mobspawninfo$builder);
    }

    public static k_594_Q P_1922_E() {
        return j_154_J.n_1700_B(0.45f, 0.3f, 10, 1, 1);
    }

    public static k_594_Q u_1723_Y() {
        return j_154_J.n_1700_B(0.1f, 0.2f, 40, 2);
    }

    public static k_594_Q v_4262_N() {
        return j_154_J.n_1700_B(0.45f, 0.3f, 10, 1);
    }

    private static k_594_Q n_1700_B(float depth, float scale, int parrotWeight, int parrotMaxCount, int ocelotMaxCount) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.w_1484_f(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.O_508_d, parrotWeight, 1, parrotMaxCount)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.s_2632_s, 2, 1, ocelotMaxCount)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.z_1333_t, 1, 1, 2));
        mobspawninfo$builder.n_1700_B();
        return j_154_J.n_1700_B(depth, scale, 0.9f, false, false, false, mobspawninfo$builder);
    }

    private static k_594_Q n_1700_B(float depth, float scale, int parrotWeight, int parrotMaxCount) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.w_1484_f(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.O_508_d, parrotWeight, 1, parrotMaxCount)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.z_1333_t, 80, 1, 2)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.s_2632_s, 2, 1, 1));
        return j_154_J.n_1700_B(depth, scale, 0.9f, true, false, false, mobspawninfo$builder);
    }

    private static k_594_Q n_1700_B(float depth, float scale, float downfall, boolean hasOnlyBambooVegetation, boolean isEdgeBiome, boolean isModified, MobSpawnSettings.n_1700_B mobSpawnBuilder) {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        if (!isEdgeBiome && !isModified) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.P_1922_E);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.c_3005_b);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (hasOnlyBambooVegetation) {
            BiomeDefaultFeatures.Y_259_p(biomegenerationsettings$builder);
        } else {
            if (!isEdgeBiome && !isModified) {
                BiomeDefaultFeatures.Y_601_j(biomegenerationsettings$builder);
            }
            if (isEdgeBiome) {
                BiomeDefaultFeatures.x_607_J(biomegenerationsettings$builder);
            } else {
                BiomeDefaultFeatures.t_4043_B(biomegenerationsettings$builder);
            }
        }
        BiomeDefaultFeatures.Z_976_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.d_2427_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_2632_s(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.G_564_y).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.95f).G_564_y(downfall).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.95f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobSpawnBuilder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> surfaceBuilder, boolean isEdgeBiome) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.g_221_o, 5, 4, 6));
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(surfaceBuilder);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.A_4115_X);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (isEdgeBiome) {
            BiomeDefaultFeatures.Y_1740_V(biomegenerationsettings$builder);
        } else {
            BiomeDefaultFeatures.A_4115_X(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.M_588_G(biomegenerationsettings$builder);
        BiomeDefaultFeatures.P_4830_p(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.R_4764_Y).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.2f).G_564_y(0.3f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.2f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, boolean hasVillageAndOutpost, boolean hasDesertPyramid, boolean hasFossils) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.u_1723_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.G_564_y);
        if (hasVillageAndOutpost) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.Y_259_p);
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.n_1700_B);
        }
        if (hasDesertPyramid) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.u_1723_Y);
        }
        if (hasFossils) {
            BiomeDefaultFeatures.r_715_M(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.Z_875_P);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.v_4262_N(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.B_1668_F(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.l_1233_K(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.O_508_d(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.P_4830_p).n_1700_B(depth).J_1907_R(scale).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(boolean isSunflowerVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.G_564_y(mobspawninfo$builder);
        if (!isSunflowerVariant) {
            mobspawninfo$builder.n_1700_B();
        }
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        if (!isSunflowerVariant) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.Y_601_j).n_1700_B(StructureFeatures.n_1700_B);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.c_4037_x(biomegenerationsettings$builder);
        if (isSunflowerVariant) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.D_60_a);
        }
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.e_2887_G(biomegenerationsettings$builder);
        if (isSunflowerVariant) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.RetryCallException);
        }
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        if (isSunflowerVariant) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.k_3961_g);
        } else {
            BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.u_1723_Y).n_1700_B(0.125f).J_1907_R(0.05f).R_4764_Y(0.8f).G_564_y(0.4f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.8f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    private static k_594_Q n_1700_B(BiomeGenerationSettings.n_1700_B generationSettingsBuilder) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.t_148_a(mobspawninfo$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_148_a).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(0.5f).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(0xA080A0).G_564_y(0).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(generationSettingsBuilder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q w_1484_f() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.P_1922_E);
        return j_154_J.n_1700_B(biomegenerationsettings$builder);
    }

    public static k_594_Q t_148_a() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.P_1922_E).n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.n_1700_B);
        return j_154_J.n_1700_B(biomegenerationsettings$builder);
    }

    public static k_594_Q s_956_w() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.P_1922_E).n_1700_B(StructureFeatures.t_1786_h);
        return j_154_J.n_1700_B(biomegenerationsettings$builder);
    }

    public static k_594_Q u_2550_I() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.P_1922_E).n_1700_B(StructureFeatures.t_1786_h).n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.J_1907_R).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.G_564_y);
        return j_154_J.n_1700_B(biomegenerationsettings$builder);
    }

    public static k_594_Q M_588_G() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.P_1922_E).n_1700_B(T_3975_o.J_1907_R.n_1700_B, Features.u_1723_Y);
        return j_154_J.n_1700_B(biomegenerationsettings$builder);
    }

    public static k_594_Q n_1700_B(float depth, float scale) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.v_4262_N(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.h_1847_R);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_221_o(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.M_182_A).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.9f).G_564_y(1.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.9f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    private static k_594_Q n_1700_B(float depth, float scale, float temperature, boolean isHighland, boolean isShatteredSavanna, MobSpawnSettings.n_1700_B mobSpawnBuilder) {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(isShatteredSavanna ? SurfaceBuilders.multiplayerClientSuggestionProvider : SurfaceBuilders.s_956_w);
        if (!isHighland && !isShatteredSavanna) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.Q_2552_b).n_1700_B(StructureFeatures.n_1700_B);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(isHighland ? StructureFeatures.A_4115_X : StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        if (!isShatteredSavanna) {
            BiomeDefaultFeatures.z_1737_N(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (isShatteredSavanna) {
            BiomeDefaultFeatures.H_2857_Y(biomegenerationsettings$builder);
            BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
            BiomeDefaultFeatures.v_4276_D(biomegenerationsettings$builder);
        } else {
            BiomeDefaultFeatures.c_3005_b(biomegenerationsettings$builder);
            BiomeDefaultFeatures.Z_976_R(biomegenerationsettings$builder);
            BiomeDefaultFeatures.d_2461_k(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.v_4262_N).n_1700_B(depth).J_1907_R(scale).R_4764_Y(temperature).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(temperature)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobSpawnBuilder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, float temperature, boolean isHighland, boolean isShatteredSavanna) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = j_154_J.k_2293_S();
        return j_154_J.n_1700_B(depth, scale, temperature, isHighland, isShatteredSavanna, mobspawninfo$builder);
    }

    private static MobSpawnSettings.n_1700_B k_2293_S() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.n_3318_d, 1, 2, 6)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.Q_4569_t, 1, 1, 1));
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        return mobspawninfo$builder;
    }

    public static k_594_Q P_4830_p() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = j_154_J.k_2293_S();
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.g_221_o, 8, 4, 4));
        return j_154_J.n_1700_B(1.5f, 0.025f, 1.0f, true, false, mobspawninfo$builder);
    }

    private static k_594_Q n_1700_B(ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> surfaceBuilder, float depth, float scale, boolean isHighland, boolean hasOakTrees) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(surfaceBuilder);
        BiomeDefaultFeatures.n_1700_B(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(isHighland ? StructureFeatures.A_4115_X : StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_2550_I(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (hasOakTrees) {
            BiomeDefaultFeatures.e_4240_b(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.G_624_v(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.D_4792_h(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.P_1922_E).n_1700_B(depth).J_1907_R(scale).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(2.0f)).P_1922_E(10387789).u_1723_Y(9470285).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q J_1907_R(float depth, float scale, boolean isHighland) {
        return j_154_J.n_1700_B(SurfaceBuilders.n_1700_B, depth, scale, isHighland, false);
    }

    public static k_594_Q J_1907_R(float depth, float scale) {
        return j_154_J.n_1700_B(SurfaceBuilders.C_2741_M, depth, scale, true, true);
    }

    public static k_594_Q h_1847_R() {
        return j_154_J.n_1700_B(SurfaceBuilders.u_1723_Y, 0.1f, 0.2f, true, false);
    }

    private static k_594_Q n_1700_B(MobSpawnSettings.n_1700_B mobSpawnBuilder, int waterColor, int waterFogColor, boolean isDeepVariant, BiomeGenerationSettings.n_1700_B generationSettingsBuilder) {
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.M_588_G).n_1700_B(isDeepVariant ? -1.8f : -1.0f).J_1907_R(0.1f).R_4764_Y(0.5f).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(waterColor).R_4764_Y(waterFogColor).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.5f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobSpawnBuilder.J_1907_R()).n_1700_B(generationSettingsBuilder.n_1700_B()).n_1700_B();
    }

    private static BiomeGenerationSettings.n_1700_B n_1700_B(ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> surfaceBuilder, boolean hasOceanMonument, boolean isWarmOcean, boolean isDeepVariant) {
        ConfiguredStructureFeature<OceanRuinConfiguration, ? extends StructureFeature<OceanRuinConfiguration>> structurefeature;
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(surfaceBuilder);
        ConfiguredStructureFeature<OceanRuinConfiguration, ? extends StructureFeature<OceanRuinConfiguration>> k_2140_p2 = structurefeature = isWarmOcean ? StructureFeatures.h_1847_R : StructureFeatures.P_4830_p;
        if (isDeepVariant) {
            if (hasOceanMonument) {
                biomegenerationsettings$builder.n_1700_B(StructureFeatures.M_588_G);
            }
            BiomeDefaultFeatures.R_4764_Y(biomegenerationsettings$builder);
            biomegenerationsettings$builder.n_1700_B(structurefeature);
        } else {
            biomegenerationsettings$builder.n_1700_B(structurefeature);
            if (hasOceanMonument) {
                biomegenerationsettings$builder.n_1700_B(StructureFeatures.M_588_G);
            }
            BiomeDefaultFeatures.R_4764_Y(biomegenerationsettings$builder);
        }
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.Y_1740_V);
        BiomeDefaultFeatures.P_1922_E(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.C_2741_M(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        return biomegenerationsettings$builder;
    }

    public static k_594_Q J_1907_R(boolean isDeepVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 3, 4, 15);
        mobspawninfo$builder.n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.D_60_a, 15, 1, 5));
        boolean flag = !isDeepVariant;
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = j_154_J.n_1700_B(SurfaceBuilders.s_956_w, isDeepVariant, false, flag);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, isDeepVariant ? Features.Y_259_p : Features.Y_601_j);
        BiomeDefaultFeatures.i_1637_u(biomegenerationsettings$builder);
        BiomeDefaultFeatures.A_1038_p(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return j_154_J.n_1700_B(mobspawninfo$builder, 4020182, 329011, isDeepVariant, biomegenerationsettings$builder);
    }

    public static k_594_Q R_4764_Y(boolean isDeepVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 1, 4, 10);
        mobspawninfo$builder.n_1700_B(Z_749_F.G_564_y, new MobSpawnSettings.R_4764_Y(t_5_h.h_1847_R, 1, 1, 2));
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = j_154_J.n_1700_B(SurfaceBuilders.s_956_w, isDeepVariant, false, true);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, isDeepVariant ? Features.k_2293_S : Features.Q_2552_b);
        BiomeDefaultFeatures.i_1637_u(biomegenerationsettings$builder);
        BiomeDefaultFeatures.A_1038_p(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return j_154_J.n_1700_B(mobspawninfo$builder, 4159204, 329011, isDeepVariant, biomegenerationsettings$builder);
    }

    public static k_594_Q G_564_y(boolean isDeepVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        if (isDeepVariant) {
            BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 8, 4, 8);
        } else {
            BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 10, 2, 15);
        }
        mobspawninfo$builder.n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.j_276_v, 5, 1, 3)).n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.S_4022_R, 25, 8, 8)).n_1700_B(Z_749_F.G_564_y, new MobSpawnSettings.R_4764_Y(t_5_h.h_1847_R, 2, 1, 2));
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = j_154_J.n_1700_B(SurfaceBuilders.t_1786_h, isDeepVariant, true, false);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, isDeepVariant ? Features.c_3005_b : Features.Z_875_P);
        if (isDeepVariant) {
            BiomeDefaultFeatures.i_1637_u(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.Ping(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return j_154_J.n_1700_B(mobspawninfo$builder, 4566514, 267827, isDeepVariant, biomegenerationsettings$builder);
    }

    public static k_594_Q Q_4569_t() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.j_276_v, 15, 1, 3));
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 10, 4);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = j_154_J.n_1700_B(SurfaceBuilders.w_1484_f, false, true, false).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.h_2739_B).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.Z_875_P).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.H_2857_Y);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return j_154_J.n_1700_B(mobspawninfo$builder, 4445678, 270131, false, biomegenerationsettings$builder);
    }

    public static k_594_Q M_182_A() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder, 5, 1);
        mobspawninfo$builder.n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.t_1786_h, 5, 1, 1));
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = j_154_J.n_1700_B(SurfaceBuilders.w_1484_f, true, true, false).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.c_3005_b);
        BiomeDefaultFeatures.i_1637_u(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return j_154_J.n_1700_B(mobspawninfo$builder, 4445678, 270131, true, biomegenerationsettings$builder);
    }

    public static k_594_Q P_1922_E(boolean isDeepVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.G_564_y, new MobSpawnSettings.R_4764_Y(t_5_h.j_1564_a, 1, 1, 4)).n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.D_60_a, 15, 1, 5)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsClientConfig, 1, 1, 2));
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.t_1786_h, 5, 1, 1));
        float f = isDeepVariant ? 0.5f : 0.0f;
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.v_4262_N);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.P_4830_p);
        if (isDeepVariant) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.M_588_G);
        }
        BiomeDefaultFeatures.R_4764_Y(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.Y_1740_V);
        BiomeDefaultFeatures.P_1922_E(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.RealmsClientConfig(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.f_4016_n(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.C_2741_M(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(isDeepVariant ? k_594_Q.P_1922_E.J_1907_R : k_594_Q.P_1922_E.R_4764_Y).n_1700_B(k_594_Q.R_4764_Y.M_588_G).n_1700_B(isDeepVariant ? -1.8f : -1.0f).J_1907_R(0.1f).R_4764_Y(f).n_1700_B(k_594_Q.u_1723_Y.J_1907_R).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(3750089).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    private static k_594_Q n_1700_B(float depth, float scale, boolean isFlowerForestVariant, MobSpawnSettings.n_1700_B mobSpawnBuilder) {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        if (isFlowerForestVariant) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.o_2767_H);
        } else {
            BiomeDefaultFeatures.T_2506_i(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        if (isFlowerForestVariant) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.X_1313_W);
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.k_2302_P);
            BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        } else {
            BiomeDefaultFeatures.q_2307_F(biomegenerationsettings$builder);
            BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
            BiomeDefaultFeatures.q_4610_l(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.u_2550_I).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.7f).G_564_y(0.8f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.7f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobSpawnBuilder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    private static MobSpawnSettings.n_1700_B q_2307_F() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        return mobspawninfo$builder;
    }

    public static k_594_Q R_4764_Y(float depth, float scale) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = j_154_J.q_2307_F().n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.j_2266_I, 5, 4, 4)).n_1700_B();
        return j_154_J.n_1700_B(depth, scale, false, mobspawninfo$builder);
    }

    public static k_594_Q t_1786_h() {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = j_154_J.q_2307_F().n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.UploadStatus, 4, 2, 3));
        return j_154_J.n_1700_B(0.1f, 0.4f, true, mobspawninfo$builder);
    }

    public static k_594_Q n_1700_B(float depth, float scale, boolean isSnowyVariant, boolean isMountainVariant, boolean hasVillageAndOutpost, boolean hasIgloos) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.j_2266_I, 8, 4, 4)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.UploadStatus, 4, 2, 3)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.A_4115_X, 8, 2, 4));
        if (!isSnowyVariant && !isMountainVariant) {
            mobspawninfo$builder.n_1700_B();
        }
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        float f = isSnowyVariant ? -0.5f : 0.25f;
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        if (hasVillageAndOutpost) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.k_2293_S);
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.n_1700_B);
        }
        if (hasIgloos) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.v_4262_N);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(isMountainVariant ? StructureFeatures.A_4115_X : StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_1786_h(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.Q_2552_b(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.N_2525_X(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        if (isSnowyVariant) {
            BiomeDefaultFeatures.multiplayerClientSuggestionProvider(biomegenerationsettings$builder);
        } else {
            BiomeDefaultFeatures.w_1457_N(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(isSnowyVariant ? k_594_Q.P_1922_E.R_4764_Y : k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.J_1907_R).n_1700_B(depth).J_1907_R(scale).R_4764_Y(f).G_564_y(isSnowyVariant ? 0.4f : 0.8f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(isSnowyVariant ? 4020182 : 4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q R_4764_Y(float depth, float scale, boolean isHillsVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.G_564_y);
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, isHillsVariant ? Features.i_601_W : Features.x_92_N);
        BiomeDefaultFeatures.T_2506_i(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.q_4610_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.u_2550_I).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.7f).G_564_y(0.8f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.7f)).n_1700_B(BiomeSpecialEffects.J_1907_R.J_1907_R).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q G_564_y(float depth, float scale, boolean isHillsVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        BiomeDefaultFeatures.n_1700_B(mobspawninfo$builder);
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.V_1225_t, 1, 1, 1));
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.Y_259_p);
        if (!isHillsVariant) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.s_956_w);
        }
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.J_1907_R);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.H_2857_Y);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        if (!isHillsVariant) {
            BiomeDefaultFeatures.r_715_M(biomegenerationsettings$builder);
        }
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.Q_4569_t(biomegenerationsettings$builder);
        BiomeDefaultFeatures.z_4693_k(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.z_1333_t(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        if (isHillsVariant) {
            BiomeDefaultFeatures.r_715_M(biomegenerationsettings$builder);
        } else {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.q_2307_F);
        }
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.Q_4569_t).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.8f).G_564_y(0.9f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(6388580).R_4764_Y(2302743).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.8f)).P_1922_E(6975545).n_1700_B(BiomeSpecialEffects.J_1907_R.R_4764_Y).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, boolean isIceSpikesBiome, boolean isMountainVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B().n_1700_B(0.07f);
        BiomeDefaultFeatures.P_1922_E(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(isIceSpikesBiome ? SurfaceBuilders.M_588_G : SurfaceBuilders.s_956_w);
        if (!isIceSpikesBiome && !isMountainVariant) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.C_2741_M).n_1700_B(StructureFeatures.v_4262_N);
        }
        BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        if (!isIceSpikesBiome && !isMountainVariant) {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.n_1700_B);
        }
        biomegenerationsettings$builder.n_1700_B(isMountainVariant ? StructureFeatures.A_4115_X : StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        if (isIceSpikesBiome) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.A_4115_X);
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.Y_1740_V);
        }
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.n_3318_d(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.R_4764_Y).n_1700_B(k_594_Q.R_4764_Y.w_1484_f).n_1700_B(depth).J_1907_R(scale).R_4764_Y(0.0f).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.0f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, float temperature, int waterColor, boolean isSnowy) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.G_564_y, new MobSpawnSettings.R_4764_Y(t_5_h.j_1564_a, 2, 1, 4)).n_1700_B(Z_749_F.P_1922_E, new MobSpawnSettings.R_4764_Y(t_5_h.D_60_a, 5, 1, 5));
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        mobspawninfo$builder.n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.t_1786_h, isSnowy ? 1 : 100, 1, 1));
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.s_956_w);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.J_1907_R);
        biomegenerationsettings$builder.n_1700_B(StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.C_2741_M(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        if (!isSnowy) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.C_2741_M);
        }
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(isSnowy ? k_594_Q.P_1922_E.R_4764_Y : k_594_Q.P_1922_E.J_1907_R).n_1700_B(k_594_Q.R_4764_Y.h_1847_R).n_1700_B(depth).J_1907_R(scale).R_4764_Y(temperature).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(waterColor).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(temperature)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q n_1700_B(float depth, float scale, float temperature, float downfall, int waterColor, boolean isColdBiome, boolean isStoneVariant) {
        MobSpawnSettings.n_1700_B mobspawninfo$builder = new MobSpawnSettings.n_1700_B();
        if (!isStoneVariant && !isColdBiome) {
            mobspawninfo$builder.n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.l_4537_E, 5, 2, 5));
        }
        BiomeDefaultFeatures.R_4764_Y(mobspawninfo$builder);
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(isStoneVariant ? SurfaceBuilders.Y_601_j : SurfaceBuilders.G_564_y);
        if (isStoneVariant) {
            BiomeDefaultFeatures.J_1907_R(biomegenerationsettings$builder);
        } else {
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.J_1907_R);
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.multiplayerClientSuggestionProvider);
            biomegenerationsettings$builder.n_1700_B(StructureFeatures.t_148_a);
        }
        biomegenerationsettings$builder.n_1700_B(isStoneVariant ? StructureFeatures.A_4115_X : StructureFeatures.q_2307_F);
        BiomeDefaultFeatures.G_564_y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.u_1723_Y(biomegenerationsettings$builder);
        BiomeDefaultFeatures.w_1484_f(biomegenerationsettings$builder);
        BiomeDefaultFeatures.t_148_a(biomegenerationsettings$builder);
        BiomeDefaultFeatures.s_956_w(biomegenerationsettings$builder);
        BiomeDefaultFeatures.h_1847_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.X_933_l(biomegenerationsettings$builder);
        BiomeDefaultFeatures.H_1990_U(biomegenerationsettings$builder);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        BiomeDefaultFeatures.T_3594_S(biomegenerationsettings$builder);
        BiomeDefaultFeatures.p_178_J(biomegenerationsettings$builder);
        BiomeDefaultFeatures.j_276_v(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(isColdBiome ? k_594_Q.P_1922_E.R_4764_Y : k_594_Q.P_1922_E.J_1907_R).n_1700_B(isStoneVariant ? k_594_Q.R_4764_Y.n_1700_B : k_594_Q.R_4764_Y.s_956_w).n_1700_B(depth).J_1907_R(scale).R_4764_Y(temperature).G_564_y(downfall).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(waterColor).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(temperature)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(mobspawninfo$builder.J_1907_R()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q multiplayerClientSuggestionProvider() {
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.M_182_A);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.s_956_w, Features.Z_976_R);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.n_1700_B).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(0.5f).G_564_y(0.5f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(12638463).G_564_y(j_154_J.n_1700_B(0.5f)).n_1700_B(AmbientMoodSettings.J_1907_R).n_1700_B()).n_1700_B(MobSpawnSettings.J_1907_R).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q w_1457_N() {
        MobSpawnSettings mobspawninfo = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_1740_V, 50, 4, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.c_132_F, 100, 4, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.B_1668_F, 2, 4, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_259_p, 1, 4, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.i_1637_u, 15, 4, 4)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsWorldOptions, 60, 1, 2)).J_1907_R();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.Q_4569_t).n_1700_B(StructureFeatures.t_4043_B).n_1700_B(StructureFeatures.Q_4569_t).n_1700_B(StructureFeatures.w_1457_N).n_1700_B(T_3975_o.n_1700_B.n_1700_B, Carvers.u_1723_Y).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.T_3594_S);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.z_1333_t).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientConfig).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.f_4016_n).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.M_588_G).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.P_4830_p).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.r_3651_U).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RowButton).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientOutdatedScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.s_2632_s);
        BiomeDefaultFeatures.UploadStatus(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_1786_h).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(0x330808).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(SoundEvents.t_148_a).n_1700_B(new AmbientMoodSettings(SoundEvents.s_956_w, 6000, 8, 2.0)).n_1700_B(new AmbientAdditionsSettings(SoundEvents.w_1484_f, 0.0111)).n_1700_B(Musics.n_1700_B(SoundEvents.AutoLes)).n_1700_B()).n_1700_B(mobspawninfo).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q Y_601_j() {
        double d0 = 0.7;
        double d1 = 0.15;
        MobSpawnSettings mobspawninfo = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.V_1446_Y, 20, 5, 5)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_1740_V, 50, 4, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_259_p, 1, 4, 4)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsWorldOptions, 60, 1, 2)).n_1700_B(t_5_h.V_1446_Y, 0.7, 0.15).n_1700_B(t_5_h.Y_1740_V, 0.7, 0.15).n_1700_B(t_5_h.Y_259_p, 0.7, 0.15).n_1700_B(t_5_h.RealmsWorldOptions, 0.7, 0.15).J_1907_R();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.w_1457_N).n_1700_B(StructureFeatures.Q_4569_t).n_1700_B(StructureFeatures.M_182_A).n_1700_B(StructureFeatures.t_4043_B).n_1700_B(StructureFeatures.w_1457_N).n_1700_B(T_3975_o.n_1700_B.n_1700_B, Carvers.u_1723_Y).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.T_3594_S).n_1700_B(T_3975_o.J_1907_R.R_4764_Y, Features.w_1457_N).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.z_1333_t).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.M_588_G).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.P_4830_p).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.e_1992_r).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientConfig).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.f_4016_n).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientOutdatedScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.s_2632_s).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.W_3464_O);
        BiomeDefaultFeatures.UploadStatus(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_1786_h).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(1787717).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(new AmbientParticleSettings(ParticleTypes.f_4016_n, 0.00625f)).n_1700_B(SoundEvents.M_588_G).n_1700_B(new AmbientMoodSettings(SoundEvents.P_4830_p, 6000, 8, 2.0)).n_1700_B(new AmbientAdditionsSettings(SoundEvents.u_2550_I, 0.0111)).n_1700_B(Musics.n_1700_B(SoundEvents.AutoPilot)).n_1700_B()).n_1700_B(mobspawninfo).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q Y_259_p() {
        MobSpawnSettings mobspawninfo = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_1740_V, 40, 1, 1)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.B_1668_F, 100, 2, 5)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsWorldOptions, 60, 1, 2)).J_1907_R();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.J_1907_R).n_1700_B(StructureFeatures.t_4043_B).n_1700_B(T_3975_o.n_1700_B.n_1700_B, Carvers.u_1723_Y).n_1700_B(StructureFeatures.Q_4569_t).n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.v_4262_N).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.g_2268_R).n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.w_1484_f).n_1700_B(T_3975_o.J_1907_R.P_1922_E, Features.t_148_a).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.s_956_w).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.u_2550_I).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.D_4792_h).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientConfig).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.f_4016_n).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.M_588_G).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.P_4830_p).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.r_3651_U).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RowButton).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientOutdatedScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.l_1233_K).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsConfirmScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsCreateRealmScreen);
        BiomeDefaultFeatures.e_1992_r(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_1786_h).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(4341314).n_1700_B(6840176).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(new AmbientParticleSettings(ParticleTypes.h_4320_q, 0.118093334f)).n_1700_B(SoundEvents.R_4764_Y).n_1700_B(new AmbientMoodSettings(SoundEvents.G_564_y, 6000, 8, 2.0)).n_1700_B(new AmbientAdditionsSettings(SoundEvents.J_1907_R, 0.0111)).n_1700_B(Musics.n_1700_B(SoundEvents.AutoLeave)).n_1700_B()).n_1700_B(mobspawninfo).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q Q_2552_b() {
        MobSpawnSettings mobspawninfo = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.c_132_F, 1, 2, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.e_4240_b, 9, 3, 4)).n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.i_1637_u, 5, 3, 4)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsWorldOptions, 60, 1, 2)).J_1907_R();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.R_4764_Y).n_1700_B(StructureFeatures.t_4043_B).n_1700_B(T_3975_o.n_1700_B.n_1700_B, Carvers.u_1723_Y).n_1700_B(StructureFeatures.Q_4569_t).n_1700_B(StructureFeatures.w_1457_N).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.T_3594_S);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.z_1333_t).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientConfig).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.M_588_G).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.P_4830_p).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientOutdatedScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.s_2632_s).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.multiplayerClientSuggestionProvider).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.u_55_V).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.h_1847_R);
        BiomeDefaultFeatures.UploadStatus(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_1786_h).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(0x330303).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(new AmbientParticleSettings(ParticleTypes.j_276_v, 0.025f)).n_1700_B(SoundEvents.u_1723_Y).n_1700_B(new AmbientMoodSettings(SoundEvents.v_4262_N, 6000, 8, 2.0)).n_1700_B(new AmbientAdditionsSettings(SoundEvents.P_1922_E, 0.0111)).n_1700_B(Musics.n_1700_B(SoundEvents.AutoPotion)).n_1700_B()).n_1700_B(mobspawninfo).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }

    public static k_594_Q C_2741_M() {
        MobSpawnSettings mobspawninfo = new MobSpawnSettings.n_1700_B().n_1700_B(Z_749_F.n_1700_B, new MobSpawnSettings.R_4764_Y(t_5_h.Y_259_p, 1, 4, 4)).n_1700_B(Z_749_F.J_1907_R, new MobSpawnSettings.R_4764_Y(t_5_h.RealmsWorldOptions, 60, 1, 2)).n_1700_B(t_5_h.Y_259_p, 1.0, 0.12).J_1907_R();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(SurfaceBuilders.Q_2552_b).n_1700_B(StructureFeatures.Q_4569_t).n_1700_B(StructureFeatures.w_1457_N).n_1700_B(StructureFeatures.t_4043_B).n_1700_B(T_3975_o.n_1700_B.n_1700_B, Carvers.u_1723_Y).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.T_3594_S);
        BiomeDefaultFeatures.g_2268_R(biomegenerationsettings$builder);
        biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.z_1333_t).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientConfig).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.f_4016_n).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.M_588_G).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.P_4830_p).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.RealmsClientOutdatedScreen).n_1700_B(T_3975_o.J_1907_R.w_1484_f, Features.s_2632_s).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.RealmsPersistence).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.Q_4569_t).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.M_182_A).n_1700_B(T_3975_o.J_1907_R.t_148_a, Features.t_1786_h);
        BiomeDefaultFeatures.UploadStatus(biomegenerationsettings$builder);
        return new k_594_Q.J_1907_R().n_1700_B(k_594_Q.P_1922_E.n_1700_B).n_1700_B(k_594_Q.R_4764_Y.t_1786_h).n_1700_B(0.1f).J_1907_R(0.2f).R_4764_Y(2.0f).G_564_y(0.0f).n_1700_B(new BiomeSpecialEffects.n_1700_B().J_1907_R(4159204).R_4764_Y(329011).n_1700_B(1705242).G_564_y(j_154_J.n_1700_B(2.0f)).n_1700_B(new AmbientParticleSettings(ParticleTypes.UploadStatus, 0.01428f)).n_1700_B(SoundEvents.Q_4569_t).n_1700_B(new AmbientMoodSettings(SoundEvents.M_182_A, 6000, 8, 2.0)).n_1700_B(new AmbientAdditionsSettings(SoundEvents.h_1847_R, 0.0111)).n_1700_B(Musics.n_1700_B(SoundEvents.AutoRespawn)).n_1700_B()).n_1700_B(mobspawninfo).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B();
    }
}



