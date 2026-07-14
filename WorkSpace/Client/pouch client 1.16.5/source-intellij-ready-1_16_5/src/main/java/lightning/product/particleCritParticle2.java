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

public class particleCritParticle2
extends TextureSheetParticle {
    private particleCritParticle2(b_4507_u world, double x, double y, double z, double hue) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.s_956_w *= (double)0.01f;
        this.u_2550_I *= (double)0.01f;
        this.M_588_G *= (double)0.01f;
        this.u_2550_I += 0.2;
        this.Q_2552_b = Math.max(0.0f, u_530_F.n_1700_B(((float)hue + 0.0f) * ((float)Math.PI * 2)) * 0.65f + 0.35f);
        this.C_2741_M = Math.max(0.0f, u_530_F.n_1700_B(((float)hue + 0.33333334f) * ((float)Math.PI * 2)) * 0.65f + 0.35f);
        this.k_2293_S = Math.max(0.0f, u_530_F.n_1700_B(((float)hue + 0.6666667f) * ((float)Math.PI * 2)) * 0.65f + 0.35f);
        this.A_4115_X *= 1.5f;
        this.Y_601_j = 6;
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
            this.s_956_w *= (double)0.66f;
            this.u_2550_I *= (double)0.66f;
            this.M_588_G *= (double)0.66f;
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
            particleCritParticle2 noteparticle = new particleCritParticle2(worldIn, x, y, z, xSpeed);
            noteparticle.n_1700_B(this.n_1700_B);
            return noteparticle;
        }
    }
}


