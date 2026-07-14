/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.WeightedBakedModel;
import lightning.product.o_25_d;
import lightning.product.UnbakedModel;
import lightning.product.ModelState;

public class MultiVariant
implements UnbakedModel {
    private final List<o_25_d> n_1700_B;

    public MultiVariant(List<o_25_d> variantListIn) {
        this.n_1700_B = variantListIn;
    }

    public List<o_25_d> n_1700_B() {
        return this.n_1700_B;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof MultiVariant) {
            MultiVariant variantlist = (MultiVariant)p_equals_1_;
            return this.n_1700_B.equals(variantlist.n_1700_B);
        }
        return false;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    @Override
    public Collection<g_2336_b> P_1922_E() {
        return this.n_1700_B().stream().map(o_25_d::R_4764_Y).collect(Collectors.toSet());
    }

    @Override
    public Collection<T_2910_P> n_1700_B(Function<g_2336_b, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return this.n_1700_B().stream().map(o_25_d::R_4764_Y).distinct().flatMap(variantModel -> ((UnbakedModel)modelGetter.apply((g_2336_b)variantModel)).n_1700_B(modelGetter, missingTextureErrors).stream()).collect(Collectors.toSet());
    }

    @Override
    @Nullable
    public S_3826_o n_1700_B(g_2561_p modelBakeryIn, Function<T_2910_P, B_3871_I> spriteGetterIn, ModelState transformIn, g_2336_b locationIn) {
        if (this.n_1700_B().isEmpty()) {
            return null;
        }
        WeightedBakedModel.n_1700_B weightedbakedmodel$builder = new WeightedBakedModel.n_1700_B();
        for (o_25_d variant : this.n_1700_B()) {
            S_3826_o ibakedmodel = modelBakeryIn.n_1700_B(variant.R_4764_Y(), variant);
            weightedbakedmodel$builder.n_1700_B(ibakedmodel, variant.G_564_y());
        }
        return weightedbakedmodel$builder.n_1700_B();
    }

    public static class n_1700_B
    implements JsonDeserializer<MultiVariant> {
        public MultiVariant n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            ArrayList list = Lists.newArrayList();
            if (p_deserialize_1_.isJsonArray()) {
                JsonArray jsonarray = p_deserialize_1_.getAsJsonArray();
                if (jsonarray.size() == 0) {
                    throw new JsonParseException("Empty variant array");
                }
                for (JsonElement jsonelement : jsonarray) {
                    list.add((o_25_d)p_deserialize_3_.deserialize(jsonelement, o_25_d.class));
                }
            } else {
                list.add((o_25_d)p_deserialize_3_.deserialize(p_deserialize_1_, o_25_d.class));
            }
            return new MultiVariant(list);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}


