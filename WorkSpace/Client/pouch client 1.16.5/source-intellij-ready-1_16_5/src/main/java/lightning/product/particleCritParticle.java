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
import lightning.product.u_530_F;

public class particleCritParticle
extends TextureSheetParticle {
    private particleCritParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        float f;
        this.s_956_w *= (double)0.1f;
        this.u_2550_I *= (double)0.1f;
        this.M_588_G *= (double)0.1f;
        this.s_956_w += motionX * 0.4;
        this.u_2550_I += motionY * 0.4;
        this.M_588_G += motionZ * 0.4;
        this.Q_2552_b = f = (float)(Math.random() * (double)0.3f + (double)0.6f);
        this.C_2741_M = f;
        this.k_2293_S = f;
        this.A_4115_X *= 0.75f;
        this.Y_601_j = Math.max((int)(6.0 / (Math.random() * 0.8 + 0.6)), 1);
        this.h_1847_R = false;
        this.n_1700_B();
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        return this.A_4115_X * u_530_F.n_1700_B(((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j * 32.0f, 0.0f, 1.0f);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.C_2741_M = (float)((double)this.C_2741_M * 0.96);
            this.k_2293_S = (float)((double)this.k_2293_S * 0.9);
            this.s_956_w *= (double)0.7f;
            this.u_2550_I *= (double)0.7f;
            this.M_588_G *= (double)0.7f;
            this.u_2550_I -= (double)0.02f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    public static class R_4764_Y
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public R_4764_Y(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            particleCritParticle critparticle = new particleCritParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            critparticle.Q_2552_b *= 0.3f;
            critparticle.C_2741_M *= 0.8f;
            critparticle.n_1700_B(this.n_1700_B);
            return critparticle;
        }
    }

    public static class J_1907_R
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public J_1907_R(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            particleCritParticle critparticle = new particleCritParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            critparticle.n_1700_B(this.n_1700_B);
            return critparticle;
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
            particleCritParticle critparticle = new particleCritParticle(worldIn, x, y, z, xSpeed, ySpeed + 1.0, zSpeed);
            critparticle.n_1700_B(20);
            critparticle.n_1700_B(this.n_1700_B);
            return critparticle;
        }
    }
}


