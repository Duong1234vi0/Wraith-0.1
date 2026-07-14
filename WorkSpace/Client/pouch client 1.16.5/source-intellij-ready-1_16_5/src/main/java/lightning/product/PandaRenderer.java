/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.M_1336_P;
import lightning.product.f_863_t;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3013_R;
import lightning.product.j_3341_s;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.PandaModel;

public class PandaRenderer
extends r_1334_c<j_3013_R, PandaModel<j_3013_R>> {
    private static final Map<j_3013_R.G_564_y, g_2336_b> n_1700_B = j_3341_s.n_1700_B(Maps.newEnumMap(j_3013_R.G_564_y.class), p_217776_0_ -> {
        p_217776_0_.put(j_3013_R.G_564_y.n_1700_B, new g_2336_b("textures/entity/panda/panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.J_1907_R, new g_2336_b("textures/entity/panda/lazy_panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.R_4764_Y, new g_2336_b("textures/entity/panda/worried_panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.G_564_y, new g_2336_b("textures/entity/panda/playful_panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.P_1922_E, new g_2336_b("textures/entity/panda/brown_panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.u_1723_Y, new g_2336_b("textures/entity/panda/weak_panda.png"));
        p_217776_0_.put(j_3013_R.G_564_y.v_4262_N, new g_2336_b("textures/entity/panda/aggressive_panda.png"));
    });

    public PandaRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new PandaModel(9, 0.0f), 0.9f);
        this.n_1700_B(new f_863_t(this));
    }

    @Override
    public g_2336_b n_1700_B(j_3013_R entity) {
        return n_1700_B.getOrDefault((Object)entity.A_1306_N(), n_1700_B.get((Object)j_3013_R.G_564_y.n_1700_B));
    }

    @Override
    protected void n_1700_B(j_3013_R entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        float f8;
        float f6;
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (entityLiving.h_1847_R > 0) {
            float f1;
            int i = entityLiving.h_1847_R;
            int j = i + 1;
            float f = 7.0f;
            float f2 = f1 = entityLiving.d_() ? 0.3f : 0.8f;
            if (i < 8) {
                float f3 = (float)(90 * i) / 7.0f;
                float f4 = (float)(90 * j) / 7.0f;
                float f22 = this.n_1700_B(f3, f4, j, partialTicks, 8.0f);
                matrixStackIn.n_1700_B(0.0, (double)((f1 + 0.2f) * (f22 / 90.0f)), 0.0);
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-f22));
            } else if (i < 16) {
                float f13 = ((float)i - 8.0f) / 7.0f;
                float f16 = 90.0f + 90.0f * f13;
                float f5 = 90.0f + 90.0f * ((float)j - 8.0f) / 7.0f;
                float f10 = this.n_1700_B(f16, f5, j, partialTicks, 16.0f);
                matrixStackIn.n_1700_B(0.0, (double)(f1 + 0.2f + (f1 - 0.2f) * (f10 - 90.0f) / 90.0f), 0.0);
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-f10));
            } else if ((float)i < 24.0f) {
                float f14 = ((float)i - 16.0f) / 7.0f;
                float f17 = 180.0f + 90.0f * f14;
                float f19 = 180.0f + 90.0f * ((float)j - 16.0f) / 7.0f;
                float f11 = this.n_1700_B(f17, f19, j, partialTicks, 24.0f);
                matrixStackIn.n_1700_B(0.0, (double)(f1 + f1 * (270.0f - f11) / 90.0f), 0.0);
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-f11));
            } else if (i < 32) {
                float f15 = ((float)i - 24.0f) / 7.0f;
                float f18 = 270.0f + 90.0f * f15;
                float f20 = 270.0f + 90.0f * ((float)j - 24.0f) / 7.0f;
                float f12 = this.n_1700_B(f18, f20, j, partialTicks, 32.0f);
                matrixStackIn.n_1700_B(0.0, (double)(f1 * ((360.0f - f12) / 90.0f)), 0.0);
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-f12));
            }
        }
        if ((f6 = entityLiving.c_3005_b(partialTicks)) > 0.0f) {
            matrixStackIn.n_1700_B(0.0, (double)(0.8f * f6), 0.0);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.v_4262_N(f6, entityLiving.f_4016_n, entityLiving.f_4016_n + 90.0f)));
            matrixStackIn.n_1700_B(0.0, (double)(-1.0f * f6), 0.0);
            if (entityLiving.Module()) {
                float f7 = (float)(Math.cos((double)entityLiving.RealmsWorldResetDto * 1.25) * Math.PI * (double)0.05f);
                matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f7));
                if (entityLiving.d_()) {
                    matrixStackIn.n_1700_B(0.0, (double)0.8f, (double)0.55f);
                }
            }
        }
        if ((f8 = entityLiving.H_2857_Y(partialTicks)) > 0.0f) {
            float f9 = entityLiving.d_() ? 0.5f : 1.3f;
            matrixStackIn.n_1700_B(0.0, (double)(f9 * f8), 0.0);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.v_4262_N(f8, entityLiving.f_4016_n, entityLiving.f_4016_n + 180.0f)));
        }
    }

    private float n_1700_B(float p_217775_1_, float p_217775_2_, int p_217775_3_, float p_217775_4_, float p_217775_5_) {
        return (float)p_217775_3_ < p_217775_5_ ? u_530_F.v_4262_N(p_217775_4_, p_217775_1_, p_217775_2_) : p_217775_1_;
    }
}



