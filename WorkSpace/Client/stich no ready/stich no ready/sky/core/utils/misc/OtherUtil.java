package sky.core.utils.misc;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Iterator;
import java.util.Locale;
import mods.viaversion.vialoadingbase.ViaLoadingBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector2f;
import sky.core.utils.Wrapper;

public class OtherUtil {
   public static Vector2f getMouse(int mouseX, int mouseY) {
      return OtherUtil.GuiUtils.getMouse((float)mouseX, (float)mouseY);
   }

   public static String getPlayerCoordinates() {
      return OtherUtil.PlayerUtils.getPlayerCoordinates();
   }

   public static String calculateBPS() {
      return OtherUtil.PlayerUtils.calculateBPS();
   }

   public static int calculatePing() {
      return OtherUtil.PlayerUtils.calculatePing();
   }

   public static boolean ViaCheck(ProtocolVersion protocolVersion) {
      if (!ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(protocolVersion)) {
         return false;
      } else {
         Iterator var1 = Via.getManager().getConnectionManager().getConnections().iterator();

         UserConnection conn;
         do {
            if (!var1.hasNext()) {
               return false;
            }

            conn = (UserConnection)var1.next();
            if (conn == null) {
               return false;
            }
         } while(!conn.getProtocolInfo().getUsername().equalsIgnoreCase(Wrapper.mc.session.getProfile().getName()));

         return true;
      }
   }

   public static class GuiUtils {
      public static Vector2f getMouse(float mouseX, float mouseY) {
         double scale = Wrapper.mw.getGuiScaleFactor() / 2.0;
         return new Vector2f((float)((double)mouseX * scale), (float)((double)mouseY * scale));
      }
   }

   public static class PlayerUtils {
      private static int cachedPing = 0;
      private static long lastUpdateTime = 0L;

      public static String getPlayerCoordinates() {
         Minecraft var10000 = Wrapper.mc;
         PlayerEntity player = Minecraft.player;
         if (player != null) {
            int posX = (int)player.getPosX();
            int posY = (int)player.getPosY();
            int posZ = (int)player.getPosZ();
            return String.format("%d %d %d", posX, posY, posZ);
         } else {
            return "";
         }
      }

      public static String calculateBPS() {
         Minecraft var10000 = Wrapper.mc;
         PlayerEntity player = Minecraft.player;
         double deltaX = player.getPosX() - player.prevPosX;
         double deltaZ = player.getPosZ() - player.prevPosZ;
         double deltaY = player.getPosY() - player.prevPosY;
         double distance = Math.hypot(deltaX, deltaZ);
         distance = Math.hypot(distance, deltaY);
         return String.format(Locale.US, "%.1f", distance * 20.0);
      }

      public static int calculatePing() {
         long currentTime = System.currentTimeMillis();
         if (currentTime - lastUpdateTime > 100L) {
            label14: {
               if (Wrapper.mc.getConnection() != null) {
                  ClientPlayNetHandler var10000 = Wrapper.mc.getConnection();
                  Minecraft var10001 = Wrapper.mc;
                  if (var10000.getPlayerInfo(Minecraft.player.getUniqueID()) != null) {
                     var10000 = Wrapper.mc.getConnection();
                     var10001 = Wrapper.mc;
                     cachedPing = var10000.getPlayerInfo(Minecraft.player.getUniqueID()).getResponseTime();
                     break label14;
                  }
               }

               cachedPing = 0;
            }

            lastUpdateTime = currentTime;
         }

         return cachedPing;
      }
   }
}
