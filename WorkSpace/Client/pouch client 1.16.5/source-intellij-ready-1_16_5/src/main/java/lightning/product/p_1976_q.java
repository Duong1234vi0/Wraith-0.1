/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.V_772_m;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.u_530_F;
import lightning.product.w_2099_r;

public class p_1976_q
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;
    private float J_1907_R = 1.0f;

    protected p_1976_q(k_4690_i world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet sprites) {
        super(world, x, y, z);
        this.n_1700_B = sprites;
        this.s_956_w = motionX + (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) * 0.005;
        this.u_2550_I = motionY + (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) * 0.005;
        this.M_588_G = motionZ + (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) * 0.005;
        this.Y_601_j = 10;
        this.A_4115_X = 0.15f;
        this.h_1847_R = false;
        this.J_1907_R(sprites);
        V_772_m player = MinecraftClient.A_4115_X().Y_259_p;
        if (player != null && player.v_4262_N(x, y, z) < 36.0) {
            this.J_1907_R -= 0.8f;
        }
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
        if (!w_2099_r.M_182_A()) {
            this.s_956_w();
            return;
        }
        float opacity = w_2099_r.w_1457_N();
        if (opacity <= 0.0f) {
            this.s_956_w();
            return;
        }
        this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
        this.J_1907_R(this.n_1700_B);
        this.J_1907_R -= 0.1f;
        V_772_m player = MinecraftClient.A_4115_X().Y_259_p;
        if (player != null && player.v_4262_N(this.v_4262_N, this.w_1484_f, this.t_148_a) < 36.0) {
            this.J_1907_R -= 0.8f;
        }
        this.A_4115_X = Math.max(0.0f, 1.0f - (float)this.w_1457_N / (float)this.Y_601_j) * 0.15f;
        this.q_2307_F = u_530_F.n_1700_B(this.J_1907_R, 0.0f, 1.0f) * opacity;
        if (this.J_1907_R <= 0.0f) {
            this.s_956_w();
        }
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
            return new p_1976_q((k_4690_i)worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}



