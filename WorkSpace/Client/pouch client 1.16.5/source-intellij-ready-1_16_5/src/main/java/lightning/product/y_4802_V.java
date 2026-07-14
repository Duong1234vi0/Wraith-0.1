/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class y_4802_V
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    private y_4802_V(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteSetWithAge) {
        super(world, x, y, z);
        this.n_1700_B = spriteSetWithAge;
        this.Y_601_j = 4;
        this.Y_259_p = 0.008f;
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.J_1907_R(spriteSetWithAge);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.u_2550_I -= (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.J_1907_R(this.n_1700_B);
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new y_4802_V(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


