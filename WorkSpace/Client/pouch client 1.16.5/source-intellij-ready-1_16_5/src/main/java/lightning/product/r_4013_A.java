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

public class r_4013_A
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    private r_4013_A(b_4507_u world, double x, double y, double z, double scale, SpriteSet spriteWithAge) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        float f;
        this.Y_601_j = 6 + this.multiplayerClientSuggestionProvider.nextInt(4);
        this.Q_2552_b = f = this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.4f;
        this.C_2741_M = f;
        this.k_2293_S = f;
        this.A_4115_X = 2.0f * (1.0f - (float)scale * 0.5f);
        this.n_1700_B = spriteWithAge;
        this.J_1907_R(spriteWithAge);
    }

    @Override
    public int n_1700_B(float partialTick) {
        return 0xF000F0;
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
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.G_564_y;
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new r_4013_A(worldIn, x, y, z, xSpeed, this.n_1700_B);
        }
    }
}


