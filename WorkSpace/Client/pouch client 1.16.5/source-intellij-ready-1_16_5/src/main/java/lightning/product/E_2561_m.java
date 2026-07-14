/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.ImmutableBiMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.DefaultedRegistry;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.SetTag;

public interface E_2561_m<T> {
    public Map<g_2336_b, r_109_r<T>> n_1700_B();

    @Nullable
    default public r_109_r<T> n_1700_B(g_2336_b resourceLocationIn) {
        return this.n_1700_B().get(resourceLocationIn);
    }

    public r_109_r<T> J_1907_R(g_2336_b var1);

    @Nullable
    public g_2336_b n_1700_B(r_109_r<T> var1);

    default public g_2336_b J_1907_R(r_109_r<T> tag) {
        g_2336_b resourcelocation = this.n_1700_B(tag);
        if (resourcelocation == null) {
            throw new IllegalStateException("Unrecognized tag");
        }
        return resourcelocation;
    }

    default public Collection<g_2336_b> J_1907_R() {
        return this.n_1700_B().keySet();
    }

    default public Collection<g_2336_b> n_1700_B(T itemIn) {
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<g_2336_b, r_109_r<T>> entry : this.n_1700_B().entrySet()) {
            if (!entry.getValue().n_1700_B(itemIn)) continue;
            list.add(entry.getKey());
        }
        return list;
    }

    default public void n_1700_B(b_2585_i buffer, DefaultedRegistry<T> defaulted) {
        Map<g_2336_b, r_109_r<T>> map = this.n_1700_B();
        buffer.G_564_y(map.size());
        for (Map.Entry<g_2336_b, r_109_r<T>> entry : map.entrySet()) {
            buffer.n_1700_B(entry.getKey());
            buffer.G_564_y(entry.getValue().n_1700_B().size());
            for (T t : entry.getValue().n_1700_B()) {
                buffer.G_564_y(defaulted.n_1700_B(t));
            }
        }
    }

    public static <T> E_2561_m<T> n_1700_B(b_2585_i buffer, V_3137_a<T> registry) {
        HashMap map = Maps.newHashMap();
        int i = buffer.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            g_2336_b resourcelocation = buffer.P_4830_p();
            int k = buffer.u_1723_Y();
            ImmutableSet.Builder builder = ImmutableSet.builder();
            for (int l = 0; l < k; ++l) {
                builder.add(registry.n_1700_B(buffer.u_1723_Y()));
            }
            map.put(resourcelocation, r_109_r.n_1700_B(builder.build()));
        }
        return E_2561_m.n_1700_B(map);
    }

    public static <T> E_2561_m<T> R_4764_Y() {
        return E_2561_m.n_1700_B(ImmutableBiMap.of());
    }

    public static <T> E_2561_m<T> n_1700_B(Map<g_2336_b, r_109_r<T>> idTagMap) {
        ImmutableBiMap bimap = ImmutableBiMap.copyOf(idTagMap);
        return new E_2561_m<T>((BiMap)bimap){
            private final r_109_r<T> J_1907_R = SetTag.J_1907_R();
            final /* synthetic */ BiMap n_1700_B;
            {
                this.n_1700_B = biMap;
            }

            @Override
            public r_109_r<T> J_1907_R(g_2336_b id) {
                return (r_109_r)this.n_1700_B.getOrDefault((Object)id, this.J_1907_R);
            }

            @Override
            @Nullable
            public g_2336_b n_1700_B(r_109_r<T> tag) {
                return tag instanceof r_109_r.J_1907_R ? ((r_109_r.J_1907_R)tag).J_1907_R() : (g_2336_b)this.n_1700_B.inverse().get(tag);
            }

            @Override
            public Map<g_2336_b, r_109_r<T>> n_1700_B() {
                return this.n_1700_B;
            }
        };
    }
}


