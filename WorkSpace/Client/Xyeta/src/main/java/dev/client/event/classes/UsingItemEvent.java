package dev.client.event.classes;

import dev.client.event.CancellableEvent;
import dev.client.event.EventType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class UsingItemEvent extends CancellableEvent {
   EventType eventType;

   public UsingItemEvent(EventType eventType) {
      this.eventType = eventType;
   }

   public EventType getEventType() {
      return this.eventType;
   }
}
