package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import sky.core.events.EventNoPush;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;

public class NoPush extends Module {
   public MultiBooleanSetting mode = new MultiBooleanSetting("Тип", new BooleanSetting[]{new BooleanSetting("Игроки", true), new BooleanSetting("Блоки", true), new BooleanSetting("Вода", true), new BooleanSetting("Удочки", true)});

   public NoPush() {
      super("No Push", "Позволяет не отталкиваться при определенных условиях, обеспечивая стабильное передвижение", Category.Movement);
      this.addSettings(new Setting[]{this.mode});
   }

   @EventTarget
   public void onEvent(EventNoPush event) {
      boolean var10000;
      switch (event.noPushType) {
         case Block:
            var10000 = this.mode.is("Блоки");
            break;
         case Water:
            var10000 = this.mode.is("Вода");
            break;
         case Player:
            var10000 = this.mode.is("Игроки");
            break;
         case FishingRod:
            var10000 = this.mode.is("Удочки");
            break;
         default:
            throw new IncompatibleClassChangeError();
      }

      boolean cancel = var10000;
      if (cancel) {
         event.setCancelled(true);
      }

   }
}
