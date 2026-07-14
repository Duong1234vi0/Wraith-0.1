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

public class SuspendedTownParticle
extends TextureSheetParticle {
    private SuspendedTownParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        float f;
        this.Q_2552_b = f = this.multiplayerClientSuggestionProvider.nextFloat() * 0.1f + 0.2f;
        this.C_2741_M = f;
        this.k_2293_S = f;
        this.n_1700_B(0.02f, 0.02f);
        this.A_4115_X *= this.multiplayerClientSuggestionProvider.nextFloat() * 0.6f + 0.5f;
        this.s_956_w *= (double)0.02f;
        this.u_2550_I *= (double)0.02f;
        this.M_588_G *= (double)0.02f;
        this.Y_601_j = (int)(20.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public void n_1700_B(double x, double y, double z) {
        this.n_1700_B(this.P_4830_p().offset(x, y, z));
        this.u_2550_I();
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
            this.s_956_w *= 0.99;
            this.u_2550_I *= 0.99;
            this.M_588_G *= 0.99;
        }
    }

    public static class G_564_y
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public G_564_y(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SuspendedTownParticle suspendedtownparticle = new SuspendedTownParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            suspendedtownparticle.n_1700_B(this.n_1700_B);
            suspendedtownparticle.n_1700_B(1.0f, 1.0f, 1.0f);
            return suspendedtownparticle;
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
            SuspendedTownParticle suspendedtownparticle = new SuspendedTownParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            suspendedtownparticle.n_1700_B(this.n_1700_B);
            return suspendedtownparticle;
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
            SuspendedTownParticle suspendedtownparticle = new SuspendedTownParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            suspendedtownparticle.n_1700_B(0.3f, 0.5f, 1.0f);
            suspendedtownparticle.n_1700_B(this.n_1700_B);
            suspendedtownparticle.P_1922_E(1.0f - worldIn.w_1457_N.nextFloat() * 0.7f);
            suspendedtownparticle.n_1700_B(suspendedtownparticle.t_148_a() / 2);
            return suspendedtownparticle;
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
            SuspendedTownParticle suspendedtownparticle = new SuspendedTownParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            suspendedtownparticle.n_1700_B(this.n_1700_B);
            suspendedtownparticle.n_1700_B(1.0f, 1.0f, 1.0f);
            suspendedtownparticle.n_1700_B(3 + worldIn.e_4240_b().nextInt(5));
            return suspendedtownparticle;
        }
    }
}


