/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.SimplePreparableReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class P_2507_S
extends SimplePreparableReloadListener<Map<g_2336_b, JsonElement>> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final int J_1907_R = ".json".length();
    private final Gson R_4764_Y;
    private final String G_564_y;

    public P_2507_S(Gson p_i51536_1_, String p_i51536_2_) {
        this.R_4764_Y = p_i51536_1_;
        this.G_564_y = p_i51536_2_;
    }

    protected Map<g_2336_b, JsonElement> n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        HashMap map = Maps.newHashMap();
        int i = this.G_564_y.length() + 1;
        for (g_2336_b resourcelocation : resourceManagerIn.n_1700_B(this.G_564_y, (String p_223379_0_) -> p_223379_0_.endsWith(".json"))) {
            String s = resourcelocation.J_1907_R();
            g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), s.substring(i, s.length() - J_1907_R));
            try {
                Resource iresource = resourceManagerIn.n_1700_B(resourcelocation);
                try {
                    InputStream inputstream = iresource.J_1907_R();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));){
                        JsonElement jsonelement = i_4431_W.n_1700_B(this.R_4764_Y, (Reader)reader, JsonElement.class);
                        if (jsonelement != null) {
                            JsonElement jsonelement1 = map.put(resourcelocation1, jsonelement);
                            if (jsonelement1 == null) continue;
                            throw new IllegalStateException("Duplicate data file ignored with ID " + String.valueOf(resourcelocation1));
                        }
                        n_1700_B.error("Couldn't load data file {} from {} as it's null or empty", (Object)resourcelocation1, (Object)resourcelocation);
                    }
                    finally {
                        if (inputstream == null) continue;
                        inputstream.close();
                    }
                }
                finally {
                    if (iresource == null) continue;
                    iresource.close();
                }
            }
            catch (JsonParseException | IOException | IllegalArgumentException jsonparseexception) {
                n_1700_B.error("Couldn't parse data file {} from {}", (Object)resourcelocation1, (Object)resourcelocation, (Object)jsonparseexception);
            }
        }
        return map;
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }
}


