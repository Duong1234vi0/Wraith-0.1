/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.e_1174_E;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class UseItemGoal<T extends Z_530_i>
extends Goal {
    private final T n_1700_B;
    private final Z_1993_T J_1907_R;
    private final Predicate<? super T> R_4764_Y;
    private final SoundEvent G_564_y;

    public UseItemGoal(T user, Z_1993_T stack, @Nullable SoundEvent p_i50319_3_, Predicate<? super T> p_i50319_4_) {
        this.n_1700_B = user;
        this.J_1907_R = stack;
        this.G_564_y = p_i50319_3_;
        this.R_4764_Y = p_i50319_4_;
    }

    @Override
    public boolean n_1700_B() {
        return this.R_4764_Y.test(this.n_1700_B);
    }

    @Override
    public boolean J_1907_R() {
        return ((r_4811_B)this.n_1700_B).Y_601_j();
    }

    @Override
    public void R_4764_Y() {
        ((Z_530_i)this.n_1700_B).n_1700_B(e_1174_E.n_1700_B, this.J_1907_R.t_148_a());
        ((r_4811_B)this.n_1700_B).J_1907_R(x_1688_C.n_1700_B);
    }

    @Override
    public void G_564_y() {
        ((Z_530_i)this.n_1700_B).n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
        if (this.G_564_y != null) {
            ((N_4263_v)this.n_1700_B).n_1700_B(this.G_564_y, 1.0f, ((r_4811_B)this.n_1700_B).M_3508_C().nextFloat() * 0.2f + 0.9f);
        }
    }
}


