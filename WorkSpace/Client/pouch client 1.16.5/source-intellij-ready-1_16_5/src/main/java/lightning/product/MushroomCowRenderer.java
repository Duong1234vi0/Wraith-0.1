/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.N_4235_V;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.r_1334_c;
import lightning.product.s_4023_U;
import lightning.product.w_2040_b;
import lightning.product.w_3245_r;

public class MushroomCowRenderer
extends r_1334_c<s_4023_U, w_3245_r<s_4023_U>> {
    private static final Map<s_4023_U.n_1700_B, g_2336_b> n_1700_B = j_3341_s.n_1700_B(Maps.newHashMap(), p_217773_0_ -> {
        p_217773_0_.put(s_4023_U.n_1700_B.J_1907_R, new g_2336_b("textures/entity/cow/brown_mooshroom.png"));
        p_217773_0_.put(s_4023_U.n_1700_B.n_1700_B, new g_2336_b("textures/entity/cow/red_mooshroom.png"));
    });

    public MushroomCowRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new w_3245_r(), 0.7f);
        this.n_1700_B(new N_4235_V<s_4023_U>(this));
    }

    @Override
    public g_2336_b n_1700_B(s_4023_U entity) {
        return n_1700_B.get((Object)entity.h_1640_b());
    }
}


