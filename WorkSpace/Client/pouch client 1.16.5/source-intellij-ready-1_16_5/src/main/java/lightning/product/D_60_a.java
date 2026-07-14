/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Date;
import java.util.Map;
import lightning.product.ValueObject;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D_60_a
extends ValueObject {
    private static final Logger u_1723_Y = LogManager.getLogger();
    public String n_1700_B;
    public Date J_1907_R;
    public long R_4764_Y;
    private boolean v_4262_N;
    public Map<String, String> G_564_y = Maps.newHashMap();
    public Map<String, String> P_1922_E = Maps.newHashMap();

    public static D_60_a n_1700_B(JsonElement p_230750_0_) {
        JsonObject jsonobject = p_230750_0_.getAsJsonObject();
        D_60_a backup = new D_60_a();
        try {
            backup.n_1700_B = JsonUtils.n_1700_B("backupId", jsonobject, "");
            backup.J_1907_R = JsonUtils.n_1700_B("lastModifiedDate", jsonobject);
            backup.R_4764_Y = JsonUtils.n_1700_B("size", jsonobject, 0L);
            if (jsonobject.has("metadata")) {
                JsonObject jsonobject1 = jsonobject.getAsJsonObject("metadata");
                for (Map.Entry entry : jsonobject1.entrySet()) {
                    if (((JsonElement)entry.getValue()).isJsonNull()) continue;
                    backup.G_564_y.put(D_60_a.n_1700_B((String)entry.getKey()), ((JsonElement)entry.getValue()).getAsString());
                }
            }
        }
        catch (Exception exception) {
            u_1723_Y.error("Could not parse Backup: " + exception.getMessage());
        }
        return backup;
    }

    private static String n_1700_B(String p_230751_0_) {
        String[] astring = p_230751_0_.split("_");
        StringBuilder stringbuilder = new StringBuilder();
        for (String s : astring) {
            if (s == null || s.length() < 1) continue;
            if ("of".equals(s)) {
                stringbuilder.append(s).append(" ");
                continue;
            }
            char c0 = Character.toUpperCase(s.charAt(0));
            stringbuilder.append(c0).append(s.substring(1)).append(" ");
        }
        return stringbuilder.toString();
    }

    public boolean n_1700_B() {
        return this.v_4262_N;
    }

    public void n_1700_B(boolean p_230752_1_) {
        this.v_4262_N = p_230752_1_;
    }
}


