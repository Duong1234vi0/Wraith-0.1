/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.u_530_F;

public class PlayerCloudParticle
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    private PlayerCloudParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteSetWithAge) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        float f1;
        this.n_1700_B = spriteSetWithAge;
        float f = 2.5f;
        this.s_956_w *= (double)0.1f;
        this.u_2550_I *= (double)0.1f;
        this.M_588_G *= (double)0.1f;
        this.s_956_w += motionX;
        this.u_2550_I += motionY;
        this.M_588_G += motionZ;
        this.Q_2552_b = f1 = 1.0f - (float)(Math.random() * (double)0.3f);
        this.C_2741_M = f1;
        this.k_2293_S = f1;
        this.A_4115_X *= 1.875f;
        int i = (int)(8.0 / (Math.random() * 0.8 + 0.3));
        this.Y_601_j = (int)Math.max((float)i * 2.5f, 1.0f);
        this.h_1847_R = false;
        this.J_1907_R(spriteSetWithAge);
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
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
            double d0;
            this.J_1907_R(this.n_1700_B);
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.96f;
            this.u_2550_I *= (double)0.96f;
            this.M_588_G *= (double)0.96f;
            a_3913_L playerentity = this.R_4764_Y.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, 2.0, false);
            if (playerentity != null && this.w_1484_f > (d0 = playerentity.X_2960_b())) {
                this.w_1484_f += (d0 - this.w_1484_f) * 0.2;
                this.u_2550_I += (playerentity.I_4348_c().R_4764_Y - this.u_2550_I) * 0.2;
                this.J_1907_R(this.v_4262_N, this.w_1484_f, this.t_148_a);
            }
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
            PlayerCloudParticle particle = new PlayerCloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
            particle.n_1700_B(200.0f, 50.0f, 120.0f);
            particle.P_1922_E(0.4f);
            return particle;
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
            return new PlayerCloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


