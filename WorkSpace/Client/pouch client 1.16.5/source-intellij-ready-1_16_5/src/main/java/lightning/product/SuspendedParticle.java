/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.ParticleRenderType;
import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;

public class SuspendedParticle
extends TextureSheetParticle {
    private SuspendedParticle(b_4507_u world, double x, double y, double z) {
        super(world, x, y - 0.125, z);
        this.Q_2552_b = 0.4f;
        this.C_2741_M = 0.4f;
        this.k_2293_S = 0.7f;
        this.n_1700_B(0.01f, 0.01f);
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.2f;
        this.Y_601_j = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.h_1847_R = false;
    }

    private SuspendedParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y - 0.125, z, motionX, motionY, motionZ);
        this.n_1700_B(0.01f, 0.01f);
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.6f;
        this.Y_601_j = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.h_1847_R = false;
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
        if (this.Y_601_j-- <= 0) {
            this.s_956_w();
        } else {
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
        }
    }

    public static class R_4764_Y
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public R_4764_Y(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            double d0 = (double)worldIn.w_1457_N.nextFloat() * -1.9 * (double)worldIn.w_1457_N.nextFloat() * 0.1;
            SuspendedParticle underwaterparticle = new SuspendedParticle(worldIn, x, y, z, 0.0, d0, 0.0);
            underwaterparticle.n_1700_B(this.n_1700_B);
            underwaterparticle.n_1700_B(0.1f, 0.1f, 0.3f);
            underwaterparticle.n_1700_B(0.001f, 0.001f);
            return underwaterparticle;
        }
    }

    public static class J_1907_R
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public J_1907_R(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SuspendedParticle underwaterparticle = new SuspendedParticle(worldIn, x, y, z);
            underwaterparticle.n_1700_B(this.n_1700_B);
            return underwaterparticle;
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
            Random random = worldIn.w_1457_N;
            double d0 = random.nextGaussian() * (double)1.0E-6f;
            double d1 = random.nextGaussian() * (double)1.0E-4f;
            double d2 = random.nextGaussian() * (double)1.0E-6f;
            SuspendedParticle underwaterparticle = new SuspendedParticle(worldIn, x, y, z, d0, d1, d2);
            underwaterparticle.n_1700_B(this.n_1700_B);
            underwaterparticle.n_1700_B(0.9f, 0.4f, 0.5f);
            return underwaterparticle;
        }
    }
}


