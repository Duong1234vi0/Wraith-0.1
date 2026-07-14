/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.MemoryModuleType;

public class LookAtTargetSink
extends Behavior<Z_530_i> {
    public LookAtTargetSink(int durationMin, int durationMax) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.n_1700_B)), durationMin, durationMax);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        return entityIn.y_1945_D().R_4764_Y(MemoryModuleType.h_1847_R).filter(posWrapper -> posWrapper.n_1700_B(entityIn)).isPresent();
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.h_1847_R);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, Z_530_i owner, long gameTime) {
        owner.y_1945_D().R_4764_Y(MemoryModuleType.h_1847_R).ifPresent(posWrapper -> owner.c_3005_b().n_1700_B(posWrapper.n_1700_B()));
    }
}


