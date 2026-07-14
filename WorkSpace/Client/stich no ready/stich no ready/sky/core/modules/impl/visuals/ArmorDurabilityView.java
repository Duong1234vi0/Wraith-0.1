package sky.core.modules.impl.visuals;

import sky.core.modules.Category;
import sky.core.modules.Module;

public class ArmorDurabilityView extends Module {
   private static final float[][] COLOR_TABLE = new float[][]{{0.6F, 0.0F, 0.0F}, {0.8F, 0.0F, 0.0F}, {1.0F, 0.0F, 0.0F}, {1.0F, 0.2F, 0.0F}, {1.0F, 0.4F, 0.0F}, {1.0F, 0.6F, 0.0F}, {1.0F, 0.8F, 0.0F}, {1.0F, 1.0F, 0.0F}, {0.9F, 1.0F, 0.0F}, {0.7F, 1.0F, 0.0F}, {0.5F, 1.0F, 0.0F}, {0.0F, 0.6F, 0.0F}, {0.0F, 0.7F, 0.0F}, {0.0F, 0.8F, 0.0F}, {0.0F, 0.9F, 0.0F}, {0.0F, 1.0F, 0.0F}, {0.2F, 1.0F, 0.2F}, {0.4F, 1.0F, 0.4F}, {0.6F, 1.0F, 0.6F}, {0.8F, 1.0F, 0.8F}, {1.0F, 1.0F, 1.0F}};

   public ArmorDurabilityView() {
      super("ArmorDurabilityView", "Красит броню в зависимости от прочности", Category.Visuals);
   }

   public static float[] getDurabilityColor(float durability) {
      if (!(durability < 0.0F) && !(durability > 1.0F)) {
         int interval = (int)(durability * 100.0F / 5.0F);
         return COLOR_TABLE[Math.min(interval, COLOR_TABLE.length - 1)];
      } else {
         return new float[]{1.0F, 1.0F, 1.0F};
      }
   }
}
