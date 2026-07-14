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

public class I_1965_o
extends StoredUserEntry<GameProfile> {
    private final int n_1700_B;
    private final boolean J_1907_R;

    public I_1965_o(GameProfile player, int permissionLevelIn, boolean bypassesPlayerLimitIn) {
        super(player);
        this.n_1700_B = permissionLevelIn;
        this.J_1907_R = bypassesPlayerLimitIn;
    }

    public I_1965_o(JsonObject p_i1150_1_) {
        super(I_1965_o.J_1907_R(p_i1150_1_));
        this.n_1700_B = p_i1150_1_.has("level") ? p_i1150_1_.get("level").getAsInt() : 0;
        this.J_1907_R = p_i1150_1_.has("bypassesPlayerLimit") && p_i1150_1_.get("bypassesPlayerLimit").getAsBoolean();
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    protected void n_1700_B(JsonObject data) {
        if (this.u_1723_Y() != null) {
            data.addProperty("uuid", ((GameProfile)this.u_1723_Y()).getId() == null ? "" : ((GameProfile)this.u_1723_Y()).getId().toString());
            data.addProperty("name", ((GameProfile)this.u_1723_Y()).getName());
            data.addProperty("level", (Number)this.n_1700_B);
            data.addProperty("bypassesPlayerLimit", Boolean.valueOf(this.J_1907_R));
        }
    }

    private static GameProfile J_1907_R(JsonObject p_152643_0_) {
        if (p_152643_0_.has("uuid") && p_152643_0_.has("name")) {
            UUID uuid;
            String s = p_152643_0_.get("uuid").getAsString();
            try {
                uuid = UUID.fromString(s);
            }
            catch (Throwable throwable) {
                return null;
            }
            return new GameProfile(uuid, p_152643_0_.get("name").getAsString());
        }
        return null;
    }
}


