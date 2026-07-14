/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  it.unimi.dsi.fastutil.ints.IntCollection
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lightning.product.C_3240_x;
import lightning.product.F_3283_z;
import lightning.product.PreparableReloadListener;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.AllMissingGlyphProvider;
import lightning.product.Y_4083_F;
import lightning.product.e_3495_r;
import lightning.product.g_2336_b;
import lightning.product.i_1930_v;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import lightning.product.SimplePreparableReloadListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class o_3912_n
implements AutoCloseable {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final g_2336_b n_1700_B = new g_2336_b("minecraft", "missing");
    private final F_3283_z R_4764_Y;
    private final Map<g_2336_b, F_3283_z> G_564_y = Maps.newHashMap();
    private final C_3240_x P_1922_E;
    private Map<g_2336_b, g_2336_b> u_1723_Y = ImmutableMap.of();
    private final PreparableReloadListener v_4262_N = new SimplePreparableReloadListener<Map<g_2336_b, List<e_3495_r>>>(){

        protected Map<g_2336_b, List<e_3495_r>> n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
            profilerIn.n_1700_B();
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            HashMap map = Maps.newHashMap();
            for (g_2336_b resourcelocation : resourceManagerIn.n_1700_B("font", (String p_215274_0_) -> p_215274_0_.endsWith(".json"))) {
                String s = resourcelocation.J_1907_R();
                g_2336_b resourcelocation1 = new g_2336_b(resourcelocation.R_4764_Y(), s.substring("font/".length(), s.length() - ".json".length()));
                List list = map.computeIfAbsent(resourcelocation1, p_215272_0_ -> Lists.newArrayList((Object[])new e_3495_r[]{new AllMissingGlyphProvider()}));
                profilerIn.n_1700_B(resourcelocation1::toString);
                try {
                    for (Resource iresource : resourceManagerIn.R_4764_Y(resourcelocation)) {
                        profilerIn.n_1700_B(iresource::R_4764_Y);
                        try (InputStream inputstream = iresource.J_1907_R();
                             BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));){
                            profilerIn.n_1700_B("reading");
                            JsonArray jsonarray = i_4431_W.P_4830_p(i_4431_W.n_1700_B(gson, (Reader)reader, JsonObject.class), "providers");
                            profilerIn.J_1907_R("parsing");
                            for (int i = jsonarray.size() - 1; i >= 0; --i) {
                                JsonObject jsonobject = i_4431_W.w_1484_f(jsonarray.get(i), "providers[" + i + "]");
                                try {
                                    String s1 = i_4431_W.u_1723_Y(jsonobject, "type");
                                    i_1930_v glyphprovidertypes = i_1930_v.n_1700_B(s1);
                                    profilerIn.n_1700_B(s1);
                                    e_3495_r iglyphprovider = glyphprovidertypes.n_1700_B(jsonobject).n_1700_B(resourceManagerIn);
                                    if (iglyphprovider != null) {
                                        list.add(iglyphprovider);
                                    }
                                    profilerIn.R_4764_Y();
                                    continue;
                                }
                                catch (RuntimeException runtimeexception) {
                                    J_1907_R.warn("Unable to read definition '{}' in fonts.json in resourcepack: '{}': {}", (Object)resourcelocation1, (Object)iresource.R_4764_Y(), (Object)runtimeexception.getMessage());
                                }
                            }
                            profilerIn.R_4764_Y();
                        }
                        catch (RuntimeException runtimeexception1) {
                            J_1907_R.warn("Unable to load font '{}' in fonts.json in resourcepack: '{}': {}", (Object)resourcelocation1, (Object)iresource.R_4764_Y(), (Object)runtimeexception1.getMessage());
                        }
                        profilerIn.R_4764_Y();
                    }
                }
                catch (IOException ioexception) {
                    J_1907_R.warn("Unable to load font '{}' in fonts.json: {}", (Object)resourcelocation1, (Object)ioexception.getMessage());
                }
                profilerIn.n_1700_B("caching");
                IntOpenHashSet intset = new IntOpenHashSet();
                for (e_3495_r iglyphprovider1 : list) {
                    intset.addAll((IntCollection)iglyphprovider1.n_1700_B());
                }
                intset.forEach(p_238555_1_ -> {
                    block1: {
                        e_3495_r iglyphprovider2;
                        if (p_238555_1_ == 32) break block1;
                        Iterator iterator = Lists.reverse((List)list).iterator();
                        while (iterator.hasNext() && (iglyphprovider2 = (e_3495_r)iterator.next()).n_1700_B(p_238555_1_) == null) {
                        }
                    }
                });
                profilerIn.R_4764_Y();
                profilerIn.R_4764_Y();
            }
            profilerIn.J_1907_R();
            return map;
        }

        protected void n_1700_B(Map<g_2336_b, List<e_3495_r>> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
            profilerIn.n_1700_B();
            profilerIn.n_1700_B("closing");
            o_3912_n.this.G_564_y.values().forEach(F_3283_z::close);
            o_3912_n.this.G_564_y.clear();
            profilerIn.J_1907_R("reloading");
            objectIn.forEach((p_238556_1_, p_238556_2_) -> {
                F_3283_z font = new F_3283_z(o_3912_n.this.P_1922_E, (g_2336_b)p_238556_1_);
                font.n_1700_B(Lists.reverse((List)p_238556_2_));
                o_3912_n.this.G_564_y.put((g_2336_b)p_238556_1_, font);
            });
            profilerIn.R_4764_Y();
            profilerIn.J_1907_R();
        }

        @Override
        public String i_() {
            return "FontManager";
        }

        @Override
        protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
            this.n_1700_B((Map)object, s_2107_a, x_2951_U);
        }

        @Override
        protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
            return this.n_1700_B(s_2107_a, x_2951_U);
        }
    };

    public o_3912_n(C_3240_x p_i49772_1_) {
        this.P_1922_E = p_i49772_1_;
        this.R_4764_Y = j_3341_s.n_1700_B(new F_3283_z(p_i49772_1_, n_1700_B), p_238550_0_ -> p_238550_0_.n_1700_B(Lists.newArrayList((Object[])new e_3495_r[]{new AllMissingGlyphProvider()})));
    }

    public void n_1700_B(Map<g_2336_b, g_2336_b> p_238551_1_) {
        this.u_1723_Y = p_238551_1_;
    }

    public Y_4083_F n_1700_B() {
        return new Y_4083_F(p_238552_1_ -> this.G_564_y.getOrDefault(this.u_1723_Y.getOrDefault(p_238552_1_, (g_2336_b)p_238552_1_), this.R_4764_Y));
    }

    public PreparableReloadListener J_1907_R() {
        return this.v_4262_N;
    }

    @Override
    public void close() {
        this.G_564_y.values().forEach(F_3283_z::close);
        this.R_4764_Y.close();
    }
}


