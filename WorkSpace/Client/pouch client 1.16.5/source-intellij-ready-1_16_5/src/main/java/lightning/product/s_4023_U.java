/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.tuple.Pair
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.ItemUtils;
import lightning.product.K_4074_S;
import lightning.product.SuspiciousStewItem;
import lightning.product.ItemTags;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Cow;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Shearable;
import lightning.product.FlowerBlock;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_422_i;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.LightningBolt;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import org.apache.commons.lang3.tuple.Pair;

public class s_4023_U
extends Cow
implements Shearable {
    private static final h_256_u<String> h_1847_R = C_4114_x.n_1700_B(s_4023_U.class, EntityDataSerializers.G_564_y);
    private g_422_i Q_4569_t;
    private int M_182_A;
    private UUID t_1786_h;

    public s_4023_U(t_5_h<? extends s_4023_U> type, b_4507_u worldIn) {
        super((t_5_h<? extends Cow>)type, worldIn);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return worldIn.getBlockState(pos.down()).n_1700_B(a_3742_W.A_2714_y) ? 10.0f : worldIn.w_1484_f(pos) - 0.5f;
    }

    public static boolean J_1907_R(t_5_h<s_4023_U> p_223318_0_, LevelAccessor p_223318_1_, a_3160_D p_223318_2_, c_1514_x p_223318_3_, Random p_223318_4_) {
        return p_223318_1_.getBlockState(p_223318_3_.down()).n_1700_B(a_3742_W.A_2714_y) && p_223318_1_.n_1700_B(p_223318_3_, 0) > 8;
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        UUID uuid = p_241841_2_.w_2705_t();
        if (!uuid.equals(this.t_1786_h)) {
            this.n_1700_B(this.h_1640_b() == lightning.product.s_4023_U$n_1700_B.n_1700_B ? lightning.product.s_4023_U$n_1700_B.J_1907_R : lightning.product.s_4023_U$n_1700_B.n_1700_B);
            this.t_1786_h = uuid;
            this.n_1700_B(SoundEvents.ElytraMotion, 2.0f, 1.0f);
        }
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, lightning.product.s_4023_U$n_1700_B.n_1700_B.R_4764_Y);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.S_4088_D && !this.d_()) {
            Z_1993_T itemstack1;
            boolean flag = false;
            if (this.Q_4569_t != null) {
                flag = true;
                itemstack1 = new Z_1993_T(Items.Q_2342_H);
                SuspiciousStewItem.n_1700_B(itemstack1, this.Q_4569_t, this.M_182_A);
                this.Q_4569_t = null;
                this.M_182_A = 0;
            } else {
                itemstack1 = new Z_1993_T(Items.MinecraftAccess);
            }
            Z_1993_T itemstack2 = ItemUtils.n_1700_B(itemstack, p_230254_1_, itemstack1, false);
            p_230254_1_.n_1700_B(p_230254_2_, itemstack2);
            SoundEvent soundevent = flag ? SoundEvents.c_892_d : SoundEvents.Flight;
            this.n_1700_B(soundevent, 1.0f, 1.0f);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (itemstack.J_1907_R() == Items.LightPredicate && this.n_1700_B()) {
            this.n_1700_B(D_38_f.w_1484_f);
            if (!this.O_508_d.Y_259_p) {
                itemstack.n_1700_B(1, p_230254_1_, (T p_213442_1_) -> p_213442_1_.G_564_y(p_230254_2_));
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (this.h_1640_b() == lightning.product.s_4023_U$n_1700_B.J_1907_R && itemstack.J_1907_R().n_1700_B(ItemTags.d_2427_y)) {
            if (this.Q_4569_t != null) {
                for (int i = 0; i < 2; ++i) {
                    this.O_508_d.n_1700_B(ParticleTypes.B_1668_F, this.O_3598_v() + this.RealmsWorldOptions.nextDouble() / 2.0, this.P_1922_E(0.5), this.l_2647_k() + this.RealmsWorldOptions.nextDouble() / 2.0, 0.0, this.RealmsWorldOptions.nextDouble() / 5.0, 0.0);
                }
            } else {
                Optional<Pair<g_422_i, Integer>> optional = this.M_588_G(itemstack);
                if (!optional.isPresent()) {
                    return m_3054_I.R_4764_Y;
                }
                Pair<g_422_i, Integer> pair = optional.get();
                if (!p_230254_1_.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
                for (int j = 0; j < 4; ++j) {
                    this.O_508_d.n_1700_B(ParticleTypes.M_182_A, this.O_3598_v() + this.RealmsWorldOptions.nextDouble() / 2.0, this.P_1922_E(0.5), this.l_2647_k() + this.RealmsWorldOptions.nextDouble() / 2.0, 0.0, this.RealmsWorldOptions.nextDouble() / 5.0, 0.0);
                }
                this.Q_4569_t = (g_422_i)pair.getLeft();
                this.M_182_A = (Integer)pair.getRight();
                this.n_1700_B(SoundEvents.F_3698_k, 2.0f, 1.0f);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    public void n_1700_B(D_38_f category) {
        this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.GuiMove, category, 1.0f, 1.0f);
        if (!this.O_508_d.v_4276_D()) {
            ((e_3591_l)this.O_508_d).n_1700_B(ParticleTypes.C_2741_M, this.O_3598_v(), this.P_1922_E(0.5), this.l_2647_k(), 1, 0.0, 0.0, 0.0, 0.0);
            this.Ops();
            Cow cowentity = t_5_h.M_588_G.n_1700_B(this.O_508_d);
            cowentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
            cowentity.t_1786_h(this.g_46_E());
            cowentity.C_1162_e = this.C_1162_e;
            if (this.t_3452_g()) {
                cowentity.n_1700_B(this.k_2302_P());
                cowentity.M_182_A(this.V_118_c());
            }
            if (this.s_2632_s()) {
                cowentity.T_3594_S();
            }
            cowentity.Q_4569_t(this.P_925_e());
            this.O_508_d.a_(cowentity);
            for (int i = 0; i < 5; ++i) {
                this.O_508_d.a_(new n_1494_c(this.O_508_d, this.O_3598_v(), this.P_1922_E(1.0), this.l_2647_k(), new Z_1993_T(this.h_1640_b().G_564_y.J_1907_R())));
            }
        }
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && !this.d_();
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Type", this.h_1640_b().R_4764_Y);
        if (this.Q_4569_t != null) {
            compound.n_1700_B("EffectId", (byte)g_422_i.n_1700_B(this.Q_4569_t));
            compound.J_1907_R("EffectDuration", this.M_182_A);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B(lightning.product.s_4023_U$n_1700_B.n_1700_B(compound.M_588_G("Type")));
        if (compound.R_4764_Y("EffectId", 1)) {
            this.Q_4569_t = g_422_i.n_1700_B(compound.u_1723_Y("EffectId"));
        }
        if (compound.R_4764_Y("EffectDuration", 3)) {
            this.M_182_A = compound.w_1484_f("EffectDuration");
        }
    }

    private Optional<Pair<g_422_i, Integer>> M_588_G(Z_1993_T p_213443_1_) {
        T_2915_h block;
        q_1613_l item = p_213443_1_.J_1907_R();
        if (item instanceof v_1669_V && (block = ((v_1669_V)item).v_4262_N()) instanceof FlowerBlock) {
            FlowerBlock flowerblock = (FlowerBlock)block;
            return Optional.of(Pair.of((Object)flowerblock.J_1907_R(), (Object)flowerblock.t_148_a()));
        }
        return Optional.empty();
    }

    private void n_1700_B(n_1700_B typeIn) {
        this.l_4537_E.J_1907_R(h_1847_R, typeIn.R_4764_Y);
    }

    public n_1700_B h_1640_b() {
        return lightning.product.s_4023_U$n_1700_B.n_1700_B(this.l_4537_E.n_1700_B(h_1847_R));
    }

    public s_4023_U R_4764_Y(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        s_4023_U mooshroomentity = t_5_h.D_4792_h.n_1700_B(p_241840_1_);
        mooshroomentity.n_1700_B(this.n_1700_B((s_4023_U)p_241840_2_));
        return mooshroomentity;
    }

    private n_1700_B n_1700_B(s_4023_U p_213445_1_) {
        n_1700_B mooshroomentity$type1;
        n_1700_B mooshroomentity$type = this.h_1640_b();
        n_1700_B mooshroomentity$type2 = mooshroomentity$type == (mooshroomentity$type1 = p_213445_1_.h_1640_b()) && this.RealmsWorldOptions.nextInt(1024) == 0 ? (mooshroomentity$type == lightning.product.s_4023_U$n_1700_B.J_1907_R ? lightning.product.s_4023_U$n_1700_B.n_1700_B : lightning.product.s_4023_U$n_1700_B.J_1907_R) : (this.RealmsWorldOptions.nextBoolean() ? mooshroomentity$type : mooshroomentity$type1);
        return mooshroomentity$type2;
    }

    @Override
    public /* synthetic */ Cow J_1907_R(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.R_4764_Y(e_3591_l2, c_893_i);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.R_4764_Y(e_3591_l2, c_893_i);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("red", a_3742_W.RealmsPersistence.multiplayerClientSuggestionProvider());
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("brown", a_3742_W.JsonUtils.multiplayerClientSuggestionProvider());
        private final String R_4764_Y;
        private final K_4074_S G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn, K_4074_S renderStateIn) {
            this.R_4764_Y = nameIn;
            this.G_564_y = renderStateIn;
        }

        public K_4074_S n_1700_B() {
            return this.G_564_y;
        }

        private static n_1700_B n_1700_B(String nameIn) {
            for (n_1700_B mooshroomentity$type : lightning.product.s_4023_U$n_1700_B.values()) {
                if (!mooshroomentity$type.R_4764_Y.equals(nameIn)) continue;
                return mooshroomentity$type;
            }
            return n_1700_B;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.s_4023_U$n_1700_B.J_1907_R();
        }
    }
}



