/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.Target;
import lightning.product.M_3179_b;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.NodeEvaluator;

public class D_3856_V {
    private final D_1436_R[] n_1700_B = new D_1436_R[32];
    private final int J_1907_R;
    private final NodeEvaluator R_4764_Y;
    private final M_3179_b G_564_y = new M_3179_b();

    public D_3856_V(NodeEvaluator p_i51280_1_, int p_i51280_2_) {
        this.R_4764_Y = p_i51280_1_;
        this.J_1907_R = p_i51280_2_;
    }

    @Nullable
    public b_1722_e n_1700_B(PathNavigationRegion p_227478_1_, Z_530_i p_227478_2_, Set<c_1514_x> p_227478_3_, float p_227478_4_, int p_227478_5_, float p_227478_6_) {
        this.G_564_y.n_1700_B();
        this.R_4764_Y.n_1700_B(p_227478_1_, p_227478_2_);
        D_1436_R pathpoint = this.R_4764_Y.J_1907_R();
        Map<Target, c_1514_x> map = p_227478_3_.stream().collect(Collectors.toMap(p_224782_1_ -> this.R_4764_Y.n_1700_B((double)p_224782_1_.getX(), (double)p_224782_1_.getY(), (double)p_224782_1_.getZ()), Function.identity()));
        b_1722_e path = this.n_1700_B(pathpoint, map, p_227478_4_, p_227478_5_, p_227478_6_);
        this.R_4764_Y.n_1700_B();
        return path;
    }

    @Nullable
    private b_1722_e n_1700_B(D_1436_R p_227479_1_, Map<Target, c_1514_x> p_227479_2_, float p_227479_3_, int p_227479_4_, float p_227479_5_) {
        Set<Target> set = p_227479_2_.keySet();
        p_227479_1_.P_1922_E = 0.0f;
        p_227479_1_.v_4262_N = p_227479_1_.u_1723_Y = this.n_1700_B(p_227479_1_, set);
        this.G_564_y.n_1700_B();
        this.G_564_y.n_1700_B(p_227479_1_);
        ImmutableSet set1 = ImmutableSet.of();
        int i = 0;
        HashSet set2 = Sets.newHashSetWithExpectedSize((int)set.size());
        int j = (int)((float)this.J_1907_R * p_227479_5_);
        while (!this.G_564_y.R_4764_Y() && ++i < j) {
            D_1436_R pathpoint = this.G_564_y.J_1907_R();
            pathpoint.t_148_a = true;
            for (Target flaggedpathpoint : set) {
                if (!(pathpoint.R_4764_Y(flaggedpathpoint) <= (float)p_227479_4_)) continue;
                flaggedpathpoint.J_1907_R();
                set2.add(flaggedpathpoint);
            }
            if (!set2.isEmpty()) break;
            if (pathpoint.n_1700_B(p_227479_1_) >= p_227479_3_) continue;
            int k = this.R_4764_Y.n_1700_B(this.n_1700_B, pathpoint);
            for (int l = 0; l < k; ++l) {
                D_1436_R pathpoint1 = this.n_1700_B[l];
                float f = pathpoint.n_1700_B(pathpoint1);
                pathpoint1.s_956_w = pathpoint.s_956_w + f;
                float f1 = pathpoint.P_1922_E + f + pathpoint1.u_2550_I;
                if (!(pathpoint1.s_956_w < p_227479_3_) || pathpoint1.G_564_y() && !(f1 < pathpoint1.P_1922_E)) continue;
                pathpoint1.w_1484_f = pathpoint;
                pathpoint1.P_1922_E = f1;
                pathpoint1.u_1723_Y = this.n_1700_B(pathpoint1, set) * 1.5f;
                if (pathpoint1.G_564_y()) {
                    this.G_564_y.n_1700_B(pathpoint1, pathpoint1.P_1922_E + pathpoint1.u_1723_Y);
                    continue;
                }
                pathpoint1.v_4262_N = pathpoint1.P_1922_E + pathpoint1.u_1723_Y;
                this.G_564_y.n_1700_B(pathpoint1);
            }
        }
        Optional<b_1722_e> optional = !set2.isEmpty() ? set2.stream().map(p_224778_2_ -> this.n_1700_B(p_224778_2_.n_1700_B(), (c_1514_x)p_227479_2_.get(p_224778_2_), true)).min(Comparator.comparingInt(b_1722_e::P_1922_E)) : set.stream().map(p_224777_2_ -> this.n_1700_B(p_224777_2_.n_1700_B(), (c_1514_x)p_227479_2_.get(p_224777_2_), false)).min(Comparator.comparingDouble(b_1722_e::h_1847_R).thenComparingInt(b_1722_e::P_1922_E));
        return !optional.isPresent() ? null : optional.get();
    }

    private float n_1700_B(D_1436_R p_224776_1_, Set<Target> p_224776_2_) {
        float f = Float.MAX_VALUE;
        for (Target flaggedpathpoint : p_224776_2_) {
            float f1 = p_224776_1_.n_1700_B(flaggedpathpoint);
            flaggedpathpoint.n_1700_B(f1, p_224776_1_);
            f = Math.min(f1, f);
        }
        return f;
    }

    private b_1722_e n_1700_B(D_1436_R p_224780_1_, c_1514_x p_224780_2_, boolean p_224780_3_) {
        ArrayList list = Lists.newArrayList();
        D_1436_R pathpoint = p_224780_1_;
        list.add(0, p_224780_1_);
        while (pathpoint.w_1484_f != null) {
            pathpoint = pathpoint.w_1484_f;
            list.add(0, pathpoint);
        }
        return new b_1722_e(list, p_224780_2_, p_224780_3_);
    }
}


