/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.j_1564_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class M_1641_O
extends ValueObject {
    private static final Logger J_1907_R = LogManager.getLogger();
    public List<j_1564_a> n_1700_B;

    public static M_1641_O n_1700_B(String p_230786_0_) {
        M_1641_O realmsserverplayerlists = new M_1641_O();
        realmsserverplayerlists.n_1700_B = Lists.newArrayList();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230786_0_).getAsJsonObject();
            if (jsonobject.get("lists").isJsonArray()) {
                JsonArray jsonarray = jsonobject.get("lists").getAsJsonArray();
                Iterator iterator = jsonarray.iterator();
                while (iterator.hasNext()) {
                    realmsserverplayerlists.n_1700_B.add(j_1564_a.n_1700_B(((JsonElement)iterator.next()).getAsJsonObject()));
                }
            }
        }
        catch (Exception exception) {
            J_1907_R.error("Could not parse RealmsServerPlayerLists: " + exception.getMessage());
        }
        return realmsserverplayerlists;
    }
}


