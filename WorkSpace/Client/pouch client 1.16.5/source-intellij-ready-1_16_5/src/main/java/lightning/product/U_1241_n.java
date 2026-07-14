/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lightning.product.ValueObject;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_1241_n
extends ValueObject {
    private static final Logger J_1907_R = LogManager.getLogger();
    public String n_1700_B;

    public static U_1241_n n_1700_B(String p_230767_0_) {
        U_1241_n realmsnews = new U_1241_n();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230767_0_).getAsJsonObject();
            realmsnews.n_1700_B = JsonUtils.n_1700_B("newsLink", jsonobject, null);
        }
        catch (Exception exception) {
            J_1907_R.error("Could not parse RealmsNews: " + exception.getMessage());
        }
        return realmsnews;
    }
}


