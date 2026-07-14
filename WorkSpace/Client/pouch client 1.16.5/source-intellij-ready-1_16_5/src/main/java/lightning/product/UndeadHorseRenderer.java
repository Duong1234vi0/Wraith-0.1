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
import lightning.product.U_2534_D;
import lightning.product.HorseModel;
import lightning.product.AbstractHorseRenderer;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import lightning.product.w_2040_b;

public class UndeadHorseRenderer
extends AbstractHorseRenderer<U_2534_D, HorseModel<U_2534_D>> {
    private static final Map<t_5_h<?>, g_2336_b> n_1700_B = Maps.newHashMap((Map)ImmutableMap.of(t_5_h.RealmsScreenWithCallback, (Object)new g_2336_b("textures/entity/horse/horse_zombie.png"), t_5_h.PlayerInfo, (Object)new g_2336_b("textures/entity/horse/horse_skeleton.png")));

    public UndeadHorseRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new HorseModel(0.0f), 1.0f);
    }

    @Override
    public g_2336_b n_1700_B(U_2534_D entity) {
        return n_1700_B.get(entity.f_4016_n());
    }
}


