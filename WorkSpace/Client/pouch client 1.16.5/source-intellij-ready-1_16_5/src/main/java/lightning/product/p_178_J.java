/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.F_1410_V;
import lightning.product.H_1083_k;
import lightning.product.SharedConstants;
import lightning.product.PlayerInfo;
import lightning.product.M_1641_O;
import lightning.product.RealmsClientConfig;
import lightning.product.RealmsWorldOptions;
import lightning.product.R_3908_n;
import lightning.product.U_1241_n;
import lightning.product.V_1225_t;
import lightning.product.V_1446_Y;
import lightning.product.MinecraftClient;
import lightning.product.RetryCallException;
import lightning.product.f_4016_n;
import lightning.product.h_4320_q;
import lightning.product.Ops;
import lightning.product.j_276_v;
import lightning.product.k_3961_g;
import lightning.product.l_4537_E;
import lightning.product.q_1982_R;
import lightning.product.RealmsWorldResetDto;
import lightning.product.t_4219_U;
import lightning.product.GuardedSerializer;
import lightning.product.u_744_e;
import lightning.product.dtoRealmsServerAddress;
import lightning.product.w_612_n;
import lightning.product.y_1700_S;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class p_178_J {
    public static J_1907_R n_1700_B = lightning.product.p_178_J$J_1907_R.n_1700_B;
    private static boolean J_1907_R;
    private static final Logger R_4764_Y;
    private final String G_564_y;
    private final String P_1922_E;
    private final MinecraftClient u_1723_Y;
    private static final GuardedSerializer v_4262_N;

    public static p_178_J n_1700_B() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        String s = minecraft.z_1737_N().R_4764_Y();
        String s1 = minecraft.z_1737_N().n_1700_B();
        if (!J_1907_R) {
            J_1907_R = true;
            String s2 = System.getenv("realms.environment");
            if (s2 == null) {
                s2 = System.getProperty("realms.environment");
            }
            if (s2 != null) {
                if ("LOCAL".equals(s2)) {
                    p_178_J.G_564_y();
                } else if ("STAGE".equals(s2)) {
                    p_178_J.J_1907_R();
                }
            }
        }
        return new p_178_J(s1, s, minecraft);
    }

    public static void J_1907_R() {
        n_1700_B = lightning.product.p_178_J$J_1907_R.J_1907_R;
    }

    public static void R_4764_Y() {
        n_1700_B = lightning.product.p_178_J$J_1907_R.n_1700_B;
    }

    public static void G_564_y() {
        n_1700_B = lightning.product.p_178_J$J_1907_R.R_4764_Y;
    }

    public p_178_J(String p_i242128_1_, String p_i242128_2_, MinecraftClient p_i242128_3_) {
        this.G_564_y = p_i242128_1_;
        this.P_1922_E = p_i242128_2_;
        this.u_1723_Y = p_i242128_3_;
        RealmsClientConfig.n_1700_B(p_i242128_3_.d_2461_k());
    }

    public w_612_n P_1922_E() throws u_744_e {
        String s = this.R_4764_Y("worlds");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return w_612_n.n_1700_B(s1);
    }

    public q_1982_R n_1700_B(long p_224935_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$ID".replace("$ID", String.valueOf(p_224935_1_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return q_1982_R.R_4764_Y(s1);
    }

    public M_1641_O u_1723_Y() throws u_744_e {
        String s = this.R_4764_Y("activities/liveplayerlist");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return M_1641_O.n_1700_B(s1);
    }

    public dtoRealmsServerAddress J_1907_R(long p_224904_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/v1/$ID/join/pc".replace("$ID", "" + p_224904_1_));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s, 5000, 30000));
        return dtoRealmsServerAddress.n_1700_B(s1);
    }

    public void n_1700_B(long p_224900_1_, String p_224900_3_, String p_224900_4_) throws u_744_e {
        V_1225_t realmsdescriptiondto = new V_1225_t(p_224900_3_, p_224900_4_);
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/initialize".replace("$WORLD_ID", String.valueOf(p_224900_1_)));
        String s1 = v_4262_N.n_1700_B(realmsdescriptiondto);
        this.n_1700_B(j_276_v.n_1700_B(s, s1, 5000, 10000));
    }

    public Boolean v_4262_N() throws u_744_e {
        String s = this.R_4764_Y("mco/available");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return Boolean.valueOf(s1);
    }

    public Boolean w_1484_f() throws u_744_e {
        String s = this.R_4764_Y("mco/stageAvailable");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return Boolean.valueOf(s1);
    }

    public n_1700_B t_148_a() throws u_744_e {
        String s = this.R_4764_Y("mco/client/compatible");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        try {
            return lightning.product.p_178_J$n_1700_B.valueOf(s1);
        }
        catch (IllegalArgumentException illegalargumentexception) {
            throw new u_744_e(500, "Could not check compatible version, got response: " + s1, -1, "");
        }
    }

    public void n_1700_B(long p_224908_1_, String p_224908_3_) throws u_744_e {
        String s = this.R_4764_Y("invites" + "/$WORLD_ID/invite/$UUID".replace("$WORLD_ID", String.valueOf(p_224908_1_)).replace("$UUID", p_224908_3_));
        this.n_1700_B(j_276_v.J_1907_R(s));
    }

    public void R_4764_Y(long p_224912_1_) throws u_744_e {
        String s = this.R_4764_Y("invites" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(p_224912_1_)));
        this.n_1700_B(j_276_v.J_1907_R(s));
    }

    public q_1982_R J_1907_R(long p_224910_1_, String p_224910_3_) throws u_744_e {
        PlayerInfo playerinfo = new PlayerInfo();
        playerinfo.n_1700_B(p_224910_3_);
        String s = this.R_4764_Y("invites" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(p_224910_1_)));
        String s1 = this.n_1700_B(j_276_v.J_1907_R(s, v_4262_N.n_1700_B(playerinfo)));
        return q_1982_R.R_4764_Y(s1);
    }

    public k_3961_g G_564_y(long p_224923_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/backups".replace("$WORLD_ID", String.valueOf(p_224923_1_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return k_3961_g.n_1700_B(s1);
    }

    public void J_1907_R(long p_224922_1_, String p_224922_3_, String p_224922_4_) throws u_744_e {
        V_1225_t realmsdescriptiondto = new V_1225_t(p_224922_3_, p_224922_4_);
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(p_224922_1_)));
        this.n_1700_B(j_276_v.J_1907_R(s, v_4262_N.n_1700_B(realmsdescriptiondto)));
    }

    public void n_1700_B(long p_224925_1_, int p_224925_3_, RealmsWorldOptions p_224925_4_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/slot/$SLOT_ID".replace("$WORLD_ID", String.valueOf(p_224925_1_)).replace("$SLOT_ID", String.valueOf(p_224925_3_)));
        String s1 = p_224925_4_.R_4764_Y();
        this.n_1700_B(j_276_v.J_1907_R(s, s1));
    }

    public boolean n_1700_B(long p_224927_1_, int p_224927_3_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/slot/$SLOT_ID".replace("$WORLD_ID", String.valueOf(p_224927_1_)).replace("$SLOT_ID", String.valueOf(p_224927_3_)));
        String s1 = this.n_1700_B(j_276_v.R_4764_Y(s, ""));
        return Boolean.valueOf(s1);
    }

    public void R_4764_Y(long p_224928_1_, String p_224928_3_) throws u_744_e {
        String s = this.n_1700_B("worlds" + "/$WORLD_ID/backups".replace("$WORLD_ID", String.valueOf(p_224928_1_)), "backupId=" + p_224928_3_);
        this.n_1700_B(j_276_v.J_1907_R(s, "", 40000, 600000));
    }

    public l_4537_E n_1700_B(int p_224930_1_, int p_224930_2_, q_1982_R.J_1907_R p_224930_3_) throws u_744_e {
        String s = this.n_1700_B("worlds" + "/templates/$WORLD_TYPE".replace("$WORLD_TYPE", p_224930_3_.toString()), String.format("page=%d&pageSize=%d", p_224930_1_, p_224930_2_));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return l_4537_E.n_1700_B(s1);
    }

    public Boolean G_564_y(long p_224905_1_, String p_224905_3_) throws u_744_e {
        String s = "/minigames/$MINIGAME_ID/$WORLD_ID".replace("$MINIGAME_ID", p_224905_3_).replace("$WORLD_ID", String.valueOf(p_224905_1_));
        String s1 = this.R_4764_Y("worlds" + s);
        return Boolean.valueOf(this.n_1700_B(j_276_v.R_4764_Y(s1, "")));
    }

    public Ops P_1922_E(long p_224906_1_, String p_224906_3_) throws u_744_e {
        String s = "/$WORLD_ID/$PROFILE_UUID".replace("$WORLD_ID", String.valueOf(p_224906_1_)).replace("$PROFILE_UUID", p_224906_3_);
        String s1 = this.R_4764_Y("ops" + s);
        return Ops.n_1700_B(this.n_1700_B(j_276_v.J_1907_R(s1, "")));
    }

    public Ops u_1723_Y(long p_224929_1_, String p_224929_3_) throws u_744_e {
        String s = "/$WORLD_ID/$PROFILE_UUID".replace("$WORLD_ID", String.valueOf(p_224929_1_)).replace("$PROFILE_UUID", p_224929_3_);
        String s1 = this.R_4764_Y("ops" + s);
        return Ops.n_1700_B(this.n_1700_B(j_276_v.J_1907_R(s1)));
    }

    public Boolean P_1922_E(long p_224942_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/open".replace("$WORLD_ID", String.valueOf(p_224942_1_)));
        String s1 = this.n_1700_B(j_276_v.R_4764_Y(s, ""));
        return Boolean.valueOf(s1);
    }

    public Boolean u_1723_Y(long p_224932_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/close".replace("$WORLD_ID", String.valueOf(p_224932_1_)));
        String s1 = this.n_1700_B(j_276_v.R_4764_Y(s, ""));
        return Boolean.valueOf(s1);
    }

    public Boolean n_1700_B(long p_224943_1_, String p_224943_3_, Integer p_224943_4_, boolean p_224943_5_) throws u_744_e {
        RealmsWorldResetDto realmsworldresetdto = new RealmsWorldResetDto(p_224943_3_, -1L, p_224943_4_, p_224943_5_);
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/reset".replace("$WORLD_ID", String.valueOf(p_224943_1_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s, v_4262_N.n_1700_B(realmsworldresetdto), 30000, 80000));
        return Boolean.valueOf(s1);
    }

    public Boolean v_4262_N(long p_224924_1_, String p_224924_3_) throws u_744_e {
        RealmsWorldResetDto realmsworldresetdto = new RealmsWorldResetDto(null, Long.valueOf(p_224924_3_), -1, false);
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/reset".replace("$WORLD_ID", String.valueOf(p_224924_1_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s, v_4262_N.n_1700_B(realmsworldresetdto), 30000, 80000));
        return Boolean.valueOf(s1);
    }

    public H_1083_k v_4262_N(long p_224933_1_) throws u_744_e {
        String s = this.R_4764_Y("subscriptions" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(p_224933_1_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return H_1083_k.n_1700_B(s1);
    }

    public int s_956_w() throws u_744_e {
        return this.u_2550_I().n_1700_B.size();
    }

    public t_4219_U u_2550_I() throws u_744_e {
        String s = this.R_4764_Y("invites/pending");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        t_4219_U pendinginviteslist = t_4219_U.n_1700_B(s1);
        pendinginviteslist.n_1700_B.removeIf(this::n_1700_B);
        return pendinginviteslist;
    }

    private boolean n_1700_B(h_4320_q p_244733_1_) {
        try {
            UUID uuid = UUID.fromString(p_244733_1_.G_564_y);
            return this.u_1723_Y.dtoRealmsServerAddress().P_1922_E(uuid);
        }
        catch (IllegalArgumentException illegalargumentexception) {
            return false;
        }
    }

    public void n_1700_B(String p_224901_1_) throws u_744_e {
        String s = this.R_4764_Y("invites" + "/accept/$INVITATION_ID".replace("$INVITATION_ID", p_224901_1_));
        this.n_1700_B(j_276_v.R_4764_Y(s, ""));
    }

    public F_1410_V J_1907_R(long p_224917_1_, int p_224917_3_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/slot/$SLOT_ID/download".replace("$WORLD_ID", String.valueOf(p_224917_1_)).replace("$SLOT_ID", String.valueOf(p_224917_3_)));
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return F_1410_V.n_1700_B(s1);
    }

    @Nullable
    public R_3908_n w_1484_f(long p_224934_1_, @Nullable String p_224934_3_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID/backups/upload".replace("$WORLD_ID", String.valueOf(p_224934_1_)));
        return R_3908_n.n_1700_B(this.n_1700_B(j_276_v.R_4764_Y(s, R_3908_n.J_1907_R(p_224934_3_))));
    }

    public void J_1907_R(String p_224913_1_) throws u_744_e {
        String s = this.R_4764_Y("invites" + "/reject/$INVITATION_ID".replace("$INVITATION_ID", p_224913_1_));
        this.n_1700_B(j_276_v.R_4764_Y(s, ""));
    }

    public void M_588_G() throws u_744_e {
        String s = this.R_4764_Y("mco/tos/agreed");
        this.n_1700_B(j_276_v.J_1907_R(s, ""));
    }

    public U_1241_n P_4830_p() throws u_744_e {
        String s = this.R_4764_Y("mco/v1/news");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s, 5000, 10000));
        return U_1241_n.n_1700_B(s1);
    }

    public void n_1700_B(V_1446_Y p_224903_1_) throws u_744_e {
        String s = this.R_4764_Y("regions/ping/stat");
        this.n_1700_B(j_276_v.J_1907_R(s, v_4262_N.n_1700_B(p_224903_1_)));
    }

    public Boolean h_1847_R() throws u_744_e {
        String s = this.R_4764_Y("trial");
        String s1 = this.n_1700_B(j_276_v.n_1700_B(s));
        return Boolean.valueOf(s1);
    }

    public void w_1484_f(long p_224916_1_) throws u_744_e {
        String s = this.R_4764_Y("worlds" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(p_224916_1_)));
        this.n_1700_B(j_276_v.J_1907_R(s));
    }

    @Nullable
    private String R_4764_Y(String p_224926_1_) {
        return this.n_1700_B(p_224926_1_, (String)null);
    }

    @Nullable
    private String n_1700_B(String p_224907_1_, @Nullable String p_224907_2_) {
        try {
            return new URI(p_178_J.n_1700_B.P_1922_E, p_178_J.n_1700_B.G_564_y, "/" + p_224907_1_, p_224907_2_, null).toASCIIString();
        }
        catch (URISyntaxException urisyntaxexception) {
            urisyntaxexception.printStackTrace();
            return null;
        }
    }

    private String n_1700_B(j_276_v<?> p_224938_1_) throws u_744_e {
        p_224938_1_.n_1700_B("sid", this.G_564_y);
        p_224938_1_.n_1700_B("user", this.P_1922_E);
        p_224938_1_.n_1700_B("version", SharedConstants.n_1700_B().getName());
        try {
            int i = p_224938_1_.J_1907_R();
            if (i != 503 && i != 277) {
                String s = p_224938_1_.R_4764_Y();
                if (i >= 200 && i < 300) {
                    return s;
                }
                if (i == 401) {
                    String s1 = p_224938_1_.R_4764_Y("WWW-Authenticate");
                    R_4764_Y.info("Could not authorize you against Realms server: " + s1);
                    throw new u_744_e(i, s1, -1, s1);
                }
                if (s != null && s.length() != 0) {
                    f_4016_n realmserror = f_4016_n.n_1700_B(s);
                    R_4764_Y.error("Realms http code: " + i + " -  error code: " + realmserror.J_1907_R() + " -  message: " + realmserror.n_1700_B() + " - raw body: " + s);
                    throw new u_744_e(i, s, realmserror);
                }
                R_4764_Y.error("Realms error code: " + i + " message: " + s);
                throw new u_744_e(i, s, i, "");
            }
            int j = p_224938_1_.n_1700_B();
            throw new RetryCallException(j, i);
        }
        catch (y_1700_S realmshttpexception) {
            throw new u_744_e(500, "Could not connect to Realms: " + realmshttpexception.getMessage(), -1, "");
        }
    }

    static {
        R_4764_Y = LogManager.getLogger();
        v_4262_N = new GuardedSerializer();
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("pc.realms.minecraft.net", "https");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("pc-stage.realms.minecraft.net", "https");
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("localhost:8080", "http");
        public String G_564_y;
        public String P_1922_E;
        private static final /* synthetic */ J_1907_R[] u_1723_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])u_1723_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String p_i51584_3_, String p_i51584_4_) {
            this.G_564_y = p_i51584_3_;
            this.P_1922_E = p_i51584_4_;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            u_1723_Y = lightning.product.p_178_J$J_1907_R.n_1700_B();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.p_178_J$n_1700_B.n_1700_B();
        }
    }
}



