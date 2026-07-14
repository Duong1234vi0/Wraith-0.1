/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.CallSite;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import lightning.product.D_4024_W;
import lightning.product.G_624_v;
import lightning.product.H_2506_c;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.v_1900_v;

public final class G_1539_D
implements MinecraftAccess {
    public static final G_1539_D n_1700_B;
    public static volatile String J_1907_R;
    private static volatile String R_4764_Y;
    private final AtomicInteger G_564_y = new AtomicInteger(0);
    private static final int P_1922_E = 18000;
    private static final int u_1723_Y = 18000;
    private static final int v_4262_N = 22000;
    private static final int w_1484_f = 3;
    private static final long t_148_a = 45000L;
    private static final Pattern s_956_w;
    private final CopyOnWriteArraySet<String> u_2550_I = new CopyOnWriteArraySet();
    private volatile List<n_1700_B> M_588_G = Collections.emptyList();
    private volatile List<J_1907_R> P_4830_p = Collections.emptyList();
    private volatile boolean h_1847_R;
    private volatile long Q_4569_t;
    private final AtomicBoolean M_182_A = new AtomicBoolean(false);
    private volatile long t_1786_h;
    private Thread multiplayerClientSuggestionProvider;
    private volatile boolean w_1457_N;
    private volatile int Y_601_j;
    private volatile long Y_259_p;
    private volatile boolean Q_2552_b;
    private volatile long C_2741_M;
    private static final long k_2293_S = 1500L;
    private static final long q_2307_F = 500L;
    private static final long Z_875_P = 1500L;
    private static final double t_4043_B = 20.0;
    private static final String x_607_J;
    private static final String e_4240_b;
    private static final String n_3318_d;

    private G_1539_D() {
    }

    public static String n_1700_B(String url) {
        if (url == null) {
            return null;
        }
        String u = url.trim();
        if (u.isEmpty()) {
            return null;
        }
        while (u.endsWith("/")) {
            u = u.substring(0, u.length() - 1);
        }
        return u;
    }

    public List<String> n_1700_B() {
        String prop = System.getProperty("pouch.irc.url");
        if (prop != null && !prop.trim().isEmpty()) {
            return G_1539_D.P_1922_E(prop);
        }
        return G_1539_D.P_1922_E(J_1907_R);
    }

    private static List<String> P_1922_E(String raw) {
        ArrayList<String> out = new ArrayList<String>();
        if (raw == null) {
            return out;
        }
        for (String segment : raw.split(",")) {
            String n = G_1539_D.n_1700_B(segment);
            if (n == null) continue;
            out.add(n);
        }
        return out;
    }

    public String J_1907_R() {
        List<String> bases = this.n_1700_B();
        if (bases.isEmpty()) {
            return null;
        }
        int idx = Math.floorMod(this.G_564_y.get(), bases.size());
        return bases.get(idx);
    }

    public void R_4764_Y() {
        List<String> bases = this.n_1700_B();
        if (bases.size() <= 1) {
            return;
        }
        this.G_564_y.incrementAndGet();
    }

    public boolean G_564_y() {
        return !this.n_1700_B().isEmpty();
    }

    public boolean P_1922_E() {
        return this.M_182_A.get();
    }

    public static String u_1723_Y() {
        return R_4764_Y;
    }

    public static boolean v_4262_N() {
        String c = R_4764_Y;
        return c != null && !c.isEmpty();
    }

    private static void u_1723_Y(String code) {
        R_4764_Y = code == null || code.isEmpty() ? null : code;
    }

    public static boolean J_1907_R(String code) {
        if (code == null) {
            return false;
        }
        return s_956_w.matcher(code.trim()).matches();
    }

    public static boolean R_4764_Y(String mcGameProfileName) {
        return n_1700_B.v_4262_N(mcGameProfileName);
    }

    public static List<n_1700_B> w_1484_f() {
        return G_1539_D.n_1700_B.M_588_G;
    }

    public static List<J_1907_R> t_148_a() {
        return G_1539_D.n_1700_B.P_4830_p;
    }

    public static void s_956_w() {
        G_1539_D.n_1700_B.h_1847_R = false;
        G_1539_D.n_1700_B.Q_4569_t = 0L;
    }

    synchronized void u_2550_I() {
        this.P_4830_p = Collections.emptyList();
        this.h_1847_R = false;
        this.Q_4569_t = 0L;
    }

    private boolean v_4262_N(String mcGameProfileName) {
        if (!this.M_182_A.get() || mcGameProfileName == null) {
            return false;
        }
        if (!G_1539_D.v_4262_N()) {
            return false;
        }
        return this.u_2550_I.contains(mcGameProfileName.toLowerCase(Locale.ROOT));
    }

    public synchronized void M_588_G() {
        if (!this.G_564_y()) {
            return;
        }
        if (this.M_182_A.get()) {
            return;
        }
        this.M_182_A.set(true);
        this.w_1457_N = false;
        this.Y_601_j = 0;
        this.Y_259_p = 0L;
        this.Q_2552_b = false;
        this.G_564_y.set(0);
        this.C_2741_M = 0L;
        this.t_1786_h = 0L;
        G_1539_D.s_956_w();
        this.multiplayerClientSuggestionProvider = new Thread(this::t_1786_h, "pouch-irc-poll");
        this.multiplayerClientSuggestionProvider.setDaemon(true);
        this.multiplayerClientSuggestionProvider.start();
    }

    public synchronized void P_4830_p() {
        String mcNick;
        this.M_182_A.set(false);
        this.w_1457_N = false;
        this.Y_601_j = 0;
        this.Q_2552_b = false;
        this.u_2550_I.clear();
        this.C_2741_M = 0L;
        this.M_588_G = Collections.emptyList();
        this.u_2550_I();
        String room = R_4764_Y;
        G_1539_D.u_1723_Y(null);
        Thread t = this.multiplayerClientSuggestionProvider;
        this.multiplayerClientSuggestionProvider = null;
        if (t != null) {
            t.interrupt();
        }
        if (room != null && c_3005_b != null && G_1539_D.c_3005_b.Y_259_p != null && this.G_564_y() && (mcNick = G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName()) != null && !mcNick.isEmpty()) {
            new Thread(() -> G_1539_D.t_148_a(mcNick), "pouch-party-leave-irc-off").start();
        }
    }

    private void t_1786_h() {
        for (int t = 0; t < Math.max(1, Math.min(5, this.n_1700_B().size() * 2)); ++t) {
            try {
                String base = this.J_1907_R();
                if (base == null || !this.M_182_A.get()) break;
                this.w_1484_f(base);
                break;
            }
            catch (Exception ignored) {
                this.R_4764_Y();
                continue;
            }
        }
        while (this.M_182_A.get() && !Thread.currentThread().isInterrupted()) {
            int maxTries = Math.max(1, Math.min(5, this.n_1700_B().size() * 2));
            for (int t = 0; t < maxTries && this.M_182_A.get() && !Thread.currentThread().isInterrupted(); ++t) {
                try {
                    JsonArray arr;
                    boolean attachPartyCoords;
                    String json;
                    String base = this.J_1907_R();
                    if (base == null || (json = G_1539_D.M_588_G(this.n_1700_B(base, this.t_1786_h, attachPartyCoords = this.Y_601_j()))) == null) break;
                    JsonObject root = new JsonParser().parse(json).getAsJsonObject();
                    if (root.has("party") && root.get("party").isJsonArray()) {
                        this.n_1700_B(root.getAsJsonArray("party"));
                    }
                    if (root.has("party_markers") && root.get("party_markers").isJsonArray()) {
                        this.J_1907_R(root.getAsJsonArray("party_markers"));
                    }
                    if ((arr = root.getAsJsonArray("messages")) != null) {
                        for (JsonElement el : arr) {
                            String rr;
                            JsonObject m = el.getAsJsonObject();
                            long id = m.get("id").getAsLong();
                            String nick = m.get("nick").getAsString();
                            String text = m.get("text").getAsString();
                            if (id > this.t_1786_h) {
                                this.t_1786_h = id;
                            }
                            Object roleBracket = "";
                            if (m.has("role") && !m.get("role").isJsonNull() && !(rr = G_1539_D.u_2550_I(m.get("role").getAsString())).isEmpty()) {
                                D_4024_W roleColor = rr.toLowerCase(Locale.ROOT).contains("admin") ? D_4024_W.P_4830_p : D_4024_W.v_4262_N;
                                roleBracket = String.valueOf((Object)D_4024_W.t_148_a) + "[" + String.valueOf((Object)roleColor) + rr + String.valueOf((Object)D_4024_W.t_148_a) + "] " + String.valueOf((Object)D_4024_W.Q_2552_b);
                            }
                            String line = (String)roleBracket + String.valueOf((Object)D_4024_W.w_1484_f) + "<" + String.valueOf((Object)D_4024_W.M_182_A) + nick + String.valueOf((Object)D_4024_W.w_1484_f) + "> " + String.valueOf((Object)D_4024_W.M_182_A) + text;
                            if (c_3005_b == null) continue;
                            c_3005_b.execute(() -> {
                                if (G_1539_D.c_3005_b.Y_259_p != null) {
                                    v_1900_v.J_1907_R(new U_2871_b(line), new Object[0]);
                                }
                            });
                        }
                    }
                    if (attachPartyCoords) {
                        this.C_2741_M = System.currentTimeMillis();
                    }
                    this.multiplayerClientSuggestionProvider();
                    break;
                }
                catch (Exception ignored) {
                    this.w_1457_N();
                    this.R_4764_Y();
                    try {
                        Thread.sleep(400L);
                        continue;
                    }
                    catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            try {
                long interval = G_1539_D.v_4262_N() ? 500L : 1500L;
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        this.M_182_A.set(false);
        this.Q_2552_b = false;
    }

    private void multiplayerClientSuggestionProvider() {
        this.Y_601_j = 0;
        if (this.w_1457_N) {
            return;
        }
        this.w_1457_N = true;
        if (this.Q_2552_b) {
            return;
        }
        this.Q_2552_b = true;
        if (c_3005_b != null) {
            c_3005_b.execute(() -> {
                if (G_1539_D.c_3005_b.Y_259_p != null && this.M_182_A.get()) {
                    v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.M_182_A) + "IRC: connect"), new Object[0]);
                }
            });
        }
    }

    private void w_1457_N() {
        ++this.Y_601_j;
        if (this.w_1457_N || this.Y_601_j < 3) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.Y_259_p < 45000L) {
            return;
        }
        this.Y_259_p = now;
        if (c_3005_b != null) {
            c_3005_b.execute(() -> {
                if (G_1539_D.c_3005_b.Y_259_p != null && this.M_182_A.get() && !this.w_1457_N) {
                    v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u041d\u0435 \u0443\u0434\u0430\u0451\u0442\u0441\u044f \u043f\u043e\u043b\u0443\u0447\u0430\u0442\u044c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u044f (\u0447\u0430\u0441\u0442\u043e \u0443 \u043f\u0440\u043e\u0432\u0430\u0439\u0434\u0435\u0440\u043e\u0432 \u0420\u0424 \u0440\u0435\u0436\u0443\u0442 \u043f\u043e\u0440\u0442 6767). " + String.valueOf((Object)D_4024_W.w_1484_f) + "\u041d\u0443\u0436\u0435\u043d relay \u043d\u0430 https://:443 \u0438\u043b\u0438 VPN/\u043f\u0440\u043e\u043a\u0441\u0438: " + String.valueOf((Object)D_4024_W.M_182_A) + "-Dpouch.irc.proxy.type=http -Dpouch.irc.proxy.host=127.0.0.1 -Dpouch.irc.proxy.port=7890"), new Object[0]);
                }
            });
        }
    }

    private boolean Y_601_j() {
        if (!G_1539_D.v_4262_N() || c_3005_b == null || G_1539_D.c_3005_b.Y_259_p == null) {
            return false;
        }
        String room = R_4764_Y;
        if (room == null || !s_956_w.matcher(room.trim()).matches()) {
            return false;
        }
        long now = System.currentTimeMillis();
        return now - this.C_2741_M >= 1500L;
    }

    private String n_1700_B(String base, long afterId, boolean includePartyCoords) {
        StringBuilder sb = new StringBuilder(base).append("/irc/poll?after=").append(afterId);
        try {
            String mcNick;
            String irc;
            if (c_3005_b != null && G_1539_D.c_3005_b.Y_259_p != null && (irc = G_624_v.t_148_a.n_1700_B) != null && !irc.trim().isEmpty() && (mcNick = G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName()) != null && !mcNick.isEmpty()) {
                boolean inPartyRoom;
                sb.append("&presence_irc=").append(URLEncoder.encode(irc.trim(), StandardCharsets.UTF_8));
                sb.append("&presence_mc=").append(URLEncoder.encode(mcNick, StandardCharsets.UTF_8));
                String room = R_4764_Y;
                boolean bl = inPartyRoom = room != null && s_956_w.matcher(room.trim()).matches();
                if (inPartyRoom) {
                    sb.append("&party_room=").append(URLEncoder.encode(room.trim(), StandardCharsets.UTF_8));
                    if (includePartyCoords) {
                        sb.append("&px=").append(G_1539_D.c_3005_b.Y_259_p.O_3598_v());
                        sb.append("&py=").append(G_1539_D.c_3005_b.Y_259_p.X_2960_b());
                        sb.append("&pz=").append(G_1539_D.c_3005_b.Y_259_p.l_2647_k());
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return sb.toString();
    }

    private void n_1700_B(JsonArray arr) {
        HashSet<String> next = new HashSet<String>();
        ArrayList<n_1700_B> remotes = new ArrayList<n_1700_B>();
        for (JsonElement el : arr) {
            String mcp;
            JsonObject o;
            if (!el.isJsonObject() || !(o = el.getAsJsonObject()).has("mc") || (mcp = o.get("mc").getAsString()) == null || mcp.isEmpty()) continue;
            next.add(mcp.toLowerCase(Locale.ROOT));
            boolean hasPos = o.has("x") && !o.get("x").isJsonNull() && o.has("y") && !o.get("y").isJsonNull() && o.has("z") && !o.get("z").isJsonNull();
            double px = 0.0;
            double py = 0.0;
            double pz = 0.0;
            if (hasPos) {
                px = o.get("x").getAsDouble();
                py = o.get("y").getAsDouble();
                pz = o.get("z").getAsDouble();
            }
            remotes.add(new n_1700_B(mcp, hasPos, px, py, pz));
        }
        this.u_2550_I.clear();
        this.u_2550_I.addAll(next);
        this.M_588_G = Collections.unmodifiableList(remotes);
    }

    private synchronized void n_1700_B(J_1907_R marker) {
        String fk;
        if (!this.M_182_A.get()) {
            return;
        }
        ArrayList<J_1907_R> next = new ArrayList<J_1907_R>(this.P_4830_p);
        String string = fk = marker.J_1907_R == null ? "" : marker.J_1907_R.toLowerCase(Locale.ROOT);
        if (!fk.isEmpty()) {
            next.removeIf(x -> x.J_1907_R != null && x.J_1907_R.toLowerCase(Locale.ROOT).equals(fk));
        }
        next.removeIf(x -> x.n_1700_B == marker.n_1700_B);
        next.add(marker);
        this.P_4830_p = Collections.unmodifiableList(next);
        this.Q_4569_t = Math.max(this.Q_4569_t, marker.n_1700_B);
    }

    private synchronized void J_1907_R(JsonArray arr) {
        ArrayList<J_1907_R> list = new ArrayList<J_1907_R>();
        for (JsonElement el : arr) {
            JsonObject o;
            if (!el.isJsonObject() || !(o = el.getAsJsonObject()).has("id") || !o.has("from_mc") || !o.has("exp")) continue;
            long id = o.get("id").getAsLong();
            String from = o.get("from_mc").getAsString();
            String tgt = o.has("target_mc") && !o.get("target_mc").isJsonNull() ? o.get("target_mc").getAsString() : "";
            double px = o.has("px") ? o.get("px").getAsDouble() : 0.0;
            double py = o.has("py") ? o.get("py").getAsDouble() : 0.0;
            double pz = o.has("pz") ? o.get("pz").getAsDouble() : 0.0;
            double exp = o.get("exp").getAsDouble();
            list.add(new J_1907_R(id, from, tgt, px, py, pz, exp));
        }
        long batchMax = 0L;
        for (J_1907_R m : list) {
            batchMax = Math.max(batchMax, m.n_1700_B);
        }
        List<J_1907_R> prev = this.P_4830_p;
        for (J_1907_R keep : prev) {
            if (keep.n_1700_B <= batchMax || !list.stream().noneMatch(x -> x.n_1700_B == keep.n_1700_B)) continue;
            list.add(keep);
        }
        long mergedMax = 0L;
        for (J_1907_R m : list) {
            mergedMax = Math.max(mergedMax, m.n_1700_B);
        }
        if (!this.h_1847_R) {
            this.Q_4569_t = mergedMax;
            this.h_1847_R = true;
            this.P_4830_p = Collections.unmodifiableList(list);
            return;
        }
        long prevMax = this.Q_4569_t;
        String selfMc = c_3005_b != null && G_1539_D.c_3005_b.Y_259_p != null && G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName() != null ? G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName() : "";
        for (J_1907_R m : list) {
            if (m.n_1700_B <= prevMax || m.R_4764_Y == null || m.R_4764_Y.isEmpty() || !selfMc.isEmpty() && m.J_1907_R != null && m.J_1907_R.equalsIgnoreCase(selfMc)) continue;
            String msg = "\u041e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u044c \u0443 " + m.R_4764_Y;
            if (c_3005_b == null) continue;
            c_3005_b.execute(() -> U_3758_B.n_1700_B("M", msg, H_2506_c.n_1700_B(255, 200, 60)));
        }
        this.Q_4569_t = Math.max(prevMax, mergedMax);
        this.P_4830_p = Collections.unmodifiableList(list);
    }

    private void w_1484_f(String base) throws IOException {
        String json = G_1539_D.M_588_G(this.n_1700_B(base, 0L, false));
        if (json == null) {
            return;
        }
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();
        if (root.has("party") && root.get("party").isJsonArray()) {
            this.n_1700_B(root.getAsJsonArray("party"));
        }
        if (root.has("party_markers") && root.get("party_markers").isJsonArray()) {
            this.J_1907_R(root.getAsJsonArray("party_markers"));
        }
        JsonArray arr = root.getAsJsonArray("messages");
        long max = this.t_1786_h;
        if (arr != null) {
            for (JsonElement el : arr) {
                long id = el.getAsJsonObject().get("id").getAsLong();
                if (id <= max) continue;
                max = id;
            }
        }
        this.t_1786_h = max;
        this.multiplayerClientSuggestionProvider();
    }

    public void n_1700_B(String room, String fromMc, String targetMcOrNull, double px, double py, double pz) {
        String urlBase = this.J_1907_R();
        String r = room == null ? "" : room.trim();
        String from = fromMc == null ? "" : fromMc;
        String tgt = targetMcOrNull == null || targetMcOrNull.trim().isEmpty() ? null : targetMcOrNull.trim();
        new Thread(() -> {
            block10: {
                try {
                    if (urlBase == null || r.isEmpty() || from.isEmpty()) {
                        return;
                    }
                    StringBuilder jb = new StringBuilder();
                    jb.append("{\"room\":\"").append(G_1539_D.s_956_w(r)).append("\",\"from_mc\":\"").append(G_1539_D.s_956_w(from));
                    jb.append("\",\"px\":").append(px).append(",\"py\":").append(py).append(",\"pz\":").append(pz);
                    if (tgt != null) {
                        jb.append(",\"target_mc\":\"").append(G_1539_D.s_956_w(tgt)).append("\"");
                    }
                    jb.append("}");
                    String resp = G_1539_D.R_4764_Y(urlBase + "/irc/party/marker", jb.toString());
                    JsonObject root = new JsonParser().parse(resp).getAsJsonObject();
                    if (!"1".equals(root.has("ok") ? root.get("ok").getAsString() : "0")) {
                        String err;
                        String e = err = root.has("error") ? root.get("error").getAsString() : "\u043e\u0448\u0438\u0431\u043a\u0430";
                        if (c_3005_b != null) {
                            c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041c\u0435\u0442\u043a\u0430: " + e), new Object[0]));
                        }
                    } else if (root.has("id")) {
                        try {
                            long idNew = Long.parseLong(root.get("id").getAsString());
                            double exp = (double)System.currentTimeMillis() / 1000.0 + 20.0;
                            String tgtStr = tgt == null ? "" : tgt;
                            J_1907_R optimistic = new J_1907_R(idNew, from, tgtStr, px, py, pz, exp);
                            n_1700_B.n_1700_B(optimistic);
                        }
                        catch (Exception exception) {}
                    }
                }
                catch (Exception ex) {
                    if (c_3005_b == null) break block10;
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(n_3318_d), new Object[0]));
                }
            }
        }, "pouch-party-marker").start();
    }

    public void h_1847_R() {
        if (c_3005_b == null || G_1539_D.c_3005_b.Y_259_p == null) {
            return;
        }
        String mcNick = G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName();
        if (mcNick == null || mcNick.isEmpty()) {
            return;
        }
        String urlBase = this.J_1907_R();
        new Thread(() -> {
            try {
                String code;
                if (urlBase == null) {
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay"), new Object[0]));
                    return;
                }
                String body = "{\"mc\":\"" + G_1539_D.s_956_w(mcNick) + "\"}";
                String resp = G_1539_D.R_4764_Y(urlBase + "/irc/party/create", body);
                JsonObject root = new JsonParser().parse(resp).getAsJsonObject();
                if (!"1".equals(root.has("ok") ? root.get("ok").getAsString() : "0")) {
                    String err;
                    String e = err = root.has("error") ? root.get("error").getAsString() : "\u043e\u0448\u0438\u0431\u043a\u0430";
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] " + e), new Object[0]));
                    return;
                }
                String c = code = root.get("code").getAsString();
                c_3005_b.execute(() -> {
                    G_1539_D.u_1723_Y(c);
                    G_1539_D.s_956_w();
                    v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.u_2550_I) + "[Party] \u041b\u0438\u0447\u043d\u0430\u044f \u043a\u043e\u043c\u043d\u0430\u0442\u0430. \u041a\u043e\u0434 \u0434\u043b\u044f \u0434\u0440\u0443\u0437\u0435\u0439: " + String.valueOf((Object)D_4024_W.M_182_A) + c), new Object[0]);
                    v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.w_1484_f) + "[Party] \u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435: .party join " + c), new Object[0]);
                });
            }
            catch (Exception e) {
                c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(e_4240_b), new Object[0]));
            }
        }, "pouch-party-create").start();
    }

    public void G_564_y(String codeRaw) {
        String code;
        if (c_3005_b == null || G_1539_D.c_3005_b.Y_259_p == null) {
            return;
        }
        String mcNick = G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName();
        if (mcNick == null || mcNick.isEmpty()) {
            return;
        }
        String string = code = codeRaw == null ? "" : codeRaw.trim();
        if (!G_1539_D.J_1907_R(code)) {
            v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041a\u043e\u0434 \u2014 6 \u0446\u0438\u0444\u0440, \u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440 .party join 798546"), new Object[0]);
            return;
        }
        String urlBase = this.J_1907_R();
        String c = code;
        new Thread(() -> {
            try {
                if (urlBase == null) {
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay"), new Object[0]));
                    return;
                }
                String body = "{\"mc\":\"" + G_1539_D.s_956_w(mcNick) + "\",\"code\":\"" + G_1539_D.s_956_w(c) + "\"}";
                String resp = G_1539_D.R_4764_Y(urlBase + "/irc/party/join", body);
                JsonObject root = new JsonParser().parse(resp).getAsJsonObject();
                if (!"1".equals(root.has("ok") ? root.get("ok").getAsString() : "0")) {
                    String err;
                    String e = err = root.has("error") ? root.get("error").getAsString() : "\u043e\u0448\u0438\u0431\u043a\u0430";
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] " + e), new Object[0]));
                    return;
                }
                c_3005_b.execute(() -> {
                    G_1539_D.u_1723_Y(c);
                    G_1539_D.s_956_w();
                    v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.u_2550_I) + "[Party] \u0412\u044b \u0432 \u043a\u043e\u043c\u043d\u0430\u0442\u0435 " + String.valueOf((Object)D_4024_W.M_182_A) + c), new Object[0]);
                });
            }
            catch (Exception e) {
                c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(e_4240_b), new Object[0]));
            }
        }, "pouch-party-join").start();
    }

    public void Q_4569_t() {
        if (c_3005_b == null || G_1539_D.c_3005_b.Y_259_p == null) {
            return;
        }
        String mcNick = G_1539_D.c_3005_b.Y_259_p.y_4642_Y().getName();
        if (mcNick == null || mcNick.isEmpty()) {
            return;
        }
        if (!G_1539_D.v_4262_N()) {
            v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.w_1484_f) + "[Party] \u0412\u044b \u043d\u0435 \u0432 \u043b\u0438\u0447\u043d\u043e\u0439 \u043a\u043e\u043c\u043d\u0430\u0442\u0435"), new Object[0]);
            return;
        }
        String urlBase = this.J_1907_R();
        new Thread(() -> {
            try {
                if (urlBase != null) {
                    String body = "{\"mc\":\"" + G_1539_D.s_956_w(mcNick) + "\"}";
                    G_1539_D.R_4764_Y(urlBase + "/irc/party/leave", body);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            c_3005_b.execute(() -> {
                G_1539_D.u_1723_Y(null);
                this.u_2550_I();
                v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.w_1484_f) + "[Party] \u0412\u044b \u0432\u044b\u0448\u043b\u0438 \u0438\u0437 \u043b\u0438\u0447\u043d\u043e\u0439 \u043a\u043e\u043c\u043d\u0430\u0442\u044b"), new Object[0]);
            });
        }, "pouch-party-leave").start();
    }

    public void M_182_A() {
        if (!this.G_564_y()) {
            if (c_3005_b != null) {
                c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay"), new Object[0]));
            }
            return;
        }
        String code = G_1539_D.u_1723_Y();
        if (code == null || !G_1539_D.J_1907_R(code)) {
            v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u0412\u044b \u043d\u0435 \u0432 \u043b\u0438\u0447\u043d\u043e\u0439 \u043a\u043e\u043c\u043d\u0430\u0442\u0435 (.party create / .party join)"), new Object[0]);
            return;
        }
        String room = code.trim();
        String urlBase = this.J_1907_R();
        new Thread(() -> {
            block9: {
                try {
                    if (urlBase == null) {
                        if (c_3005_b != null) {
                            c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay"), new Object[0]));
                        }
                        return;
                    }
                    String q = urlBase + "/irc/party?party_room=" + URLEncoder.encode(room, StandardCharsets.UTF_8);
                    String resp = G_1539_D.M_588_G(q);
                    JsonObject root = new JsonParser().parse(resp).getAsJsonObject();
                    JsonArray arr = root.getAsJsonArray("members");
                    ArrayList<CallSite> lines = new ArrayList<CallSite>();
                    lines.add((CallSite)((Object)(String.valueOf((Object)D_4024_W.u_2550_I) + "[Party] \u041a\u043e\u0434: " + String.valueOf((Object)D_4024_W.M_182_A) + room + String.valueOf((Object)D_4024_W.t_148_a) + " (API)")));
                    if (arr == null || arr.size() == 0) {
                        lines.add((CallSite)((Object)(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0423\u0447\u0430\u0441\u0442\u043d\u0438\u043a\u043e\u0432 \u0432 \u043e\u0442\u0432\u0435\u0442\u0435 \u043d\u0435\u0442 (\u043d\u0438\u043a\u0442\u043e \u043d\u0435 \u0432 \u043a\u043e\u043c\u043d\u0430\u0442\u0435 \u0438\u043b\u0438 presence \u0435\u0449\u0451 \u043d\u0435 \u043e\u0431\u043d\u043e\u0432\u043b\u0451\u043d).")));
                    } else {
                        lines.add((CallSite)((Object)(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0423\u0447\u0430\u0441\u0442\u043d\u0438\u043a\u0438 (" + arr.size() + "):")));
                        for (JsonElement el : arr) {
                            if (!el.isJsonObject()) continue;
                            JsonObject o = el.getAsJsonObject();
                            String mcp = o.has("mc") && !o.get("mc").isJsonNull() ? o.get("mc").getAsString() : "?";
                            String ircp = o.has("irc") && !o.get("irc").isJsonNull() ? o.get("irc").getAsString() : "";
                            String row = String.valueOf((Object)D_4024_W.M_182_A) + " \u00b7 " + mcp;
                            if (!ircp.isEmpty() && !ircp.equalsIgnoreCase(mcp)) {
                                row = row + String.valueOf((Object)D_4024_W.t_148_a) + " (irc: " + String.valueOf((Object)D_4024_W.w_1484_f) + ircp + String.valueOf((Object)D_4024_W.t_148_a) + ")";
                            }
                            lines.add((CallSite)((Object)row));
                        }
                    }
                    ArrayList<CallSite> toPrint = lines;
                    if (c_3005_b != null) {
                        c_3005_b.execute(() -> {
                            for (String line : toPrint) {
                                v_1900_v.n_1700_B(new U_2871_b(line), new Object[0]);
                            }
                        });
                    }
                }
                catch (Exception e) {
                    if (c_3005_b == null) break block9;
                    c_3005_b.execute(() -> v_1900_v.n_1700_B(new U_2871_b(e_4240_b), new Object[0]));
                }
            }
        }, "pouch-party-info").start();
    }

    private static void t_148_a(String mcNick) {
        try {
            String urlBase = n_1700_B.J_1907_R();
            if (urlBase == null) {
                return;
            }
            String body = "{\"mc\":\"" + G_1539_D.s_956_w(mcNick) + "\"}";
            G_1539_D.R_4764_Y(urlBase + "/irc/party/leave", body);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void n_1700_B(String nick, String text) {
        this.J_1907_R(nick, text, null);
    }

    public void n_1700_B(String nick, String text, String partyRoom) {
        this.J_1907_R(nick, text, partyRoom);
    }

    private void J_1907_R(String nick, String text, String partyRoomOrNull) {
        String safeNick = nick == null ? "?" : nick.replace("\"", "'");
        String safeText = text == null ? "" : text;
        String safeRole = G_1539_D.Y_259_p();
        String partyTrimmed = partyRoomOrNull == null ? null : partyRoomOrNull.trim();
        new Thread(() -> {
            if (this.n_1700_B().isEmpty()) {
                if (c_3005_b != null) {
                    c_3005_b.execute(() -> v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay: DEFAULT_IRC_BASE \u0432 \u043a\u043e\u0434\u0435 \u0438\u043b\u0438 -Dpouch.irc.url=\u2026"), new Object[0]));
                }
                return;
            }
            StringBuilder jb = new StringBuilder();
            jb.append("{\"nick\":\"").append(G_1539_D.s_956_w(safeNick)).append("\",\"text\":\"").append(G_1539_D.s_956_w(safeText)).append("\"");
            if (safeRole != null) {
                jb.append(",\"role\":\"").append(G_1539_D.s_956_w(safeRole)).append("\"");
            }
            if (partyTrimmed != null && G_1539_D.J_1907_R(partyTrimmed)) {
                jb.append(",\"party_room\":\"").append(G_1539_D.s_956_w(partyTrimmed)).append("\"");
            }
            jb.append("}");
            String body = jb.toString();
            int maxSend = Math.max(2, Math.min(6, this.n_1700_B().size() + 2));
            for (int attempt = 0; attempt < maxSend; ++attempt) {
                try {
                    String urlBase = this.J_1907_R();
                    if (urlBase == null) {
                        if (c_3005_b != null) {
                            c_3005_b.execute(() -> v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay: DEFAULT_IRC_BASE \u0432 \u043a\u043e\u0434\u0435 \u0438\u043b\u0438 -Dpouch.irc.url=\u2026"), new Object[0]));
                        }
                        return;
                    }
                    int code = G_1539_D.J_1907_R(urlBase + "/irc/send", body);
                    if (code >= 200 && code < 300) {
                        return;
                    }
                    this.R_4764_Y();
                    continue;
                }
                catch (Exception e) {
                    this.R_4764_Y();
                }
            }
            if (c_3005_b != null) {
                c_3005_b.execute(() -> v_1900_v.J_1907_R(new U_2871_b(x_607_J).n_1700_B(Z_1567_W.n_1700_B), new Object[0]));
            }
        }, "pouch-irc-send").start();
    }

    private static String s_956_w(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r", " ").replace("\n", " ");
    }

    private static String Y_259_p() {
        try {
            if (G_624_v.t_148_a == null) {
                return null;
            }
            String r = G_624_v.t_148_a.J_1907_R;
            if (r == null) {
                return null;
            }
            if ((r = r.trim()).isEmpty()) {
                return null;
            }
            return G_1539_D.u_2550_I(r);
        }
        catch (Exception ignored) {
            return null;
        }
    }

    private static String u_2550_I(String r) {
        if (r == null) {
            return "";
        }
        String t = r.replace('\r', ' ').replace('\n', ' ').replace('\u00a7', ' ');
        if (t.length() > 48) {
            t = t.substring(0, 48);
        }
        return t.trim();
    }

    private static void n_1700_B(HttpURLConnection c, int readTimeoutMs) {
        c.setConnectTimeout(18000);
        c.setReadTimeout(readTimeoutMs);
        c.setRequestProperty("User-Agent", "PouchClient-IRC/1.0 (Java)");
        c.setRequestProperty("Accept", "application/json,*/*;q=0.1");
        c.setRequestProperty("Connection", "close");
        c.setInstanceFollowRedirects(true);
    }

    private static HttpURLConnection n_1700_B(URL url) throws IOException {
        return (HttpURLConnection)url.openConnection(G_1539_D.Q_2552_b());
    }

    private static Proxy Q_2552_b() {
        int port;
        String host = System.getProperty("pouch.irc.proxy.host", "").trim();
        String portStr = System.getProperty("pouch.irc.proxy.port", "").trim();
        if (host.isEmpty() || portStr.isEmpty()) {
            return Proxy.NO_PROXY;
        }
        try {
            port = Integer.parseInt(portStr);
        }
        catch (NumberFormatException e) {
            return Proxy.NO_PROXY;
        }
        String type = System.getProperty("pouch.irc.proxy.type", "").trim().toLowerCase(Locale.ROOT);
        if ("socks".equals(type) || "socks5".equals(type) || "socks4".equals(type)) {
            return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }

    private static int J_1907_R(String urlStr, String json) throws IOException {
        InputStream in;
        URL url = new URL(urlStr);
        HttpURLConnection c = G_1539_D.n_1700_B(url);
        c.setRequestMethod("POST");
        G_1539_D.n_1700_B(c, 22000);
        c.setDoOutput(true);
        c.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        c.setFixedLengthStreamingMode(bytes.length);
        try (OutputStream os = c.getOutputStream();){
            os.write(bytes);
        }
        int code = c.getResponseCode();
        InputStream inputStream = in = code >= 200 && code < 300 ? c.getInputStream() : c.getErrorStream();
        if (in != null) {
            G_1539_D.n_1700_B(in);
        }
        return code;
    }

    private static String R_4764_Y(String urlStr, String json) throws IOException {
        InputStream in;
        URL url = new URL(urlStr);
        HttpURLConnection c = G_1539_D.n_1700_B(url);
        c.setRequestMethod("POST");
        G_1539_D.n_1700_B(c, 22000);
        c.setDoOutput(true);
        c.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        c.setFixedLengthStreamingMode(bytes.length);
        try (OutputStream os = c.getOutputStream();){
            os.write(bytes);
        }
        int code = c.getResponseCode();
        InputStream inputStream = in = code >= 200 && code < 300 ? c.getInputStream() : c.getErrorStream();
        if (in == null) {
            throw new IOException("HTTP " + code);
        }
        String body = new String(G_1539_D.J_1907_R(in), StandardCharsets.UTF_8);
        if (code < 200 || code >= 300) {
            throw new IOException("HTTP " + code + ": " + body);
        }
        return body;
    }

    private static String M_588_G(String urlStr) throws IOException {
        InputStream in;
        URL url = new URL(urlStr);
        HttpURLConnection c = G_1539_D.n_1700_B(url);
        c.setRequestMethod("GET");
        G_1539_D.n_1700_B(c, 18000);
        int code = c.getResponseCode();
        InputStream inputStream = in = code >= 200 && code < 300 ? c.getInputStream() : c.getErrorStream();
        if (in == null) {
            throw new IOException("HTTP " + code);
        }
        try (InputStream stream = in;){
            String string = new String(G_1539_D.J_1907_R(stream), StandardCharsets.UTF_8);
            return string;
        }
    }

    private static void n_1700_B(InputStream in) throws IOException {
        if (in == null) {
            return;
        }
        G_1539_D.J_1907_R(in);
    }

    private static byte[] J_1907_R(InputStream in) throws IOException {
        int n;
        byte[] buf = new byte[8192];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((n = in.read(buf)) >= 0) {
            bos.write(buf, 0, n);
        }
        return bos.toByteArray();
    }

    static {
        boolean enable = true;
        String override = System.getProperty("pouch.irc.ruHardening");
        if (override != null && !override.isEmpty()) {
            enable = Boolean.parseBoolean(override.trim());
        }
        if (enable) {
            System.setProperty("java.net.preferIPv4Addresses", "true");
        }
        n_1700_B = new G_1539_D();
        J_1907_R = "http://147.45.45.43:6767";
        s_956_w = Pattern.compile("^\\d{6}$");
        x_607_J = String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u041d\u0435\u0442 \u0441\u0432\u044f\u0437\u0438 \u0441 relay";
        e_4240_b = String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0441\u0432\u044f\u0437\u0438 \u0441 relay";
        n_3318_d = String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041c\u0435\u0442\u043a\u0430: \u043d\u0435\u0442 \u0441\u0432\u044f\u0437\u0438 \u0441 relay";
    }

    public static final class n_1700_B {
        public final String n_1700_B;
        public final boolean J_1907_R;
        public final double R_4764_Y;
        public final double G_564_y;
        public final double P_1922_E;

        public n_1700_B(String mc, boolean hasPosition, double x, double y, double z) {
            this.n_1700_B = mc;
            this.J_1907_R = hasPosition;
            this.R_4764_Y = x;
            this.G_564_y = y;
            this.P_1922_E = z;
        }
    }

    public static final class J_1907_R {
        public final long n_1700_B;
        public final String J_1907_R;
        public final String R_4764_Y;
        public final double G_564_y;
        public final double P_1922_E;
        public final double u_1723_Y;
        public final double v_4262_N;

        public J_1907_R(long id, String fromMc, String targetMc, double px, double py, double pz, double exp) {
            this.n_1700_B = id;
            this.J_1907_R = fromMc == null ? "" : fromMc;
            this.R_4764_Y = targetMc == null ? "" : targetMc;
            this.G_564_y = px;
            this.P_1922_E = py;
            this.u_1723_Y = pz;
            this.v_4262_N = exp;
        }
    }
}



