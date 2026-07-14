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

public class CampfireSmokeParticle
extends TextureSheetParticle {
    private CampfireSmokeParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, boolean longLivingEmber) {
        super(world, x, y, z);
        this.G_564_y(3.0f);
        this.n_1700_B(0.25f, 0.25f);
        this.Y_601_j = longLivingEmber ? this.multiplayerClientSuggestionProvider.nextInt(50) + 280 : this.multiplayerClientSuggestionProvider.nextInt(50) + 80;
        this.Y_259_p = 3.0E-6f;
        this.s_956_w = motionX;
        this.u_2550_I = motionY + (double)(this.multiplayerClientSuggestionProvider.nextFloat() / 500.0f);
        this.M_588_G = motionZ;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ < this.Y_601_j && !(this.q_2307_F <= 0.0f)) {
            this.s_956_w += (double)(this.multiplayerClientSuggestionProvider.nextFloat() / 5000.0f * (float)(this.multiplayerClientSuggestionProvider.nextBoolean() ? 1 : -1));
            this.M_588_G += (double)(this.multiplayerClientSuggestionProvider.nextFloat() / 5000.0f * (float)(this.multiplayerClientSuggestionProvider.nextBoolean() ? 1 : -1));
            this.u_2550_I -= (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (this.w_1457_N >= this.Y_601_j - 60 && this.q_2307_F > 0.01f) {
                this.q_2307_F -= 0.015f;
            }
        } else {
            this.s_956_w();
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
    }

    public static class J_1907_R
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public J_1907_R(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            CampfireSmokeParticle campfireparticle = new CampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, true);
            campfireparticle.P_1922_E(0.95f);
            campfireparticle.n_1700_B(this.n_1700_B);
            return campfireparticle;
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
            CampfireSmokeParticle campfireparticle = new CampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, false);
            campfireparticle.P_1922_E(0.9f);
            campfireparticle.n_1700_B(this.n_1700_B);
            return campfireparticle;
        }
    }
}


