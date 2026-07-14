package sky.core.utils.component.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.minecraft.client.Minecraft;
import sky.core.events.EventInput;
import sky.core.utils.component.Component;

public class MoveComponent extends Component {
   public static int stopTicks = 0;
   public static boolean stop = false;
   static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

   @EventTarget
   public void onEvent(EventInput event) {
      if (stopTicks > 0) {
         if (stop) {
            event.setStrafe(0.0F);
            event.setForward(0.0F);
         }

         mc.gameSettings.keyBindSprint.setPressed(false);
         Minecraft var10000 = mc;
         Minecraft.player.setSprinting(false);
         --stopTicks;
      } else if (stop) {
         stop = false;
      }

   }

   public static int getStopTicks() {
      return stopTicks;
   }

   public static void setStopTicks(int stopTicks) {
      MoveComponent.stopTicks = stopTicks;
   }

   public static boolean isStop() {
      return stop;
   }

   public static void setStop(boolean stop) {
      MoveComponent.stop = stop;
   }
}
