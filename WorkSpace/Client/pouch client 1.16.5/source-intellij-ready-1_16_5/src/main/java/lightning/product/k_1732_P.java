/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.L_2225_p;
import lightning.product.N_1216_z;
import lightning.product.N_4263_v;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.Z_1993_T;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class k_1732_P
extends Behavior<L_2225_p> {
    private Set<q_1613_l> n_1700_B = ImmutableSet.of();

    public k_1732_P() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        return a_3236_r.n_1700_B(owner.y_1945_D(), MemoryModuleType.t_1786_h, t_5_h.RealmsDefaultUncaughtExceptionHandler);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.n_1700_B(worldIn, entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        L_2225_p villagerentity = (L_2225_p)entityIn.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).get();
        a_3236_r.n_1700_B((r_4811_B)entityIn, (r_4811_B)villagerentity, 0.5f);
        this.n_1700_B = k_1732_P.n_1700_B(entityIn, villagerentity);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        L_2225_p villagerentity = (L_2225_p)owner.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).get();
        if (!(owner.G_564_y((N_4263_v)villagerentity) > 5.0)) {
            a_3236_r.n_1700_B((r_4811_B)owner, (r_4811_B)villagerentity, 0.5f);
            owner.n_1700_B(worldIn, villagerentity, gameTime);
            if (owner.U_3758_B() && (owner.c_2086_l().J_1907_R() == VillagerProfession.u_1723_Y || villagerentity.y_3417_N())) {
                k_1732_P.n_1700_B(owner, L_2225_p.Q_4569_t.keySet(), villagerentity);
            }
            if (villagerentity.c_2086_l().J_1907_R() == VillagerProfession.u_1723_Y && owner.J_3635_s().n_1700_B(Items.V_3441_j) > Items.V_3441_j.u_2550_I() / 2) {
                k_1732_P.n_1700_B(owner, (Set<q_1613_l>)ImmutableSet.of((Object)Items.V_3441_j), villagerentity);
            }
            if (!this.n_1700_B.isEmpty() && owner.J_3635_s().n_1700_B(this.n_1700_B)) {
                k_1732_P.n_1700_B(owner, this.n_1700_B, villagerentity);
            }
        }
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.t_1786_h);
    }

    private static Set<q_1613_l> n_1700_B(L_2225_p p_220585_0_, L_2225_p p_220585_1_) {
        ImmutableSet<q_1613_l> immutableset = p_220585_1_.c_2086_l().J_1907_R().J_1907_R();
        ImmutableSet<q_1613_l> immutableset1 = p_220585_0_.c_2086_l().J_1907_R().J_1907_R();
        return immutableset.stream().filter(p_220587_1_ -> !immutableset1.contains(p_220587_1_)).collect(Collectors.toSet());
    }

    private static void n_1700_B(L_2225_p p_220586_0_, Set<q_1613_l> p_220586_1_, r_4811_B p_220586_2_) {
        N_1216_z inventory = p_220586_0_.J_3635_s();
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int i = 0; i < inventory.Y_259_p(); ++i) {
            int j;
            q_1613_l item;
            Z_1993_T itemstack1 = inventory.s_956_w(i);
            if (itemstack1.n_1700_B() || !p_220586_1_.contains(item = itemstack1.J_1907_R())) continue;
            if (itemstack1.t_4043_B() > itemstack1.R_4764_Y() / 2) {
                j = itemstack1.t_4043_B() / 2;
            } else {
                if (itemstack1.t_4043_B() <= 24) continue;
                j = itemstack1.t_4043_B() - 24;
            }
            itemstack1.v_4262_N(j);
            itemstack = new Z_1993_T(item, j);
            break;
        }
        if (!itemstack.n_1700_B()) {
            a_3236_r.n_1700_B((r_4811_B)p_220586_0_, itemstack, p_220586_2_.s_4990_V());
        }
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


