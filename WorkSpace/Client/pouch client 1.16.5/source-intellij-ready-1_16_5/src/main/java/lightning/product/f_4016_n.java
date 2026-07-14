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
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class f_4016_n {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final String J_1907_R;
    private final int R_4764_Y;

    private f_4016_n(String p_i241823_1_, int p_i241823_2_) {
        this.J_1907_R = p_i241823_1_;
        this.R_4764_Y = p_i241823_2_;
    }

    public static f_4016_n n_1700_B(String p_241826_0_) {
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_241826_0_).getAsJsonObject();
            String s = JsonUtils.n_1700_B("errorMsg", jsonobject, "");
            int i = JsonUtils.n_1700_B("errorCode", jsonobject, -1);
            return new f_4016_n(s, i);
        }
        catch (Exception exception) {
            n_1700_B.error("Could not parse RealmsError: " + exception.getMessage());
            n_1700_B.error("The error was: " + p_241826_0_);
            return new f_4016_n("Failed to parse response from server", -1);
        }
    }

    public String n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }
}


