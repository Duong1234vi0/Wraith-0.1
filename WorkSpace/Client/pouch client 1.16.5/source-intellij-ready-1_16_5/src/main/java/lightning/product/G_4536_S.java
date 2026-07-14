/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.CraftingContainer;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.MenuType;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.TemptGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_2900_S;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Shearable;
import lightning.product.DyeItem;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.g_1941_L;
import lightning.product.g_2336_b;
import lightning.product.g_4684_T;
import lightning.product.h_256_u;
import lightning.product.RecipeType;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.n_1494_c;
import lightning.product.o_4810_o;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.x_1688_C;

public class G_4536_S
extends Animal
implements Shearable {
    private static final h_256_u<Byte> h_1847_R = C_4114_x.n_1700_B(G_4536_S.class, EntityDataSerializers.n_1700_B);
    private static final Map<e_933_M, q_1803_e> Q_4569_t = j_3341_s.n_1700_B(Maps.newEnumMap(e_933_M.class), (T p_203402_0_) -> {
        p_203402_0_.put(e_933_M.n_1700_B, a_3742_W.R_3077_Z);
        p_203402_0_.put(e_933_M.J_1907_R, a_3742_W.RealmsScreenWithCallback);
        p_203402_0_.put(e_933_M.R_4764_Y, a_3742_W.M_2677_i);
        p_203402_0_.put(e_933_M.G_564_y, a_3742_W.c_132_F);
        p_203402_0_.put(e_933_M.P_1922_E, a_3742_W.g_4106_L);
        p_203402_0_.put(e_933_M.u_1723_Y, a_3742_W.RealmsClientOutdatedScreen);
        p_203402_0_.put(e_933_M.v_4262_N, a_3742_W.W_3464_O);
        p_203402_0_.put(e_933_M.w_1484_f, a_3742_W.RealmsConfirmScreen);
        p_203402_0_.put(e_933_M.t_148_a, a_3742_W.RealmsCreateRealmScreen);
        p_203402_0_.put(e_933_M.s_956_w, a_3742_W.C_290_v);
        p_203402_0_.put(e_933_M.u_2550_I, a_3742_W.w_728_N);
        p_203402_0_.put(e_933_M.M_588_G, a_3742_W.J_4256_G);
        p_203402_0_.put(e_933_M.P_4830_p, a_3742_W.RealmsLongConfirmationScreen);
        p_203402_0_.put(e_933_M.h_1847_R, a_3742_W.RealmsLongRunningMcoTaskScreen);
        p_203402_0_.put(e_933_M.Q_4569_t, a_3742_W.i_2993_w);
        p_203402_0_.put(e_933_M.M_182_A, a_3742_W.RealmsParentalConsentScreen);
    });
    private static final Map<e_933_M, float[]> M_182_A = Maps.newEnumMap(Arrays.stream(e_933_M.values()).collect(Collectors.toMap(p_200204_0_ -> p_200204_0_, G_4536_S::R_4764_Y)));
    private int t_1786_h;
    private g_4684_T multiplayerClientSuggestionProvider;

    private static float[] R_4764_Y(e_933_M dyeColorIn) {
        if (dyeColorIn == e_933_M.n_1700_B) {
            return new float[]{0.9019608f, 0.9019608f, 0.9019608f};
        }
        float[] afloat = dyeColorIn.G_564_y();
        float f = 0.75f;
        return new float[]{afloat[0] * 0.75f, afloat[1] * 0.75f, afloat[2] * 0.75f};
    }

    public static float[] n_1700_B(e_933_M dyeColor) {
        return M_182_A.get(dyeColor);
    }

    public G_4536_S(t_5_h<? extends G_4536_S> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.multiplayerClientSuggestionProvider = new g_4684_T(this);
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 1.25));
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(3, new TemptGoal((PathfinderMob)this, 1.1, b_3278_X.n_1700_B(Items.V_3441_j), false));
        this.s_956_w.n_1700_B(4, new v_2621_q(this, 1.1));
        this.s_956_w.n_1700_B(5, this.multiplayerClientSuggestionProvider);
        this.s_956_w.n_1700_B(6, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected void X_933_l() {
        this.t_1786_h = this.multiplayerClientSuggestionProvider.v_4262_N();
        super.X_933_l();
    }

    @Override
    public void Y_1740_V() {
        if (this.O_508_d.Y_259_p) {
            this.t_1786_h = Math.max(0, this.t_1786_h - 1);
        }
        super.Y_1740_V();
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 8.0).n_1700_B(Attributes.G_564_y, 0.23f);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, (byte)0);
    }

    @Override
    public g_2336_b g_221_o() {
        if (this.V_1176_p()) {
            return this.f_4016_n().w_1484_f();
        }
        switch (this.h_1640_b()) {
            default: {
                return o_4810_o.g_221_o;
            }
            case J_1907_R: {
                return o_4810_o.e_2887_G;
            }
            case R_4764_Y: {
                return o_4810_o.B_1668_F;
            }
            case G_564_y: {
                return o_4810_o.g_164_R;
            }
            case P_1922_E: {
                return o_4810_o.X_933_l;
            }
            case u_1723_Y: {
                return o_4810_o.Z_976_R;
            }
            case v_4262_N: {
                return o_4810_o.H_1990_U;
            }
            case w_1484_f: {
                return o_4810_o.N_2525_X;
            }
            case t_148_a: {
                return o_4810_o.c_4037_x;
            }
            case s_956_w: {
                return o_4810_o.g_2268_R;
            }
            case u_2550_I: {
                return o_4810_o.T_3594_S;
            }
            case M_588_G: {
                return o_4810_o.D_4792_h;
            }
            case P_4830_p: {
                return o_4810_o.s_2632_s;
            }
            case h_1847_R: {
                return o_4810_o.l_1233_K;
            }
            case Q_4569_t: {
                return o_4810_o.z_1333_t;
            }
            case M_182_A: 
        }
        return o_4810_o.O_508_d;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 10) {
            this.t_1786_h = 40;
        } else {
            super.n_1700_B(id);
        }
    }

    public float c_3005_b(float p_70894_1_) {
        if (this.t_1786_h <= 0) {
            return 0.0f;
        }
        if (this.t_1786_h >= 4 && this.t_1786_h <= 36) {
            return 1.0f;
        }
        return this.t_1786_h < 4 ? ((float)this.t_1786_h - p_70894_1_) / 4.0f : -((float)(this.t_1786_h - 40) - p_70894_1_) / 4.0f;
    }

    public float H_2857_Y(float p_70890_1_) {
        if (this.t_1786_h > 4 && this.t_1786_h <= 36) {
            float f = ((float)(this.t_1786_h - 4) - p_70890_1_) / 32.0f;
            return 0.62831855f + 0.2199115f * u_530_F.n_1700_B(f * 28.7f);
        }
        return this.t_1786_h > 0 ? 0.62831855f : this.f_4016_n * ((float)Math.PI / 180);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.LightPredicate) {
            if (!this.O_508_d.Y_259_p && this.n_1700_B()) {
                this.n_1700_B(D_38_f.w_1484_f);
                itemstack.n_1700_B(1, p_230254_1_, (T p_213613_1_) -> p_213613_1_.G_564_y(p_230254_2_));
                return m_3054_I.n_1700_B;
            }
            return m_3054_I.J_1907_R;
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    public void n_1700_B(D_38_f category) {
        this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.D_3640_k, category, 1.0f, 1.0f);
        this.w_1457_N(true);
        int i = 1 + this.RealmsWorldOptions.nextInt(3);
        for (int j = 0; j < i; ++j) {
            n_1494_c itementity = this.n_1700_B(Q_4569_t.get(this.h_1640_b()), 1);
            if (itementity == null) continue;
            itementity.v_4262_N(itementity.I_4348_c().J_1907_R((this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.1f, this.RealmsWorldOptions.nextFloat() * 0.05f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.1f));
        }
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && !this.V_1176_p() && !this.d_();
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Sheared", this.V_1176_p());
        compound.n_1700_B("Color", (byte)this.h_1640_b().J_1907_R());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("Sheared"));
        this.J_1907_R(e_933_M.n_1700_B(compound.u_1723_Y("Color")));
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.A_1604_A;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.k_200_a;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.V_3982_O;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.U_4107_W, 0.15f, 1.0f);
    }

    public e_933_M h_1640_b() {
        return e_933_M.n_1700_B(this.l_4537_E.n_1700_B(h_1847_R) & 0xF);
    }

    public void J_1907_R(e_933_M color) {
        byte b0 = this.l_4537_E.n_1700_B(h_1847_R);
        this.l_4537_E.J_1907_R(h_1847_R, (byte)(b0 & 0xF0 | color.J_1907_R() & 0xF));
    }

    public boolean V_1176_p() {
        return (this.l_4537_E.n_1700_B(h_1847_R) & 0x10) != 0;
    }

    public void w_1457_N(boolean sheared) {
        byte b0 = this.l_4537_E.n_1700_B(h_1847_R);
        if (sheared) {
            this.l_4537_E.J_1907_R(h_1847_R, (byte)(b0 | 0x10));
        } else {
            this.l_4537_E.J_1907_R(h_1847_R, (byte)(b0 & 0xFFFFFFEF));
        }
    }

    public static e_933_M n_1700_B(Random random) {
        int i = random.nextInt(100);
        if (i < 5) {
            return e_933_M.M_182_A;
        }
        if (i < 10) {
            return e_933_M.w_1484_f;
        }
        if (i < 15) {
            return e_933_M.t_148_a;
        }
        if (i < 18) {
            return e_933_M.P_4830_p;
        }
        return random.nextInt(500) == 0 ? e_933_M.v_4262_N : e_933_M.n_1700_B;
    }

    public G_4536_S J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        G_4536_S sheepentity = (G_4536_S)p_241840_2_;
        G_4536_S sheepentity1 = t_5_h.k_3961_g.n_1700_B(p_241840_1_);
        sheepentity1.J_1907_R(this.n_1700_B(this, (Animal)sheepentity));
        return sheepentity1;
    }

    @Override
    public void d_2427_y() {
        this.w_1457_N(false);
        if (this.d_()) {
            this.a_(60);
        }
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.J_1907_R(G_4536_S.n_1700_B(worldIn.e_4240_b()));
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private e_933_M n_1700_B(Animal father, Animal mother) {
        e_933_M dyecolor = ((G_4536_S)father).h_1640_b();
        e_933_M dyecolor1 = ((G_4536_S)mother).h_1640_b();
        CraftingContainer craftinginventory = G_4536_S.n_1700_B(dyecolor, dyecolor1);
        return this.O_508_d.s_956_w().n_1700_B(RecipeType.n_1700_B, craftinginventory, this.O_508_d).map(p_213614_1_ -> p_213614_1_.n_1700_B(craftinginventory)).map(Z_1993_T::J_1907_R).filter(DyeItem.class::isInstance).map(DyeItem.class::cast).map(DyeItem::R_4764_Y).orElseGet(() -> this.O_508_d.w_1457_N.nextBoolean() ? dyecolor : dyecolor1);
    }

    private static CraftingContainer n_1700_B(e_933_M color, e_933_M color1) {
        CraftingContainer craftinginventory = new CraftingContainer(new a_2900_S((MenuType)null, -1){

            @Override
            public boolean n_1700_B(a_3913_L playerIn) {
                return false;
            }
        }, 2, 1);
        craftinginventory.J_1907_R(0, new Z_1993_T(DyeItem.n_1700_B(color)));
        craftinginventory.J_1907_R(1, new Z_1993_T(DyeItem.n_1700_B(color1)));
        return craftinginventory;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.95f * sizeIn.J_1907_R;
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }
}


