/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSyntaxException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.Container;
import lightning.product.P_2507_S;
import lightning.product.NonNullList;
import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.ProfilerFiller;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class G_3474_H
extends P_2507_S {
    private static final Gson n_1700_B = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger J_1907_R = LogManager.getLogger();
    private Map<RecipeType<?>, Map<g_2336_b, Recipe<?>>> R_4764_Y = ImmutableMap.of();
    private boolean G_564_y;

    public G_3474_H() {
        super(n_1700_B, "recipes");
    }

    protected void n_1700_B(Map<g_2336_b, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        this.G_564_y = false;
        HashMap map = Maps.newHashMap();
        for (Map.Entry<g_2336_b, JsonElement> entry : objectIn.entrySet()) {
            g_2336_b resourcelocation = entry.getKey();
            try {
                Recipe<?> irecipe = G_3474_H.n_1700_B(resourcelocation, i_4431_W.w_1484_f(entry.getValue(), "top element"));
                map.computeIfAbsent(irecipe.v_4262_N(), recipeType -> ImmutableMap.builder()).put((Object)resourcelocation, irecipe);
            }
            catch (JsonParseException | IllegalArgumentException jsonparseexception) {
                J_1907_R.error("Parsing error loading recipe {}", (Object)resourcelocation, (Object)jsonparseexception);
            }
        }
        this.R_4764_Y = (Map)map.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, recipeEntry -> ((ImmutableMap.Builder)recipeEntry.getValue()).build()));
        J_1907_R.info("Loaded {} recipes", (Object)map.size());
    }

    public <C extends Container, T extends Recipe<C>> Optional<T> n_1700_B(RecipeType<T> recipeTypeIn, C inventoryIn, b_4507_u worldIn) {
        return this.J_1907_R(recipeTypeIn).values().stream().flatMap(recipe -> j_3341_s.n_1700_B(recipeTypeIn.n_1700_B(recipe, worldIn, inventoryIn))).findFirst();
    }

    public <C extends Container, T extends Recipe<C>> List<T> n_1700_B(RecipeType<T> recipeType) {
        return this.J_1907_R(recipeType).values().stream().map(recipe -> recipe).collect(Collectors.toList());
    }

    public <C extends Container, T extends Recipe<C>> List<T> J_1907_R(RecipeType<T> recipeTypeIn, C inventoryIn, b_4507_u worldIn) {
        return this.J_1907_R(recipeTypeIn).values().stream().flatMap(recipe -> j_3341_s.n_1700_B(recipeTypeIn.n_1700_B(recipe, worldIn, inventoryIn))).sorted(Comparator.comparing(recipe -> recipe.R_4764_Y().s_956_w())).collect(Collectors.toList());
    }

    private <C extends Container, T extends Recipe<C>> Map<g_2336_b, Recipe<C>> J_1907_R(RecipeType<T> recipeTypeIn) {
        return this.R_4764_Y.getOrDefault(recipeTypeIn, Collections.emptyMap());
    }

    public <C extends Container, T extends Recipe<C>> NonNullList<Z_1993_T> R_4764_Y(RecipeType<T> recipeTypeIn, C inventoryIn, b_4507_u worldIn) {
        Optional<T> optional = this.n_1700_B(recipeTypeIn, inventoryIn, worldIn);
        if (optional.isPresent()) {
            return ((Recipe)optional.get()).J_1907_R(inventoryIn);
        }
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B(inventoryIn.Y_259_p(), Z_1993_T.J_1907_R);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            nonnulllist.set(i, inventoryIn.s_956_w(i));
        }
        return nonnulllist;
    }

    public Optional<? extends Recipe<?>> n_1700_B(g_2336_b recipeId) {
        return this.R_4764_Y.values().stream().map(recipeMap -> (Recipe)recipeMap.get(recipeId)).filter(Objects::nonNull).findFirst();
    }

    public Collection<Recipe<?>> J_1907_R() {
        return this.R_4764_Y.values().stream().flatMap(recipeMap -> recipeMap.values().stream()).collect(Collectors.toSet());
    }

    public Stream<g_2336_b> R_4764_Y() {
        return this.R_4764_Y.values().stream().flatMap(recipeMap -> recipeMap.keySet().stream());
    }

    public static Recipe<?> n_1700_B(g_2336_b recipeId, JsonObject json) {
        String s = i_4431_W.u_1723_Y(json, "type");
        return V_3137_a.s_2632_s.J_1907_R(new g_2336_b(s)).orElseThrow(() -> new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'")).J_1907_R(recipeId, json);
    }

    public void n_1700_B(Iterable<Recipe<?>> recipes) {
        this.G_564_y = false;
        HashMap map = Maps.newHashMap();
        recipes.forEach(recipe -> {
            Map map1 = map.computeIfAbsent(recipe.v_4262_N(), recipeType -> Maps.newHashMap());
            Recipe irecipe = map1.put(recipe.u_1723_Y(), recipe);
            if (irecipe != null) {
                throw new IllegalStateException("Duplicate recipe ignored with ID " + String.valueOf(recipe.u_1723_Y()));
            }
        });
        this.R_4764_Y = ImmutableMap.copyOf((Map)map);
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((Map)object, s_2107_a, x_2951_U);
    }
}


