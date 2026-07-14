/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.F_2860_q;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.SimpleAnimatedParticle;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class V_3080_W
extends SimpleAnimatedParticle {
    private V_3080_W(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, spriteWithAge, -0.05f);
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.A_4115_X *= 0.75f;
        this.Y_601_j = 60 + this.multiplayerClientSuggestionProvider.nextInt(12);
        this.J_1907_R(spriteWithAge);
        if (this.multiplayerClientSuggestionProvider.nextInt(4) == 0) {
            this.n_1700_B(0.6f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.2f, 0.6f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.3f, this.multiplayerClientSuggestionProvider.nextFloat() * 0.2f);
        } else {
            this.n_1700_B(0.1f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.2f, 0.4f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.3f, this.multiplayerClientSuggestionProvider.nextFloat() * 0.2f);
        }
        this.u_1723_Y(0.6f);
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            F_2860_q event = new F_2860_q(x, y, z, xSpeed, ySpeed, zSpeed);
            lightning.product.A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return null;
            }
            return new V_3080_W(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


