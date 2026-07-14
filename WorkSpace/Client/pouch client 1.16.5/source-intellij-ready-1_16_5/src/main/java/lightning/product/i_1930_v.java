/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.function.Function;
import lightning.product.K_1311_s;
import lightning.product.j_3341_s;
import lightning.product.n_4006_Y;
import lightning.product.o_525_o;
import lightning.product.u_653_C;

public final class i_1930_v
extends Enum<i_1930_v> {
    public static final /* enum */ i_1930_v n_1700_B = new i_1930_v("bitmap", o_525_o.n_1700_B::n_1700_B);
    public static final /* enum */ i_1930_v J_1907_R = new i_1930_v("ttf", u_653_C::n_1700_B);
    public static final /* enum */ i_1930_v R_4764_Y = new i_1930_v("legacy_unicode", K_1311_s.n_1700_B::n_1700_B);
    private static final Map<String, i_1930_v> G_564_y;
    private final String P_1922_E;
    private final Function<JsonObject, n_4006_Y> u_1723_Y;
    private static final /* synthetic */ i_1930_v[] v_4262_N;

    public static i_1930_v[] values() {
        return (i_1930_v[])v_4262_N.clone();
    }

    public static i_1930_v valueOf(String name) {
        return Enum.valueOf(i_1930_v.class, name);
    }

    private i_1930_v(String typeIn, Function<JsonObject, n_4006_Y> factoryIn) {
        this.P_1922_E = typeIn;
        this.u_1723_Y = factoryIn;
    }

    public static i_1930_v n_1700_B(String typeIn) {
        i_1930_v glyphprovidertypes = G_564_y.get(typeIn);
        if (glyphprovidertypes == null) {
            throw new IllegalArgumentException("Invalid type: " + typeIn);
        }
        return glyphprovidertypes;
    }

    public n_4006_Y n_1700_B(JsonObject jsonIn) {
        return this.u_1723_Y.apply(jsonIn);
    }

    private static /* synthetic */ i_1930_v[] n_1700_B() {
        return new i_1930_v[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        v_4262_N = i_1930_v.n_1700_B();
        G_564_y = j_3341_s.n_1700_B(Maps.newHashMap(), p_211639_0_ -> {
            for (i_1930_v glyphprovidertypes : i_1930_v.values()) {
                p_211639_0_.put(glyphprovidertypes.P_1922_E, glyphprovidertypes);
            }
        });
    }
}

