/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.Q_3744_j;
import lightning.product.HorseArmorLayer;
import lightning.product.HorseModel;
import lightning.product.AbstractHorseRenderer;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.Horse;
import lightning.product.w_2040_b;
import lightning.product.z_3514_P;

public final class HorseRenderer
extends AbstractHorseRenderer<Horse, HorseModel<Horse>> {
    private static final Map<Q_3744_j, g_2336_b> n_1700_B = j_3341_s.n_1700_B(Maps.newEnumMap(Q_3744_j.class), p_239384_0_ -> {
        p_239384_0_.put(Q_3744_j.n_1700_B, new g_2336_b("textures/entity/horse/horse_white.png"));
        p_239384_0_.put(Q_3744_j.J_1907_R, new g_2336_b("textures/entity/horse/horse_creamy.png"));
        p_239384_0_.put(Q_3744_j.R_4764_Y, new g_2336_b("textures/entity/horse/horse_chestnut.png"));
        p_239384_0_.put(Q_3744_j.G_564_y, new g_2336_b("textures/entity/horse/horse_brown.png"));
        p_239384_0_.put(Q_3744_j.P_1922_E, new g_2336_b("textures/entity/horse/horse_black.png"));
        p_239384_0_.put(Q_3744_j.u_1723_Y, new g_2336_b("textures/entity/horse/horse_gray.png"));
        p_239384_0_.put(Q_3744_j.v_4262_N, new g_2336_b("textures/entity/horse/horse_darkbrown.png"));
    });

    public HorseRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new HorseModel(0.0f), 1.1f);
        this.n_1700_B(new z_3514_P(this));
        this.n_1700_B(new HorseArmorLayer(this));
    }

    @Override
    public g_2336_b n_1700_B(Horse entity) {
        return n_1700_B.get((Object)entity.V_1176_p());
    }
}


