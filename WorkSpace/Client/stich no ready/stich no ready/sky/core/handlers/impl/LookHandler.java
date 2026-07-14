package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import sky.core.events.EventLook;
import sky.core.events.EventRotation;
import sky.core.utils.component.Component;

public class LookHandler extends Component {
   private static boolean active;
   private static float freeYaw;
   private static float freePitch;

   @EventTarget
   private void onLook(EventLook e) {
      if (active) {
         this.rotateTowards(e.yaw, e.pitch);
         e.setCancelled(true);
      }

   }

   @EventTarget
   private void onRotation(EventRotation e) {
      if (active) {
         e.setRotation(new Vector2f(freeYaw, freePitch));
      } else {
         freeYaw = e.getRotation().x;
         freePitch = e.getRotation().y;
      }

   }

   private void rotateTowards(double targetYaw, double targetPitch) {
      freePitch = MathHelper.clamp((float)((double)freePitch + targetPitch * 0.15), -90.0F, 90.0F);
      freeYaw = (float)((double)freeYaw + targetYaw * 0.15);
   }

   public static void setActive(boolean active) {
      LookHandler.active = active;
   }

   public static boolean isActive() {
      return active;
   }

   public static float getFreeYaw() {
      return freeYaw;
   }

   public static float getFreePitch() {
      return freePitch;
   }

   public static void setFreeYaw(float freeYaw) {
      LookHandler.freeYaw = freeYaw;
   }

   public static void setFreePitch(float freePitch) {
      LookHandler.freePitch = freePitch;
   }
}
