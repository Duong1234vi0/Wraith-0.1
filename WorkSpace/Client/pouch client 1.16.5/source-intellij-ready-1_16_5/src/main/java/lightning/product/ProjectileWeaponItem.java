/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.ItemTags;
import lightning.product.Z_1993_T;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public abstract class ProjectileWeaponItem
extends q_1613_l {
    public static final Predicate<Z_1993_T> n_1700_B = stack -> stack.J_1907_R().n_1700_B(ItemTags.c_4037_x);
    public static final Predicate<Z_1993_T> J_1907_R = n_1700_B.or(stack -> stack.J_1907_R() == Items.FenceBlock);

    public ProjectileWeaponItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    public Predicate<Z_1993_T> v_4262_N() {
        return this.R_4764_Y();
    }

    public abstract Predicate<Z_1993_T> R_4764_Y();

    public static Z_1993_T n_1700_B(r_4811_B living, Predicate<Z_1993_T> isAmmo) {
        if (isAmmo.test(living.R_4764_Y(x_1688_C.J_1907_R))) {
            return living.R_4764_Y(x_1688_C.J_1907_R);
        }
        return isAmmo.test(living.R_4764_Y(x_1688_C.n_1700_B)) ? living.R_4764_Y(x_1688_C.n_1700_B) : Z_1993_T.J_1907_R;
    }

    @Override
    public int G_564_y() {
        return 1;
    }

    public abstract int P_1922_E();
}


