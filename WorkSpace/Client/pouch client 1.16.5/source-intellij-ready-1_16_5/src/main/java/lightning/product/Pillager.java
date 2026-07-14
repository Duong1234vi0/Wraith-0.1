/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.D_2364_U;
import lightning.product.RandomStrollGoal;
import lightning.product.Attributes;
import lightning.product.K_1310_v;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.M_4954_p;
import lightning.product.N_1216_z;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.RangedCrossbowAttackGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.AbstractIllager;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3129_s;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_2414_j;

public class Pillager
extends AbstractIllager
implements M_4954_p {
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(Pillager.class, EntityDataSerializers.t_148_a);
    private final N_1216_z h_1847_R = new N_1216_z(5);

    public Pillager(t_5_h<? extends Pillager> type, b_4507_u worldIn) {
        super((t_5_h<? extends AbstractIllager>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new W_4304_a.J_1907_R(this, this, 10.0f));
        this.s_956_w.n_1700_B(3, new RangedCrossbowAttackGoal<Pillager>(this, 1.0, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomStrollGoal(this, 0.6));
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, a_3913_L.class, 15.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 15.0f));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, false));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
    }

    public static s_1415_m.n_1700_B U_1697_c() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.35f).n_1700_B(Attributes.n_1700_B, 24.0).n_1700_B(Attributes.u_1723_Y, 5.0).n_1700_B(Attributes.J_1907_R, 32.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(R_4764_Y, false);
    }

    @Override
    public boolean n_1700_B(ProjectileWeaponItem p_230280_1_) {
        return p_230280_1_ == Items.V_2454_J;
    }

    public boolean V_537_k() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    @Override
    public void J_1907_R(boolean isCharging) {
        this.l_4537_E.J_1907_R(R_4764_Y, isCharging);
    }

    @Override
    public void n_1700_B() {
        this.UploadTokenCache = 0;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        q_2896_o listnbt = new q_2896_o();
        for (int i = 0; i < this.h_1847_R.Y_259_p(); ++i) {
            Z_1993_T itemstack = this.h_1847_R.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            listnbt.add(itemstack.J_1907_R(new U_2912_j()));
        }
        compound.n_1700_B("Inventory", listnbt);
    }

    @Override
    public AbstractIllager.n_1700_B u_1723_Y() {
        if (this.V_537_k()) {
            return AbstractIllager.n_1700_B.u_1723_Y;
        }
        if (this.n_1700_B(Items.V_2454_J)) {
            return AbstractIllager.n_1700_B.P_1922_E;
        }
        return this.P_2272_O() ? AbstractIllager.n_1700_B.J_1907_R : AbstractIllager.n_1700_B.w_1484_f;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        q_2896_o listnbt = compound.G_564_y("Inventory", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            Z_1993_T itemstack = Z_1993_T.n_1700_B(listnbt.n_1700_B(i));
            if (itemstack.n_1700_B()) continue;
            this.h_1847_R.n_1700_B(itemstack);
        }
        this.R_4764_Y(true);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        return !blockstate.n_1700_B(a_3742_W.t_148_a) && !blockstate.n_1700_B(a_3742_W.A_4115_X) ? 0.5f - worldIn.w_1484_f(pos) : 10.0f;
    }

    @Override
    public int c_4037_x() {
        return 1;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.n_1700_B(difficultyIn);
        this.J_1907_R(difficultyIn);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.V_2454_J));
    }

    @Override
    protected void Z_875_P(float p_241844_1_) {
        Z_1993_T itemstack;
        super.Z_875_P(p_241844_1_);
        if (this.RealmsWorldOptions.nextInt(300) == 0 && (itemstack = this.A_2714_y()).J_1907_R() == Items.V_2454_J) {
            Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(itemstack);
            map.putIfAbsent(Enchantments.z_1737_N, 1);
            K_4096_w.n_1700_B(map, itemstack);
            this.n_1700_B(e_1174_E.n_1700_B, itemstack);
        }
    }

    @Override
    public boolean Q_4569_t(N_4263_v entityIn) {
        if (super.Q_4569_t(entityIn)) {
            return true;
        }
        if (entityIn instanceof r_4811_B && ((r_4811_B)entityIn).F_2860_q() == MobType.G_564_y) {
            return this.L_1362_X() == null && entityIn.L_1362_X() == null;
        }
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.U_2474_c;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.q_4361_M;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.f_508_U;
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        this.n_1700_B((r_4811_B)this, 1.6f);
    }

    @Override
    public void n_1700_B(r_4811_B p_230284_1_, Z_1993_T p_230284_2_, Projectile p_230284_3_, float p_230284_4_) {
        this.n_1700_B(this, p_230284_1_, p_230284_3_, p_230284_4_, 1.6f);
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        Z_1993_T itemstack = itemEntity.P_1922_E();
        if (itemstack.J_1907_R() instanceof x_2414_j) {
            super.J_1907_R(itemEntity);
        } else {
            q_1613_l item = itemstack.J_1907_R();
            if (this.J_1907_R(item)) {
                this.n_1700_B(itemEntity);
                Z_1993_T itemstack1 = this.h_1847_R.n_1700_B(itemstack);
                if (itemstack1.n_1700_B()) {
                    itemEntity.Ops();
                } else {
                    itemstack.P_1922_E(itemstack1.t_4043_B());
                }
            }
        }
    }

    private boolean J_1907_R(q_1613_l p_213672_1_) {
        return this.J_3635_s() && p_213672_1_ == Items.o_3946_o;
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (super.n_1700_B(inventorySlot, itemStackIn)) {
            return true;
        }
        int i = inventorySlot - 300;
        if (i >= 0 && i < this.h_1847_R.Y_259_p()) {
            this.h_1847_R.J_1907_R(i, itemStackIn);
            return true;
        }
        return false;
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
        boolean flag;
        b_3129_s raid = this.y_2447_C();
        boolean bl = flag = this.RealmsWorldOptions.nextFloat() <= raid.Y_259_p();
        if (flag) {
            Z_1993_T itemstack = new Z_1993_T(Items.V_2454_J);
            HashMap map = Maps.newHashMap();
            if (wave > raid.n_1700_B(R_2450_T.R_4764_Y)) {
                map.put(Enchantments.d_2427_y, 2);
            } else if (wave > raid.n_1700_B(R_2450_T.J_1907_R)) {
                map.put(Enchantments.d_2427_y, 1);
            }
            map.put(Enchantments.n_3318_d, 1);
            K_4096_w.n_1700_B(map, itemstack);
            this.n_1700_B(e_1174_E.n_1700_B, itemstack);
        }
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.j_2302_z;
    }
}


