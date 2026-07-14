/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.M_914_T;
import lightning.product.O_4568_K;
import lightning.product.e_2866_D;
import lightning.product.EnderEyesLayer;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.w_2498_n;

public class EndermanRenderer
extends r_1334_c<M_914_T, w_2498_n<M_914_T>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/enderman/enderman.png");
    private final Random t_1786_h = new Random();

    public EndermanRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new w_2498_n(0.0f), 0.5f);
        this.n_1700_B(new EnderEyesLayer<M_914_T>(this));
        this.n_1700_B(new O_4568_K(this));
    }

    @Override
    public void n_1700_B(M_914_T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        K_4074_S blockstate = entityIn.J_3635_s();
        w_2498_n endermanmodel = (w_2498_n)this.n_1700_B();
        endermanmodel.M_588_G = blockstate != null;
        endermanmodel.P_4830_p = entityIn.o_82_k();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public e_2866_D n_1700_B(M_914_T entityIn, float partialTicks) {
        if (entityIn.o_82_k()) {
            double d0 = 0.02;
            return new e_2866_D(this.t_1786_h.nextGaussian() * 0.02, 0.0, this.t_1786_h.nextGaussian() * 0.02);
        }
        return super.n_1700_B(entityIn, partialTicks);
    }

    @Override
    public g_2336_b n_1700_B(M_914_T entity) {
        return n_1700_B;
    }
}


