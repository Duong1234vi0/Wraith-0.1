package unique.core.gui.font;

import net.minecraft.class_2960;

public class Texture {
   final class_2960 id;

   public Texture(String path) {
      this.id = class_2960.method_60655("unique", this.validatePath(path));
   }

   public Texture(class_2960 i) {
      this.id = class_2960.method_60655(i.method_12836(), i.method_12832());
   }

   String validatePath(String path) {
      if (class_2960.method_20208(path)) {
         return path;
      } else {
         StringBuilder ret = new StringBuilder();

         for (char c : path.toLowerCase().toCharArray()) {
            if (class_2960.method_29184(c)) {
               ret.append(c);
            }
         }

         return ret.toString();
      }
   }

   public class_2960 getId() {
      return this.id;
   }
}
