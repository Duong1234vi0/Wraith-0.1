/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.C_332_W;
import lightning.product.D_3097_e;
import lightning.product.F_1013_a;
import lightning.product.H_1491_c;
import lightning.product.NumberSetting;
import lightning.product.O_3016_i;
import lightning.product.ServerData;
import lightning.product.Module;
import lightning.product.a_2587_Z;
import lightning.product.b_2037_V;
import lightning.product.m_3545_A;
import lightning.product.ModeSetting;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;

public class BedrockProxy
extends Module {
    private static final String v_4262_N = C_332_W.n_1700_B + "bedrock-proxy\\";
    private static final String w_1484_f = v_4262_N + "ViaProxy.jar";
    private static final String t_148_a = v_4262_N + "viaproxy.yml";
    private static final String s_956_w = v_4262_N + "start-viaproxy.bat";
    private static final String u_2550_I = "https://api.github.com/repos/ViaVersion/ViaProxy/releases/latest";
    private static final String M_588_G = "Bedrock 1.26.0";
    private final O_3016_i P_4830_p = new O_3016_i("Bedrock IP");
    private final NumberSetting bedrockPortSetting = new NumberSetting("Bedrock Port", 19132.0f, 1.0f, 65535.0f, 1.0f);
    private final NumberSetting javaPortLokalnyySetting = new NumberSetting("Java Port (\u043b\u043e\u043a\u0430\u043b\u044c\u043d\u044b\u0439)", 25565.0f, 1.0f, 65535.0f, 1.0f);
    private final ModeSetting avtorizaciyaMode = new ModeSetting("\u0410\u0432\u0442\u043e\u0440\u0438\u0437\u0430\u0446\u0438\u044f", "\u0411\u0435\u0437 \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430", "\u0411\u0435\u0437 \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430", "Bedrock \u0430\u043a\u043a\u0430\u0443\u043d\u0442");
    private final b_2037_V t_1786_h = new b_2037_V("\u0421\u0442\u0430\u0442\u0443\u0441: \u043d\u0435 \u0437\u0430\u043f\u0443\u0449\u0435\u043d");
    private final H_1491_c multiplayerClientSuggestionProvider = new H_1491_c("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u043b\u043e\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0441\u0435\u0440\u0432\u0435\u0440", this::h_1847_R);
    private final H_1491_c w_1457_N = new H_1491_c("\u041f\u0438\u043d\u0433 Bedrock \u0441\u0435\u0440\u0432\u0435\u0440\u0430", this::Q_4569_t);
    private final H_1491_c Y_601_j = new H_1491_c("\u0421\u043a\u0430\u0447\u0430\u0442\u044c ViaProxy", this::M_182_A);
    private final H_1491_c Y_259_p = new H_1491_c("\u0417\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438", this::t_1786_h);
    private final H_1491_c Q_2552_b = new H_1491_c("\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438", this::multiplayerClientSuggestionProvider, this::Y_601_j);
    private final H_1491_c C_2741_M = new H_1491_c("\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043f\u0430\u043f\u043a\u0443 \u043f\u0440\u043e\u043a\u0441\u0438", this::w_1457_N);
    private Process k_2293_S;
    private ExecutorService q_2307_F;
    private volatile boolean Z_875_P;
    private final D_3097_e t_4043_B = new D_3097_e();

    public BedrockProxy() {
        super("BedrockProxy", ModuleCategory.P_1922_E);
        this.addSettings(this.P_4830_p, this.bedrockPortSetting, this.javaPortLokalnyySetting, this.avtorizaciyaMode, this.t_1786_h, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.Q_2552_b();
        if (new File(w_1484_f).exists()) {
            this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0433\u043e\u0442\u043e\u0432 \u043a \u0437\u0430\u043f\u0443\u0441\u043a\u0443");
        } else {
            this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: ViaProxy \u043d\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d");
            v_1900_v.n_1700_B("\u00a7eViaProxy \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d. \u041d\u0430\u0436\u043c\u0438\u0442\u0435 '\u0421\u043a\u0430\u0447\u0430\u0442\u044c ViaProxy' \u0432 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430\u0445 \u043c\u043e\u0434\u0443\u043b\u044f.", new Object[0]);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.multiplayerClientSuggestionProvider();
        if (this.q_2307_F != null) {
            this.q_2307_F.shutdownNow();
            this.q_2307_F = null;
        }
    }

    private void h_1847_R() {
        int port = ((Float)this.javaPortLokalnyySetting.getValue()).intValue();
        String localAddress = "127.0.0.1:" + port;
        String target = this.C_2741_M();
        F_1013_a.J_1907_R(localAddress);
        ServerData serverData = new ServerData("\u00a7b[BE Proxy] \u00a7r127.0.0.1:" + port + " \u00a77-> \u00a7r" + target, localAddress, false);
        m_3545_A serverList = new m_3545_A(c_3005_b);
        serverList.n_1700_B();
        serverList.J_1907_R(serverData);
        serverList.J_1907_R();
        v_1900_v.n_1700_B("\u00a7a\u041b\u043e\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0441\u0435\u0440\u0432\u0435\u0440 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d: \u00a7f" + localAddress, new Object[0]);
        v_1900_v.n_1700_B("\u00a77\u0421\u043d\u0430\u0447\u0430\u043b\u0430 \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u0435 \u043f\u0440\u043e\u043a\u0441\u0438, \u043f\u043e\u0442\u043e\u043c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0430\u0439\u0442\u0435\u0441\u044c \u043a \u044d\u0442\u043e\u043c\u0443 \u0441\u0435\u0440\u0432\u0435\u0440\u0443 \u0432 Multiplayer.", new Object[0]);
        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d " + localAddress);
    }

    private void Q_4569_t() {
        String ip = (String)this.P_4830_p.J_1907_R();
        if (ip == null || ip.trim().isEmpty()) {
            v_1900_v.n_1700_B("\u00a7c\u0423\u043a\u0430\u0436\u0438\u0442\u0435 IP Bedrock \u0441\u0435\u0440\u0432\u0435\u0440\u0430.", new Object[0]);
            return;
        }
        int port = ((Float)this.bedrockPortSetting.getValue()).intValue();
        v_1900_v.n_1700_B("\u00a7e\u041f\u0438\u043d\u0433\u0443\u044e " + ip.trim() + ":" + port + "...", new Object[0]);
        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043f\u0438\u043d\u0433\u0443\u044e...");
        this.Y_259_p().submit(() -> {
            try {
                a_2587_Z info = this.t_4043_B.J_1907_R(ip.trim(), port, 5000);
                v_1900_v.n_1700_B("\u00a7a=== Bedrock \u0441\u0435\u0440\u0432\u0435\u0440 ===", new Object[0]);
                v_1900_v.n_1700_B("\u00a7f\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435: \u00a7e" + info.R_4764_Y(), new Object[0]);
                v_1900_v.n_1700_B("\u00a7f\u0412\u0435\u0440\u0441\u0438\u044f: \u00a7b" + info.P_1922_E() + " \u00a77(\u043f\u0440\u043e\u0442\u043e\u043a\u043e\u043b: " + info.G_564_y() + ")", new Object[0]);
                v_1900_v.n_1700_B("\u00a7f\u0418\u0433\u0440\u043e\u043a\u0438: \u00a7a" + info.u_1723_Y() + "\u00a77/\u00a7a" + info.v_4262_N(), new Object[0]);
                v_1900_v.n_1700_B("\u00a7f\u0420\u0435\u0436\u0438\u043c: \u00a7d" + info.s_956_w(), new Object[0]);
                if (!info.t_148_a().isEmpty()) {
                    v_1900_v.n_1700_B("\u00a7f\u041c\u0438\u0440: \u00a77" + info.t_148_a(), new Object[0]);
                }
                v_1900_v.n_1700_B("\u00a7a====================", new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: " + info.R_4764_Y() + " (" + info.u_1723_Y() + "/" + info.v_4262_N() + ")");
            }
            catch (Exception e) {
                v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0438\u043d\u0433\u0430: " + e.getMessage(), new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043e\u0448\u0438\u0431\u043a\u0430 \u043f\u0438\u043d\u0433\u0430");
            }
        });
    }

    private void M_182_A() {
        if (this.Z_875_P) {
            v_1900_v.n_1700_B("\u00a7c\u0417\u0430\u0433\u0440\u0443\u0437\u043a\u0430 \u0443\u0436\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0435\u0442\u0441\u044f.", new Object[0]);
            return;
        }
        this.Z_875_P = true;
        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0430 ViaProxy...");
        v_1900_v.n_1700_B("\u00a7e\u0418\u0449\u0443 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0438\u0439 ViaProxy...", new Object[0]);
        this.Y_259_p().submit(() -> {
            try {
                this.Q_2552_b();
                String downloadUrl = this.Z_875_P();
                if (downloadUrl == null) {
                    throw new IOException("\u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d jar-\u0430\u0441\u0441\u0435\u0442 ViaProxy \u0432 GitHub releases");
                }
                v_1900_v.n_1700_B("\u00a77\u0421\u043a\u0430\u0447\u0438\u0432\u0430\u044e ViaProxy...", new Object[0]);
                this.n_1700_B(downloadUrl, Path.of(w_1484_f, new String[0]));
                v_1900_v.n_1700_B("\u00a7aViaProxy \u0441\u043a\u0430\u0447\u0430\u043d.", new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0433\u043e\u0442\u043e\u0432 \u043a \u0437\u0430\u043f\u0443\u0441\u043a\u0443");
            }
            catch (Exception e) {
                v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 ViaProxy: " + e.getMessage(), new Object[0]);
                v_1900_v.n_1700_B("\u00a77\u041c\u043e\u0436\u043d\u043e \u0441\u043a\u0430\u0447\u0430\u0442\u044c \u0432\u0440\u0443\u0447\u043d\u0443\u044e: \u00a7fhttps://github.com/ViaVersion/ViaProxy/releases/latest", new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438");
            }
            finally {
                this.Z_875_P = false;
            }
        });
    }

    private void t_1786_h() {
        if (this.Y_601_j()) {
            v_1900_v.n_1700_B("\u00a7c\u041f\u0440\u043e\u043a\u0441\u0438 \u0443\u0436\u0435 \u0437\u0430\u043f\u0443\u0449\u0435\u043d.", new Object[0]);
            return;
        }
        File jar = new File(w_1484_f);
        if (!jar.exists()) {
            v_1900_v.n_1700_B("\u00a7cViaProxy \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d. \u0421\u043d\u0430\u0447\u0430\u043b\u0430 \u0441\u043a\u0430\u0447\u0430\u0439\u0442\u0435 \u0435\u0433\u043e.", new Object[0]);
            this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: ViaProxy \u043d\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d");
            return;
        }
        String ip = (String)this.P_4830_p.J_1907_R();
        if (ip == null || ip.trim().isEmpty()) {
            v_1900_v.n_1700_B("\u00a7c\u0423\u043a\u0430\u0436\u0438\u0442\u0435 IP Bedrock \u0441\u0435\u0440\u0432\u0435\u0440\u0430.", new Object[0]);
            return;
        }
        int localPort = ((Float)this.javaPortLokalnyySetting.getValue()).intValue();
        int targetPort = ((Float)this.bedrockPortSetting.getValue()).intValue();
        if (this.n_1700_B(localPort, 200)) {
            v_1900_v.n_1700_B("\u00a7c\u041f\u043e\u0440\u0442 127.0.0.1:" + localPort + " \u0443\u0436\u0435 \u0437\u0430\u043d\u044f\u0442.", new Object[0]);
            v_1900_v.n_1700_B("\u00a77\u0417\u0430\u043a\u0440\u043e\u0439\u0442\u0435 \u0441\u0442\u0430\u0440\u044b\u0439 ViaProxy/\u043b\u043e\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0441\u0435\u0440\u0432\u0435\u0440 \u0438\u043b\u0438 \u043f\u043e\u0441\u0442\u0430\u0432\u044c\u0442\u0435 \u0434\u0440\u0443\u0433\u043e\u0439 Java Port, \u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440 25566.", new Object[0]);
            this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043f\u043e\u0440\u0442 " + localPort + " \u0437\u0430\u043d\u044f\u0442");
            return;
        }
        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0437\u0430\u043f\u0443\u0441\u043a...");
        v_1900_v.n_1700_B("\u00a7e\u0417\u0430\u043f\u0443\u0441\u043a\u0430\u044e ViaProxy: \u00a7f127.0.0.1:" + localPort + " \u00a77-> \u00a7f" + ip.trim() + ":" + targetPort, new Object[0]);
        this.Y_259_p().submit(() -> {
            try {
                String targetVersion = this.n_1700_B(ip.trim(), targetPort);
                this.n_1700_B(ip.trim(), targetPort, localPort, targetVersion);
                if (this.avtorizaciyaMode.isMode("Bedrock \u0430\u043a\u043a\u0430\u0443\u043d\u0442") && !this.H_2857_Y()) {
                    v_1900_v.n_1700_B("\u00a7e\u0412 ViaProxy \u0435\u0449\u0451 \u043d\u0435\u0442 Bedrock \u0430\u043a\u043a\u0430\u0443\u043d\u0442\u0430.", new Object[0]);
                    v_1900_v.n_1700_B("\u00a77\u0412 \u043e\u0442\u043a\u0440\u044b\u0442\u043e\u0439 \u043a\u043e\u043d\u0441\u043e\u043b\u0438 \u0432\u0432\u0435\u0434\u0438\u0442\u0435: \u00a7faccount add bedrock\u00a77, \u0437\u0430\u0442\u0435\u043c \u0432\u043e\u0439\u0434\u0438\u0442\u0435 \u043f\u043e \u043a\u043e\u0434\u0443 Microsoft.", new Object[0]);
                    v_1900_v.n_1700_B("\u00a77\u041f\u043e\u0441\u043b\u0435 \u0443\u0441\u043f\u0435\u0448\u043d\u043e\u0433\u043e \u0432\u0445\u043e\u0434\u0430 \u043f\u0435\u0440\u0435\u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u0435 \u043f\u0440\u043e\u043a\u0441\u0438.", new Object[0]);
                }
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "\"ViaProxy\"", "/D", v_4262_N, "cmd.exe", "/k", "start-viaproxy.bat");
                builder.directory(new File(v_4262_N));
                builder.redirectErrorStream(true);
                this.k_2293_S = builder.start();
                v_1900_v.n_1700_B("\u00a7a\u041e\u0442\u043a\u0440\u044b\u043b ViaProxy \u0432 \u043e\u0442\u0434\u0435\u043b\u044c\u043d\u043e\u0439 \u043a\u043e\u043d\u0441\u043e\u043b\u0438. \u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0430\u0439\u0442\u0435\u0441\u044c \u043a \u00a7f127.0.0.1:" + localPort, new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043f\u0440\u043e\u0432\u0435\u0440\u044f\u044e \u043f\u043e\u0440\u0442 127.0.0.1:" + localPort);
                this.J_1907_R(localPort);
            }
            catch (Exception e) {
                v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u043f\u0443\u0441\u043a\u0430 ViaProxy: " + e.getMessage(), new Object[0]);
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u043f\u0443\u0441\u043a\u0430");
            }
        });
    }

    private void n_1700_B(Process process) {
        this.Y_259_p().submit(() -> {
            boolean started = false;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));){
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[ViaProxy] " + line);
                    String lower = line.toLowerCase(Locale.ROOT);
                    if (!started && (lower.contains("viaproxy started successfully") || lower.contains("starting proxy server"))) {
                        started = true;
                        v_1900_v.n_1700_B("\u00a7aViaProxy \u0437\u0430\u043f\u0443\u0449\u0435\u043d. \u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0430\u0439\u0442\u0435\u0441\u044c \u043a \u00a7f127.0.0.1:" + ((Float)this.javaPortLokalnyySetting.getValue()).intValue(), new Object[0]);
                        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442 (127.0.0.1:" + ((Float)this.javaPortLokalnyySetting.getValue()).intValue() + ")");
                    }
                    if (!lower.contains("failed") && !lower.contains("exception") && !lower.contains("error")) continue;
                    v_1900_v.n_1700_B("\u00a7c[ViaProxy] " + line, new Object[0]);
                }
                int exitCode = process.waitFor();
                if (this.k_2293_S == process) {
                    this.k_2293_S = null;
                }
                if (exitCode != 0) {
                    this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: ViaProxy \u0437\u0430\u0432\u0435\u0440\u0448\u0438\u043b\u0441\u044f \u0441 \u043e\u0448\u0438\u0431\u043a\u043e\u0439");
                    v_1900_v.n_1700_B("\u00a7cViaProxy \u0437\u0430\u0432\u0435\u0440\u0448\u0438\u043b\u0441\u044f \u0441 \u043a\u043e\u0434\u043e\u043c " + exitCode + ".", new Object[0]);
                    v_1900_v.n_1700_B("\u00a77\u041f\u0440\u043e\u0432\u0435\u0440\u044c\u0442\u0435 viaproxy.yml \u0432 \u043f\u0430\u043f\u043a\u0435 \u043f\u0440\u043e\u043a\u0441\u0438 \u0438 \u043f\u043e\u043f\u0440\u043e\u0431\u0443\u0439\u0442\u0435 \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c ViaProxy.jar \u0432\u0440\u0443\u0447\u043d\u0443\u044e.", new Object[0]);
                } else if (started) {
                    this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d");
                }
            }
            catch (Exception e) {
                if (this.k_2293_S == process) {
                    this.k_2293_S = null;
                }
                this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: ViaProxy \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d");
            }
        });
    }

    private void multiplayerClientSuggestionProvider() {
        if (this.k_2293_S == null) {
            return;
        }
        Process process = this.k_2293_S;
        this.k_2293_S = null;
        if (process.isAlive()) {
            process.destroy();
            try {
                if (!process.waitFor(3L, TimeUnit.SECONDS)) {
                    process.destroyForcibly();
                }
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                process.destroyForcibly();
            }
        }
        v_1900_v.n_1700_B("\u00a7eViaProxy \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d.", new Object[0]);
        this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d");
    }

    private void w_1457_N() {
        this.Q_2552_b();
        try {
            Runtime.getRuntime().exec("explorer.exe " + v_4262_N);
        }
        catch (IOException e) {
            v_1900_v.n_1700_B("\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u043f\u0430\u043f\u043a\u0443: " + e.getMessage(), new Object[0]);
        }
    }

    private boolean Y_601_j() {
        return this.k_2293_S != null && this.k_2293_S.isAlive();
    }

    private void J_1907_R(int port) {
        this.Y_259_p().submit(() -> {
            for (int i = 0; i < 30; ++i) {
                if (this.n_1700_B(port, 300)) {
                    this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442 (127.0.0.1:" + port + ")");
                    v_1900_v.n_1700_B("\u00a7a\u041f\u0440\u043e\u043a\u0441\u0438 \u0441\u043b\u0443\u0448\u0430\u0435\u0442 \u00a7f127.0.0.1:" + port, new Object[0]);
                    return;
                }
                try {
                    Thread.sleep(500L);
                    continue;
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            this.t_1786_h.n_1700_B("\u0421\u0442\u0430\u0442\u0443\u0441: \u043f\u043e\u0440\u0442 \u043d\u0435 \u043e\u0442\u043a\u0440\u044b\u043b\u0441\u044f");
            v_1900_v.n_1700_B("\u00a7cViaProxy \u043d\u0435 \u043e\u0442\u043a\u0440\u044b\u043b \u043f\u043e\u0440\u0442 127.0.0.1:" + port + ".", new Object[0]);
            v_1900_v.n_1700_B("\u00a77\u041e\u0442\u043a\u0440\u043e\u0439\u0442\u0435 \u00a7f" + v_4262_N + "viaproxy.log \u00a77\u0438 \u043f\u0440\u0438\u0448\u043b\u0438\u0442\u0435 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0438\u0435 \u0441\u0442\u0440\u043e\u043a\u0438.", new Object[0]);
        });
    }

    private boolean n_1700_B(int port, int timeoutMs) {
        boolean bl;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", port), timeoutMs);
            bl = true;
        }
        catch (Throwable throwable) {
            try {
                try {
                    socket.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (IOException ignored) {
                return false;
            }
        }
        socket.close();
        return bl;
    }

    private ExecutorService Y_259_p() {
        if (this.q_2307_F == null || this.q_2307_F.isShutdown()) {
            this.q_2307_F = Executors.newFixedThreadPool(3, r -> {
                Thread thread = new Thread(r, "BedrockProxy-Worker");
                thread.setDaemon(true);
                return thread;
            });
        }
        return this.q_2307_F;
    }

    private void Q_2552_b() {
        File dir = new File(v_4262_N);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private String C_2741_M() {
        String ip = (String)this.P_4830_p.J_1907_R();
        String host = ip == null || ip.trim().isEmpty() ? "bedrock-server" : ip.trim();
        return host + ":" + ((Float)this.bedrockPortSetting.getValue()).intValue();
    }

    private String k_2293_S() {
        File bundledJava21 = this.q_2307_F();
        if (bundledJava21 != null) {
            return bundledJava21.getAbsolutePath();
        }
        String javaHome = System.getProperty("java.home");
        if (javaHome != null && !javaHome.isEmpty()) {
            File java = new File(javaHome, "bin\\java.exe");
            if (java.exists()) {
                return java.getAbsolutePath();
            }
            File javaw = new File(javaHome, "bin\\javaw.exe");
            if (javaw.exists()) {
                return javaw.getAbsolutePath();
            }
        }
        return "java";
    }

    private File q_2307_F() {
        String userHome = System.getProperty("user.home");
        if (userHome == null || userHome.isEmpty()) {
            return null;
        }
        File jdksDir = new File(userHome, ".jdks");
        File[] jdks = jdksDir.listFiles(File::isDirectory);
        if (jdks == null) {
            return null;
        }
        for (File jdk : jdks) {
            File java;
            if (!jdk.getName().contains("21") || !(java = new File(jdk, "bin\\java.exe")).exists()) continue;
            return java;
        }
        return null;
    }

    private String Z_875_P() throws IOException {
        Matcher regularJar;
        String json;
        HttpURLConnection connection = this.R_4764_Y(u_2550_I);
        try (InputStream input = connection.getInputStream();){
            json = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
        if (!this.c_3005_b() && (regularJar = Pattern.compile("\"browser_download_url\"\\s*:\\s*\"([^\"]*ViaProxy-[^\"]*(?<!java8)\\.jar)\"").matcher(json)).find()) {
            return regularJar.group(1).replace("\\/", "/");
        }
        Matcher java8 = Pattern.compile("\"browser_download_url\"\\s*:\\s*\"([^\"]*ViaProxy-[^\"]*java8\\.jar)\"").matcher(json);
        if (java8.find()) {
            return java8.group(1).replace("\\/", "/");
        }
        Matcher anyJar = Pattern.compile("\"browser_download_url\"\\s*:\\s*\"([^\"]*ViaProxy-[^\"]*\\.jar)\"").matcher(json);
        return anyJar.find() ? anyJar.group(1).replace("\\/", "/") : null;
    }

    private boolean c_3005_b() {
        String version = System.getProperty("java.specification.version", "");
        return version.equals("1.8") || version.equals("8");
    }

    private void n_1700_B(String url, Path target) throws IOException {
        HttpURLConnection connection = this.R_4764_Y(url);
        if (connection.getResponseCode() != 200) {
            throw new IOException("HTTP " + connection.getResponseCode());
        }
        try (InputStream input = connection.getInputStream();){
            Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private HttpURLConnection R_4764_Y(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Pouch-BedrockProxy");
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(120000);
        connection.setInstanceFollowRedirects(true);
        return connection;
    }

    private String n_1700_B(String host, int port) {
        try {
            a_2587_Z info = this.t_4043_B.J_1907_R(host, port, 5000);
            String gameVersion = info.P_1922_E();
            if (gameVersion != null && gameVersion.matches("\\d+(\\.\\d+){1,3}")) {
                v_1900_v.n_1700_B("\u00a7aBedrock \u0432\u0435\u0440\u0441\u0438\u044f \u0441\u0435\u0440\u0432\u0435\u0440\u0430: \u00a7f" + gameVersion + " \u00a77(\u043f\u0440\u043e\u0442\u043e\u043a\u043e\u043b " + info.G_564_y() + ")", new Object[0]);
                return "Bedrock " + gameVersion;
            }
        }
        catch (Exception e) {
            v_1900_v.n_1700_B("\u00a7e\u041d\u0435 \u0441\u043c\u043e\u0433 \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c Bedrock \u0432\u0435\u0440\u0441\u0438\u044e, \u043e\u0441\u0442\u0430\u0432\u043b\u044f\u044e Bedrock 1.26.0: " + e.getMessage(), new Object[0]);
        }
        return M_588_G;
    }

    private boolean H_2857_Y() {
        try {
            Path savesPath = Path.of(v_4262_N, "saves.json");
            if (!Files.exists(savesPath, new LinkOption[0])) {
                return false;
            }
            String saves = Files.readString(savesPath, StandardCharsets.UTF_8);
            return !saves.contains("\"accountsV4\":[]");
        }
        catch (IOException ignored) {
            return false;
        }
    }

    private void n_1700_B(String bedrockIp, int bedrockPort, int javaPort, String targetVersion) {
        boolean useAccount = this.avtorizaciyaMode.isMode("Bedrock \u0430\u043a\u043a\u0430\u0443\u043d\u0442");
        String config = "bind-address: 127.0.0.1:%d\ntarget-address: %s:%d\ntarget-version: %s\nconnect-timeout: 8000\nproxy-online-mode: false\nauth-method: %s\nminecraft-account-index: 0\nbetacraft-auth: false\nproxy: ''\nbackend-proxy-url: ''\nbackend-haproxy: false\nfrontend-haproxy: false\nchat-signing: true\ncompression-threshold: 256\nallow-beta-pinging: false\nignore-protocol-translation-errors: true\nsuppress-client-protocol-errors: true\nallow-legacy-client-passthrough: false\nbungeecord-player-info-passthrough: false\nrewrite-handshake-packet: true\nrewrite-transfer-packets: true\ncustom-motd: ''\ncustom-favicon-path: ''\nresource-pack-url: ''\nwildcard-domain-handling: NONE\nsimple-voice-chat-support: false\nfix-fabric-particle-api: true\nfake-accept-resource-packs: true\nskip-config-state-packet-queue: false\nlog-ips: true\nlog-client-status-requests: true\n".formatted(javaPort, bedrockIp, bedrockPort, targetVersion, useAccount ? "ACCOUNT" : "NONE");
        try {
            Files.writeString(Path.of(t_148_a, new String[0]), (CharSequence)config, StandardCharsets.UTF_8, new OpenOption[0]);
            this.A_4115_X();
        }
        catch (IOException e) {
            v_1900_v.n_1700_B("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u044f viaproxy.yml: " + e.getMessage(), new Object[0]);
        }
    }

    private void A_4115_X() throws IOException {
        String script = "@echo off\r\ncd /d \"%~dp0\"\r\necho Starting ViaProxy...\r\n\"" + this.k_2293_S() + "\" -Xms256M -Xmx1024M -jar ViaProxy.jar config viaproxy.yml\r\necho.\r\necho ViaProxy closed. Press any key to exit...\r\npause >nul\r\n";
        Files.writeString(Path.of(s_956_w, new String[0]), (CharSequence)script, StandardCharsets.UTF_8, new OpenOption[0]);
    }
}



