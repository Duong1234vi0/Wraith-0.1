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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.S_4022_R;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class l_4537_E
extends ValueObject {
    private static final Logger P_1922_E = LogManager.getLogger();
    public List<S_4022_R> n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;
    public int G_564_y;

    public l_4537_E() {
    }

    public l_4537_E(int p_i51733_1_) {
        this.n_1700_B = Collections.emptyList();
        this.J_1907_R = 0;
        this.R_4764_Y = p_i51733_1_;
        this.G_564_y = -1;
    }

    public static l_4537_E n_1700_B(String p_230804_0_) {
        l_4537_E worldtemplatepaginatedlist = new l_4537_E();
        worldtemplatepaginatedlist.n_1700_B = Lists.newArrayList();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230804_0_).getAsJsonObject();
            if (jsonobject.get("templates").isJsonArray()) {
                Iterator iterator = jsonobject.get("templates").getAsJsonArray().iterator();
                while (iterator.hasNext()) {
                    worldtemplatepaginatedlist.n_1700_B.add(S_4022_R.n_1700_B(((JsonElement)iterator.next()).getAsJsonObject()));
                }
            }
            worldtemplatepaginatedlist.J_1907_R = JsonUtils.n_1700_B("page", jsonobject, 0);
            worldtemplatepaginatedlist.R_4764_Y = JsonUtils.n_1700_B("size", jsonobject, 0);
            worldtemplatepaginatedlist.G_564_y = JsonUtils.n_1700_B("total", jsonobject, 0);
        }
        catch (Exception exception) {
            P_1922_E.error("Could not parse WorldTemplatePaginatedList: " + exception.getMessage());
        }
        return worldtemplatepaginatedlist;
    }
}


