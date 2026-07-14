/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.collect.ComparisonChain
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.commons.lang3.builder.EqualsBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import lightning.product.ValueObject;
import lightning.product.H_1883_T;
import lightning.product.PlayerInfo;
import lightning.product.RealmsWorldOptions;
import lightning.product.ServerData;
import lightning.product.RealmsServerPing;
import lightning.product.MinecraftClient;
import lightning.product.j_1564_a;
import lightning.product.JsonUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class q_1982_R
extends ValueObject {
    private static final Logger w_1457_N = LogManager.getLogger();
    public long n_1700_B;
    public String J_1907_R;
    public String R_4764_Y;
    public String G_564_y;
    public R_4764_Y P_1922_E;
    public String u_1723_Y;
    public String v_4262_N;
    public List<PlayerInfo> w_1484_f;
    public Map<Integer, RealmsWorldOptions> t_148_a;
    public boolean s_956_w;
    public boolean u_2550_I;
    public int M_588_G;
    public J_1907_R P_4830_p;
    public int h_1847_R;
    public String Q_4569_t;
    public int M_182_A;
    public String t_1786_h;
    public RealmsServerPing multiplayerClientSuggestionProvider = new RealmsServerPing();

    public String n_1700_B() {
        return this.G_564_y;
    }

    public String J_1907_R() {
        return this.R_4764_Y;
    }

    public String R_4764_Y() {
        return this.Q_4569_t;
    }

    public void n_1700_B(String p_230773_1_) {
        this.R_4764_Y = p_230773_1_;
    }

    public void J_1907_R(String p_230777_1_) {
        this.G_564_y = p_230777_1_;
    }

    public void n_1700_B(j_1564_a p_230772_1_) {
        ArrayList list = Lists.newArrayList();
        int i = 0;
        for (String s : p_230772_1_.J_1907_R) {
            if (s.equals(MinecraftClient.A_4115_X().z_1737_N().J_1907_R())) continue;
            String s1 = "";
            try {
                s1 = H_1883_T.n_1700_B(s);
            }
            catch (Exception exception) {
                w_1457_N.error("Could not get name for " + s, (Throwable)exception);
                continue;
            }
            list.add(s1);
            ++i;
        }
        this.multiplayerClientSuggestionProvider.n_1700_B = String.valueOf(i);
        this.multiplayerClientSuggestionProvider.J_1907_R = Joiner.on((char)'\n').join((Iterable)list);
    }

    public static q_1982_R n_1700_B(JsonObject p_230770_0_) {
        q_1982_R realmsserver = new q_1982_R();
        try {
            realmsserver.n_1700_B = JsonUtils.n_1700_B("id", p_230770_0_, -1L);
            realmsserver.J_1907_R = JsonUtils.n_1700_B("remoteSubscriptionId", p_230770_0_, null);
            realmsserver.R_4764_Y = JsonUtils.n_1700_B("name", p_230770_0_, null);
            realmsserver.G_564_y = JsonUtils.n_1700_B("motd", p_230770_0_, null);
            realmsserver.P_1922_E = q_1982_R.P_1922_E(JsonUtils.n_1700_B("state", p_230770_0_, lightning.product.q_1982_R$R_4764_Y.n_1700_B.name()));
            realmsserver.u_1723_Y = JsonUtils.n_1700_B("owner", p_230770_0_, null);
            if (p_230770_0_.get("players") != null && p_230770_0_.get("players").isJsonArray()) {
                realmsserver.w_1484_f = q_1982_R.n_1700_B(p_230770_0_.get("players").getAsJsonArray());
                q_1982_R.n_1700_B(realmsserver);
            } else {
                realmsserver.w_1484_f = Lists.newArrayList();
            }
            realmsserver.M_588_G = JsonUtils.n_1700_B("daysLeft", p_230770_0_, 0);
            realmsserver.s_956_w = JsonUtils.n_1700_B("expired", p_230770_0_, false);
            realmsserver.u_2550_I = JsonUtils.n_1700_B("expiredTrial", p_230770_0_, false);
            realmsserver.P_4830_p = q_1982_R.u_1723_Y(JsonUtils.n_1700_B("worldType", p_230770_0_, lightning.product.q_1982_R$J_1907_R.n_1700_B.name()));
            realmsserver.v_4262_N = JsonUtils.n_1700_B("ownerUUID", p_230770_0_, "");
            realmsserver.t_148_a = p_230770_0_.get("slots") != null && p_230770_0_.get("slots").isJsonArray() ? q_1982_R.J_1907_R(p_230770_0_.get("slots").getAsJsonArray()) : q_1982_R.P_1922_E();
            realmsserver.Q_4569_t = JsonUtils.n_1700_B("minigameName", p_230770_0_, null);
            realmsserver.h_1847_R = JsonUtils.n_1700_B("activeSlot", p_230770_0_, -1);
            realmsserver.M_182_A = JsonUtils.n_1700_B("minigameId", p_230770_0_, -1);
            realmsserver.t_1786_h = JsonUtils.n_1700_B("minigameImage", p_230770_0_, null);
        }
        catch (Exception exception) {
            w_1457_N.error("Could not parse McoServer: " + exception.getMessage());
        }
        return realmsserver;
    }

    private static void n_1700_B(q_1982_R p_230771_0_) {
        p_230771_0_.w_1484_f.sort((p_229951_0_, p_229951_1_) -> ComparisonChain.start().compareFalseFirst(p_229951_1_.G_564_y(), p_229951_0_.G_564_y()).compare((Comparable)((Object)p_229951_0_.n_1700_B().toLowerCase(Locale.ROOT)), (Comparable)((Object)p_229951_1_.n_1700_B().toLowerCase(Locale.ROOT))).result());
    }

    private static List<PlayerInfo> n_1700_B(JsonArray p_230769_0_) {
        ArrayList list = Lists.newArrayList();
        for (JsonElement jsonelement : p_230769_0_) {
            try {
                JsonObject jsonobject = jsonelement.getAsJsonObject();
                PlayerInfo playerinfo = new PlayerInfo();
                playerinfo.n_1700_B(JsonUtils.n_1700_B("name", jsonobject, null));
                playerinfo.J_1907_R(JsonUtils.n_1700_B("uuid", jsonobject, null));
                playerinfo.n_1700_B(JsonUtils.n_1700_B("operator", jsonobject, false));
                playerinfo.J_1907_R(JsonUtils.n_1700_B("accepted", jsonobject, false));
                playerinfo.R_4764_Y(JsonUtils.n_1700_B("online", jsonobject, false));
                list.add(playerinfo);
            }
            catch (Exception exception) {}
        }
        return list;
    }

    private static Map<Integer, RealmsWorldOptions> J_1907_R(JsonArray p_230776_0_) {
        HashMap map = Maps.newHashMap();
        for (JsonElement jsonelement : p_230776_0_) {
            try {
                JsonObject jsonobject = jsonelement.getAsJsonObject();
                JsonParser jsonparser = new JsonParser();
                JsonElement jsonelement1 = jsonparser.parse(jsonobject.get("options").getAsString());
                RealmsWorldOptions realmsworldoptions = jsonelement1 == null ? RealmsWorldOptions.n_1700_B() : RealmsWorldOptions.n_1700_B(jsonelement1.getAsJsonObject());
                int i = JsonUtils.n_1700_B("slotId", jsonobject, -1);
                map.put(i, realmsworldoptions);
            }
            catch (Exception exception) {}
        }
        for (int j = 1; j <= 3; ++j) {
            if (map.containsKey(j)) continue;
            map.put(j, RealmsWorldOptions.J_1907_R());
        }
        return map;
    }

    private static Map<Integer, RealmsWorldOptions> P_1922_E() {
        HashMap map = Maps.newHashMap();
        map.put(1, RealmsWorldOptions.J_1907_R());
        map.put(2, RealmsWorldOptions.J_1907_R());
        map.put(3, RealmsWorldOptions.J_1907_R());
        return map;
    }

    public static q_1982_R R_4764_Y(String p_230779_0_) {
        try {
            return q_1982_R.n_1700_B(new JsonParser().parse(p_230779_0_).getAsJsonObject());
        }
        catch (Exception exception) {
            w_1457_N.error("Could not parse McoServer: " + exception.getMessage());
            return new q_1982_R();
        }
    }

    private static R_4764_Y P_1922_E(String p_230780_0_) {
        try {
            return lightning.product.q_1982_R$R_4764_Y.valueOf(p_230780_0_);
        }
        catch (Exception exception) {
            return lightning.product.q_1982_R$R_4764_Y.n_1700_B;
        }
    }

    private static J_1907_R u_1723_Y(String p_230781_0_) {
        try {
            return lightning.product.q_1982_R$J_1907_R.valueOf(p_230781_0_);
        }
        catch (Exception exception) {
            return lightning.product.q_1982_R$J_1907_R.n_1700_B;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.n_1700_B, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.s_956_w});
    }

    public boolean equals(Object p_equals_1_) {
        if (p_equals_1_ == null) {
            return false;
        }
        if (p_equals_1_ == this) {
            return true;
        }
        if (p_equals_1_.getClass() != this.getClass()) {
            return false;
        }
        q_1982_R realmsserver = (q_1982_R)p_equals_1_;
        return new EqualsBuilder().append(this.n_1700_B, realmsserver.n_1700_B).append((Object)this.R_4764_Y, (Object)realmsserver.R_4764_Y).append((Object)this.G_564_y, (Object)realmsserver.G_564_y).append((Object)this.P_1922_E, (Object)realmsserver.P_1922_E).append((Object)this.u_1723_Y, (Object)realmsserver.u_1723_Y).append(this.s_956_w, realmsserver.s_956_w).append((Object)this.P_4830_p, (Object)this.P_4830_p).isEquals();
    }

    public q_1982_R G_564_y() {
        q_1982_R realmsserver = new q_1982_R();
        realmsserver.n_1700_B = this.n_1700_B;
        realmsserver.J_1907_R = this.J_1907_R;
        realmsserver.R_4764_Y = this.R_4764_Y;
        realmsserver.G_564_y = this.G_564_y;
        realmsserver.P_1922_E = this.P_1922_E;
        realmsserver.u_1723_Y = this.u_1723_Y;
        realmsserver.w_1484_f = this.w_1484_f;
        realmsserver.t_148_a = this.n_1700_B(this.t_148_a);
        realmsserver.s_956_w = this.s_956_w;
        realmsserver.u_2550_I = this.u_2550_I;
        realmsserver.M_588_G = this.M_588_G;
        realmsserver.multiplayerClientSuggestionProvider = new RealmsServerPing();
        realmsserver.multiplayerClientSuggestionProvider.n_1700_B = this.multiplayerClientSuggestionProvider.n_1700_B;
        realmsserver.multiplayerClientSuggestionProvider.J_1907_R = this.multiplayerClientSuggestionProvider.J_1907_R;
        realmsserver.P_4830_p = this.P_4830_p;
        realmsserver.v_4262_N = this.v_4262_N;
        realmsserver.Q_4569_t = this.Q_4569_t;
        realmsserver.h_1847_R = this.h_1847_R;
        realmsserver.M_182_A = this.M_182_A;
        realmsserver.t_1786_h = this.t_1786_h;
        return realmsserver;
    }

    public Map<Integer, RealmsWorldOptions> n_1700_B(Map<Integer, RealmsWorldOptions> p_230774_1_) {
        HashMap map = Maps.newHashMap();
        for (Map.Entry<Integer, RealmsWorldOptions> entry : p_230774_1_.entrySet()) {
            map.put(entry.getKey(), entry.getValue().G_564_y());
        }
        return map;
    }

    public String n_1700_B(int p_237696_1_) {
        return this.R_4764_Y + " (" + this.t_148_a.get(p_237696_1_).n_1700_B(p_237696_1_) + ")";
    }

    public ServerData G_564_y(String p_244783_1_) {
        return new ServerData(this.R_4764_Y, p_244783_1_, false);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return this.G_564_y();
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y();
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] G_564_y;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])G_564_y.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.q_1982_R$R_4764_Y.n_1700_B();
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R();
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] u_1723_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])u_1723_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.q_1982_R$J_1907_R.n_1700_B();
        }
    }

    public static class n_1700_B
    implements Comparator<q_1982_R> {
        private final String n_1700_B;

        public n_1700_B(String p_i51687_1_) {
            this.n_1700_B = p_i51687_1_;
        }

        public int n_1700_B(q_1982_R p_compare_1_, q_1982_R p_compare_2_) {
            return ComparisonChain.start().compareTrueFirst(p_compare_1_.P_1922_E == lightning.product.q_1982_R$R_4764_Y.R_4764_Y, p_compare_2_.P_1922_E == lightning.product.q_1982_R$R_4764_Y.R_4764_Y).compareTrueFirst(p_compare_1_.u_2550_I, p_compare_2_.u_2550_I).compareTrueFirst(p_compare_1_.u_1723_Y.equals(this.n_1700_B), p_compare_2_.u_1723_Y.equals(this.n_1700_B)).compareFalseFirst(p_compare_1_.s_956_w, p_compare_2_.s_956_w).compareTrueFirst(p_compare_1_.P_1922_E == lightning.product.q_1982_R$R_4764_Y.J_1907_R, p_compare_2_.P_1922_E == lightning.product.q_1982_R$R_4764_Y.J_1907_R).compare(p_compare_1_.n_1700_B, p_compare_2_.n_1700_B).result();
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.n_1700_B((q_1982_R)object, (q_1982_R)object2);
        }
    }
}



