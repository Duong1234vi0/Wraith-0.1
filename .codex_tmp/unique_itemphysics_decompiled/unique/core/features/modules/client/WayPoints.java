package unique.core.features.modules.client;

import net.minecraft.class_124;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.joml.Vector4d;
import unique.core.core.Managers;
import unique.core.core.manager.world.WayPointManager;
import unique.core.features.modules.Module;
import unique.core.gui.font.FontRenderers;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.TextureStorage;

public final class WayPoints extends Module {
   public WayPoints() {
      super("WayPoints", Module.Category.CLIENT);
   }

   @Override
   public void onEnable() {
      this.sendMessage(Managers.COMMAND.getPrefix() + "waypoint add x y z name");
   }

   @Override
   public void onRender2D(class_332 context) {
      if (!Managers.WAYPOINT.getWayPoints().isEmpty() && !fullNullCheck()) {
         for (WayPointManager.WayPoint wp : Managers.WAYPOINT.getWayPoints()) {
            if (wp.getName() != null
               && (!mc.method_1542() || !wp.getServer().equals("SinglePlayer"))
               && (mc.method_1562().method_45734() == null || mc.method_1562().method_45734().field_3761.contains(wp.getServer()))
               && mc.field_1687.method_27983().method_29177().method_12832().equals(wp.getDimension())) {
               double difX = wp.getX() - mc.field_1724.method_19538().field_1352;
               double difZ = wp.getZ() - mc.field_1724.method_19538().field_1350;
               float yaw = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0);
               double plYaw = class_3532.method_15393(mc.field_1724.method_36454());
               if (!(Math.abs(yaw - plYaw) > 90.0)) {
                  class_243 vector = new class_243(wp.getX(), wp.getY(), wp.getZ());
                  Vector4d position = null;
                  vector = Render3DEngine.worldSpaceToScreenSpace(new class_243(vector.field_1352, vector.field_1351, vector.field_1350));
                  position = new Vector4d(vector.field_1352, vector.field_1351, vector.field_1350, 0.0);
                  position.x = Math.min(vector.field_1352, position.x);
                  position.y = Math.min(vector.field_1351, position.y);
                  position.z = Math.max(vector.field_1352, position.z);
                  double posX = position.x;
                  double posY = position.y;
                  double endPosX = position.z;
                  float diff = (float)(endPosX - posX) / 2.0F;
                  float tagX = (float)((posX + diff - FontRenderers.sf_bold_mini.getStringWidth(wp.getName()) / 2.0F) * 1.0);
                  String coords = wp.getX() + " " + wp.getZ();
                  float tagX2 = (float)((posX + diff - FontRenderers.sf_bold_mini.getStringWidth(coords) / 2.0F) * 1.0);
                  String distance = String.format("%.0f", Math.sqrt(mc.field_1724.method_5649(wp.getX(), wp.getY(), wp.getZ()))) + "m";
                  float tagX3 = (float)((posX + diff - FontRenderers.sf_bold_mini.getStringWidth(distance) / 2.0F) * 1.0);
                  context.method_51448().method_22903();
                  context.method_51448().method_22904(posX - 10.0, posY - 35.0, 0.0);
                  context.method_25293(TextureStorage.waypoint, 0, 0, 20, 20, 0.0F, 0.0F, 20, 20, 20, 20);
                  context.method_51448().method_22909();
                  FontRenderers.sf_bold_mini.drawString(context.method_51448(), wp.getName(), tagX, (float)posY - 10.0F, -1);
                  FontRenderers.sf_bold_mini.drawString(context.method_51448(), class_124.field_1080 + coords, tagX2, (float)posY - 2.0F, -1);
                  FontRenderers.sf_bold_mini.drawString(context.method_51448(), class_124.field_1080 + distance, tagX3, (float)posY + 6.0F, -1);
               }
            }
         }
      }
   }
}
