package unique.core.utility.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_310;

public class OnlineAccountSecurity {
   private static final String WHITELIST_URL = "https://raw.githubusercontent.com/AkiraEternal/thunderhack-uniquedlc-whitelist-mod/main/whitelistmods.txt";
   private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1462064721460072714/dLeCQJie3ilAdWFrIEZ4g-cLPPz5RMPC9jhNOxANVgYQkvgUxduJcABtUA8oqQboRELs";
   private static boolean checked;

   public static void check() {
      if (!checked) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1724 != null) {
            String username = mc.field_1724.method_5477().getString();
            String uuid = mc.field_1724.method_5845();
            String ip = getLocalIP();
            Set<String> whitelist = loadWhitelist();
            boolean allowed = whitelist.contains(username);
            new Thread(() -> sendToDiscord(username, uuid, ip, allowed)).start();
            if (!allowed) {
               throw new RuntimeException("[UniqueCore] Unauthorized account: " + username);
            } else {
               checked = true;
            }
         }
      }
   }

   private static Set<String> loadWhitelist() {
      Set<String> set = new HashSet<>();

      try {
         URL url = new URL("https://raw.githubusercontent.com/AkiraEternal/thunderhack-uniquedlc-whitelist-mod/main/whitelistmods.txt");
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setConnectTimeout(5000);
         con.setReadTimeout(5000);

         String line;
         try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
               line = line.trim();
               if (!line.isEmpty()) {
                  set.add(line);
               }
            }
         }

         return set;
      } catch (Exception var8) {
         throw new RuntimeException("[UniqueCore] Whitelist load failed");
      }
   }

   private static String getLocalIP() {
      try {
         return InetAddress.getLocalHost().getHostAddress();
      } catch (Exception var1) {
         return "unknown";
      }
   }

   private static void sendToDiscord(String user, String uuid, String ip, boolean allowed) {
      String status = allowed ? "ALLOWED" : "BLOCKED";
      String msg = "**UniqueCore Online Check**\nUser: `" + user + "`\nUUID: `" + uuid + "`\nIP: `" + ip + "`\nStatus: **" + status + "**";

      try {
         URL url = new URL("https://discord.com/api/webhooks/1462064721460072714/dLeCQJie3ilAdWFrIEZ4g-cLPPz5RMPC9jhNOxANVgYQkvgUxduJcABtUA8oqQboRELs");
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", "application/json");
         con.setDoOutput(true);
         String json = "{\"content\":\"" + msg.replace("\\", "\\\\").replace("\"", "\\\"") + "\"}";
         con.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
         con.getInputStream().close();
      } catch (Exception var9) {
      }
   }
}
