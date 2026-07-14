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

public class n_2564_g
extends TextureSheetParticle {
    private final SpriteSet n_1700_B;

    protected n_2564_g(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z);
        float f;
        this.n_1700_B = spriteWithAge;
        this.s_956_w = motionX + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.u_2550_I = motionY + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.M_588_G = motionZ + (Math.random() * 2.0 - 1.0) * (double)0.05f;
        this.Q_2552_b = f = this.multiplayerClientSuggestionProvider.nextFloat() * 0.3f + 0.7f;
        this.C_2741_M = f;
        this.k_2293_S = f;
        this.A_4115_X = 0.1f * (this.multiplayerClientSuggestionProvider.nextFloat() * this.multiplayerClientSuggestionProvider.nextFloat() * 6.0f + 1.0f);
        this.Y_601_j = (int)(16.0 / ((double)this.multiplayerClientSuggestionProvider.nextFloat() * 0.8 + 0.2)) + 2;
        this.J_1907_R(spriteWithAge);
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
            this.J_1907_R(this.n_1700_B);
            this.u_2550_I += 0.004;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.9f;
            this.u_2550_I *= (double)0.9f;
            this.M_588_G *= (double)0.9f;
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
            return new n_2564_g(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


