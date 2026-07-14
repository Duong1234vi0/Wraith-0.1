package sky.core.ui.altmanager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import sky.core.utils.other.AsyncManager;

public class Account {
   public String accountName;
   public long dateAdded;
   public ResourceLocation skin;

   public Account(String accountName) {
      this(accountName, System.currentTimeMillis());
   }

   public Account(String accountName, long dateAdded) {
      this.accountName = "";
      this.accountName = accountName;
      this.dateAdded = dateAdded;
      UUID offline = UUID.nameUUIDFromBytes(("OfflinePlayer:" + accountName).getBytes(StandardCharsets.UTF_8));
      this.skin = DefaultPlayerSkin.getDefaultSkin(offline);
   }

   public void loadSkinAsync() {
      UUID offline = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.accountName).getBytes(StandardCharsets.UTF_8));
      AsyncManager.run(() -> {
         UUID resolved;
         try {
            resolved = resolveUUID(this.accountName);
         } catch (IOException var5) {
            resolved = offline;
         }

         Minecraft mc = Minecraft.getInstance();
         if (mc != null) {
            mc.execute(() -> {
               mc.getSkinManager().loadProfileTextures(new GameProfile(resolved, this.accountName), (type, loc, tex) -> {
                  if (type == Type.SKIN) {
                     this.skin = loc;
                  }

               }, false);
            });
         }
      });
   }

   public static UUID resolveUUID(String name) throws IOException {
      try {
         InputStreamReader in = new InputStreamReader((new URL("https://api.mojang.com/users/profiles/minecraft/" + name)).openStream(), StandardCharsets.UTF_8);

         UUID var9;
         label37: {
            UUID var5;
            try {
               JsonObject json = (JsonObject)(new Gson()).fromJson(in, JsonObject.class);
               if (json == null || !json.has("id")) {
                  var9 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
                  break label37;
               }

               String raw = json.get("id").getAsString();
               String dashed = raw.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
               var5 = UUID.fromString(dashed);
            } catch (Throwable var7) {
               try {
                  in.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }

               throw var7;
            }

            in.close();
            return var5;
         }

         in.close();
         return var9;
      } catch (Throwable var8) {
         return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
      }
   }
}
