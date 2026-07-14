/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Function;
import lightning.product.WalkTarget;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.Position;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetWalkTargetAwayFrom<T>
extends Behavior<PathfinderMob> {
    private final MemoryModuleType<T> n_1700_B;
    private final float R_4764_Y;
    private final int G_564_y;
    private final Function<T, e_2866_D> P_1922_E;

    public SetWalkTargetAwayFrom(MemoryModuleType<T> p_i231533_1_, float p_i231533_2_, int p_i231533_3_, boolean p_i231533_4_, Function<T, e_2866_D> p_i231533_5_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)(p_i231533_4_ ? S_50_d.R_4764_Y : S_50_d.J_1907_R)), p_i231533_1_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231533_1_;
        this.R_4764_Y = p_i231533_2_;
        this.G_564_y = p_i231533_3_;
        this.P_1922_E = p_i231533_5_;
    }

    public static SetWalkTargetAwayFrom<c_1514_x> n_1700_B(MemoryModuleType<c_1514_x> p_233963_0_, float p_233963_1_, int p_233963_2_, boolean p_233963_3_) {
        return new SetWalkTargetAwayFrom<c_1514_x>(p_233963_0_, p_233963_1_, p_233963_2_, p_233963_3_, e_2866_D::R_4764_Y);
    }

    public static SetWalkTargetAwayFrom<? extends N_4263_v> J_1907_R(MemoryModuleType<? extends N_4263_v> p_233965_0_, float p_233965_1_, int p_233965_2_, boolean p_233965_3_) {
        return new SetWalkTargetAwayFrom<N_4263_v>(p_233965_0_, p_233965_1_, p_233965_2_, p_233965_3_, N_4263_v::s_4990_V);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, PathfinderMob owner) {
        return this.J_1907_R(owner) ? false : owner.s_4990_V().n_1700_B((Position)this.n_1700_B(owner), (double)this.G_564_y);
    }

    private e_2866_D n_1700_B(PathfinderMob p_233961_1_) {
        return this.P_1922_E.apply(p_233961_1_.y_1945_D().R_4764_Y(this.n_1700_B).get());
    }

    private boolean J_1907_R(PathfinderMob p_233964_1_) {
        e_2866_D vector3d1;
        if (!p_233964_1_.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p)) {
            return false;
        }
        WalkTarget walktarget = p_233964_1_.y_1945_D().R_4764_Y(MemoryModuleType.P_4830_p).get();
        if (walktarget.J_1907_R() != this.R_4764_Y) {
            return false;
        }
        e_2866_D vector3d = walktarget.n_1700_B().n_1700_B().G_564_y(p_233964_1_.s_4990_V());
        return vector3d.J_1907_R(vector3d1 = this.n_1700_B(p_233964_1_).G_564_y(p_233964_1_.s_4990_V())) < 0.0;
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        SetWalkTargetAwayFrom.n_1700_B(entityIn, this.n_1700_B(entityIn), this.R_4764_Y);
    }

    private static void n_1700_B(PathfinderMob p_233962_0_, e_2866_D p_233962_1_, float p_233962_2_) {
        for (int i = 0; i < 10; ++i) {
            e_2866_D vector3d = W_3371_U.G_564_y(p_233962_0_, 16, 7, p_233962_1_);
            if (vector3d == null) continue;
            p_233962_0_.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(vector3d, p_233962_2_, 0));
            return;
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


