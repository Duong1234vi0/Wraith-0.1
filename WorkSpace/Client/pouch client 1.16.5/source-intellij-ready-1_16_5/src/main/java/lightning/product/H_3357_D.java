/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.function.IntUnaryOperator;
import javax.annotation.Nullable;
import lightning.product.i_4431_W;
import lightning.product.u_530_F;

public class H_3357_D
implements IntUnaryOperator {
    private final Integer n_1700_B;
    private final Integer J_1907_R;
    private final IntUnaryOperator R_4764_Y;

    private H_3357_D(@Nullable Integer p_i51273_1_, @Nullable Integer p_i51273_2_) {
        this.n_1700_B = p_i51273_1_;
        this.J_1907_R = p_i51273_2_;
        if (p_i51273_1_ == null) {
            if (p_i51273_2_ == null) {
                this.R_4764_Y = p_215845_0_ -> p_215845_0_;
            } else {
                int i = p_i51273_2_;
                this.R_4764_Y = p_215844_1_ -> Math.min(i, p_215844_1_);
            }
        } else {
            int k = p_i51273_1_;
            if (p_i51273_2_ == null) {
                this.R_4764_Y = p_215846_1_ -> Math.max(k, p_215846_1_);
            } else {
                int j = p_i51273_2_;
                this.R_4764_Y = p_215847_2_ -> u_530_F.n_1700_B(p_215847_2_, k, j);
            }
        }
    }

    public static H_3357_D n_1700_B(int p_215843_0_, int p_215843_1_) {
        return new H_3357_D(p_215843_0_, p_215843_1_);
    }

    public static H_3357_D n_1700_B(int p_215848_0_) {
        return new H_3357_D(p_215848_0_, null);
    }

    public static H_3357_D J_1907_R(int p_215851_0_) {
        return new H_3357_D(null, p_215851_0_);
    }

    @Override
    public int applyAsInt(int p_applyAsInt_1_) {
        return this.R_4764_Y.applyAsInt(p_applyAsInt_1_);
    }

    public static class n_1700_B
    implements JsonDeserializer<H_3357_D>,
    JsonSerializer<H_3357_D> {
        public H_3357_D n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "value");
            Integer integer = jsonobject.has("min") ? Integer.valueOf(i_4431_W.u_2550_I(jsonobject, "min")) : null;
            Integer integer1 = jsonobject.has("max") ? Integer.valueOf(i_4431_W.u_2550_I(jsonobject, "max")) : null;
            return new H_3357_D(integer, integer1);
        }

        public JsonElement n_1700_B(H_3357_D p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            if (p_serialize_1_.J_1907_R != null) {
                jsonobject.addProperty("max", (Number)p_serialize_1_.J_1907_R);
            }
            if (p_serialize_1_.n_1700_B != null) {
                jsonobject.addProperty("min", (Number)p_serialize_1_.n_1700_B);
            }
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((H_3357_D)object, type, jsonSerializationContext);
        }
    }
}

