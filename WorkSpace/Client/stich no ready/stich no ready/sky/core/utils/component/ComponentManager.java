package sky.core.utils.component;

import com.darkmagician6.eventapi.EventManager;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;
import sky.core.handlers.impl.LookHandler;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.component.impl.SmoothRotationComponent;

public final class ComponentManager extends HashMap {
   public void init() {
      this.add(new LookHandler(), new RotationComponent(), new SmoothRotationComponent(), new MoveComponent());
      this.values().forEach((component) -> {
         EventManager.register(component);
      });
   }

   public void add(Component... components) {
      Component[] var2 = components;
      int var3 = components.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Component component = var2[var4];
         this.put(component.getClass(), component);
      }

   }

   public Component get(Class clazz) {
      Stream var10000 = this.values().stream().filter((component) -> {
         return component.getClass() == clazz;
      });
      Objects.requireNonNull(clazz);
      return (Component)var10000.map(clazz::cast).findFirst().orElse((Object)null);
   }
}
