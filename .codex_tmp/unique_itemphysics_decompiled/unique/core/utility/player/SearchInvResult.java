package unique.core.utility.player;

import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;
import unique.core.features.modules.Module;

public record SearchInvResult(int slot, boolean found, class_1799 stack) {
   private static final SearchInvResult NOT_FOUND_RESULT = new SearchInvResult(-1, false, null);

   public static SearchInvResult notFound() {
      return NOT_FOUND_RESULT;
   }

   @NotNull
   public static SearchInvResult inOffhand(class_1799 stack) {
      return new SearchInvResult(999, true, stack);
   }

   public boolean isHolding() {
      return Module.mc.field_1724 == null ? false : Module.mc.field_1724.method_31548().field_7545 == this.slot;
   }

   public boolean isInHotBar() {
      return this.slot < 9;
   }

   public void switchTo() {
      if (this.found && this.isInHotBar()) {
         InventoryUtility.switchTo(this.slot);
      }
   }

   public void switchToSilent() {
      if (this.found && this.isInHotBar()) {
         InventoryUtility.switchToSilent(this.slot);
      }
   }
}
