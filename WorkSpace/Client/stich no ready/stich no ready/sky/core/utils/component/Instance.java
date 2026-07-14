package sky.core.utils.component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sky.core.SkyCore;

public final class Instance {
   private static final ConcurrentMap componentInstances = new ConcurrentHashMap();

   public static Component getComponent(Class clazz) {
      return (Component)clazz.cast(componentInstances.computeIfAbsent(clazz, (instance) -> {
         return SkyCore.getInstance().getComponentManager().get(instance);
      }));
   }

   private Instance() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
