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

public class SpellParticle
extends TextureSheetParticle {
    private static final Random n_1700_B = new Random();
    private final SpriteSet J_1907_R;

    private SpellParticle(b_4507_u p_i232429_1_, double x, double y, double z, double p_i232429_8_, double motionY, double p_i232429_12_, SpriteSet spriteWithAge) {
        super(p_i232429_1_, x, y, z, 0.5 - n_1700_B.nextDouble(), motionY, 0.5 - n_1700_B.nextDouble());
        this.J_1907_R = spriteWithAge;
        this.u_2550_I *= (double)0.2f;
        if (p_i232429_8_ == 0.0 && p_i232429_12_ == 0.0) {
            this.s_956_w *= (double)0.1f;
            this.M_588_G *= (double)0.1f;
        }
        this.A_4115_X *= 0.75f;
        this.Y_601_j = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.h_1847_R = false;
        this.J_1907_R(spriteWithAge);
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.R_4764_Y;
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
            this.u_2550_I += 0.004;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            if (this.w_1484_f == this.P_1922_E) {
                this.s_956_w *= 1.1;
                this.M_588_G *= 1.1;
            }
            this.s_956_w *= (double)0.96f;
            this.u_2550_I *= (double)0.96f;
            this.M_588_G *= (double)0.96f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }

    public static class P_1922_E
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet n_1700_B;

        public P_1922_E(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SpellParticle spellparticle = new SpellParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
            float f = worldIn.w_1457_N.nextFloat() * 0.5f + 0.35f;
            spellparticle.n_1700_B(1.0f * f, 0.0f * f, 1.0f * f);
            return spellparticle;
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
            SpellParticle particle = new SpellParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
            particle.n_1700_B((float)xSpeed, (float)ySpeed, (float)zSpeed);
            return particle;
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
            return new SpellParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
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
            return new SpellParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
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
            SpellParticle particle = new SpellParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.n_1700_B);
            particle.P_1922_E(0.15f);
            particle.n_1700_B((float)xSpeed, (float)ySpeed, (float)zSpeed);
            return particle;
        }
    }
}


