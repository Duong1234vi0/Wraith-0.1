/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.g_2336_b;
import lightning.product.EndermiteModel;
import lightning.product.r_1334_c;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;
import lightning.product.Endermite;

public class EndermiteRenderer
extends r_1334_c<Endermite, EndermiteModel<Endermite>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/endermite.png");

    public EndermiteRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new EndermiteModel(), 0.3f);
    }

    protected float n_1700_B(Endermite entityLivingBaseIn) {
        return 180.0f;
    }

    public g_2336_b J_1907_R(Endermite entity) {
        return n_1700_B;
    }

    @Override
    protected /* synthetic */ float R_4764_Y(r_4811_B r_4811_B2) {
        return this.n_1700_B((Endermite)r_4811_B2);
    }

    @Override
    public /* synthetic */ g_2336_b n_1700_B(N_4263_v n_4263_v) {
        return this.J_1907_R((Endermite)n_4263_v);
    }
}


