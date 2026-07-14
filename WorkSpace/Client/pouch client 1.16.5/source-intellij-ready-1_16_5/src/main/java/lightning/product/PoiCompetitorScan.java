/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class PoiCompetitorScan
extends Behavior<L_2225_p> {
    final VillagerProfession n_1700_B;

    public PoiCompetitorScan(VillagerProfession p_i231525_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.R_4764_Y, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.v_4262_N, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231525_1_;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        F_427_K globalpos = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y).get();
        worldIn.p_178_J().R_4764_Y(globalpos.J_1907_R()).ifPresent(p_233933_3_ -> a_3236_r.n_1700_B(entityIn, (L_2225_p p_233935_3_) -> this.n_1700_B(globalpos, (q_2232_A)p_233933_3_, (L_2225_p)p_233935_3_)).reduce(entityIn, PoiCompetitorScan::n_1700_B));
    }

    private static L_2225_p n_1700_B(L_2225_p p_233932_0_, L_2225_p p_233932_1_) {
        L_2225_p villagerentity1;
        L_2225_p villagerentity;
        if (p_233932_0_.G_564_y() > p_233932_1_.G_564_y()) {
            villagerentity = p_233932_0_;
            villagerentity1 = p_233932_1_;
        } else {
            villagerentity = p_233932_1_;
            villagerentity1 = p_233932_0_;
        }
        villagerentity1.y_1945_D().J_1907_R(MemoryModuleType.R_4764_Y);
        return villagerentity;
    }

    private boolean n_1700_B(F_427_K p_233934_1_, q_2232_A p_233934_2_, L_2225_p p_233934_3_) {
        return this.n_1700_B(p_233934_3_) && p_233934_1_.equals(p_233934_3_.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y).get()) && this.n_1700_B(p_233934_2_, p_233934_3_.c_2086_l().J_1907_R());
    }

    private boolean n_1700_B(q_2232_A p_233930_1_, VillagerProfession p_233930_2_) {
        return p_233930_2_.n_1700_B().J_1907_R().test(p_233930_1_);
    }

    private boolean n_1700_B(L_2225_p p_233931_1_) {
        return p_233931_1_.y_1945_D().R_4764_Y(MemoryModuleType.R_4764_Y).isPresent();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


