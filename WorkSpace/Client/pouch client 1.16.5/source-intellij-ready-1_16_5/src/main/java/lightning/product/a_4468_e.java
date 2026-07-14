/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.K_4074_S;
import lightning.product.S_1431_H;
import lightning.product.S_50_d;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class a_4468_e
extends Behavior<r_4811_B> {
    @Nullable
    private D_1436_R n_1700_B;
    private int R_4764_Y;

    public a_4468_e() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Y_601_j, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.Q_2552_b, (Object)((Object)S_50_d.R_4764_Y)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        b_1722_e path = owner.y_1945_D().R_4764_Y(MemoryModuleType.Y_601_j).get();
        if (!path.J_1907_R() && !path.R_4764_Y()) {
            if (!Objects.equals(this.n_1700_B, path.w_1484_f())) {
                this.R_4764_Y = 20;
                return true;
            }
            if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
            }
            return this.R_4764_Y == 0;
        }
        return false;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        S_1431_H doorblock1;
        c_1514_x blockpos1;
        K_4074_S blockstate1;
        b_1722_e path = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.Y_601_j).get();
        this.n_1700_B = path.w_1484_f();
        D_1436_R pathpoint = path.t_148_a();
        D_1436_R pathpoint1 = path.w_1484_f();
        c_1514_x blockpos = pathpoint.R_4764_Y();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.n_1700_B(BlockTags.w_1484_f)) {
            S_1431_H doorblock = (S_1431_H)blockstate.J_1907_R();
            if (!doorblock.w_1484_f(blockstate)) {
                doorblock.n_1700_B((b_4507_u)worldIn, blockstate, blockpos, true);
            }
            this.R_4764_Y(worldIn, entityIn, blockpos);
        }
        if ((blockstate1 = worldIn.getBlockState(blockpos1 = pathpoint1.R_4764_Y())).n_1700_B(BlockTags.w_1484_f) && !(doorblock1 = (S_1431_H)blockstate1.J_1907_R()).w_1484_f(blockstate1)) {
            doorblock1.n_1700_B((b_4507_u)worldIn, blockstate1, blockpos1, true);
            this.R_4764_Y(worldIn, entityIn, blockpos1);
        }
        a_4468_e.n_1700_B(worldIn, entityIn, pathpoint, pathpoint1);
    }

    public static void n_1700_B(e_3591_l p_242294_0_, r_4811_B p_242294_1_, @Nullable D_1436_R p_242294_2_, @Nullable D_1436_R p_242294_3_) {
        E_4668_a<Set<F_427_K>> brain = p_242294_1_.y_1945_D();
        if (brain.n_1700_B(MemoryModuleType.Q_2552_b)) {
            Iterator<F_427_K> iterator = brain.R_4764_Y(MemoryModuleType.Q_2552_b).get().iterator();
            while (iterator.hasNext()) {
                F_427_K globalpos = iterator.next();
                c_1514_x blockpos = globalpos.J_1907_R();
                if (p_242294_2_ != null && p_242294_2_.R_4764_Y().equals(blockpos) || p_242294_3_ != null && p_242294_3_.R_4764_Y().equals(blockpos)) continue;
                if (a_4468_e.n_1700_B(p_242294_0_, p_242294_1_, globalpos)) {
                    iterator.remove();
                    continue;
                }
                K_4074_S blockstate = p_242294_0_.getBlockState(blockpos);
                if (!blockstate.n_1700_B(BlockTags.w_1484_f)) {
                    iterator.remove();
                    continue;
                }
                S_1431_H doorblock = (S_1431_H)blockstate.J_1907_R();
                if (!doorblock.w_1484_f(blockstate)) {
                    iterator.remove();
                    continue;
                }
                if (a_4468_e.n_1700_B(p_242294_0_, p_242294_1_, blockpos)) {
                    iterator.remove();
                    continue;
                }
                doorblock.n_1700_B((b_4507_u)p_242294_0_, blockstate, blockpos, false);
                iterator.remove();
            }
        }
    }

    private static boolean n_1700_B(e_3591_l p_242295_0_, r_4811_B p_242295_1_, c_1514_x p_242295_2_) {
        E_4668_a<List<r_4811_B>> brain = p_242295_1_.y_1945_D();
        return !brain.n_1700_B(MemoryModuleType.v_4262_N) ? false : brain.R_4764_Y(MemoryModuleType.v_4262_N).get().stream().filter(p_242298_1_ -> p_242298_1_.f_4016_n() == p_242295_1_.f_4016_n()).filter(p_242299_1_ -> p_242295_2_.withinDistance(p_242299_1_.s_4990_V(), 2.0)).anyMatch(p_242297_2_ -> a_4468_e.J_1907_R(p_242295_0_, p_242297_2_, p_242295_2_));
    }

    private static boolean J_1907_R(e_3591_l p_242300_0_, r_4811_B p_242300_1_, c_1514_x p_242300_2_) {
        if (!p_242300_1_.y_1945_D().n_1700_B(MemoryModuleType.Y_601_j)) {
            return false;
        }
        b_1722_e path = p_242300_1_.y_1945_D().R_4764_Y(MemoryModuleType.Y_601_j).get();
        if (path.R_4764_Y()) {
            return false;
        }
        D_1436_R pathpoint = path.t_148_a();
        if (pathpoint == null) {
            return false;
        }
        D_1436_R pathpoint1 = path.w_1484_f();
        return p_242300_2_.equals(pathpoint.R_4764_Y()) || p_242300_2_.equals(pathpoint1.R_4764_Y());
    }

    private static boolean n_1700_B(e_3591_l p_242296_0_, r_4811_B p_242296_1_, F_427_K p_242296_2_) {
        return p_242296_2_.n_1700_B() != p_242296_0_.g_2268_R() || !p_242296_2_.J_1907_R().withinDistance(p_242296_1_.s_4990_V(), 2.0);
    }

    private void R_4764_Y(e_3591_l p_242301_1_, r_4811_B p_242301_2_, c_1514_x p_242301_3_) {
        E_4668_a<?> brain = p_242301_2_.y_1945_D();
        F_427_K globalpos = F_427_K.n_1700_B(p_242301_1_.g_2268_R(), p_242301_3_);
        if (brain.R_4764_Y(MemoryModuleType.Q_2552_b).isPresent()) {
            brain.R_4764_Y(MemoryModuleType.Q_2552_b).get().add(globalpos);
        } else {
            brain.n_1700_B(MemoryModuleType.Q_2552_b, Sets.newHashSet((Object[])new F_427_K[]{globalpos}));
        }
    }
}


