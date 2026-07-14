/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.NoRenderParticle;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_3457_g;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.m_229_F;
import lightning.product.SimpleParticleType;
import lightning.product.ParticleTypes;

public class A_2204_Z
extends NoRenderParticle {
    private final float n_1700_B;
    private final float J_1907_R;
    private final float H_2857_Y;

    protected A_2204_Z(k_4690_i world, double x, double y, double z, float width, float speed) {
        super(world, x, y, z);
        this.Y_259_p = 0.0f;
        this.Y_601_j = 24;
        this.n_1700_B = Math.min(2.0f, speed);
        this.J_1907_R = width;
        this.H_2857_Y = this.n_1700_B / 2.0f + width / 3.0f;
        this.R_4764_Y.n_1700_B(m_229_F.G_564_y, true, x, y, z, (double)width, (double)this.H_2857_Y, 0.0);
        this.R_4764_Y.n_1700_B(m_229_F.P_1922_E, true, x, y, z, (double)width, (double)this.H_2857_Y, 0.0);
        this.R_4764_Y.n_1700_B(m_229_F.u_1723_Y, true, x, y, z, (double)width, 0.0, 0.0);
        if (this.n_1700_B > 0.5f) {
            this.J_1907_R(width, 0.1875f + this.n_1700_B / 8.0f + width / 6.0f, 0.15f);
        } else {
            this.s_956_w();
        }
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (this.w_1457_N == 8) {
            this.R_4764_Y.n_1700_B(m_229_F.G_564_y, true, this.v_4262_N, this.w_1484_f, this.t_148_a, (double)(this.J_1907_R * 0.66f), (double)(this.H_2857_Y * 2.0f), 0.0);
            this.R_4764_Y.n_1700_B(m_229_F.P_1922_E, true, this.v_4262_N, this.w_1484_f, this.t_148_a, (double)(this.J_1907_R * 0.66f), (double)(this.H_2857_Y * 2.0f), 0.0);
            this.R_4764_Y.n_1700_B(m_229_F.u_1723_Y, true, this.v_4262_N, this.w_1484_f, this.t_148_a, (double)(this.J_1907_R * 0.66f), 0.0, 0.0);
            this.J_1907_R(this.J_1907_R * 0.66f, 0.375f + this.n_1700_B / 8.0f + this.J_1907_R / 6.0f, 0.05f);
        }
        if (!this.R_4764_Y.getFluidState(new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).n_1700_B(FluidTags.J_1907_R)) {
            this.s_956_w();
        }
    }

    private void J_1907_R(float width, float speed, float spread) {
        int i = 0;
        while ((float)i < width * 20.0f) {
            double xVel = (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * (double)spread;
            double yVel = (double)speed * (1.0 + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 0.25);
            double zVel = (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * (double)spread;
            this.R_4764_Y.n_1700_B(ParticleTypes.h_1847_R, this.v_4262_N + xVel / (double)spread * (double)width, this.w_1484_f + 0.0625, this.t_148_a + zVel / (double)spread * (double)width, xVel, yVel, zVel);
            ++i;
        }
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            if (!(worldIn instanceof k_4690_i)) {
                return null;
            }
            k_4690_i clientWorld = (k_4690_i)worldIn;
            return new A_2204_Z(clientWorld, x, y, z, (float)xSpeed, (float)ySpeed);
        }
    }
}


