/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.u_530_F;

public class BaseAshSmokeParticle
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;
    private final double J_1907_R;

    protected BaseAshSmokeParticle(b_4507_u world, double x, double y, double z, float defaultMotionMultX, float defaultMotionMultY, float defaultMotionMultZ, double motionX, double motionY, double motionZ, float scale, SpriteSet spriteWithAge, float colorMult, int maxAge, double yAccel, boolean canCollide) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        float f;
        this.J_1907_R = yAccel;
        this.n_1700_B = spriteWithAge;
        this.s_956_w *= (double)defaultMotionMultX;
        this.u_2550_I *= (double)defaultMotionMultY;
        this.M_588_G *= (double)defaultMotionMultZ;
        this.s_956_w += motionX;
        this.u_2550_I += motionY;
        this.M_588_G += motionZ;
        this.Q_2552_b = f = world.w_1457_N.nextFloat() * colorMult;
        this.C_2741_M = f;
        this.k_2293_S = f;
        this.A_4115_X *= 0.75f * scale;
        this.Y_601_j = (int)((double)maxAge / ((double)world.w_1457_N.nextFloat() * 0.8 + 0.2));
        this.Y_601_j = (int)((float)this.Y_601_j * scale);
        this.Y_601_j = Math.max(this.Y_601_j, 1);
        this.J_1907_R(spriteWithAge);
        this.h_1847_R = canCollide;
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
            this.u_2550_I += this.J_1907_R;
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
}


