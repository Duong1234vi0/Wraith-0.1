/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  org.apache.http.NameValuePair
 *  org.apache.http.client.utils.URLEncodedUtils
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import lightning.product.U_3443_A;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class t_4562_T {
    static ExecutorService n_1700_B = Executors.newSingleThreadExecutor();
    private static final String R_4764_Y = "9fbc7315-7200-4b2b-a655-bb38c865da17";
    private static HttpServer G_564_y;
    private static Consumer<String> P_1922_E;
    static Gson J_1907_R;

    static void n_1700_B(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                return;
            }
        }
        catch (IOException | SecurityException | UnsupportedOperationException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", url).start();
            } else {
                new ProcessBuilder("xdg-open", url).start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void n_1700_B(Consumer<String> callback) {
        P_1922_E = callback;
        t_4562_T.n_1700_B();
        String redirect = URLEncoder.encode("http://localhost:8247", StandardCharsets.UTF_8);
        String scope = URLEncoder.encode("XboxLive.signin offline_access", StandardCharsets.UTF_8);
        String authUrl = "https://login.live.com/oauth20_authorize.srf?client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&response_type=code&redirect_uri=" + redirect + "&scope=" + scope;
        System.out.println("Microsoft auth URL: " + authUrl);
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(authUrl), null);
        }
        catch (Exception exception) {
            // empty catch block
        }
        t_4562_T.n_1700_B(authUrl);
    }

    public static G_564_y J_1907_R(String refreshToken) {
        String tokenResp = U_3443_A.n_1700_B("https://login.live.com/oauth20_token.srf", "client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&client_secret=Bzn8Q~YryydJsydgnnxHgJq.NM3Oo4.AEEohLbBb&refresh_token=" + refreshToken + "&grant_type=refresh_token&redirect_uri=http://localhost:8247", false);
        System.out.println("MicrosoftLogin: token endpoint response: " + tokenResp);
        n_1700_B res = (n_1700_B)J_1907_R.fromJson(tokenResp, n_1700_B.class);
        if (res == null) {
            System.out.println("MicrosoftLogin: failed to parse auth token response");
            return new G_564_y();
        }
        String accessToken = res.n_1700_B;
        refreshToken = res.J_1907_R;
        String xblReq = "{\"Properties\":{\"AuthMethod\":\"RPS\",\"SiteName\":\"user.auth.xboxlive.com\",\"RpsTicket\":\"d=" + accessToken + "\"},\"RelyingParty\":\"http://auth.xboxlive.com\",\"TokenType\":\"JWT\"}";
        String xblResp = U_3443_A.n_1700_B("https://user.auth.xboxlive.com/user/authenticate", xblReq, true);
        System.out.println("MicrosoftLogin: xbl authenticate response: " + xblResp);
        v_4262_N xblRes = (v_4262_N)J_1907_R.fromJson(xblResp, v_4262_N.class);
        if (xblRes == null) {
            System.out.println("MicrosoftLogin: failed to parse XBL response");
            return new G_564_y();
        }
        String xstsReq = "{\"Properties\":{\"SandboxId\":\"RETAIL\",\"UserTokens\":[\"" + xblRes.n_1700_B + "\"]},\"RelyingParty\":\"rp://api.minecraftservices.com/\",\"TokenType\":\"JWT\"}";
        String xstsResp = U_3443_A.n_1700_B("https://xsts.auth.xboxlive.com/xsts/authorize", xstsReq, true);
        System.out.println("MicrosoftLogin: xsts response: " + xstsResp);
        v_4262_N xstsRes = (v_4262_N)J_1907_R.fromJson(xstsResp, v_4262_N.class);
        if (xstsRes == null) {
            System.out.println("MicrosoftLogin: failed to parse XSTS response");
            return new G_564_y();
        }
        String mcReq = "{\"identityToken\":\"XBL3.0 x=" + xblRes.J_1907_R.n_1700_B[0].n_1700_B + ";" + xstsRes.n_1700_B + "\"}";
        String mcResp = U_3443_A.n_1700_B("https://api.minecraftservices.com/authentication/login_with_xbox", mcReq, true);
        System.out.println("MicrosoftLogin: minecraft login response: " + mcResp);
        P_1922_E mcRes = (P_1922_E)J_1907_R.fromJson(mcResp, P_1922_E.class);
        if (mcRes == null) {
            System.out.println("MicrosoftLogin: failed to parse Minecraft login response");
            return new G_564_y();
        }
        String ownershipResp = U_3443_A.n_1700_B("https://api.minecraftservices.com/entitlements/mcstore", mcRes.n_1700_B);
        System.out.println("MicrosoftLogin: ownership response: " + ownershipResp);
        J_1907_R gameOwnershipRes = (J_1907_R)J_1907_R.fromJson(ownershipResp, J_1907_R.class);
        if (gameOwnershipRes == null || !gameOwnershipRes.n_1700_B()) {
            System.out.println("MicrosoftLogin: no game ownership");
            return new G_564_y();
        }
        String profileResp = U_3443_A.n_1700_B("https://api.minecraftservices.com/minecraft/profile", mcRes.n_1700_B);
        System.out.println("MicrosoftLogin: profile response: " + profileResp);
        u_1723_Y profileRes = (u_1723_Y)J_1907_R.fromJson(profileResp, u_1723_Y.class);
        if (profileRes == null) {
            System.out.println("MicrosoftLogin: failed to parse profile response");
            return new G_564_y();
        }
        return new G_564_y(mcRes.n_1700_B, refreshToken, profileRes.n_1700_B, profileRes.J_1907_R);
    }

    private static void n_1700_B() {
        if (G_564_y != null) {
            return;
        }
        try {
            G_564_y = HttpServer.create(new InetSocketAddress("localhost", 8247), 0);
            G_564_y.createContext("/", new R_4764_Y());
            G_564_y.setExecutor(n_1700_B);
            G_564_y.start();
            System.out.println("MicrosoftLogin: local callback server started on http://localhost:8247/");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void J_1907_R() {
        if (G_564_y == null) {
            return;
        }
        G_564_y.stop(0);
        G_564_y = null;
        P_1922_E = null;
    }

    static {
        J_1907_R = new Gson();
    }

    private static class n_1700_B {
        @Expose
        @SerializedName(value="access_token")
        public String n_1700_B;
        @Expose
        @SerializedName(value="refresh_token")
        public String J_1907_R;

        private n_1700_B() {
        }
    }

    public static class G_564_y {
        public String n_1700_B;
        public String J_1907_R;
        public String R_4764_Y;
        public String G_564_y;

        public G_564_y() {
        }

        public G_564_y(String mcToken, String newRefreshToken, String uuid, String username) {
            this.n_1700_B = mcToken;
            this.J_1907_R = newRefreshToken;
            this.R_4764_Y = uuid;
            this.G_564_y = username;
        }

        public boolean n_1700_B() {
            return this.n_1700_B != null;
        }
    }

    private static class v_4262_N {
        @Expose
        @SerializedName(value="Token")
        public String n_1700_B;
        @Expose
        @SerializedName(value="DisplayClaims")
        public n_1700_B J_1907_R;

        private v_4262_N() {
        }

        private static class lightning.product.t_4562_T$v_4262_N$n_1700_B {
            @Expose
            @SerializedName(value="xui")
            private n_1700_B[] n_1700_B;

            private lightning.product.t_4562_T$v_4262_N$n_1700_B() {
            }

            private static class n_1700_B {
                @Expose
                @SerializedName(value="uhs")
                private String n_1700_B;

                private n_1700_B() {
                }
            }
        }
    }

    private static class P_1922_E {
        @Expose
        @SerializedName(value="access_token")
        public String n_1700_B;

        private P_1922_E() {
        }
    }

    private static class J_1907_R {
        @Expose
        @SerializedName(value="items")
        private n_1700_B[] n_1700_B;

        private J_1907_R() {
        }

        private boolean n_1700_B() {
            boolean hasProduct = false;
            boolean hasGame = false;
            for (n_1700_B item : this.n_1700_B) {
                if (item.n_1700_B.equals("product_minecraft")) {
                    hasProduct = true;
                    continue;
                }
                if (!item.n_1700_B.equals("game_minecraft")) continue;
                hasGame = true;
            }
            return hasProduct && hasGame;
        }

        private static class n_1700_B {
            @Expose
            @SerializedName(value="name")
            private String n_1700_B;

            private n_1700_B() {
            }
        }
    }

    private static class u_1723_Y {
        @Expose
        @SerializedName(value="id")
        public String n_1700_B;
        @Expose
        @SerializedName(value="name")
        public String J_1907_R;

        private u_1723_Y() {
        }
    }

    private static class R_4764_Y
    implements HttpHandler {
        private R_4764_Y() {
        }

        public void n_1700_B(HttpExchange req) throws IOException {
            if (req.getRequestMethod().equals("GET")) {
                List query = URLEncodedUtils.parse((URI)req.getRequestURI(), (String)StandardCharsets.UTF_8.name());
                boolean ok = false;
                for (NameValuePair pair : query) {
                    if (!pair.getName().equals("code")) continue;
                    try {
                        this.n_1700_B(pair.getValue());
                        this.n_1700_B(req, "<html>You may now close this page.<script>close()</script></html>");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        this.n_1700_B(req, "<html>Authentication failed: " + e.getMessage() + "</html>");
                    }
                    ok = true;
                    break;
                }
                if (!ok) {
                    String queryStr = req.getRequestURI().getQuery();
                    System.out.println("MicrosoftLogin: callback without code, query=" + queryStr);
                    this.n_1700_B(req, "<html>Cannot authenticate. Query: " + (queryStr == null ? "" : queryStr) + "</html>");
                }
            }
            t_4562_T.J_1907_R();
        }

        private void n_1700_B(String code) {
            String response = U_3443_A.n_1700_B("https://login.live.com/oauth20_token.srf", "client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&code=" + code + "&client_secret=Bzn8Q~YryydJsydgnnxHgJq.NM3Oo4.AEEohLbBb&grant_type=authorization_code&redirect_uri=http://localhost:8247", false);
            n_1700_B res = (n_1700_B)J_1907_R.fromJson(response, n_1700_B.class);
            if (res == null) {
                P_1922_E.accept(null);
            } else {
                P_1922_E.accept(res.J_1907_R);
            }
        }

        private void n_1700_B(HttpExchange req, String text) throws IOException {
            OutputStream out = req.getResponseBody();
            req.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            req.sendResponseHeaders(200, text.length());
            out.write(text.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        }
    }
}

