/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.F_4355_q;
import lightning.product.ReputationEventType;
import lightning.product.I_2154_Z;
import lightning.product.J_2868_p;
import lightning.product.MobEffects;
import lightning.product.VillagerData;
import lightning.product.K_4096_w;
import lightning.product.L_2225_p;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.VillagerProfession;
import lightning.product.R_3043_n;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3137_a;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.MerchantOffers;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.l_4118_l;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;
import org.apache.logging.log4j.Logger;

public class l_4140_i
extends F_4355_q
implements I_2154_Z {
    private static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(l_4140_i.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<VillagerData> J_1907_R = C_4114_x.n_1700_B(l_4140_i.class, EntityDataSerializers.t_1786_h);
    private int R_4764_Y;
    private UUID h_1847_R;
    private Tag Q_4569_t;
    private U_2912_j M_182_A;
    private int t_1786_h;

    public l_4140_i(t_5_h<? extends l_4140_i> p_i50186_1_, b_4507_u p_i50186_2_) {
        super((t_5_h<? extends F_4355_q>)p_i50186_1_, p_i50186_2_);
        this.n_1700_B(this.c_2086_l().n_1700_B(V_3137_a.r_715_M.n_1700_B(this.RealmsWorldOptions)));
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
        this.l_4537_E.n_1700_B(J_1907_R, new VillagerData(R_3043_n.R_4764_Y, VillagerProfession.n_1700_B, 1));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        VillagerData.n_1700_B.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.c_2086_l()).resultOrPartial(arg_0 -> ((Logger)D_4792_h).error(arg_0)).ifPresent(p_234343_1_ -> compound.n_1700_B("VillagerData", (Tag)p_234343_1_));
        if (this.M_182_A != null) {
            compound.n_1700_B("Offers", this.M_182_A);
        }
        if (this.Q_4569_t != null) {
            compound.n_1700_B("Gossips", this.Q_4569_t);
        }
        compound.J_1907_R("ConversionTime", this.U_3758_B() ? this.R_4764_Y : -1);
        if (this.h_1847_R != null) {
            compound.n_1700_B("ConversionPlayer", this.h_1847_R);
        }
        compound.J_1907_R("Xp", this.t_1786_h);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("VillagerData", 10)) {
            DataResult dataresult = VillagerData.n_1700_B.parse(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compound.R_4764_Y("VillagerData")));
            dataresult.resultOrPartial(arg_0 -> ((Logger)D_4792_h).error(arg_0)).ifPresent(this::n_1700_B);
        }
        if (compound.R_4764_Y("Offers", 10)) {
            this.M_182_A = compound.M_182_A("Offers");
        }
        if (compound.R_4764_Y("Gossips", 10)) {
            this.Q_4569_t = compound.G_564_y("Gossips", 10);
        }
        if (compound.R_4764_Y("ConversionTime", 99) && compound.w_1484_f("ConversionTime") > -1) {
            this.n_1700_B(compound.J_1907_R("ConversionPlayer") ? compound.n_1700_B("ConversionPlayer") : null, compound.w_1484_f("ConversionTime"));
        }
        if (compound.R_4764_Y("Xp", 3)) {
            this.t_1786_h = compound.w_1484_f("Xp");
        }
    }

    @Override
    public void v_() {
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && this.U_3758_B()) {
            int i = this.y_3417_N();
            this.R_4764_Y -= i;
            if (this.R_4764_Y <= 0) {
                this.R_4764_Y((e_3591_l)this.O_508_d);
            }
        }
        super.v_();
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.p_863_D) {
            if (this.J_1907_R(MobEffects.multiplayerClientSuggestionProvider)) {
                if (!p_230254_1_.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
                if (!this.O_508_d.Y_259_p) {
                    this.n_1700_B(p_230254_1_.w_2705_t(), this.RealmsWorldOptions.nextInt(2401) + 3600);
                }
                return m_3054_I.n_1700_B;
            }
            return m_3054_I.J_1907_R;
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    protected boolean J_3635_s() {
        return false;
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.U_3758_B() && this.t_1786_h == 0;
    }

    public boolean U_3758_B() {
        return this.D_60_a().n_1700_B(n_1700_B);
    }

    private void n_1700_B(@Nullable UUID conversionStarterIn, int conversionTimeIn) {
        this.h_1847_R = conversionStarterIn;
        this.R_4764_Y = conversionTimeIn;
        this.D_60_a().J_1907_R(n_1700_B, true);
        this.G_564_y(MobEffects.multiplayerClientSuggestionProvider);
        this.n_1700_B(new k_2610_C(MobEffects.P_1922_E, conversionTimeIn, Math.min(this.O_508_d.x_607_J().n_1700_B() - 1, 0)));
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)16);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 16) {
            if (!this.y_1700_S()) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k(), SoundEvents.WoodType, this.r_2478_U(), 1.0f + this.RealmsWorldOptions.nextFloat(), this.RealmsWorldOptions.nextFloat() * 0.7f + 0.3f, false);
            }
        } else {
            super.n_1700_B(id);
        }
    }

    private void R_4764_Y(e_3591_l p_213791_1_) {
        a_3913_L playerentity;
        L_2225_p villagerentity = this.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, false);
        for (e_1174_E equipmentslottype : e_1174_E.values()) {
            Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
            if (itemstack.n_1700_B()) continue;
            if (K_4096_w.G_564_y(itemstack)) {
                villagerentity.n_1700_B(equipmentslottype.J_1907_R() + 300, itemstack);
                continue;
            }
            double d0 = this.P_1922_E(equipmentslottype);
            if (!(d0 > 1.0)) continue;
            this.a_(itemstack);
        }
        villagerentity.n_1700_B(this.c_2086_l());
        if (this.Q_4569_t != null) {
            villagerentity.n_1700_B(this.Q_4569_t);
        }
        if (this.M_182_A != null) {
            villagerentity.J_1907_R(new MerchantOffers(this.M_182_A));
        }
        villagerentity.Y_601_j(this.t_1786_h);
        villagerentity.n_1700_B(p_213791_1_, p_213791_1_.J_1907_R(villagerentity.b_2312_j()), a_3160_D.t_148_a, (V_3157_k)null, null);
        if (this.h_1847_R != null && (playerentity = p_213791_1_.n_1700_B(this.h_1847_R)) instanceof B_4088_l) {
            U_3554_Q.multiplayerClientSuggestionProvider.n_1700_B((B_4088_l)playerentity, this, villagerentity);
            p_213791_1_.n_1700_B(ReputationEventType.n_1700_B, (N_4263_v)playerentity, villagerentity);
        }
        villagerentity.n_1700_B(new k_2610_C(MobEffects.t_148_a, 200, 0));
        if (!this.y_1700_S()) {
            p_213791_1_.n_1700_B((a_3913_L)null, 1027, this.b_2312_j(), 0);
        }
    }

    private int y_3417_N() {
        int i = 1;
        if (this.RealmsWorldOptions.nextFloat() < 0.01f) {
            int j = 0;
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int k = (int)this.O_3598_v() - 4; k < (int)this.O_3598_v() + 4 && j < 14; ++k) {
                for (int l = (int)this.X_2960_b() - 4; l < (int)this.X_2960_b() + 4 && j < 14; ++l) {
                    for (int i1 = (int)this.l_2647_k() - 4; i1 < (int)this.l_2647_k() + 4 && j < 14; ++i1) {
                        T_2915_h block = this.O_508_d.getBlockState(blockpos$mutable.n_1700_B(k, l, i1)).J_1907_R();
                        if (block != a_3742_W.Z_4720_K && !(block instanceof J_2868_p)) continue;
                        if (this.RealmsWorldOptions.nextFloat() < 0.3f) {
                            ++i;
                        }
                        ++j;
                    }
                }
            }
        }
        return i;
    }

    @Override
    protected float O_2761_o() {
        return this.d_() ? (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 2.0f : (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f;
    }

    @Override
    public SoundEvent z_4693_k() {
        return SoundEvents.WitherWallSkullBlock;
    }

    @Override
    public SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.MaterialColor;
    }

    @Override
    public SoundEvent u_796_y() {
        return SoundEvents.Material;
    }

    @Override
    public SoundEvent V_1176_p() {
        return SoundEvents.w_1454_v;
    }

    @Override
    protected Z_1993_T y_2447_C() {
        return Z_1993_T.J_1907_R;
    }

    public void v_4262_N(U_2912_j p_213790_1_) {
        this.M_182_A = p_213790_1_;
    }

    public void n_1700_B(Tag p_223727_1_) {
        this.Q_4569_t = p_223727_1_;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(this.c_2086_l().n_1700_B(R_3043_n.n_1700_B(worldIn.n_1700_B(this.b_2312_j()))));
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void n_1700_B(VillagerData p_213792_1_) {
        VillagerData villagerdata = this.c_2086_l();
        if (villagerdata.J_1907_R() != p_213792_1_.J_1907_R()) {
            this.M_182_A = null;
        }
        this.l_4537_E.J_1907_R(J_1907_R, p_213792_1_);
    }

    @Override
    public VillagerData c_2086_l() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    public void n_1700_B(int p_213789_1_) {
        this.t_1786_h = p_213789_1_;
    }
}


