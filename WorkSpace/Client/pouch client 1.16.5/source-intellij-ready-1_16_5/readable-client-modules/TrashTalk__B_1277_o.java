/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.reflect.TypeToken
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import lightning.product.C_332_W;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.r_4811_B;
import lightning.product.y_2603_k;

public class B_1277_o
extends X_3546_T {
    public q_366_O v_4262_N = new q_366_O("\u041f\u0440\u0435\u0444\u0438\u043a\u0441", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u0412\u043e\u0441\u043a\u043b\u0438\u0446\u0430\u0442\u0435\u043b\u044c\u043d\u044b\u0439");
    public p_1977_n w_1484_f = new p_1977_n("\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0442\u044c \u043d\u0438\u043a", true);
    public p_1977_n t_148_a = new p_1977_n("\u0414\u0435\u0444\u043e\u043b\u0442\u043d\u044b\u0435 \u0444\u0440\u0430\u0437\u044b", true);
    private static final Random s_956_w = new Random();
    private static volatile B_1277_o u_2550_I;
    private static final List<String> M_588_G;
    private static final List<String> P_4830_p;
    private final List<String> h_1847_R = new ArrayList<String>();
    private final List<String> Q_4569_t = new ArrayList<String>();
    private r_4811_B M_182_A;
    private boolean t_1786_h;
    private boolean N_4405_n = true;

    public B_1277_o() {
        super("TrashTalk", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
        u_2550_I = this;
        this.t_1786_h();
    }

    public static B_1277_o h_1847_R() {
        return u_2550_I;
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.M_182_A = null;
        this.t_1786_h = false;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        r_4811_B target;
        if (B_1277_o.c_3005_b.Y_259_p == null || B_1277_o.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.N_4405_n && !B_1277_o.c_3005_b.Y_259_p.H_3699_F() && B_1277_o.c_3005_b.Y_259_p.g_46_E() <= 0.0f) {
            String message = this.Q_4569_t() + this.G_564_y(lightning.product.B_1277_o$n_1700_B.J_1907_R);
            if (message != null && !message.isEmpty()) {
                B_1277_o.c_3005_b.Y_259_p.n_1700_B(message);
            }
            this.N_4405_n = false;
        } else if (B_1277_o.c_3005_b.Y_259_p.H_3699_F() && B_1277_o.c_3005_b.Y_259_p.g_46_E() > 0.0f) {
            this.N_4405_n = true;
        }
        r_3979_X attackAura = (r_3979_X)o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class);
        r_4811_B r_4811_B2 = target = attackAura != null ? attackAura.h_1847_R() : null;
        if (target != null) {
            String phrase;
            boolean targetIsDead;
            boolean targetIsAlive = target.H_3699_F() && target.g_46_E() > 0.0f;
            boolean bl = targetIsDead = target.g_46_E() <= 0.0f || target.O_2151_c > 0;
            if (this.t_1786_h && targetIsDead && (phrase = this.G_564_y(lightning.product.B_1277_o$n_1700_B.n_1700_B)) != null && !phrase.isEmpty()) {
                String nickname = this.w_1484_f.t_148_a() != false ? target.O_1309_Q().getString() + ", " : "";
                B_1277_o.c_3005_b.Y_259_p.n_1700_B(this.Q_4569_t() + nickname + phrase);
            }
            this.t_1786_h = targetIsAlive;
            this.M_182_A = target;
        } else if (this.M_182_A != null) {
            this.M_182_A = null;
            this.t_1786_h = false;
        }
    }

    private String G_564_y(n_1700_B kind) {
        int cN;
        List<String> defaults = kind == lightning.product.B_1277_o$n_1700_B.n_1700_B ? M_588_G : P_4830_p;
        List<String> custom = kind == lightning.product.B_1277_o$n_1700_B.n_1700_B ? this.h_1847_R : this.Q_4569_t;
        boolean wantDefaults = this.t_148_a.t_148_a();
        int dN = wantDefaults ? defaults.size() : 0;
        int total = dN + (cN = custom.size());
        if (total == 0) {
            return null;
        }
        int idx = s_956_w.nextInt(total);
        return idx < dN ? defaults.get(idx) : custom.get(idx - dN);
    }

    private String Q_4569_t() {
        return this.v_4262_N.J_1907_R("\u0412\u043e\u0441\u043a\u043b\u0438\u0446\u0430\u0442\u0435\u043b\u044c\u043d\u044b\u0439") ? "! " : "";
    }

    public synchronized boolean n_1700_B(n_1700_B kind, String phrase) {
        if (kind == null || phrase == null) {
            return false;
        }
        String trimmed = phrase.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        List<String> bucket = this.P_1922_E(kind);
        bucket.add(trimmed);
        this.N_4405_n();
        return true;
    }

    public synchronized String n_1700_B(n_1700_B kind, int oneBasedIndex) {
        if (kind == null) {
            return null;
        }
        List<String> bucket = this.P_1922_E(kind);
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= bucket.size()) {
            return null;
        }
        String removed = bucket.remove(i);
        this.N_4405_n();
        return removed;
    }

    public synchronized int n_1700_B(n_1700_B kind) {
        if (kind == null) {
            return 0;
        }
        List<String> bucket = this.P_1922_E(kind);
        int n = bucket.size();
        bucket.clear();
        this.N_4405_n();
        return n;
    }

    public synchronized List<String> J_1907_R(n_1700_B kind) {
        if (kind == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<String>(this.P_1922_E(kind)));
    }

    public int R_4764_Y(n_1700_B kind) {
        return (kind == lightning.product.B_1277_o$n_1700_B.n_1700_B ? M_588_G : P_4830_p).size();
    }

    private List<String> P_1922_E(n_1700_B kind) {
        return kind == lightning.product.B_1277_o$n_1700_B.n_1700_B ? this.h_1847_R : this.Q_4569_t;
    }

    private static File M_182_A() {
        return new File(C_332_W.n_1700_B, "trashtalk.json");
    }

    private synchronized void t_1786_h() {
        File file = B_1277_o.M_182_A();
        if (!file.isFile()) {
            return;
        }
        try (FileReader reader = new FileReader(file);){
            JsonObject root = new JsonParser().parse((Reader)reader).getAsJsonObject();
            Type stringList = new TypeToken<List<String>>(this){}.getType();
            Gson gson = new Gson();
            this.h_1847_R.clear();
            this.Q_4569_t.clear();
            if (root.has("kill")) {
                this.h_1847_R.addAll((Collection)gson.fromJson(root.get("kill"), stringList));
            }
            if (root.has("death")) {
                this.Q_4569_t.addAll((Collection)gson.fromJson(root.get("death"), stringList));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private synchronized void N_4405_n() {
        File file = B_1277_o.M_182_A();
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        JsonObject root = new JsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        root.add("kill", gson.toJsonTree(this.h_1847_R));
        root.add("death", gson.toJsonTree(this.Q_4569_t));
        try (FileWriter writer = new FileWriter(file);){
            gson.toJson((JsonElement)root, (Appendable)writer);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    static {
        M_588_G = List.of("\u0441\u044f\u0434\u044c, \u0442\u0432\u043e\u0439 \u0441\u043a\u0438\u043b\u043b \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u0441\u044f \u0435\u0449\u0451 \u0432 \u0442\u0443\u0442\u043e\u0440\u0438\u0430\u043b\u0435", "\u0442\u044b \u0442\u0430\u043a \u0441\u0442\u0430\u0440\u0430\u0435\u0448\u044c\u0441\u044f, \u0447\u0442\u043e \u043f\u043e\u0447\u0442\u0438 \u043f\u043e\u043f\u0430\u043b \u043f\u043e \u043c\u043d\u0435", "\u0432\u044b\u043f\u0435\u0439 \u0432\u043e\u0434\u0438\u0447\u043a\u0438 \u0438 \u043f\u043e\u0434\u0443\u043c\u0430\u0439, \u0433\u0434\u0435 \u0442\u044b \u043e\u0448\u0438\u0431\u0441\u044f", "\u0437\u0430 \u0447\u0442\u043e \u0442\u044b \u0442\u0430\u043a \u0441\u043e \u0441\u0432\u043e\u0435\u0439 \u043a\u043b\u0430\u0432\u0438\u0430\u0442\u0443\u0440\u043e\u0439, \u043e\u043d\u0430 \u0436\u0435 \u043d\u0435 \u0432\u0438\u043d\u043e\u0432\u0430\u0442\u0430", "\u0443\u0432\u0430\u0436\u0435\u043d\u0438\u0435 \u0442\u0435\u043c, \u043a\u0442\u043e \u0441\u043e\u0433\u043b\u0430\u0441\u0438\u043b\u0441\u044f \u0438\u0433\u0440\u0430\u0442\u044c \u0441 \u0442\u043e\u0431\u043e\u0439 \u0432 \u043e\u0434\u043d\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u0435", "\u0442\u044b \u043d\u0435 \u043f\u0440\u043e\u0438\u0433\u0440\u0430\u043b, \u0442\u044b \u043f\u0440\u043e\u0441\u0442\u043e \u0431\u0435\u0441\u043f\u043b\u0430\u0442\u043d\u043e \u043c\u0435\u043d\u044f \u043f\u043e\u0442\u0440\u0435\u043d\u0438\u0440\u043e\u0432\u0430\u043b", "\u0435\u0441\u043b\u0438 \u0431\u044b \u0442\u0438\u043b\u044c\u0442 \u0434\u0430\u0432\u0430\u043b \u0430\u0447\u0438\u0432\u043a\u0438 \u2014 \u0443 \u0442\u0435\u0431\u044f \u0431\u044b\u043b \u0431\u044b 100% \u043f\u0440\u043e\u0433\u0440\u0435\u0441\u0441", "\u044d\u0442\u043e \u043d\u0435 \u043b\u0430\u0433\u0438, \u044d\u0442\u043e \u0442\u0432\u043e\u0438 \u0440\u0443\u043a\u0438 \u043d\u0430 0.5x \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438", "\u0441\u0438\u0434\u0438\u0448\u044c, \u0434\u044b\u0448\u0438\u0448\u044c, \u043f\u0440\u043e\u0438\u0433\u0440\u044b\u0432\u0430\u0435\u0448\u044c \u2014 \u043d\u043e\u0440\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0434\u0435\u043d\u044c, \u043f\u0440\u043e\u0434\u043e\u043b\u0436\u0430\u0439", "\u0441\u0442\u0430\u0432\u044c \u0430\u0447\u0438\u0432\u043a\u0443 \u00ab\u0443\u043c\u0435\u0440 \u043a\u0440\u0430\u0441\u0438\u0432\u043e\u00bb, \u0442\u044b \u0435\u0451 \u0447\u0435\u0441\u0442\u043d\u043e \u0437\u0430\u0441\u043b\u0443\u0436\u0438\u043b", "\u043b\u0443\u0447\u0448\u0435\u0435, \u0447\u0442\u043e \u0442\u044b \u0441\u0434\u0435\u043b\u0430\u043b \u0432 \u044d\u0442\u043e\u043c \u0440\u0430\u0443\u043d\u0434\u0435 \u2014 \u0440\u0435\u0441\u043f\u0430\u0432\u043d", "\u0440\u0435\u0441\u043f \u0447\u0435\u0440\u0435\u0437 5, \u0441\u043a\u0438\u043b\u043b \u0447\u0435\u0440\u0435\u0437 \u043d\u0438\u043a\u043e\u0433\u0434\u0430", "\u0442\u044b \u043a\u0430\u043a \u0430\u043d\u0442\u0438-\u0442\u0443\u0442\u043e\u0440\u0438\u0430\u043b: \u043f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0435\u0448\u044c, \u043a\u0430\u043a \u041d\u0415 \u043d\u0430\u0434\u043e", "\u0441\u043d\u0438\u043c\u0430\u0439 \u0445\u0430\u0439\u043b\u0430\u0439\u0442, \u0442\u0430\u043a\u043e\u0439 \u043a\u0430\u0434\u0440 \u0440\u0430\u0437 \u0432 \u043a\u0430\u0440\u044c\u0435\u0440\u0443", "\u043d\u0435 \u0440\u0430\u0441\u0441\u0442\u0440\u0430\u0438\u0432\u0430\u0439\u0441\u044f, \u0443 \u043c\u0435\u043d\u044f \u043f\u0440\u043e\u0441\u0442\u043e \u0447\u0438\u0442, \u043a\u043e\u0442\u043e\u0440\u044b\u0439 \u043d\u0430\u0437\u044b\u0432\u0430\u0435\u0442\u0441\u044f \u00ab\u0440\u0435\u0430\u043a\u0446\u0438\u044f\u00bb", "\u043f\u0440\u043e\u0438\u0433\u0440\u0430\u043b \u043d\u0443\u0431\u0443, \u0434\u0435\u043b\u0430\u0435\u0442 \u0434\u0435\u043d\u044c", "\u0442\u044b \u043d\u0435 \u043d\u0430\u0433\u043d\u0443\u043b \u043c\u0435\u043d\u044f, \u0442\u044b \u0441\u043e\u0433\u043d\u0443\u043b \u0442\u043e\u043b\u044c\u043a\u043e \u0441\u0432\u043e\u044e \u043a\u043b\u0430\u0432\u0438\u0430\u0442\u0443\u0440\u0443", "\u0438\u0437\u0432\u0438\u043d\u0438, \u043d\u0435 \u0443\u0441\u043b\u044b\u0448\u0430\u043b, \u044f \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0437\u0430\u043d\u044f\u0442 \u0442\u0432\u043e\u0438\u043c \u043b\u0443\u0442\u043e\u043c", "\u043f\u0435\u0440\u0435\u0443\u0441\u0442\u0430\u043d\u043e\u0432\u0438 \u0438\u0433\u0440\u0443, \u043c\u043e\u0436\u0435\u0442 \u0432 \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0435\u0439 \u0432\u0435\u0440\u0441\u0438\u0438 \u043f\u043e\u0432\u0435\u0437\u0451\u0442", "\u0443 \u0442\u0435\u0431\u044f ping \u043d\u043e\u0440\u043c, \u0430 \u0432\u043e\u0442 brain timeout 504", "\u044f \u0431\u044b \u0441\u043a\u0430\u0437\u0430\u043b gg, \u043d\u043e \u0438\u0433\u0440\u044b \u043d\u0435 \u0431\u044b\u043b\u043e", "\u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0438\u0439 \u0440\u0430\u0443\u043d\u0434 \u043f\u043e\u043f\u0440\u043e\u0441\u0438 \u0443 \u043c\u0435\u043d\u044f \u0444\u043e\u0440\u044b \u2014 \u043c\u043e\u0436\u0435\u0442, \u0434\u043e\u0439\u0434\u0451\u0442 \u0434\u043e \u043f\u044f\u0442\u043e\u0433\u043e \u0445\u0438\u0442\u0430", "\u0442\u044b \u0434\u0430\u043b \u043c\u043d\u0435 +1 \u043a \u041a\u0414 \u0438 -10 \u043a \u043d\u0430\u0441\u0442\u0440\u043e\u0435\u043d\u0438\u044e \u0441\u0435\u0431\u0435", "\u043d\u0435 \u0443\u0447\u0438\u0441\u044c \u0438\u0433\u0440\u0430\u0442\u044c, \u0443 \u0442\u0435\u0431\u044f \u043f\u043e\u043b\u0443\u0447\u0430\u0435\u0442\u0441\u044f \u0440\u0430\u0437\u0432\u043b\u0435\u043a\u0430\u0442\u044c", "\u043f\u043e\u0441\u043b\u0435 \u0442\u0430\u043a\u043e\u0439 \u0438\u0433\u0440\u044b \u0440\u0435\u0430\u043b\u044c\u043d\u043e \u0445\u043e\u0447\u0435\u0442\u0441\u044f \u043e\u0431\u043d\u044f\u0442\u044c. \u043d\u043e \u044f \u043d\u0435 \u0431\u0443\u0434\u0443", "\u0438\u0434\u0438 \u043e\u0442\u0434\u043e\u0445\u043d\u0438, \u0430 \u043b\u0443\u0447\u0448\u0435 \u2014 \u043d\u0430\u0441\u043e\u0432\u0441\u0435\u043c", "\u043a\u043e\u043c\u0431\u043e: \u0442\u044b + \u043a\u043b\u0430\u0432\u0438\u0430\u0442\u0443\u0440\u0430 + \u0445\u0443\u0451\u0432\u044b\u0439 \u0434\u0435\u043d\u044c = \u043c\u043e\u0439 \u043a\u0438\u043b\u043b", "\u0442\u044b \u043d\u0435 \u043d\u0443\u0431, \u0442\u044b \u043f\u0440\u0438\u043a\u043b\u0430\u0434\u043d\u043e\u0439 \u043a\u0443\u0440\u0441 \u043f\u043e \u0442\u043e\u043c\u0443, \u043a\u0430\u043a \u0441\u043b\u0438\u0432\u0430\u0442\u044c");
        P_4830_p = List.of("lag", "desync", "my bad", "unlucky", "that was close", "almost had you", "nice", "gg", "good fight", "well played", "close one", "unfortunate", "thats rough", "bad timing", "nice play", "well fought", "good game", "that was fun", "respect", "wp", "close fight", "unlucky death", "nice kill", "gg wp", "\u043f\u0430\u043a\u0435\u0442\u044b \u0443\u043b\u0435\u0442\u0435\u043b\u0438 \u0432 \u043d\u0438\u0436\u043d\u0438\u0439 \u043c\u0438\u0440", "\u0442\u0438\u043b\u044c\u0442\u0430 \u043d\u0435\u0442, \u0435\u0441\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043e\u043f\u044b\u0442", "\u043b\u0430\u0434\u043d\u043e, \u0437\u0430\u0441\u043b\u0443\u0436\u0438\u043b", "\u043a\u0440\u0430\u0441\u0438\u0432\u044b\u0439 \u0442\u0430\u0439\u043c\u0438\u043d\u0433", "\u043d\u0443 \u043e\u043a, \u0431\u044b\u0432\u0430\u0435\u0442", "\u043f\u043e\u0439\u043c\u0430\u043b, \u043d\u0435 \u0443\u0431\u0435\u0433\u0443", "\u043b\u043e\u0432\u043a\u043e, \u043f\u0440\u0438\u0437\u043d\u0430\u044e", "\u0442\u0443\u0442 \u0440\u0435\u0430\u043b\u044c\u043d\u043e \u043c\u043e\u0439 \u0444\u0435\u0439\u043b", "\u043b\u0430\u0434\u043d\u043e, \u043f\u043b\u044e\u0441 \u043a \u043a\u0430\u0440\u043c\u0435 \u0442\u0435\u0431\u0435", "\u044d\u0442\u043e \u0431\u044b\u043b \u043d\u0435 \u0441\u0430\u043c\u044b\u0439 \u0443\u043c\u043d\u044b\u0439 \u043c\u043e\u0439 \u0445\u043e\u0434", "\u043d\u0443 \u043d\u043e\u0440\u043c, \u0447\u0438\u0441\u0442\u043e", "\u0432\u0430\u0448 \u0445\u043e\u0434 \u0447\u0438\u0449\u0435 \u0431\u044b\u043b", "\u043b\u0430\u0434\u043d\u043e, \u0442\u0432\u043e\u044f \u043c\u0438\u043d\u0443\u0442\u0430", "\u0437\u0430\u0431\u0438\u0440\u0430\u044e lesson learned");
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("kill", "\u0412\u0440\u0430\u0433");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("death", "\u0421\u043c\u0435\u0440\u0442\u044c");
        public final String R_4764_Y;
        public final String G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String token, String ruLabel) {
            this.R_4764_Y = token;
            this.G_564_y = ruLabel;
        }

        public static n_1700_B n_1700_B(String s) {
            if (s == null) {
                return null;
            }
            String t = s.trim().toLowerCase(Locale.ROOT);
            for (n_1700_B k : lightning.product.B_1277_o$n_1700_B.values()) {
                if (!k.R_4764_Y.equalsIgnoreCase(t)) continue;
                return k;
            }
            if (t.equals("\u0432\u0440\u0430\u0433") || t.equals("kill") || t.equals("\u0443\u0431\u0438\u0439\u0441\u0442\u0432\u043e") || t.equals("k")) {
                return n_1700_B;
            }
            if (t.equals("\u0441\u043c\u0435\u0440\u0442\u044c") || t.equals("death") || t.equals("d")) {
                return J_1907_R;
            }
            return null;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.B_1277_o$n_1700_B.n_1700_B();
        }
    }
}

