/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import lightning.product.StoredUserEntry;
import lightning.product.P_1560_G;
import lightning.product.UserBanListEntry;

public class q_1829_g
extends P_1560_G<GameProfile, UserBanListEntry> {
    public q_1829_g(File bansFile) {
        super(bansFile);
    }

    @Override
    protected StoredUserEntry<GameProfile> n_1700_B(JsonObject entryData) {
        return new UserBanListEntry(entryData);
    }

    public boolean n_1700_B(GameProfile profile) {
        return this.G_564_y(profile);
    }

    @Override
    public String[] n_1700_B() {
        String[] astring = new String[this.G_564_y().size()];
        int i = 0;
        for (StoredUserEntry userlistentry : this.G_564_y()) {
            astring[i++] = ((GameProfile)userlistentry.u_1723_Y()).getName();
        }
        return astring;
    }

    @Override
    protected String J_1907_R(GameProfile obj) {
        return obj.getId().toString();
    }

    @Override
    protected /* synthetic */ String n_1700_B(Object object) {
        return this.J_1907_R((GameProfile)object);
    }
}


