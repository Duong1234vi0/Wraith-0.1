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
import lightning.product.u_530_F;

public class b_296_o
extends TextureSheetParticle {
    private float n_1700_B;

    private b_296_o(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z);
        this.Y_601_j = (int)(Math.random() * 60.0) + 30;
        this.h_1847_R = false;
        this.s_956_w = 0.0;
        this.u_2550_I = -0.05;
        this.M_588_G = 0.0;
        this.n_1700_B(0.02f, 0.02f);
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.2f;
        this.Y_259_p = 0.002f;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            float f = 0.6f;
            this.s_956_w += (double)(0.6f * u_530_F.J_1907_R(this.n_1700_B));
            this.M_588_G += (double)(0.6f * u_530_F.n_1700_B(this.n_1700_B));
            this.s_956_w *= 0.07;
            this.M_588_G *= 0.07;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (!this.R_4764_Y.getFluidState(new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).n_1700_B(FluidTags.J_1907_R) || this.P_4830_p) {
                this.s_956_w();
            }
            this.n_1700_B = (float)((double)this.n_1700_B + 0.08);
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
            b_296_o currentdownparticle = new b_296_o(worldIn, x, y, z);
            currentdownparticle.n_1700_B(this.n_1700_B);
            return currentdownparticle;
        }
    }
}


