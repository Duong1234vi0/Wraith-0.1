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

public class NearestHealableRaiderTargetGoal<T extends r_4811_B>
extends NearestAttackableTargetGoal<T> {
    private int t_148_a = 0;

    public NearestHealableRaiderTargetGoal(W_4304_a raider, Class<T> targetClass, boolean checkSight, @Nullable Predicate<r_4811_B> p_i50311_4_) {
        super(raider, targetClass, 500, checkSight, false, p_i50311_4_);
    }

    public int v_4262_N() {
        return this.t_148_a;
    }

    public void w_1484_f() {
        --this.t_148_a;
    }

    @Override
    public boolean n_1700_B() {
        if (this.t_148_a <= 0 && this.P_1922_E.M_3508_C().nextBoolean()) {
            if (!((W_4304_a)this.P_1922_E).J_3635_s()) {
                return false;
            }
            this.s_956_w();
            return this.R_4764_Y != null;
        }
        return false;
    }

    @Override
    public void R_4764_Y() {
        this.t_148_a = 200;
        super.R_4764_Y();
    }
}


