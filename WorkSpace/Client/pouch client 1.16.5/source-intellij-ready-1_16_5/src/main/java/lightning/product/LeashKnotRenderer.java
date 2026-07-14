/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.f_1643_e;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;

public class LeashKnotRenderer
extends Z_2049_e<LeashFenceKnotEntity> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/lead_knot.png");
    private final f_1643_e<LeashFenceKnotEntity> v_4262_N = new f_1643_e();

    public LeashKnotRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(LeashFenceKnotEntity entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        this.v_4262_N.n_1700_B(entityIn, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.v_4262_N.getRenderType(n_1700_B));
        this.v_4262_N.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(LeashFenceKnotEntity entity) {
        return n_1700_B;
    }
}


