/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.B_4315_z;
import lightning.product.I_3755_Y;
import lightning.product.HumanoidMobRenderer;
import lightning.product.Z_530_i;
import lightning.product.AbstractPiglin;
import lightning.product.g_2336_b;
import lightning.product.n_1658_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.w_2040_b;

public class o_2315_w
extends HumanoidMobRenderer<Z_530_i, I_3755_Y<Z_530_i>> {
    private static final Map<t_5_h<?>, g_2336_b> n_1700_B = ImmutableMap.of(t_5_h.i_1637_u, (Object)new g_2336_b("textures/entity/piglin/piglin.png"), t_5_h.c_132_F, (Object)new g_2336_b("textures/entity/piglin/zombified_piglin.png"), t_5_h.Ping, (Object)new g_2336_b("textures/entity/piglin/piglin_brute.png"));

    public o_2315_w(w_2040_b p_i232472_1_, boolean p_i232472_2_) {
        super(p_i232472_1_, o_2315_w.R_4764_Y(p_i232472_2_), 0.5f, 1.0019531f, 1.0f, 1.0019531f);
        this.n_1700_B(new B_4315_z(this, new n_1658_l(0.5f), new n_1658_l(1.02f)));
    }

    private static I_3755_Y<Z_530_i> R_4764_Y(boolean p_239395_0_) {
        I_3755_Y<Z_530_i> piglinmodel = new I_3755_Y<Z_530_i>(0.0f, 64, 64);
        if (p_239395_0_) {
            piglinmodel.P_4830_p.s_956_w = false;
        }
        return piglinmodel;
    }

    @Override
    public g_2336_b n_1700_B(Z_530_i entity) {
        g_2336_b resourcelocation = n_1700_B.get(entity.f_4016_n());
        if (resourcelocation == null) {
            throw new IllegalArgumentException("I don't know what texture to use for " + String.valueOf(entity.f_4016_n()));
        }
        return resourcelocation;
    }

    protected boolean R_4764_Y(Z_530_i p_230495_1_) {
        return p_230495_1_ instanceof AbstractPiglin && ((AbstractPiglin)p_230495_1_).V_1176_p();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(r_4811_B r_4811_B2) {
        return this.R_4764_Y((Z_530_i)r_4811_B2);
    }
}


