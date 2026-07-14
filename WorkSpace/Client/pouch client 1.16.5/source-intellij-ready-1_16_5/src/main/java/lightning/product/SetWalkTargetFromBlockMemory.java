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
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.WalkTarget;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetWalkTargetFromBlockMemory
extends Behavior<L_2225_p> {
    private final MemoryModuleType<F_427_K> n_1700_B;
    private final float R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;

    public SetWalkTargetFromBlockMemory(MemoryModuleType<F_427_K> p_i51501_1_, float p_i51501_2_, int p_i51501_3_, int p_i51501_4_, int p_i51501_5_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Y_1740_V, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), p_i51501_1_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i51501_1_;
        this.R_4764_Y = p_i51501_2_;
        this.G_564_y = p_i51501_3_;
        this.P_1922_E = p_i51501_4_;
        this.u_1723_Y = p_i51501_5_;
    }

    private void n_1700_B(L_2225_p p_225457_1_, long p_225457_2_) {
        E_4668_a<L_2225_p> brain = p_225457_1_.y_1945_D();
        p_225457_1_.n_1700_B(this.n_1700_B);
        brain.J_1907_R(this.n_1700_B);
        brain.n_1700_B(MemoryModuleType.Y_1740_V, Long.valueOf(p_225457_2_));
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        E_4668_a<L_2225_p> brain = entityIn.y_1945_D();
        brain.R_4764_Y(this.n_1700_B).ifPresent(p_220545_6_ -> {
            if (!this.n_1700_B(worldIn, (F_427_K)p_220545_6_) && !this.n_1700_B(worldIn, entityIn)) {
                if (this.n_1700_B(entityIn, (F_427_K)p_220545_6_)) {
                    int i;
                    e_2866_D vector3d = null;
                    int j = 1000;
                    for (i = 0; i < 1000 && (vector3d == null || this.n_1700_B(entityIn, F_427_K.n_1700_B(worldIn.g_2268_R(), new c_1514_x(vector3d)))); ++i) {
                        vector3d = W_3371_U.J_1907_R(entityIn, 15, 7, e_2866_D.R_4764_Y(p_220545_6_.J_1907_R()));
                    }
                    if (i == 1000) {
                        this.n_1700_B(entityIn, gameTimeIn);
                        return;
                    }
                    brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(vector3d, this.R_4764_Y, this.G_564_y));
                } else if (!this.n_1700_B(worldIn, entityIn, (F_427_K)p_220545_6_)) {
                    brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(p_220545_6_.J_1907_R(), this.R_4764_Y, this.G_564_y));
                }
            } else {
                this.n_1700_B(entityIn, gameTimeIn);
            }
        });
    }

    @Override
    private boolean n_1700_B(e_3591_l p_223017_1_, L_2225_p p_223017_2_) {
        Optional<Long> optional = p_223017_2_.y_1945_D().R_4764_Y(MemoryModuleType.Y_1740_V);
        if (optional.isPresent()) {
            return p_223017_1_.X_933_l() - optional.get() > (long)this.u_1723_Y;
        }
        return false;
    }

    private boolean n_1700_B(L_2225_p p_242304_1_, F_427_K p_242304_2_) {
        return p_242304_2_.J_1907_R().manhattanDistance(p_242304_1_.b_2312_j()) > this.P_1922_E;
    }

    @Override
    private boolean n_1700_B(e_3591_l p_242303_1_, F_427_K p_242303_2_) {
        return p_242303_2_.n_1700_B() != p_242303_1_.g_2268_R();
    }

    private boolean n_1700_B(e_3591_l p_220547_1_, L_2225_p p_220547_2_, F_427_K p_220547_3_) {
        return p_220547_3_.n_1700_B() == p_220547_1_.g_2268_R() && p_220547_3_.J_1907_R().manhattanDistance(p_220547_2_.b_2312_j()) <= this.G_564_y;
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


