/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.BanListEntry;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class UserBanListEntry
extends BanListEntry<GameProfile> {
    public UserBanListEntry(GameProfile profile) {
        this(profile, (Date)null, (String)null, (Date)null, (String)null);
    }

    public UserBanListEntry(GameProfile profile, @Nullable Date startDate, @Nullable String banner, @Nullable Date endDate, @Nullable String banReason) {
        super(profile, startDate, banner, endDate, banReason);
    }

    public UserBanListEntry(JsonObject json) {
        super(UserBanListEntry.J_1907_R(json), json);
    }

    @Override
    protected void n_1700_B(JsonObject data) {
        if (this.u_1723_Y() != null) {
            data.addProperty("uuid", ((GameProfile)this.u_1723_Y()).getId() == null ? "" : ((GameProfile)this.u_1723_Y()).getId().toString());
            data.addProperty("name", ((GameProfile)this.u_1723_Y()).getName());
            super.n_1700_B(data);
        }
    }

    @Override
    public x_282_a G_564_y() {
        GameProfile gameprofile = (GameProfile)this.u_1723_Y();
        return new U_2871_b(gameprofile.getName() != null ? gameprofile.getName() : Objects.toString(gameprofile.getId(), "(Unknown)"));
    }

    private static GameProfile J_1907_R(JsonObject json) {
        if (json.has("uuid") && json.has("name")) {
            UUID uuid;
            String s = json.get("uuid").getAsString();
            try {
                uuid = UUID.fromString(s);
            }
            catch (Throwable throwable) {
                return null;
            }
            return new GameProfile(uuid, json.get("name").getAsString());
        }
        return null;
    }
}


