/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.MultiPart;
import lightning.product.T_2915_h;
import lightning.product.MultiVariant;
import lightning.product.Y_1835_y;
import lightning.product.i_4431_W;
import lightning.product.o_25_d;
import lightning.product.Selector;

public class BlockModelDefinition {
    private final Map<String, MultiVariant> n_1700_B = Maps.newLinkedHashMap();
    private MultiPart J_1907_R;

    public static BlockModelDefinition n_1700_B(n_1700_B containerHolderIn, Reader readerIn) {
        return i_4431_W.n_1700_B(containerHolderIn.n_1700_B, readerIn, BlockModelDefinition.class);
    }

    public BlockModelDefinition(Map<String, MultiVariant> variants, MultiPart multipartIn) {
        this.J_1907_R = multipartIn;
        this.n_1700_B.putAll(variants);
    }

    public BlockModelDefinition(List<BlockModelDefinition> definitions) {
        BlockModelDefinition blockmodeldefinition = null;
        for (BlockModelDefinition blockmodeldefinition1 : definitions) {
            if (blockmodeldefinition1.J_1907_R()) {
                this.n_1700_B.clear();
                blockmodeldefinition = blockmodeldefinition1;
            }
            this.n_1700_B.putAll(blockmodeldefinition1.n_1700_B);
        }
        if (blockmodeldefinition != null) {
            this.J_1907_R = blockmodeldefinition.J_1907_R;
        }
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof BlockModelDefinition) {
            BlockModelDefinition blockmodeldefinition = (BlockModelDefinition)p_equals_1_;
            if (this.n_1700_B.equals(blockmodeldefinition.n_1700_B)) {
                return this.J_1907_R() ? this.J_1907_R.equals(blockmodeldefinition.J_1907_R) : !blockmodeldefinition.J_1907_R();
            }
        }
        return false;
    }

    public int hashCode() {
        return 31 * this.n_1700_B.hashCode() + (this.J_1907_R() ? this.J_1907_R.hashCode() : 0);
    }

    public Map<String, MultiVariant> n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.J_1907_R != null;
    }

    public MultiPart R_4764_Y() {
        return this.J_1907_R;
    }

    public static final class n_1700_B {
        protected final Gson n_1700_B = new GsonBuilder().registerTypeAdapter(BlockModelDefinition.class, (Object)new J_1907_R()).registerTypeAdapter(o_25_d.class, (Object)new o_25_d.n_1700_B()).registerTypeAdapter(MultiVariant.class, (Object)new MultiVariant.n_1700_B()).registerTypeAdapter(MultiPart.class, (Object)new MultiPart.n_1700_B(this)).registerTypeAdapter(Selector.class, (Object)new Selector.n_1700_B()).create();
        private Y_1835_y<T_2915_h, K_4074_S> J_1907_R;

        public Y_1835_y<T_2915_h, K_4074_S> n_1700_B() {
            return this.J_1907_R;
        }

        public void n_1700_B(Y_1835_y<T_2915_h, K_4074_S> stateContainerIn) {
            this.J_1907_R = stateContainerIn;
        }
    }

    public static class J_1907_R
    implements JsonDeserializer<BlockModelDefinition> {
        public BlockModelDefinition n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            Map<String, MultiVariant> map = this.n_1700_B(p_deserialize_3_, jsonobject);
            MultiPart multipart = this.J_1907_R(p_deserialize_3_, jsonobject);
            if (!map.isEmpty() || multipart != null && !multipart.J_1907_R().isEmpty()) {
                return new BlockModelDefinition(map, multipart);
            }
            throw new JsonParseException("Neither 'variants' nor 'multipart' found");
        }

        protected Map<String, MultiVariant> n_1700_B(JsonDeserializationContext deserializationContext, JsonObject object) {
            HashMap map = Maps.newHashMap();
            if (object.has("variants")) {
                JsonObject jsonobject = i_4431_W.M_588_G(object, "variants");
                for (Map.Entry entry : jsonobject.entrySet()) {
                    map.put((String)entry.getKey(), (MultiVariant)deserializationContext.deserialize((JsonElement)entry.getValue(), MultiVariant.class));
                }
            }
            return map;
        }

        @Nullable
        protected MultiPart J_1907_R(JsonDeserializationContext deserializationContext, JsonObject object) {
            if (!object.has("multipart")) {
                return null;
            }
            JsonArray jsonarray = i_4431_W.P_4830_p(object, "multipart");
            return (MultiPart)deserializationContext.deserialize((JsonElement)jsonarray, MultiPart.class);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}


