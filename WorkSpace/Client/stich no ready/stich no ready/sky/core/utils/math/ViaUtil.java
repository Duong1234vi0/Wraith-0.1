package sky.core.utils.math;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Iterator;
import mods.viaversion.vialoadingbase.ViaLoadingBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import sky.core.utils.Wrapper;

public final class ViaUtil implements Wrapper {
   public static boolean allowedBypass() {
      if (!ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         return false;
      } else {
         Iterator var0 = Via.getManager().getConnectionManager().getConnections().iterator();

         UserConnection conn;
         do {
            if (!var0.hasNext()) {
               return false;
            }

            conn = (UserConnection)var0.next();
            if (conn == null) {
               return false;
            }
         } while(!conn.getProtocolInfo().getUsername().equalsIgnoreCase(mc.session.getProfile().getName()));

         return true;
      }
   }

   public static void sendPositionPacket(double x, double y, double z, float yaw, float pitch, boolean onGround) {
      Minecraft var10000 = mc;
      Minecraft.player.connection.sendPacket(new CPlayerPacket.PositionRotationPacket(x, y, z, yaw, pitch, onGround));
   }

   public static void sendPositionPacket(float yaw, float pitch, boolean sendUseItemPacket) {
      Minecraft var10000 = mc;
      ClientPlayNetHandler var4 = Minecraft.player.connection;
      Minecraft var10003 = mc;
      double var5 = Minecraft.player.getPosX();
      Minecraft var10004 = mc;
      double var6 = Minecraft.player.getPosY();
      Minecraft var10005 = mc;
      double var3 = Minecraft.player.getPosZ();
      Minecraft var10008 = mc;
      var4.sendPacket(new CPlayerPacket.PositionRotationPacket(var5, var6, var3, yaw, pitch, Minecraft.player.isOnGround()));
      if (sendUseItemPacket) {
         sendUseItemPacket(true);
      }

   }

   public static void sendPositionPacket() {
      Minecraft var10000 = mc;
      ClientPlayNetHandler var1 = Minecraft.player.connection;
      Minecraft var10003 = mc;
      double var2 = Minecraft.player.getPosX();
      Minecraft var10004 = mc;
      double var3 = Minecraft.player.getPosY();
      Minecraft var10005 = mc;
      double var0 = Minecraft.player.getPosZ();
      Minecraft var10006 = mc;
      Minecraft var10007 = mc;
      Minecraft var10008 = mc;
      var1.sendPacket(new CPlayerPacket.PositionRotationPacket(var2, var3, var0, Minecraft.player.rotationYaw, Minecraft.player.rotationPitch, Minecraft.player.isOnGround()));
   }

   public static void sendUseItemPacket(boolean checkHandActive) {
      Minecraft var10000;
      if (checkHandActive) {
         var10000 = mc;
         if (Minecraft.player.isHandActive()) {
            return;
         }
      }

      var10000 = mc;
      Minecraft.player.connection.sendPacketWithoutEvent(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
   }

   private ViaUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
