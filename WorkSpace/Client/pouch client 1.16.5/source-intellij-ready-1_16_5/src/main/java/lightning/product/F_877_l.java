/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Supplier
 *  com.google.common.base.Suppliers
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonIOException
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DataResult$PartialResult
 *  com.mojang.serialization.Decoder
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Encoder
 *  com.mojang.serialization.JsonOps
 *  com.mojang.serialization.Lifecycle
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lightning.product.RegistryWriteOps;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.DelegatingOps;
import lightning.product.WritableRegistry;
import lightning.product.r_4097_j;
import lightning.product.v_1758_J;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class F_877_l<T>
extends DelegatingOps<T> {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final n_1700_B R_4764_Y;
    private final r_4097_j.J_1907_R G_564_y;
    private final Map<f_2392_k<? extends V_3137_a<?>>, J_1907_R<?>> P_1922_E;
    private final F_877_l<JsonElement> u_1723_Y;

    public static <T> F_877_l<T> n_1700_B(DynamicOps<T> ops, ResourceManager resourceManager, r_4097_j.J_1907_R dynamicRegistries) {
        return F_877_l.n_1700_B(ops, lightning.product.F_877_l$n_1700_B.n_1700_B(resourceManager), dynamicRegistries);
    }

    public static <T> F_877_l<T> n_1700_B(DynamicOps<T> ops, n_1700_B resourceAccess, r_4097_j.J_1907_R dynamicRegistries) {
        F_877_l<T> worldsettingsimport = new F_877_l<T>(ops, resourceAccess, dynamicRegistries, Maps.newIdentityHashMap());
        r_4097_j.n_1700_B(dynamicRegistries, worldsettingsimport);
        return worldsettingsimport;
    }

    private F_877_l(DynamicOps<T> ops, n_1700_B resourceAccess, r_4097_j.J_1907_R dynamicRegistries, IdentityHashMap<f_2392_k<? extends V_3137_a<?>>, J_1907_R<?>> registryToResultMap) {
        super(ops);
        this.R_4764_Y = resourceAccess;
        this.G_564_y = dynamicRegistries;
        this.P_1922_E = registryToResultMap;
        this.u_1723_Y = ops == JsonOps.INSTANCE ? this : new F_877_l<T>(JsonOps.INSTANCE, resourceAccess, dynamicRegistries, (IdentityHashMap<f_2392_k<V_3137_a<?>>, J_1907_R<?>>)registryToResultMap);
    }

    protected <E> DataResult<Pair<Supplier<E>, T>> n_1700_B(T input, f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> mapCodec, boolean allowInlineDefinitions) {
        Optional optional = this.G_564_y.n_1700_B(registryKey);
        if (!optional.isPresent()) {
            return DataResult.error((String)("Unknown registry: " + String.valueOf(registryKey)));
        }
        WritableRegistry mutableregistry = optional.get();
        DataResult dataresult = g_2336_b.n_1700_B.decode(this.n_1700_B, input);
        if (!dataresult.result().isPresent()) {
            return !allowInlineDefinitions ? DataResult.error((String)"Inline definitions not allowed here") : mapCodec.decode((DynamicOps)this, input).map(instanceInputPair -> instanceInputPair.mapFirst(instance -> () -> instance));
        }
        Pair pair = (Pair)dataresult.result().get();
        g_2336_b resourcelocation = (g_2336_b)pair.getFirst();
        return this.n_1700_B(registryKey, mutableregistry, mapCodec, resourcelocation).map(instanceSupplier -> Pair.of((Object)instanceSupplier, (Object)pair.getSecond()));
    }

    public <E> DataResult<v_1758_J<E>> n_1700_B(v_1758_J<E> simpleRegistry, f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> mapCodec) {
        Collection<g_2336_b> collection = this.R_4764_Y.n_1700_B(registryKey);
        DataResult dataresult = DataResult.success(simpleRegistry, (Lifecycle)Lifecycle.stable());
        String s = registryKey.n_1700_B().J_1907_R() + "/";
        for (g_2336_b resourcelocation : collection) {
            String s1 = resourcelocation.J_1907_R();
            if (!s1.endsWith(".json")) {
                J_1907_R.warn("Skipping resource {} since it is not a json file", (Object)resourcelocation);
                continue;
            }
            if (!s1.startsWith(s)) {
                J_1907_R.warn("Skipping resource {} since it does not have a registry name prefix", (Object)resourcelocation);
                continue;
            }
            String s2 = s1.substring(s.length(), s1.length() - ".json".length());
            g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), s2);
            dataresult = dataresult.flatMap(registry -> this.n_1700_B((f_2392_k)registryKey, (WritableRegistry)registry, mapCodec, resourcelocation1).map(instanceSupplier -> registry));
        }
        return dataresult.setPartial(simpleRegistry);
    }

    private <E> DataResult<Supplier<E>> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey, WritableRegistry<E> mutableRegistry, Codec<E> mapCodec, g_2336_b id) {
        f_2392_k registrykey = f_2392_k.n_1700_B(registryKey, id);
        J_1907_R<E> resultmap = this.J_1907_R(registryKey);
        DataResult dataresult = resultmap.n_1700_B.get(registrykey);
        if (dataresult != null) {
            return dataresult;
        }
        com.google.common.base.Supplier supplier = Suppliers.memoize(() -> {
            Object e = mutableRegistry.n_1700_B(registrykey);
            if (e == null) {
                throw new RuntimeException("Error during recursive registry parsing, element resolved too early: " + String.valueOf(registrykey));
            }
            return e;
        });
        resultmap.n_1700_B.put(registrykey, DataResult.success((Object)supplier));
        DataResult dataresult1 = this.R_4764_Y.n_1700_B((DynamicOps<JsonElement>)this.u_1723_Y, registryKey, registrykey, mapCodec);
        Optional optional = dataresult1.result();
        if (optional.isPresent()) {
            Pair pair = (Pair)optional.get();
            mutableRegistry.n_1700_B((OptionalInt)pair.getSecond(), registrykey, pair.getFirst(), dataresult1.lifecycle());
        }
        DataResult dataresult2 = !optional.isPresent() && mutableRegistry.n_1700_B(registrykey) != null ? DataResult.success(() -> mutableRegistry.n_1700_B(registrykey), (Lifecycle)Lifecycle.stable()) : dataresult1.map(instanceIndexPair -> () -> mutableRegistry.n_1700_B(registrykey));
        resultmap.n_1700_B.put(registrykey, dataresult2);
        return dataresult2;
    }

    private <E> J_1907_R<E> J_1907_R(f_2392_k<? extends V_3137_a<E>> key) {
        return this.P_1922_E.computeIfAbsent(key, registryKey -> new J_1907_R());
    }

    protected <E> DataResult<V_3137_a<E>> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey) {
        return this.G_564_y.n_1700_B(registryKey).map(mutableRegistry -> DataResult.success((Object)mutableRegistry, (Lifecycle)mutableRegistry.R_4764_Y())).orElseGet(() -> DataResult.error((String)("Unknown registry: " + String.valueOf(registryKey))));
    }

    public static interface lightning.product.F_877_l$n_1700_B {
        public Collection<g_2336_b> n_1700_B(f_2392_k<? extends V_3137_a<?>> var1);

        public <E> DataResult<Pair<E, OptionalInt>> n_1700_B(DynamicOps<JsonElement> var1, f_2392_k<? extends V_3137_a<E>> var2, f_2392_k<E> var3, Decoder<E> var4);

        public static lightning.product.F_877_l$n_1700_B n_1700_B(final ResourceManager manager) {
            return new lightning.product.F_877_l$n_1700_B(){

                @Override
                public Collection<g_2336_b> n_1700_B(f_2392_k<? extends V_3137_a<?>> registryKey) {
                    return manager.n_1700_B(registryKey.n_1700_B().J_1907_R(), fileName -> fileName.endsWith(".json"));
                }

                /*
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public <E> DataResult<Pair<E, OptionalInt>> n_1700_B(DynamicOps<JsonElement> jsonOps, f_2392_k<? extends V_3137_a<E>> registryKey, f_2392_k<E> objectKey, Decoder<E> decoder) {
                    g_2336_b resourcelocation = objectKey.n_1700_B();
                    g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), registryKey.n_1700_B().J_1907_R() + "/" + resourcelocation.J_1907_R() + ".json");
                    try (Resource iresource = manager.n_1700_B(resourcelocation1);){
                        DataResult dataResult;
                        try (InputStreamReader reader = new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8);){
                            JsonParser jsonparser = new JsonParser();
                            JsonElement jsonelement = jsonparser.parse((Reader)reader);
                            dataResult = decoder.parse(jsonOps, (Object)jsonelement).map(instance -> Pair.of((Object)instance, (Object)OptionalInt.empty()));
                        }
                        return dataResult;
                    }
                    catch (JsonIOException | JsonSyntaxException | IOException ioexception) {
                        return DataResult.error((String)("Failed to parse " + String.valueOf(resourcelocation1) + " file: " + ioexception.getMessage()));
                    }
                }

                public String toString() {
                    return "ResourceAccess[" + String.valueOf(manager) + "]";
                }
            };
        }

        public static final class n_1700_B
        implements lightning.product.F_877_l$n_1700_B {
            private final Map<f_2392_k<?>, JsonElement> n_1700_B = Maps.newIdentityHashMap();
            private final Object2IntMap<f_2392_k<?>> J_1907_R = new Object2IntOpenCustomHashMap(j_3341_s.u_2550_I());
            private final Map<f_2392_k<?>, Lifecycle> R_4764_Y = Maps.newIdentityHashMap();

            public <E> void n_1700_B(r_4097_j.J_1907_R dynamicRegistries, f_2392_k<E> key, Encoder<E> encoder, int id, E instance, Lifecycle lifecycle) {
                DataResult dataresult = encoder.encodeStart(RegistryWriteOps.n_1700_B(JsonOps.INSTANCE, dynamicRegistries), instance);
                Optional optional = dataresult.error();
                if (optional.isPresent()) {
                    J_1907_R.error("Error adding element: {}", (Object)((DataResult.PartialResult)optional.get()).message());
                } else {
                    this.n_1700_B.put(key, (JsonElement)dataresult.result().get());
                    this.J_1907_R.put(key, id);
                    this.R_4764_Y.put(key, lifecycle);
                }
            }

            @Override
            public Collection<g_2336_b> n_1700_B(f_2392_k<? extends V_3137_a<?>> registryKey) {
                return this.n_1700_B.keySet().stream().filter(key -> key.n_1700_B(registryKey)).map(key -> new g_2336_b(key.n_1700_B().R_4764_Y(), registryKey.n_1700_B().J_1907_R() + "/" + key.n_1700_B().J_1907_R() + ".json")).collect(Collectors.toList());
            }

            @Override
            public <E> DataResult<Pair<E, OptionalInt>> n_1700_B(DynamicOps<JsonElement> jsonOps, f_2392_k<? extends V_3137_a<E>> registryKey, f_2392_k<E> objectKey, Decoder<E> decoder) {
                JsonElement jsonelement = this.n_1700_B.get(objectKey);
                return jsonelement == null ? DataResult.error((String)("Unknown element: " + String.valueOf(objectKey))) : decoder.parse(jsonOps, (Object)jsonelement).setLifecycle(this.R_4764_Y.get(objectKey)).map(instance -> Pair.of((Object)instance, (Object)OptionalInt.of(this.J_1907_R.getInt((Object)objectKey))));
            }
        }
    }

    static final class J_1907_R<E> {
        private final Map<f_2392_k<E>, DataResult<Supplier<E>>> n_1700_B = Maps.newIdentityHashMap();

        private J_1907_R() {
        }
    }
}


