/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.h_4320_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class t_4219_U
extends ValueObject {
    private static final Logger J_1907_R = LogManager.getLogger();
    public List<h_4320_q> n_1700_B = Lists.newArrayList();

    public static t_4219_U n_1700_B(String p_230756_0_) {
        t_4219_U pendinginviteslist = new t_4219_U();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230756_0_).getAsJsonObject();
            if (jsonobject.get("invites").isJsonArray()) {
                Iterator iterator = jsonobject.get("invites").getAsJsonArray().iterator();
                while (iterator.hasNext()) {
                    pendinginviteslist.n_1700_B.add(h_4320_q.n_1700_B(((JsonElement)iterator.next()).getAsJsonObject()));
                }
            }
        }
        catch (Exception exception) {
            J_1907_R.error("Could not parse PendingInvitesList: " + exception.getMessage());
        }
        return pendinginviteslist;
    }
}


