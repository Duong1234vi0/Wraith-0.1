/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;

public class SimpleAnimatedParticle
extends TextureSheetParticle {
    protected final SpriteSet n_1700_B;
    private final float J_1907_R;
    private float Y_1740_V = 0.91f;
    private float t_4043_B;
    private float x_607_J;
    private float e_4240_b;
    private boolean n_3318_d;

    protected SimpleAnimatedParticle(b_4507_u world, double x, double y, double z, SpriteSet spriteWithAge, float yAccel) {
        super(world, x, y, z);
        this.n_1700_B = spriteWithAge;
        this.J_1907_R = yAccel;
    }

    public void J_1907_R(int color) {
        float f = (float)((color & 0xFF0000) >> 16) / 255.0f;
        float f1 = (float)((color & 0xFF00) >> 8) / 255.0f;
        float f2 = (float)((color & 0xFF) >> 0) / 255.0f;
        float f3 = 1.0f;
        this.n_1700_B(f * 1.0f, f1 * 1.0f, f2 * 1.0f);
    }

    public void R_4764_Y(int rgb) {
        this.t_4043_B = (float)((rgb & 0xFF0000) >> 16) / 255.0f;
        this.x_607_J = (float)((rgb & 0xFF00) >> 8) / 255.0f;
        this.e_4240_b = (float)((rgb & 0xFF) >> 0) / 255.0f;
        this.n_3318_d = true;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
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
                if (this.n_3318_d) {
                    this.Q_2552_b += (this.t_4043_B - this.Q_2552_b) * 0.2f;
                    this.C_2741_M += (this.x_607_J - this.C_2741_M) * 0.2f;
                    this.k_2293_S += (this.e_4240_b - this.k_2293_S) * 0.2f;
                }
            }
            this.u_2550_I += (double)this.J_1907_R;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)this.Y_1740_V;
            this.u_2550_I *= (double)this.Y_1740_V;
            this.M_588_G *= (double)this.Y_1740_V;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }

    @Override
    public int n_1700_B(float partialTick) {
        return 0xF000F0;
    }

    protected void u_1723_Y(float airFriction) {
        this.Y_1740_V = airFriction;
    }
}


