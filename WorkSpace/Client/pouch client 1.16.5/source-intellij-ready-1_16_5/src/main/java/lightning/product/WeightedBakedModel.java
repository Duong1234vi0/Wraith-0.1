/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.L_4237_Q;
import lightning.product.S_3826_o;
import lightning.product.b_257_Y;
import lightning.product.WeighedRandom;
import lightning.product.c_932_S;

public class WeightedBakedModel
implements S_3826_o {
    private final int n_1700_B;
    private final List<J_1907_R> J_1907_R;
    private final S_3826_o R_4764_Y;

    public WeightedBakedModel(List<J_1907_R> modelsIn) {
        this.J_1907_R = modelsIn;
        this.n_1700_B = WeighedRandom.n_1700_B(modelsIn);
        this.R_4764_Y = modelsIn.get((int)0).n_1700_B;
    }

    @Override
    public List<c_932_S> n_1700_B(@Nullable K_4074_S state, @Nullable b_257_Y side, Random rand) {
        return WeighedRandom.n_1700_B(this.J_1907_R, (int)(Math.abs((int)((int)rand.nextLong())) % this.n_1700_B)).n_1700_B.n_1700_B(state, side, rand);
    }

    @Override
    public boolean n_1700_B() {
        return this.R_4764_Y.n_1700_B();
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y.J_1907_R();
    }

    @Override
    public boolean R_4764_Y() {
        return this.R_4764_Y.R_4764_Y();
    }

    @Override
    public boolean G_564_y() {
        return this.R_4764_Y.G_564_y();
    }

    @Override
    public B_3871_I P_1922_E() {
        return this.R_4764_Y.P_1922_E();
    }

    @Override
    public ItemTransforms u_1723_Y() {
        return this.R_4764_Y.u_1723_Y();
    }

    @Override
    public L_4237_Q v_4262_N() {
        return this.R_4764_Y.v_4262_N();
    }

    static class J_1907_R
    extends WeighedRandom.n_1700_B {
        protected final S_3826_o n_1700_B;

        public J_1907_R(S_3826_o modelIn, int itemWeightIn) {
            super(itemWeightIn);
            this.n_1700_B = modelIn;
        }
    }

    public static class n_1700_B {
        private final List<J_1907_R> n_1700_B = Lists.newArrayList();

        public n_1700_B n_1700_B(@Nullable S_3826_o model, int weight) {
            if (model != null) {
                this.n_1700_B.add(new J_1907_R(model, weight));
            }
            return this;
        }

        @Nullable
        public S_3826_o n_1700_B() {
            if (this.n_1700_B.isEmpty()) {
                return null;
            }
            return this.n_1700_B.size() == 1 ? this.n_1700_B.get((int)0).n_1700_B : new WeightedBakedModel(this.n_1700_B);
        }
    }
}


