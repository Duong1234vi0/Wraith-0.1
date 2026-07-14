/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.D_2364_U;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.IronGolemModel;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;

public class IronGolemCrackinessLayer
extends RenderLayer<D_2364_U, IronGolemModel<D_2364_U>> {
    private static final Map<D_2364_U.n_1700_B, g_2336_b> n_1700_B = ImmutableMap.of((Object)((Object)D_2364_U.n_1700_B.J_1907_R), (Object)new g_2336_b("textures/entity/iron_golem/iron_golem_crackiness_low.png"), (Object)((Object)D_2364_U.n_1700_B.R_4764_Y), (Object)new g_2336_b("textures/entity/iron_golem/iron_golem_crackiness_medium.png"), (Object)((Object)D_2364_U.n_1700_B.G_564_y), (Object)new g_2336_b("textures/entity/iron_golem/iron_golem_crackiness_high.png"));

    public IronGolemCrackinessLayer(j_4203_m<D_2364_U, IronGolemModel<D_2364_U>> p_i226040_1_) {
        super(p_i226040_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, D_2364_U entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        D_2364_U.n_1700_B irongolementity$cracks;
        if (!entitylivingbaseIn.F_3572_x() && (irongolementity$cracks = entitylivingbaseIn.h_1640_b()) != D_2364_U.n_1700_B.n_1700_B) {
            g_2336_b resourcelocation = n_1700_B.get((Object)irongolementity$cracks);
            IronGolemCrackinessLayer.renderCutoutModel(this.getEntityModel(), resourcelocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (D_2364_U)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}


