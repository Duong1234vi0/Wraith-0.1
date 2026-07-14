package sky.core.utils.misc;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SUpdateBossInfoPacket;
import net.minecraft.network.play.server.SUpdateBossInfoPacket.Operation;
import net.minecraft.scoreboard.Score;
import net.minecraft.util.StringUtils;
import sky.core.utils.Wrapper;

public class ServerUtil implements Wrapper {
   public static boolean isPvP() {
      return ServerUtil.BossInfoHandler.pvpMode;
   }

   public static float getHealth(LivingEntity entity) {
      if (entity instanceof PlayerEntity p) {
         Iterator iterator;
         if (mc.world != null && (iterator = mc.world.getScoreboard().getObjectivesForEntity(p.getGameProfile().getName()).entrySet().iterator()).hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            Score score = (Score)entry.getValue();
            return (float)score.getScorePoints();
         }
      }

      return (float)((int)(entity.getHealth() + entity.getAbsorptionAmount()));
   }

   public static boolean isConnectedToServer(String ip) {
      return mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP != null && mc.getCurrentServerData().serverIP.contains(ip);
   }

   public static class BossInfoHandler {
      private static boolean pvpMode = false;
      private static UUID uuid = null;

      public static void updateBossInfo(SUpdateBossInfoPacket packet) {
         if (packet.getOperation() == Operation.ADD) {
            String name = StringUtils.stripControlCodes(packet.getName().getString()).toLowerCase();
            if (name.contains("pvp") || name.contains("у вас осталось")) {
               pvpMode = true;
               uuid = packet.getUniqueId();
            }
         } else if (packet.getOperation() == Operation.REMOVE && packet.getUniqueId().equals(uuid)) {
            pvpMode = false;
            uuid = null;
         }

      }

      public static boolean isPvP() {
         return pvpMode;
      }
   }
}
