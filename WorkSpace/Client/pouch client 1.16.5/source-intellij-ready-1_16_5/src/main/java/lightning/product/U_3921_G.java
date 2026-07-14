/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.BlockGetter;
import lightning.product.TextureSheetParticle;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class U_3921_G
extends TextureSheetParticle {
    protected U_3921_G(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.s_956_w *= (double)0.3f;
        this.u_2550_I = Math.random() * (double)0.2f + (double)0.1f;
        this.M_588_G *= (double)0.3f;
        this.n_1700_B(0.01f, 0.01f);
        this.Y_259_p = 0.06f;
        this.Y_601_j = (int)(8.0 / (Math.random() * 0.8 + 0.2));
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
        if (this.Y_601_j-- <= 0) {
            this.s_956_w();
        } else {
            c_1514_x blockpos;
            double d0;
            this.u_2550_I -= (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.98f;
            this.u_2550_I *= (double)0.98f;
            this.M_588_G *= (double)0.98f;
            if (this.P_4830_p) {
                if (Math.random() < 0.5) {
                    this.s_956_w();
                }
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
            if ((d0 = Math.max(this.R_4764_Y.getBlockState(blockpos = new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a)).u_2550_I(this.R_4764_Y, blockpos).n_1700_B(b_257_Y.n_1700_B.J_1907_R, this.v_4262_N - (double)blockpos.getX(), this.t_148_a - (double)blockpos.getZ()), (double)this.R_4764_Y.getFluidState(blockpos).n_1700_B((BlockGetter)this.R_4764_Y, blockpos))) > 0.0 && this.w_1484_f < (double)blockpos.getY() + d0) {
                this.s_956_w();
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
            U_3921_G rainparticle = new U_3921_G(worldIn, x, y, z);
            rainparticle.n_1700_B(this.n_1700_B);
            return rainparticle;
        }
    }
}


