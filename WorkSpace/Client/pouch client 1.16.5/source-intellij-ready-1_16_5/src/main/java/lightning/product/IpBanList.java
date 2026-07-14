/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;
import lightning.product.StoredUserEntry;
import lightning.product.P_1560_G;
import lightning.product.IpBanListEntry;

public class IpBanList
extends P_1560_G<String, IpBanListEntry> {
    public IpBanList(File bansFile) {
        super(bansFile);
    }

    @Override
    protected StoredUserEntry<String> n_1700_B(JsonObject entryData) {
        return new IpBanListEntry(entryData);
    }

    public boolean n_1700_B(SocketAddress address) {
        String s = this.R_4764_Y(address);
        return this.G_564_y(s);
    }

    public boolean n_1700_B(String p_199044_1_) {
        return this.G_564_y(p_199044_1_);
    }

    @Override
    public IpBanListEntry J_1907_R(SocketAddress address) {
        String s = this.R_4764_Y(address);
        return (IpBanListEntry)this.J_1907_R(s);
    }

    private String R_4764_Y(SocketAddress address) {
        String s = address.toString();
        if (s.contains("/")) {
            s = s.substring(s.indexOf(47) + 1);
        }
        if (s.contains(":")) {
            s = s.substring(0, s.indexOf(58));
        }
        return s;
    }
}


