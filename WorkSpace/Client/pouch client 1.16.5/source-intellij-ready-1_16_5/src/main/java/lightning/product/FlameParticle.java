/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.RisingParticle;
import lightning.product.u_530_F;

public class FlameParticle
extends RisingParticle {
    private FlameParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public void n_1700_B(double x, double y, double z) {
        this.n_1700_B(this.P_4830_p().offset(x, y, z));
        this.u_2550_I();
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        float f = ((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j;
        return this.A_4115_X * (1.0f - f * f * 0.5f);
    }

    @Override
    public int n_1700_B(float partialTick) {
        float f = ((float)this.w_1457_N + partialTick) / (float)this.Y_601_j;
        f = u_530_F.n_1700_B(f, 0.0f, 1.0f);
        int i = super.n_1700_B(partialTick);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        if ((j += (int)(f * 15.0f * 16.0f)) > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FlameParticle flameparticle = new FlameParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            flameparticle.n_1700_B(this.n_1700_B);
            return flameparticle;
        }
    }
}


