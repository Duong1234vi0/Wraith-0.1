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
import lightning.product.DustParticleOptions;
import lightning.product.u_530_F;

public class L_3884_T
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    private L_3884_T(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, DustParticleOptions particleData, SpriteSet spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.n_1700_B = spriteWithAge;
        this.s_956_w *= (double)0.1f;
        this.u_2550_I *= (double)0.1f;
        this.M_588_G *= (double)0.1f;
        float f = (float)Math.random() * 0.4f + 0.6f;
        this.Q_2552_b = ((float)(Math.random() * (double)0.2f) + 0.8f) * particleData.n_1700_B() * f;
        this.C_2741_M = ((float)(Math.random() * (double)0.2f) + 0.8f) * particleData.J_1907_R() * f;
        this.k_2293_S = ((float)(Math.random() * (double)0.2f) + 0.8f) * particleData.P_1922_E() * f;
        this.A_4115_X *= 0.75f * particleData.u_1723_Y();
        int i = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.Y_601_j = (int)Math.max((float)i * particleData.u_1723_Y(), 1.0f);
        this.J_1907_R(spriteWithAge);
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
            this.J_1907_R(this.n_1700_B);
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (this.w_1484_f == this.P_1922_E) {
                this.s_956_w *= 1.1;
                this.M_588_G *= 1.1;
            }
            this.s_956_w *= (double)0.96f;
            this.u_2550_I *= (double)0.96f;
            this.M_588_G *= (double)0.96f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }

    public static class n_1700_B
    implements ParticleProvider<DustParticleOptions> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(DustParticleOptions typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new L_3884_T(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn, this.n_1700_B);
        }
    }
}


