/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class Q_2974_D
extends TextureSheetParticle {
    private Q_2974_D(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.n_1700_B(0.02f, 0.02f);
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.2f;
        this.s_956_w = motionX * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.u_2550_I = motionY * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.M_588_G = motionZ * (double)0.2f + (Math.random() * 2.0 - 1.0) * (double)0.02f;
        this.Y_601_j = (int)(8.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.Y_601_j-- <= 0) {
            this.s_956_w();
        } else {
            this.u_2550_I += 0.002;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.85f;
            this.u_2550_I *= (double)0.85f;
            this.M_588_G *= (double)0.85f;
            if (!this.R_4764_Y.getFluidState(new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).n_1700_B(FluidTags.J_1907_R)) {
                this.s_956_w();
            }
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Q_2974_D bubbleparticle = new Q_2974_D(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            bubbleparticle.n_1700_B(this.n_1700_B);
            return bubbleparticle;
        }
    }
}


