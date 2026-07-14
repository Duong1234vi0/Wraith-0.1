/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.Rotations;
import lightning.product.C_4114_x;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.NonNullList;
import lightning.product.R_1815_U;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.X_426_i;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.k_4231_L;
import lightning.product.m_3054_I;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.LightningBolt;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.y_4319_k;

public class D_686_b
extends r_4811_B {
    private static final Rotations t_148_a = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations s_956_w = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations u_2550_I = new Rotations(-10.0f, 0.0f, -10.0f);
    private static final Rotations M_588_G = new Rotations(-15.0f, 0.0f, 10.0f);
    private static final Rotations P_4830_p = new Rotations(-1.0f, 0.0f, -1.0f);
    private static final Rotations h_1847_R = new Rotations(1.0f, 0.0f, 1.0f);
    private static final R_1815_U Q_4569_t = new R_1815_U(0.0f, 0.0f, true);
    private static final R_1815_U M_182_A = t_5_h.J_1907_R.u_2550_I().n_1700_B(0.5f);
    public static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.n_1700_B);
    public static final h_256_u<Rotations> J_1907_R = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    public static final h_256_u<Rotations> R_4764_Y = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    public static final h_256_u<Rotations> G_564_y = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    public static final h_256_u<Rotations> P_1922_E = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    public static final h_256_u<Rotations> u_1723_Y = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    public static final h_256_u<Rotations> v_4262_N = C_4114_x.n_1700_B(D_686_b.class, EntityDataSerializers.u_2550_I);
    private static final Predicate<N_4263_v> t_1786_h = entity -> entity instanceof y_4319_k && ((y_4319_k)entity).h_1847_R() == y_4319_k.n_1700_B.n_1700_B;
    private final NonNullList<Z_1993_T> multiplayerClientSuggestionProvider = NonNullList.n_1700_B(2, Z_1993_T.J_1907_R);
    private final NonNullList<Z_1993_T> w_1457_N = NonNullList.n_1700_B(4, Z_1993_T.J_1907_R);
    private boolean Y_601_j;
    public long w_1484_f;
    private int Y_259_p;
    private Rotations Q_2552_b = t_148_a;
    private Rotations C_2741_M = s_956_w;
    private Rotations k_2293_S = u_2550_I;
    private Rotations q_2307_F = M_588_G;
    private Rotations Z_875_P = P_4830_p;
    private Rotations c_3005_b = h_1847_R;

    public D_686_b(t_5_h<? extends D_686_b> p_i50225_1_, b_4507_u world) {
        super((t_5_h<? extends r_4811_B>)p_i50225_1_, world);
        this.RealmsServerPing = 0.0f;
    }

    public D_686_b(b_4507_u worldIn, double posX, double posY, double posZ) {
        this((t_5_h<? extends D_686_b>)t_5_h.J_1907_R, worldIn);
        this.J_1907_R(posX, posY, posZ);
    }

    @Override
    public void g_() {
        double d0 = this.O_3598_v();
        double d1 = this.X_2960_b();
        double d2 = this.l_2647_k();
        super.g_();
        this.J_1907_R(d0, d1, d2);
    }

    private boolean t_4043_B() {
        return !this.Q_4569_t() && !this.u_744_e();
    }

    @Override
    public boolean w_1457_N() {
        return super.w_1457_N() && this.t_4043_B();
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
        this.l_4537_E.n_1700_B(J_1907_R, t_148_a);
        this.l_4537_E.n_1700_B(R_4764_Y, s_956_w);
        this.l_4537_E.n_1700_B(G_564_y, u_2550_I);
        this.l_4537_E.n_1700_B(P_1922_E, M_588_G);
        this.l_4537_E.n_1700_B(u_1723_Y, P_4830_p);
        this.l_4537_E.n_1700_B(v_4262_N, h_1847_R);
    }

    @Override
    public Iterable<Z_1993_T> f_3449_S() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public Iterable<Z_1993_T> u_55_V() {
        return this.w_1457_N;
    }

    @Override
    public Z_1993_T J_1907_R(e_1174_E slotIn) {
        switch (slotIn.n_1700_B()) {
            case n_1700_B: {
                return this.multiplayerClientSuggestionProvider.get(slotIn.J_1907_R());
            }
            case J_1907_R: {
                return this.w_1457_N.get(slotIn.J_1907_R());
            }
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public void n_1700_B(e_1174_E slotIn, Z_1993_T stack) {
        switch (slotIn.n_1700_B()) {
            case n_1700_B: {
                this.J_1907_R(stack);
                this.multiplayerClientSuggestionProvider.set(slotIn.J_1907_R(), stack);
                break;
            }
            case J_1907_R: {
                this.J_1907_R(stack);
                this.w_1457_N.set(slotIn.J_1907_R(), stack);
            }
        }
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        e_1174_E equipmentslottype;
        if (inventorySlot == 98) {
            equipmentslottype = e_1174_E.n_1700_B;
        } else if (inventorySlot == 99) {
            equipmentslottype = e_1174_E.J_1907_R;
        } else if (inventorySlot == 100 + e_1174_E.u_1723_Y.J_1907_R()) {
            equipmentslottype = e_1174_E.u_1723_Y;
        } else if (inventorySlot == 100 + e_1174_E.P_1922_E.J_1907_R()) {
            equipmentslottype = e_1174_E.P_1922_E;
        } else if (inventorySlot == 100 + e_1174_E.G_564_y.J_1907_R()) {
            equipmentslottype = e_1174_E.G_564_y;
        } else {
            if (inventorySlot != 100 + e_1174_E.R_4764_Y.J_1907_R()) {
                return false;
            }
            equipmentslottype = e_1174_E.R_4764_Y;
        }
        if (!itemStackIn.n_1700_B() && !Z_530_i.R_4764_Y(equipmentslottype, itemStackIn) && equipmentslottype != e_1174_E.u_1723_Y) {
            return false;
        }
        this.n_1700_B(equipmentslottype, itemStackIn);
        return true;
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        return this.J_1907_R(equipmentslottype).n_1700_B() && !this.G_564_y(equipmentslottype);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        q_2896_o listnbt = new q_2896_o();
        for (Z_1993_T itemstack : this.w_1457_N) {
            U_2912_j compoundnbt = new U_2912_j();
            if (!itemstack.n_1700_B()) {
                itemstack.J_1907_R(compoundnbt);
            }
            listnbt.add(compoundnbt);
        }
        compound.n_1700_B("ArmorItems", listnbt);
        q_2896_o listnbt1 = new q_2896_o();
        for (Z_1993_T itemstack1 : this.multiplayerClientSuggestionProvider) {
            U_2912_j compoundnbt1 = new U_2912_j();
            if (!itemstack1.n_1700_B()) {
                itemstack1.J_1907_R(compoundnbt1);
            }
            listnbt1.add(compoundnbt1);
        }
        compound.n_1700_B("HandItems", listnbt1);
        compound.n_1700_B("Invisible", this.F_3572_x());
        compound.n_1700_B("Small", this.u_1723_Y());
        compound.n_1700_B("ShowArms", this.w_1484_f());
        compound.J_1907_R("DisabledSlots", this.Y_259_p);
        compound.n_1700_B("NoBasePlate", this.h_1847_R());
        if (this.Q_4569_t()) {
            compound.n_1700_B("Marker", this.Q_4569_t());
        }
        compound.n_1700_B("Pose", this.e_4240_b());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("ArmorItems", 9)) {
            q_2896_o listnbt = compound.G_564_y("ArmorItems", 10);
            for (int i = 0; i < this.w_1457_N.size(); ++i) {
                this.w_1457_N.set(i, Z_1993_T.n_1700_B(listnbt.n_1700_B(i)));
            }
        }
        if (compound.R_4764_Y("HandItems", 9)) {
            q_2896_o listnbt1 = compound.G_564_y("HandItems", 10);
            for (int j = 0; j < this.multiplayerClientSuggestionProvider.size(); ++j) {
                this.multiplayerClientSuggestionProvider.set(j, Z_1993_T.n_1700_B(listnbt1.n_1700_B(j)));
            }
        }
        this.M_588_G(compound.t_1786_h("Invisible"));
        this.n_1700_B(compound.t_1786_h("Small"));
        this.R_4764_Y(compound.t_1786_h("ShowArms"));
        this.Y_259_p = compound.w_1484_f("DisabledSlots");
        this.G_564_y(compound.t_1786_h("NoBasePlate"));
        this.P_1922_E(compound.t_1786_h("Marker"));
        this.j_1564_a = !this.t_4043_B();
        U_2912_j compoundnbt = compound.M_182_A("Pose");
        this.v_4262_N(compoundnbt);
    }

    private void v_4262_N(U_2912_j tagCompound) {
        q_2896_o listnbt = tagCompound.G_564_y("Head", 5);
        this.n_1700_B(listnbt.isEmpty() ? t_148_a : new Rotations(listnbt));
        q_2896_o listnbt1 = tagCompound.G_564_y("Body", 5);
        this.J_1907_R(listnbt1.isEmpty() ? s_956_w : new Rotations(listnbt1));
        q_2896_o listnbt2 = tagCompound.G_564_y("LeftArm", 5);
        this.R_4764_Y(listnbt2.isEmpty() ? u_2550_I : new Rotations(listnbt2));
        q_2896_o listnbt3 = tagCompound.G_564_y("RightArm", 5);
        this.G_564_y(listnbt3.isEmpty() ? M_588_G : new Rotations(listnbt3));
        q_2896_o listnbt4 = tagCompound.G_564_y("LeftLeg", 5);
        this.P_1922_E(listnbt4.isEmpty() ? P_4830_p : new Rotations(listnbt4));
        q_2896_o listnbt5 = tagCompound.G_564_y("RightLeg", 5);
        this.u_1723_Y(listnbt5.isEmpty() ? h_1847_R : new Rotations(listnbt5));
    }

    private U_2912_j e_4240_b() {
        U_2912_j compoundnbt = new U_2912_j();
        if (!t_148_a.equals(this.Q_2552_b)) {
            compoundnbt.n_1700_B("Head", this.Q_2552_b.n_1700_B());
        }
        if (!s_956_w.equals(this.C_2741_M)) {
            compoundnbt.n_1700_B("Body", this.C_2741_M.n_1700_B());
        }
        if (!u_2550_I.equals(this.k_2293_S)) {
            compoundnbt.n_1700_B("LeftArm", this.k_2293_S.n_1700_B());
        }
        if (!M_588_G.equals(this.q_2307_F)) {
            compoundnbt.n_1700_B("RightArm", this.q_2307_F.n_1700_B());
        }
        if (!P_4830_p.equals(this.Z_875_P)) {
            compoundnbt.n_1700_B("LeftLeg", this.Z_875_P.n_1700_B());
        }
        if (!h_1847_R.equals(this.c_3005_b)) {
            compoundnbt.n_1700_B("RightLeg", this.c_3005_b.n_1700_B());
        }
        return compoundnbt;
    }

    @Override
    public boolean w_728_N() {
        return false;
    }

    @Override
    protected void Z_875_P(N_4263_v entityIn) {
    }

    @Override
    protected void F_391_H() {
        List<N_4263_v> list = this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W(), t_1786_h);
        for (int i = 0; i < list.size(); ++i) {
            N_4263_v entity = list.get(i);
            if (!(this.G_564_y(entity) <= 0.2)) continue;
            entity.P_1922_E(this);
        }
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, e_2866_D vec, x_1688_C hand) {
        Z_1993_T itemstack = player.R_4764_Y(hand);
        if (!this.Q_4569_t() && itemstack.J_1907_R() != Items.HorizontalDirectionalBlock) {
            if (player.d_2461_k()) {
                return m_3054_I.n_1700_B;
            }
            if (player.O_508_d.Y_259_p) {
                return m_3054_I.J_1907_R;
            }
            e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstack);
            if (itemstack.n_1700_B()) {
                e_1174_E equipmentslottype2;
                e_1174_E equipmentslottype1 = this.s_956_w(vec);
                e_1174_E e_1174_E2 = equipmentslottype2 = this.G_564_y(equipmentslottype1) ? equipmentslottype : equipmentslottype1;
                if (this.n_1700_B(equipmentslottype2) && this.n_1700_B(player, equipmentslottype2, itemstack, hand)) {
                    return m_3054_I.n_1700_B;
                }
            } else {
                if (this.G_564_y(equipmentslottype)) {
                    return m_3054_I.G_564_y;
                }
                if (equipmentslottype.n_1700_B() == e_1174_E.n_1700_B.n_1700_B && !this.w_1484_f()) {
                    return m_3054_I.G_564_y;
                }
                if (this.n_1700_B(player, equipmentslottype, itemstack, hand)) {
                    return m_3054_I.n_1700_B;
                }
            }
            return m_3054_I.R_4764_Y;
        }
        return m_3054_I.R_4764_Y;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private e_1174_E s_956_w(e_2866_D p_190772_1_) {
        e_1174_E equipmentslottype = e_1174_E.n_1700_B;
        boolean flag = this.u_1723_Y();
        double d0 = flag ? p_190772_1_.R_4764_Y * 2.0 : p_190772_1_.R_4764_Y;
        e_1174_E equipmentslottype1 = e_1174_E.R_4764_Y;
        if (d0 >= 0.1) {
            double d = flag ? 0.8 : 0.45;
            if (d0 < 0.1 + d && this.n_1700_B(equipmentslottype1)) {
                return e_1174_E.R_4764_Y;
            }
        }
        double d = flag ? 0.3 : 0.0;
        if (d0 >= 0.9 + d) {
            double d2 = flag ? 1.0 : 0.7;
            if (d0 < 0.9 + d2 && this.n_1700_B(e_1174_E.P_1922_E)) {
                return e_1174_E.P_1922_E;
            }
        }
        if (d0 >= 0.4) {
            double d3 = flag ? 1.0 : 0.8;
            if (d0 < 0.4 + d3 && this.n_1700_B(e_1174_E.G_564_y)) {
                return e_1174_E.G_564_y;
            }
        }
        if (d0 >= 1.6 && this.n_1700_B(e_1174_E.u_1723_Y)) {
            return e_1174_E.u_1723_Y;
        }
        if (this.n_1700_B(e_1174_E.n_1700_B)) return equipmentslottype;
        if (!this.n_1700_B(e_1174_E.J_1907_R)) return equipmentslottype;
        return e_1174_E.J_1907_R;
    }

    private boolean G_564_y(e_1174_E slotIn) {
        return (this.Y_259_p & 1 << slotIn.R_4764_Y()) != 0 || slotIn.n_1700_B() == e_1174_E.n_1700_B.n_1700_B && !this.w_1484_f();
    }

    private boolean n_1700_B(a_3913_L player, e_1174_E slot, Z_1993_T stack, x_1688_C hand) {
        Z_1993_T itemstack = this.J_1907_R(slot);
        if (!itemstack.n_1700_B() && (this.Y_259_p & 1 << slot.R_4764_Y() + 8) != 0) {
            return false;
        }
        if (itemstack.n_1700_B() && (this.Y_259_p & 1 << slot.R_4764_Y() + 16) != 0) {
            return false;
        }
        if (player.C_415_h.G_564_y && itemstack.n_1700_B() && !stack.n_1700_B()) {
            Z_1993_T itemstack2 = stack.t_148_a();
            itemstack2.P_1922_E(1);
            this.n_1700_B(slot, itemstack2);
            return true;
        }
        if (!stack.n_1700_B() && stack.t_4043_B() > 1) {
            if (!itemstack.n_1700_B()) {
                return false;
            }
            Z_1993_T itemstack1 = stack.t_148_a();
            itemstack1.P_1922_E(1);
            this.n_1700_B(slot, itemstack1);
            stack.v_4262_N(1);
            return true;
        }
        this.n_1700_B(slot, stack);
        player.n_1700_B(hand, itemstack);
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (!this.O_508_d.Y_259_p && !this.t_4219_U) {
            if (P_11_z.P_4830_p.equals(source)) {
                this.Ops();
                return false;
            }
            if (!(this.n_1700_B(source) || this.Y_601_j || this.Q_4569_t())) {
                if (source.G_564_y()) {
                    this.v_4262_N(source);
                    this.Ops();
                    return false;
                }
                if (P_11_z.n_1700_B.equals(source)) {
                    if (this.RealmsPersistence()) {
                        this.u_1723_Y(source, 0.15f);
                    } else {
                        this.P_1922_E(5);
                    }
                    return false;
                }
                if (P_11_z.R_4764_Y.equals(source) && this.g_46_E() > 0.5f) {
                    this.u_1723_Y(source, 4.0f);
                    return false;
                }
                boolean flag = source.s_956_w() instanceof h_384_L;
                boolean flag1 = flag && ((h_384_L)source.s_956_w()).Q_4569_t() > 0;
                boolean flag2 = "player".equals(source.t_1786_h());
                if (!flag2 && !flag) {
                    return false;
                }
                if (source.u_2550_I() instanceof a_3913_L && !((a_3913_L)source.u_2550_I()).C_415_h.P_1922_E) {
                    return false;
                }
                if (source.Q_2552_b()) {
                    this.d_2427_y();
                    this.n_3318_d();
                    this.Ops();
                    return flag1;
                }
                long i = this.O_508_d.X_933_l();
                if (i - this.w_1484_f > 5L && !flag) {
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)32);
                    this.w_1484_f = i;
                } else {
                    this.u_1723_Y(source);
                    this.n_3318_d();
                    this.Ops();
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 32) {
            if (this.O_508_d.Y_259_p) {
                this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.X_933_l, this.r_2478_U(), 0.3f, 1.0f, false);
                this.w_1484_f = this.O_508_d.X_933_l();
            }
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength() * 4.0;
        if (Double.isNaN(d0) || d0 == 0.0) {
            d0 = 4.0;
        }
        return distance < (d0 *= 64.0) * d0;
    }

    private void n_3318_d() {
        if (this.O_508_d instanceof e_3591_l) {
            ((e_3591_l)this.O_508_d).n_1700_B(new X_426_i(ParticleTypes.G_564_y, a_3742_W.h_1847_R.multiplayerClientSuggestionProvider()), this.O_3598_v(), this.P_1922_E(0.6666666666666666), this.l_2647_k(), 10, (double)(this.C_415_h() / 4.0f), (double)(this.v_165_F() / 4.0f), this.C_415_h() / 4.0f, 0.05);
        }
    }

    private void u_1723_Y(P_11_z source, float p_213817_2_) {
        float f = this.g_46_E();
        if ((f -= p_213817_2_) <= 0.5f) {
            this.v_4262_N(source);
            this.Ops();
        } else {
            this.t_1786_h(f);
        }
    }

    private void u_1723_Y(P_11_z source) {
        T_2915_h.n_1700_B(this.O_508_d, this.b_2312_j(), new Z_1993_T(Items.p_1168_n));
        this.v_4262_N(source);
    }

    private void v_4262_N(P_11_z source) {
        this.d_2427_y();
        this.G_564_y(source);
        for (int i = 0; i < this.multiplayerClientSuggestionProvider.size(); ++i) {
            Z_1993_T itemstack = this.multiplayerClientSuggestionProvider.get(i);
            if (itemstack.n_1700_B()) continue;
            T_2915_h.n_1700_B(this.O_508_d, this.b_2312_j().up(), itemstack);
            this.multiplayerClientSuggestionProvider.set(i, Z_1993_T.J_1907_R);
        }
        for (int j = 0; j < this.w_1457_N.size(); ++j) {
            Z_1993_T itemstack1 = this.w_1457_N.get(j);
            if (itemstack1.n_1700_B()) continue;
            T_2915_h.n_1700_B(this.O_508_d, this.b_2312_j().up(), itemstack1);
            this.w_1457_N.set(j, Z_1993_T.J_1907_R);
        }
    }

    private void d_2427_y() {
        this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.B_1668_F, this.r_2478_U(), 1.0f, 1.0f);
    }

    @Override
    protected float v_4262_N(float p_110146_1_, float p_110146_2_) {
        this.D_4361_a = this.j_276_v;
        this.C_1162_e = this.p_178_J;
        return 0.0f;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * (this.d_() ? 0.5f : 0.9f);
    }

    @Override
    public double O_2151_c() {
        return this.Q_4569_t() ? 0.0 : (double)0.1f;
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.t_4043_B()) {
            super.w_1484_f(travelVector);
        }
    }

    @Override
    public void Q_4569_t(float offset) {
        this.D_4361_a = this.j_276_v = offset;
        this.JsonUtils = this.f_3449_S = offset;
    }

    @Override
    public void h_1847_R(float rotation) {
        this.D_4361_a = this.j_276_v = rotation;
        this.JsonUtils = this.f_3449_S = rotation;
    }

    @Override
    public void v_() {
        Rotations rotations5;
        Rotations rotations4;
        Rotations rotations3;
        Rotations rotations2;
        Rotations rotations1;
        super.v_();
        Rotations rotations = this.l_4537_E.n_1700_B(J_1907_R);
        if (!this.Q_2552_b.equals(rotations)) {
            this.n_1700_B(rotations);
        }
        if (!this.C_2741_M.equals(rotations1 = this.l_4537_E.n_1700_B(R_4764_Y))) {
            this.J_1907_R(rotations1);
        }
        if (!this.k_2293_S.equals(rotations2 = this.l_4537_E.n_1700_B(G_564_y))) {
            this.R_4764_Y(rotations2);
        }
        if (!this.q_2307_F.equals(rotations3 = this.l_4537_E.n_1700_B(P_1922_E))) {
            this.G_564_y(rotations3);
        }
        if (!this.Z_875_P.equals(rotations4 = this.l_4537_E.n_1700_B(u_1723_Y))) {
            this.P_1922_E(rotations4);
        }
        if (!this.c_3005_b.equals(rotations5 = this.l_4537_E.n_1700_B(v_4262_N))) {
            this.u_1723_Y(rotations5);
        }
    }

    @Override
    protected void f_691_R() {
        this.M_588_G(this.Y_601_j);
    }

    @Override
    public void M_588_G(boolean invisible) {
        this.Y_601_j = invisible;
        super.M_588_G(invisible);
    }

    @Override
    public boolean d_() {
        return this.u_1723_Y();
    }

    @Override
    public void e_1992_r() {
        this.Ops();
    }

    @Override
    public boolean l_1268_F() {
        return this.F_3572_x();
    }

    @Override
    public w_1454_v h_() {
        return this.Q_4569_t() ? w_1454_v.G_564_y : super.h_();
    }

    private void n_1700_B(boolean small) {
        this.l_4537_E.J_1907_R(n_1700_B, this.n_1700_B(this.l_4537_E.n_1700_B(n_1700_B), 1, small));
    }

    public boolean u_1723_Y() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 1) != 0;
    }

    private void R_4764_Y(boolean showArms) {
        this.l_4537_E.J_1907_R(n_1700_B, this.n_1700_B(this.l_4537_E.n_1700_B(n_1700_B), 4, showArms));
    }

    public boolean w_1484_f() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 4) != 0;
    }

    private void G_564_y(boolean noBasePlate) {
        this.l_4537_E.J_1907_R(n_1700_B, this.n_1700_B(this.l_4537_E.n_1700_B(n_1700_B), 8, noBasePlate));
    }

    public boolean h_1847_R() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 8) != 0;
    }

    private void P_1922_E(boolean marker) {
        this.l_4537_E.J_1907_R(n_1700_B, this.n_1700_B(this.l_4537_E.n_1700_B(n_1700_B), 16, marker));
    }

    public boolean Q_4569_t() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 0x10) != 0;
    }

    private byte n_1700_B(byte p_184797_1_, int p_184797_2_, boolean p_184797_3_) {
        p_184797_1_ = p_184797_3_ ? (byte)(p_184797_1_ | p_184797_2_) : (byte)(p_184797_1_ & ~p_184797_2_);
        return p_184797_1_;
    }

    public void n_1700_B(Rotations vec) {
        this.Q_2552_b = vec;
        this.l_4537_E.J_1907_R(J_1907_R, vec);
    }

    public void J_1907_R(Rotations vec) {
        this.C_2741_M = vec;
        this.l_4537_E.J_1907_R(R_4764_Y, vec);
    }

    public void R_4764_Y(Rotations vec) {
        this.k_2293_S = vec;
        this.l_4537_E.J_1907_R(G_564_y, vec);
    }

    public void G_564_y(Rotations vec) {
        this.q_2307_F = vec;
        this.l_4537_E.J_1907_R(P_1922_E, vec);
    }

    public void P_1922_E(Rotations vec) {
        this.Z_875_P = vec;
        this.l_4537_E.J_1907_R(u_1723_Y, vec);
    }

    public void u_1723_Y(Rotations vec) {
        this.c_3005_b = vec;
        this.l_4537_E.J_1907_R(v_4262_N, vec);
    }

    public Rotations M_182_A() {
        return this.Q_2552_b;
    }

    public Rotations multiplayerClientSuggestionProvider() {
        return this.C_2741_M;
    }

    public Rotations C_2741_M() {
        return this.k_2293_S;
    }

    public Rotations k_2293_S() {
        return this.q_2307_F;
    }

    public Rotations c_3005_b() {
        return this.Z_875_P;
    }

    public Rotations A_4115_X() {
        return this.c_3005_b;
    }

    @Override
    public boolean C_290_v() {
        return super.C_290_v() && !this.Q_4569_t();
    }

    @Override
    public boolean t_1786_h(N_4263_v entityIn) {
        return entityIn instanceof a_3913_L && !this.O_508_d.n_1700_B((a_3913_L)entityIn, this.b_2312_j());
    }

    @Override
    public k_4231_L d_2169_p() {
        return k_4231_L.J_1907_R;
    }

    @Override
    protected SoundEvent M_588_G(int heightIn) {
        return SoundEvents.g_164_R;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.X_933_l;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.B_1668_F;
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
    }

    @Override
    public boolean F_1446_q() {
        return false;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (n_1700_B.equals(key)) {
            this.g_();
            this.s_2632_s = !this.Q_4569_t();
        }
        super.n_1700_B(key);
    }

    @Override
    public boolean r_4790_y() {
        return false;
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return this.multiplayerClientSuggestionProvider(this.Q_4569_t());
    }

    private R_1815_U multiplayerClientSuggestionProvider(boolean p_242330_1_) {
        if (p_242330_1_) {
            return Q_4569_t;
        }
        return this.d_() ? M_182_A : this.f_4016_n().u_2550_I();
    }

    @Override
    public e_2866_D M_588_G(float p_241842_1_) {
        if (this.Q_4569_t()) {
            I_4817_s axisalignedbb = this.multiplayerClientSuggestionProvider(false).n_1700_B(this.s_4990_V());
            c_1514_x blockpos = this.b_2312_j();
            int i = Integer.MIN_VALUE;
            for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(new c_1514_x(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ), new c_1514_x(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ))) {
                int j = Math.max(this.O_508_d.getLightFor(K_4719_o.J_1907_R, blockpos1), this.O_508_d.getLightFor(K_4719_o.n_1700_B, blockpos1));
                if (j == 15) {
                    return e_2866_D.n_1700_B(blockpos1);
                }
                if (j <= i) continue;
                i = j;
                blockpos = blockpos1.toImmutable();
            }
            return e_2866_D.n_1700_B(blockpos);
        }
        return super.M_588_G(p_241842_1_);
    }
}


