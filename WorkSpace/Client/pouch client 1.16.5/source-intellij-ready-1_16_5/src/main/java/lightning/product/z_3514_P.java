/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.D_4792_h;
import lightning.product.RenderLayer;
import lightning.product.H_4868_c;
import lightning.product.N_4263_v;
import lightning.product.HorseModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.j_4203_m;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.Horse;

public class z_3514_P
extends RenderLayer<Horse, HorseModel<Horse>> {
    private static final Map<H_4868_c, g_2336_b> n_1700_B = j_3341_s.n_1700_B(Maps.newEnumMap(H_4868_c.class), p_239406_0_ -> {
        p_239406_0_.put(H_4868_c.n_1700_B, null);
        p_239406_0_.put(H_4868_c.J_1907_R, new g_2336_b("textures/entity/horse/horse_markings_white.png"));
        p_239406_0_.put(H_4868_c.R_4764_Y, new g_2336_b("textures/entity/horse/horse_markings_whitefield.png"));
        p_239406_0_.put(H_4868_c.G_564_y, new g_2336_b("textures/entity/horse/horse_markings_whitedots.png"));
        p_239406_0_.put(H_4868_c.P_1922_E, new g_2336_b("textures/entity/horse/horse_markings_blackdots.png"));
    });

    public z_3514_P(j_4203_m<Horse, HorseModel<Horse>> p_i232476_1_) {
        super(p_i232476_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, Horse entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        g_2336_b resourcelocation = n_1700_B.get((Object)entitylivingbaseIn.J_3635_s());
        if (resourcelocation != null && !entitylivingbaseIn.F_3572_x()) {
            D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.w_1484_f(resourcelocation));
            ((HorseModel)this.getEntityModel()).render(matrixStackIn, ivertexbuilder, packedLightIn, o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (Horse)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


