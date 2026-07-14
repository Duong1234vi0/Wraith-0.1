/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lightning.product.B_3871_I;
import lightning.product.L_3848_p;
import lightning.product.T_2910_P;
import lightning.product.g_2336_b;

public class AtlasSet
implements AutoCloseable {
    private final Map<g_2336_b, L_3848_p> n_1700_B;

    public AtlasSet(Collection<L_3848_p> atlasTexturesIn) {
        this.n_1700_B = atlasTexturesIn.stream().collect(Collectors.toMap(L_3848_p::R_4764_Y, Function.identity()));
    }

    public L_3848_p n_1700_B(g_2336_b locationIn) {
        return this.n_1700_B.get(locationIn);
    }

    public B_3871_I n_1700_B(T_2910_P materialIn) {
        return this.n_1700_B.get(materialIn.n_1700_B()).J_1907_R(materialIn.J_1907_R());
    }

    @Override
    public void close() {
        this.n_1700_B.values().forEach(L_3848_p::J_1907_R);
        this.n_1700_B.clear();
    }
}


