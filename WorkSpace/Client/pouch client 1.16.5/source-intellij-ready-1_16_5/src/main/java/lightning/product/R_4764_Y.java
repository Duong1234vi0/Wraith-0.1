/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import lightning.product.G_564_y;
import lightning.product.ClientIntentionPacket;
import lightning.product.J_1907_R;
import lightning.product.Q_4569_t;
import lightning.product.U_2871_b;
import lightning.product.Bots;
import lightning.product.MinecraftClient;
import lightning.product.ServerboundHelloPacket;
import lightning.product.d_4952_K;
import lightning.product.n_1700_B;
import lightning.product.ClientBootstrap;
import lightning.product.t_148_a;
import lightning.product.t_1786_h;
import lightning.product.u_1723_Y;
import lightning.product.u_2550_I;

public class R_4764_Y {
    private static final SecureRandom n_1700_B = new SecureRandom();
    private static final ScheduledExecutorService J_1907_R = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "BotStarter-StartQueue");
        t.setDaemon(true);
        return t;
    });
    private static final ExecutorService R_4764_Y = Executors.newCachedThreadPool(new ThreadFactory(){
        private int n_1700_B = 0;

        @Override
        public synchronized Thread newThread(Runnable r) {
            Thread t = new Thread(r, "BotStarter-Connect-" + ++this.n_1700_B);
            t.setDaemon(true);
            return t;
        }
    });
    private static final ScheduledExecutorService G_564_y = Executors.newScheduledThreadPool(2, r -> {
        Thread t = new Thread(r, "BotStarter-LoginPacketDelay");
        t.setDaemon(true);
        return t;
    });
    private static final AtomicBoolean P_1922_E = new AtomicBoolean(false);
    private static final AtomicBoolean u_1723_Y = new AtomicBoolean(false);
    private static final AtomicLong v_4262_N = new AtomicLong(0L);
    private static final int w_1484_f = 8;

    private static String n_1700_B(int len) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(n_1700_B.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length())));
        }
        return sb.toString();
    }

    private static Bots u_1723_Y() {
        try {
            return (Bots)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Bots.class);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static int v_4262_N() {
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            int v = ((Float)m.s_956_w.J_1907_R()).intValue();
            if (v < 3) {
                v = 3;
            }
            if (v > 20) {
                v = 20;
            }
            return v;
        }
        return 8;
    }

    private static String w_1484_f() {
        String p;
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        String string = p = m != null ? (String)m.t_148_a.J_1907_R() : null;
        if (p == null || p.trim().isEmpty()) {
            p = "Rickstone";
        }
        return p;
    }

    private static void t_148_a() {
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null && m.M_588_G != null) {
            lightning.product.J_1907_R.n_1700_B((String)m.M_588_G.J_1907_R());
        }
    }

    public static void n_1700_B(String username, String ip) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, 25565, true, null);
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":25565");
        }
    }

    public static void n_1700_B(String username, String ip, int port) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, port, false, null);
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":" + port);
        }
    }

    public static void n_1700_B(String username, String ip, u_1723_Y behavior) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, 25565, true, behavior);
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":25565");
        }
    }

    public static void n_1700_B(String username, String ip, int port, u_1723_Y behavior) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, port, false, behavior);
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":" + port);
        }
    }

    public static void n_1700_B(String username, String ip, String targetName) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, 25565, true, new t_148_a(targetName));
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":25565");
        }
    }

    public static void J_1907_R(String username, String ip, String targetName) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, 25565, true, new G_564_y(targetName));
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":25565");
        }
    }

    public static void J_1907_R(String username, String ip) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.n_1700_B(username, ip, 25565, true, new u_2550_I());
        Bots m = lightning.product.R_4764_Y.u_1723_Y();
        if (m != null) {
            m.n_1700_B(username, ip + ":25565");
        }
    }

    public static void n_1700_B(int count, String host, int port) {
        lightning.product.R_4764_Y.t_148_a();
        lightning.product.R_4764_Y.J_1907_R(count, host, port);
    }

    public static void n_1700_B(int count, String server) {
        lightning.product.R_4764_Y.t_148_a();
        String host = server;
        int port = 25565;
        int idx = server.lastIndexOf(58);
        if (idx > 0 && idx < server.length() - 1) {
            host = server.substring(0, idx);
            try {
                port = Integer.parseInt(server.substring(idx + 1));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        lightning.product.R_4764_Y.J_1907_R(count, host, port);
    }

    public static void J_1907_R(int count, String host, int port) {
        lightning.product.R_4764_Y.t_148_a();
        if (count <= 0) {
            return;
        }
        u_1723_Y.set(false);
        long generation = v_4262_N.get();
        ConcurrentHashMap.KeySetView used = ConcurrentHashMap.newKeySet();
        for (n_1700_B b : lightning.product.J_1907_R.n_1700_B) {
            if (b == null || b.R_4764_Y == null) continue;
            used.add(b.R_4764_Y.O_1309_Q().getString());
        }
        int step = lightning.product.R_4764_Y.v_4262_N();
        String prefix = lightning.product.R_4764_Y.w_1484_f();
        int i = 0;
        while (i < count) {
            int idx2 = i++;
            J_1907_R.schedule(() -> {
                if (!lightning.product.R_4764_Y.n_1700_B(generation)) {
                    return;
                }
                String name = lightning.product.R_4764_Y.n_1700_B(prefix, used, idx2);
                used.add(name);
                lightning.product.R_4764_Y.n_1700_B(name, host, port, true, null);
                Bots m = lightning.product.R_4764_Y.u_1723_Y();
                if (m != null) {
                    m.n_1700_B(name, host + ":" + port);
                }
            }, (long)idx2 * (long)step, TimeUnit.SECONDS);
        }
    }

    public static void n_1700_B() {
        u_1723_Y.set(true);
        P_1922_E.set(false);
        v_4262_N.incrementAndGet();
    }

    public static boolean J_1907_R() {
        return P_1922_E.compareAndSet(false, true);
    }

    public static boolean R_4764_Y() {
        return P_1922_E.compareAndSet(true, false);
    }

    public static boolean G_564_y() {
        return P_1922_E.get();
    }

    private static boolean n_1700_B(long generation) {
        while (P_1922_E.get()) {
            if (u_1723_Y.get() || v_4262_N.get() != generation) {
                return false;
            }
            try {
                Thread.sleep(150L);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return !u_1723_Y.get() && v_4262_N.get() == generation;
    }

    private static String n_1700_B(String prefix, Set<String> used, int index) {
        String alpha;
        String candidate;
        int remaining;
        String digits = String.valueOf(index % 1000);
        String cleanPrefix = prefix.replaceAll("[^A-Za-z0-9_]", "");
        if (cleanPrefix.isEmpty()) {
            cleanPrefix = "Bot";
        }
        if ((remaining = 16 - cleanPrefix.length()) <= digits.length()) {
            int targetPrefixLen = Math.max(1, 16 - (digits.length() + 1));
            if (cleanPrefix.length() > targetPrefixLen) {
                cleanPrefix = cleanPrefix.substring(0, targetPrefixLen);
            }
            remaining = 16 - cleanPrefix.length();
        }
        int alphaLen = Math.max(1, remaining - digits.length());
        int attempts = 0;
        while (used.contains(candidate = cleanPrefix + (alpha = lightning.product.R_4764_Y.n_1700_B(alphaLen)) + digits) && ++attempts < 25) {
        }
        return candidate;
    }

    private static void n_1700_B(String username, String ip, int port, boolean nativeTransport, u_1723_Y behavior) {
        lightning.product.R_4764_Y.t_148_a();
        if (username == null || ((String)username).trim().isEmpty()) {
            username = "Bot" + System.currentTimeMillis();
        }
        if (((String)username).length() > 1 && "SQR".indexOf(((String)username).charAt(0)) >= 0 && ((String)username).length() > 2 && Character.isUpperCase(((String)username).charAt(1))) {
            username = "Bot" + (String)username;
        }
        Object finalUsername = username;
        u_1723_Y finalBehavior = behavior;
        R_4764_Y.execute(() -> R_4764_Y.J_1907_R((String)finalUsername, ip, port, nativeTransport, finalBehavior));
    }

    public static void P_1922_E() {
        for (int i = lightning.product.J_1907_R.n_1700_B.size() - 1; i >= 0; --i) {
            n_1700_B bot = lightning.product.J_1907_R.n_1700_B.get(i);
            if (bot != null && bot.n_1700_B != null) {
                bot.n_1700_B.n_1700_B(new U_2871_b("Bot disconnected by user"));
            }
            lightning.product.J_1907_R.n_1700_B.remove(i);
        }
    }

    public static void n_1700_B(String name) {
        for (int i = lightning.product.J_1907_R.n_1700_B.size() - 1; i >= 0; --i) {
            n_1700_B bot = lightning.product.J_1907_R.n_1700_B.get(i);
            if (bot == null || bot.R_4764_Y == null || !bot.R_4764_Y.O_1309_Q().getString().equals(name)) continue;
            if (bot.n_1700_B != null) {
                bot.n_1700_B.n_1700_B(new U_2871_b("Bot disconnected by user"));
            }
            lightning.product.J_1907_R.n_1700_B.remove(i);
        }
    }

    public static void n_1700_B(String botName, u_1723_Y behavior) {
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (bot == null || bot.R_4764_Y == null || !bot.R_4764_Y.O_1309_Q().getString().equals(botName)) continue;
            bot.n_1700_B(behavior);
            break;
        }
    }

    public static void R_4764_Y(String botName, String targetName) {
        lightning.product.R_4764_Y.n_1700_B(botName, new t_148_a(targetName));
    }

    public static void G_564_y(String botName, String targetName) {
        lightning.product.R_4764_Y.n_1700_B(botName, new G_564_y(targetName));
    }

    public static void J_1907_R(String botName) {
        lightning.product.R_4764_Y.n_1700_B(botName, new u_2550_I());
    }

    private static /* synthetic */ void J_1907_R(String finalUsername, String ip, int port, boolean nativeTransport, u_1723_Y finalBehavior) {
        try {
            UUID uuid = UUID.randomUUID();
            GameProfile gameProfile = new GameProfile(uuid, finalUsername);
            if (gameProfile.getId() == null || gameProfile.getName() == null || gameProfile.getName().isEmpty()) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430: \u041d\u0435\u0432\u0430\u043b\u0438\u0434\u043d\u044b\u0439 GameProfile \u0434\u043b\u044f " + finalUsername);
                return;
            }
            t_1786_h botNetwork = t_1786_h.n_1700_B(InetAddress.getByName(ip), port, nativeTransport);
            botNetwork.n_1700_B(gameProfile);
            if (finalBehavior != null) {
                lightning.product.J_1907_R.G_564_y.put(finalUsername, finalBehavior);
            }
            botNetwork.n_1700_B(new Q_4569_t(botNetwork, MinecraftClient.A_4115_X(), null, status -> {}));
            botNetwork.n_1700_B(new ClientIntentionPacket(ip, port, d_4952_K.G_564_y));
            G_564_y.schedule(() -> botNetwork.n_1700_B(new ServerboundHelloPacket(gameProfile)), 500L, TimeUnit.MILLISECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



