package sky.core.utils.net;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.Desktop.Action;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class MicrosoftLogin {
   static ExecutorService executor = Executors.newSingleThreadExecutor();
   private static final String CLIENT_ID = "9fbc7315-7200-4b2b-a655-bb38c865da17";
   private static HttpServer server;
   private static Consumer callback;
   static Gson gson = new Gson();

   static void browse(String url) {
      try {
         if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
            return;
         }
      } catch (SecurityException | IOException | URISyntaxException | UnsupportedOperationException var3) {
         var3.printStackTrace();
      }

      try {
         String os = System.getProperty("os.name").toLowerCase();
         if (os.contains("win")) {
            (new ProcessBuilder(new String[]{"rundll32", "url.dll,FileProtocolHandler", url})).start();
         } else if (os.contains("mac")) {
            (new ProcessBuilder(new String[]{"open", url})).start();
         } else {
            (new ProcessBuilder(new String[]{"xdg-open", url})).start();
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static void getRefreshToken(Consumer callback) {
      MicrosoftLogin.callback = callback;
      startServer();
      String redirect = URLEncoder.encode("http://localhost:8247", StandardCharsets.UTF_8);
      String scope = URLEncoder.encode("XboxLive.signin offline_access", StandardCharsets.UTF_8);
      String authUrl = "https://login.live.com/oauth20_authorize.srf?client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&response_type=code&redirect_uri=" + redirect + "&scope=" + scope;
      System.out.println("Microsoft auth URL: " + authUrl);

      try {
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(authUrl), (ClipboardOwner)null);
      } catch (Exception var5) {
      }

      browse(authUrl);
   }

   public static LoginData login(String refreshToken) {
      String tokenResp = BrowserUtil.postExternal("https://login.live.com/oauth20_token.srf", "client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&client_secret=Bzn8Q~YryydJsydgnnxHgJq.NM3Oo4.AEEohLbBb&refresh_token=" + refreshToken + "&grant_type=refresh_token&redirect_uri=http://localhost:8247", false);
      System.out.println("MicrosoftLogin: token endpoint response: " + tokenResp);
      AuthTokenResponse res = (AuthTokenResponse)gson.fromJson(tokenResp, AuthTokenResponse.class);
      if (res == null) {
         System.out.println("MicrosoftLogin: failed to parse auth token response");
         return new LoginData();
      } else {
         String accessToken = res.access_token;
         refreshToken = res.refresh_token;
         String xblReq = "{\"Properties\":{\"AuthMethod\":\"RPS\",\"SiteName\":\"user.auth.xboxlive.com\",\"RpsTicket\":\"d=" + accessToken + "\"},\"RelyingParty\":\"http://auth.xboxlive.com\",\"TokenType\":\"JWT\"}";
         String xblResp = BrowserUtil.postExternal("https://user.auth.xboxlive.com/user/authenticate", xblReq, true);
         System.out.println("MicrosoftLogin: xbl authenticate response: " + xblResp);
         XblXstsResponse xblRes = (XblXstsResponse)gson.fromJson(xblResp, XblXstsResponse.class);
         if (xblRes == null) {
            System.out.println("MicrosoftLogin: failed to parse XBL response");
            return new LoginData();
         } else {
            String xstsReq = "{\"Properties\":{\"SandboxId\":\"RETAIL\",\"UserTokens\":[\"" + xblRes.Token + "\"]},\"RelyingParty\":\"rp://api.minecraftservices.com/\",\"TokenType\":\"JWT\"}";
            String xstsResp = BrowserUtil.postExternal("https://xsts.auth.xboxlive.com/xsts/authorize", xstsReq, true);
            System.out.println("MicrosoftLogin: xsts response: " + xstsResp);
            XblXstsResponse xstsRes = (XblXstsResponse)gson.fromJson(xstsResp, XblXstsResponse.class);
            if (xstsRes == null) {
               System.out.println("MicrosoftLogin: failed to parse XSTS response");
               return new LoginData();
            } else {
               String mcReq = "{\"identityToken\":\"XBL3.0 x=" + xblRes.DisplayClaims.xui[0].uhs + ";" + xstsRes.Token + "\"}";
               String mcResp = BrowserUtil.postExternal("https://api.minecraftservices.com/authentication/login_with_xbox", mcReq, true);
               System.out.println("MicrosoftLogin: minecraft login response: " + mcResp);
               McResponse mcRes = (McResponse)gson.fromJson(mcResp, McResponse.class);
               if (mcRes == null) {
                  System.out.println("MicrosoftLogin: failed to parse Minecraft login response");
                  return new LoginData();
               } else {
                  String ownershipResp = BrowserUtil.getBearerResponse("https://api.minecraftservices.com/entitlements/mcstore", mcRes.access_token);
                  System.out.println("MicrosoftLogin: ownership response: " + ownershipResp);
                  GameOwnershipResponse gameOwnershipRes = (GameOwnershipResponse)gson.fromJson(ownershipResp, GameOwnershipResponse.class);
                  if (gameOwnershipRes != null && gameOwnershipRes.hasGameOwnership()) {
                     String profileResp = BrowserUtil.getBearerResponse("https://api.minecraftservices.com/minecraft/profile", mcRes.access_token);
                     System.out.println("MicrosoftLogin: profile response: " + profileResp);
                     ProfileResponse profileRes = (ProfileResponse)gson.fromJson(profileResp, ProfileResponse.class);
                     if (profileRes == null) {
                        System.out.println("MicrosoftLogin: failed to parse profile response");
                        return new LoginData();
                     } else {
                        return new LoginData(mcRes.access_token, refreshToken, profileRes.id, profileRes.name);
                     }
                  } else {
                     System.out.println("MicrosoftLogin: no game ownership");
                     return new LoginData();
                  }
               }
            }
         }
      }
   }

   private static void startServer() {
      if (server == null) {
         try {
            (server = HttpServer.create(new InetSocketAddress("localhost", 8247), 0)).createContext("/", new Handler());
            server.setExecutor(executor);
            server.start();
            System.out.println("MicrosoftLogin: local callback server started on http://localhost:8247/");
         } catch (IOException var1) {
            var1.printStackTrace();
         }

      }
   }

   private static void stopServer() {
      if (server != null) {
         server.stop(0);
         server = null;
         callback = null;
      }
   }

   private static class AuthTokenResponse {
      @Expose
      @SerializedName("access_token")
      public String access_token;
      @Expose
      @SerializedName("refresh_token")
      public String refresh_token;
   }

   public static class LoginData {
      public String mcToken;
      public String newRefreshToken;
      public String uuid;
      public String username;

      public LoginData() {
      }

      public LoginData(String mcToken, String newRefreshToken, String uuid, String username) {
         this.mcToken = mcToken;
         this.newRefreshToken = newRefreshToken;
         this.uuid = uuid;
         this.username = username;
      }

      public boolean isGood() {
         return this.mcToken != null;
      }
   }

   private static class XblXstsResponse {
      @Expose
      @SerializedName("Token")
      public String Token;
      @Expose
      @SerializedName("DisplayClaims")
      public DisplayClaims DisplayClaims;

      private static class DisplayClaims {
         @Expose
         @SerializedName("xui")
         private Claim[] xui;

         private static class Claim {
            @Expose
            @SerializedName("uhs")
            private String uhs;
         }
      }
   }

   private static class McResponse {
      @Expose
      @SerializedName("access_token")
      public String access_token;
   }

   private static class GameOwnershipResponse {
      @Expose
      @SerializedName("items")
      private Item[] items;

      private boolean hasGameOwnership() {
         boolean hasProduct = false;
         boolean hasGame = false;
         Item[] var3 = this.items;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Item item = var3[var5];
            if (item.name.equals("product_minecraft")) {
               hasProduct = true;
            } else if (item.name.equals("game_minecraft")) {
               hasGame = true;
            }
         }

         return hasProduct && hasGame;
      }

      private static class Item {
         @Expose
         @SerializedName("name")
         private String name;
      }
   }

   private static class ProfileResponse {
      @Expose
      @SerializedName("id")
      public String id;
      @Expose
      @SerializedName("name")
      public String name;
   }

   private static class Handler implements HttpHandler {
      public void handle(HttpExchange req) throws IOException {
         if (req.getRequestMethod().equals("GET")) {
            List query = URLEncodedUtils.parse(req.getRequestURI(), StandardCharsets.UTF_8.name());
            boolean ok = false;
            Iterator var4 = query.iterator();

            while(var4.hasNext()) {
               NameValuePair pair = (NameValuePair)var4.next();
               if (pair.getName().equals("code")) {
                  try {
                     this.handleCode(pair.getValue());
                     this.writeText(req, "<html>You may now close this page.<script>close()</script></html>");
                  } catch (Exception var7) {
                     var7.printStackTrace();
                     this.writeText(req, "<html>Authentication failed: " + var7.getMessage() + "</html>");
                  }

                  ok = true;
                  break;
               }
            }

            if (!ok) {
               String queryStr = req.getRequestURI().getQuery();
               System.out.println("MicrosoftLogin: callback without code, query=" + queryStr);
               this.writeText(req, "<html>Cannot authenticate. Query: " + (queryStr == null ? "" : queryStr) + "</html>");
            }
         }

         MicrosoftLogin.stopServer();
      }

      private void handleCode(String code) {
         String response = BrowserUtil.postExternal("https://login.live.com/oauth20_token.srf", "client_id=9fbc7315-7200-4b2b-a655-bb38c865da17&code=" + code + "&client_secret=Bzn8Q~YryydJsydgnnxHgJq.NM3Oo4.AEEohLbBb&grant_type=authorization_code&redirect_uri=http://localhost:8247", false);
         AuthTokenResponse res = (AuthTokenResponse)MicrosoftLogin.gson.fromJson(response, AuthTokenResponse.class);
         if (res == null) {
            MicrosoftLogin.callback.accept((Object)null);
         } else {
            MicrosoftLogin.callback.accept(res.refresh_token);
         }

      }

      private void writeText(HttpExchange req, String text) throws IOException {
         OutputStream out = req.getResponseBody();
         req.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
         req.sendResponseHeaders(200, (long)text.length());
         out.write(text.getBytes(StandardCharsets.UTF_8));
         out.flush();
         out.close();
      }
   }
}
