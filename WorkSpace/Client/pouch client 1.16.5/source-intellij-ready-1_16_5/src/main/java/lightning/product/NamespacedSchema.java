/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.Const$PrimitiveType
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.codecs.PrimitiveCodec
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.Const;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import lightning.product.g_2336_b;

public class NamespacedSchema
extends Schema {
    public static final PrimitiveCodec<String> n_1700_B = new PrimitiveCodec<String>(){

        public <T> DataResult<String> read(DynamicOps<T> p_read_1_, T p_read_2_) {
            return p_read_1_.getStringValue(p_read_2_).map(NamespacedSchema::n_1700_B);
        }

        public <T> T n_1700_B(DynamicOps<T> p_write_1_, String p_write_2_) {
            return (T)p_write_1_.createString(p_write_2_);
        }

        public String toString() {
            return "NamespacedString";
        }

        public /* synthetic */ Object write(DynamicOps dynamicOps, Object object) {
            return this.n_1700_B(dynamicOps, (String)object);
        }
    };
    private static final Type<String> J_1907_R = new Const.PrimitiveType(n_1700_B);

    public NamespacedSchema(int versionKey, Schema schema) {
        super(versionKey, schema);
    }

    public static String n_1700_B(String string) {
        g_2336_b resourcelocation = g_2336_b.J_1907_R(string);
        return resourcelocation != null ? resourcelocation.toString() : string;
    }

    public static Type<String> n_1700_B() {
        return J_1907_R;
    }

    public Type<?> getChoiceType(DSL.TypeReference p_getChoiceType_1_, String p_getChoiceType_2_) {
        return super.getChoiceType(p_getChoiceType_1_, NamespacedSchema.n_1700_B(p_getChoiceType_2_));
    }
}


