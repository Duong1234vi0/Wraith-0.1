package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;

public class EventWindowClick extends EventCancellable implements Event {
   private int windowId;
   private int slotId;
   private int mouseButton;
   private ClickType type;
   private ItemStack clickedItemIn;
   private short actionNumberIn;

   public int getWindowId() {
      return this.windowId;
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

   public ItemStack getClickedItemIn() {
      return this.clickedItemIn;
   }

   public short getActionNumberIn() {
      return this.actionNumberIn;
   }

   public void setWindowId(int windowId) {
      this.windowId = windowId;
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

   public void setClickedItemIn(ItemStack clickedItemIn) {
      this.clickedItemIn = clickedItemIn;
   }

   public void setActionNumberIn(short actionNumberIn) {
      this.actionNumberIn = actionNumberIn;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventWindowClick)) {
         return false;
      } else {
         EventWindowClick other = (EventWindowClick)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getWindowId() != other.getWindowId()) {
            return false;
         } else if (this.getSlotId() != other.getSlotId()) {
            return false;
         } else if (this.getMouseButton() != other.getMouseButton()) {
            return false;
         } else if (this.getActionNumberIn() != other.getActionNumberIn()) {
            return false;
         } else {
            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$clickedItemIn = this.getClickedItemIn();
            Object other$clickedItemIn = other.getClickedItemIn();
            if (this$clickedItemIn == null) {
               if (other$clickedItemIn != null) {
                  return false;
               }
            } else if (!this$clickedItemIn.equals(other$clickedItemIn)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventWindowClick;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      result = result * 59 + this.getWindowId();
      result = result * 59 + this.getSlotId();
      result = result * 59 + this.getMouseButton();
      result = result * 59 + this.getActionNumberIn();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $clickedItemIn = this.getClickedItemIn();
      result = result * 59 + ($clickedItemIn == null ? 43 : $clickedItemIn.hashCode());
      return result;
   }

   public String toString() {
      int var10000 = this.getWindowId();
      return "EventWindowClick(windowId=" + var10000 + ", slotId=" + this.getSlotId() + ", mouseButton=" + this.getMouseButton() + ", type=" + String.valueOf(this.getType()) + ", clickedItemIn=" + String.valueOf(this.getClickedItemIn()) + ", actionNumberIn=" + this.getActionNumberIn() + ")";
   }

   public EventWindowClick(int windowId, int slotId, int mouseButton, ClickType type, ItemStack clickedItemIn, short actionNumberIn) {
      this.windowId = windowId;
      this.slotId = slotId;
      this.mouseButton = mouseButton;
      this.type = type;
      this.clickedItemIn = clickedItemIn;
      this.actionNumberIn = actionNumberIn;
   }
}
