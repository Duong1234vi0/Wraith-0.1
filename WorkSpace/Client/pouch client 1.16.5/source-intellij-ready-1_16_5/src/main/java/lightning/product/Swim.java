/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.FluidTags;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class Swim
extends Behavior<Z_530_i> {
    private final float n_1700_B;

    public Swim(float p_i231540_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
        this.n_1700_B = p_i231540_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i owner) {
        return owner.RowButton() && owner.J_1907_R(FluidTags.J_1907_R) > owner.i_3196_G() || owner.W_3464_O();
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Z_530_i entityIn, long gameTimeIn) {
        return this.n_1700_B(worldIn, entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, Z_530_i owner, long gameTime) {
        if (owner.M_3508_C().nextFloat() < this.n_1700_B) {
            owner.t_4043_B().n_1700_B();
        }
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


