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

public class SoulParticle
extends RisingParticle {
    private final SpriteSet n_1700_B;

    private SoulParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.n_1700_B = spriteWithAge;
        this.G_564_y(1.5f);
        this.J_1907_R(spriteWithAge);
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (!this.Q_4569_t) {
            this.J_1907_R(this.n_1700_B);
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
            SoulParticle soulparticle = new SoulParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
            soulparticle.P_1922_E(1.0f);
            return soulparticle;
        }
    }
}


