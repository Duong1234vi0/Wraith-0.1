package sky.core.events;

import net.minecraft.util.text.event.ClickEvent;

public class EventClientClick extends ClickEvent {
   public EventClientClick(ClickEvent.Action action, String value) {
      super(action, value);
   }
}
