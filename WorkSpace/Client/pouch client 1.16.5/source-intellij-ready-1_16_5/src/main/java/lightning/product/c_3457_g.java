/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.stream.Stream;
import lightning.product.D_4792_h;
import lightning.product.ParticleRenderType;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Main_1;
import lightning.product.CollisionContext;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import lightning.product.MobAppearanceParticle;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.z_883_p;
import net.optifine.BlockPosM;

public abstract class c_3457_g {
    private static final I_4817_s n_1700_B = new I_4817_s(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    protected final b_4507_u R_4764_Y;
    protected double G_564_y;
    protected double P_1922_E;
    protected double u_1723_Y;
    protected double v_4262_N;
    protected double w_1484_f;
    protected double t_148_a;
    protected double s_956_w;
    protected double u_2550_I;
    protected double M_588_G;
    private I_4817_s J_1907_R = n_1700_B;
    protected boolean P_4830_p;
    protected boolean h_1847_R = true;
    private boolean H_2857_Y;
    protected boolean Q_4569_t;
    protected float M_182_A = 0.6f;
    protected float t_1786_h = 1.8f;
    protected final Random multiplayerClientSuggestionProvider = new Random();
    protected int w_1457_N;
    protected int Y_601_j;
    protected float Y_259_p;
    protected float Q_2552_b = 1.0f;
    protected float C_2741_M = 1.0f;
    protected float k_2293_S = 1.0f;
    protected float q_2307_F = 1.0f;
    protected float Z_875_P;
    protected float c_3005_b;
    private BlockPosM A_4115_X = new BlockPosM();

    protected c_3457_g(b_4507_u world, double x, double y, double z) {
        this.R_4764_Y = world;
        this.n_1700_B(0.2f, 0.2f);
        this.J_1907_R(x, y, z);
        this.G_564_y = x;
        this.P_1922_E = y;
        this.u_1723_Y = z;
        this.Y_601_j = (int)(4.0f / (this.multiplayerClientSuggestionProvider.nextFloat() * 0.9f + 0.1f));
    }

    public c_3457_g(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        this(world, x, y, z);
        this.s_956_w = motionX + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        this.u_2550_I = motionY + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        this.M_588_G = motionZ + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        float f = (float)(Math.random() + Math.random() + 1.0) * 0.15f;
        float f1 = u_530_F.n_1700_B(this.s_956_w * this.s_956_w + this.u_2550_I * this.u_2550_I + this.M_588_G * this.M_588_G);
        this.s_956_w = this.s_956_w / (double)f1 * (double)f * (double)0.4f;
        this.u_2550_I = this.u_2550_I / (double)f1 * (double)f * (double)0.4f + (double)0.1f;
        this.M_588_G = this.M_588_G / (double)f1 * (double)f * (double)0.4f;
    }

    public c_3457_g R_4764_Y(float multiplier) {
        this.s_956_w *= (double)multiplier;
        this.u_2550_I = (this.u_2550_I - (double)0.1f) * (double)multiplier + (double)0.1f;
        this.M_588_G *= (double)multiplier;
        return this;
    }

    public c_3457_g G_564_y(float scale) {
        this.n_1700_B(0.2f * scale, 0.2f * scale);
        return this;
    }

    public void n_1700_B(float particleRedIn, float particleGreenIn, float particleBlueIn) {
        this.Q_2552_b = particleRedIn;
        this.C_2741_M = particleGreenIn;
        this.k_2293_S = particleBlueIn;
    }

    protected void P_1922_E(float alpha) {
        this.q_2307_F = alpha;
    }

    public void n_1700_B(int particleLifeTime) {
        this.Y_601_j = particleLifeTime;
    }

    public int t_148_a() {
        return this.Y_601_j;
    }

    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.u_2550_I -= 0.04 * (double)this.Y_259_p;
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.98f;
            this.u_2550_I *= (double)0.98f;
            this.M_588_G *= (double)0.98f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }

    public abstract void n_1700_B(D_4792_h var1, h_3572_K var2, float var3);

    public abstract ParticleRenderType J_1907_R();

    public String toString() {
        return this.getClass().getSimpleName() + ", Pos (" + this.v_4262_N + "," + this.w_1484_f + "," + this.t_148_a + "), RGBA (" + this.Q_2552_b + "," + this.C_2741_M + "," + this.k_2293_S + "," + this.q_2307_F + "), Age " + this.w_1457_N;
    }

    public void s_956_w() {
        this.Q_4569_t = true;
    }

    protected void n_1700_B(float particleWidth, float particleHeight) {
        if (particleWidth != this.M_182_A || particleHeight != this.t_1786_h) {
            this.M_182_A = particleWidth;
            this.t_1786_h = particleHeight;
            I_4817_s axisalignedbb = this.P_4830_p();
            double d0 = (axisalignedbb.minX + axisalignedbb.maxX - (double)particleWidth) / 2.0;
            double d1 = (axisalignedbb.minZ + axisalignedbb.maxZ - (double)particleWidth) / 2.0;
            this.n_1700_B(new I_4817_s(d0, axisalignedbb.minY, d1, d0 + (double)this.M_182_A, axisalignedbb.minY + (double)this.t_1786_h, d1 + (double)this.M_182_A));
        }
    }

