/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.b_4507_u;
import lightning.product.c_3457_g;
import lightning.product.h_3572_K;

public class NoRenderParticle
extends c_3457_g {
    protected NoRenderParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected NoRenderParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public final void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.u_1723_Y;
    }
}


