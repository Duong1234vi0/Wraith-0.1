package sky.core.utils.managers.impl.staffmanager;

import java.util.Objects;
import net.minecraft.util.text.ITextComponent;

public class Staff {
   private final String name;
   private final ITextComponent prefix;
   private final boolean vanished;

   public Staff(String name, ITextComponent prefix, boolean vanished) {
      this.name = name;
      this.prefix = prefix;
      this.vanished = vanished;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o instanceof Staff) {
         Staff staff = (Staff)o;
         return Objects.equals(this.name, staff.name);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.name});
   }

   public String getName() {
      return this.name;
   }

   public ITextComponent getPrefix() {
      return this.prefix;
   }

   public boolean isVanished() {
      return this.vanished;
   }
}
