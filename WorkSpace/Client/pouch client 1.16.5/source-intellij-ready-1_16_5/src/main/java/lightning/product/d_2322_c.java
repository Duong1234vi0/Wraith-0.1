/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.A_4919_q;
import lightning.product.A_69_b;
import lightning.product.C_4998_y;
import lightning.product.E_4668_a;
import lightning.product.I_3700_V;
import lightning.product.I_408_V;
import lightning.product.K_4074_S;
import lightning.product.Hoglin;
import lightning.product.S_3014_o;
import lightning.product.Z_530_i;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Sensor;
import lightning.product.AbstractPiglin;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import lightning.product.WitherSkeleton;

public class d_2322_c
extends Sensor<r_4811_B> {
    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.w_1484_f, MemoryModuleType.v_4262_N, MemoryModuleType.v_4276_D, MemoryModuleType.H_1990_U, MemoryModuleType.z_1333_t, MemoryModuleType.X_933_l, (Object[])new MemoryModuleType[]{MemoryModuleType.Z_976_R, MemoryModuleType.c_4037_x, MemoryModuleType.N_2525_X, MemoryModuleType.s_2632_s, MemoryModuleType.l_1233_K, MemoryModuleType.r_715_M});
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, r_4811_B entityIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.n_1700_B(MemoryModuleType.r_715_M, d_2322_c.R_4764_Y(worldIn, entityIn));
        Optional<Object> optional = Optional.empty();
        Optional<Object> optional1 = Optional.empty();
        Optional<Object> optional2 = Optional.empty();
        Optional<Object> optional3 = Optional.empty();
        Optional<Object> optional4 = Optional.empty();
        Optional<Object> optional5 = Optional.empty();
        Optional<Object> optional6 = Optional.empty();
        int i = 0;
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        for (r_4811_B livingentity : brain.R_4764_Y(MemoryModuleType.w_1484_f).orElse((List<r_4811_B>)ImmutableList.of())) {
            if (livingentity instanceof Hoglin) {
                Hoglin hoglinentity = (Hoglin)livingentity;
                if (hoglinentity.d_() && !optional2.isPresent()) {
                    optional2 = Optional.of(hoglinentity);
                    continue;
                }
                if (!hoglinentity.V_1176_p()) continue;
                ++i;
                if (optional1.isPresent() || !hoglinentity.J_3635_s()) continue;
                optional1 = Optional.of(hoglinentity);
                continue;
            }
            if (livingentity instanceof S_3014_o) {
                list.add((S_3014_o)livingentity);
                continue;
            }
            if (livingentity instanceof A_69_b) {
                A_69_b piglinentity = (A_69_b)livingentity;
                if (piglinentity.d_() && !optional3.isPresent()) {
                    optional3 = Optional.of(piglinentity);
                    continue;
                }
                if (!piglinentity.y_2447_C()) continue;
                list.add(piglinentity);
                continue;
            }
            if (livingentity instanceof a_3913_L) {
                a_3913_L playerentity = (a_3913_L)livingentity;
                if (!optional5.isPresent() && I_408_V.u_1723_Y.test(livingentity) && !A_4919_q.n_1700_B(playerentity)) {
                    optional5 = Optional.of(playerentity);
                }
                if (optional6.isPresent() || playerentity.d_2461_k() || !A_4919_q.J_1907_R(playerentity)) continue;
                optional6 = Optional.of(playerentity);
                continue;
            }
            if (optional.isPresent() || !(livingentity instanceof WitherSkeleton) && !(livingentity instanceof I_3700_V)) {
                if (optional4.isPresent() || !A_4919_q.n_1700_B(livingentity.f_4016_n())) continue;
                optional4 = Optional.of(livingentity);
                continue;
            }
            optional = Optional.of((Z_530_i)livingentity);
        }
        for (r_4811_B livingentity1 : brain.R_4764_Y(MemoryModuleType.v_4262_N).orElse((List<r_4811_B>)ImmutableList.of())) {
            if (!(livingentity1 instanceof AbstractPiglin) || !((AbstractPiglin)livingentity1).y_2447_C()) continue;
            list1.add((AbstractPiglin)livingentity1);
        }
        brain.n_1700_B(MemoryModuleType.v_4276_D, optional);
        brain.n_1700_B(MemoryModuleType.X_933_l, optional1);
        brain.n_1700_B(MemoryModuleType.Z_976_R, optional2);
        brain.n_1700_B(MemoryModuleType.D_4792_h, optional4);
        brain.n_1700_B(MemoryModuleType.H_1990_U, optional5);
        brain.n_1700_B(MemoryModuleType.z_1333_t, optional6);
        brain.n_1700_B(MemoryModuleType.N_2525_X, list1);
        brain.n_1700_B(MemoryModuleType.c_4037_x, list);
        brain.n_1700_B(MemoryModuleType.s_2632_s, Integer.valueOf(list.size()));
        brain.n_1700_B(MemoryModuleType.l_1233_K, Integer.valueOf(i));
    }

    private static Optional<c_1514_x> R_4764_Y(e_3591_l world, r_4811_B livingEntity) {
        return c_1514_x.getClosestMatchingPosition(livingEntity.b_2312_j(), 8, 4, pos -> d_2322_c.n_1700_B(world, pos));
    }

    private static boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        K_4074_S blockstate = world.getBlockState(pos);
        boolean flag = blockstate.n_1700_B(BlockTags.z_4693_k);
        return flag && blockstate.n_1700_B(a_3742_W.y_4842_Z) ? C_4998_y.w_1484_f(blockstate) : flag;
    }
}


