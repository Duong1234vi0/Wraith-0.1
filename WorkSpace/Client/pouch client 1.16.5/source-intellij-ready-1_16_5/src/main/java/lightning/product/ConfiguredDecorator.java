/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import lightning.product.DecorationContext;
import lightning.product.Decoratable;
import lightning.product.P_1781_m;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.DecoratedDecoratorConfiguration;
import lightning.product.y_2419_Z;

public class ConfiguredDecorator<DC extends P_1781_m>
implements Decoratable<ConfiguredDecorator<?>> {
    public static final Codec<ConfiguredDecorator<?>> n_1700_B = V_3137_a.H_1083_k.dispatch("type", p_236954_0_ -> p_236954_0_.J_1907_R, y_2419_Z::n_1700_B);
    private final y_2419_Z<DC> J_1907_R;
    private final DC R_4764_Y;

    public ConfiguredDecorator(y_2419_Z<DC> decorator, DC config) {
        this.J_1907_R = decorator;
        this.R_4764_Y = config;
    }

    public Stream<c_1514_x> n_1700_B(DecorationContext p_242876_1_, Random p_242876_2_, c_1514_x p_242876_3_) {
        return this.J_1907_R.n_1700_B(p_242876_1_, p_242876_2_, this.R_4764_Y, p_242876_3_);
    }

    public String toString() {
        return String.format("[%s %s]", V_3137_a.H_1083_k.J_1907_R(this.J_1907_R), this.R_4764_Y);
    }

    public ConfiguredDecorator<?> J_1907_R(ConfiguredDecorator<?> p_227228_1_) {
        return new ConfiguredDecorator<DecoratedDecoratorConfiguration>(y_2419_Z.H_2857_Y, new DecoratedDecoratorConfiguration(p_227228_1_, this));
    }

    public DC J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public /* synthetic */ Object n_1700_B(ConfiguredDecorator i_3648_a2) {
        return this.J_1907_R(i_3648_a2);
    }
}


