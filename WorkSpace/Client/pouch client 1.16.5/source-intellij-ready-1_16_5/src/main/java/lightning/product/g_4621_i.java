/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.HashSet;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.N_1216_z;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.ParticleOptions;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.Merchant;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.MerchantOffers;
import lightning.product.EntityDataSerializers;
import lightning.product.VillagerTrades;
import lightning.product.r_1780_L;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.MerchantOffer;

public abstract class g_4621_i
extends AgableMob
implements Merchant,
r_1780_L {
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(g_4621_i.class, EntityDataSerializers.J_1907_R);
    @Nullable
    private a_3913_L M_182_A;
    @Nullable
    protected MerchantOffers h_1847_R;
    private final N_1216_z t_1786_h = new N_1216_z(8);

    public g_4621_i(t_5_h<? extends g_4621_i> type, b_4507_u worldIn) {
        super((t_5_h<? extends AgableMob>)type, worldIn);
        this.n_1700_B(I_1869_h.M_588_G, 16.0f);
        this.n_1700_B(I_1869_h.P_4830_p, -1.0f);
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(false);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public int y_4642_Y() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    public void w_1457_N(int ticks) {
        this.l_4537_E.J_1907_R(Q_4569_t, ticks);
    }

    @Override
    public int G_564_y() {
        return 0;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? 0.81f : 1.62f;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, 0);
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player) {
        this.M_182_A = player;
    }

    @Override
    @Nullable
    public a_3913_L n_1700_B() {
        return this.M_182_A;
    }

    public boolean h_1640_b() {
        return this.M_182_A != null;
    }

    @Override
    public MerchantOffers J_1907_R() {
        if (this.h_1847_R == null) {
            this.h_1847_R = new MerchantOffers();
            this.o_82_k();
        }
        return this.h_1847_R;
    }

    @Override
    public void n_1700_B(@Nullable MerchantOffers offers) {
    }

    @Override
    public void n_1700_B(int xpIn) {
    }

    @Override
    public void n_1700_B(MerchantOffer offer) {
        offer.s_956_w();
        this.G_564_y = -this.v_4276_D();
        this.J_1907_R(offer);
        if (this.M_182_A instanceof B_4088_l) {
            U_3554_Q.w_1457_N.n_1700_B((B_4088_l)this.M_182_A, this, offer.G_564_y());
        }
    }

    protected abstract void J_1907_R(MerchantOffer var1);

    @Override
    public boolean P_1922_E() {
        return true;
    }

    @Override
    public void n_1700_B(Z_1993_T stack) {
        if (!this.O_508_d.Y_259_p && this.G_564_y > -this.v_4276_D() + 20) {
            this.G_564_y = -this.v_4276_D();
            this.n_1700_B(this.w_1457_N(!stack.n_1700_B()), this.d_4500_Q(), this.O_2761_o());
        }
    }

    @Override
    public SoundEvent u_1723_Y() {
        return SoundEvents.LeavesBlock;
    }

    protected SoundEvent w_1457_N(boolean getYesSound) {
        return getYesSound ? SoundEvents.LeavesBlock : SoundEvents.C_1985_D;
    }

    public void V_1176_p() {
        this.n_1700_B(SoundEvents.JukeboxBlock, this.d_4500_Q(), this.O_2761_o());
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        MerchantOffers merchantoffers = this.J_1907_R();
        if (!merchantoffers.isEmpty()) {
            compound.n_1700_B("Offers", merchantoffers.n_1700_B());
        }
        compound.n_1700_B("Inventory", this.t_1786_h.R_4764_Y());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("Offers", 10)) {
            this.h_1847_R = new MerchantOffers(compound.M_182_A("Offers"));
        }
        this.t_1786_h.n_1700_B(compound.G_564_y("Inventory", 10));
    }

    @Override
    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        this.y_2447_C();
        return super.n_1700_B(server);
    }

    protected void y_2447_C() {
        this.n_1700_B((a_3913_L)null);
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        super.R_4764_Y(cause);
        this.y_2447_C();
    }

    protected void n_1700_B(ParticleOptions particleData) {
        for (int i = 0; i < 5; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
            this.O_508_d.n_1700_B(particleData, this.G_564_y(1.0), this.M_766_z() + 1.0, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return false;
    }

    public N_1216_z J_3635_s() {
        return this.t_1786_h;
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (super.n_1700_B(inventorySlot, itemStackIn)) {
            return true;
        }
        int i = inventorySlot - 300;
        if (i >= 0 && i < this.t_1786_h.Y_259_p()) {
            this.t_1786_h.J_1907_R(i, itemStackIn);
            return true;
        }
        return false;
    }

    @Override
    public b_4507_u R_4764_Y() {
        return this.O_508_d;
    }

    protected abstract void o_82_k();

    protected void n_1700_B(MerchantOffers givenMerchantOffers, VillagerTrades.v_4262_N[] newTrades, int maxNumbers) {
        HashSet set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while (set.size() < maxNumbers) {
                set.add(this.RealmsWorldOptions.nextInt(newTrades.length));
            }
        } else {
            for (int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }
        for (Integer integer : set) {
            VillagerTrades.v_4262_N villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.n_1700_B(this, this.RealmsWorldOptions);
            if (merchantoffer == null) continue;
            givenMerchantOffers.add(merchantoffer);
        }
    }

    @Override
    public e_2866_D P_1922_E(float partialTicks) {
        float f = u_530_F.v_4262_N(partialTicks, this.D_4361_a, this.C_1162_e) * ((float)Math.PI / 180);
        e_2866_D vector3d = new e_2866_D(0.0, this.i_601_W().getYSize() - 1.0, 0.2);
        return this.P_4830_p(partialTicks).P_1922_E(vector3d.J_1907_R(-f));
    }
}


