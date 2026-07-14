package sky.core.events;

import com.darkmagician6.eventapi.events.Event;

public class EventChat implements Event {
   private String message;
   private boolean cancelled;

   public EventChat(String message) {
      this.message = message;
      this.cancelled = false;
   }

   public String getMessage() {
      return this.message;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }

   public EventChat(String message, boolean cancelled) {
      this.message = message;
      this.cancelled = cancelled;
   }
}
