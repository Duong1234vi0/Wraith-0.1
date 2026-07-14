/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.E_4668_a;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class r_1628_p
extends Behavior<PathfinderMob> {
    public r_1628_p() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.t_148_a, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.R_4764_Y)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        return worldIn.e_4240_b().nextInt(10) == 0 && this.P_1922_E(owner);
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        r_4811_B livingentity = this.J_1907_R((r_4811_B)entityIn);
        if (livingentity != null) {
            this.n_1700_B(worldIn, entityIn, livingentity);
        } else {
            Optional<r_4811_B> optional = this.J_1907_R(entityIn);
            if (optional.isPresent()) {
                r_1628_p.n_1700_B(entityIn, optional.get());
            } else {
                this.n_1700_B(entityIn).ifPresent(p_220506_1_ -> r_1628_p.n_1700_B(entityIn, p_220506_1_));
            }
        }
    }

    private void n_1700_B(e_3591_l p_220508_1_, PathfinderMob p_220508_2_, r_4811_B p_220508_3_) {
        for (int i = 0; i < 10; ++i) {
            e_2866_D vector3d = W_3371_U.J_1907_R(p_220508_2_, 20, 8);
            if (vector3d == null || !p_220508_1_.q_2307_F(new c_1514_x(vector3d))) continue;
            p_220508_2_.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(vector3d, 0.6f, 0));
            return;
        }
    }

    private static void n_1700_B(PathfinderMob p_220498_0_, r_4811_B p_220498_1_) {
        E_4668_a<?> brain = p_220498_0_.y_1945_D();
        brain.n_1700_B(MemoryModuleType.t_1786_h, p_220498_1_);
        brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(p_220498_1_, true));
        brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new o_4722_d(p_220498_1_, false), 0.6f, 1));
    }

    private Optional<r_4811_B> n_1700_B(PathfinderMob p_220510_1_) {
        return this.G_564_y(p_220510_1_).stream().findAny();
    }

    private Optional<r_4811_B> J_1907_R(PathfinderMob p_220497_1_) {
        Map<r_4811_B, Integer> map = this.R_4764_Y(p_220497_1_);
        return map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).filter(p_220504_0_ -> (Integer)p_220504_0_.getValue() > 0 && (Integer)p_220504_0_.getValue() <= 5).map(Map.Entry::getKey).findFirst();
    }

    private Map<r_4811_B, Integer> R_4764_Y(PathfinderMob p_220505_1_) {
        HashMap map = Maps.newHashMap();
        this.G_564_y(p_220505_1_).stream().filter(this::R_4764_Y).forEach(p_220509_2_ -> {
            Integer integer = map.compute(this.n_1700_B((r_4811_B)p_220509_2_), (p_220511_0_, p_220511_1_) -> p_220511_1_ == null ? 1 : p_220511_1_ + 1);
        });
        return map;
    }

    private List<r_4811_B> G_564_y(PathfinderMob p_220503_1_) {
        return p_220503_1_.y_1945_D().R_4764_Y(MemoryModuleType.t_148_a).get();
    }

    private r_4811_B n_1700_B(r_4811_B p_220495_1_) {
        return p_220495_1_.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).get();
    }

    @Nullable
    private r_4811_B J_1907_R(r_4811_B p_220500_1_) {
        return p_220500_1_.y_1945_D().R_4764_Y(MemoryModuleType.t_148_a).get().stream().filter(p_220507_2_ -> this.n_1700_B(p_220500_1_, (r_4811_B)p_220507_2_)).findAny().orElse(null);
    }

    private boolean R_4764_Y(r_4811_B p_220502_1_) {
        return p_220502_1_.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).isPresent();
    }

    private boolean n_1700_B(r_4811_B p_220499_1_, r_4811_B p_220499_2_) {
        return p_220499_2_.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).filter(p_220496_1_ -> p_220496_1_ == p_220499_1_).isPresent();
    }

    private boolean P_1922_E(PathfinderMob p_220501_1_) {
        return p_220501_1_.y_1945_D().n_1700_B(MemoryModuleType.t_148_a);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


