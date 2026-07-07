package unique.core.core.manager.player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_2663;
import net.minecraft.class_3532;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import unique.core.UniqueCore;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.events.impl.EventPostTick;
import unique.core.events.impl.PacketEvent;
import unique.core.events.impl.TotemPopEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.AntiBot;

public class CombatManager implements IManager {
   public HashMap<String, Integer> popList = new HashMap<>();

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!Module.fullNullCheck()) {
         if (event.getPacket() instanceof class_2663 pac && pac.method_11470() == 35) {
            class_1297 ent = pac.method_11469(mc.field_1687);
            if (!(ent instanceof class_1657)) {
               return;
            }

            if (this.popList == null) {
               this.popList = new HashMap<>();
            }

            if (this.popList.get(ent.method_5477().getString()) == null) {
               this.popList.put(ent.method_5477().getString(), 1);
            } else if (this.popList.get(ent.method_5477().getString()) != null) {
               this.popList.put(ent.method_5477().getString(), this.popList.get(ent.method_5477().getString()) + 1);
            }

            UniqueCore.EVENT_BUS.post(new TotemPopEvent((class_1657)ent, this.popList.get(ent.method_5477().getString())));
         }
      }
   }

   @EventHandler
   public void onPostTick(EventPostTick event) {
      if (!Module.fullNullCheck()) {
         for (class_1657 player : mc.field_1687.method_18456()) {
            if (!AntiBot.bots.contains(player) && player.method_6032() <= 0.0F && this.popList.containsKey(player.method_5477().getString())) {
               this.popList.remove(player.method_5477().getString(), this.popList.get(player.method_5477().getString()));
            }
         }
      }
   }

   public int getPops(@NotNull class_1657 entity) {
      return this.popList.get(entity.method_5477().getString()) == null ? 0 : this.popList.get(entity.method_5477().getString());
   }

   public List<class_1657> getTargets(float range) {
      return mc.field_1687
         .method_18456()
         .stream()
         .filter(e -> !e.method_29504())
         .filter(entityPlayer -> !Managers.FRIEND.isFriend(entityPlayer.method_5477().getString()))
         .filter(entityPlayer -> entityPlayer != mc.field_1724)
         .filter(entityPlayer -> mc.field_1724.method_5858(entityPlayer) < range * range)
         .sorted(Comparator.comparing(e -> mc.field_1724.method_5858(e)))
         .collect(Collectors.toList());
   }

   @Nullable
   public class_1657 getTarget(float range, @NotNull CombatManager.TargetBy targetBy) {
      class_1657 target = null;
      switch (targetBy) {
         case Distance:
            target = this.getNearestTarget(range);
            break;
         case FOV:
            target = this.getTargetByFOV(range);
            break;
         case Health:
            target = this.getTargetByHealth(range);
      }

      return target;
   }

   @Nullable
   public class_1657 getNearestTarget(float range) {
      return this.getTargets(range).stream().min(Comparator.comparing(t -> mc.field_1724.method_5739(t))).orElse(null);
   }

   public class_1657 getTargetByHealth(float range) {
      return this.getTargets(range).stream().min(Comparator.comparing(t -> t.method_6032() + t.method_6067())).orElse(null);
   }

   public class_1657 getTargetByFOV(float range) {
      return this.getTargets(range).stream().min(Comparator.comparing(this::getFOVAngle)).orElse(null);
   }

   public class_1657 getTargetByFOV(float range, float fov) {
      return this.getTargets(range)
         .stream()
         .filter(entityPlayer -> this.getFOVAngle(entityPlayer) < fov)
         .min(Comparator.comparing(this::getFOVAngle))
         .orElse(null);
   }

   @Nullable
   public class_1657 getTarget(float range, @NotNull CombatManager.TargetBy targetBy, @NotNull Predicate<class_1657> predicate) {
      class_1657 target = null;
      switch (targetBy) {
         case Distance:
            target = this.getNearestTarget(range, predicate);
            break;
         case FOV:
            target = this.getTargetByFOV(range, predicate);
            break;
         case Health:
            target = this.getTargetByHealth(range, predicate);
      }

      return target;
   }

   @Nullable
   public class_1657 getNearestTarget(float range, Predicate<class_1657> predicate) {
      return this.getTargets(range).stream().filter(predicate).min(Comparator.comparing(t -> mc.field_1724.method_5739(t))).orElse(null);
   }

   public class_1657 getTargetByHealth(float range, Predicate<class_1657> predicate) {
      return this.getTargets(range).stream().filter(predicate).min(Comparator.comparing(t -> t.method_6032() + t.method_6067())).orElse(null);
   }

   public class_1657 getTargetByFOV(float range, Predicate<class_1657> predicate) {
      return this.getTargets(range).stream().filter(predicate).min(Comparator.comparing(this::getFOVAngle)).orElse(null);
   }

   private float getFOVAngle(@NotNull class_1309 e) {
      float yaw = (float)class_3532.method_15338(
         Math.toDegrees(Math.atan2(e.method_23321() - mc.field_1724.method_23321(), e.method_23317() - mc.field_1724.method_23317())) - 90.0
      );
      return Math.abs(yaw - class_3532.method_15393(mc.field_1724.method_36454()));
   }

   public static enum TargetBy {
      Distance,
      FOV,
      Health;
   }
}
