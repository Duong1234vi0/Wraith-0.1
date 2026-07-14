/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class ValueObject {
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("{");
        for (Field field : this.getClass().getFields()) {
            if (ValueObject.J_1907_R(field)) continue;
            try {
                stringbuilder.append(ValueObject.n_1700_B(field)).append("=").append(field.get(this)).append(" ");
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    private static String n_1700_B(Field p_237702_0_) {
        SerializedName serializedname = p_237702_0_.getAnnotation(SerializedName.class);
        return serializedname != null ? serializedname.value() : p_237702_0_.getName();
    }

    private static boolean J_1907_R(Field p_230801_0_) {
        return Modifier.isStatic(p_230801_0_.getModifiers());
    }
}


