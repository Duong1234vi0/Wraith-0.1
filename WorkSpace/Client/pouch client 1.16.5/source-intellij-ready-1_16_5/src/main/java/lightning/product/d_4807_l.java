/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_3921_G;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class d_4807_l
extends U_3921_G {
    private d_4807_l(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.Y_259_p = 0.04f;
        if (motionY == 0.0 && (motionX != 0.0 || motionZ != 0.0)) {
            this.s_956_w = motionX;
            this.u_2550_I = 0.1;
            this.M_588_G = motionZ;
        }
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            d_4807_l splashparticle = new d_4807_l(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            splashparticle.n_1700_B(this.n_1700_B);
            return splashparticle;
        }
    }
}


