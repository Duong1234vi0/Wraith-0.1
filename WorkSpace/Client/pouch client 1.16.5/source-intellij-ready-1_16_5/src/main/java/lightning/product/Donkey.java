/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.AgableMob;
import lightning.product.Animal;
import lightning.product.P_11_z;
import lightning.product.U_2534_D;
import lightning.product.SoundEvents;
import lightning.product.W_3443_Y;
import lightning.product.SoundEvent;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.Horse;
import lightning.product.t_5_h;

public class Donkey
extends W_3443_Y {
    public Donkey(t_5_h<? extends Donkey> p_i50239_1_, b_4507_u world) {
        super((t_5_h<? extends W_3443_Y>)p_i50239_1_, world);
    }

    @Override
    protected SoundEvent z_4693_k() {
        super.z_4693_k();
        return SoundEvents.v_165_F;
    }

    @Override
    protected SoundEvent KeyBindSetting() {
        super.KeyBindSetting();
        return SoundEvents.s_4990_V;
    }

    @Override
    protected SoundEvent u_796_y() {
        super.u_796_y();
        return SoundEvents.I_4348_c;
    }

    @Override
    @Nullable
    protected SoundEvent Setting() {
        return SoundEvents.O_3598_v;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        super.P_1922_E(damageSourceIn);
        return SoundEvents.X_2960_b;
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        if (!(otherAnimal instanceof Donkey) && !(otherAnimal instanceof Horse)) {
            return false;
        }
        return this.MultiBooleanSetting() && ((U_2534_D)otherAnimal).MultiBooleanSetting();
    }

    @Override
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        t_5_h<W_3443_Y> entitytype = p_241840_2_ instanceof Horse ? t_5_h.T_3594_S : t_5_h.Q_4569_t;
        U_2534_D abstracthorseentity = entitytype.n_1700_B(p_241840_1_);
        this.n_1700_B(p_241840_2_, abstracthorseentity);
        return abstracthorseentity;
    }
}


