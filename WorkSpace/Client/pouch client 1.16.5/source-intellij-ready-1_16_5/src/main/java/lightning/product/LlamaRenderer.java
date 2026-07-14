/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.g_2336_b;
import lightning.product.g_4407_j;
import lightning.product.r_1334_c;
import lightning.product.LlamaDecorLayer;
import lightning.product.LlamaModel;
import lightning.product.w_2040_b;

public class LlamaRenderer
extends r_1334_c<g_4407_j, LlamaModel<g_4407_j>> {
    private static final g_2336_b[] n_1700_B = new g_2336_b[]{new g_2336_b("textures/entity/llama/creamy.png"), new g_2336_b("textures/entity/llama/white.png"), new g_2336_b("textures/entity/llama/brown.png"), new g_2336_b("textures/entity/llama/gray.png")};

    public LlamaRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new LlamaModel(0.0f), 0.7f);
        this.n_1700_B(new LlamaDecorLayer(this));
    }

    @Override
    public g_2336_b n_1700_B(g_4407_j entity) {
        return n_1700_B[entity.P_3676_m()];
    }
}


