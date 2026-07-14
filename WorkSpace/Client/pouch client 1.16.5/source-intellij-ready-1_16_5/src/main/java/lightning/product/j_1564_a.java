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
import java.util.ArrayList;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class j_1564_a
extends ValueObject {
    private static final Logger R_4764_Y = LogManager.getLogger();
    private static final JsonParser G_564_y = new JsonParser();
    public long n_1700_B;
    public List<String> J_1907_R;

    public static j_1564_a n_1700_B(JsonObject p_230785_0_) {
        j_1564_a realmsserverplayerlist = new j_1564_a();
        try {
            JsonElement jsonelement;
            realmsserverplayerlist.n_1700_B = JsonUtils.n_1700_B("serverId", p_230785_0_, -1L);
            String s = JsonUtils.n_1700_B("playerList", p_230785_0_, null);
            realmsserverplayerlist.J_1907_R = s != null ? ((jsonelement = G_564_y.parse(s)).isJsonArray() ? j_1564_a.n_1700_B(jsonelement.getAsJsonArray()) : Lists.newArrayList()) : Lists.newArrayList();
        }
        catch (Exception exception) {
            R_4764_Y.error("Could not parse RealmsServerPlayerList: " + exception.getMessage());
        }
        return realmsserverplayerlist;
    }

    private static List<String> n_1700_B(JsonArray p_230784_0_) {
        ArrayList list = Lists.newArrayList();
        for (JsonElement jsonelement : p_230784_0_) {
            try {
                list.add(jsonelement.getAsString());
            }
            catch (Exception exception) {}
        }
        return list;
    }
}


