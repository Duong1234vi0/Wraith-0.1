/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.BaseAshSmokeParticle;

public class K_1668_X
extends BaseAshSmokeParticle {
    protected K_1668_X(b_4507_u world, double x, double y, double z, double motionMultX, double motionMultY, double motionMultZ, float scale, SpriteSet spriteWithAge) {
        super(world, x, y, z, 0.1f, -0.1f, 0.1f, motionMultX, motionMultY, motionMultZ, scale, spriteWithAge, 0.5f, 20, -0.004, false);
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new K_1668_X(worldIn, x, y, z, 0.0, 0.0, 0.0, 1.0f, this.n_1700_B);
        }
    }
}


