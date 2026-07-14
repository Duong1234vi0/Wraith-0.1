/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_4507_u;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.PortalParticle;

public class ReversePortalParticle
extends PortalParticle {
    private ReversePortalParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.A_4115_X = (float)((double)this.A_4115_X * 1.5);
        this.Y_601_j = (int)(Math.random() * 2.0) + 60;
    }

    @Override
    public float J_1907_R(float scaleFactor) {
        float f = 1.0f - ((float)this.w_1457_N + scaleFactor) / ((float)this.Y_601_j * 1.5f);
        return this.A_4115_X * f;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            float f = (float)this.w_1457_N / (float)this.Y_601_j;
            this.v_4262_N += this.s_956_w * (double)f;
            this.w_1484_f += this.u_2550_I * (double)f;
            this.t_148_a += this.M_588_G * (double)f;
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
            ReversePortalParticle reverseportalparticle = new ReversePortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            reverseportalparticle.n_1700_B(this.n_1700_B);
            return reverseportalparticle;
        }
    }
}


