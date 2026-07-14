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
import lightning.product.DebugPackets;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class YieldJobSite
extends Behavior<L_2225_p> {
    private final float n_1700_B;

    public YieldJobSite(float p_i231545_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.G_564_y, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.R_4764_Y, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.v_4262_N, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231545_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        if (owner.d_()) {
            return false;
        }
        return owner.c_2086_l().J_1907_R() == VillagerProfession.n_1700_B;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        c_1514_x blockpos = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y).get().J_1907_R();
        Optional<q_2232_A> optional = worldIn.p_178_J().R_4764_Y(blockpos);
        if (optional.isPresent()) {
            a_3236_r.n_1700_B(entityIn, p_234021_3_ -> this.n_1700_B((q_2232_A)optional.get(), (L_2225_p)p_234021_3_, blockpos)).findFirst().ifPresent(p_234023_4_ -> this.n_1700_B(worldIn, entityIn, (L_2225_p)p_234023_4_, blockpos, p_234023_4_.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y).isPresent()));
        }
    }

    private boolean n_1700_B(q_2232_A p_234018_1_, L_2225_p p_234018_2_, c_1514_x p_234018_3_) {
        boolean flag = p_234018_2_.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y).isPresent();
        if (flag) {
            return false;
        }
        Optional<F_427_K> optional = p_234018_2_.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y);
        VillagerProfession villagerprofession = p_234018_2_.c_2086_l().J_1907_R();
        if (p_234018_2_.c_2086_l().J_1907_R() != VillagerProfession.n_1700_B && villagerprofession.n_1700_B().J_1907_R().test(p_234018_1_)) {
            return !optional.isPresent() ? this.n_1700_B(p_234018_2_, p_234018_3_, p_234018_1_) : optional.get().J_1907_R().equals(p_234018_3_);
        }
        return false;
    }

    private void n_1700_B(e_3591_l p_234022_1_, L_2225_p p_234022_2_, L_2225_p p_234022_3_, c_1514_x p_234022_4_, boolean p_234022_5_) {
        this.n_1700_B(p_234022_2_);
        if (!p_234022_5_) {
            a_3236_r.n_1700_B((r_4811_B)p_234022_3_, p_234022_4_, this.n_1700_B, 1);
            p_234022_3_.y_1945_D().n_1700_B(MemoryModuleType.G_564_y, F_427_K.n_1700_B(p_234022_1_.g_2268_R(), p_234022_4_));
            DebugPackets.R_4764_Y(p_234022_1_, p_234022_4_);
        }
    }

    private boolean n_1700_B(L_2225_p p_234020_1_, c_1514_x p_234020_2_, q_2232_A p_234020_3_) {
        b_1722_e path = p_234020_1_.e_4240_b().n_1700_B(p_234020_2_, p_234020_3_.R_4764_Y());
        return path != null && path.s_956_w();
    }

    private void n_1700_B(L_2225_p p_234019_1_) {
        p_234019_1_.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        p_234019_1_.y_1945_D().J_1907_R(MemoryModuleType.h_1847_R);
        p_234019_1_.y_1945_D().J_1907_R(MemoryModuleType.G_564_y);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


