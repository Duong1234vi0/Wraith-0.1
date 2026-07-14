/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_1913_J;
import lightning.product.g_2336_b;
import lightning.product.BeeModel;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class L_3489_J
extends r_1334_c<b_1913_J, BeeModel<b_1913_J>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/bee/bee_angry.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/bee/bee_angry_nectar.png");
    private static final g_2336_b multiplayerClientSuggestionProvider = new g_2336_b("textures/entity/bee/bee.png");
    private static final g_2336_b w_1457_N = new g_2336_b("textures/entity/bee/bee_nectar.png");

    public L_3489_J(w_2040_b p_i226033_1_) {
        super(p_i226033_1_, new BeeModel(), 0.4f);
    }

    @Override
    public g_2336_b n_1700_B(b_1913_J entity) {
        if (entity.B_()) {
            return entity.V_537_k() ? t_1786_h : n_1700_B;
        }
        return entity.V_537_k() ? w_1457_N : multiplayerClientSuggestionProvider;
    }
}


