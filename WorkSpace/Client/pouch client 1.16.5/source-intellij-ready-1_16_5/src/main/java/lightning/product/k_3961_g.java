/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.D_60_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_3961_g
extends ValueObject {
    private static final Logger J_1907_R = LogManager.getLogger();
    public List<D_60_a> n_1700_B;

    public static k_3961_g n_1700_B(String p_230753_0_) {
        JsonParser jsonparser = new JsonParser();
        k_3961_g backuplist = new k_3961_g();
        backuplist.n_1700_B = Lists.newArrayList();
        try {
            JsonElement jsonelement = jsonparser.parse(p_230753_0_).getAsJsonObject().get("backups");
            if (jsonelement.isJsonArray()) {
                Iterator iterator = jsonelement.getAsJsonArray().iterator();
                while (iterator.hasNext()) {
                    backuplist.n_1700_B.add(D_60_a.n_1700_B((JsonElement)iterator.next()));
                }
            }
        }
        catch (Exception exception) {
            J_1907_R.error("Could not parse BackupList: " + exception.getMessage());
        }
        return backuplist;
    }
}


