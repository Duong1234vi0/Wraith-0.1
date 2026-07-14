/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.Date;
import lightning.product.ValueObject;
import lightning.product.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class h_4320_q
extends ValueObject {
    private static final Logger u_1723_Y = LogManager.getLogger();
    public String n_1700_B;
    public String J_1907_R;
    public String R_4764_Y;
    public String G_564_y;
    public Date P_1922_E;

    public static h_4320_q n_1700_B(JsonObject p_230755_0_) {
        h_4320_q pendinginvite = new h_4320_q();
        try {
            pendinginvite.n_1700_B = JsonUtils.n_1700_B("invitationId", p_230755_0_, "");
            pendinginvite.J_1907_R = JsonUtils.n_1700_B("worldName", p_230755_0_, "");
            pendinginvite.R_4764_Y = JsonUtils.n_1700_B("worldOwnerName", p_230755_0_, "");
            pendinginvite.G_564_y = JsonUtils.n_1700_B("worldOwnerUuid", p_230755_0_, "");
            pendinginvite.P_1922_E = JsonUtils.n_1700_B("date", p_230755_0_);
        }
        catch (Exception exception) {
            u_1723_Y.error("Could not parse PendingInvite: " + exception.getMessage());
        }
        return pendinginvite;
    }
}


