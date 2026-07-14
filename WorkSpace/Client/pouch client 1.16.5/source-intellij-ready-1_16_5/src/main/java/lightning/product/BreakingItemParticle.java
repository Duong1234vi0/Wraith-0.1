/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ParticleRenderType;
import lightning.product.N_3869_i;
import lightning.product.TextureSheetParticle;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.Items;

public class BreakingItemParticle
extends TextureSheetParticle {
    private final float n_1700_B;
    private final float J_1907_R;

    private BreakingItemParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, Z_1993_T stack) {
        this(world, x, y, z, stack);
        this.s_956_w *= (double)0.1f;
        this.u_2550_I *= (double)0.1f;
        this.M_588_G *= (double)0.1f;
        this.s_956_w += motionX;
        this.u_2550_I += motionY;
        this.M_588_G += motionZ;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.n_1700_B;
    }

    protected BreakingItemParticle(b_4507_u world, double x, double y, double z, Z_1993_T stack) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.n_1700_B(MinecraftClient.A_4115_X().r_715_M().n_1700_B(stack, world, null).P_1922_E());
        this.Y_259_p = 1.0f;
        this.A_4115_X /= 2.0f;
        this.n_1700_B = this.multiplayerClientSuggestionProvider.nextFloat() * 3.0f;
        this.J_1907_R = this.multiplayerClientSuggestionProvider.nextFloat() * 3.0f;
    }

    @Override
    protected float R_4764_Y() {
        return this.H_2857_Y.n_1700_B((double)((this.n_1700_B + 1.0f) / 4.0f * 16.0f));
    }

    @Override
    protected float G_564_y() {
        return this.H_2857_Y.n_1700_B((double)(this.n_1700_B / 4.0f * 16.0f));
    }

    @Override
    protected float P_1922_E() {
        return this.H_2857_Y.J_1907_R((double)(this.J_1907_R / 4.0f * 16.0f));
    }

    @Override
    protected float u_1723_Y() {
        return this.H_2857_Y.J_1907_R((double)((this.J_1907_R + 1.0f) / 4.0f * 16.0f));
    }

    public static class R_4764_Y
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BreakingItemParticle(worldIn, x, y, z, new Z_1993_T(Items.i_770_g));
        }
    }

    public static class J_1907_R
    implements ParticleProvider<SimpleParticleType> {
        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BreakingItemParticle(worldIn, x, y, z, new Z_1993_T(Items.Z_3822_q));
        }
    }

    public static class n_1700_B
    implements ParticleProvider<N_3869_i> {
        @Override
        public c_3457_g n_1700_B(N_3869_i typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BreakingItemParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn.n_1700_B());
        }
    }
}



