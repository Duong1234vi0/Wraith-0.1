package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Items;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class FastExp extends Module {
   public FastExp() {
      super("FastExp", "Позволяет быстро бросать пузырьки опыта", Category.Player);
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
         mc.rightClickDelayTimer = 0;
      }

   }
}
