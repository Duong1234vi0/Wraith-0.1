/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.ChestedHorseModel;
import lightning.product.N_4263_v;
import lightning.product.W_3443_Y;
import lightning.product.AbstractHorseRenderer;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import lightning.product.w_2040_b;

public class ChestedHorseRenderer<T extends W_3443_Y>
extends AbstractHorseRenderer<T, ChestedHorseModel<T>> {
    private static final Map<t_5_h<?>, g_2336_b> n_1700_B = Maps.newHashMap((Map)ImmutableMap.of(t_5_h.Q_4569_t, (Object)new g_2336_b("textures/entity/horse/donkey.png"), t_5_h.T_3594_S, (Object)new g_2336_b("textures/entity/horse/mule.png")));

    public ChestedHorseRenderer(w_2040_b renderManagerIn, float scaleIn) {
        super(renderManagerIn, new ChestedHorseModel(0.0f), scaleIn);
    }

    @Override
    public g_2336_b n_1700_B(T entity) {
        return n_1700_B.get(((N_4263_v)entity).f_4016_n());
    }
}


