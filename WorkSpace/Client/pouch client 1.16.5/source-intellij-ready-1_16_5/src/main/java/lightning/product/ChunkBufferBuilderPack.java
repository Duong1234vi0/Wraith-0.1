/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.D_3318_r;
import lightning.product.o_2576_A;

public class ChunkBufferBuilderPack {
    private final Map<o_2576_A, D_3318_r> n_1700_B = o_2576_A.k_2293_S().stream().collect(Collectors.toMap(renderType -> renderType, renderType -> new D_3318_r(renderType.q_2307_F())));

    public D_3318_r n_1700_B(o_2576_A renderTypeIn) {
        return this.n_1700_B.get(renderTypeIn);
    }

    public void n_1700_B() {
        this.n_1700_B.values().forEach(D_3318_r::w_1484_f);
    }

    public void J_1907_R() {
        this.n_1700_B.values().forEach(D_3318_r::t_148_a);
    }
}


