/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
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
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.K_4074_S;
import lightning.product.MultiPartBakedModel;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.T_2915_h;
import lightning.product.MultiVariant;
import lightning.product.Y_1835_y;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.BlockModelDefinition;
import lightning.product.UnbakedModel;
import lightning.product.ModelState;
import lightning.product.Selector;

public class MultiPart
implements UnbakedModel {
    private final Y_1835_y<T_2915_h, K_4074_S> n_1700_B;
    private final List<Selector> J_1907_R;

    public MultiPart(Y_1835_y<T_2915_h, K_4074_S> stateContainerIn, List<Selector> selectorsIn) {
        this.n_1700_B = stateContainerIn;
        this.J_1907_R = selectorsIn;
    }

    public List<Selector> n_1700_B() {
        return this.J_1907_R;
    }

    public Set<MultiVariant> J_1907_R() {
        HashSet set = Sets.newHashSet();
        for (Selector selector : this.J_1907_R) {
            set.add(selector.n_1700_B());
        }
        return set;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof MultiPart)) {
            return false;
        }
        MultiPart multipart = (MultiPart)p_equals_1_;
        return Objects.equals(this.n_1700_B, multipart.n_1700_B) && Objects.equals(this.J_1907_R, multipart.J_1907_R);
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R);
    }

    @Override
    public Collection<g_2336_b> P_1922_E() {
        return this.n_1700_B().stream().flatMap(selector -> selector.n_1700_B().P_1922_E().stream()).collect(Collectors.toSet());
    }

    @Override
    public Collection<T_2910_P> n_1700_B(Function<g_2336_b, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return this.n_1700_B().stream().flatMap(selector -> selector.n_1700_B().n_1700_B(modelGetter, missingTextureErrors).stream()).collect(Collectors.toSet());
    }

    @Override
    @Nullable
    public S_3826_o n_1700_B(g_2561_p modelBakeryIn, Function<T_2910_P, B_3871_I> spriteGetterIn, ModelState transformIn, g_2336_b locationIn) {
        MultiPartBakedModel.n_1700_B multipartbakedmodel$builder = new MultiPartBakedModel.n_1700_B();
        for (Selector selector : this.n_1700_B()) {
            S_3826_o ibakedmodel = selector.n_1700_B().n_1700_B(modelBakeryIn, spriteGetterIn, transformIn, locationIn);
            if (ibakedmodel == null) continue;
            multipartbakedmodel$builder.n_1700_B(selector.n_1700_B(this.n_1700_B), ibakedmodel);
        }
        return multipartbakedmodel$builder.n_1700_B();
    }

    public static class n_1700_B
    implements JsonDeserializer<MultiPart> {
        private final BlockModelDefinition.n_1700_B n_1700_B;

        public n_1700_B(BlockModelDefinition.n_1700_B containerHolderIn) {
            this.n_1700_B = containerHolderIn;
        }

        public MultiPart n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            return new MultiPart(this.n_1700_B.n_1700_B(), this.n_1700_B(p_deserialize_3_, p_deserialize_1_.getAsJsonArray()));
        }

        private List<Selector> n_1700_B(JsonDeserializationContext context, JsonArray elements) {
            ArrayList list = Lists.newArrayList();
            for (JsonElement jsonelement : elements) {
                list.add((Selector)context.deserialize(jsonelement, Selector.class));
            }
            return list;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}


