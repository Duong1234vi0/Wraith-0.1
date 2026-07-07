package unique.core.utility.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1041;
import net.minecraft.class_310;

public interface WindowResizeCallback {
   Event<WindowResizeCallback> EVENT = EventFactory.createArrayBacked(WindowResizeCallback.class, callbacks -> (client, window) -> {
      for (WindowResizeCallback callback : callbacks) {
         callback.onResized(client, window);
      }
   });

   void onResized(class_310 var1, class_1041 var2);
}
