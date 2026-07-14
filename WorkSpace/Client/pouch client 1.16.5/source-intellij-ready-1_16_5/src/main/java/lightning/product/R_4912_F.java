/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.S_315_z;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class R_4912_F
extends S_315_z {
    protected R_4912_F(k_4690_i world, double x, double y, double z, float width, float height, SpriteSet provider) {
        super(world, x, y, z, width, height, provider);
        this.Q_2552_b = 1.0f;
        this.C_2741_M = 1.0f;
        this.k_2293_S = 1.0f;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet provider) {
            this.n_1700_B = provider;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            if (!(worldIn instanceof k_4690_i)) {
                return null;
            }
            k_4690_i clientWorld = (k_4690_i)worldIn;
            return new R_4912_F(clientWorld, x, y, z, (float)xSpeed, (float)ySpeed, this.n_1700_B);
        }
    }
}


