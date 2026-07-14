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

public class M_3637_E
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    private M_3637_E(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.n_1700_B = spriteWithAge;
        this.s_956_w *= (double)0.3f;
        this.u_2550_I = Math.random() * (double)0.2f + (double)0.1f;
        this.M_588_G *= (double)0.3f;
        this.n_1700_B(0.01f, 0.01f);
        this.Y_601_j = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.J_1907_R(spriteWithAge);
        this.Y_259_p = 0.0f;
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        int i = 60 - this.Y_601_j;
        if (this.Y_601_j-- <= 0) {
            this.s_956_w();
        } else {
            this.u_2550_I -= (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.98f;
            this.u_2550_I *= (double)0.98f;
            this.M_588_G *= (double)0.98f;
            float f = (float)i * 0.001f;
            this.n_1700_B(f, f);
            this.n_1700_B(this.n_1700_B.n_1700_B(i % 4, 4));
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
            return new M_3637_E(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


