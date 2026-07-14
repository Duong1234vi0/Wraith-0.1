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
import java.util.UUID;
import lightning.product.StoredUserEntry;

public class UserWhiteListEntry
extends StoredUserEntry<GameProfile> {
    public UserWhiteListEntry(GameProfile profile) {
        super(profile);
    }

    public UserWhiteListEntry(JsonObject json) {
        super(UserWhiteListEntry.J_1907_R(json));
    }

    @Override
    protected void n_1700_B(JsonObject data) {
        if (this.u_1723_Y() != null) {
            data.addProperty("uuid", ((GameProfile)this.u_1723_Y()).getId() == null ? "" : ((GameProfile)this.u_1723_Y()).getId().toString());
            data.addProperty("name", ((GameProfile)this.u_1723_Y()).getName());
        }
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


