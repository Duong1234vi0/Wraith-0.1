/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 */
package lightning.product;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import lightning.product.FluidTags;
import lightning.product.TagContainer;
import lightning.product.E_2561_m;
import lightning.product.ItemTags;
import lightning.product.StaticTagHelper;
import lightning.product.g_2336_b;
import lightning.product.BlockTags;
import lightning.product.EntityTypeTags;

public class O_4030_c {
    private static final Map<g_2336_b, StaticTagHelper<?>> n_1700_B = Maps.newHashMap();

    public static <T> StaticTagHelper<T> n_1700_B(g_2336_b id, Function<TagContainer, E_2561_m<T>> supplierToCollectionFunction) {
        StaticTagHelper<T> tagregistry = new StaticTagHelper<T>(supplierToCollectionFunction);
        StaticTagHelper<T> tagregistry1 = n_1700_B.putIfAbsent(id, tagregistry);
        if (tagregistry1 != null) {
            throw new IllegalStateException("Duplicate entry for static tag collection: " + String.valueOf(id));
        }
        return tagregistry;
    }

    public static void n_1700_B(TagContainer supplier) {
        n_1700_B.values().forEach(registry -> registry.n_1700_B(supplier));
    }

    public static void n_1700_B() {
        n_1700_B.values().forEach(StaticTagHelper::n_1700_B);
    }

    public static Multimap<g_2336_b, g_2336_b> J_1907_R(TagContainer supplier) {
        HashMultimap multimap = HashMultimap.create();
        n_1700_B.forEach((arg_0, arg_1) -> O_4030_c.n_1700_B((Multimap)multimap, supplier, arg_0, arg_1));
        return multimap;
    }

    public static void J_1907_R() {
        StaticTagHelper[] atagregistry = new StaticTagHelper[]{BlockTags.n_1700_B, ItemTags.n_1700_B, FluidTags.n_1700_B, EntityTypeTags.n_1700_B};
        boolean flag = Stream.of(atagregistry).anyMatch(registry -> !n_1700_B.containsValue(registry));
        if (flag) {
            throw new IllegalStateException("Missing helper registrations");
        }
    }

    private static /* synthetic */ void n_1700_B(Multimap multimap, TagContainer supplier, g_2336_b id, StaticTagHelper registry) {
        multimap.putAll((Object)id, registry.J_1907_R(supplier));
    }
}


