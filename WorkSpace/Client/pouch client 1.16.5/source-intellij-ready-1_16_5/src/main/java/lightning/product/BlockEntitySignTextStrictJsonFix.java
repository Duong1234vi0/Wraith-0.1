/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.lang.reflect.Type;
import lightning.product.MutableComponent;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.i_4431_W;
import lightning.product.NamedEntityFix;
import lightning.product.x_282_a;
import org.apache.commons.lang3.StringUtils;

public class BlockEntitySignTextStrictJsonFix
extends NamedEntityFix {
    public static final Gson n_1700_B = new GsonBuilder().registerTypeAdapter(x_282_a.class, (Object)new JsonDeserializer<x_282_a>(){

        public MutableComponent n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            if (p_deserialize_1_.isJsonPrimitive()) {
                return new U_2871_b(p_deserialize_1_.getAsString());
            }
            if (p_deserialize_1_.isJsonArray()) {
                JsonArray jsonarray = p_deserialize_1_.getAsJsonArray();
                MutableComponent iformattabletextcomponent = null;
                for (JsonElement jsonelement : jsonarray) {
                    MutableComponent iformattabletextcomponent1 = this.n_1700_B(jsonelement, jsonelement.getClass(), p_deserialize_3_);
                    if (iformattabletextcomponent == null) {
                        iformattabletextcomponent = iformattabletextcomponent1;
                        continue;
                    }
                    iformattabletextcomponent.n_1700_B(iformattabletextcomponent1);
                }
                return iformattabletextcomponent;
            }
            throw new JsonParseException("Don't know how to turn " + String.valueOf(p_deserialize_1_) + " into a Component");
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }).create();

    public BlockEntitySignTextStrictJsonFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntitySignTextStrictJsonFix", References.u_2550_I, "Sign");
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209647_1_, String p_209647_2_) {
        String s = p_209647_1_.get(p_209647_2_).asString("");
        x_282_a itextcomponent = null;
        if (!"null".equals(s) && !StringUtils.isEmpty((CharSequence)s)) {
            if (s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"' || s.charAt(0) == '{' && s.charAt(s.length() - 1) == '}') {
                try {
                    itextcomponent = i_4431_W.n_1700_B(n_1700_B, s, x_282_a.class, true);
                    if (itextcomponent == null) {
                        itextcomponent = U_2871_b.R_4764_Y;
                    }
                }
                catch (JsonParseException jsonParseException) {
                    // empty catch block
                }
                if (itextcomponent == null) {
                    try {
                        itextcomponent = x_282_a.n_1700_B.n_1700_B(s);
                    }
                    catch (JsonParseException jsonParseException) {
                        // empty catch block
                    }
                }
                if (itextcomponent == null) {
                    try {
                        itextcomponent = x_282_a.n_1700_B.J_1907_R(s);
                    }
                    catch (JsonParseException jsonParseException) {
                        // empty catch block
                    }
                }
                if (itextcomponent == null) {
                    itextcomponent = new U_2871_b(s);
                }
            } else {
                itextcomponent = new U_2871_b(s);
            }
        } else {
            itextcomponent = U_2871_b.R_4764_Y;
        }
        return p_209647_1_.set(p_209647_2_, p_209647_1_.createString(x_282_a.n_1700_B.n_1700_B(itextcomponent)));
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), p_206380_1_ -> {
            p_206380_1_ = this.n_1700_B((Dynamic<?>)p_206380_1_, "Text1");
            p_206380_1_ = this.n_1700_B((Dynamic<?>)p_206380_1_, "Text2");
            p_206380_1_ = this.n_1700_B((Dynamic<?>)p_206380_1_, "Text3");
            return this.n_1700_B((Dynamic<?>)p_206380_1_, "Text4");
        });
    }
}


