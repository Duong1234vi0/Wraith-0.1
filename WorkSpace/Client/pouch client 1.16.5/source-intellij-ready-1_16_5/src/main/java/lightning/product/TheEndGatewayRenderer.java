/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4605_O;
import lightning.product.R_4531_p;
import lightning.product.T_2978_m;
import lightning.product.e_933_M;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class TheEndGatewayRenderer
extends T_2978_m<A_4605_O> {
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/entity/end_gateway_beam.png");

    public TheEndGatewayRenderer(f_2689_h p_i226018_1_) {
        super(p_i226018_1_);
    }

    @Override
    public void n_1700_B(A_4605_O tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.v_4262_N() || tileEntityIn.w_1484_f()) {
            float f = tileEntityIn.v_4262_N() ? tileEntityIn.n_1700_B(partialTicks) : tileEntityIn.J_1907_R(partialTicks);
            double d0 = tileEntityIn.v_4262_N() ? 256.0 : 50.0;
            f = u_530_F.n_1700_B(f * (float)Math.PI);
            int i = u_530_F.R_4764_Y((double)f * d0);
            float[] afloat = tileEntityIn.v_4262_N() ? e_933_M.R_4764_Y.G_564_y() : e_933_M.u_2550_I.G_564_y();
            long j = tileEntityIn.c_3005_b().X_933_l();
            R_4531_p.n_1700_B(matrixStackIn, bufferIn, R_4764_Y, partialTicks, f, j, 0, i, afloat, 0.15f, 0.175f);
            R_4531_p.n_1700_B(matrixStackIn, bufferIn, R_4764_Y, partialTicks, f, j, 0, -i, afloat, 0.15f, 0.175f);
        }
        super.n_1700_B(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }

    @Override
    protected int n_1700_B(double p_191286_1_) {
        return super.n_1700_B(p_191286_1_) + 1;
    }

    @Override
    protected float n_1700_B() {
        return 1.0f;
    }
}


