/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.mojang.bridge.game.GameVersion
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.bridge.game.GameVersion;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import lightning.product.SharedConstants;
import lightning.product.i_4431_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class H_3790_v
implements GameVersion {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final GameVersion n_1700_B = new H_3790_v();
    private final String R_4764_Y;
    private final String G_564_y;
    private final boolean P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final Date t_148_a;
    private final String s_956_w;

    private H_3790_v() {
        this.R_4764_Y = UUID.randomUUID().toString().replaceAll("-", "");
        this.G_564_y = "1.16.5";
        this.P_1922_E = true;
        this.u_1723_Y = 2586;
        this.v_4262_N = SharedConstants.J_1907_R();
        this.w_1484_f = 6;
        this.t_148_a = new Date();
        this.s_956_w = "1.16.5";
    }

    private H_3790_v(JsonObject json) {
        this.R_4764_Y = i_4431_W.u_1723_Y(json, "id");
        this.G_564_y = i_4431_W.u_1723_Y(json, "name");
        this.s_956_w = i_4431_W.u_1723_Y(json, "release_target");
        this.P_1922_E = i_4431_W.w_1484_f(json, "stable");
        this.u_1723_Y = i_4431_W.u_2550_I(json, "world_version");
        this.v_4262_N = i_4431_W.u_2550_I(json, "protocol_version");
        this.w_1484_f = i_4431_W.u_2550_I(json, "pack_version");
        this.t_148_a = Date.from(ZonedDateTime.parse(i_4431_W.u_1723_Y(json, "build_time")).toInstant());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static GameVersion n_1700_B() {
        try (InputStream inputstream = H_3790_v.class.getResourceAsStream("/version.json");){
            H_3790_v minecraftversion;
            if (inputstream == null) {
                J_1907_R.warn("Missing version information!");
                GameVersion gameVersion = n_1700_B;
                return gameVersion;
            }
            try (InputStreamReader inputstreamreader = new InputStreamReader(inputstream);){
                minecraftversion = new H_3790_v(i_4431_W.n_1700_B(inputstreamreader));
            }
            H_3790_v h_3790_v = minecraftversion;
            return h_3790_v;
        }
        catch (JsonParseException | IOException ioexception) {
            throw new IllegalStateException("Game version information is corrupt", ioexception);
        }
    }

    public String getId() {
        return this.R_4764_Y;
    }

    public String getName() {
        return this.G_564_y;
    }

    public String getReleaseTarget() {
        return this.s_956_w;
    }

    public int getWorldVersion() {
        return this.u_1723_Y;
    }

    public int getProtocolVersion() {
        return this.v_4262_N;
    }

    public int getPackVersion() {
        return this.w_1484_f;
    }

    public Date getBuildTime() {
        return this.t_148_a;
    }

    public boolean isStable() {
        return this.P_1922_E;
    }
}


