/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Date;

public class JsonUtils {
    public static String n_1700_B(String p_225171_0_, JsonObject p_225171_1_, String p_225171_2_) {
        JsonElement jsonelement = p_225171_1_.get(p_225171_0_);
        if (jsonelement != null) {
            return jsonelement.isJsonNull() ? p_225171_2_ : jsonelement.getAsString();
        }
        return p_225171_2_;
    }

    public static int n_1700_B(String p_225172_0_, JsonObject p_225172_1_, int p_225172_2_) {
        JsonElement jsonelement = p_225172_1_.get(p_225172_0_);
        if (jsonelement != null) {
            return jsonelement.isJsonNull() ? p_225172_2_ : jsonelement.getAsInt();
        }
        return p_225172_2_;
    }

    public static long n_1700_B(String p_225169_0_, JsonObject p_225169_1_, long p_225169_2_) {
        JsonElement jsonelement = p_225169_1_.get(p_225169_0_);
        if (jsonelement != null) {
            return jsonelement.isJsonNull() ? p_225169_2_ : jsonelement.getAsLong();
        }
        return p_225169_2_;
    }

    public static boolean n_1700_B(String p_225170_0_, JsonObject p_225170_1_, boolean p_225170_2_) {
        JsonElement jsonelement = p_225170_1_.get(p_225170_0_);
        if (jsonelement != null) {
            return jsonelement.isJsonNull() ? p_225170_2_ : jsonelement.getAsBoolean();
        }
        return p_225170_2_;
    }

    public static Date n_1700_B(String p_225173_0_, JsonObject p_225173_1_) {
        JsonElement jsonelement = p_225173_1_.get(p_225173_0_);
        return jsonelement != null ? new Date(Long.parseLong(jsonelement.getAsString())) : new Date();
    }
}


