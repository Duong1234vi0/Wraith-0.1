package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventSwapWorld extends EventCancellable implements Event {
   public State state;

   public State getState() {
      return this.state;
   }

   public void setState(State state) {
      this.state = state;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSwapWorld)) {
         return false;
      } else {
         EventSwapWorld other = (EventSwapWorld)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$state = this.getState();
            Object other$state = other.getState();
            if (this$state == null) {
               if (other$state != null) {
                  return false;
               }
            } else if (!this$state.equals(other$state)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventSwapWorld;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $state = this.getState();
      result = result * 59 + ($state == null ? 43 : $state.hashCode());
      return result;
   }

   public String toString() {
      return "EventSwapWorld(state=" + String.valueOf(this.getState()) + ")";
   }

   public EventSwapWorld(State state) {
      this.state = state;
   }

   public static enum State {
      LOAD,
      CHANGED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{LOAD, CHANGED};
      }
   }
}
