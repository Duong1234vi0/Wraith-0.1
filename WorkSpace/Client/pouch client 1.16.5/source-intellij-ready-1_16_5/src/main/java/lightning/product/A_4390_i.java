/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Locale;
import javax.annotation.Nullable;
import lightning.product.C_4114_x;
import lightning.product.DifficultyInstance;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_933_M;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.AbstractSchoolingFish;
import lightning.product.Items;
import lightning.product.t_5_h;

public class A_4390_i
extends AbstractSchoolingFish {
    private static final h_256_u<Integer> J_1907_R = C_4114_x.n_1700_B(A_4390_i.class, EntityDataSerializers.J_1907_R);
    private static final g_2336_b[] R_4764_Y = new g_2336_b[]{new g_2336_b("textures/entity/fish/tropical_a.png"), new g_2336_b("textures/entity/fish/tropical_b.png")};
    private static final g_2336_b[] h_1847_R = new g_2336_b[]{new g_2336_b("textures/entity/fish/tropical_a_pattern_1.png"), new g_2336_b("textures/entity/fish/tropical_a_pattern_2.png"), new g_2336_b("textures/entity/fish/tropical_a_pattern_3.png"), new g_2336_b("textures/entity/fish/tropical_a_pattern_4.png"), new g_2336_b("textures/entity/fish/tropical_a_pattern_5.png"), new g_2336_b("textures/entity/fish/tropical_a_pattern_6.png")};
    private static final g_2336_b[] Q_4569_t = new g_2336_b[]{new g_2336_b("textures/entity/fish/tropical_b_pattern_1.png"), new g_2336_b("textures/entity/fish/tropical_b_pattern_2.png"), new g_2336_b("textures/entity/fish/tropical_b_pattern_3.png"), new g_2336_b("textures/entity/fish/tropical_b_pattern_4.png"), new g_2336_b("textures/entity/fish/tropical_b_pattern_5.png"), new g_2336_b("textures/entity/fish/tropical_b_pattern_6.png")};
    public static final int[] n_1700_B = new int[]{A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.w_1484_f, e_933_M.J_1907_R, e_933_M.w_1484_f), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.v_4262_N, e_933_M.w_1484_f, e_933_M.w_1484_f), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.v_4262_N, e_933_M.w_1484_f, e_933_M.M_588_G), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.M_588_G, e_933_M.n_1700_B, e_933_M.w_1484_f), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.J_1907_R, e_933_M.M_588_G, e_933_M.w_1484_f), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.n_1700_B, e_933_M.J_1907_R, e_933_M.n_1700_B), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.u_1723_Y, e_933_M.v_4262_N, e_933_M.G_564_y), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.s_956_w, e_933_M.u_2550_I, e_933_M.P_1922_E), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.M_588_G, e_933_M.n_1700_B, e_933_M.Q_4569_t), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.u_1723_Y, e_933_M.n_1700_B, e_933_M.P_1922_E), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.t_148_a, e_933_M.n_1700_B, e_933_M.w_1484_f), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.M_588_G, e_933_M.n_1700_B, e_933_M.J_1907_R), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.G_564_y, e_933_M.s_956_w, e_933_M.v_4262_N), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.P_1922_E, e_933_M.u_1723_Y, e_933_M.G_564_y), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.u_2550_I, e_933_M.Q_4569_t, e_933_M.n_1700_B), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.R_4764_Y, e_933_M.w_1484_f, e_933_M.Q_4569_t), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.s_956_w, e_933_M.Q_4569_t, e_933_M.n_1700_B), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.v_4262_N, e_933_M.n_1700_B, e_933_M.P_1922_E), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.n_1700_B, e_933_M.Q_4569_t, e_933_M.n_1700_B), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.J_1907_R, e_933_M.w_1484_f, e_933_M.n_1700_B), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.G_564_y, e_933_M.s_956_w, e_933_M.P_1922_E), A_4390_i.n_1700_B(lightning.product.A_4390_i$J_1907_R.v_4262_N, e_933_M.P_1922_E, e_933_M.P_1922_E)};
    private boolean M_182_A = true;

    private static int n_1700_B(J_1907_R size, e_933_M pattern, e_933_M bodyColor) {
        return size.n_1700_B() & 0xFF | (size.J_1907_R() & 0xFF) << 8 | (pattern.J_1907_R() & 0xFF) << 16 | (bodyColor.J_1907_R() & 0xFF) << 24;
    }

    public A_4390_i(t_5_h<? extends A_4390_i> p_i50242_1_, b_4507_u p_i50242_2_) {
        super((t_5_h<? extends AbstractSchoolingFish>)p_i50242_1_, p_i50242_2_);
    }

    public static String J_1907_R(int p_212324_0_) {
        return "entity.minecraft.tropical_fish.predefined." + p_212324_0_;
    }

    public static e_933_M w_1457_N(int p_212326_0_) {
        return e_933_M.n_1700_B(A_4390_i.k_2293_S(p_212326_0_));
    }

    public static e_933_M Y_601_j(int p_212323_0_) {
        return e_933_M.n_1700_B(A_4390_i.q_2307_F(p_212323_0_));
    }

    public static String Y_259_p(int p_212327_0_) {
        int i = A_4390_i.C_2741_M(p_212327_0_);
        int j = A_4390_i.Z_875_P(p_212327_0_);
        return "entity.minecraft.tropical_fish.type." + lightning.product.A_4390_i$J_1907_R.n_1700_B(i, j);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(J_1907_R, 0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Variant", this.V_537_k());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Q_2552_b(compound.w_1484_f("Variant"));
    }

    public void Q_2552_b(int p_204215_1_) {
        this.l_4537_E.J_1907_R(J_1907_R, p_204215_1_);
    }

    @Override
    public boolean t_1786_h(int sizeIn) {
        return !this.M_182_A;
    }

    public int V_537_k() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    @Override
    protected void u_2550_I(Z_1993_T bucket) {
        super.u_2550_I(bucket);
        U_2912_j compoundnbt = bucket.M_182_A();
        compoundnbt.J_1907_R("BucketVariantTag", this.V_537_k());
    }

    @Override
    protected Z_1993_T y_4642_Y() {
        return new Z_1993_T(Items.v_2826_q);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.FenceGateBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.M_4472_P;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.FlowerBlock;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.FletchingTableBlock;
    }

    private static int k_2293_S(int p_204216_0_) {
        return (p_204216_0_ & 0xFF0000) >> 16;
    }

    public float[] c_2086_l() {
        return e_933_M.n_1700_B(A_4390_i.k_2293_S(this.V_537_k())).G_564_y();
    }

    private static int q_2307_F(int p_204212_0_) {
        return (p_204212_0_ & 0xFF000000) >> 24;
    }

    public float[] o_4117_e() {
        return e_933_M.n_1700_B(A_4390_i.q_2307_F(this.V_537_k())).G_564_y();
    }

    public static int C_2741_M(int p_212325_0_) {
        return Math.min(p_212325_0_ & 0xFF, 1);
    }

    public int U_3758_B() {
        return A_4390_i.C_2741_M(this.V_537_k());
    }

    private static int Z_875_P(int p_204213_0_) {
        return Math.min((p_204213_0_ & 0xFF00) >> 8, 5);
    }

    public g_2336_b y_3417_N() {
        return A_4390_i.C_2741_M(this.V_537_k()) == 0 ? h_1847_R[A_4390_i.Z_875_P(this.V_537_k())] : Q_4569_t[A_4390_i.Z_875_P(this.V_537_k())];
    }

    public g_2336_b A_1306_N() {
        return R_4764_Y[A_4390_i.C_2741_M(this.V_537_k())];
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        int l;
        int k;
        int j;
        int i;
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (dataTag != null && dataTag.R_4764_Y("BucketVariantTag", 3)) {
            this.Q_2552_b(dataTag.w_1484_f("BucketVariantTag"));
            return spawnDataIn;
        }
        if (spawnDataIn instanceof n_1700_B) {
            n_1700_B tropicalfishentity$tropicalfishdata = (n_1700_B)spawnDataIn;
            i = tropicalfishentity$tropicalfishdata.J_1907_R;
            j = tropicalfishentity$tropicalfishdata.R_4764_Y;
            k = tropicalfishentity$tropicalfishdata.G_564_y;
            l = tropicalfishentity$tropicalfishdata.P_1922_E;
        } else if ((double)this.RealmsWorldOptions.nextFloat() < 0.9) {
            int i1 = j_3341_s.n_1700_B(n_1700_B, this.RealmsWorldOptions);
            i = i1 & 0xFF;
            j = (i1 & 0xFF00) >> 8;
            k = (i1 & 0xFF0000) >> 16;
            l = (i1 & 0xFF000000) >> 24;
            spawnDataIn = new n_1700_B(this, i, j, k, l);
        } else {
            this.M_182_A = false;
            i = this.RealmsWorldOptions.nextInt(2);
            j = this.RealmsWorldOptions.nextInt(6);
            k = this.RealmsWorldOptions.nextInt(15);
            l = this.RealmsWorldOptions.nextInt(15);
        }
        this.Q_2552_b(i | j << 8 | k << 16 | l << 24);
        return spawnDataIn;
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(0, 0);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(0, 1);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(0, 2);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(0, 3);
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(0, 4);
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R(0, 5);
        public static final /* enum */ J_1907_R v_4262_N = new J_1907_R(1, 0);
        public static final /* enum */ J_1907_R w_1484_f = new J_1907_R(1, 1);
        public static final /* enum */ J_1907_R t_148_a = new J_1907_R(1, 2);
        public static final /* enum */ J_1907_R s_956_w = new J_1907_R(1, 3);
        public static final /* enum */ J_1907_R u_2550_I = new J_1907_R(1, 4);
        public static final /* enum */ J_1907_R M_588_G = new J_1907_R(1, 5);
        private final int P_4830_p;
        private final int h_1847_R;
        private static final J_1907_R[] Q_4569_t;
        private static final /* synthetic */ J_1907_R[] M_182_A;

        public static J_1907_R[] values() {
            return (J_1907_R[])M_182_A.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(int p_i49832_3_, int p_i49832_4_) {
            this.P_4830_p = p_i49832_3_;
            this.h_1847_R = p_i49832_4_;
        }

        public int n_1700_B() {
            return this.P_4830_p;
        }

        public int J_1907_R() {
            return this.h_1847_R;
        }

        public static String n_1700_B(int p_212548_0_, int p_212548_1_) {
            return Q_4569_t[p_212548_1_ + 6 * p_212548_0_].R_4764_Y();
        }

        public String R_4764_Y() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        private static /* synthetic */ J_1907_R[] G_564_y() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G};
        }

        static {
            M_182_A = lightning.product.A_4390_i$J_1907_R.G_564_y();
            Q_4569_t = lightning.product.A_4390_i$J_1907_R.values();
        }
    }

    static class n_1700_B
    extends AbstractSchoolingFish.n_1700_B {
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;

        private n_1700_B(A_4390_i p_i49859_1_, int p_i49859_2_, int p_i49859_3_, int p_i49859_4_, int p_i49859_5_) {
            super(p_i49859_1_);
            this.J_1907_R = p_i49859_2_;
            this.R_4764_Y = p_i49859_3_;
            this.G_564_y = p_i49859_4_;
            this.P_1922_E = p_i49859_5_;
        }
    }
}


