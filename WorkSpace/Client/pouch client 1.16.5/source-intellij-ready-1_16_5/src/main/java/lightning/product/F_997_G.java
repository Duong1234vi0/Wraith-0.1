/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.DebugPackets;
import lightning.product.C_4998_y;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.M_4472_P;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.X_1924_A;
import lightning.product.a_3913_L;
import lightning.product.b_1913_J;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.BlockTags;
import lightning.product.EntityTypeTags;
import lightning.product.q_2896_o;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.v_1577_d;

public class F_997_G
extends i_2154_H
implements X_1924_A {
    private final List<n_1700_B> n_1700_B = Lists.newArrayList();
    @Nullable
    private c_1514_x J_1907_R = null;

    public F_997_G() {
        super(BlockEntityType.e_4240_b);
    }

    @Override
    public void J_1907_R() {
        if (this.n_1700_B()) {
            this.n_1700_B(null, this.u_2550_I.getBlockState(this.x_607_J()), lightning.product.F_997_G$J_1907_R.R_4764_Y);
        }
        super.J_1907_R();
    }

    public boolean n_1700_B() {
        if (this.u_2550_I == null) {
            return false;
        }
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(this.M_588_G.add(-1, -1, -1), this.M_588_G.add(1, 1, 1))) {
            if (!(this.u_2550_I.getBlockState(blockpos).J_1907_R() instanceof M_4472_P)) continue;
            return true;
        }
        return false;
    }

    public boolean v_4262_N() {
        return this.n_1700_B.isEmpty();
    }

    public boolean w_1484_f() {
        return this.n_1700_B.size() == 3;
    }

    public void n_1700_B(@Nullable a_3913_L p_226963_1_, K_4074_S p_226963_2_, J_1907_R p_226963_3_) {
        List<N_4263_v> list = this.n_1700_B(p_226963_2_, p_226963_3_);
        if (p_226963_1_ != null) {
            for (N_4263_v entity : list) {
                if (!(entity instanceof b_1913_J)) continue;
                b_1913_J beeentity = (b_1913_J)entity;
                if (!(p_226963_1_.s_4990_V().v_4262_N(entity.s_4990_V()) <= 16.0)) continue;
                if (!this.u_2550_I()) {
                    beeentity.R_4764_Y((r_4811_B)p_226963_1_);
                    continue;
                }
                beeentity.Y_601_j(400);
            }
        }
    }

    private List<N_4263_v> n_1700_B(K_4074_S p_226965_1_, J_1907_R p_226965_2_) {
        ArrayList list = Lists.newArrayList();
        this.n_1700_B.removeIf(p_226966_4_ -> this.n_1700_B(p_226965_1_, (n_1700_B)p_226966_4_, list, p_226965_2_));
        return list;
    }

    public void n_1700_B(N_4263_v p_226961_1_, boolean p_226961_2_) {
        this.n_1700_B(p_226961_1_, p_226961_2_, 0);
    }

    public int s_956_w() {
        return this.n_1700_B.size();
    }

    public static int n_1700_B(K_4074_S p_226964_0_) {
        return p_226964_0_.R_4764_Y(v_1577_d.h_1847_R);
    }

    public boolean u_2550_I() {
        return C_4998_y.n_1700_B(this.u_2550_I, this.x_607_J());
    }

    protected void M_588_G() {
        DebugPackets.n_1700_B(this);
    }

    public void n_1700_B(N_4263_v p_226962_1_, boolean p_226962_2_, int p_226962_3_) {
        if (this.n_1700_B.size() < 3) {
            p_226962_1_.A_3959_N();
            p_226962_1_.C_3538_G();
            U_2912_j compoundnbt = new U_2912_j();
            p_226962_1_.G_564_y(compoundnbt);
            this.n_1700_B.add(new n_1700_B(compoundnbt, p_226962_3_, p_226962_2_ ? 2400 : 600));
            if (this.u_2550_I != null) {
                b_1913_J beeentity;
                if (p_226962_1_ instanceof b_1913_J && (beeentity = (b_1913_J)p_226962_1_).h_1640_b() && (!this.h_1847_R() || this.u_2550_I.w_1457_N.nextBoolean())) {
                    this.J_1907_R = beeentity.y_4642_Y();
                }
                c_1514_x blockpos = this.x_607_J();
                this.u_2550_I.n_1700_B((a_3913_L)null, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), SoundEvents.RealmsWorldOptions, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
            p_226962_1_.Ops();
        }
    }

    private boolean n_1700_B(K_4074_S p_235651_1_, n_1700_B p_235651_2_, @Nullable List<N_4263_v> p_235651_3_, J_1907_R p_235651_4_) {
        boolean flag;
        if ((this.u_2550_I.z_4693_k() || this.u_2550_I.c_4037_x()) && p_235651_4_ != lightning.product.F_997_G$J_1907_R.R_4764_Y) {
            return false;
        }
        c_1514_x blockpos = this.x_607_J();
        U_2912_j compoundnbt = p_235651_2_.n_1700_B;
        compoundnbt.multiplayerClientSuggestionProvider("Passengers");
        compoundnbt.multiplayerClientSuggestionProvider("Leash");
        compoundnbt.multiplayerClientSuggestionProvider("UUID");
        b_257_Y direction = p_235651_1_.R_4764_Y(v_1577_d.P_4830_p);
        c_1514_x blockpos1 = blockpos.offset(direction);
        boolean bl = flag = !this.u_2550_I.getBlockState(blockpos1).u_2550_I(this.u_2550_I, blockpos1).J_1907_R();
        if (flag && p_235651_4_ != lightning.product.F_997_G$J_1907_R.R_4764_Y) {
            return false;
        }
        N_4263_v entity = t_5_h.n_1700_B(compoundnbt, this.u_2550_I, p_226960_0_ -> p_226960_0_);
        if (entity != null) {
            if (!entity.f_4016_n().n_1700_B(EntityTypeTags.G_564_y)) {
                return false;
            }
            if (entity instanceof b_1913_J) {
                b_1913_J beeentity = (b_1913_J)entity;
                if (this.h_1847_R() && !beeentity.h_1640_b() && this.u_2550_I.w_1457_N.nextFloat() < 0.9f) {
                    beeentity.v_4262_N(this.J_1907_R);
                }
                if (p_235651_4_ == lightning.product.F_997_G$J_1907_R.n_1700_B) {
                    int i;
                    beeentity.U_3758_B();
                    if (p_235651_1_.J_1907_R().n_1700_B(BlockTags.Ping) && (i = F_997_G.n_1700_B(p_235651_1_)) < 5) {
                        int j;
                        int n = j = this.u_2550_I.w_1457_N.nextInt(100) == 0 ? 2 : 1;
                        if (i + j > 5) {
                            --j;
                        }
                        this.u_2550_I.J_1907_R(this.x_607_J(), (K_4074_S)p_235651_1_.n_1700_B(v_1577_d.h_1847_R, i + j));
                    }
                }
                this.n_1700_B(p_235651_2_.J_1907_R, beeentity);
                if (p_235651_3_ != null) {
                    p_235651_3_.add(beeentity);
                }
                float f = entity.C_415_h();
                double d3 = flag ? 0.0 : 0.55 + (double)(f / 2.0f);
                double d0 = (double)blockpos.getX() + 0.5 + d3 * (double)direction.t_148_a();
                double d1 = (double)blockpos.getY() + 0.5 - (double)(entity.v_165_F() / 2.0f);
                double d2 = (double)blockpos.getZ() + 0.5 + d3 * (double)direction.u_2550_I();
                entity.J_1907_R(d0, d1, d2, entity.p_178_J, entity.f_4016_n);
            }
            this.u_2550_I.n_1700_B((a_3913_L)null, blockpos, SoundEvents.RealmsWorldResetDto, D_38_f.P_1922_E, 1.0f, 1.0f);
            return this.u_2550_I.a_(entity);
        }
        return false;
    }

    private void n_1700_B(int p_235650_1_, b_1913_J p_235650_2_) {
        int i = p_235650_2_.x_();
        if (i < 0) {
            p_235650_2_.b_(Math.min(0, i + p_235650_1_));
        } else if (i > 0) {
            p_235650_2_.b_(Math.max(0, i - p_235650_1_));
        }
        p_235650_2_.w_1457_N(Math.max(0, p_235650_2_.h_973_D() - p_235650_1_));
        p_235650_2_.V_1176_p();
    }

    private boolean h_1847_R() {
        return this.J_1907_R != null;
    }

    private void Q_4569_t() {
        Iterator<n_1700_B> iterator = this.n_1700_B.iterator();
        K_4074_S blockstate = this.e_4240_b();
        while (iterator.hasNext()) {
            n_1700_B beehivetileentity$bee = iterator.next();
            if (beehivetileentity$bee.J_1907_R > beehivetileentity$bee.R_4764_Y) {
                J_1907_R beehivetileentity$state;
                J_1907_R j_1907_R = beehivetileentity$state = beehivetileentity$bee.n_1700_B.t_1786_h("HasNectar") ? lightning.product.F_997_G$J_1907_R.n_1700_B : lightning.product.F_997_G$J_1907_R.J_1907_R;
                if (this.n_1700_B(blockstate, beehivetileentity$bee, (List<N_4263_v>)null, beehivetileentity$state)) {
                    iterator.remove();
                }
            }
            ++beehivetileentity$bee.J_1907_R;
        }
    }

    @Override
    public void P_1922_E() {
        if (!this.u_2550_I.Y_259_p) {
            this.Q_4569_t();
            c_1514_x blockpos = this.x_607_J();
            if (this.n_1700_B.size() > 0 && this.u_2550_I.e_4240_b().nextDouble() < 0.005) {
                double d0 = (double)blockpos.getX() + 0.5;
                double d1 = blockpos.getY();
                double d2 = (double)blockpos.getZ() + 0.5;
                this.u_2550_I.n_1700_B((a_3913_L)null, d0, d1, d2, SoundEvents.H_1083_k, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
            this.M_588_G();
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B.clear();
        q_2896_o listnbt = nbt.G_564_y("Bees", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            n_1700_B beehivetileentity$bee = new n_1700_B(compoundnbt.M_182_A("EntityData"), compoundnbt.w_1484_f("TicksInHive"), compoundnbt.w_1484_f("MinOccupationTicks"));
            this.n_1700_B.add(beehivetileentity$bee);
        }
        this.J_1907_R = null;
        if (nbt.P_1922_E("FlowerPos")) {
            this.J_1907_R = n_3832_I.J_1907_R(nbt.M_182_A("FlowerPos"));
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("Bees", this.P_4830_p());
        if (this.h_1847_R()) {
            compound.n_1700_B("FlowerPos", n_3832_I.n_1700_B(this.J_1907_R));
        }
        return compound;
    }

    public q_2896_o P_4830_p() {
        q_2896_o listnbt = new q_2896_o();
        for (n_1700_B beehivetileentity$bee : this.n_1700_B) {
            beehivetileentity$bee.n_1700_B.multiplayerClientSuggestionProvider("UUID");
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("EntityData", beehivetileentity$bee.n_1700_B);
            compoundnbt.J_1907_R("TicksInHive", beehivetileentity$bee.J_1907_R);
            compoundnbt.J_1907_R("MinOccupationTicks", beehivetileentity$bee.R_4764_Y);
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.F_997_G$J_1907_R.n_1700_B();
        }
    }

    static class n_1700_B {
        private final U_2912_j n_1700_B;
        private int J_1907_R;
        private final int R_4764_Y;

        private n_1700_B(U_2912_j nbt, int ticksInHive, int minOccupationTicks) {
            nbt.multiplayerClientSuggestionProvider("UUID");
            this.n_1700_B = nbt;
            this.J_1907_R = ticksInHive;
            this.R_4764_Y = minOccupationTicks;
        }
    }
}


