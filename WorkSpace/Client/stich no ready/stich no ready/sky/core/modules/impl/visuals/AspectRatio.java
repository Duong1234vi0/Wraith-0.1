package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import sky.core.events.EventAspectRatio;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class AspectRatio extends Module {
   public final ModeSetting mode = new ModeSetting("Соотношение", "Кастомное", new String[]{"4:3", "16:9", "1:1", "16:10", "Кастомное"});
   public final SliderSetting aspectRatio = new SliderSetting("Значение", 1.0F, 0.1F, 5.0F, 0.1F, () -> {
      return this.mode.is("Кастомное");
   });

   public AspectRatio() {
      super("AspectRatio", "Изменяет соотношение сторон экрана", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.aspectRatio});
   }

   @EventTarget
   public void onEvent(EventAspectRatio event) {
      float var10000;
      switch ((String)this.mode.get()) {
         case "4:3":
            var10000 = 1.3333334F;
            break;
         case "16:9":
            var10000 = 1.7777778F;
            break;
         case "1:1":
            var10000 = 1.0F;
            break;
         case "16:10":
            var10000 = 1.6F;
            break;
         default:
            var10000 = (Float)this.aspectRatio.get();
      }

      float value = var10000;
      event.setAspectRatio(value);
   }
}
