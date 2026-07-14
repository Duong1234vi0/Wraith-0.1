package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventNoPush extends EventCancellable implements Event {
   public final NoPushType noPushType;

   public EventNoPush(NoPushType noPushType) {
      this.noPushType = noPushType;
   }

   public NoPushType getNoPushType() {
      return this.noPushType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventNoPush)) {
         return false;
      } else {
         EventNoPush other = (EventNoPush)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$noPushType = this.getNoPushType();
            Object other$noPushType = other.getNoPushType();
            if (this$noPushType == null) {
               if (other$noPushType != null) {
                  return false;
               }
            } else if (!this$noPushType.equals(other$noPushType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventNoPush;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $noPushType = this.getNoPushType();
      result = result * 59 + ($noPushType == null ? 43 : $noPushType.hashCode());
      return result;
   }

   public String toString() {
      return "EventNoPush(noPushType=" + String.valueOf(this.getNoPushType()) + ")";
   }

   public static enum NoPushType {
      Block,
      Water,
      Player,
      FishingRod;

      // $FF: synthetic method
      private static NoPushType[] $values() {
         return new NoPushType[]{Block, Water, Player, FishingRod};
      }
   }
}
