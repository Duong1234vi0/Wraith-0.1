/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.tuple.Pair
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.L_4237_Q;
import lightning.product.S_3826_o;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.j_3341_s;
import org.apache.commons.lang3.tuple.Pair;

public class MultiPartBakedModel
implements S_3826_o {
    private final List<Pair<Predicate<K_4074_S>, S_3826_o>> v_4262_N;
    protected final boolean n_1700_B;
    protected final boolean J_1907_R;
    protected final boolean R_4764_Y;
    protected final B_3871_I G_564_y;
    protected final ItemTransforms P_1922_E;
    protected final L_4237_Q u_1723_Y;
    private final Map<K_4074_S, BitSet> w_1484_f = new Object2ObjectOpenCustomHashMap(j_3341_s.u_2550_I());

    public MultiPartBakedModel(List<Pair<Predicate<K_4074_S>, S_3826_o>> selectors) {
        this.v_4262_N = selectors;
        S_3826_o ibakedmodel = (S_3826_o)selectors.iterator().next().getRight();
        this.n_1700_B = ibakedmodel.n_1700_B();
        this.J_1907_R = ibakedmodel.J_1907_R();
        this.R_4764_Y = ibakedmodel.R_4764_Y();
        this.G_564_y = ibakedmodel.P_1922_E();
        this.P_1922_E = ibakedmodel.u_1723_Y();
        this.u_1723_Y = ibakedmodel.v_4262_N();
    }

    @Override
    public List<c_932_S> n_1700_B(@Nullable K_4074_S state, @Nullable b_257_Y side, Random rand) {
        if (state == null) {
            return Collections.emptyList();
        }
        BitSet bitset = this.w_1484_f.get(state);
        if (bitset == null) {
            bitset = new BitSet();
            for (int i = 0; i < this.v_4262_N.size(); ++i) {
                Pair<Predicate<K_4074_S>, S_3826_o> pair = this.v_4262_N.get(i);
                if (!((Predicate)pair.getLeft()).test(state)) continue;
                bitset.set(i);
            }
            this.w_1484_f.put(state, bitset);
        }
        ArrayList list = Lists.newArrayList();
        long k = rand.nextLong();
        for (int j = 0; j < bitset.length(); ++j) {
            if (!bitset.get(j)) continue;
            list.addAll(((S_3826_o)this.v_4262_N.get(j).getRight()).n_1700_B(state, side, new Random(k)));
        }
        return list;
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    @Override
    public boolean G_564_y() {
        return false;
    }

    @Override
    public B_3871_I P_1922_E() {
        return this.G_564_y;
    }

    @Override
    public ItemTransforms u_1723_Y() {
        return this.P_1922_E;
    }

    @Override
    public L_4237_Q v_4262_N() {
        return this.u_1723_Y;
    }

    public static class n_1700_B {
        private final List<Pair<Predicate<K_4074_S>, S_3826_o>> n_1700_B = Lists.newArrayList();

        public void n_1700_B(Predicate<K_4074_S> predicate, S_3826_o model) {
            this.n_1700_B.add((Pair<Predicate<K_4074_S>, S_3826_o>)Pair.of(predicate, (Object)model));
        }

        public S_3826_o n_1700_B() {
            return new MultiPartBakedModel(this.n_1700_B);
        }
    }
}


