/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.N_4263_v;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.e_2866_D;
import lightning.product.k_4690_i;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.u_530_F;
import lightning.product.w_2099_r;

public class a_794_m
extends TextureSheetParticle {
    private final int n_1700_B;

    protected a_794_m(k_4690_i world, double x, double y, double z, double targetId, SpriteSet sprites) {
        super(world, x, y, z);
        this.n_1700_B = (int)targetId;
        this.Y_601_j = 30 + this.multiplayerClientSuggestionProvider.nextInt(19);
        this.A_4115_X = 0.1f;
        this.s_956_w = (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) / 25.0;
        this.u_2550_I = (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) / 25.0;
        this.M_588_G = (double)(this.multiplayerClientSuggestionProvider.nextInt(21) - 10) / 25.0;
        this.h_1847_R = true;
        this.n_1700_B(sprites.n_1700_B(this.multiplayerClientSuggestionProvider));
        if (this.multiplayerClientSuggestionProvider.nextBoolean()) {
            this.n_1700_B(0.0f, 1.0f, 0.0f);
        } else {
            this.n_1700_B(1.0f, 1.0f, 0.0f);
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
        if (!w_2099_r.Q_4569_t()) {
            this.s_956_w();
            return;
        }
        float opacity = w_2099_r.multiplayerClientSuggestionProvider();
        if (opacity <= 0.0f) {
            this.s_956_w();
            return;
        }
        this.q_2307_F = opacity;
        if (this.w_1457_N >= this.Y_601_j / 5 && this.w_1457_N < this.Y_601_j / 4) {
            this.s_956_w = 0.0;
            this.u_2550_I = 0.0;
            this.M_588_G = 0.0;
        }
        N_4263_v target = this.R_4764_Y.J_1907_R(this.n_1700_B);
        if (this.w_1457_N >= this.Y_601_j / 4 && target != null) {
            e_2866_D dir = new e_2866_D(target.O_3598_v() - this.v_4262_N, target.X_2960_b() + (double)target.v_165_F() * 0.5 - this.w_1484_f, target.l_2647_k() - this.t_148_a);
            double distance = dir.u_1723_Y();
            if (distance < 2.0) {
                this.s_956_w();
                return;
            }
            if (distance < 20.0 && distance > 1.0E-6) {
                e_2866_D normalized = dir.n_1700_B(1.0 / distance);
                double speed = (double)Math.max(this.w_1457_N - 15, 0) * 0.05;
                this.s_956_w = normalized.J_1907_R * speed;
                this.u_2550_I = normalized.R_4764_Y * speed;
                this.M_588_G = normalized.G_564_y * speed;
                this.q_2307_F = u_530_F.n_1700_B(1.0f - ((float)this.w_1457_N - (float)this.Y_601_j / 1.5f) / 20.0f, 0.0f, 1.0f) * opacity;
            }
        }
        this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
        if (this.P_4830_p) {
            this.s_956_w *= 0.7;
            this.M_588_G *= 0.7;
        }
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
            return new a_794_m((k_4690_i)worldIn, x, y, z, xSpeed, this.n_1700_B);
        }
    }
}


