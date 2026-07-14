/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.SimpleAnimatedParticle;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class EndRodParticle
extends SimpleAnimatedParticle {
    private EndRodParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, spriteWithAge, -5.0E-4f);
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.A_4115_X *= 0.75f;
        this.Y_601_j = 60 + this.multiplayerClientSuggestionProvider.nextInt(12);
        this.R_4764_Y(15916745);
        this.J_1907_R(spriteWithAge);
    }

    @Override
    public void n_1700_B(double x, double y, double z) {
        this.n_1700_B(this.P_4830_p().offset(x, y, z));
        this.u_2550_I();
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EndRodParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


