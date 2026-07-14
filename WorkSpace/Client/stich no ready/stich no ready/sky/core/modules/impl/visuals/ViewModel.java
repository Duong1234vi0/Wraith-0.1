package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.HandSide;
import sky.core.events.EventViewModel;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ClickSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class ViewModel extends Module {
   public final SliderSetting right_x = new SliderSetting("Правая рука X", 0.0F, -2.0F, 2.0F, 0.1F);
   public final SliderSetting right_y = new SliderSetting("Правая рука Y", 0.0F, -2.0F, 2.0F, 0.1F);
   public final SliderSetting right_z = new SliderSetting("Правая рука Z", 0.0F, -2.0F, 2.0F, 0.1F);
   public final SliderSetting left_x = new SliderSetting("Левая рука X", 0.0F, -2.0F, 2.0F, 0.1F);
   public final SliderSetting left_y = new SliderSetting("Левая рука Y", 0.0F, -2.0F, 2.0F, 0.1F);
   public final SliderSetting left_z = new SliderSetting("Левая рука Z", 0.0F, -2.0F, 2.0F, 0.1F);
   public final ClickSetting reset = new ClickSetting("Сбросить", () -> {
      this.right_x.set(0.0F);
      this.right_y.set(0.0F);
      this.right_z.set(0.0F);
      this.left_x.set(0.0F);
      this.left_y.set(0.0F);
      this.left_z.set(0.0F);
   });

   public ViewModel() {
      super("View Model", "Позволяет изменить положение рук", Category.Visuals);
      this.addSettings(new Setting[]{this.right_x, this.right_y, this.right_z, this.left_x, this.left_y, this.left_z, this.reset});
   }

   @EventTarget
   public void onEvent(EventViewModel event) {
      MatrixStack matrixStack = event.getMatrixStack();
      if (event.getHandside() == HandSide.RIGHT) {
         matrixStack.translate((double)(Float)this.right_x.get(), (double)(Float)this.right_y.get(), (double)(Float)this.right_z.get());
      } else {
         matrixStack.translate((double)(Float)this.left_x.get(), (double)(Float)this.left_y.get(), (double)(Float)this.left_z.get());
      }

   }
}
