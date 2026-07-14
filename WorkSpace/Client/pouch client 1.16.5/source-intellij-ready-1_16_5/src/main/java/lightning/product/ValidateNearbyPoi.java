/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Predicate;
import lightning.product.DebugPackets;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.S_50_d;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.BlockTags;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class ValidateNearbyPoi
extends Behavior<r_4811_B> {
    private final MemoryModuleType<F_427_K> n_1700_B;
    private final Predicate<q_2232_A> R_4764_Y;

    public ValidateNearbyPoi(q_2232_A p_i50338_1_, MemoryModuleType<F_427_K> p_i50338_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(p_i50338_2_, (Object)((Object)S_50_d.n_1700_B)));
        this.R_4764_Y = p_i50338_1_.J_1907_R();
        this.n_1700_B = p_i50338_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        F_427_K globalpos = owner.y_1945_D().R_4764_Y(this.n_1700_B).get();
        return worldIn.g_2268_R() == globalpos.n_1700_B() && globalpos.J_1907_R().withinDistance(owner.s_4990_V(), 16.0);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        F_427_K globalpos = brain.R_4764_Y(this.n_1700_B).get();
        c_1514_x blockpos = globalpos.J_1907_R();
        e_3591_l serverworld = worldIn.T_2506_i().n_1700_B(globalpos.n_1700_B());
        if (serverworld != null && !this.n_1700_B(serverworld, blockpos)) {
            if (this.n_1700_B(serverworld, blockpos, entityIn)) {
                brain.J_1907_R(this.n_1700_B);
                worldIn.p_178_J().J_1907_R(blockpos);
                DebugPackets.R_4764_Y(worldIn, blockpos);
            }
        } else {
            brain.J_1907_R(this.n_1700_B);
        }
    }

    private boolean n_1700_B(e_3591_l world, c_1514_x pos, r_4811_B p_223019_3_) {
        K_4074_S blockstate = world.getBlockState(pos);
        return blockstate.J_1907_R().n_1700_B(BlockTags.d_2461_k) && blockstate.R_4764_Y(J_2868_p.h_1847_R) != false && !p_223019_3_.z_2372_L();
    }

    @Override
    private boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        return !world.p_178_J().n_1700_B(pos, this.R_4764_Y);
    }
}


