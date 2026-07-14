/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import lightning.product.F_427_K;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.N_1216_z;
import lightning.product.WorkAtPoi;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_648_i;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.MemoryModuleType;

public class WorkAtComposter
extends WorkAtPoi {
    private static final List<q_1613_l> n_1700_B = ImmutableList.of((Object)Items.G_4691_Q, (Object)Items.MushroomBlock);

    @Override
    protected void n_1700_B(e_3591_l world, L_2225_p villager) {
        F_427_K globalpos;
        K_4074_S blockstate;
        Optional<F_427_K> optional = villager.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y);
        if (optional.isPresent() && (blockstate = world.getBlockState((globalpos = optional.get()).J_1907_R())).n_1700_B(a_3742_W.P_2068_y)) {
            this.n_1700_B(villager);
            this.n_1700_B(world, villager, globalpos, blockstate);
        }
    }

    private void n_1700_B(e_3591_l world, L_2225_p villager, F_427_K p_234016_3_, K_4074_S state) {
        c_1514_x blockpos = p_234016_3_.J_1907_R();
        if (state.R_4764_Y(a_648_i.P_4830_p) == 8) {
            state = a_648_i.R_4764_Y(state, world, blockpos);
        }
        int i = 20;
        int j = 10;
        int[] aint = new int[n_1700_B.size()];
        N_1216_z inventory = villager.J_3635_s();
        int k = inventory.Y_259_p();
        K_4074_S blockstate = state;
        for (int l = k - 1; l >= 0 && i > 0; --l) {
            int k1;
            Z_1993_T itemstack = inventory.s_956_w(l);
            int i1 = n_1700_B.indexOf(itemstack.J_1907_R());
            if (i1 == -1) continue;
            int j1 = itemstack.t_4043_B();
            aint[i1] = k1 = aint[i1] + j1;
            int l1 = Math.min(Math.min(k1 - 10, i), j1);
            if (l1 <= 0) continue;
            i -= l1;
            for (int i2 = 0; i2 < l1; ++i2) {
                if ((blockstate = a_648_i.n_1700_B(blockstate, world, itemstack, blockpos)).R_4764_Y(a_648_i.P_4830_p) != 7) continue;
                this.n_1700_B(world, state, blockpos, blockstate);
                return;
            }
        }
        this.n_1700_B(world, state, blockpos, blockstate);
    }

    private void n_1700_B(e_3591_l p_242308_1_, K_4074_S p_242308_2_, c_1514_x p_242308_3_, K_4074_S p_242308_4_) {
        p_242308_1_.R_4764_Y(1500, p_242308_3_, p_242308_4_ != p_242308_2_ ? 1 : 0);
    }

    private void n_1700_B(L_2225_p villager) {
        N_1216_z inventory = villager.J_3635_s();
        if (inventory.n_1700_B(Items.m_3828_C) <= 36) {
            int i = inventory.n_1700_B(Items.V_3441_j);
            int j = 3;
            int k = 3;
            int l = Math.min(3, i / 3);
            if (l != 0) {
                int i1 = l * 3;
                inventory.n_1700_B(Items.V_3441_j, i1);
                Z_1993_T itemstack = inventory.n_1700_B(new Z_1993_T(Items.m_3828_C, l));
                if (!itemstack.n_1700_B()) {
                    villager.n_1700_B(itemstack, 0.5f);
                }
            }
        }
    }
}


