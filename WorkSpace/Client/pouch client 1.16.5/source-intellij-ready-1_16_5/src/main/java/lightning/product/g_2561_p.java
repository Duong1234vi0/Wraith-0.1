/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.base.Splitter
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.tuple.Triple
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.B_1814_Y;
import lightning.product.B_3871_I;
import lightning.product.C_3240_x;
import lightning.product.F_3565_Q;
import lightning.product.K_4074_S;
import lightning.product.L_3848_p;
import lightning.product.AtlasSet;
import lightning.product.Resource;
import lightning.product.MultiPart;
import lightning.product.O_2369_F;
import lightning.product.BellRenderer;
import lightning.product.ConduitRenderer;
import lightning.product.ResourceManager;
import lightning.product.S_3779_r;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.V_3137_a;
import lightning.product.ProfilerFiller;
import lightning.product.MultiVariant;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_4440_Q;
import lightning.product.d_1062_x;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_4467_X;
import lightning.product.BlockModelDefinition;
import lightning.product.Transformation;
import lightning.product.o_2576_A;
import lightning.product.o_3047_I;
import lightning.product.BlockModelShaper;
import lightning.product.EnchantTableRenderer;
import lightning.product.v_3760_Q;
import lightning.product.UnbakedModel;
import lightning.product.ModelState;
import lightning.product.Selector;
import lightning.product.y_6_Q;
import net.optifine.reflect.Reflector;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class g_2561_p {
    public static final T_2910_P n_1700_B = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("block/fire_0"));
    public static final T_2910_P J_1907_R = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("block/fire_1"));
    public static final T_2910_P R_4764_Y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("block/lava_flow"));
    public static final T_2910_P G_564_y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("block/water_flow"));
    public static final T_2910_P P_1922_E = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("block/water_overlay"));
    public static final T_2910_P u_1723_Y = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/banner_base"));
    public static final T_2910_P v_4262_N = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/shield_base"));
    public static final T_2910_P w_1484_f = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/shield_base_nopattern"));
    public static final List<g_2336_b> t_148_a = IntStream.range(0, 10).mapToObj(p_lambda$static$0_0_ -> new g_2336_b("block/destroy_stage_" + p_lambda$static$0_0_)).collect(Collectors.toList());
    public static final List<g_2336_b> s_956_w = t_148_a.stream().map(p_lambda$static$1_0_ -> new g_2336_b("textures/" + p_lambda$static$1_0_.J_1907_R() + ".png")).collect(Collectors.toList());
    public static final List<o_2576_A> u_2550_I = s_956_w.stream().map(o_2576_A::Q_4569_t).collect(Collectors.toList());
    private static final Set<T_2910_P> t_1786_h = j_3341_s.n_1700_B(Sets.newHashSet(), (T p_lambda$static$2_0_) -> {
        p_lambda$static$2_0_.add(G_564_y);
        p_lambda$static$2_0_.add(R_4764_Y);
        p_lambda$static$2_0_.add(P_1922_E);
        p_lambda$static$2_0_.add(n_1700_B);
        p_lambda$static$2_0_.add(J_1907_R);
        p_lambda$static$2_0_.add(BellRenderer.n_1700_B);
        p_lambda$static$2_0_.add(ConduitRenderer.n_1700_B);
        p_lambda$static$2_0_.add(ConduitRenderer.J_1907_R);
        p_lambda$static$2_0_.add(ConduitRenderer.R_4764_Y);
        p_lambda$static$2_0_.add(ConduitRenderer.G_564_y);
        p_lambda$static$2_0_.add(ConduitRenderer.P_1922_E);
        p_lambda$static$2_0_.add(ConduitRenderer.u_1723_Y);
        p_lambda$static$2_0_.add(EnchantTableRenderer.n_1700_B);
        p_lambda$static$2_0_.add(u_1723_Y);
        p_lambda$static$2_0_.add(v_4262_N);
        p_lambda$static$2_0_.add(w_1484_f);
        for (g_2336_b resourcelocation : t_148_a) {
            p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, resourcelocation));
        }
        p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, y_6_Q.J_1907_R));
        p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, y_6_Q.R_4764_Y));
        p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, y_6_Q.G_564_y));
        p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, y_6_Q.v_4262_N));
        p_lambda$static$2_0_.add(new T_2910_P(L_3848_p.n_1700_B, y_6_Q.w_1484_f));
        b_4440_Q.n_1700_B(p_lambda$static$2_0_::add);
    });
    private static final Logger multiplayerClientSuggestionProvider = LogManager.getLogger();
    public static final d_1062_x M_588_G = new d_1062_x("builtin/missing", "missing");
    private static final String w_1457_N = M_588_G.toString();
    @VisibleForTesting
    public static final String P_4830_p = ("{    'textures': {       'particle': '" + F_3565_Q.n_1700_B().J_1907_R() + "',       'missingno': '" + F_3565_Q.n_1700_B().J_1907_R() + "'    },    'elements': [         {  'from': [ 0, 0, 0 ],            'to': [ 16, 16, 16 ],            'faces': {                'down':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'down',  'texture': '#missingno' },                'up':    { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'up',    'texture': '#missingno' },                'north': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'north', 'texture': '#missingno' },                'south': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'south', 'texture': '#missingno' },                'west':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'west',  'texture': '#missingno' },                'east':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'east',  'texture': '#missingno' }            }        }    ]}").replace('\'', '\"');
    private static final Map<String, String> Y_601_j = Maps.newHashMap((Map)ImmutableMap.of((Object)"missing", (Object)P_4830_p));
    private static final Splitter Y_259_p = Splitter.on((char)',');
    private static final Splitter Q_2552_b = Splitter.on((char)'=').limit(2);
    public static final o_3047_I h_1847_R = j_3341_s.n_1700_B(o_3047_I.n_1700_B("{\"gui_light\": \"front\"}"), (T p_lambda$static$3_0_) -> {
        p_lambda$static$3_0_.J_1907_R = "generation marker";
    });
    public static final o_3047_I Q_4569_t = j_3341_s.n_1700_B(o_3047_I.n_1700_B("{\"gui_light\": \"side\"}"), (T p_lambda$static$4_0_) -> {
        p_lambda$static$4_0_.J_1907_R = "block entity marker";
    });
    private static final Y_1835_y<T_2915_h, K_4074_S> C_2741_M = new Y_1835_y.n_1700_B(a_3742_W.n_1700_B).n_1700_B(new v_3760_Q[]{U_1266_O.n_1700_B("map")}).n_1700_B(T_2915_h::multiplayerClientSuggestionProvider, K_4074_S::new);
    private static final B_1814_Y k_2293_S = new B_1814_Y();
    private static final Map<g_2336_b, Y_1835_y<T_2915_h, K_4074_S>> q_2307_F = ImmutableMap.of((Object)new g_2336_b("item_frame"), C_2741_M);
    private final ResourceManager Z_875_P;
    @Nullable
    private AtlasSet c_3005_b;
    private final k_4467_X H_2857_Y;
    private final Set<g_2336_b> A_4115_X = Sets.newHashSet();
    private final BlockModelDefinition.n_1700_B Y_1740_V = new BlockModelDefinition.n_1700_B();
    private final Map<g_2336_b, UnbakedModel> t_4043_B = Maps.newHashMap();
    private final Map<Triple<g_2336_b, Transformation, Boolean>, S_3826_o> x_607_J = Maps.newHashMap();
    private final Map<g_2336_b, UnbakedModel> e_4240_b = Maps.newHashMap();
    private final Map<g_2336_b, S_3826_o> n_3318_d = Maps.newHashMap();
    private Map<g_2336_b, Pair<L_3848_p, L_3848_p.n_1700_B>> d_2427_y;
    private int z_1737_N = 1;
    private final Object2IntMap<K_4074_S> v_4276_D = (Object2IntMap)j_3341_s.n_1700_B(new Object2IntOpenHashMap(), (T p_lambda$new$5_0_) -> p_lambda$new$5_0_.defaultReturnValue(-1));
    public Map<g_2336_b, UnbakedModel> M_182_A;

    public g_2561_p(ResourceManager resourceManagerIn, k_4467_X blockColorsIn, ProfilerFiller profilerIn, int maxMipmapLevel) {
        this(resourceManagerIn, blockColorsIn, true);
        this.n_1700_B(profilerIn, maxMipmapLevel);
    }

    protected g_2561_p(ResourceManager p_i242117_1_, k_4467_X p_i242117_2_, boolean p_i242117_3_) {
        this.Z_875_P = p_i242117_1_;
        this.H_2857_Y = p_i242117_2_;
    }

    protected void n_1700_B(ProfilerFiller p_processLoading_1_, int p_processLoading_2_) {
        Reflector.ModelLoaderRegistry_onModelLoadingStart.callVoid(new Object[0]);
        p_processLoading_1_.n_1700_B("missing_model");
        try {
            this.t_4043_B.put(M_588_G, this.G_564_y(M_588_G));
            this.n_1700_B(M_588_G);
        }
        catch (IOException ioexception) {
            multiplayerClientSuggestionProvider.error("Error loading missing model, should never happen :(", (Throwable)ioexception);
            throw new RuntimeException(ioexception);
        }
        p_processLoading_1_.J_1907_R("static_definitions");
        q_2307_F.forEach((p_lambda$processLoading$7_1_, p_lambda$processLoading$7_2_) -> p_lambda$processLoading$7_2_.n_1700_B().forEach(p_lambda$null$6_2_ -> this.n_1700_B(BlockModelShaper.n_1700_B(p_lambda$processLoading$7_1_, p_lambda$null$6_2_))));
        p_processLoading_1_.J_1907_R("blocks");
        for (T_2915_h block : V_3137_a.q_4610_l) {
            block.t_1786_h().n_1700_B().forEach(p_lambda$processLoading$8_1_ -> this.n_1700_B(BlockModelShaper.R_4764_Y(p_lambda$processLoading$8_1_)));
        }
        p_processLoading_1_.J_1907_R("items");
        for (g_2336_b resourcelocation : V_3137_a.e_2887_G.G_564_y()) {
            this.n_1700_B(new d_1062_x(resourcelocation, "inventory"));
        }
        p_processLoading_1_.J_1907_R("special");
        this.n_1700_B(new d_1062_x("minecraft:trident_in_hand#inventory"));
        for (g_2336_b resourcelocation1 : this.R_4764_Y()) {
            this.R_4764_Y(resourcelocation1);
        }
        p_processLoading_1_.J_1907_R("textures");
        this.M_182_A = this.t_4043_B;
        TextureUtils.registerCustomModels(this);
        LinkedHashSet set = Sets.newLinkedHashSet();
        Set set1 = this.e_4240_b.values().stream().flatMap(p_lambda$processLoading$9_2_ -> p_lambda$processLoading$9_2_.n_1700_B(this::n_1700_B, set).stream()).collect(Collectors.toSet());
        set1.addAll(t_1786_h);
        Reflector.call(Reflector.ForgeHooksClient_gatherFluidTextures, set1);
        set.stream().filter(p_lambda$processLoading$10_0_ -> !((String)p_lambda$processLoading$10_0_.getSecond()).equals(w_1457_N)).forEach(p_lambda$processLoading$11_0_ -> multiplayerClientSuggestionProvider.warn("Unable to resolve texture reference: {} in {}", p_lambda$processLoading$11_0_.getFirst(), p_lambda$processLoading$11_0_.getSecond()));
        Map<g_2336_b, List<T_2910_P>> map = set1.stream().collect(Collectors.groupingBy(T_2910_P::n_1700_B));
        p_processLoading_1_.J_1907_R("stitching");
        this.d_2427_y = Maps.newHashMap();
        for (Map.Entry<g_2336_b, List<T_2910_P>> entry : map.entrySet()) {
            L_3848_p atlastexture = new L_3848_p(entry.getKey());
            L_3848_p.n_1700_B atlastexture$sheetdata = atlastexture.n_1700_B(this.Z_875_P, entry.getValue().stream().map(T_2910_P::J_1907_R), p_processLoading_1_, p_processLoading_2_);
            this.d_2427_y.put(entry.getKey(), (Pair<L_3848_p, L_3848_p.n_1700_B>)Pair.of((Object)atlastexture, (Object)atlastexture$sheetdata));
        }
        p_processLoading_1_.R_4764_Y();
    }

    public AtlasSet n_1700_B(C_3240_x resourceManagerIn, ProfilerFiller profilerIn) {
        profilerIn.n_1700_B("atlas");
        for (Pair<L_3848_p, L_3848_p.n_1700_B> pair : this.d_2427_y.values()) {
            L_3848_p atlastexture = (L_3848_p)pair.getFirst();
            L_3848_p.n_1700_B atlastexture$sheetdata = (L_3848_p.n_1700_B)pair.getSecond();
            atlastexture.n_1700_B(atlastexture$sheetdata);
            resourceManagerIn.n_1700_B(atlastexture.R_4764_Y(), atlastexture);
            resourceManagerIn.n_1700_B(atlastexture.R_4764_Y());
            atlastexture.J_1907_R(atlastexture$sheetdata);
        }
        this.c_3005_b = new AtlasSet(this.d_2427_y.values().stream().map(Pair::getFirst).collect(Collectors.toList()));
        profilerIn.J_1907_R("baking");
        this.e_4240_b.keySet().forEach(p_lambda$uploadTextures$12_1_ -> {
            S_3826_o ibakedmodel = null;
            try {
                ibakedmodel = this.n_1700_B((g_2336_b)p_lambda$uploadTextures$12_1_, S_3779_r.n_1700_B);
            }
            catch (Exception exception) {
                multiplayerClientSuggestionProvider.warn("Unable to bake model: '{}': {}", p_lambda$uploadTextures$12_1_, (Object)exception);
            }
            if (ibakedmodel != null) {
                this.n_3318_d.put((g_2336_b)p_lambda$uploadTextures$12_1_, ibakedmodel);
            }
        });
        profilerIn.R_4764_Y();
        return this.c_3005_b;
    }

    private static Predicate<K_4074_S> n_1700_B(Y_1835_y<T_2915_h, K_4074_S> containerIn, String variantIn) {
        HashMap map = Maps.newHashMap();
        for (String s : Y_259_p.split((CharSequence)variantIn)) {
            Iterator iterator = Q_2552_b.split((CharSequence)s).iterator();
            if (!iterator.hasNext()) continue;
            String s1 = (String)iterator.next();
            v_3760_Q<?> property = containerIn.n_1700_B(s1);
            if (property != null && iterator.hasNext()) {
                String s2 = (String)iterator.next();
                Object comparable = g_2561_p.n_1700_B(property, s2);
                if (comparable == null) {
                    throw new RuntimeException("Unknown value: '" + s2 + "' for blockstate property: '" + s1 + "' " + String.valueOf(property.n_1700_B()));
                }
                map.put(property, comparable);
                continue;
            }
            if (s1.isEmpty()) continue;
            throw new RuntimeException("Unknown blockstate property: '" + s1 + "'");
        }
        T_2915_h block = containerIn.R_4764_Y();
        return p_lambda$parseVariantKey$13_2_ -> {
            if (p_lambda$parseVariantKey$13_2_ != null && block == p_lambda$parseVariantKey$13_2_.J_1907_R()) {
                for (Map.Entry entry : map.entrySet()) {
                    if (Objects.equals(p_lambda$parseVariantKey$13_2_.R_4764_Y((v_3760_Q)entry.getKey()), entry.getValue())) continue;
                    return false;
                }
                return true;
            }
            return false;
        };
    }

    @Nullable
    static <T extends Comparable<T>> T n_1700_B(v_3760_Q<T> property, String value) {
        return (T)property.J_1907_R(value).orElse(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public UnbakedModel n_1700_B(g_2336_b modelLocation) {
        if (this.t_4043_B.containsKey(modelLocation)) {
            return this.t_4043_B.get(modelLocation);
        }
        if (this.A_4115_X.contains(modelLocation)) {
            throw new IllegalStateException("Circular reference while loading " + String.valueOf(modelLocation));
        }
        this.A_4115_X.add(modelLocation);
        UnbakedModel iunbakedmodel = this.t_4043_B.get(M_588_G);
        while (!this.A_4115_X.isEmpty()) {
            g_2336_b resourcelocation = this.A_4115_X.iterator().next();
            try {
                if (this.t_4043_B.containsKey(resourcelocation)) continue;
                this.J_1907_R(resourcelocation);
            }
            catch (n_1700_B modelbakery$blockstatedefinitionexception) {
                multiplayerClientSuggestionProvider.warn(modelbakery$blockstatedefinitionexception.getMessage());
                this.t_4043_B.put(resourcelocation, iunbakedmodel);
            }
            catch (Exception exception) {
                multiplayerClientSuggestionProvider.warn("Unable to load model: '{}' referenced from: {}: {}", (Object)resourcelocation, (Object)modelLocation);
                multiplayerClientSuggestionProvider.warn(exception.getClass().getName() + ": " + exception.getMessage());
                this.t_4043_B.put(resourcelocation, iunbakedmodel);
            }
            finally {
                this.A_4115_X.remove(resourcelocation);
            }
        }
        return this.t_4043_B.getOrDefault(modelLocation, iunbakedmodel);
    }

    private void J_1907_R(g_2336_b blockstateLocation) throws Exception {
        if (!(blockstateLocation instanceof d_1062_x)) {
            this.n_1700_B(blockstateLocation, (UnbakedModel)this.G_564_y(blockstateLocation));
        } else {
            d_1062_x modelresourcelocation = (d_1062_x)blockstateLocation;
            if (Objects.equals(modelresourcelocation.n_1700_B(), "inventory")) {
                g_2336_b resourcelocation2 = new g_2336_b(blockstateLocation.R_4764_Y(), "item/" + blockstateLocation.J_1907_R());
                String s = blockstateLocation.J_1907_R();
                if (s.startsWith("optifine/") || s.startsWith("item/")) {
                    resourcelocation2 = blockstateLocation;
                }
                o_3047_I blockmodel = this.G_564_y(resourcelocation2);
                this.n_1700_B((g_2336_b)modelresourcelocation, (UnbakedModel)blockmodel);
                this.t_4043_B.put(resourcelocation2, blockmodel);
            } else {
                g_2336_b resourcelocation = new g_2336_b(blockstateLocation.R_4764_Y(), blockstateLocation.J_1907_R());
                Y_1835_y statecontainer = Optional.ofNullable(q_2307_F.get(resourcelocation)).orElseGet(() -> V_3137_a.q_4610_l.n_1700_B(resourcelocation).t_1786_h());
                this.Y_1740_V.n_1700_B(statecontainer);
                ImmutableList list = ImmutableList.copyOf(this.H_2857_Y.n_1700_B((T_2915_h)statecontainer.R_4764_Y()));
                ImmutableList immutablelist = statecontainer.n_1700_B();
                HashMap map = Maps.newHashMap();
                immutablelist.forEach(p_lambda$loadBlockstate$15_2_ -> {
                    K_4074_S blockstate = map.put(BlockModelShaper.n_1700_B(resourcelocation, p_lambda$loadBlockstate$15_2_), p_lambda$loadBlockstate$15_2_);
                });
                HashMap map1 = Maps.newHashMap();
                g_2336_b resourcelocation1 = new g_2336_b(blockstateLocation.R_4764_Y(), "blockstates/" + blockstateLocation.J_1907_R() + ".json");
                UnbakedModel iunbakedmodel = this.t_4043_B.get(M_588_G);
                J_1907_R modelbakery$modellistwrapper = new J_1907_R((List<UnbakedModel>)ImmutableList.of((Object)iunbakedmodel), (List<Object>)ImmutableList.of());
                Pair pair = Pair.of((Object)iunbakedmodel, () -> modelbakery$modellistwrapper);
                try {
                    List list1;
                    try {
                        list1 = this.Z_875_P.R_4764_Y(resourcelocation1).stream().map(p_lambda$loadBlockstate$17_1_ -> {
                            Pair pair;
                            block8: {
                                InputStream inputstream = p_lambda$loadBlockstate$17_1_.J_1907_R();
                                try {
                                    pair = Pair.of((Object)p_lambda$loadBlockstate$17_1_.R_4764_Y(), (Object)BlockModelDefinition.n_1700_B(this.Y_1740_V, new InputStreamReader(inputstream, StandardCharsets.UTF_8)));
                                    if (inputstream == null) break block8;
                                }
                                catch (Throwable throwable) {
                                    try {
                                        if (inputstream != null) {
                                            try {
                                                inputstream.close();
                                            }
                                            catch (Throwable throwable2) {
                                                throwable.addSuppressed(throwable2);
                                            }
                                        }
                                        throw throwable;
                                    }
                                    catch (Exception exception11) {
                                        throw new n_1700_B(String.format("Exception loading blockstate definition: '%s' in resourcepack: '%s': %s", p_lambda$loadBlockstate$17_1_.n_1700_B(), p_lambda$loadBlockstate$17_1_.R_4764_Y(), exception11.getMessage()));
                                    }
                                }
                                inputstream.close();
                            }
                            return pair;
                        }).collect(Collectors.toList());
                    }
                    catch (IOException ioexception) {
                        multiplayerClientSuggestionProvider.warn("Exception loading blockstate definition: {}: {}", (Object)resourcelocation1, (Object)ioexception);
                        HashMap lvt_20_1_ = Maps.newHashMap();
                        map.forEach((p_lambda$loadBlockstate$25_5_, p_lambda$loadBlockstate$25_6_) -> {
                            Pair pair2 = (Pair)map1.get(p_lambda$loadBlockstate$25_6_);
                            if (pair2 == null) {
                                multiplayerClientSuggestionProvider.warn("Exception loading blockstate definition: '{}' missing model for variant: '{}'", (Object)resourcelocation1, p_lambda$loadBlockstate$25_5_);
                                pair2 = pair;
                            }
                            this.n_1700_B((g_2336_b)p_lambda$loadBlockstate$25_5_, (UnbakedModel)pair2.getFirst());
                            try {
                                J_1907_R modelbakery$modellistwrapper1 = (J_1907_R)((Supplier)pair2.getSecond()).get();
                                ((Set)lvt_20_1_.computeIfAbsent(modelbakery$modellistwrapper1, p_lambda$null$24_0_ -> Sets.newIdentityHashSet())).add(p_lambda$loadBlockstate$25_6_);
                            }
                            catch (Exception exception11) {
                                multiplayerClientSuggestionProvider.warn("Exception evaluating model definition: '{}'", p_lambda$loadBlockstate$25_5_, (Object)exception11);
                            }
                        });
                        lvt_20_1_.forEach((p_lambda$loadBlockstate$26_1_, p_lambda$loadBlockstate$26_2_) -> {
                            Iterator iterator = ((Set)p_lambda$loadBlockstate$26_2_).iterator();
                            while (iterator.hasNext()) {
                                K_4074_S blockstate = (K_4074_S)iterator.next();
                                if (blockstate.w_1484_f() == O_2369_F.R_4764_Y) continue;
                                iterator.remove();
                                this.v_4276_D.put((Object)blockstate, 0);
                            }
                            if (((Set)p_lambda$loadBlockstate$26_2_).size() > 1) {
                                this.n_1700_B((Set)p_lambda$loadBlockstate$26_2_);
                            }
                        });
                        return;
                    }
                    for (Pair pair1 : list1) {
                        MultiPart multipart;
                        BlockModelDefinition blockmodeldefinition = (BlockModelDefinition)pair1.getSecond();
                        IdentityHashMap map2 = Maps.newIdentityHashMap();
                        if (blockmodeldefinition.J_1907_R()) {
                            multipart = blockmodeldefinition.R_4764_Y();
                            immutablelist.forEach(arg_0 -> g_2561_p.n_1700_B(map2, multipart, (List)list, arg_0));
                        } else {
                            multipart = null;
                        }
                        blockmodeldefinition.n_1700_B().forEach((arg_0, arg_1) -> g_2561_p.n_1700_B(immutablelist, statecontainer, map2, (List)list, multipart, pair, blockmodeldefinition, resourcelocation1, pair1, arg_0, arg_1));
                        map1.putAll(map2);
                    }
                    return;
                }
                catch (n_1700_B modelbakery$blockstatedefinitionexception) {
                    throw modelbakery$blockstatedefinitionexception;
                }
                catch (Exception exception1) {
                    throw new n_1700_B(String.format("Exception loading blockstate definition: '%s': %s", resourcelocation1, exception1));
                }
                finally {
                    HashMap lvt_20_1_ = Maps.newHashMap();
                    map.forEach((p_lambda$loadBlockstate$25_5_, p_lambda$loadBlockstate$25_6_) -> {
                        Pair pair2 = (Pair)map1.get(p_lambda$loadBlockstate$25_6_);
                        if (pair2 == null) {
                            multiplayerClientSuggestionProvider.warn("Exception loading blockstate definition: '{}' missing model for variant: '{}'", (Object)resourcelocation1, p_lambda$loadBlockstate$25_5_);
                            pair2 = pair;
                        }
                        this.n_1700_B((g_2336_b)p_lambda$loadBlockstate$25_5_, (UnbakedModel)pair2.getFirst());
                        try {
                            J_1907_R modelbakery$modellistwrapper1 = (J_1907_R)((Supplier)pair2.getSecond()).get();
                            ((Set)lvt_20_1_.computeIfAbsent(modelbakery$modellistwrapper1, p_lambda$null$24_0_ -> Sets.newIdentityHashSet())).add(p_lambda$loadBlockstate$25_6_);
                        }
                        catch (Exception exception11) {
                            multiplayerClientSuggestionProvider.warn("Exception evaluating model definition: '{}'", p_lambda$loadBlockstate$25_5_, (Object)exception11);
                        }
                    });
                    lvt_20_1_.forEach((p_lambda$loadBlockstate$26_1_, p_lambda$loadBlockstate$26_2_) -> {
                        Iterator iterator = ((Set)p_lambda$loadBlockstate$26_2_).iterator();
                        while (iterator.hasNext()) {
                            K_4074_S blockstate = (K_4074_S)iterator.next();
                            if (blockstate.w_1484_f() == O_2369_F.R_4764_Y) continue;
                            iterator.remove();
                            this.v_4276_D.put((Object)blockstate, 0);
                        }
                        if (((Set)p_lambda$loadBlockstate$26_2_).size() > 1) {
                            this.n_1700_B((Set)p_lambda$loadBlockstate$26_2_);
                        }
                    });
                }
            }
        }
    }

    private void n_1700_B(g_2336_b locationIn, UnbakedModel modelIn) {
        this.t_4043_B.put(locationIn, modelIn);
        this.A_4115_X.addAll(modelIn.P_1922_E());
    }

    private void R_4764_Y(g_2336_b p_addModelToCache_1_) {
        UnbakedModel iunbakedmodel = this.n_1700_B(p_addModelToCache_1_);
        this.t_4043_B.put(p_addModelToCache_1_, iunbakedmodel);
        this.e_4240_b.put(p_addModelToCache_1_, iunbakedmodel);
    }

    public void n_1700_B(d_1062_x locationIn) {
        UnbakedModel iunbakedmodel = this.n_1700_B((g_2336_b)locationIn);
        this.t_4043_B.put(locationIn, iunbakedmodel);
        this.e_4240_b.put(locationIn, iunbakedmodel);
    }

    private void n_1700_B(Iterable<K_4074_S> blockStatesIn) {
        int i = this.z_1737_N++;
        blockStatesIn.forEach(p_lambda$registerModelIds$27_2_ -> this.v_4276_D.put(p_lambda$registerModelIds$27_2_, i));
    }

    @Nullable
    public S_3826_o n_1700_B(g_2336_b locationIn, ModelState transformIn) {
        return this.n_1700_B(locationIn, transformIn, this.c_3005_b::n_1700_B);
    }

    public S_3826_o n_1700_B(g_2336_b p_getBakedModel_1_, ModelState p_getBakedModel_2_, Function<T_2910_P, B_3871_I> p_getBakedModel_3_) {
        o_3047_I blockmodel;
        Triple triple = Triple.of((Object)p_getBakedModel_1_, (Object)p_getBakedModel_2_.n_1700_B(), (Object)p_getBakedModel_2_.J_1907_R());
        if (this.x_607_J.containsKey(triple)) {
            return this.x_607_J.get(triple);
        }
        if (this.c_3005_b == null) {
            throw new IllegalStateException("bake called too early");
        }
        UnbakedModel iunbakedmodel = this.n_1700_B(p_getBakedModel_1_);
        if (iunbakedmodel instanceof o_3047_I && (blockmodel = (o_3047_I)iunbakedmodel).u_1723_Y() == h_1847_R) {
            if (Reflector.ForgeHooksClient.exists()) {
                return k_2293_S.n_1700_B(p_getBakedModel_3_, blockmodel).n_1700_B(this, blockmodel, p_getBakedModel_3_, p_getBakedModel_2_, p_getBakedModel_1_, false);
            }
            return k_2293_S.n_1700_B(this.c_3005_b::n_1700_B, blockmodel).n_1700_B(this, blockmodel, this.c_3005_b::n_1700_B, p_getBakedModel_2_, p_getBakedModel_1_, false);
        }
        S_3826_o ibakedmodel = iunbakedmodel.n_1700_B(this, this.c_3005_b::n_1700_B, p_getBakedModel_2_, p_getBakedModel_1_);
        if (Reflector.ForgeHooksClient.exists()) {
            ibakedmodel = iunbakedmodel.n_1700_B(this, p_getBakedModel_3_, p_getBakedModel_2_, p_getBakedModel_1_);
        }
        this.x_607_J.put((Triple<g_2336_b, Transformation, Boolean>)triple, ibakedmodel);
        return ibakedmodel;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private o_3047_I G_564_y(g_2336_b location) throws IOException {
        o_3047_I basePath;
        Resource iresource;
        Reader reader;
        block9: {
            g_2336_b resourcelocation;
            String s;
            block8: {
                reader = null;
                iresource = null;
                s = location.J_1907_R();
                resourcelocation = location;
                if (!"builtin/generated".equals(s)) break block8;
                o_3047_I o_3047_I2 = h_1847_R;
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(iresource);
                return o_3047_I2;
            }
            if ("builtin/entity".equals(s)) break block9;
            if (s.startsWith("builtin/")) {
                String s2 = s.substring("builtin/".length());
                String s1 = Y_601_j.get(s2);
                if (s1 == null) {
                    throw new FileNotFoundException(location.toString());
                }
                reader = new StringReader(s1);
            } else {
                resourcelocation = this.P_1922_E(location);
                iresource = this.Z_875_P.n_1700_B(resourcelocation);
                reader = new InputStreamReader(iresource.J_1907_R(), StandardCharsets.UTF_8);
            }
            o_3047_I blockmodel = o_3047_I.n_1700_B(reader);
            blockmodel.J_1907_R = location.toString();
            String s3 = TextureUtils.getBasePath(resourcelocation.J_1907_R());
            g_2561_p.n_1700_B(blockmodel, s3);
            o_3047_I o_3047_I3 = blockmodel;
            IOUtils.closeQuietly((Reader)reader);
            IOUtils.closeQuietly((Closeable)iresource);
            return o_3047_I3;
        }
        try {
            basePath = Q_4569_t;
        }
        finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(iresource);
        }
        return basePath;
    }

    public Map<g_2336_b, S_3826_o> n_1700_B() {
        return this.n_3318_d;
    }

    public Object2IntMap<K_4074_S> J_1907_R() {
        return this.v_4276_D;
    }

    private g_2336_b P_1922_E(g_2336_b p_getModelLocation_1_) {
        String s = p_getModelLocation_1_.J_1907_R();
        if (s.startsWith("optifine/")) {
            if (!s.endsWith(".json")) {
                p_getModelLocation_1_ = new g_2336_b(p_getModelLocation_1_.R_4764_Y(), s + ".json");
            }
            return p_getModelLocation_1_;
        }
        return new g_2336_b(p_getModelLocation_1_.R_4764_Y(), "models/" + p_getModelLocation_1_.J_1907_R() + ".json");
    }

    public static void n_1700_B(o_3047_I p_fixModelLocations_0_, String p_fixModelLocations_1_) {
        g_2336_b resourcelocation = g_2561_p.n_1700_B(p_fixModelLocations_0_.P_1922_E, p_fixModelLocations_1_);
        if (resourcelocation != p_fixModelLocations_0_.P_1922_E) {
            p_fixModelLocations_0_.P_1922_E = resourcelocation;
        }
        if (p_fixModelLocations_0_.R_4764_Y != null) {
            for (Map.Entry<String, Either<T_2910_P, String>> entry : p_fixModelLocations_0_.R_4764_Y.entrySet()) {
                T_2910_P rendermaterial;
                g_2336_b resourcelocation1;
                String s;
                String s1;
                Either<T_2910_P, String> either = entry.getValue();
                Optional optional = either.left();
                if (!optional.isPresent() || (s1 = g_2561_p.n_1700_B(s = (resourcelocation1 = (rendermaterial = (T_2910_P)optional.get()).J_1907_R()).J_1907_R(), p_fixModelLocations_1_)).equals(s)) continue;
                g_2336_b resourcelocation2 = new g_2336_b(resourcelocation1.R_4764_Y(), s1);
                T_2910_P rendermaterial1 = new T_2910_P(rendermaterial.n_1700_B(), resourcelocation2);
                Either either1 = Either.left((Object)rendermaterial1);
                entry.setValue((Either<T_2910_P, String>)either1);
            }
        }
    }

    public static g_2336_b n_1700_B(g_2336_b p_fixModelLocation_0_, String p_fixModelLocation_1_) {
        if (p_fixModelLocation_0_ != null && p_fixModelLocation_1_ != null) {
            if (!p_fixModelLocation_0_.R_4764_Y().equals("minecraft")) {
                return p_fixModelLocation_0_;
            }
            String s = p_fixModelLocation_0_.J_1907_R();
            String s1 = g_2561_p.n_1700_B(s, p_fixModelLocation_1_);
            if (s1 != s) {
                p_fixModelLocation_0_ = new g_2336_b(p_fixModelLocation_0_.R_4764_Y(), s1);
            }
            return p_fixModelLocation_0_;
        }
        return p_fixModelLocation_0_;
    }

    private static String n_1700_B(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
        p_fixResourcePath_0_ = TextureUtils.fixResourcePath(p_fixResourcePath_0_, p_fixResourcePath_1_);
        p_fixResourcePath_0_ = StrUtils.removeSuffix(p_fixResourcePath_0_, ".json");
        return StrUtils.removeSuffix(p_fixResourcePath_0_, ".png");
    }

    public Set<g_2336_b> R_4764_Y() {
        return Collections.emptySet();
    }

    public AtlasSet G_564_y() {
        return this.c_3005_b;
    }

    private static /* synthetic */ void n_1700_B(ImmutableList immutablelist, Y_1835_y statecontainer, Map map2, List list, MultiPart multipart, Pair pair, BlockModelDefinition blockmodeldefinition, g_2336_b resourcelocation1, Pair pair1, String p_lambda$loadBlockstate$23_9_, MultiVariant p_lambda$loadBlockstate$23_10_) {
        try {
            immutablelist.stream().filter(g_2561_p.n_1700_B(statecontainer, p_lambda$loadBlockstate$23_9_)).forEach(p_lambda$null$22_6_ -> {
                Pair pair2 = map2.put(p_lambda$null$22_6_, Pair.of((Object)p_lambda$loadBlockstate$23_10_, () -> lightning.product.g_2561_p$J_1907_R.n_1700_B(p_lambda$null$22_6_, p_lambda$loadBlockstate$23_10_, list)));
                if (pair2 != null && pair2.getFirst() != multipart) {
                    map2.put(p_lambda$null$22_6_, pair);
                    throw new RuntimeException("Overlapping definition with: " + (String)blockmodeldefinition.n_1700_B().entrySet().stream().filter(p_lambda$null$21_1_ -> p_lambda$null$21_1_.getValue() == pair2.getFirst()).findFirst().get().getKey());
                }
            });
        }
        catch (Exception exception1) {
            multiplayerClientSuggestionProvider.warn("Exception loading blockstate definition: '{}' in resourcepack: '{}' for variant: '{}': {}", (Object)resourcelocation1, pair1.getFirst(), (Object)p_lambda$loadBlockstate$23_9_, (Object)exception1.getMessage());
        }
    }

    private static /* synthetic */ void n_1700_B(Map map2, MultiPart multipart, List list, K_4074_S p_lambda$loadBlockstate$19_3_) {
        Pair pair2 = map2.put(p_lambda$loadBlockstate$19_3_, Pair.of((Object)multipart, () -> lightning.product.g_2561_p$J_1907_R.n_1700_B(p_lambda$loadBlockstate$19_3_, multipart, list)));
    }

    static class n_1700_B
    extends RuntimeException {
        public n_1700_B(String message) {
            super(message);
        }
    }

    static class J_1907_R {
        private final List<UnbakedModel> n_1700_B;
        private final List<Object> J_1907_R;

        public J_1907_R(List<UnbakedModel> modelsIn, List<Object> colorValuesIn) {
            this.n_1700_B = modelsIn;
            this.J_1907_R = colorValuesIn;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof J_1907_R)) {
                return false;
            }
            J_1907_R modelbakery$modellistwrapper = (J_1907_R)p_equals_1_;
            return Objects.equals(this.n_1700_B, modelbakery$modellistwrapper.n_1700_B) && Objects.equals(this.J_1907_R, modelbakery$modellistwrapper.J_1907_R);
        }

        public int hashCode() {
            return 31 * this.n_1700_B.hashCode() + this.J_1907_R.hashCode();
        }

        public static J_1907_R n_1700_B(K_4074_S blockStateIn, MultiPart multipartIn, Collection<v_3760_Q<?>> propertiesIn) {
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = blockStateIn.J_1907_R().t_1786_h();
            List list = (List)multipartIn.n_1700_B().stream().filter(p_lambda$makeWrapper$0_2_ -> p_lambda$makeWrapper$0_2_.n_1700_B(statecontainer).test(blockStateIn)).map(Selector::n_1700_B).collect(ImmutableList.toImmutableList());
            List<Object> list1 = lightning.product.g_2561_p$J_1907_R.n_1700_B(blockStateIn, propertiesIn);
            return new J_1907_R(list, list1);
        }

        public static J_1907_R n_1700_B(K_4074_S blockStateIn, UnbakedModel modelIn, Collection<v_3760_Q<?>> propertiesIn) {
            List<Object> list = lightning.product.g_2561_p$J_1907_R.n_1700_B(blockStateIn, propertiesIn);
            return new J_1907_R((List<UnbakedModel>)ImmutableList.of((Object)modelIn), list);
        }

        private static List<Object> n_1700_B(K_4074_S blockStateIn, Collection<v_3760_Q<?>> propertiesIn) {
            return (List)propertiesIn.stream().map(blockStateIn::R_4764_Y).collect(ImmutableList.toImmutableList());
        }
    }
}


