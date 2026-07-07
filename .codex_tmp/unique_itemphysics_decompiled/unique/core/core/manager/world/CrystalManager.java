package unique.core.core.manager.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1511;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;

public class CrystalManager implements IManager {
   private final Map<Integer, Long> deadCrystals = new ConcurrentHashMap<>();
   private final Map<Integer, CrystalManager.Attempt> attackedCrystals = new ConcurrentHashMap<>();
   private final Map<class_2338, CrystalManager.Attempt> awaitingPositions = new ConcurrentHashMap<>();

   public void onAttack(class_1511 crystal) {
      this.setDead(crystal.method_5628(), System.currentTimeMillis());
      this.addAttack(crystal);
   }

   public void reset() {
      this.deadCrystals.clear();
      this.attackedCrystals.clear();
      this.awaitingPositions.clear();
   }

   public void update() {
      long time = System.currentTimeMillis();
      this.deadCrystals.entrySet().removeIf(entry -> time - entry.getValue() > Managers.SERVER.getPing() * 2L);
      this.attackedCrystals.entrySet().removeIf(entry -> entry.getValue().shouldRemove());
      this.awaitingPositions.entrySet().removeIf(entry -> entry.getValue().shouldRemove());
   }

   public boolean isDead(Integer id) {
      return this.deadCrystals.containsKey(id);
   }

   public void setDead(Integer id, long deathTime) {
      this.deadCrystals.putIfAbsent(id, deathTime);
   }

   public boolean isBlocked(Integer id) {
      return this.attackedCrystals.containsKey(id) && this.attackedCrystals.get(id).canSetPosBlocked();
   }

   public void addAttack(class_1511 entity) {
      this.attackedCrystals.compute(entity.method_5628(), (pos, attempt) -> {
         if (attempt == null) {
            return new CrystalManager.Attempt(System.currentTimeMillis(), 1, entity.method_19538());
         } else {
            if (ModuleManager.autoCrystal.breakFailsafe.getValue()) {
               attempt.addAttempt();
            }

            return (CrystalManager.Attempt)attempt;
         }
      });
   }

   public Map<class_2338, CrystalManager.Attempt> getAwaitingPositions() {
      return this.awaitingPositions;
   }

   public void confirmSpawn(class_2338 bp) {
      this.awaitingPositions.remove(bp);
   }

   public void addAwaitingPos(class_2338 blockPos) {
      boolean blocked = ModuleManager.autoCrystal.isPositionBlockedByCrystal(blockPos.method_10084());
      this.awaitingPositions.compute(blockPos, (pos, attempt) -> {
         if (attempt == null) {
            return new CrystalManager.Attempt(System.currentTimeMillis(), 1, blockPos.method_46558());
         } else {
            if (!blocked && ModuleManager.autoCrystal.placeFailsafe.getValue()) {
               attempt.addAttempt();
            }

            return (CrystalManager.Attempt)attempt;
         }
      });
   }

   public boolean isPositionBlocked(class_2338 bp) {
      return this.awaitingPositions.containsKey(bp) && this.awaitingPositions.get(bp).canSetPosBlocked();
   }

   public class Attempt {
      long time;
      int attempts;
      float distance;
      public class_243 pos;

      Attempt(long time, int attempts, class_243 pos) {
         this.time = time;
         this.pos = pos;
         this.attempts = attempts;
         this.distance = (float)IManager.mc.field_1724.method_5707(pos);
      }

      public class_243 getPos() {
         return this.pos;
      }

      public long getTime() {
         return this.time;
      }

      public float getDistance() {
         return this.distance;
      }

      public boolean shouldRemove() {
         return Math.abs(this.distance - IManager.mc.field_1724.method_5707(this.pos)) >= 1.0;
      }

      public void addAttempt() {
         this.attempts++;
      }

      public boolean canSetPosBlocked() {
         return this.attempts >= Math.max((float)ModuleManager.autoCrystal.attempts.getValue().intValue(), Managers.SERVER.getPing() / 25.0F);
      }
   }
}
