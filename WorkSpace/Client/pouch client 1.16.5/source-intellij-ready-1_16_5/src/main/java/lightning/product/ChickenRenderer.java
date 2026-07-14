/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.X_4861_v;
import lightning.product.ChickenModel;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class ChickenRenderer
extends r_1334_c<X_4861_v, ChickenModel<X_4861_v>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/chicken.png");

    public ChickenRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new ChickenModel(), 0.3f);
    }

    @Override
    public g_2336_b n_1700_B(X_4861_v entity) {
        return n_1700_B;
    }

    @Override
    protected float n_1700_B(X_4861_v livingBase, float partialTicks) {
        float f = u_530_F.v_4262_N(partialTicks, livingBase.t_1786_h, livingBase.h_1847_R);
        float f1 = u_530_F.v_4262_N(partialTicks, livingBase.M_182_A, livingBase.Q_4569_t);
        return (u_530_F.n_1700_B(f) + 1.0f) * f1;
    }
}


