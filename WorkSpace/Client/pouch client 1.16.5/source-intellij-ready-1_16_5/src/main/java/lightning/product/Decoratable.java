/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.S_4291_z;
import lightning.product.ChanceDecoratorConfiguration;
import lightning.product.g_1198_o;
import lightning.product.ConfiguredDecorator;
import lightning.product.CountConfiguration;
import lightning.product.y_2419_Z;
import lightning.product.RangeDecoratorConfiguration;

public interface Decoratable<R> {
    public R n_1700_B(ConfiguredDecorator<?> var1);

    default public R n_1700_B(int p_242729_1_) {
        return this.n_1700_B(y_2419_Z.J_1907_R.J_1907_R(new ChanceDecoratorConfiguration(p_242729_1_)));
    }

    default public R n_1700_B(g_1198_o p_242730_1_) {
        return this.n_1700_B(y_2419_Z.R_4764_Y.J_1907_R(new CountConfiguration(p_242730_1_)));
    }

    default public R J_1907_R(int p_242731_1_) {
        return this.n_1700_B(g_1198_o.n_1700_B(p_242731_1_));
    }

    default public R R_4764_Y(int p_242732_1_) {
        return this.n_1700_B(g_1198_o.n_1700_B(0, p_242732_1_));
    }

    default public R G_564_y(int p_242733_1_) {
        return this.n_1700_B(y_2419_Z.M_588_G.J_1907_R(new RangeDecoratorConfiguration(0, 0, p_242733_1_)));
    }

    default public R n_1700_B() {
        return this.n_1700_B(y_2419_Z.v_4262_N.J_1907_R(S_4291_z.J_1907_R));
    }
}


