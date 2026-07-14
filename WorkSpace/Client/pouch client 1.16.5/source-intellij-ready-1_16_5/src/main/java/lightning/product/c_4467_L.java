/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.MinecartRenderer;
import lightning.product.Z_3224_L;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.o_3091_w;
import lightning.product.r_1637_F;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class c_4467_L
extends MinecartRenderer<r_1637_F> {
    public c_4467_L(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected void n_1700_B(r_1637_F entityIn, float partialTicks, K_4074_S stateIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        int i = entityIn.Q_2552_b();
        if (i > -1 && (float)i - partialTicks + 1.0f < 10.0f) {
            float f = 1.0f - ((float)i - partialTicks + 1.0f) / 10.0f;
            f = u_530_F.n_1700_B(f, 0.0f, 1.0f);
            f *= f;
            f *= f;
            float f1 = 1.0f + f * 0.3f;
            matrixStackIn.n_1700_B(f1, f1, f1);
        }
        c_4467_L.n_1700_B(stateIn, matrixStackIn, bufferIn, packedLightIn, i > -1 && i / 5 % 2 == 0);
    }

    public static void n_1700_B(K_4074_S blockStateIn, g_221_o matrixStackIn, o_3091_w renderTypeBuffer, int combinedLight, boolean doFullBright) {
        int i = doFullBright ? Z_3224_L.n_1700_B(Z_3224_L.n_1700_B(1.0f), 10) : Z_3224_L.n_1700_B;
        if (Config.isShaders() && doFullBright) {
            Shaders.setEntityColor(1.0f, 1.0f, 1.0f, 0.5f);
        }
        MinecraftClient.A_4115_X().z_1333_t().n_1700_B(blockStateIn, matrixStackIn, renderTypeBuffer, combinedLight, i);
        if (Config.isShaders()) {
            Shaders.setEntityColor(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }
}



