/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.B_3871_I;
import lightning.product.K_4074_S;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.d_1062_x;
import lightning.product.g_2336_b;
import lightning.product.ModelManager;
import lightning.product.v_3760_Q;

public class BlockModelShaper {
    private final Map<K_4074_S, S_3826_o> n_1700_B = Maps.newIdentityHashMap();
    private final ModelManager J_1907_R;

    public BlockModelShaper(ModelManager manager) {
        this.J_1907_R = manager;
    }

    public B_3871_I n_1700_B(K_4074_S state) {
        return this.J_1907_R(state).P_1922_E();
    }

    public S_3826_o J_1907_R(K_4074_S state) {
        S_3826_o ibakedmodel = this.n_1700_B.get(state);
        if (ibakedmodel == null) {
            ibakedmodel = this.J_1907_R.J_1907_R();
        }
        return ibakedmodel;
    }

    public ModelManager n_1700_B() {
        return this.J_1907_R;
    }

    public void J_1907_R() {
        this.n_1700_B.clear();
        for (T_2915_h block : V_3137_a.q_4610_l) {
            block.t_1786_h().n_1700_B().forEach(state -> {
                S_3826_o ibakedmodel = this.n_1700_B.put((K_4074_S)state, this.J_1907_R.n_1700_B(BlockModelShaper.R_4764_Y(state)));
            });
        }
    }

    public static d_1062_x R_4764_Y(K_4074_S state) {
        return BlockModelShaper.n_1700_B(V_3137_a.q_4610_l.J_1907_R(state.J_1907_R()), state);
    }

    public static d_1062_x n_1700_B(g_2336_b location, K_4074_S state) {
        return new d_1062_x(location, BlockModelShaper.n_1700_B(state.q_2307_F()));
    }

    public static String n_1700_B(Map<v_3760_Q<?>, Comparable<?>> propertyValues) {
        StringBuilder stringbuilder = new StringBuilder();
        for (Map.Entry<v_3760_Q<?>, Comparable<?>> entry : propertyValues.entrySet()) {
            if (stringbuilder.length() != 0) {
                stringbuilder.append(',');
            }
            v_3760_Q<?> property = entry.getKey();
            stringbuilder.append(property.P_1922_E());
            stringbuilder.append('=');
            stringbuilder.append(BlockModelShaper.n_1700_B(property, entry.getValue()));
        }
        return stringbuilder.toString();
    }

    private static <T extends Comparable<T>> String n_1700_B(v_3760_Q<T> property, Comparable<?> value) {
        return property.n_1700_B(value);
    }
}


