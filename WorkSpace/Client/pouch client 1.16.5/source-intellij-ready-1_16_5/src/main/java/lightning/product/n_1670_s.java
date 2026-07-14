/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.SharedConstants;
import lightning.product.V_3137_a;
import lightning.product.Biomes;
import lightning.product.LazyArea;
import lightning.product.f_2392_k;
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;
import lightning.product.p_3451_N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_1670_s {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final LazyArea J_1907_R;

    public n_1670_s(p_3451_N<LazyArea> lazyAreaFactoryIn) {
        this.J_1907_R = lazyAreaFactoryIn.make();
    }

    public k_594_Q n_1700_B(V_3137_a<k_594_Q> p_242936_1_, int p_242936_2_, int p_242936_3_) {
        int i = this.J_1907_R.n_1700_B(p_242936_2_, p_242936_3_);
        f_2392_k<k_594_Q> registrykey = Biomes.n_1700_B(i);
        if (registrykey == null) {
            throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
        }
        k_594_Q biome = p_242936_1_.n_1700_B(registrykey);
        if (biome == null) {
            if (SharedConstants.G_564_y) {
                throw j_3341_s.R_4764_Y(new IllegalStateException("Unknown biome id: " + i));
            }
            n_1700_B.warn("Unknown biome id: ", (Object)i);
            return p_242936_1_.n_1700_B(Biomes.n_1700_B(0));
        }
        return biome;
    }
}


