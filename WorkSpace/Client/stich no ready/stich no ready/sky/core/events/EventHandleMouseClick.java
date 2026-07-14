package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;

public class EventHandleMouseClick extends EventCancellable implements Event {
   private Slot slotIn;
   private int slotId;
   private int mouseButton;
   private ClickType type;

   public Slot getSlotIn() {
      return this.slotIn;
   }

   public int getSlotId() {
      return this.slotId;
   }

   public int getMouseButton() {
      return this.mouseButton;
   }

   public ClickType getType() {
      return this.type;
   }

   public void setSlotIn(Slot slotIn) {
      this.slotIn = slotIn;
   }

   public void setSlotId(int slotId) {
      this.slotId = slotId;
   }

   public void setMouseButton(int mouseButton) {
      this.mouseButton = mouseButton;
   }

   public void setType(ClickType type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventHandleMouseClick)) {
         return false;
      } else {
         EventHandleMouseClick other = (EventHandleMouseClick)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getSlotId() != other.getSlotId()) {
            return false;
         } else if (this.getMouseButton() != other.getMouseButton()) {
            return false;
         } else {
            label40: {
               Object this$slotIn = this.getSlotIn();
               Object other$slotIn = other.getSlotIn();
               if (this$slotIn == null) {
                  if (other$slotIn == null) {
                     break label40;
                  }
               } else if (this$slotIn.equals(other$slotIn)) {
                  break label40;
               }

               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventHandleMouseClick;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getSlotId();
      result = result * 59 + this.getMouseButton();
      Object $slotIn = this.getSlotIn();
      result = result * 59 + ($slotIn == null ? 43 : $slotIn.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getSlotIn());
      return "EventHandleMouseClick(slotIn=" + var10000 + ", slotId=" + this.getSlotId() + ", mouseButton=" + this.getMouseButton() + ", type=" + String.valueOf(this.getType()) + ")";
   }

   public EventHandleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
      this.slotIn = slotIn;
      this.slotId = slotId;
      this.mouseButton = mouseButton;
      this.type = type;
   }
}
