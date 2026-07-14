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

public class F_1410_V
extends ValueObject {
    private static final Logger G_564_y = LogManager.getLogger();
    public String n_1700_B;
    public String J_1907_R;
    public String R_4764_Y;

    public static F_1410_V n_1700_B(String p_230802_0_) {
        JsonParser jsonparser = new JsonParser();
        JsonObject jsonobject = jsonparser.parse(p_230802_0_).getAsJsonObject();
        F_1410_V worlddownload = new F_1410_V();
        try {
            worlddownload.n_1700_B = JsonUtils.n_1700_B("downloadLink", jsonobject, "");
            worlddownload.J_1907_R = JsonUtils.n_1700_B("resourcePackUrl", jsonobject, "");
            worlddownload.R_4764_Y = JsonUtils.n_1700_B("resourcePackHash", jsonobject, "");
        }
        catch (Exception exception) {
            G_564_y.error("Could not parse WorldDownload: " + exception.getMessage());
        }
        return worlddownload;
    }
}


