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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.AxeItem;
import lightning.product.PathNavigation;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.E_194_H;
import lightning.product.BlockGetter;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.LookControl;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.Sensing;
import lightning.product.K_4096_w;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.BowItem;
import lightning.product.P_11_z;
import lightning.product.P_2973_E;
import lightning.product.NonNullList;
import lightning.product.R_2450_T;
import lightning.product.MoveControl;
import lightning.product.R_2515_i;
import lightning.product.T_1316_M;
import lightning.product.T_2717_K;
import lightning.product.JumpControl;
import lightning.product.AbstractSkullBlock;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.DiggerItem;
import lightning.product.b_4507_u;
import lightning.product.BodyRotationControl;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.e_446_u;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.i_2099_H;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_4231_L;
import lightning.product.k_4690_i;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.Goal;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import lightning.product.x_1835_e;
import lightning.product.SpawnEggItem;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;

public abstract class Z_530_i
extends r_4811_B {
    private static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(Z_530_i.class, EntityDataSerializers.n_1700_B);
    public int G_564_y;
    protected int P_1922_E;
    protected LookControl u_1723_Y;
    protected MoveControl v_4262_N;
    protected JumpControl w_1484_f;
    private final BodyRotationControl J_1907_R;
    protected PathNavigation t_148_a;
    protected final E_194_H s_956_w;
    protected final E_194_H u_2550_I;
    private r_4811_B R_4764_Y;
    private final Sensing h_1847_R;
    private final NonNullList<Z_1993_T> Q_4569_t = NonNullList.n_1700_B(2, Z_1993_T.J_1907_R);
    protected final float[] M_588_G = new float[2];
    private final NonNullList<Z_1993_T> M_182_A = NonNullList.n_1700_B(4, Z_1993_T.J_1907_R);
    protected final float[] P_4830_p = new float[4];
    private boolean t_1786_h;
    private boolean multiplayerClientSuggestionProvider;
    private final Map<I_1869_h, Float> w_1457_N = Maps.newEnumMap(I_1869_h.class);
    private g_2336_b Y_601_j;
    private long Y_259_p;
    @Nullable
    private N_4263_v Q_2552_b;
    private int C_2741_M;
    @Nullable
    private U_2912_j k_2293_S;
    private c_1514_x q_2307_F = c_1514_x.ZERO;
    private float Z_875_P = -1.0f;

    protected Z_530_i(t_5_h<? extends Z_530_i> type, b_4507_u worldIn) {
        super((t_5_h<? extends r_4811_B>)type, worldIn);
        this.s_956_w = new E_194_H(worldIn.s_2632_s());
        this.u_2550_I = new E_194_H(worldIn.s_2632_s());
        this.u_1723_Y = new LookControl(this);
        this.v_4262_N = new MoveControl(this);
        this.w_1484_f = new JumpControl(this);
        this.J_1907_R = this.k_2293_S();
        this.t_148_a = this.J_1907_R(worldIn);
        this.h_1847_R = new Sensing(this);
        Arrays.fill(this.P_4830_p, 0.085f);
        Arrays.fill(this.M_588_G, 0.085f);
        if (worldIn != null && !worldIn.Y_259_p) {
            this.M_182_A();
        }
    }

    protected void M_182_A() {
    }

    public static s_1415_m.n_1700_B multiplayerClientSuggestionProvider() {
        return r_4811_B.P_4639_N().n_1700_B(Attributes.J_1907_R, 16.0).n_1700_B(Attributes.v_4262_N);
    }

    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new i_2099_H(this, worldIn);
    }

    protected boolean C_2741_M() {
        return false;
    }

    public float n_1700_B(I_1869_h nodeType) {
        Z_530_i mobentity = this.l_3609_d() instanceof Z_530_i && ((Z_530_i)this.l_3609_d()).C_2741_M() ? (Z_530_i)this.l_3609_d() : this;
        Float f = mobentity.w_1457_N.get((Object)nodeType);
        return f == null ? nodeType.n_1700_B() : f.floatValue();
    }

    public void n_1700_B(I_1869_h nodeType, float priority) {
        this.w_1457_N.put(nodeType, Float.valueOf(priority));
    }

    public boolean J_1907_R(I_1869_h p_233660_1_) {
        return p_233660_1_ != I_1869_h.M_588_G && p_233660_1_ != I_1869_h.h_1847_R && p_233660_1_ != I_1869_h.M_182_A && p_233660_1_ != I_1869_h.G_564_y;
    }

    protected BodyRotationControl k_2293_S() {
        return new BodyRotationControl(this);
    }

    public LookControl c_3005_b() {
        return this.u_1723_Y;
    }

    public MoveControl A_4115_X() {
        if (this.y_2772_m() && this.l_3609_d() instanceof Z_530_i) {
            Z_530_i mobentity = (Z_530_i)this.l_3609_d();
            return mobentity.A_4115_X();
        }
        return this.v_4262_N;
    }

    public JumpControl t_4043_B() {
        return this.w_1484_f;
    }

    public PathNavigation e_4240_b() {
        if (this.y_2772_m() && this.l_3609_d() instanceof Z_530_i) {
            Z_530_i mobentity = (Z_530_i)this.l_3609_d();
            return mobentity.e_4240_b();
        }
        return this.t_148_a;
    }

    public Sensing n_3318_d() {
        return this.h_1847_R;
    }

    @Nullable
    public r_4811_B t_148_a() {
        return this.R_4764_Y;
    }

    public void R_4764_Y(@Nullable r_4811_B entitylivingbaseIn) {
        this.R_4764_Y = entitylivingbaseIn;
        Reflector.callVoid(Reflector.ForgeHooks_onLivingSetAttackTarget, this, entitylivingbaseIn);
    }

    @Override
    public boolean n_1700_B(t_5_h<?> typeIn) {
        return typeIn != t_5_h.Y_1740_V;
    }

    public boolean n_1700_B(ProjectileWeaponItem p_230280_1_) {
        return false;
    }

    public void d_2427_y() {
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
    }

    public int v_4276_D() {
        return 80;
    }

    public void G_624_v() {
        SoundEvent soundevent = this.z_4693_k();
        if (soundevent != null) {
            this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
        }
    }

    @Override
    public void V_1446_Y() {
        super.V_1446_Y();
        this.O_508_d.D_4792_h().n_1700_B("mobBaseTick");
        if (this.RealmsLongRunningMcoTaskScreen() && this.RealmsWorldOptions.nextInt(1000) < this.G_564_y++) {
            this.Q_4569_t();
            this.G_624_v();
        }
        this.O_508_d.D_4792_h().R_4764_Y();
    }

    @Override
    protected void J_1907_R(P_11_z source) {
        this.Q_4569_t();
        super.J_1907_R(source);
    }

    private void Q_4569_t() {
        this.G_564_y = -this.v_4276_D();
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        if (this.P_1922_E > 0) {
            int i = this.P_1922_E;
            for (int j = 0; j < this.M_182_A.size(); ++j) {
                if (this.M_182_A.get(j).n_1700_B() || !(this.P_4830_p[j] <= 1.0f)) continue;
                i += 1 + this.RealmsWorldOptions.nextInt(3);
            }
            for (int k = 0; k < this.Q_4569_t.size(); ++k) {
                if (this.Q_4569_t.get(k).n_1700_B() || !(this.M_588_G[k] <= 1.0f)) continue;
                i += 1 + this.RealmsWorldOptions.nextInt(3);
            }
            return i;
        }
        return this.P_1922_E;
    }

    public void T_2506_i() {
        if (this.O_508_d.Y_259_p) {
            for (int i = 0; i < 20; ++i) {
                double d0 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d1 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d2 = this.RealmsWorldOptions.nextGaussian() * 0.02;
                double d3 = 10.0;
                this.O_508_d.n_1700_B(ParticleTypes.z_4693_k, this.R_4764_Y(1.0) - d0 * 10.0, this.M_766_z() - d1 * 10.0, this.v_4262_N(1.0) - d2 * 10.0, d0, d1, d2);
            }
        } else {
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)20);
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 20) {
            this.T_2506_i();
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public void v_() {
        if (Config.isSmoothWorld() && this.h_1640_b()) {
            this.V_1176_p();
        } else {
            super.v_();
            if (!this.O_508_d.Y_259_p) {
                this.h_1847_R();
                if (this.RealmsWorldResetDto % 5 == 0) {
                    this.q_4610_l();
                }
            }
        }
    }

    protected void q_4610_l() {
        boolean flag = !(this.n_3864_h() instanceof Z_530_i);
        boolean flag1 = !(this.l_3609_d() instanceof g_1462_f);
        this.s_956_w.n_1700_B(Goal.n_1700_B.n_1700_B, flag);
        this.s_956_w.n_1700_B(Goal.n_1700_B.R_4764_Y, flag && flag1);
        this.s_956_w.n_1700_B(Goal.n_1700_B.J_1907_R, flag);
    }

    @Override
    protected float v_4262_N(float p_110146_1_, float p_110146_2_) {
        this.J_1907_R.n_1700_B();
        return p_110146_2_;
    }

    @Nullable
    protected SoundEvent z_4693_k() {
        return null;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("CanPickUpLoot", this.D_4792_h());
        compound.n_1700_B("PersistenceRequired", this.multiplayerClientSuggestionProvider);
        q_2896_o listnbt = new q_2896_o();
        for (Z_1993_T z_1993_T : this.M_182_A) {
            U_2912_j u_2912_j = new U_2912_j();
            if (!z_1993_T.n_1700_B()) {
                z_1993_T.J_1907_R(u_2912_j);
            }
            listnbt.add(u_2912_j);
        }
        compound.n_1700_B("ArmorItems", listnbt);
        q_2896_o listnbt1 = new q_2896_o();
        for (Z_1993_T z_1993_T : this.Q_4569_t) {
            U_2912_j compoundnbt1 = new U_2912_j();
            if (!z_1993_T.n_1700_B()) {
                z_1993_T.J_1907_R(compoundnbt1);
            }
            listnbt1.add(compoundnbt1);
        }
        compound.n_1700_B("HandItems", listnbt1);
        q_2896_o q_2896_o2 = new q_2896_o();
        for (float f : this.P_4830_p) {
            q_2896_o2.add(T_2717_K.n_1700_B(f));
        }
        compound.n_1700_B("ArmorDropChances", q_2896_o2);
        q_2896_o q_2896_o3 = new q_2896_o();
        for (float f1 : this.M_588_G) {
            q_2896_o3.add(T_2717_K.n_1700_B(f1));
        }
        compound.n_1700_B("HandDropChances", q_2896_o3);
        if (this.Q_2552_b != null) {
            U_2912_j compoundnbt2 = new U_2912_j();
            if (this.Q_2552_b instanceof r_4811_B) {
                UUID uuid = this.Q_2552_b.w_2705_t();
                compoundnbt2.n_1700_B("UUID", uuid);
            } else if (this.Q_2552_b instanceof P_2973_E) {
                c_1514_x blockpos = ((P_2973_E)this.Q_2552_b).u_2550_I();
                compoundnbt2.J_1907_R("X", blockpos.getX());
                compoundnbt2.J_1907_R("Y", blockpos.getY());
                compoundnbt2.J_1907_R("Z", blockpos.getZ());
            }
            compound.n_1700_B("Leash", compoundnbt2);
        } else if (this.k_2293_S != null) {
            compound.n_1700_B("Leash", this.k_2293_S.v_4262_N());
        }
        compound.n_1700_B("LeftHanded", this.r_4414_L());
        if (this.Y_601_j != null) {
            compound.n_1700_B("DeathLootTable", this.Y_601_j.toString());
            if (this.Y_259_p != 0L) {
                compound.n_1700_B("DeathLootTableSeed", this.Y_259_p);
            }
        }
        if (this.n_473_l()) {
            compound.n_1700_B("NoAI", this.n_473_l());
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("CanPickUpLoot", 1)) {
            this.R_4764_Y(compound.t_1786_h("CanPickUpLoot"));
        }
        this.multiplayerClientSuggestionProvider = compound.t_1786_h("PersistenceRequired");
        if (compound.R_4764_Y("ArmorItems", 9)) {
            q_2896_o listnbt = compound.G_564_y("ArmorItems", 10);
            for (int i = 0; i < this.M_182_A.size(); ++i) {
                this.M_182_A.set(i, Z_1993_T.n_1700_B(listnbt.n_1700_B(i)));
            }
        }
        if (compound.R_4764_Y("HandItems", 9)) {
            q_2896_o listnbt1 = compound.G_564_y("HandItems", 10);
            for (int j = 0; j < this.Q_4569_t.size(); ++j) {
                this.Q_4569_t.set(j, Z_1993_T.n_1700_B(listnbt1.n_1700_B(j)));
            }
        }
        if (compound.R_4764_Y("ArmorDropChances", 9)) {
            q_2896_o listnbt2 = compound.G_564_y("ArmorDropChances", 5);
            for (int k = 0; k < listnbt2.size(); ++k) {
                this.P_4830_p[k] = listnbt2.w_1484_f(k);
            }
        }
        if (compound.R_4764_Y("HandDropChances", 9)) {
            q_2896_o listnbt3 = compound.G_564_y("HandDropChances", 5);
            for (int l = 0; l < listnbt3.size(); ++l) {
                this.M_588_G[l] = listnbt3.w_1484_f(l);
            }
        }
        if (compound.R_4764_Y("Leash", 10)) {
            this.k_2293_S = compound.M_182_A("Leash");
        }
        this.P_1922_E(compound.t_1786_h("LeftHanded"));
        if (compound.R_4764_Y("DeathLootTable", 8)) {
            this.Y_601_j = new g_2336_b(compound.M_588_G("DeathLootTable"));
            this.Y_259_p = compound.t_148_a("DeathLootTableSeed");
        }
        this.G_564_y(compound.t_1786_h("NoAI"));
    }

    @Override
    protected void n_1700_B(P_11_z damageSourceIn, boolean attackedRecently) {
        super.n_1700_B(damageSourceIn, attackedRecently);
        this.Y_601_j = null;
    }

    @Override
    protected q_1704_m.n_1700_B n_1700_B(boolean attackedRecently, P_11_z damageSourceIn) {
        return super.n_1700_B(attackedRecently, damageSourceIn).n_1700_B(this.Y_259_p, this.RealmsWorldOptions);
    }

    @Override
    public final g_2336_b q_3401_q() {
        return this.Y_601_j == null ? this.g_221_o() : this.Y_601_j;
    }

    protected g_2336_b g_221_o() {
        return super.q_3401_q();
    }

    public void C_2741_M(float amount) {
        this.L_4248_u = amount;
    }

    public void k_2293_S(float amount) {
        this.P_5000_x = amount;
    }

    public void q_2307_F(float amount) {
        this.L_1362_X = amount;
    }

    @Override
    public void w_1457_N(float speedIn) {
        super.w_1457_N(speedIn);
        this.C_2741_M(speedIn);
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        this.O_508_d.D_4792_h().n_1700_B("looting");
        boolean flag = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R);
        if (Reflector.ForgeEventFactory_getMobGriefingEvent.exists()) {
            flag = Reflector.callBoolean(Reflector.ForgeEventFactory_getMobGriefingEvent, this.O_508_d, this);
        }
        if (!this.O_508_d.Y_259_p && this.D_4792_h() && this.RealmsLongRunningMcoTaskScreen() && !this.TextRenderingUtils && flag) {
            for (n_1494_c itementity : this.O_508_d.n_1700_B(n_1494_c.class, this.i_601_W().grow(1.0, 0.0, 1.0))) {
                if (itementity.t_4219_U || itementity.P_1922_E().n_1700_B() || itementity.Q_4569_t() || !this.t_148_a(itementity.P_1922_E())) continue;
                this.J_1907_R(itementity);
            }
        }
        this.O_508_d.D_4792_h().R_4764_Y();
    }

    protected void J_1907_R(n_1494_c itemEntity) {
        Z_1993_T itemstack = itemEntity.P_1922_E();
        if (this.v_4262_N(itemstack)) {
            this.n_1700_B(itemEntity);
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            itemEntity.Ops();
        }
    }

    public boolean v_4262_N(Z_1993_T p_233665_1_) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(p_233665_1_);
        Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
        boolean flag = this.n_1700_B(p_233665_1_, itemstack);
        if (flag && this.w_1484_f(p_233665_1_)) {
            double d0 = this.P_1922_E(equipmentslottype);
            if (!itemstack.n_1700_B() && (double)Math.max(this.RealmsWorldOptions.nextFloat() - 0.1f, 0.0f) < d0) {
                this.a_(itemstack);
            }
            this.J_1907_R(equipmentslottype, p_233665_1_);
            this.J_1907_R(p_233665_1_);
            return true;
        }
        return false;
    }

    protected void J_1907_R(e_1174_E p_233657_1_, Z_1993_T p_233657_2_) {
        this.n_1700_B(p_233657_1_, p_233657_2_);
        this.G_564_y(p_233657_1_);
        this.multiplayerClientSuggestionProvider = true;
    }

    public void G_564_y(e_1174_E p_233663_1_) {
        switch (p_233663_1_.n_1700_B()) {
            case n_1700_B: {
                this.M_588_G[p_233663_1_.J_1907_R()] = 2.0f;
                break;
            }
            case J_1907_R: {
                this.P_4830_p[p_233663_1_.J_1907_R()] = 2.0f;
            }
        }
    }

    protected boolean n_1700_B(Z_1993_T candidate, Z_1993_T existing) {
        if (existing.n_1700_B()) {
            return true;
        }
        if (candidate.J_1907_R() instanceof SwordItem) {
            if (!(existing.J_1907_R() instanceof SwordItem)) {
                return true;
            }
            SwordItem sworditem = (SwordItem)candidate.J_1907_R();
            SwordItem sworditem1 = (SwordItem)existing.J_1907_R();
            if (sworditem.v_4262_N() != sworditem1.v_4262_N()) {
                return sworditem.v_4262_N() > sworditem1.v_4262_N();
            }
            return this.J_1907_R(candidate, existing);
        }
        if (candidate.J_1907_R() instanceof BowItem && existing.J_1907_R() instanceof BowItem) {
            return this.J_1907_R(candidate, existing);
        }
        if (candidate.J_1907_R() instanceof Z_1630_j && existing.J_1907_R() instanceof Z_1630_j) {
            return this.J_1907_R(candidate, existing);
        }
        if (candidate.J_1907_R() instanceof R_2515_i) {
            if (K_4096_w.G_564_y(existing)) {
                return false;
            }
            if (!(existing.J_1907_R() instanceof R_2515_i)) {
                return true;
            }
            R_2515_i armoritem = (R_2515_i)candidate.J_1907_R();
            R_2515_i armoritem1 = (R_2515_i)existing.J_1907_R();
            if (armoritem.v_4262_N() != armoritem1.v_4262_N()) {
                return armoritem.v_4262_N() > armoritem1.v_4262_N();
            }
            if (armoritem.w_1484_f() != armoritem1.w_1484_f()) {
                return armoritem.w_1484_f() > armoritem1.w_1484_f();
            }
            return this.J_1907_R(candidate, existing);
        }
        if (candidate.J_1907_R() instanceof DiggerItem) {
            if (existing.J_1907_R() instanceof v_1669_V) {
                return true;
            }
            if (existing.J_1907_R() instanceof DiggerItem) {
                DiggerItem toolitem = (DiggerItem)candidate.J_1907_R();
                DiggerItem toolitem1 = (DiggerItem)existing.J_1907_R();
                if (toolitem.v_4262_N() != toolitem1.v_4262_N()) {
                    return toolitem.v_4262_N() > toolitem1.v_4262_N();
                }
                return this.J_1907_R(candidate, existing);
            }
        }
        return false;
    }

    public boolean J_1907_R(Z_1993_T p_233659_1_, Z_1993_T p_233659_2_) {
        if (p_233659_1_.v_4262_N() >= p_233659_2_.v_4262_N() && (!p_233659_1_.h_1847_R() || p_233659_2_.h_1847_R())) {
            if (p_233659_1_.h_1847_R() && p_233659_2_.h_1847_R()) {
                return p_233659_1_.Q_4569_t().G_564_y().stream().anyMatch(p_lambda$func_233659_b_$0_0_ -> !p_lambda$func_233659_b_$0_0_.equals("Damage")) && !p_233659_2_.Q_4569_t().G_564_y().stream().anyMatch(p_lambda$func_233659_b_$1_0_ -> !p_lambda$func_233659_b_$1_0_.equals("Damage"));
            }
            return false;
        }
        return true;
    }

    public boolean w_1484_f(Z_1993_T stack) {
        return true;
    }

    public boolean t_148_a(Z_1993_T p_230293_1_) {
        return this.w_1484_f(p_230293_1_);
    }

    public boolean w_1484_f(double distanceToClosestPlayer) {
        return true;
    }

    public boolean e_2887_G() {
        return this.y_2772_m();
    }

    protected boolean B_1668_F() {
        return false;
    }

    @Override
    public void a_178_J() {
        if (this.O_508_d.x_607_J() == R_2450_T.n_1700_B && this.B_1668_F()) {
            this.Ops();
        } else if (!this.s_2632_s() && !this.e_2887_G()) {
            a_3913_L entity = this.O_508_d.n_1700_B((N_4263_v)this, -1.0);
            if (Reflector.ForgeEventFactory_canEntityDespawn.exists()) {
                Object object = Reflector.ForgeEventFactory_canEntityDespawn.call((Object)this);
                if (object == ReflectorForge.EVENT_RESULT_DENY) {
                    this.UploadTokenCache = 0;
                    entity = null;
                } else if (object == ReflectorForge.EVENT_RESULT_ALLOW) {
                    this.Ops();
                    entity = null;
                }
            }
            if (entity != null) {
                int i;
                int j;
                double d0 = entity.G_564_y((N_4263_v)this);
                if (d0 > (double)(j = (i = this.f_4016_n().P_1922_E().u_1723_Y()) * i) && this.w_1484_f(d0)) {
                    this.Ops();
                }
                int k = this.f_4016_n().P_1922_E().v_4262_N();
                int l = k * k;
                if (this.UploadTokenCache > 600 && this.RealmsWorldOptions.nextInt(800) == 0 && d0 > (double)l && this.w_1484_f(d0)) {
                    this.Ops();
                } else if (d0 < (double)l) {
                    this.UploadTokenCache = 0;
                }
            }
        } else {
            this.UploadTokenCache = 0;
        }
    }

    @Override
    protected final void H_2857_Y() {
        ++this.UploadTokenCache;
        this.O_508_d.D_4792_h().n_1700_B("sensing");
        this.h_1847_R.n_1700_B();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().n_1700_B("targetSelector");
        this.u_2550_I.n_1700_B();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().n_1700_B("goalSelector");
        this.s_956_w.n_1700_B();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().n_1700_B("navigation");
        this.t_148_a.n_1700_B();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().n_1700_B("mob tick");
        this.X_933_l();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().n_1700_B("controls");
        this.O_508_d.D_4792_h().n_1700_B("move");
        this.v_4262_N.n_1700_B();
        this.O_508_d.D_4792_h().J_1907_R("look");
        this.u_1723_Y.n_1700_B();
        this.O_508_d.D_4792_h().J_1907_R("jump");
        this.w_1484_f.J_1907_R();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.O_508_d.D_4792_h().R_4764_Y();
        this.g_164_R();
    }

    protected void g_164_R() {
        DebugPackets.n_1700_B(this.O_508_d, this, this.s_956_w);
    }

    protected void X_933_l() {
    }

    public int Z_976_R() {
        return 40;
    }

    public int H_1990_U() {
        return 75;
    }

    public int N_2525_X() {
        return 10;
    }

    public void n_1700_B(N_4263_v entityIn, float maxYawIncrease, float maxPitchIncrease) {
        double d2;
        double d0 = entityIn.O_3598_v() - this.O_3598_v();
        double d1 = entityIn.l_2647_k() - this.l_2647_k();
        if (entityIn instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)entityIn;
            d2 = livingentity.X_2048_Y() - this.X_2048_Y();
        } else {
            d2 = (entityIn.i_601_W().minY + entityIn.i_601_W().maxY) / 2.0 - this.X_2048_Y();
        }
        double d3 = u_530_F.n_1700_B(d0 * d0 + d1 * d1);
        float f = (float)(u_530_F.G_564_y(d1, d0) * 57.2957763671875) - 90.0f;
        float f1 = (float)(-(u_530_F.G_564_y(d2, d3) * 57.2957763671875));
        this.f_4016_n = this.n_1700_B(this.f_4016_n, f1, maxPitchIncrease);
        this.p_178_J = this.n_1700_B(this.p_178_J, f, maxYawIncrease);
    }

    private float n_1700_B(float angle, float targetAngle, float maxIncrease) {
        float f = u_530_F.v_4262_N(targetAngle - angle);
        if (f > maxIncrease) {
            f = maxIncrease;
        }
        if (f < -maxIncrease) {
            f = -maxIncrease;
        }
        return angle + f;
    }

    public static boolean n_1700_B(t_5_h<? extends Z_530_i> typeIn, LevelAccessor worldIn, a_3160_D reason, c_1514_x pos, Random randomIn) {
        c_1514_x blockpos = pos.down();
        return reason == a_3160_D.R_4764_Y || worldIn.getBlockState(blockpos).n_1700_B((BlockGetter)worldIn, blockpos, typeIn);
    }

    public boolean n_1700_B(LevelAccessor worldIn, a_3160_D spawnReasonIn) {
        return true;
    }

    public boolean n_1700_B(T_1316_M worldIn) {
        return !worldIn.G_564_y(this.i_601_W()) && worldIn.P_1922_E(this);
    }

    public int c_4037_x() {
        return 4;
    }

    public boolean t_1786_h(int sizeIn) {
        return false;
    }

    @Override
    public int n_3197_X() {
        if (this.t_148_a() == null) {
            return 3;
        }
        int i = (int)(this.g_46_E() - this.L_1733_J() * 0.33f);
        if ((i -= (3 - this.O_508_d.x_607_J().n_1700_B()) * 4) < 0) {
            i = 0;
        }
        return i + 3;
    }

    @Override
    public Iterable<Z_1993_T> f_3449_S() {
        return this.Q_4569_t;
    }

    @Override
    public Iterable<Z_1993_T> u_55_V() {
        return this.M_182_A;
    }

    @Override
    public Z_1993_T J_1907_R(e_1174_E slotIn) {
        switch (slotIn.n_1700_B()) {
            case n_1700_B: {
                return this.Q_4569_t.get(slotIn.J_1907_R());
            }
            case J_1907_R: {
                return this.M_182_A.get(slotIn.J_1907_R());
            }
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public void n_1700_B(e_1174_E slotIn, Z_1993_T stack) {
        switch (slotIn.n_1700_B()) {
            case n_1700_B: {
                this.Q_4569_t.set(slotIn.J_1907_R(), stack);
                break;
            }
            case J_1907_R: {
                this.M_182_A.set(slotIn.J_1907_R(), stack);
            }
        }
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        super.n_1700_B(source, looting, recentlyHitIn);
        for (e_1174_E equipmentslottype : e_1174_E.values()) {
            boolean flag;
            Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
            float f = this.P_1922_E(equipmentslottype);
            boolean bl = flag = f > 1.0f;
            if (itemstack.n_1700_B() || K_4096_w.P_1922_E(itemstack) || !recentlyHitIn && !flag || !(Math.max(this.RealmsWorldOptions.nextFloat() - (float)looting * 0.01f, 0.0f) < f)) continue;
            if (!flag && itemstack.P_1922_E()) {
                itemstack.J_1907_R(itemstack.w_1484_f() - this.RealmsWorldOptions.nextInt(1 + this.RealmsWorldOptions.nextInt(Math.max(itemstack.w_1484_f() - 3, 1))));
            }
            this.a_(itemstack);
            this.n_1700_B(equipmentslottype, Z_1993_T.J_1907_R);
        }
    }

    protected float P_1922_E(e_1174_E slotIn) {
        return switch (slotIn.n_1700_B()) {
            case e_1174_E.n_1700_B.n_1700_B -> this.M_588_G[slotIn.J_1907_R()];
            case e_1174_E.n_1700_B.J_1907_R -> this.P_4830_p[slotIn.J_1907_R()];
            default -> 0.0f;
        };
    }

    protected void n_1700_B(DifficultyInstance difficulty) {
        if (this.RealmsWorldOptions.nextFloat() < 0.15f * difficulty.R_4764_Y()) {
            float f;
            int i = this.RealmsWorldOptions.nextInt(2);
            float f2 = f = this.O_508_d.x_607_J() == R_2450_T.G_564_y ? 0.1f : 0.25f;
            if (this.RealmsWorldOptions.nextFloat() < 0.095f) {
                ++i;
            }
            if (this.RealmsWorldOptions.nextFloat() < 0.095f) {
                ++i;
            }
            if (this.RealmsWorldOptions.nextFloat() < 0.095f) {
                ++i;
            }
            boolean flag = true;
            for (e_1174_E equipmentslottype : e_1174_E.values()) {
                q_1613_l item;
                if (equipmentslottype.n_1700_B() != e_1174_E.n_1700_B.J_1907_R) continue;
                Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
                if (!flag && this.RealmsWorldOptions.nextFloat() < f) break;
                flag = false;
                if (!itemstack.n_1700_B() || (item = Z_530_i.n_1700_B(equipmentslottype, i)) == null) continue;
                this.n_1700_B(equipmentslottype, new Z_1993_T(item));
            }
        }
    }

    public static e_1174_E s_956_w(Z_1993_T stack) {
        e_1174_E equipmentslottype;
        if (Reflector.IForgeItemStack_getEquipmentSlot.exists() && (equipmentslottype = (e_1174_E)((Object)Reflector.call(stack, Reflector.IForgeItemStack_getEquipmentSlot, new Object[0]))) != null) {
            return equipmentslottype;
        }
        q_1613_l item = stack.J_1907_R();
        if (!(item == a_3742_W.X_2048_Y.u_1723_Y() || item instanceof v_1669_V && ((v_1669_V)item).v_4262_N() instanceof AbstractSkullBlock)) {
            if (item instanceof R_2515_i) {
                return ((R_2515_i)item).R_4764_Y();
            }
            if (item == Items.NyliumBlock) {
                return e_1174_E.P_1922_E;
            }
            return ReflectorForge.isShield(stack, null) ? e_1174_E.J_1907_R : e_1174_E.n_1700_B;
        }
        return e_1174_E.u_1723_Y;
    }

    @Nullable
    public static q_1613_l n_1700_B(e_1174_E slotIn, int chance) {
        switch (slotIn) {
            case u_1723_Y: {
                if (chance == 0) {
                    return Items.t_1509_b;
                }
                if (chance == 1) {
                    return Items.h_3066_J;
                }
                if (chance == 2) {
                    return Items.S_4325_V;
                }
                if (chance == 3) {
                    return Items.T_1170_t;
                }
                if (chance == 4) {
                    return Items.q_4361_M;
                }
            }
            case P_1922_E: {
                if (chance == 0) {
                    return Items.r_2090_h;
                }
                if (chance == 1) {
                    return Items.m_38_G;
                }
                if (chance == 2) {
                    return Items.f_800_j;
                }
                if (chance == 3) {
                    return Items.k_2282_P;
                }
                if (chance == 4) {
                    return Items.f_508_U;
                }
            }
            case G_564_y: {
                if (chance == 0) {
                    return Items.z_2759_Q;
                }
                if (chance == 1) {
                    return Items.k_4946_A;
                }
                if (chance == 2) {
                    return Items.R_2329_T;
                }
                if (chance == 3) {
                    return Items.U_2474_c;
                }
                if (chance == 4) {
                    return Items.A_1603_w;
                }
            }
            case R_4764_Y: {
                if (chance == 0) {
                    return Items.a_1344_X;
                }
                if (chance == 1) {
                    return Items.m_4644_u;
                }
                if (chance == 2) {
                    return Items.F_747_P;
                }
                if (chance == 3) {
                    return Items.j_2302_z;
                }
                if (chance != 4) break;
                return Items.V_4557_X;
            }
        }
        return null;
    }

    protected void J_1907_R(DifficultyInstance difficulty) {
        float f = difficulty.R_4764_Y();
        this.Z_875_P(f);
        for (e_1174_E equipmentslottype : e_1174_E.values()) {
            if (equipmentslottype.n_1700_B() != e_1174_E.n_1700_B.J_1907_R) continue;
            this.n_1700_B(f, equipmentslottype);
        }
    }

    protected void Z_875_P(float p_241844_1_) {
        if (!this.A_2714_y().n_1700_B() && this.RealmsWorldOptions.nextFloat() < 0.25f * p_241844_1_) {
            this.n_1700_B(e_1174_E.n_1700_B, K_4096_w.n_1700_B(this.RealmsWorldOptions, this.A_2714_y(), (int)(5.0f + p_241844_1_ * (float)this.RealmsWorldOptions.nextInt(18)), false));
        }
    }

    protected void n_1700_B(float p_242289_1_, e_1174_E p_242289_2_) {
        Z_1993_T itemstack = this.J_1907_R(p_242289_2_);
        if (!itemstack.n_1700_B() && this.RealmsWorldOptions.nextFloat() < 0.5f * p_242289_1_) {
            this.n_1700_B(p_242289_2_, K_4096_w.n_1700_B(this.RealmsWorldOptions, itemstack, (int)(5.0f + p_242289_1_ * (float)this.RealmsWorldOptions.nextInt(18)), false));
        }
    }

    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(Attributes.J_1907_R).R_4764_Y(new U_1880_G("Random spawn bonus", this.RealmsWorldOptions.nextGaussian() * 0.05, U_1880_G.n_1700_B.J_1907_R));
        if (this.RealmsWorldOptions.nextFloat() < 0.05f) {
            this.P_1922_E(true);
        } else {
            this.P_1922_E(false);
        }
        return spawnDataIn;
    }

    public boolean g_2268_R() {
        return false;
    }

    public void T_3594_S() {
        this.multiplayerClientSuggestionProvider = true;
    }

    public void n_1700_B(e_1174_E slotIn, float chance) {
        switch (slotIn.n_1700_B()) {
            case n_1700_B: {
                this.M_588_G[slotIn.J_1907_R()] = chance;
                break;
            }
            case J_1907_R: {
                this.P_4830_p[slotIn.J_1907_R()] = chance;
            }
        }
    }

    public boolean D_4792_h() {
        return this.t_1786_h;
    }

    public void R_4764_Y(boolean canPickup) {
        this.t_1786_h = canPickup;
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        return this.J_1907_R(equipmentslottype).n_1700_B() && this.D_4792_h();
    }

    public boolean s_2632_s() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public final m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        if (!this.RealmsLongRunningMcoTaskScreen()) {
            return m_3054_I.R_4764_Y;
        }
        if (this.y_2622_c() == player) {
            this.n_1700_B(true, !player.C_415_h.G_564_y);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        m_3054_I actionresulttype = this.R_4764_Y(player, hand);
        if (actionresulttype.n_1700_B()) {
            return actionresulttype;
        }
        actionresulttype = this.J_1907_R(player, hand);
        return actionresulttype.n_1700_B() ? actionresulttype : super.n_1700_B(player, hand);
    }

    private m_3054_I R_4764_Y(a_3913_L p_233661_1_, x_1688_C p_233661_2_) {
        m_3054_I actionresulttype;
        Z_1993_T itemstack = p_233661_1_.R_4764_Y(p_233661_2_);
        if (itemstack.J_1907_R() == Items.c_1788_D && this.G_564_y(p_233661_1_)) {
            this.J_1907_R((N_4263_v)p_233661_1_, true);
            itemstack.v_4262_N(1);
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        if (itemstack.J_1907_R() == Items.HorizontalDirectionalBlock && (actionresulttype = itemstack.n_1700_B(p_233661_1_, (r_4811_B)this, p_233661_2_)).n_1700_B()) {
            return actionresulttype;
        }
        if (itemstack.J_1907_R() instanceof SpawnEggItem) {
            if (this.O_508_d instanceof e_3591_l) {
                SpawnEggItem spawneggitem = (SpawnEggItem)itemstack.J_1907_R();
                Optional<Z_530_i> optional = spawneggitem.n_1700_B(p_233661_1_, this, this.f_4016_n(), (e_3591_l)this.O_508_d, this.s_4990_V(), itemstack);
                optional.ifPresent(p_lambda$func_233661_c_$2_2_ -> this.n_1700_B(p_233661_1_, (Z_530_i)p_lambda$func_233661_c_$2_2_));
                return optional.isPresent() ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
            }
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    protected void n_1700_B(a_3913_L playerIn, Z_530_i child) {
    }

    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        return m_3054_I.R_4764_Y;
    }

    public boolean l_1233_K() {
        return this.u_1723_Y(this.b_2312_j());
    }

    public boolean u_1723_Y(c_1514_x pos) {
        if (this.Z_875_P == -1.0f) {
            return true;
        }
        return this.q_2307_F.distanceSq(pos) < (double)(this.Z_875_P * this.Z_875_P);
    }

    public void n_1700_B(c_1514_x pos, int distance) {
        this.q_2307_F = pos;
        this.Z_875_P = distance;
    }

    public c_1514_x z_1333_t() {
        return this.q_2307_F;
    }

    public float L_3537_K() {
        return this.Z_875_P;
    }

    public boolean z_3000_g() {
        return this.Z_875_P != -1.0f;
    }

    @Nullable
    public <T extends Z_530_i> T n_1700_B(t_5_h<T> p_233656_1_, boolean p_233656_2_) {
        if (this.t_4219_U) {
            return (T)((Z_530_i)null);
        }
        Z_530_i t = (Z_530_i)p_233656_1_.n_1700_B(this.O_508_d);
        t.multiplayerClientSuggestionProvider(this);
        t.n_1700_B(this.d_());
        t.G_564_y(this.n_473_l());
        if (this.t_3452_g()) {
            t.n_1700_B(this.k_2302_P());
            t.M_182_A(this.V_118_c());
        }
        if (this.s_2632_s()) {
            t.T_3594_S();
        }
        t.Q_4569_t(this.P_925_e());
        if (p_233656_2_) {
            t.R_4764_Y(this.D_4792_h());
            for (e_1174_E equipmentslottype : e_1174_E.values()) {
                Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
                if (itemstack.n_1700_B()) continue;
                t.n_1700_B(equipmentslottype, itemstack.t_148_a());
                t.n_1700_B(equipmentslottype, this.P_1922_E(equipmentslottype));
                itemstack.P_1922_E(0);
            }
        }
        this.O_508_d.a_(t);
        if (this.y_2772_m()) {
            N_4263_v entity = this.l_3609_d();
            this.A_3959_N();
            t.n_1700_B(entity, true);
        }
        this.Ops();
        return (T)t;
    }

    protected void h_1847_R() {
        if (this.k_2293_S != null) {
            this.y_4642_Y();
        }
        if (!(this.Q_2552_b == null || this.RealmsLongRunningMcoTaskScreen() && this.Q_2552_b.RealmsLongRunningMcoTaskScreen())) {
            this.n_1700_B(true, true);
        }
    }

    public void n_1700_B(boolean sendPacket, boolean dropLead) {
        if (this.Q_2552_b != null) {
            this.z_1333_t = false;
            if (!(this.Q_2552_b instanceof a_3913_L)) {
                this.Q_2552_b.z_1333_t = false;
            }
            this.Q_2552_b = null;
            this.k_2293_S = null;
            if (!this.O_508_d.Y_259_p && dropLead) {
                this.n_1700_B((q_1803_e)Items.c_1788_D);
            }
            if (!this.O_508_d.Y_259_p && sendPacket && this.O_508_d instanceof e_3591_l) {
                ((e_3591_l)this.O_508_d).Y_259_p().J_1907_R(this, new e_446_u(this, null));
            }
        }
    }

    public boolean G_564_y(a_3913_L player) {
        return !this.n_4915_F() && !(this instanceof x_1835_e);
    }

    public boolean n_4915_F() {
        return this.Q_2552_b != null;
    }

    @Nullable
    public N_4263_v y_2622_c() {
        if (this.Q_2552_b == null && this.C_2741_M != 0 && this.O_508_d.Y_259_p) {
            this.Q_2552_b = this.O_508_d.J_1907_R(this.C_2741_M);
        }
        return this.Q_2552_b;
    }

    public void J_1907_R(N_4263_v entityIn, boolean sendAttachNotification) {
        this.Q_2552_b = entityIn;
        this.k_2293_S = null;
        this.z_1333_t = true;
        if (!(this.Q_2552_b instanceof a_3913_L)) {
            this.Q_2552_b.z_1333_t = true;
        }
        if (!this.O_508_d.Y_259_p && sendAttachNotification && this.O_508_d instanceof e_3591_l) {
            ((e_3591_l)this.O_508_d).Y_259_p().J_1907_R(this, new e_446_u(this, this.Q_2552_b));
        }
        if (this.y_2772_m()) {
            this.A_3959_N();
        }
    }

    public void multiplayerClientSuggestionProvider(int leashHolderIDIn) {
        this.C_2741_M = leashHolderIDIn;
        this.n_1700_B(false, false);
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn, boolean force) {
        boolean flag = super.n_1700_B(entityIn, force);
        if (flag && this.n_4915_F()) {
            this.n_1700_B(true, true);
        }
        return flag;
    }

    private void y_4642_Y() {
        if (this.k_2293_S != null && this.O_508_d instanceof e_3591_l) {
            if (this.k_2293_S.J_1907_R("UUID")) {
                UUID uuid = this.k_2293_S.n_1700_B("UUID");
                N_4263_v entity = ((e_3591_l)this.O_508_d).J_1907_R(uuid);
                if (entity != null) {
                    this.J_1907_R(entity, true);
                    return;
                }
            } else if (this.k_2293_S.R_4764_Y("X", 99) && this.k_2293_S.R_4764_Y("Y", 99) && this.k_2293_S.R_4764_Y("Z", 99)) {
                c_1514_x blockpos = new c_1514_x(this.k_2293_S.w_1484_f("X"), this.k_2293_S.w_1484_f("Y"), this.k_2293_S.w_1484_f("Z"));
                this.J_1907_R(LeashFenceKnotEntity.n_1700_B(this.O_508_d, blockpos), true);
                return;
            }
            if (this.RealmsWorldResetDto > 100) {
                this.n_1700_B((q_1803_e)Items.c_1788_D);
                this.k_2293_S = null;
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
    public boolean v_887_r() {
        return this.g_2268_R() && super.v_887_r();
    }

    public static boolean R_4764_Y(e_1174_E slotIn, Z_1993_T stack) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(stack);
        return equipmentslottype == slotIn || equipmentslottype == e_1174_E.n_1700_B && slotIn == e_1174_E.J_1907_R || equipmentslottype == e_1174_E.J_1907_R && slotIn == e_1174_E.n_1700_B;
    }

    @Override
    public boolean w_1457_N() {
        return super.w_1457_N() && !this.n_473_l();
    }

    public void G_564_y(boolean disable) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        this.l_4537_E.J_1907_R(n_1700_B, disable ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE));
    }

    public void P_1922_E(boolean leftHanded) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        this.l_4537_E.J_1907_R(n_1700_B, leftHanded ? (byte)(b0 | 2) : (byte)(b0 & 0xFFFFFFFD));
    }

    public void multiplayerClientSuggestionProvider(boolean hasAggro) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        this.l_4537_E.J_1907_R(n_1700_B, hasAggro ? (byte)(b0 | 4) : (byte)(b0 & 0xFFFFFFFB));
    }

    public boolean n_473_l() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 1) != 0;
    }

    public boolean r_4414_L() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 2) != 0;
    }

    public boolean P_2272_O() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 4) != 0;
    }

    public void n_1700_B(boolean childZombie) {
    }

    @Override
    public k_4231_L d_2169_p() {
        return this.r_4414_L() ? k_4231_L.n_1700_B : k_4231_L.J_1907_R;
    }

    @Override
    public boolean n_1700_B(r_4811_B target) {
        return target.f_4016_n() == t_5_h.g_4106_L && ((a_3913_L)target).C_415_h.n_1700_B ? false : super.n_1700_B(target);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag;
        int i;
        float f = (float)this.J_1907_R(Attributes.u_1723_Y);
        float f1 = (float)this.J_1907_R(Attributes.v_4262_N);
        if (entityIn instanceof r_4811_B) {
            f += K_4096_w.n_1700_B(this.A_2714_y(), ((r_4811_B)entityIn).F_2860_q());
            f1 += (float)K_4096_w.J_1907_R(this);
        }
        if ((i = K_4096_w.R_4764_Y(this)) > 0) {
            entityIn.P_1922_E(i * 4);
        }
        if (flag = entityIn.n_1700_B(P_11_z.R_4764_Y(this), f)) {
            if (f1 > 0.0f && entityIn instanceof r_4811_B) {
                ((r_4811_B)entityIn).n_1700_B(f1 * 0.5f, (double)u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)), (double)(-u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180))));
                this.v_4262_N(this.I_4348_c().G_564_y(0.6, 1.0, 0.6));
            }
            if (entityIn instanceof a_3913_L) {
                a_3913_L playerentity = (a_3913_L)entityIn;
                this.n_1700_B(playerentity, this.A_2714_y(), playerentity.Y_601_j() ? playerentity.B_2580_P() : Z_1993_T.J_1907_R);
            }
            this.n_1700_B((r_4811_B)this, entityIn);
            this.C_2741_M(entityIn);
        }
        return flag;
    }

    private void n_1700_B(a_3913_L p_233655_1_, Z_1993_T p_233655_2_, Z_1993_T p_233655_3_) {
        if (!p_233655_2_.n_1700_B() && !p_233655_3_.n_1700_B() && p_233655_2_.J_1907_R() instanceof AxeItem && p_233655_3_.J_1907_R() == Items.NoteBlock) {
            float f = 0.25f + (float)K_4096_w.u_1723_Y(this) * 0.05f;
            if (this.RealmsWorldOptions.nextFloat() < f) {
                p_233655_1_.p_1458_L().n_1700_B(Items.NoteBlock, 100);
                this.O_508_d.n_1700_B((N_4263_v)p_233655_1_, (byte)30);
            }
        }
    }

    protected boolean S_2828_i() {
        if (this.O_508_d.q_4610_l() && !this.O_508_d.Y_259_p) {
            c_1514_x blockpos;
            float f = this.RealmsConfirmScreen();
            c_1514_x c_1514_x2 = blockpos = this.l_3609_d() instanceof g_1462_f ? new c_1514_x(this.O_3598_v(), Math.round(this.X_2960_b()), this.l_2647_k()).up() : new c_1514_x(this.O_3598_v(), Math.round(this.X_2960_b()), this.l_2647_k());
            if (f > 0.5f && this.RealmsWorldOptions.nextFloat() * 30.0f < (f - 0.4f) * 2.0f && this.O_508_d.canSeeSky(blockpos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void R_4764_Y(r_109_r<Fluid> fluidTag) {
        if (this.e_4240_b().t_1786_h()) {
            super.R_4764_Y(fluidTag);
        } else {
            this.v_4262_N(this.I_4348_c().J_1907_R(0.0, 0.3, 0.0));
        }
    }

    @Override
    protected void X_4895_T() {
        super.X_4895_T();
        this.n_1700_B(true, false);
    }

    private boolean h_1640_b() {
        double d1;
        if (this.d_()) {
            return false;
        }
        if (this.RealmsLongRunningMcoTaskScreen > 0) {
            return false;
        }
        if (this.RealmsWorldResetDto < 20) {
            return false;
        }
        List list = this.R_4764_Y(this.Z_759_W());
        if (list == null) {
            return false;
        }
        if (list.size() != 1) {
            return false;
        }
        N_4263_v entity = (N_4263_v)list.get(0);
        double d0 = Math.max(Math.abs(this.O_3598_v() - entity.O_3598_v()) - 16.0, 0.0);
        double d2 = d0 * d0 + (d1 = Math.max(Math.abs(this.l_2647_k() - entity.l_2647_k()) - 16.0, 0.0)) * d1;
        return !this.n_1700_B(d2);
    }

    private List R_4764_Y(b_4507_u p_getListPlayers_1_) {
        b_4507_u world = this.Z_759_W();
        if (world instanceof k_4690_i) {
            k_4690_i clientworld = (k_4690_i)world;
            return clientworld.multiplayerClientSuggestionProvider();
        }
        if (world instanceof e_3591_l) {
            e_3591_l serverworld = (e_3591_l)world;
            return serverworld.multiplayerClientSuggestionProvider();
        }
        return null;
    }

    private void V_1176_p() {
        ++this.UploadTokenCache;
        if (this instanceof Monster) {
            float f = this.RealmsConfirmScreen();
            boolean flag = this instanceof W_4304_a;
            if (f > 0.5f || flag) {
                this.UploadTokenCache += 2;
            }
        }
    }
}


