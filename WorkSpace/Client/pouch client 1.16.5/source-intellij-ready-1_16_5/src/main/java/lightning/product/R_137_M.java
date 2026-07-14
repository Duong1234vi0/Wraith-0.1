/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.NoRenderParticle;
import lightning.product.TextureSheetParticle;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.b_298_p;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.SpriteSet;
import lightning.product.c_3457_g;
import lightning.product.e_933_M;
import lightning.product.h_3572_K;
import lightning.product.SimpleAnimatedParticle;
import lightning.product.j_3341_s;
import lightning.product.ParticleProvider;
import lightning.product.SimpleParticleType;
import lightning.product.q_2896_o;
import lightning.product.FireworkRocketItem;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;

public class R_137_M {

    public static class P_1922_E
    extends NoRenderParticle {
        private int n_1700_B;
        private final b_298_p J_1907_R;
        private q_2896_o H_2857_Y;
        private boolean A_4115_X;

        public P_1922_E(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, b_298_p particleManager, @Nullable U_2912_j p_i232391_15_) {
            super(world, x, y, z);
            this.s_956_w = motionX;
            this.u_2550_I = motionY;
            this.M_588_G = motionZ;
            this.J_1907_R = particleManager;
            this.Y_601_j = 8;
            if (p_i232391_15_ != null) {
                this.H_2857_Y = p_i232391_15_.G_564_y("Explosions", 10);
                if (this.H_2857_Y.isEmpty()) {
                    this.H_2857_Y = null;
                } else {
                    this.Y_601_j = this.H_2857_Y.size() * 2 - 1;
                    for (int i = 0; i < this.H_2857_Y.size(); ++i) {
                        U_2912_j compoundnbt = this.H_2857_Y.n_1700_B(i);
                        if (!compoundnbt.t_1786_h("Flicker")) continue;
                        this.A_4115_X = true;
                        this.Y_601_j += 15;
                        break;
                    }
                }
            }
        }

        @Override
        public void n_1700_B() {
            if (this.n_1700_B == 0 && this.H_2857_Y != null) {
                boolean flag = this.R_4764_Y();
                boolean flag1 = false;
                if (this.H_2857_Y.size() >= 3) {
                    flag1 = true;
                } else {
                    for (int i = 0; i < this.H_2857_Y.size(); ++i) {
                        U_2912_j compoundnbt = this.H_2857_Y.n_1700_B(i);
                        if (FireworkRocketItem.n_1700_B.n_1700_B(compoundnbt.u_1723_Y("Type")) != FireworkRocketItem.n_1700_B.J_1907_R) continue;
                        flag1 = true;
                        break;
                    }
                }
                SoundEvent soundevent1 = flag1 ? (flag ? SoundEvents.l_2995_s : SoundEvents.M_2562_s) : (flag ? SoundEvents.m_1621_v : SoundEvents.e_837_t);
                this.R_4764_Y.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, soundevent1, D_38_f.t_148_a, 20.0f, 0.95f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.1f, true);
            }
            if (this.n_1700_B % 2 == 0 && this.H_2857_Y != null && this.n_1700_B / 2 < this.H_2857_Y.size()) {
                int k = this.n_1700_B / 2;
                U_2912_j compoundnbt1 = this.H_2857_Y.n_1700_B(k);
                FireworkRocketItem.n_1700_B fireworkrocketitem$shape = FireworkRocketItem.n_1700_B.n_1700_B(compoundnbt1.u_1723_Y("Type"));
                boolean flag4 = compoundnbt1.t_1786_h("Trail");
                boolean flag2 = compoundnbt1.t_1786_h("Flicker");
                int[] aint = compoundnbt1.h_1847_R("Colors");
                int[] aint1 = compoundnbt1.h_1847_R("FadeColors");
                if (aint.length == 0) {
                    aint = new int[]{e_933_M.M_182_A.u_1723_Y()};
                }
                switch (fireworkrocketitem$shape) {
                    default: {
                        this.n_1700_B(0.25, 2, aint, aint1, flag4, flag2);
                        break;
                    }
                    case J_1907_R: {
                        this.n_1700_B(0.5, 4, aint, aint1, flag4, flag2);
                        break;
                    }
                    case R_4764_Y: {
                        this.n_1700_B(0.5, new double[][]{{0.0, 1.0}, {0.3455, 0.309}, {0.9511, 0.309}, {0.3795918367346939, -0.12653061224489795}, {0.6122448979591837, -0.8040816326530612}, {0.0, -0.35918367346938773}}, aint, aint1, flag4, flag2, false);
                        break;
                    }
                    case G_564_y: {
                        this.n_1700_B(0.5, new double[][]{{0.0, 0.2}, {0.2, 0.2}, {0.2, 0.6}, {0.6, 0.6}, {0.6, 0.2}, {0.2, 0.2}, {0.2, 0.0}, {0.4, 0.0}, {0.4, -0.6}, {0.2, -0.6}, {0.2, -0.4}, {0.0, -0.4}}, aint, aint1, flag4, flag2, true);
                        break;
                    }
                    case P_1922_E: {
                        this.n_1700_B(aint, aint1, flag4, flag2);
                    }
                }
                int j = aint[0];
                float f = (float)((j & 0xFF0000) >> 16) / 255.0f;
                float f1 = (float)((j & 0xFF00) >> 8) / 255.0f;
                float f2 = (float)((j & 0xFF) >> 0) / 255.0f;
                c_3457_g particle = this.J_1907_R.n_1700_B(ParticleTypes.Y_1740_V, this.v_4262_N, this.w_1484_f, this.t_148_a, 0.0, 0.0, 0.0);
                particle.n_1700_B(f, f1, f2);
            }
            ++this.n_1700_B;
            if (this.n_1700_B > this.Y_601_j) {
                if (this.A_4115_X) {
                    boolean flag3 = this.R_4764_Y();
                    SoundEvent soundevent = flag3 ? SoundEvents.U_3823_u : SoundEvents.B_3040_x;
                    this.R_4764_Y.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, soundevent, D_38_f.t_148_a, 20.0f, 0.9f + this.multiplayerClientSuggestionProvider.nextFloat() * 0.15f, true);
                }
                this.s_956_w();
            }
        }

        private boolean R_4764_Y() {
            MinecraftClient minecraft = MinecraftClient.A_4115_X();
            return minecraft.s_956_w.M_588_G().J_1907_R().R_4764_Y(this.v_4262_N, this.w_1484_f, this.t_148_a) >= 256.0;
        }

        private void n_1700_B(double x, double y, double z, double motionX, double motionY, double motionZ, int[] sparkColors, int[] sparkColorFades, boolean hasTrail, boolean hasTwinkle) {
            R_4764_Y fireworkparticle$spark = (R_4764_Y)this.J_1907_R.n_1700_B(ParticleTypes.q_2307_F, x, y, z, motionX, motionY, motionZ);
            fireworkparticle$spark.n_1700_B(hasTrail);
            fireworkparticle$spark.J_1907_R(hasTwinkle);
            fireworkparticle$spark.P_1922_E(0.99f);
            int i = this.multiplayerClientSuggestionProvider.nextInt(sparkColors.length);
            fireworkparticle$spark.J_1907_R(sparkColors[i]);
            if (sparkColorFades.length > 0) {
                fireworkparticle$spark.R_4764_Y(j_3341_s.n_1700_B(sparkColorFades, this.multiplayerClientSuggestionProvider));
            }
        }

        private void n_1700_B(double speed, int size, int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn) {
            double d0 = this.v_4262_N;
            double d1 = this.w_1484_f;
            double d2 = this.t_148_a;
            for (int i = -size; i <= size; ++i) {
                for (int j = -size; j <= size; ++j) {
                    for (int k = -size; k <= size; ++k) {
                        double d3 = (double)j + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 0.5;
                        double d4 = (double)i + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 0.5;
                        double d5 = (double)k + (this.multiplayerClientSuggestionProvider.nextDouble() - this.multiplayerClientSuggestionProvider.nextDouble()) * 0.5;
                        double d6 = (double)u_530_F.n_1700_B(d3 * d3 + d4 * d4 + d5 * d5) / speed + this.multiplayerClientSuggestionProvider.nextGaussian() * 0.05;
                        this.n_1700_B(d0, d1, d2, d3 / d6, d4 / d6, d5 / d6, colours, fadeColours, trail, twinkleIn);
                        if (i == -size || i == size || j == -size || j == size) continue;
                        k += size * 2 - 1;
                    }
                }
            }
        }

        private void n_1700_B(double speed, double[][] shape, int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn, boolean p_92038_8_) {
            double d0 = shape[0][0];
            double d1 = shape[0][1];
            this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, d0 * speed, d1 * speed, 0.0, colours, fadeColours, trail, twinkleIn);
            float f = this.multiplayerClientSuggestionProvider.nextFloat() * (float)Math.PI;
            double d2 = p_92038_8_ ? 0.034 : 0.34;
            for (int i = 0; i < 3; ++i) {
                double d3 = (double)f + (double)((float)i * (float)Math.PI) * d2;
                double d4 = d0;
                double d5 = d1;
                for (int j = 1; j < shape.length; ++j) {
                    double d6 = shape[j][0];
                    double d7 = shape[j][1];
                    for (double d8 = 0.25; d8 <= 1.0; d8 += 0.25) {
                        double d9 = u_530_F.G_564_y(d8, d4, d6) * speed;
                        double d10 = u_530_F.G_564_y(d8, d5, d7) * speed;
                        double d11 = d9 * Math.sin(d3);
                        d9 *= Math.cos(d3);
                        for (double d12 = -1.0; d12 <= 1.0; d12 += 2.0) {
                            this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, d9 * d12, d10, d11 * d12, colours, fadeColours, trail, twinkleIn);
                        }
                    }
                    d4 = d6;
                    d5 = d7;
                }
            }
        }

        private void n_1700_B(int[] colours, int[] fadeColours, boolean trail, boolean twinkleIn) {
            double d0 = this.multiplayerClientSuggestionProvider.nextGaussian() * 0.05;
            double d1 = this.multiplayerClientSuggestionProvider.nextGaussian() * 0.05;
            for (int i = 0; i < 70; ++i) {
                double d2 = this.s_956_w * 0.5 + this.multiplayerClientSuggestionProvider.nextGaussian() * 0.15 + d0;
                double d3 = this.M_588_G * 0.5 + this.multiplayerClientSuggestionProvider.nextGaussian() * 0.15 + d1;
                double d4 = this.u_2550_I * 0.5 + this.multiplayerClientSuggestionProvider.nextDouble() * 0.5;
                this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, d2, d4, d3, colours, fadeColours, trail, twinkleIn);
            }
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
            R_4764_Y fireworkparticle$spark = new R_4764_Y(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, MinecraftClient.A_4115_X().v_4262_N, this.n_1700_B);
            fireworkparticle$spark.P_1922_E(0.99f);
            return fireworkparticle$spark;
        }
    }

    static class R_4764_Y
    extends SimpleAnimatedParticle {
        private boolean J_1907_R;
        private boolean Y_1740_V;
        private final b_298_p t_4043_B;
        private float x_607_J;
        private float e_4240_b;
        private float n_3318_d;
        private boolean d_2427_y;

        private R_4764_Y(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ, b_298_p particleManager, SpriteSet spriteWithAge) {
            super(world, x, y, z, spriteWithAge, -0.004f);
            this.s_956_w = motionX;
            this.u_2550_I = motionY;
            this.M_588_G = motionZ;
            this.t_4043_B = particleManager;
            this.A_4115_X *= 0.75f;
            this.Y_601_j = 48 + this.multiplayerClientSuggestionProvider.nextInt(12);
            this.J_1907_R(spriteWithAge);
        }

        public void n_1700_B(boolean trailIn) {
            this.J_1907_R = trailIn;
        }

        public void J_1907_R(boolean twinkleIn) {
            this.Y_1740_V = twinkleIn;
        }

        @Override
        public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
            if (!this.Y_1740_V || this.w_1457_N < this.Y_601_j / 3 || (this.w_1457_N + this.Y_601_j) / 3 % 2 == 0) {
                super.n_1700_B(buffer, renderInfo, partialTicks);
            }
        }

        @Override
        public void n_1700_B() {
            super.n_1700_B();
            if (this.J_1907_R && this.w_1457_N < this.Y_601_j / 2 && (this.w_1457_N + this.Y_601_j) % 2 == 0) {
                R_4764_Y fireworkparticle$spark = new R_4764_Y(this.R_4764_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, 0.0, 0.0, 0.0, this.t_4043_B, this.n_1700_B);
                fireworkparticle$spark.P_1922_E(0.99f);
                fireworkparticle$spark.n_1700_B(this.Q_2552_b, this.C_2741_M, this.k_2293_S);
                fireworkparticle$spark.w_1457_N = fireworkparticle$spark.Y_601_j / 2;
                if (this.d_2427_y) {
                    fireworkparticle$spark.d_2427_y = true;
                    fireworkparticle$spark.x_607_J = this.x_607_J;
                    fireworkparticle$spark.e_4240_b = this.e_4240_b;
                    fireworkparticle$spark.n_3318_d = this.n_3318_d;
                }
                fireworkparticle$spark.Y_1740_V = this.Y_1740_V;
                this.t_4043_B.n_1700_B(fireworkparticle$spark);
            }
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
            n_1700_B fireworkparticle$overlay = new n_1700_B(worldIn, x, y, z);
            fireworkparticle$overlay.n_1700_B(this.n_1700_B);
            return fireworkparticle$overlay;
        }
    }

    public static class n_1700_B
    extends TextureSheetParticle {
        private n_1700_B(b_4507_u world, double x, double y, double z) {
            super(world, x, y, z);
            this.Y_601_j = 4;
        }

        @Override
        public ParticleRenderType J_1907_R() {
            return ParticleRenderType.R_4764_Y;
        }

        @Override
        public void n_1700_B(D_4792_h buffer, h_3572_K renderInfo, float partialTicks) {
            this.P_1922_E(0.6f - ((float)this.w_1457_N + partialTicks - 1.0f) * 0.25f * 0.5f);
            super.n_1700_B(buffer, renderInfo, partialTicks);
        }

        @Override
        public float J_1907_R(float scaleFactor) {
            return 7.1f * u_530_F.n_1700_B(((float)this.w_1457_N + scaleFactor - 1.0f) * 0.25f * (float)Math.PI);
        }
    }
}



