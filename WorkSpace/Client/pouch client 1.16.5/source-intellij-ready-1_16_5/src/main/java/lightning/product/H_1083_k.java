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

public class H_1083_k
extends ValueObject {
    private static final Logger G_564_y = LogManager.getLogger();
    public long n_1700_B;
    public int J_1907_R;
    public n_1700_B R_4764_Y = lightning.product.H_1083_k$n_1700_B.n_1700_B;

    public static H_1083_k n_1700_B(String p_230793_0_) {
        H_1083_k subscription = new H_1083_k();
        try {
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = jsonparser.parse(p_230793_0_).getAsJsonObject();
            subscription.n_1700_B = JsonUtils.n_1700_B("startDate", jsonobject, 0L);
            subscription.J_1907_R = JsonUtils.n_1700_B("daysLeft", jsonobject, 0);
            subscription.R_4764_Y = H_1083_k.J_1907_R(JsonUtils.n_1700_B("subscriptionType", jsonobject, lightning.product.H_1083_k$n_1700_B.n_1700_B.name()));
        }
        catch (Exception exception) {
            G_564_y.error("Could not parse Subscription: " + exception.getMessage());
        }
        return subscription;
    }

    private static n_1700_B J_1907_R(String p_230794_0_) {
        try {
            return lightning.product.H_1083_k$n_1700_B.valueOf(p_230794_0_);
        }
        catch (Exception exception) {
            return lightning.product.H_1083_k$n_1700_B.n_1700_B;
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.H_1083_k$n_1700_B.n_1700_B();
        }
    }
}


