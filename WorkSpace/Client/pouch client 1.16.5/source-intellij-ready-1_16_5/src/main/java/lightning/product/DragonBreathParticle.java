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
import lightning.product.u_530_F;

public class DragonBreathParticle
extends TextureSheetParticle {
    private boolean n_1700_B;
    private final SpriteSet J_1907_R;

    private DragonBreathParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet spriteWithAge) {
        super(world, x, y, z);
        this.s_956_w = motionX;
        this.u_2550_I = motionY;
        this.M_588_G = motionZ;
        this.Q_2552_b = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider, 0.7176471f, 0.8745098f);
        this.C_2741_M = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider, 0.0f, 0.0f);
        this.k_2293_S = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider, 0.8235294f, 0.9764706f);
        this.A_4115_X *= 0.75f;
        this.Y_601_j = (int)(20.0 / ((double)this.multiplayerClientSuggestionProvider.nextFloat() * 0.8 + 0.2));
        this.n_1700_B = false;
        this.h_1847_R = false;
        this.J_1907_R = spriteWithAge;
        this.J_1907_R(spriteWithAge);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.J_1907_R(this.J_1907_R);
            if (this.P_4830_p) {
                this.u_2550_I = 0.0;
                this.n_1700_B = true;
            }
            if (this.n_1700_B) {
                this.u_2550_I += 0.002;
            }
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (this.w_1484_f == this.P_1922_E) {
                this.s_956_w *= 1.1;
                this.M_588_G *= 1.1;
            }
            this.s_956_w *= (double)0.96f;
            this.M_588_G *= (double)0.96f;
            if (this.n_1700_B) {
                this.u_2550_I *= (double)0.96f;
            }
        }
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        return this.A_4115_X * u_530_F.n_1700_B(((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j * 32.0f, 0.0f, 1.0f);
    }

    public static class n_1700_B
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new DragonBreathParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
        }
    }
}


