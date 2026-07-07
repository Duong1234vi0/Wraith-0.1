package unique.core.features.modules.player;

import net.minecraft.class_124;
import net.minecraft.class_418;
import unique.core.core.Managers;
import unique.core.core.manager.world.WayPointManager;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;

public class AutoRespawn extends Module {
   private final Setting<Boolean> deathcoords = new Setting<>("Deathcoords", true);
   private final Setting<Boolean> autokit = new Setting<>("Auto Kit", false);
   private final Setting<String> kit = new Setting<>("kit name", "Kitname", v -> this.autokit.getValue());
   private final Setting<Boolean> autohome = new Setting<>("Auto Home", false);
   private final Setting<Boolean> autowaypoint = new Setting<>("Auto Waypoint", false);
   private boolean flag;
   private int waypointCount = 0;
   private final Timer timer = new Timer();

   public AutoRespawn() {
      super("AutoRespawn", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (!fullNullCheck()) {
         if (this.timer.passedMs(2100L)) {
            this.timer.reset();
         }

         if (mc.field_1755 instanceof class_418) {
            if (this.flag) {
               this.waypointCount++;
               if (this.deathcoords.getValue()) {
                  this.sendMessage(
                     class_124.field_1065
                        + "[PlayerDeath] "
                        + class_124.field_1054
                        + (int)mc.field_1724.method_23317()
                        + " "
                        + (int)mc.field_1724.method_23318()
                        + " "
                        + (int)mc.field_1724.method_23321()
                  );
               }

               if (this.autowaypoint.getValue()) {
                  WayPointManager.WayPoint wp = new WayPointManager.WayPoint(
                     (int)mc.field_1724.method_23317(),
                     (int)mc.field_1724.method_23318(),
                     (int)mc.field_1724.method_23321(),
                     "Death №" + this.waypointCount,
                     mc.method_1542() ? "SinglePlayer" : mc.method_1562().method_45734().field_3761,
                     mc.field_1687.method_27983().method_29177().method_12832()
                  );
                  Managers.WAYPOINT.addWayPoint(wp);
               }

               mc.field_1724.method_7331();
               mc.method_1507(null);
               Managers.ASYNC.run(() -> {
                  if (this.autokit.getValue() && mc.field_1724 != null) {
                     mc.field_1724.field_3944.method_45730("kit " + this.kit.getValue());
                  }

                  if (this.autohome.getValue() && mc.field_1724 != null) {
                     mc.field_1724.field_3944.method_45730("home");
                  }
               }, 1000L);
               this.flag = false;
            }
         } else {
            this.flag = true;
         }
      }
   }
}
