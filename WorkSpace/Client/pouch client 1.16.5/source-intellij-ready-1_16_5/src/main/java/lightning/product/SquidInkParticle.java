/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.SimpleAnimatedParticle;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class SquidInkParticle
extends SimpleAnimatedParticle {
    private SquidInkParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z, spriteWithAge, 0.0f);
        this.A_4115_X = 0.5f;
        this.P_1922_E(1.0f);
        this.n_1700_B(0.0f, 0.0f, 0.0f);
        this.Y_601_j = (int)((double)(this.A_4115_X * 12.0f) / (Math.random() * (double)0.8f + (double)0.2f));
        this.J_1907_R(spriteWithAge);
        this.h_1847_R = false;
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.u_1723_Y(0.0f);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.J_1907_R(this.n_1700_B);
            if (this.w_1457_N > this.Y_601_j / 2) {
                this.P_1922_E(1.0f - ((float)this.w_1457_N - (float)(this.Y_601_j / 2)) / (float)this.Y_601_j);
            }
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (this.R_4764_Y.getBlockState(new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).v_4262_N()) {
                this.u_2550_I -= (double)0.008f;
            }
            this.s_956_w *= (double)0.92f;
            this.u_2550_I *= (double)0.92f;
            this.M_588_G *= (double)0.92f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
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
            return new SquidInkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


