/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_3394_O;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class x_3506_b
extends P_3394_O {
    protected x_3506_b(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ, 2.5f, spriteWithAge);
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new x_3506_b(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


