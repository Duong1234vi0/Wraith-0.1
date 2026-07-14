package sky.core.ui.altmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class AltConfig {
   private static final File DIR;
   private static final File FILE;

   public static void init(List accounts) {
      try {
         if (!DIR.exists()) {
            DIR.mkdirs();
         }

         if (!FILE.exists()) {
            FILE.createNewFile();
            return;
         }

         read(accounts);
      } catch (Exception var2) {
      }

   }

   public static void updateFile(List accounts) {
      try {
         if (!DIR.exists()) {
            DIR.mkdirs();
         }

         StringBuilder builder = new StringBuilder();
         Iterator var2 = accounts.iterator();

         while(var2.hasNext()) {
            Account alt = (Account)var2.next();
            builder.append(alt.accountName).append(":").append(alt.dateAdded).append("\n");
         }

         Files.write(FILE.toPath(), builder.toString().getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
      } catch (Exception var4) {
      }

   }

   private static void read(List accounts) {
      try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE), StandardCharsets.UTF_8));

         try {
            while(true) {
               String line;
               if ((line = reader.readLine()) == null) {
                  scheduleSkinLoads(accounts);
                  break;
               }

               if (!line.isEmpty() && line.contains(":")) {
                  String[] parts = line.split(":");
                  if (parts.length >= 2) {
                     String username = parts[0].trim();
                     if (!username.isEmpty()) {
                        long date = System.currentTimeMillis();

                        try {
                           date = Long.parseLong(parts[1].trim());
                        } catch (Exception var9) {
                        }

                        accounts.add(new Account(username, date));
                     }
                  }
               }
            }
         } catch (Throwable var10) {
            try {
               reader.close();
            } catch (Throwable var8) {
               var10.addSuppressed(var8);
            }

            throw var10;
         }

         reader.close();
      } catch (Exception var11) {
      }

   }

   private static void scheduleSkinLoads(List accounts) {
      if (!accounts.isEmpty()) {
         (new Thread(() -> {
            Account account;
            for(Iterator var1 = accounts.iterator(); var1.hasNext(); account.loadSkinAsync()) {
               account = (Account)var1.next();

               try {
                  Thread.sleep(450L);
               } catch (InterruptedException var4) {
                  Thread.currentThread().interrupt();
                  return;
               }
            }

         }, "AltManager-SkinLoader")).start();
      }
   }

   static {
      DIR = new File(Minecraft.getInstance().gameDir, "skycore");
      FILE = new File(DIR, "alts.txt");
   }
}
