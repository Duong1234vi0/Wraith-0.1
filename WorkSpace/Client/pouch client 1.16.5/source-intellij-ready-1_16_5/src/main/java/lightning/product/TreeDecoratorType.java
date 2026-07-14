/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.AlterGroundDecorator;
import lightning.product.N_2125_b;
import lightning.product.TreeDecorator;
import lightning.product.V_3137_a;
import lightning.product.TrunkVineDecorator;
import lightning.product.BeehiveDecorator;
import lightning.product.r_525_h;

public class TreeDecoratorType<P extends TreeDecorator> {
    public static final TreeDecoratorType<TrunkVineDecorator> n_1700_B = TreeDecoratorType.n_1700_B("trunk_vine", TrunkVineDecorator.n_1700_B);
    public static final TreeDecoratorType<r_525_h> J_1907_R = TreeDecoratorType.n_1700_B("leave_vine", r_525_h.n_1700_B);
    public static final TreeDecoratorType<N_2125_b> R_4764_Y = TreeDecoratorType.n_1700_B("cocoa", N_2125_b.n_1700_B);
    public static final TreeDecoratorType<BeehiveDecorator> G_564_y = TreeDecoratorType.n_1700_B("beehive", BeehiveDecorator.n_1700_B);
    public static final TreeDecoratorType<AlterGroundDecorator> P_1922_E = TreeDecoratorType.n_1700_B("alter_ground", AlterGroundDecorator.n_1700_B);
    private final Codec<P> u_1723_Y;

    private static <P extends TreeDecorator> TreeDecoratorType<P> n_1700_B(String p_236877_0_, Codec<P> p_236877_1_) {
        return V_3137_a.n_1700_B(V_3137_a.S_980_j, p_236877_0_, new TreeDecoratorType<P>(p_236877_1_));
    }

    private TreeDecoratorType(Codec<P> p_i232052_1_) {
        this.u_1723_Y = p_i232052_1_;
    }

    public Codec<P> n_1700_B() {
        return this.u_1723_Y;
    }
}


