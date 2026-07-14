/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lightning.product.WalkTarget;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class InsideBrownianWalk
extends Behavior<PathfinderMob> {
    private final float n_1700_B;

    public InsideBrownianWalk(float p_i50364_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i50364_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        return !worldIn.canSeeSky(owner.b_2312_j());
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        c_1514_x blockpos = entityIn.b_2312_j();
        List list = c_1514_x.getAllInBox(blockpos.add(-1, -1, -1), blockpos.add(1, 1, 1)).map(c_1514_x::toImmutable).collect(Collectors.toList());
        Collections.shuffle(list);
        Optional<c_1514_x> optional = list.stream().filter(p_220428_1_ -> !worldIn.canSeeSky((c_1514_x)p_220428_1_)).filter(p_220427_2_ -> worldIn.n_1700_B((c_1514_x)p_220427_2_, (N_4263_v)entityIn)).filter(p_220429_2_ -> worldIn.u_1723_Y(entityIn)).findFirst();
        optional.ifPresent(p_220430_2_ -> entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget((c_1514_x)p_220430_2_, this.n_1700_B, 0)));
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


