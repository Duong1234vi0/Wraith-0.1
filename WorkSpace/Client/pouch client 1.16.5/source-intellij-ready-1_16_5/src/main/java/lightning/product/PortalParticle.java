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

public class PortalParticle
extends TextureSheetParticle {
    private final double n_1700_B;
    private final double J_1907_R;
    private final double Y_1740_V;

    protected PortalParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.v_4262_N = x;
        this.w_1484_f = y;
        this.t_148_a = z;
        this.n_1700_B = this.v_4262_N;
        this.J_1907_R = this.w_1484_f;
        this.Y_1740_V = this.t_148_a;
        this.A_4115_X = 0.1f * (this.multiplayerClientSuggestionProvider.nextFloat() * 0.2f + 0.5f);
        float f = this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.4f;
        this.Q_2552_b = f * 0.9f;
        this.C_2741_M = f * 0.3f;
        this.k_2293_S = f;
        this.Y_601_j = (int)(Math.random() * 10.0) + 40;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public void n_1700_B(double x, double y, double z) {
        this.n_1700_B(this.P_4830_p().offset(x, y, z));
        this.u_2550_I();
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        float f = ((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j;
        f = 1.0f - f;
        f *= f;
        f = 1.0f - f;
        return this.A_4115_X * f;
    }

    @Override
    public int n_1700_B(float partialTick) {
        int i = super.n_1700_B(partialTick);
        float f = (float)this.w_1457_N / (float)this.Y_601_j;
        f *= f;
        f *= f;
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        if ((k += (int)(f * 15.0f * 16.0f)) > 240) {
            k = 240;
        }
        return j | k << 16;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            float f = (float)this.w_1457_N / (float)this.Y_601_j;
            float f1 = -f + f * f * 2.0f;
            float f2 = 1.0f - f1;
            this.v_4262_N = this.n_1700_B + this.s_956_w * (double)f2;
            this.w_1484_f = this.J_1907_R + this.u_2550_I * (double)f2 + (double)(1.0f - f);
            this.t_148_a = this.Y_1740_V + this.M_588_G * (double)f2;
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
            PortalParticle portalparticle = new PortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            portalparticle.n_1700_B(this.n_1700_B);
            return portalparticle;
        }
    }
}


