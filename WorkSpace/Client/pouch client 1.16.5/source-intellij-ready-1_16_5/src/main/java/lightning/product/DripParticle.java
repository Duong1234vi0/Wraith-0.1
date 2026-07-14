/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.ParticleRenderType;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.TextureSheetParticle;
import lightning.product.ParticleOptions;
import lightning.product.SoundEvents;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SpriteSet;
import lightning.product.FluidState;
import lightning.product.c_3457_g;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;

public class DripParticle
extends TextureSheetParticle {
    private final Fluid J_1907_R;
    protected boolean n_1700_B;

    private DripParticle(b_4507_u world, double x, double y, double z, Fluid fluid) {
        super(world, x, y, z);
        this.n_1700_B(0.01f, 0.01f);
        this.Y_259_p = 0.06f;
        this.J_1907_R = fluid;
    }

    @Override
    public ParticleRenderType J_1907_R() {
        return ParticleRenderType.J_1907_R;
    }

    @Override
    public int n_1700_B(float partialTick) {
        return this.n_1700_B ? 240 : super.n_1700_B(partialTick);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        this.v_4262_N();
        if (!this.Q_4569_t) {
            this.u_2550_I -= (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.w_1484_f();
            if (!this.Q_4569_t) {
                this.s_956_w *= (double)0.98f;
                this.u_2550_I *= (double)0.98f;
                this.M_588_G *= (double)0.98f;
                c_1514_x blockpos = new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a);
                FluidState fluidstate = this.R_4764_Y.getFluidState(blockpos);
                if (fluidstate.n_1700_B() == this.J_1907_R && this.w_1484_f < (double)((float)blockpos.getY() + fluidstate.n_1700_B((BlockGetter)this.R_4764_Y, blockpos))) {
                    this.s_956_w();
                }
            }
        }
    }

    protected void v_4262_N() {
        if (this.Y_601_j-- <= 0) {
            this.s_956_w();
        }
    }

    protected void w_1484_f() {
    }

    public static class multiplayerClientSuggestionProvider
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public multiplayerClientSuggestionProvider(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Q_4569_t dripparticle = new Q_4569_t(worldIn, x, y, z, Fluids.n_1700_B);
            dripparticle.n_1700_B = true;
            dripparticle.Y_601_j = (int)(28.0 / (Math.random() * 0.8 + 0.2));
            dripparticle.n_1700_B(0.51171875f, 0.03125f, 0.890625f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    public static class t_1786_h
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public t_1786_h(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Q_4569_t dripparticle = new Q_4569_t(worldIn, x, y, z, Fluids.P_1922_E);
            dripparticle.n_1700_B(1.0f, 0.2857143f, 0.083333336f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    public static class M_182_A
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public M_182_A(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Q_4569_t dripparticle = new Q_4569_t(worldIn, x, y, z, Fluids.n_1700_B);
            dripparticle.Y_601_j = (int)(128.0 / (Math.random() * 0.8 + 0.2));
            dripparticle.n_1700_B(0.522f, 0.408f, 0.082f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    static class Q_4569_t
    extends DripParticle {
        private Q_4569_t(b_4507_u world, double x, double y, double z, Fluid fluid) {
            super(world, x, y, z, fluid);
            this.Y_601_j = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }
    }

    public static class h_1847_R
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public h_1847_R(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            s_956_w dripparticle = new s_956_w(worldIn, x, y, z, Fluids.R_4764_Y, ParticleTypes.g_2268_R);
            dripparticle.n_1700_B(0.2f, 0.3f, 1.0f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    public static class P_4830_p
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public P_4830_p(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            s_956_w dripparticle = new s_956_w(worldIn, x, y, z, Fluids.n_1700_B, ParticleTypes.k_3961_g);
            dripparticle.n_1700_B = true;
            dripparticle.Y_259_p = 0.01f;
            dripparticle.n_1700_B(0.51171875f, 0.03125f, 0.890625f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    static class M_588_G
    extends DripParticle {
        private M_588_G(b_4507_u world, double x, double y, double z, Fluid fluid) {
            super(world, x, y, z, fluid);
            this.Y_601_j = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        protected void w_1484_f() {
            if (this.P_4830_p) {
                this.s_956_w();
            }
        }
    }

    public static class u_2550_I
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public u_2550_I(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            M_588_G dripparticle = new M_588_G(worldIn, x, y, z, Fluids.n_1700_B);
            dripparticle.Y_601_j = (int)(16.0 / (Math.random() * 0.8 + 0.2));
            dripparticle.Y_259_p = 0.007f;
            dripparticle.n_1700_B(0.92f, 0.782f, 0.72f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    static class s_956_w
    extends M_588_G {
        protected final ParticleOptions J_1907_R;

        private s_956_w(b_4507_u world, double x, double y, double z, Fluid fluid, ParticleOptions particleData) {
            super(world, x, y, z, fluid);
            this.J_1907_R = particleData;
        }

        @Override
        protected void w_1484_f() {
            if (this.P_4830_p) {
                this.s_956_w();
                this.R_4764_Y.n_1700_B(this.J_1907_R, this.v_4262_N, this.w_1484_f, this.t_148_a, 0.0, 0.0, 0.0);
            }
        }
    }

    public static class t_148_a
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public t_148_a(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            s_956_w dripparticle = new s_956_w(worldIn, x, y, z, Fluids.P_1922_E, ParticleTypes.M_588_G);
            dripparticle.n_1700_B(1.0f, 0.2857143f, 0.083333336f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    static class w_1484_f
    extends s_956_w {
        private w_1484_f(b_4507_u world, double x, double y, double z, Fluid fluid, ParticleOptions particleData) {
            super(world, x, y, z, fluid, particleData);
        }

        @Override
        protected void w_1484_f() {
            if (this.P_4830_p) {
                this.s_956_w();
                this.R_4764_Y.n_1700_B(this.J_1907_R, this.v_4262_N, this.w_1484_f, this.t_148_a, 0.0, 0.0, 0.0);
                this.R_4764_Y.n_1700_B(this.v_4262_N + 0.5, this.w_1484_f, this.t_148_a + 0.5, SoundEvents.M_1641_O, D_38_f.P_1922_E, 0.3f + this.R_4764_Y.w_1457_N.nextFloat() * 2.0f / 3.0f, 1.0f, false);
            }
        }
    }

    public static class v_4262_N
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public v_4262_N(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            w_1484_f dripparticle = new w_1484_f(worldIn, x, y, z, Fluids.n_1700_B, ParticleTypes.p_178_J);
            dripparticle.Y_259_p = 0.01f;
            dripparticle.n_1700_B(0.582f, 0.448f, 0.082f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    public static class u_1723_Y
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public u_1723_Y(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            n_1700_B dripparticle = new n_1700_B(worldIn, x, y, z, Fluids.R_4764_Y, ParticleTypes.h_1847_R);
            dripparticle.n_1700_B(0.2f, 0.3f, 1.0f);
            dripparticle.n_1700_B(this.n_1700_B);
            return dripparticle;
        }
    }

    public static class P_1922_E
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public P_1922_E(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            n_1700_B dripparticle$dripping = new n_1700_B(worldIn, x, y, z, Fluids.n_1700_B, ParticleTypes.D_60_a);
            dripparticle$dripping.n_1700_B = true;
            dripparticle$dripping.Y_259_p *= 0.01f;
            dripparticle$dripping.Y_601_j = 100;
            dripparticle$dripping.n_1700_B(0.51171875f, 0.03125f, 0.890625f);
            dripparticle$dripping.n_1700_B(this.n_1700_B);
            return dripparticle$dripping;
        }
    }

    public static class G_564_y
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public G_564_y(SpriteSet spriteSet) {
            this.n_1700_B = spriteSet;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            R_4764_Y dripparticle$drippinglava = new R_4764_Y(worldIn, x, y, z, Fluids.P_1922_E, ParticleTypes.u_2550_I);
            dripparticle$drippinglava.n_1700_B(this.n_1700_B);
            return dripparticle$drippinglava;
        }
    }

    static class R_4764_Y
    extends n_1700_B {
        private R_4764_Y(b_4507_u world, double x, double y, double z, Fluid fluid, ParticleOptions particleData) {
            super(world, x, y, z, fluid, particleData);
        }

        @Override
        protected void v_4262_N() {
            this.Q_2552_b = 1.0f;
            this.C_2741_M = 16.0f / (float)(40 - this.Y_601_j + 16);
            this.k_2293_S = 4.0f / (float)(40 - this.Y_601_j + 8);
            super.v_4262_N();
        }
    }

    public static class J_1907_R
    implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet n_1700_B;

        public J_1907_R(SpriteSet spriteWithAge) {
            this.n_1700_B = spriteWithAge;
        }

        @Override
        public c_3457_g n_1700_B(SimpleParticleType typeIn, b_4507_u worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            n_1700_B dripparticle$dripping = new n_1700_B(worldIn, x, y, z, Fluids.n_1700_B, ParticleTypes.Ping);
            dripparticle$dripping.Y_259_p *= 0.01f;
            dripparticle$dripping.Y_601_j = 100;
            dripparticle$dripping.n_1700_B(0.622f, 0.508f, 0.082f);
            dripparticle$dripping.n_1700_B(this.n_1700_B);
            return dripparticle$dripping;
        }
    }

    static class n_1700_B
    extends DripParticle {
        private final ParticleOptions J_1907_R;

        private n_1700_B(b_4507_u world, double x, double y, double z, Fluid fluid, ParticleOptions particleData) {
            super(world, x, y, z, fluid);
            this.J_1907_R = particleData;
            this.Y_259_p *= 0.02f;
            this.Y_601_j = 40;
        }

        @Override
        protected void v_4262_N() {
            if (this.Y_601_j-- <= 0) {
                this.s_956_w();
                this.R_4764_Y.n_1700_B(this.J_1907_R, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
            }
        }

        @Override
        protected void w_1484_f() {
            this.s_956_w *= 0.02;
            this.u_2550_I *= 0.02;
            this.M_588_G *= 0.02;
        }
    }
}


