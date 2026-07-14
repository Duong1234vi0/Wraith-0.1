/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4939_I;
import lightning.product.Z_530_i;
import lightning.product.g_2016_P;
import lightning.product.g_2336_b;
import lightning.product.n_1658_l;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.x_4904_Z;

public class HumanoidMobRenderer<T extends Z_530_i, M extends n_1658_l<T>>
extends r_1334_c<T, M> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/steve.png");

    public HumanoidMobRenderer(w_2040_b renderManagerIn, M modelBipedIn, float shadowSize) {
        this(renderManagerIn, modelBipedIn, shadowSize, 1.0f, 1.0f, 1.0f);
    }

    public HumanoidMobRenderer(w_2040_b p_i232471_1_, M p_i232471_2_, float p_i232471_3_, float p_i232471_4_, float p_i232471_5_, float p_i232471_6_) {
        super(p_i232471_1_, p_i232471_2_, p_i232471_3_);
        this.n_1700_B(new g_2016_P(this, p_i232471_4_, p_i232471_5_, p_i232471_6_));
        this.n_1700_B(new I_4939_I(this));
        this.n_1700_B(new x_4904_Z(this));
    }

    @Override
    public g_2336_b n_1700_B(T entity) {
        return n_1700_B;
    }
}


