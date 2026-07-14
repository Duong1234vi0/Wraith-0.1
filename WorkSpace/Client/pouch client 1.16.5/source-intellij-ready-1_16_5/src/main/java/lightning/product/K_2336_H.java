/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.w_2099_r;

public class K_2336_H
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    protected K_2336_H(k_4690_i world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z);
        this.n_1700_B = sprites;
        this.h_1847_R = false;
        this.u_2550_I = 0.0;
        this.s_956_w = (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) * 0.02;
        this.M_588_G = (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) * 0.02;
        this.A_4115_X = (float)(5 + this.multiplayerClientSuggestionProvider.nextInt(6)) * 0.06f;
        this.Y_601_j = 20;
        this.J_1907_R(sprites);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
            return;
        }
        if (!w_2099_r.t_1786_h()) {
            this.s_956_w();
            return;
        }
        float opacity = w_2099_r.Y_601_j();
        if (opacity <= 0.0f) {
            this.s_956_w();
            return;
        }
        this.J_1907_R(this.n_1700_B);
        this.u_2550_I += 0.004;
        this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
        this.s_956_w *= 0.882;
        this.u_2550_I *= 0.98;
        this.M_588_G *= 0.882;
        this.q_2307_F = (1.0f - (float)this.w_1457_N / (float)this.Y_601_j) * 0.45f * opacity;
    }

    @Override
    public int n_1700_B(float partialTick) {
        return 0xF000F0;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet sprites) {
            this.n_1700_B = sprites;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new K_2336_H((k_4690_i)worldIn, x, y, z, this.n_1700_B);
        }
    }
}


