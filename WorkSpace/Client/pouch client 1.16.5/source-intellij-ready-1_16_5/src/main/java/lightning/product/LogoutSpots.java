/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.joml.Vector2f
 */
package lightning.product;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.A_2226_Q;
import lightning.product.C_332_W;
import lightning.product.E_3343_g;
import lightning.product.F_489_x;
import lightning.product.H_1491_c;
import lightning.product.H_2506_c;
import lightning.product.I_3457_f;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.V_772_m;
import lightning.product.Module;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.Z_3822_q;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.v_1900_v;
import lightning.product.v_2826_q;
import lightning.product.ModuleCategory;
import org.joml.Vector2f;

public class LogoutSpots
extends Module {
    private final BooleanSetting _3dBoksEnabled = new BooleanSetting("3D \u0411\u043e\u043a\u0441", true);
    private final BooleanSetting imyaIgrokaEnabled = new BooleanSetting("\u0418\u043c\u044f \u0438\u0433\u0440\u043e\u043a\u0430", true);
    private final BooleanSetting koordinatyEnabled = new BooleanSetting("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b", true);
    private final BooleanSetting vremyaVyhodaEnabled = new BooleanSetting("\u0412\u0440\u0435\u043c\u044f \u0432\u044b\u0445\u043e\u0434\u0430", true);
    private final BooleanSetting distanciyaEnabled = new BooleanSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", true);
    private final h_2367_h M_588_G = new h_2367_h("\u0426\u0432\u0435\u0442 \u0431\u043e\u043a\u0441\u0430", true, H_2506_c.n_1700_B("#FF5555"), this._3dBoksEnabled::isEnabled);
    private final NumberSetting maksTochekSetting = new NumberSetting("\u041c\u0430\u043a\u0441. \u0442\u043e\u0447\u0435\u043a", 50.0f, 10.0f, 200.0f, 1.0f);
    private final NumberSetting dalnostRenderaSetting = new NumberSetting("\u0414\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0440\u0435\u043d\u0434\u0435\u0440\u0430", 500.0f, 50.0f, 2000.0f, 50.0f);
    private final BooleanSetting sohranyatVFaylEnabled = new BooleanSetting("\u0421\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c \u0432 \u0444\u0430\u0439\u043b", true);
    private final BooleanSetting uvedomlyatVChatEnabled = new BooleanSetting("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u044f\u0442\u044c \u0432 \u0447\u0430\u0442", true);
    private final H_1491_c t_1786_h = new H_1491_c("\u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u0432\u0441\u0435 \u0442\u043e\u0447\u043a\u0438", this::multiplayerClientSuggestionProvider);
    private final List<n_1700_B> multiplayerClientSuggestionProvider = new CopyOnWriteArrayList<n_1700_B>();
    private final SimpleDateFormat w_1457_N = new SimpleDateFormat("dd.MM HH:mm");
    private static final String Y_601_j = "logout_spots\\";
    private String Y_259_p = null;
    private final Map<String, J_1907_R> Q_2552_b = new HashMap<String, J_1907_R>();

    public static void h_1847_R() {
        try {
            File dir = new File(C_332_W.n_1700_B + Y_601_j);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Runtime.getRuntime().exec("explorer " + dir.getAbsolutePath());
        }
        catch (IOException e) {
            v_1900_v.n_1700_B("[LogoutSpots] \u041e\u0448\u0438\u0431\u043a\u0430 \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u044f \u043f\u0430\u043f\u043a\u0438: " + e.getMessage(), new Object[0]);
        }
    }

    public LogoutSpots() {
        super("LogoutSpots", ModuleCategory.R_4764_Y);
        this.addSettings(this._3dBoksEnabled, this.M_588_G, this.imyaIgrokaEnabled, this.koordinatyEnabled, this.vremyaVyhodaEnabled, this.distanciyaEnabled, this.maksTochekSetting, this.dalnostRenderaSetting, this.sohranyatVFaylEnabled, this.uvedomlyatVChatEnabled, this.t_1786_h);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.Q_4569_t();
        if (this.sohranyatVFaylEnabled.isEnabled().booleanValue() && this.Y_259_p != null) {
            this.Y_601_j();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.Q_2552_b.clear();
        if (this.sohranyatVFaylEnabled.isEnabled().booleanValue() && this.Y_259_p != null) {
            this.w_1457_N();
        }
    }

    private void Q_4569_t() {
        String newServer = this.M_182_A();
        if (newServer != null && !newServer.equals(this.Y_259_p)) {
            if (this.Y_259_p != null && this.sohranyatVFaylEnabled.isEnabled().booleanValue()) {
                this.w_1457_N();
            }
            this.Y_259_p = newServer;
            this.multiplayerClientSuggestionProvider.clear();
            this.Q_2552_b.clear();
            if (this.sohranyatVFaylEnabled.isEnabled().booleanValue()) {
                this.Y_601_j();
            }
            v_1900_v.n_1700_B("[LogoutSpots] \u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b \u0442\u043e\u0447\u043a\u0438 \u0434\u043b\u044f: " + this.Y_259_p, new Object[0]);
        }
    }

    private String M_182_A() {
        if (c_3005_b.t_4043_B() == null || c_3005_b.x_607_J()) {
            return null;
        }
        String ip = LogoutSpots.c_3005_b.t_4043_B().J_1907_R;
        if (ip == null || ip.isEmpty()) {
            return null;
        }
        return ip.toLowerCase().replaceAll(":\\d+$", "").replaceAll("[^a-z0-9.-]", "_");
    }

    private String t_1786_h() {
        if (this.Y_259_p == null) {
            return null;
        }
        return Y_601_j + this.Y_259_p + ".json";
    }

    @Y_1740_V
    public void n_1700_B(I_3457_f event) {
        if (LogoutSpots.c_3005_b.Y_259_p == null || LogoutSpots.c_3005_b.Y_601_j == null) {
            return;
        }
        if (c_3005_b.x_607_J()) {
            return;
        }
        N_4263_v n_4263_v = event.J_1907_R();
        if (n_4263_v instanceof X_4340_E) {
            X_4340_E player = (X_4340_E)n_4263_v;
            if (player instanceof V_772_m) {
                return;
            }
            String name = player.c_4037_x();
            if (name == null || name.isEmpty()) {
                return;
            }
            if (name.contains("CIT-") || name.contains("NPC") || name.startsWith("[")) {
                return;
            }
            if (player.Z_2812_M() || player.g_46_E() <= 0.0f) {
                return;
            }
            double x = Math.floor(player.O_3598_v());
            double y = Math.floor(player.X_2960_b());
            double z = Math.floor(player.l_2647_k());
            this.Q_2552_b.put(name, new J_1907_R(name, x, y, z, System.currentTimeMillis()));
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (LogoutSpots.c_3005_b.Y_259_p == null || LogoutSpots.c_3005_b.Y_601_j == null || c_3005_b.k_2293_S() == null) {
            return;
        }
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<String, J_1907_R>> iterator = this.Q_2552_b.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, J_1907_R> entry = iterator.next();
            J_1907_R pending = entry.getValue();
            if (now - pending.P_1922_E < 500L) continue;
            iterator.remove();
            boolean inTabList = false;
            for (A_2226_Q info : c_3005_b.k_2293_S().P_1922_E()) {
                if (!info.n_1700_B().getName().equalsIgnoreCase(pending.n_1700_B)) continue;
                inTabList = true;
                break;
            }
            if (inTabList) continue;
            n_1700_B spot = new n_1700_B(pending.n_1700_B, pending.J_1907_R, pending.R_4764_Y, pending.G_564_y, pending.P_1922_E);
            this.multiplayerClientSuggestionProvider.removeIf(s -> s.n_1700_B.equalsIgnoreCase(pending.n_1700_B));
            this.multiplayerClientSuggestionProvider.add(spot);
            while (this.multiplayerClientSuggestionProvider.size() > ((Float)this.maksTochekSetting.getValue()).intValue()) {
                this.multiplayerClientSuggestionProvider.remove(0);
            }
            if (this.uvedomlyatVChatEnabled.isEnabled().booleanValue()) {
                v_1900_v.n_1700_B("[LogoutSpots] %s \u0432\u044b\u0448\u0435\u043b \u043d\u0430 X: %.0f Y: %.0f Z: %.0f", pending.n_1700_B, pending.J_1907_R, pending.R_4764_Y, pending.G_564_y);
            }
            if (!this.sohranyatVFaylEnabled.isEnabled().booleanValue()) continue;
            this.w_1457_N();
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (LogoutSpots.c_3005_b.Y_259_p == null || LogoutSpots.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this._3dBoksEnabled.isEnabled().booleanValue()) {
            return;
        }
        e_2866_D camera = LogoutSpots.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        for (n_1700_B spot : this.multiplayerClientSuggestionProvider) {
            double distance = LogoutSpots.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(new e_2866_D(spot.J_1907_R, spot.R_4764_Y, spot.G_564_y));
            if (distance > (double)((Float)this.dalnostRenderaSetting.getValue()).floatValue()) continue;
            double size = 0.3;
            I_4817_s box = new I_4817_s(spot.J_1907_R - size - camera.J_1907_R, spot.R_4764_Y - camera.R_4764_Y, spot.G_564_y - size - camera.G_564_y, spot.J_1907_R + size - camera.J_1907_R, spot.R_4764_Y + 1.8 - camera.R_4764_Y, spot.G_564_y + size - camera.G_564_y);
            int color = (Integer)this.M_588_G.J_1907_R();
            F_489_x.n_1700_B(box, color, false);
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (LogoutSpots.c_3005_b.Y_259_p == null || LogoutSpots.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!(this.imyaIgrokaEnabled.isEnabled().booleanValue() || this.koordinatyEnabled.isEnabled().booleanValue() || this.vremyaVyhodaEnabled.isEnabled().booleanValue() || this.distanciyaEnabled.isEnabled().booleanValue())) {
            return;
        }
        for (n_1700_B spot : this.multiplayerClientSuggestionProvider) {
            double distance = LogoutSpots.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(new e_2866_D(spot.J_1907_R, spot.R_4764_Y, spot.G_564_y));
            if (distance > (double)((Float)this.dalnostRenderaSetting.getValue()).floatValue()) continue;
            Vector2f screenPos = v_2826_q.n_1700_B(new e_2866_D(spot.J_1907_R, spot.R_4764_Y + 2.0, spot.G_564_y));
            if (screenPos.x == Float.MAX_VALUE) continue;
            float x = screenPos.x;
            float y = screenPos.y;
            ArrayList<String> lines = new ArrayList<String>();
            if (this.imyaIgrokaEnabled.isEnabled().booleanValue()) {
                lines.add(spot.n_1700_B);
            }
            if (this.koordinatyEnabled.isEnabled().booleanValue()) {
                lines.add(String.format("X: %.0f Y: %.0f Z: %.0f", spot.J_1907_R, spot.R_4764_Y, spot.G_564_y));
            }
            if (this.vremyaVyhodaEnabled.isEnabled().booleanValue()) {
                lines.add(this.n_1700_B(spot.P_1922_E));
            }
            if (this.distanciyaEnabled.isEnabled().booleanValue()) {
                lines.add(String.format("%.1f \u0431\u043b\u043e\u043a\u043e\u0432", distance));
            }
            Z_3822_q font = l_3370_o.J_1907_R[14];
            float lineHeight = 10.0f;
            float totalHeight = (float)lines.size() * lineHeight;
            float startY = y - totalHeight / 2.0f;
            int themeColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            for (int i = 0; i < lines.size(); ++i) {
                String line = (String)lines.get(i);
                float textWidth = font.n_1700_B(line);
                float textX = x - textWidth / 2.0f;
                float textY = startY + (float)i * lineHeight;
                int textColor = i == 0 && this.imyaIgrokaEnabled.isEnabled() != false ? themeColor : -1;
                font.n_1700_B(event.J_1907_R(), line, (double)textX, (double)textY, textColor);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        this.Q_2552_b.clear();
        this.Q_4569_t();
    }

    private String n_1700_B(long timestamp) {
        long diff = System.currentTimeMillis() - timestamp;
        long seconds = diff / 1000L;
        long minutes = seconds / 60L;
        long hours = minutes / 60L;
        long days = hours / 24L;
        if (days > 0L) {
            return this.w_1457_N.format(new Date(timestamp));
        }
        if (hours > 0L) {
            return hours + " \u0447. \u043d\u0430\u0437\u0430\u0434";
        }
        if (minutes > 0L) {
            return minutes + " \u043c\u0438\u043d. \u043d\u0430\u0437\u0430\u0434";
        }
        return "\u0442\u043e\u043b\u044c\u043a\u043e \u0447\u0442\u043e";
    }

    private void multiplayerClientSuggestionProvider() {
        this.multiplayerClientSuggestionProvider.clear();
        if (this.sohranyatVFaylEnabled.isEnabled().booleanValue()) {
            this.w_1457_N();
        }
        v_1900_v.n_1700_B("[LogoutSpots] \u0412\u0441\u0435 \u0442\u043e\u0447\u043a\u0438 \u0434\u043b\u044f " + (this.Y_259_p != null ? this.Y_259_p : "\u0442\u0435\u043a\u0443\u0449\u0435\u0433\u043e \u0441\u0435\u0440\u0432\u0435\u0440\u0430") + " \u043e\u0447\u0438\u0449\u0435\u043d\u044b!", new Object[0]);
    }

    private void w_1457_N() {
        String filePath = this.t_1786_h();
        if (filePath == null) {
            return;
        }
        try {
            File file = new File(C_332_W.n_1700_B + filePath);
            file.getParentFile().mkdirs();
            JsonArray array = new JsonArray();
            for (n_1700_B spot : this.multiplayerClientSuggestionProvider) {
                JsonObject obj = new JsonObject();
                obj.addProperty("name", spot.n_1700_B);
                obj.addProperty("x", (Number)spot.J_1907_R);
                obj.addProperty("y", (Number)spot.R_4764_Y);
                obj.addProperty("z", (Number)spot.G_564_y);
                obj.addProperty("time", (Number)spot.P_1922_E);
                array.add((JsonElement)obj);
            }
            Files.writeString(file.toPath(), (CharSequence)new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)array), new OpenOption[0]);
        }
        catch (Exception e) {
            System.err.println("[LogoutSpots] Failed to save: " + e.getMessage());
        }
    }

    private void Y_601_j() {
        String filePath = this.t_1786_h();
        if (filePath == null) {
            return;
        }
        try {
            File file = new File(C_332_W.n_1700_B + filePath);
            if (!file.exists()) {
                return;
            }
            String content = Files.readString(file.toPath());
            JsonArray array = new JsonParser().parse(content).getAsJsonArray();
            this.multiplayerClientSuggestionProvider.clear();
            for (JsonElement element : array) {
                JsonObject obj = element.getAsJsonObject();
                String name = obj.get("name").getAsString();
                double x = obj.get("x").getAsDouble();
                double y = obj.get("y").getAsDouble();
                double z = obj.get("z").getAsDouble();
                long time = obj.get("time").getAsLong();
                this.multiplayerClientSuggestionProvider.add(new n_1700_B(name, x, y, z, time));
            }
            if (!this.multiplayerClientSuggestionProvider.isEmpty()) {
                v_1900_v.n_1700_B("[LogoutSpots] \u0417\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u043e " + this.multiplayerClientSuggestionProvider.size() + " \u0442\u043e\u0447\u0435\u043a", new Object[0]);
            }
        }
        catch (Exception e) {
            System.err.println("[LogoutSpots] Failed to load: " + e.getMessage());
        }
    }

    private static class J_1907_R {
        final String n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;
        final double G_564_y;
        final long P_1922_E;

        J_1907_R(String playerName, double x, double y, double z, long timestamp) {
            this.n_1700_B = playerName;
            this.J_1907_R = x;
            this.R_4764_Y = y;
            this.G_564_y = z;
            this.P_1922_E = timestamp;
        }
    }

    private static class n_1700_B {
        final String n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;
        final double G_564_y;
        final long P_1922_E;

        n_1700_B(String playerName, double x, double y, double z, long timestamp) {
            this.n_1700_B = playerName;
            this.J_1907_R = x;
            this.R_4764_Y = y;
            this.G_564_y = z;
            this.P_1922_E = timestamp;
        }
    }
}



