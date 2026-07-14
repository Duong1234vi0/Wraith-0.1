/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_2855_e;
import lightning.product.R_1299_M;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class ParrotRenderer
extends r_1334_c<R_1299_M, P_2855_e> {
    public static final g_2336_b[] n_1700_B = new g_2336_b[]{new g_2336_b("textures/entity/parrot/parrot_red_blue.png"), new g_2336_b("textures/entity/parrot/parrot_blue.png"), new g_2336_b("textures/entity/parrot/parrot_green.png"), new g_2336_b("textures/entity/parrot/parrot_yellow_blue.png"), new g_2336_b("textures/entity/parrot/parrot_grey.png")};

    public ParrotRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new P_2855_e(), 0.3f);
    }

    @Override
    public g_2336_b n_1700_B(R_1299_M entity) {
        return n_1700_B[entity.V_1176_p()];
    }

    @Override
    public float n_1700_B(R_1299_M livingBase, float partialTicks) {
        float f = u_530_F.v_4262_N(partialTicks, livingBase.t_1786_h, livingBase.h_1847_R);
        float f1 = u_530_F.v_4262_N(partialTicks, livingBase.M_182_A, livingBase.Q_4569_t);
        return (u_530_F.n_1700_B(f) + 1.0f) * f1;
    }
}


