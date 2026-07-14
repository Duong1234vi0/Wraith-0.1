/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.a_3236_r;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class VillageBoundRandomStroll
extends Behavior<PathfinderMob> {
    private final float n_1700_B;
    private final int R_4764_Y;
    private final int G_564_y;

    public VillageBoundRandomStroll(float speedIn) {
        this(speedIn, 10, 7);
    }

    public VillageBoundRandomStroll(float speedIn, int maxDistanceXZ, int maxDistanceY) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = speedIn;
        this.R_4764_Y = maxDistanceXZ;
        this.G_564_y = maxDistanceY;
    }

    protected void n_1700_B(e_3591_l worldIn, PathfinderMob entityIn, long gameTimeIn) {
        c_1514_x blockpos = entityIn.b_2312_j();
        if (worldIn.q_2307_F(blockpos)) {
            this.n_1700_B(entityIn);
        } else {
            SectionPos sectionpos = SectionPos.n_1700_B(blockpos);
            SectionPos sectionpos1 = a_3236_r.n_1700_B(worldIn, sectionpos, 2);
            if (sectionpos1 != sectionpos) {
                this.n_1700_B(entityIn, sectionpos1);
            } else {
                this.n_1700_B(entityIn);
            }
        }
    }

    private void n_1700_B(PathfinderMob p_220594_1_, SectionPos p_220594_2_) {
        Optional<e_2866_D> optional = Optional.ofNullable(W_3371_U.J_1907_R(p_220594_1_, this.R_4764_Y, this.G_564_y, e_2866_D.R_4764_Y(p_220594_2_.u_2550_I())));
        p_220594_1_.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, optional.map(p_220596_1_ -> new WalkTarget((e_2866_D)p_220596_1_, this.n_1700_B, 0)));
    }

    private void n_1700_B(PathfinderMob p_220593_1_) {
        Optional<e_2866_D> optional = Optional.ofNullable(W_3371_U.J_1907_R(p_220593_1_, this.R_4764_Y, this.G_564_y));
        p_220593_1_.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, optional.map(p_220595_1_ -> new WalkTarget((e_2866_D)p_220595_1_, this.n_1700_B, 0)));
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (PathfinderMob)r_4811_B2, l);
    }
}


