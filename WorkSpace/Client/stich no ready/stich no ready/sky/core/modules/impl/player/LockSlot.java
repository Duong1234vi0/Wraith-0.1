package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Items;
import net.minecraft.util.text.TextFormatting;
import sky.core.events.EventDropItem;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.ServerUtil;

public class LockSlot extends Module {
   private final BooleanSetting workInPVP = new BooleanSetting("Только при пвп", true);
   public final MultiBooleanSetting slots = new MultiBooleanSetting("Выберите слоты", new BooleanSetting[]{new BooleanSetting("1", false), new BooleanSetting("2", false), new BooleanSetting("3", false), new BooleanSetting("4", false), new BooleanSetting("5", false), new BooleanSetting("6", false), new BooleanSetting("7", false), new BooleanSetting("8", false), new BooleanSetting("9", false)});

   public LockSlot() {
      super("Lock Slot", "Блокирует возможность выкинуть предет в указанных слотах", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.workInPVP, this.slots});
   }

   @EventTarget
   public void onDropItem(EventDropItem e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.inventory != null) {
            var10000 = mc;
            if (Minecraft.player.getHeldItemMainhand().getItem() != Items.AIR) {
               if ((Boolean)this.workInPVP.get() && !ServerUtil.isPvP()) {
                  return;
               }

               int currentSlot = e.getCurrentSlot();
               BooleanSetting setting = this.slots.getIndex(currentSlot);
               if ((Boolean)setting.get()) {
                  e.setCancelled(true);
                  ChatUtil.addText(String.valueOf(TextFormatting.GRAY) + "Выброс предмет из слота " + (currentSlot + 1) + " был заблокирован");
               }

               return;
            }
         }
      }

   }
}
