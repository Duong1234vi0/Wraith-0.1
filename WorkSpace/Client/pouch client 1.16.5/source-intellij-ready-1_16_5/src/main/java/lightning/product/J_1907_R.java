/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.u_1723_Y;
import lightning.product.u_2550_I;
import lightning.product.w_1457_N;
import lombok.Generated;

public class J_1907_R {
    public static List<lightning.product.n_1700_B> n_1700_B = new CopyOnWriteArrayList<lightning.product.n_1700_B>();
    public static List<lightning.product.n_1700_B> J_1907_R = new CopyOnWriteArrayList<lightning.product.n_1700_B>();
    public static List<w_1457_N> R_4764_Y = new CopyOnWriteArrayList<w_1457_N>();
    private static boolean u_1723_Y = false;
    private static final long v_4262_N = 180000L;
    private static long w_1484_f = 0L;
    private static boolean t_148_a = true;
    private static volatile String s_956_w = "Rick123";
    public static Map<String, u_1723_Y> G_564_y = new ConcurrentHashMap<String, u_1723_Y>();
    private static final Map<String, n_1700_B> u_2550_I = new ConcurrentHashMap<String, n_1700_B>();
    public static final long P_1922_E = 5000L;
    private static final Pattern M_588_G = Pattern.compile("(?:(?:\u0431\u0430\u043b\u0430\u043d\u0441)|balance)\\s*[:=]?\\s*\\$?\\s*([\\d\\s.,]+)", 66);
    private static final Map<String, Long> P_4830_p = new ConcurrentHashMap<String, Long>();
    private static final Map<String, lightning.product.n_1700_B> h_1847_R = new ConcurrentHashMap<String, lightning.product.n_1700_B>();
    private static final ScheduledExecutorService Q_4569_t = Executors.newScheduledThreadPool(2, new ThreadFactory(){

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "BotManager-Async");
            t.setDaemon(true);
            return t;
        }
    });
    private static boolean M_182_A = false;
    private static String t_1786_h = "/spanw";
    private static long multiplayerClientSuggestionProvider = 180000L;

    public static void n_1700_B(String password) {
        if (password != null && !password.isEmpty()) {
            s_956_w = password;
        }
    }

    public static void n_1700_B(boolean enabled) {
        u_1723_Y = enabled;
        if (enabled) {
            w_1484_f = System.currentTimeMillis();
            t_148_a = true;
        }
    }

    public static int n_1700_B() {
        return (int)multiplayerClientSuggestionProvider;
    }

    public static void J_1907_R() {
        if (!M_182_A) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        for (lightning.product.n_1700_B bot : n_1700_B) {
            String botName;
            String normalizedBotName;
            Long lastTime;
            if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null || (lastTime = P_4830_p.get(normalizedBotName = lightning.product.J_1907_R.P_1922_E(botName = bot.P_1922_E.Q_2552_b.t_4043_B()))) != null && currentTime - lastTime < multiplayerClientSuggestionProvider) continue;
            bot.P_1922_E.Q_2552_b.n_1700_B(t_1786_h);
            P_4830_p.put(normalizedBotName, currentTime);
        }
    }

    public static boolean J_1907_R(String botName) {
        return u_2550_I.containsKey(lightning.product.J_1907_R.P_1922_E(botName));
    }

    public static void R_4764_Y() {
        if (!u_1723_Y) {
            return;
        }
        if (n_1700_B.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - w_1484_f < 180000L) {
            return;
        }
        w_1484_f = now;
        for (lightning.product.n_1700_B bot : n_1700_B) {
            if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) continue;
            try {
                bot.P_1922_E.Q_2552_b.L_4248_u = t_148_a ? 1.0f : -1.0f;
                Q_4569_t.schedule(() -> {
                    try {
                        bot.P_1922_E.Q_2552_b.L_4248_u = 0.0f;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }, 500L, TimeUnit.MILLISECONDS);
            }
            catch (Exception e) {
                System.out.println("[AntiAFK-Walk] \u041e\u0448\u0438\u0431\u043a\u0430 \u0434\u043b\u044f \u0431\u043e\u0442\u0430: " + e.getMessage());
            }
        }
        t_148_a = !t_148_a;
    }

    public static void n_1700_B(String botName, String targetPlayer) {
        String key = lightning.product.J_1907_R.P_1922_E(botName);
        if (key == null || key.isEmpty()) {
            return;
        }
        u_2550_I.put(key, new n_1700_B(botName, targetPlayer));
    }

    public static boolean n_1700_B(lightning.product.n_1700_B bot, String message) {
        if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null || message == null) {
            return false;
        }
        String botName = bot.P_1922_E.Q_2552_b.t_4043_B();
        n_1700_B request = u_2550_I.get(lightning.product.J_1907_R.P_1922_E(botName));
        if (request == null) {
            return false;
        }
        Long balance = lightning.product.J_1907_R.u_1723_Y(message);
        if (balance == null) {
            return false;
        }
        u_2550_I.remove(lightning.product.J_1907_R.P_1922_E(botName));
        if (balance <= 0L) {
            return true;
        }
        if (request.J_1907_R == null || request.J_1907_R.isEmpty()) {
            return true;
        }
        if (request.J_1907_R.equalsIgnoreCase(botName)) {
            return true;
        }
        String targetPlayer = request.J_1907_R;
        long amount = balance;
        Q_4569_t.schedule(() -> {
            try {
                if (bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null) {
                    bot.P_1922_E.Q_2552_b.n_1700_B("/pay " + targetPlayer + " " + amount);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }, 5000L, TimeUnit.MILLISECONDS);
        return true;
    }

    private static String P_1922_E(String botName) {
        return botName == null ? null : botName.trim().toLowerCase(Locale.ROOT);
    }

    private static Long u_1723_Y(String message) {
        Matcher matcher = M_588_G.matcher(message);
        if (!matcher.find()) {
            return null;
        }
        String digits = matcher.group(1);
        if (digits == null) {
            return null;
        }
        if ((digits = digits.replaceAll("[^0-9]", "")).isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(digits);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public static void G_564_y() {
        for (lightning.product.n_1700_B bot : n_1700_B) {
            if (bot == null) continue;
            bot.n_1700_B();
        }
        lightning.product.J_1907_R.J_1907_R();
    }

    public static void n_1700_B(lightning.product.n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null) {
            return;
        }
        String botName = bot.R_4764_Y.O_1309_Q().getString();
        u_1723_Y pendingBehavior = G_564_y.remove(botName);
        if (pendingBehavior != null) {
            bot.n_1700_B(pendingBehavior);
        } else {
            bot.n_1700_B(new u_2550_I());
        }
    }

    public static void J_1907_R(lightning.product.n_1700_B bot) {
        if (bot == null) {
            return;
        }
        n_1700_B.add(bot);
        String botName = lightning.product.J_1907_R.G_564_y(bot);
        if (botName != null && !botName.isEmpty()) {
            h_1847_R.put(lightning.product.J_1907_R.P_1922_E(botName), bot);
        }
        lightning.product.J_1907_R.n_1700_B(bot);
    }

    public static void R_4764_Y(lightning.product.n_1700_B bot) {
        if (bot == null) {
            return;
        }
        n_1700_B.remove(bot);
        J_1907_R.remove(bot);
        String botName = lightning.product.J_1907_R.G_564_y(bot);
        if (botName != null && !botName.isEmpty()) {
            String key = lightning.product.J_1907_R.P_1922_E(botName);
            h_1847_R.remove(key);
            P_4830_p.remove(key);
            u_2550_I.remove(key);
        }
        for (int i = R_4764_Y.size() - 1; i >= 0; --i) {
            w_1457_N tapeMouse = R_4764_Y.get(i);
            if (tapeMouse.n_1700_B() != bot) continue;
            R_4764_Y.remove(i);
        }
    }

    public static lightning.product.n_1700_B R_4764_Y(String name) {
        String normalized = lightning.product.J_1907_R.P_1922_E(name);
        if (normalized == null || normalized.isEmpty()) {
            return null;
        }
        lightning.product.n_1700_B cached = h_1847_R.get(normalized);
        if (cached != null) {
            return cached;
        }
        for (lightning.product.n_1700_B bot : n_1700_B) {
            String botName = lightning.product.J_1907_R.G_564_y(bot);
            if (botName == null || !lightning.product.J_1907_R.P_1922_E(botName).equals(normalized)) continue;
            h_1847_R.put(normalized, bot);
            return bot;
        }
        return null;
    }

    private static String G_564_y(lightning.product.n_1700_B bot) {
        if (bot == null) {
            return null;
        }
        if (bot.R_4764_Y != null && bot.R_4764_Y.O_1309_Q() != null) {
            return bot.R_4764_Y.O_1309_Q().getString();
        }
        if (bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null) {
            return bot.P_1922_E.Q_2552_b.t_4043_B();
        }
        return null;
    }

    @Generated
    public static boolean P_1922_E() {
        return u_1723_Y;
    }

    @Generated
    public static String u_1723_Y() {
        return s_956_w;
    }

    @Generated
    public static boolean v_4262_N() {
        return M_182_A;
    }

    @Generated
    public static void J_1907_R(boolean antiAFKEnabled) {
        M_182_A = antiAFKEnabled;
    }

    @Generated
    public static void G_564_y(String antiAFKCommand) {
        t_1786_h = antiAFKCommand;
    }

    @Generated
    public static String w_1484_f() {
        return t_1786_h;
    }

    @Generated
    public static void n_1700_B(long antiAFKInterval) {
        multiplayerClientSuggestionProvider = antiAFKInterval;
    }

    private static class n_1700_B {
        private final String n_1700_B;
        private final String J_1907_R;

        private n_1700_B(String botName, String targetPlayer) {
            this.n_1700_B = botName;
            this.J_1907_R = targetPlayer;
        }
    }
}


