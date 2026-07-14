/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.ParticleRenderType;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.TextureSheetParticle;
import lightning.product.X_426_i;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.u_530_F;
import lightning.product.FallingBlock;

public class FallingDustParticle
extends TextureSheetParticle {
    private final float n_1700_B;
    private final SpriteSet J_1907_R;

    private FallingDustParticle(b_4507_u world, double x, double y, double z, float red, float green, float blue, SpriteSet spriteWithAge) {
        super(world, x, y, z);
        this.J_1907_R = spriteWithAge;
        this.Q_2552_b = red;
        this.C_2741_M = green;
        this.k_2293_S = blue;
        float f = 0.9f;
        this.A_4115_X *= 0.67499995f;
        int i = (int)(32.0 / (Math.random() * 0.8 + 0.2));
        this.Y_601_j = (int)Math.max((float)i * 0.9f, 1.0f);
        this.J_1907_R(spriteWithAge);
        this.n_1700_B = ((float)Math.random() - 0.5f) * 0.1f;
        this.Z_875_P = (float)Math.random() * ((float)Math.PI * 2);
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        return this.A_4115_X * u_530_F.n_1700_B(((float)this.w_1457_N + scaleFactor) / (float)this.Y_601_j * 32.0f, 0.0f, 1.0f);
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
            this.c_3005_b = this.Z_875_P;
            this.Z_875_P += (float)Math.PI * this.n_1700_B * 2.0f;
            if (this.P_4830_p) {
                this.Z_875_P = 0.0f;
                this.c_3005_b = 0.0f;
            }
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.u_2550_I -= (double)0.003f;
            this.u_2550_I = Math.max(this.u_2550_I, (double)-0.14f);
        }
    }

    public static class n_1700_B
    implements ParticleProvider<X_426_i> {
        private final SpriteSet n_1700_B;

        public n_1700_B(SpriteSet spriteSetIn) {
            this.n_1700_B = spriteSetIn;
        }

        @Override
        @Nullable
        public c_3457_g n_1700_B(X_426_i typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            K_4074_S blockstate = typeIn.n_1700_B();
            if (!blockstate.v_4262_N() && blockstate.w_1484_f() == O_2369_F.n_1700_B) {
                return null;
            }
            c_1514_x blockpos = new c_1514_x(x, y, z);
            int i = MinecraftClient.A_4115_X().j_276_v().n_1700_B(blockstate, worldIn, blockpos);
            if (blockstate.J_1907_R() instanceof FallingBlock) {
                i = ((FallingBlock)blockstate.J_1907_R()).v_4262_N(blockstate, worldIn, blockpos);
            }
            float f = (float)(i >> 16 & 0xFF) / 255.0f;
            float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
            float f2 = (float)(i & 0xFF) / 255.0f;
            return new FallingDustParticle(worldIn, x, y, z, f, f1, f2, this.n_1700_B);
        }
    }
}



