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
import lightning.product.ParticleTypes;

public class LavaParticle
extends TextureSheetParticle {
    private LavaParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.s_956_w *= (double)0.8f;
        this.u_2550_I *= (double)0.8f;
        this.M_588_G *= (double)0.8f;
        this.u_2550_I = this.multiplayerClientSuggestionProvider.nextFloat() * 0.4f + 0.05f;
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 2.0f + 0.2f;
        this.Y_601_j = (int)(16.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public int n_1700_B(float partialTick) {
        int i = super.n_1700_B(partialTick);
        int j = 240;
        int k = i >> 16 & 0xFF;
        return 0xF0 | k << 16;
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        float f = ((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j;
        return this.A_4115_X * (1.0f - f * f);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        float f = (float)this.w_1457_N / (float)this.Y_601_j;
        if (this.multiplayerClientSuggestionProvider.nextFloat() > f) {
            this.R_4764_Y.n_1700_B(ParticleTypes.B_1668_F, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
        }
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.u_2550_I -= 0.03;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.999f;
            this.u_2550_I *= (double)0.999f;
            this.M_588_G *= (double)0.999f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
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
            LavaParticle lavaparticle = new LavaParticle(worldIn, x, y, z);
            lavaparticle.n_1700_B(this.n_1700_B);
            return lavaparticle;
        }
    }
}