    public void J_1907_R(double x, double y, double z) {
        this.v_4262_N = x;
        this.w_1484_f = y;
        this.t_148_a = z;
        float f = this.M_182_A / 2.0f;
        float f1 = this.t_1786_h;
        this.n_1700_B(new I_4817_s(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
    }

    public void n_1700_B(double x, double y, double z) {
        if (!this.H_2857_Y) {
            double d0 = x;
            double d1 = y;
            double d2 = z;
            if (this.h_1847_R && (x != 0.0 || y != 0.0 || z != 0.0) && this.R_4764_Y(x, y, z)) {
                e_2866_D vector3d = N_4263_v.n_1700_B(null, new e_2866_D(x, y, z), this.P_4830_p(), this.R_4764_Y, CollisionContext.J_1907_R(), new Main_1<s_1395_c>(Stream.empty()));
                x = vector3d.J_1907_R;
                y = vector3d.R_4764_Y;
                z = vector3d.G_564_y;
            }
            if (x != 0.0 || y != 0.0 || z != 0.0) {
                this.n_1700_B(this.P_4830_p().offset(x, y, z));
                this.u_2550_I();
            }
            if (Math.abs(d1) >= (double)1.0E-5f && Math.abs(y) < (double)1.0E-5f) {
                this.H_2857_Y = true;
            }
            boolean bl = this.P_4830_p = d1 != y && d1 < 0.0;
            if (d0 != x) {
                this.s_956_w = 0.0;
            }
            if (d2 != z) {
                this.M_588_G = 0.0;
            }
        }
    }

    protected void u_2550_I() {
        I_4817_s axisalignedbb = this.P_4830_p();
        this.v_4262_N = (axisalignedbb.minX + axisalignedbb.maxX) / 2.0;
        this.w_1484_f = axisalignedbb.minY;
        this.t_148_a = (axisalignedbb.minZ + axisalignedbb.maxZ) / 2.0;
    }

    protected int n_1700_B(float partialTick) {
        c_1514_x blockpos = new c_1514_x(this.v_4262_N, this.w_1484_f, this.t_148_a);
        return this.R_4764_Y.M_588_G(blockpos) ? z_883_p.n_1700_B(this.R_4764_Y, blockpos) : 0;
    }

    public boolean M_588_G() {
        return !this.Q_4569_t;
    }

    public I_4817_s P_4830_p() {
        return this.J_1907_R;
    }

    public void n_1700_B(I_4817_s bb) {
        this.J_1907_R = bb;
    }

    private boolean R_4764_Y(double p_hasNearBlocks_1_, double p_hasNearBlocks_3_, double p_hasNearBlocks_5_) {
        if (!(this.M_182_A > 1.0f) && !(this.t_1786_h > 1.0f)) {
            double d1;
            double d0;
            int i = u_530_F.R_4764_Y(this.v_4262_N);
            int j = u_530_F.R_4764_Y(this.w_1484_f);
            int k = u_530_F.R_4764_Y(this.t_148_a);
            this.A_4115_X.setXyz(i, j, k);
            K_4074_S blockstate = this.R_4764_Y.getBlockState(this.A_4115_X);
            if (!blockstate.v_4262_N()) {
                return true;
            }
            double d = p_hasNearBlocks_1_ > 0.0 ? this.J_1907_R.maxX : (d0 = p_hasNearBlocks_1_ < 0.0 ? this.J_1907_R.minX : this.v_4262_N);
            double d2 = p_hasNearBlocks_3_ > 0.0 ? this.J_1907_R.maxY : (d1 = p_hasNearBlocks_3_ < 0.0 ? this.J_1907_R.minY : this.w_1484_f);
            double d22 = p_hasNearBlocks_5_ > 0.0 ? this.J_1907_R.maxZ : (p_hasNearBlocks_5_ < 0.0 ? this.J_1907_R.minZ : this.t_148_a);
            int l = u_530_F.R_4764_Y(d0 + p_hasNearBlocks_1_);
            int i1 = u_530_F.R_4764_Y(d1 + p_hasNearBlocks_3_);
            int j1 = u_530_F.R_4764_Y(d22 + p_hasNearBlocks_5_);
            if (l != i || i1 != j || j1 != k) {
                this.A_4115_X.setXyz(l, i1, j1);
                K_4074_S blockstate1 = this.R_4764_Y.getBlockState(this.A_4115_X);
                if (!blockstate1.v_4262_N()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public boolean h_1847_R() {
        return !(this instanceof MobAppearanceParticle);
    }
}


