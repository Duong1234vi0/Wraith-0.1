/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;
import lightning.product.E_2561_m;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.r_109_r;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_1340_R<T> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new Gson();
    private static final int R_4764_Y = ".json".length();
    private final Function<g_2336_b, Optional<T>> G_564_y;
    private final String P_1922_E;
    private final String u_1723_Y;

    public s_1340_R(Function<g_2336_b, Optional<T>> idToTagFunction, String path, String tagType) {
        this.G_564_y = idToTagFunction;
        this.P_1922_E = path;
        this.u_1723_Y = tagType;
    }

    public CompletableFuture<Map<g_2336_b, r_109_r.n_1700_B>> n_1700_B(ResourceManager manager, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            HashMap map = Maps.newHashMap();
            for (g_2336_b resourcelocation : manager.n_1700_B(this.P_1922_E, (String fileName) -> fileName.endsWith(".json"))) {
                String s = resourcelocation.J_1907_R();
                g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), s.substring(this.P_1922_E.length() + 1, s.length() - R_4764_Y));
                try {
                    for (Resource iresource : manager.R_4764_Y(resourcelocation)) {
                        try {
                            InputStream inputstream = iresource.J_1907_R();
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));){
                                JsonObject jsonobject = i_4431_W.n_1700_B(J_1907_R, (Reader)reader, JsonObject.class);
                                if (jsonobject == null) {
                                    n_1700_B.error("Couldn't load {} tag list {} from {} in data pack {} as it is empty or null", (Object)this.u_1723_Y, (Object)resourcelocation1, (Object)resourcelocation, (Object)iresource.R_4764_Y());
                                    continue;
                                }
                                map.computeIfAbsent(resourcelocation1, id -> r_109_r.n_1700_B.n_1700_B()).n_1700_B(jsonobject, iresource.R_4764_Y());
                            }
                            finally {
                                if (inputstream == null) continue;
                                inputstream.close();
                            }
                        }
                        catch (IOException | RuntimeException ioexception) {
                            n_1700_B.error("Couldn't read {} tag list {} from {} in data pack {}", (Object)this.u_1723_Y, (Object)resourcelocation1, (Object)resourcelocation, (Object)iresource.R_4764_Y(), (Object)ioexception);
                        }
                        finally {
                            IOUtils.closeQuietly((Closeable)iresource);
                        }
                    }
                }
                catch (IOException ioexception1) {
                    n_1700_B.error("Couldn't read {} tag list {} from {}", (Object)this.u_1723_Y, (Object)resourcelocation1, (Object)resourcelocation, (Object)ioexception1);
                }
            }
            return map;
        }, executor);
    }

    public E_2561_m<T> n_1700_B(Map<g_2336_b, r_109_r.n_1700_B> idToBuilderMap) {
        HashMap map = Maps.newHashMap();
        Function function = map::get;
        Function<g_2336_b, Object> function1 = id -> this.G_564_y.apply((g_2336_b)id).orElse(null);
        while (!idToBuilderMap.isEmpty()) {
            boolean flag = false;
            Iterator<Map.Entry<g_2336_b, r_109_r.n_1700_B>> iterator = idToBuilderMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<g_2336_b, r_109_r.n_1700_B> entry = iterator.next();
                Optional<r_109_r<Object>> optional = entry.getValue().n_1700_B(function, function1);
                if (!optional.isPresent()) continue;
                map.put(entry.getKey(), optional.get());
                iterator.remove();
                flag = true;
            }
            if (flag) continue;
            break;
        }
        idToBuilderMap.forEach((tagID, builder) -> n_1700_B.error("Couldn't load {} tag {} as it is missing following references: {}", (Object)this.u_1723_Y, tagID, (Object)builder.J_1907_R(function, function1).map(Objects::toString).collect(Collectors.joining(","))));
        return E_2561_m.n_1700_B(map);
    }
}


