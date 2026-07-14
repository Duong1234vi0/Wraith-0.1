/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.q_1803_e;

public class BarrierParticle
extends TextureSheetParticle {
    private BarrierParticle(b_4507_u world, double x, double y, double z, q_1803_e itemProvider) {
        super(world, x, y, z);
        this.n_1700_B(MinecraftClient.A_4115_X().r_715_M().J_1907_R().n_1700_B(itemProvider));
        this.Y_259_p = 0.0f;
        this.Y_601_j = 80;
        this.h_1847_R = false;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.n_1700_B;
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        return 0.5f;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BarrierParticle(worldIn, x, y, z, a_3742_W.N_4890_q.u_1723_Y());
        }
    }
}



