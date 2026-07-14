/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nullable;
import lightning.product.StoredUserEntry;
import lightning.product.x_282_a;

public abstract class BanListEntry<T>
extends StoredUserEntry<T> {
    public static final SimpleDateFormat n_1700_B = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    protected final Date J_1907_R;
    protected final String R_4764_Y;
    protected final Date G_564_y;
    protected final String P_1922_E;

    public BanListEntry(T valueIn, @Nullable Date startDate, @Nullable String banner, @Nullable Date endDate, @Nullable String banReason) {
        super(valueIn);
        this.J_1907_R = startDate == null ? new Date() : startDate;
        this.R_4764_Y = banner == null ? "(Unknown)" : banner;
        this.G_564_y = endDate;
        this.P_1922_E = banReason == null ? "Banned by an operator." : banReason;
    }

    protected BanListEntry(T valueIn, JsonObject json) {
        super(valueIn);
        Date date1;
        Date date;
        try {
            date = json.has("created") ? n_1700_B.parse(json.get("created").getAsString()) : new Date();
        }
        catch (ParseException parseexception1) {
            date = new Date();
        }
        this.J_1907_R = date;
        this.R_4764_Y = json.has("source") ? json.get("source").getAsString() : "(Unknown)";
        try {
            date1 = json.has("expires") ? n_1700_B.parse(json.get("expires").getAsString()) : null;
        }
        catch (ParseException parseexception) {
            date1 = null;
        }
        this.G_564_y = date1;
        this.P_1922_E = json.has("reason") ? json.get("reason").getAsString() : "Banned by an operator.";
    }

    public String n_1700_B() {
        return this.R_4764_Y;
    }

    public Date J_1907_R() {
        return this.G_564_y;
    }

    public String R_4764_Y() {
        return this.P_1922_E;
    }

    public abstract x_282_a G_564_y();

    @Override
    boolean P_1922_E() {
        return this.G_564_y == null ? false : this.G_564_y.before(new Date());
    }

    @Override
    protected void n_1700_B(JsonObject data) {
        data.addProperty("created", n_1700_B.format(this.J_1907_R));
        data.addProperty("source", this.R_4764_Y);
        data.addProperty("expires", this.G_564_y == null ? "forever" : n_1700_B.format(this.G_564_y));
        data.addProperty("reason", this.P_1922_E);
    }
}


