/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntFunction
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lightning.product.DoubleBlockCombiner;
import lightning.product.e_1689_x;
import lightning.product.i_2154_H;
import lightning.product.z_883_p;
import net.optifine.EmissiveTextures;

public class N_4989_q<S extends i_2154_H>
implements DoubleBlockCombiner.n_1700_B<S, Int2IntFunction> {
    @Override
    public Int2IntFunction n_1700_B(S p_225539_1_, S p_225539_2_) {
        return p_lambda$func_225539_a_$0_2_ -> {
            if (EmissiveTextures.isRenderEmissive()) {
                return e_1689_x.n_1700_B;
            }
            int i = z_883_p.n_1700_B(p_225539_1_.c_3005_b(), p_225539_1_.x_607_J());
            int j = z_883_p.n_1700_B(p_225539_2_.c_3005_b(), p_225539_2_.x_607_J());
            int k = e_1689_x.n_1700_B(i);
            int l = e_1689_x.n_1700_B(j);
            int i1 = e_1689_x.J_1907_R(i);
            int j1 = e_1689_x.J_1907_R(j);
            return e_1689_x.n_1700_B(Math.max(k, l), Math.max(i1, j1));
        };
    }

    @Override
    public Int2IntFunction n_1700_B(S p_225538_1_) {
        return p_lambda$func_225538_a_$1_0_ -> p_lambda$func_225538_a_$1_0_;
    }

    public Int2IntFunction n_1700_B() {
        return p_lambda$func_225537_b_$2_0_ -> p_lambda$func_225537_b_$2_0_;
    }

    @Override
    public /* synthetic */ Object J_1907_R() {
        return this.n_1700_B();
    }
}


