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

public class CritParticle
extends TextureSheetParticle {
    private CritParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.s_956_w *= (double)0.01f;
        this.u_2550_I *= (double)0.01f;
        this.M_588_G *= (double)0.01f;
        this.u_2550_I += 0.1;
        this.A_4115_X *= 1.5f;
        this.Y_601_j = 16;
        this.h_1847_R = false;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
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
            if (this.w_1484_f == this.P_1922_E) {
                this.s_956_w *= 1.1;
                this.M_588_G *= 1.1;
            }
            this.s_956_w *= (double)0.86f;
            this.u_2550_I *= (double)0.86f;
            this.M_588_G *= (double)0.86f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
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
            CritParticle heartparticle = new CritParticle(worldIn, x, y, z);
            heartparticle.n_1700_B(this.n_1700_B);
            return heartparticle;
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
            CritParticle heartparticle = new CritParticle(worldIn, x, y + 0.5, z);
            heartparticle.n_1700_B(this.n_1700_B);
            heartparticle.n_1700_B(1.0f, 1.0f, 1.0f);
            return heartparticle;
        }
    }
}


