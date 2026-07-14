package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.TridentItem;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class ItemRelease extends Module {
   private final MultiBooleanSetting items = new MultiBooleanSetting("Предметы", new BooleanSetting[]{new BooleanSetting("Трезубец", false), new BooleanSetting("Лук", false), new BooleanSetting("Арбалет", false)});
   private final SliderSetting delay = new SliderSetting("Сила выстрела", 3.0F, 1.0F, 20.0F, 0.5F, () -> {
      return this.items.is("Лук");
   });

   public ItemRelease() {
      super("ItemRelease", "Автоматически выпускает предмет, когда он полностью натянут", Category.Player);
      this.addSettings(new Setting[]{this.items, this.delay});
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000;
      Minecraft var10001;
      if (this.items.is("Лук")) {
         var10000 = mc;
         if (Minecraft.player.inventory.getCurrentItem().getItem() instanceof BowItem) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               var10000 = mc;
               if ((float)Minecraft.player.getItemInUseMaxCount() >= (Float)this.delay.get()) {
                  var10001 = mc;
                  mc.playerController.onStoppedUsingItem(Minecraft.player);
               }
            }
         }
      }

      if (this.items.is("Трезубец")) {
         var10000 = mc;
         if (Minecraft.player.inventory.getCurrentItem().getItem() instanceof TridentItem) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               var10000 = mc;
               if (Minecraft.player.getItemInUseMaxCount() >= 10) {
                  var10001 = mc;
                  mc.playerController.onStoppedUsingItem(Minecraft.player);
               }
            }
         }
      }

      if (this.items.is("Арбалет")) {
         var10000 = mc;
         if (Minecraft.player.inventory.getCurrentItem().getItem() instanceof CrossbowItem) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               var10000 = mc;
               int var2 = Minecraft.player.getItemInUseMaxCount();
               var10001 = mc;
               if (var2 >= CrossbowItem.getChargeTime(Minecraft.player.inventory.getCurrentItem())) {
                  var10001 = mc;
                  mc.playerController.onStoppedUsingItem(Minecraft.player);
               }
            }
         }
      }

   }
}
