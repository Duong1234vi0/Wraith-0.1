package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effects;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class TestFunction extends Module {
   private final Map lastPearlPos = new HashMap();

   public TestFunction() {
      super("TestFunction", "Тест Функция", Category.Player);
   }

   @EventTarget
   public void update(EventUpdate eventUpdate) {
      Minecraft var10000 = mc;
      boolean hasEffects = Minecraft.player.isPotionActive(Effects.LEVITATION);
      if (hasEffects) {
         var10000 = mc;
         Minecraft.player.removePotionEffect(Effects.LEVITATION);
      }

   }
}
