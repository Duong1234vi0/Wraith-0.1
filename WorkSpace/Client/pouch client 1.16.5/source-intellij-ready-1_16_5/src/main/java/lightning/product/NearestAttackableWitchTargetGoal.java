/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.W_4304_a;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.r_4811_B;

public class NearestAttackableWitchTargetGoal<T extends r_4811_B>
extends NearestAttackableTargetGoal<T> {
    private boolean t_148_a = true;

    public NearestAttackableWitchTargetGoal(W_4304_a p_i50312_1_, Class<T> p_i50312_2_, int p_i50312_3_, boolean p_i50312_4_, boolean p_i50312_5_, @Nullable Predicate<r_4811_B> p_i50312_6_) {
        super(p_i50312_1_, p_i50312_2_, p_i50312_3_, p_i50312_4_, p_i50312_5_, p_i50312_6_);
    }

    public void n_1700_B(boolean p_220783_1_) {
        this.t_148_a = p_220783_1_;
    }

    @Override
    public boolean n_1700_B() {
        return this.t_148_a && super.n_1700_B();
    }
}


