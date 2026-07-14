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
import lightning.product.q_1982_R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class w_612_n
extends ValueObject {
    private static final Logger J_1907_R = LogManager.getLogger();
    public List<q_1982_R> n_1700_B;

    public static w_612_n n_1700_B(String p_230783_0_) {
        w_612_n realmsserverlist = new w_612_n();
        realmsserverlist.n_1700_B = Lists.newArrayList();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230783_0_).getAsJsonObject();
            if (jsonobject.get("servers").isJsonArray()) {
                JsonArray jsonarray = jsonobject.get("servers").getAsJsonArray();
                Iterator iterator = jsonarray.iterator();
                while (iterator.hasNext()) {
                    realmsserverlist.n_1700_B.add(q_1982_R.n_1700_B(((JsonElement)iterator.next()).getAsJsonObject()));
                }
            }
        }
        catch (Exception exception) {
            J_1907_R.error("Could not parse McoServerList: " + exception.getMessage());
        }
        return realmsserverlist;
    }
}


