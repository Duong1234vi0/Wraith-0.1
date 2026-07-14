/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.ValueObject;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class S_4022_R
extends ValueObject {
    private static final Logger s_956_w = LogManager.getLogger();
    public String n_1700_B = "";
    public String J_1907_R = "";
    public String R_4764_Y = "";
    public String G_564_y = "";
    public String P_1922_E = "";
    @Nullable
    public String u_1723_Y;
    public String v_4262_N = "";
    public String w_1484_f = "";
    public n_1700_B t_148_a = lightning.product.S_4022_R$n_1700_B.n_1700_B;

    public static S_4022_R n_1700_B(JsonObject p_230803_0_) {
        S_4022_R worldtemplate = new S_4022_R();
        try {
            worldtemplate.n_1700_B = JsonUtils.n_1700_B("id", p_230803_0_, "");
            worldtemplate.J_1907_R = JsonUtils.n_1700_B("name", p_230803_0_, "");
            worldtemplate.R_4764_Y = JsonUtils.n_1700_B("version", p_230803_0_, "");
            worldtemplate.G_564_y = JsonUtils.n_1700_B("author", p_230803_0_, "");
            worldtemplate.P_1922_E = JsonUtils.n_1700_B("link", p_230803_0_, "");
            worldtemplate.u_1723_Y = JsonUtils.n_1700_B("image", p_230803_0_, null);
            worldtemplate.v_4262_N = JsonUtils.n_1700_B("trailer", p_230803_0_, "");
            worldtemplate.w_1484_f = JsonUtils.n_1700_B("recommendedPlayers", p_230803_0_, "");
            worldtemplate.t_148_a = lightning.product.S_4022_R$n_1700_B.valueOf(JsonUtils.n_1700_B("type", p_230803_0_, lightning.product.S_4022_R$n_1700_B.n_1700_B.name()));
        }
        catch (Exception exception) {
            s_956_w.error("Could not parse WorldTemplate: " + exception.getMessage());
        }
        return worldtemplate;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.S_4022_R$n_1700_B.n_1700_B();
        }
    }
}


