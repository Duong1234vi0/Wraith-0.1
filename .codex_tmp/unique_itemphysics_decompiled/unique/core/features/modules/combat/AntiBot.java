package unique.core.features.modules.combat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_1297.class_5529;
import unique.core.events.impl.EventEntityRemoved;
import unique.core.events.impl.EventEntitySpawn;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;

public class AntiBot extends Module {
   private final Setting<AntiBot.Mode> mode = new Setting<>("Mode", AntiBot.Mode.Matrix);
   private final Setting<Boolean> enabledRemove = new Setting<>("Remove", true);
   private final Setting<Boolean> onlyAura = new Setting<>("OnlyAura", false);
   public static List<class_1657> bots = new CopyOnWriteArrayList<>();
   public static Set<UUID> botIds = ConcurrentHashMap.newKeySet();
   private final Map<UUID, Integer> pendingChecks = new HashMap<>();
   private static final int MAX_SPAWN_CHECKS = 100;

   public AntiBot() {
      super("AntiBot", Module.Category.COMBAT);
   }

   @Override
   public void onEnable() {
      if (mc.field_1687 != null) {
         bots.clear();
         botIds.clear();
         this.pendingChecks.clear();

         for (class_1657 player : mc.field_1687.method_18456()) {
            this.detect(player);
         }
      }
   }

   @EventHandler
   public void onSync(EventSync event) {
      if (this.mode.getValue() == AntiBot.Mode.Matrix) {
         if (mc.field_1687 != null) {
            if (!this.pendingChecks.isEmpty()) {
               for (class_1657 player : mc.field_1687.method_18456()) {
                  UUID uuid = player.method_5667();
                  if (this.pendingChecks.containsKey(uuid)) {
                     if (this.isBot(player)) {
                        this.addBot(player);
                        this.pendingChecks.remove(uuid);
                     } else {
                        Integer checks = this.pendingChecks.get(uuid);
                        if (checks != null) {
                           if (checks > 100) {
                              this.pendingChecks.remove(uuid);
                           } else {
                              this.pendingChecks.put(uuid, checks + 1);
                           }
                        }
                     }
                  }
               }
            }

            if (this.enabledRemove.getValue()) {
               for (class_1657 bot : bots) {
                  mc.field_1687.method_2945(bot.method_5628(), class_5529.field_26999);
               }
            }
         }
      }
   }

   @EventHandler
   public void onEntitySpawn(EventEntitySpawn event) {
      if (this.mode.getValue() == AntiBot.Mode.Matrix) {
         if (event.getEntity() instanceof class_1657 player) {
            this.detect(player);
         }
      }
   }

   @EventHandler
   public void onEntityRemoved(EventEntityRemoved event) {
      if (event.getEntity() instanceof class_1657 player) {
         bots.removeIf(bot -> bot.method_5667().equals(player.method_5667()));
         botIds.remove(player.method_5667());
         this.pendingChecks.remove(player.method_5667());
      }
   }

   private void detect(class_1657 player) {
      if (player != null && player != mc.field_1724) {
         if (!botIds.contains(player.method_5667())) {
            if (this.mode.getValue() == AntiBot.Mode.Matrix) {
               boolean hasArmor = !player.method_6118(class_1304.field_6169).method_7960()
                  || !player.method_6118(class_1304.field_6174).method_7960()
                  || !player.method_6118(class_1304.field_6172).method_7960()
                  || !player.method_6118(class_1304.field_6166).method_7960();
               boolean hasHandItem = !player.method_6118(class_1304.field_6173).method_7960() || !player.method_6118(class_1304.field_6171).method_7960();
               if (!hasArmor && !hasHandItem) {
                  this.pendingChecks.put(player.method_5667(), 0);
               }
            }
         }
      }
   }

   private void addBot(class_1657 player) {
      if (!bots.contains(player)) {
         bots.add(player);
         botIds.add(player.method_5667());
         if (mc.field_1724 != null) {
            mc.field_1724.method_7353(class_2561.method_43470(player.method_5477().getString() + " is a bot (Matrix)!"), false);
         }
      }
   }

   public boolean isBot(class_1297 entity) {
      if (entity == null) {
         return false;
      } else if (botIds.contains(entity.method_5667())) {
         return true;
      } else if (!this.onlyAura.getValue()) {
         return entity instanceof class_1657 player ? bots.contains(player) : false;
      } else {
         return Aura.target != null && Aura.target.equals(entity);
      }
   }

   @Override
   public String getDisplayInfo() {
      return String.valueOf(bots.size());
   }

   public static enum Mode {
      Matrix;
   }
}
