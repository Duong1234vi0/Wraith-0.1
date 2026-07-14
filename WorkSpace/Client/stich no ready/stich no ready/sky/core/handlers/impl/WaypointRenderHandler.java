package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
import org.joml.Vector2f;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.WaypointManager;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ProjectUtil;
import sky.core.utils.render.font.Fonts;

public class WaypointRenderHandler implements Wrapper {
   @EventTarget
   public void render(EventRender2D eventRender2D) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         String serverKey = mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP : "singleplayer";
         List waypoints = SkyCore.getInstance().getWaypointManager().getWaypoints(serverKey);
         if (!waypoints.isEmpty()) {
            var10000 = mc;
            double px = Minecraft.player.getPosX();
            var10000 = mc;
            double pz = Minecraft.player.getPosZ();
            Iterator var8 = waypoints.iterator();

            while(var8.hasNext()) {
               WaypointManager.WaypointEntry wp = (WaypointManager.WaypointEntry)var8.next();
               Vector3d worldPos = new Vector3d(wp.getX(), wp.getY(), wp.getZ());
               Vector2f screen = ProjectUtil.project2D(worldPos);
               if (!Float.isInfinite(screen.x) && !Float.isInfinite(screen.y) && screen.x != Float.MAX_VALUE && screen.y != Float.MAX_VALUE) {
                  String icon = "B";
                  String var21 = wp.getName();
                  String label = var21 + ": " + (int)Math.round(Math.hypot(px - wp.getX(), pz - wp.getZ())) + "m";
                  float iconWidth = Fonts.waypoint_icons[36].getWidth(icon);
                  float iconHeight = Fonts.waypoint_icons[36].getHeight();
                  float drawX = screen.x - iconWidth / 2.0F;
                  float drawY = screen.y - iconHeight / 2.0F;
                  Fonts.waypoint_icons[36].drawOutlineString(eventRender2D.getStack(), icon, (double)drawX, (double)drawY, -1, false, true, false, true, ColorUtil.getColor(0, 0, 0, 255));
                  float textWidth = Fonts.sf_semibold[15].getWidth(label);
                  float textX = screen.x - textWidth / 2.0F;
                  float textY = drawY + iconHeight + 2.0F + 3.0F;
                  Fonts.sf_semibold[15].drawOutlineString(eventRender2D.getStack(), label, (double)textX, (double)textY, -1, false, true, false, true, ColorUtil.getColor(0, 0, 0, 255));
               }
            }

         }
      }
   }
}
