/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.n_2564_g;

public class s_2736_T
extends n_2564_g {
    private s_2736_T(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ, spriteWithAge);
        this.Y_259_p = 0.5f;
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        this.u_2550_I -= 0.004 + 0.04 * (double)this.Y_259_p;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new s_2736_T(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


