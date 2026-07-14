package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.EventTarget;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class TapeMouse extends Module {
   public static ModeSetting modeClick = new ModeSetting("Клавиша", "Левая", new String[]{"Левая", "Правая"});
   public static SliderSetting delayForClick = new SliderSetting("Задержка", 1.0F, 1.0F, 30.0F, 1.0F);
   private int tickCounter = 0;

   public TapeMouse() {
      super("TapeMouse", "Автоматически воспроизводит клик мыши", Category.Miscellaneous);
      this.addSettings(new Setting[]{modeClick, delayForClick});
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      ++this.tickCounter;
      if ((float)this.tickCounter >= (Float)delayForClick.get()) {
         if (modeClick.is("Левая")) {
            mc.clickMouse();
         } else if (modeClick.is("Правая")) {
            mc.rightClickMouse();
         }

         this.tickCounter = 0;
      }

   }
}
