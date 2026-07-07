package unique.core.events.impl;

import net.minecraft.class_437;
import unique.core.events.Event;

public class EventScreen extends Event {
   private final class_437 screen;

   public EventScreen(class_437 screen) {
      this.screen = screen;
   }

   public class_437 getScreen() {
      return this.screen;
   }
}
