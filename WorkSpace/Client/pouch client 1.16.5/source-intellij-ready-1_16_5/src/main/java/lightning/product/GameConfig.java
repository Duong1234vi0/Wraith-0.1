/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.properties.PropertyMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.authlib.properties.PropertyMap;
import java.io.File;
import java.net.Proxy;
import javax.annotation.Nullable;
import lightning.product.q_570_v;
import lightning.product.t_813_h;
import lightning.product.u_3100_Q;
import lightning.product.x_1356_s;

public class GameConfig {
    public final G_564_y n_1700_B;
    public final q_570_v J_1907_R;
    public final n_1700_B R_4764_Y;
    public final J_1907_R G_564_y;
    public final R_4764_Y P_1922_E;

    public GameConfig(G_564_y userInfo, q_570_v screenSize, n_1700_B folderInfo, J_1907_R gameInfo, R_4764_Y serverInfo) {
        this.n_1700_B = userInfo;
        this.J_1907_R = screenSize;
        this.R_4764_Y = folderInfo;
        this.G_564_y = gameInfo;
        this.P_1922_E = serverInfo;
    }

    public static class G_564_y {
        public final u_3100_Q n_1700_B;
        public final PropertyMap J_1907_R;
        public final PropertyMap R_4764_Y;
        public final Proxy G_564_y;

        public G_564_y(u_3100_Q sessionIn, PropertyMap userPropertiesIn, PropertyMap profilePropertiesIn, Proxy proxyIn) {
            this.n_1700_B = sessionIn;
            this.J_1907_R = userPropertiesIn;
            this.R_4764_Y = profilePropertiesIn;
            this.G_564_y = proxyIn;
        }
    }

    public static class n_1700_B {
        public final File n_1700_B;
        public final File J_1907_R;
        public final File R_4764_Y;
        @Nullable
        public final String G_564_y;

        public n_1700_B(File mcDataDirIn, File resourcePacksDirIn, File assetsDirIn, @Nullable String assetIndexIn) {
            this.n_1700_B = mcDataDirIn;
            this.J_1907_R = resourcePacksDirIn;
            this.R_4764_Y = assetsDirIn;
            this.G_564_y = assetIndexIn;
        }

        public x_1356_s n_1700_B() {
            return this.G_564_y == null ? new t_813_h(this.R_4764_Y) : new x_1356_s(this.R_4764_Y, this.G_564_y);
        }
    }

    public static class J_1907_R {
        public final boolean n_1700_B;
        public final String J_1907_R;
        public final String R_4764_Y;
        public final boolean G_564_y;
        public final boolean P_1922_E;

        public J_1907_R(boolean isDemo, String version, String versionType, boolean disableMultiplayer, boolean disableChat) {
            this.n_1700_B = isDemo;
            this.J_1907_R = version;
            this.R_4764_Y = versionType;
            this.G_564_y = disableMultiplayer;
            this.P_1922_E = disableChat;
        }
    }

    public static class R_4764_Y {
        @Nullable
        public final String n_1700_B;
        public final int J_1907_R;

        public R_4764_Y(@Nullable String serverNameIn, int serverPortIn) {
            this.n_1700_B = serverNameIn;
            this.J_1907_R = serverPortIn;
        }
    }
}


