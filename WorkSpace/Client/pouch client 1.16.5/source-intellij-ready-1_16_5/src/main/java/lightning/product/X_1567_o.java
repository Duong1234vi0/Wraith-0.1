/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.BaseAshSmokeParticle;

public class X_1567_o
extends BaseAshSmokeParticle {
    protected X_1567_o(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, SpriteSet spriteWithAge) {
        super(world, x, y, z, 0.1f, -0.1f, 0.1f, motionX, motionY, motionZ, scale, spriteWithAge, 0.0f, 20, -5.0E-4, false);
        this.Q_2552_b = 0.7294118f;
        this.C_2741_M = 0.69411767f;
        this.k_2293_S = 0.7607843f;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Random random = worldIn.w_1457_N;
            double d0 = (double)random.nextFloat() * -1.9 * (double)random.nextFloat() * 0.1;
            double d1 = (double)random.nextFloat() * -0.5 * (double)random.nextFloat() * 0.1 * 5.0;
            double d2 = (double)random.nextFloat() * -1.9 * (double)random.nextFloat() * 0.1;
            return new X_1567_o(worldIn, x, y, z, d0, d1, d2, 1.0f, this.n_1700_B);
        }
    }
}


