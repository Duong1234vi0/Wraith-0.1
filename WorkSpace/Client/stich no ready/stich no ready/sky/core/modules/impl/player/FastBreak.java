package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import sky.core.events.EventBlockDamage;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class FastBreak extends Module {
   private SliderSetting breakspeed = new SliderSetting("Скорость", 0.8F, 0.1F, 1.0F, 0.1F);

   public FastBreak() {
      super("FastBreak", "Позволяет быстрее ломать блоки", Category.Player);
      this.addSettings(new Setting[]{this.breakspeed});
   }

   @EventTarget
   public void onUpdate(EventBlockDamage e) {
      mc.playerController.blockHitDelay = 0;
      if (mc.playerController.curBlockDamageMP > (Float)this.breakspeed.get()) {
         mc.playerController.curBlockDamageMP = 1.0F;
      }

   }
}
