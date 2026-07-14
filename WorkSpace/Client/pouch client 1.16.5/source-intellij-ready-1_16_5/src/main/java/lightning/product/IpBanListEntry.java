/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.Date;
import javax.annotation.Nullable;
import lightning.product.BanListEntry;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class IpBanListEntry
extends BanListEntry<String> {
    public IpBanListEntry(String valueIn) {
        this(valueIn, (Date)null, (String)null, (Date)null, (String)null);
    }

    public IpBanListEntry(String valueIn, @Nullable Date startDate, @Nullable String banner, @Nullable Date endDate, @Nullable String banReason) {
        super(valueIn, startDate, banner, endDate, banReason);
    }

    @Override
    public x_282_a G_564_y() {
        return new U_2871_b((String)this.u_1723_Y());
    }

    public IpBanListEntry(JsonObject json) {
        super(IpBanListEntry.J_1907_R(json), json);
    }

    private static String J_1907_R(JsonObject json) {
        return json.has("ip") ? json.get("ip").getAsString() : null;
    }

    @Override
    protected void n_1700_B(JsonObject data) {
        if (this.u_1723_Y() != null) {
            data.addProperty("ip", (String)this.u_1723_Y());
            super.n_1700_B(data);
        }
    }
}


