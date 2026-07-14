/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.O_2592_x;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.k_4690_i;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.ItemPropertyFunction;

public class ItemOverride {
    private final g_2336_b n_1700_B;
    private final Map<g_2336_b, Float> J_1907_R;

    public ItemOverride(g_2336_b locationIn, Map<g_2336_b, Float> propertyValues) {
        this.n_1700_B = locationIn;
        this.J_1907_R = propertyValues;
    }

    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    boolean n_1700_B(Z_1993_T stack, @Nullable k_4690_i world, @Nullable r_4811_B livingEntity) {
        q_1613_l item = stack.J_1907_R();
        for (Map.Entry<g_2336_b, Float> entry : this.J_1907_R.entrySet()) {
            ItemPropertyFunction iitempropertygetter = O_2592_x.n_1700_B(item, entry.getKey());
            if (iitempropertygetter != null && !(iitempropertygetter.call(stack, world, livingEntity) < entry.getValue().floatValue())) continue;
            return false;
        }
        return true;
    }

    public static class n_1700_B
    implements JsonDeserializer<ItemOverride> {
        protected n_1700_B() {
        }

        public ItemOverride n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "model"));
            Map<g_2336_b, Float> map = this.n_1700_B(jsonobject);
            return new ItemOverride(resourcelocation, map);
        }

        protected Map<g_2336_b, Float> n_1700_B(JsonObject json) {
            LinkedHashMap map = Maps.newLinkedHashMap();
            JsonObject jsonobject = i_4431_W.M_588_G(json, "predicate");
            for (Map.Entry entry : jsonobject.entrySet()) {
                map.put(new g_2336_b((String)entry.getKey()), Float.valueOf(i_4431_W.G_564_y((JsonElement)entry.getValue(), (String)entry.getKey())));
            }
            return map;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}


