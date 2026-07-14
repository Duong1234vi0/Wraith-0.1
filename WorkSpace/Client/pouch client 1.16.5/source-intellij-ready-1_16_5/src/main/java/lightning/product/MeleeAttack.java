/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.S_50_d;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_530_i;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import lightning.product.x_1688_C;

public class MeleeAttack
extends Behavior<Z_530_i> {
    private final int n_1700_B;

    public MeleeAttack(int cooldown) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.M_182_A, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = cooldown;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i owner) {
        r_4811_B livingentity = this.J_1907_R(owner);
        return !this.n_1700_B(owner) && a_3236_r.R_4764_Y(owner, livingentity) && a_3236_r.J_1907_R(owner, livingentity);
    }

    private boolean n_1700_B(Z_530_i mob) {
        return ((r_4811_B)mob).n_1700_B((q_1613_l item) -> item instanceof ProjectileWeaponItem && mob.n_1700_B((ProjectileWeaponItem)item));
    }

    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        r_4811_B livingentity = this.J_1907_R(entityIn);
        a_3236_r.n_1700_B((r_4811_B)entityIn, livingentity);
        entityIn.n_1700_B(x_1688_C.n_1700_B);
        entityIn.q_2307_F(livingentity);
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.M_182_A, true, this.n_1700_B);
    }

    private r_4811_B J_1907_R(Z_530_i mob) {
        return mob.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


