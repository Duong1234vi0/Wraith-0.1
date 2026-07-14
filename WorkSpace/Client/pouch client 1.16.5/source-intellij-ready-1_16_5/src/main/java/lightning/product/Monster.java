/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import java.util.function.Predicate;
import lightning.product.D_38_f;
import lightning.product.Attributes;
import lightning.product.K_4719_o;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.T_1316_M;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.PathfinderMob;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.x_1835_e;

public abstract class Monster
extends PathfinderMob
implements x_1835_e {
    protected Monster(t_5_h<? extends Monster> type, b_4507_u worldIn) {
        super((t_5_h<? extends PathfinderMob>)type, worldIn);
        this.P_1922_E = 5;
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    public void Y_1740_V() {
        this.k_3129_Y();
        this.s_();
        super.Y_1740_V();
    }

    protected void s_() {
        float f = this.RealmsConfirmScreen();
        if (f > 0.5f) {
            this.UploadTokenCache += 2;
        }
    }

    @Override
    protected boolean B_1668_F() {
        return true;
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.O_726_g;
    }

    @Override
    protected SoundEvent S_4022_R() {
        return SoundEvents.r_4601_j;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return this.n_1700_B(source) ? false : super.n_1700_B(source, amount);
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.TargetStrafe;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.TargetPearl;
    }

    @Override
    protected SoundEvent M_588_G(int heightIn) {
        return heightIn > 4 ? SoundEvents.Surround : SoundEvents.TriggerBot;
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return 0.5f - worldIn.w_1484_f(pos);
    }

    public static boolean n_1700_B(ServerLevelAccessor worldIn, c_1514_x pos, Random randomIn) {
        if (worldIn.getLightFor(K_4719_o.n_1700_B, pos) > randomIn.nextInt(32)) {
            return false;
        }
        int i = worldIn.J_1907_R().N_2525_X() ? worldIn.J_1907_R(pos, 10) : worldIn.u_2550_I(pos);
        return i <= randomIn.nextInt(8);
    }

    public static boolean J_1907_R(t_5_h<? extends Monster> type, ServerLevelAccessor worldIn, a_3160_D reason, c_1514_x pos, Random randomIn) {
        return worldIn.x_607_J() != R_2450_T.n_1700_B && Monster.n_1700_B(worldIn, pos, randomIn) && Monster.n_1700_B(type, worldIn, reason, pos, randomIn);
    }

    public static boolean R_4764_Y(t_5_h<? extends Monster> type, LevelAccessor worldIn, a_3160_D reason, c_1514_x pos, Random randomIn) {
        return worldIn.x_607_J() != R_2450_T.n_1700_B && Monster.n_1700_B(type, worldIn, reason, pos, randomIn);
    }

    public static s_1415_m.n_1700_B o_4117_e() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.u_1723_Y);
    }

    @Override
    protected boolean Q_3581_n() {
        return true;
    }

    @Override
    protected boolean I_685_r() {
        return true;
    }

    public boolean P_1922_E(a_3913_L p_230292_1_) {
        return true;
    }

    @Override
    public Z_1993_T u_1723_Y(Z_1993_T shootable) {
        if (shootable.J_1907_R() instanceof ProjectileWeaponItem) {
            Predicate<Z_1993_T> predicate = ((ProjectileWeaponItem)shootable.J_1907_R()).v_4262_N();
            Z_1993_T itemstack = ProjectileWeaponItem.n_1700_B(this, predicate);
            return itemstack.n_1700_B() ? new Z_1993_T(Items.g_24_p) : itemstack;
        }
        return Z_1993_T.J_1907_R;
    }
}



