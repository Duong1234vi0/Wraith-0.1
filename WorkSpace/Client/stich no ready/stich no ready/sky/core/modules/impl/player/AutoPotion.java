package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPickItemPacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class AutoPotion extends Module {
   private int ticksSinceLastThrow;
   private final BooleanSetting onlypvp = new BooleanSetting("Только при пвп", true);
   private final MultiBooleanSetting potions = new MultiBooleanSetting("Бросать", new BooleanSetting[]{new BooleanSetting("Скорость", true), new BooleanSetting("Сила", true), new BooleanSetting("Огнестойкость", true)});

   public AutoPotion() {
      super("Auto Potion", "Автоматически использует зелья, бросая их под себя", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.onlypvp, this.potions});
   }

   public void onEnable() {
      super.onEnable();
      this.ticksSinceLastThrow = 20;
   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      ++this.ticksSinceLastThrow;
      List slotsToThrow = new ArrayList();
      this.collectPotionSlot(slotsToThrow, this.potions.is("Скорость"), Effects.SPEED);
      this.collectPotionSlot(slotsToThrow, this.potions.is("Сила"), Effects.STRENGTH);
      this.collectPotionSlot(slotsToThrow, this.potions.is("Огнестойкость"), Effects.FIRE_RESISTANCE);
      if ((!(Boolean)this.onlypvp.get() || ServerUtil.isPvP()) && this.ticksSinceLastThrow >= 20 && !MoveUtil.isBlockUnder(0.5F) && !slotsToThrow.isEmpty()) {
         Minecraft var10002 = mc;
         RotationComponent.update(new Rotation(Minecraft.player.rotationYaw, 90.0F), 360.0F, 1, 10);
         var10002 = mc;
         if (!((new Rotation(Minecraft.player)).getDelta(Rotation.getReported()) > 1.0F)) {
            Minecraft var10000 = mc;
            int original = Minecraft.player.inventory.currentItem;
            Iterator var4 = slotsToThrow.iterator();

            while(var4.hasNext()) {
               Integer slot = (Integer)var4.next();
               if (slot < 9) {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(slot));
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
               } else {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPickItemPacket(slot));
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPickItemPacket(slot));
               }
            }

            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(original));
            this.ticksSinceLastThrow = 0;
         }
      }
   }

   private void collectPotionSlot(List slotsToThrow, boolean enabled, Effect effect) {
      if (enabled) {
         Minecraft var10000 = mc;
         if (!Minecraft.player.isPotionActive(effect)) {
            int hotbarSlot = InventoryUtil.findPotionSlotWithEffects(true, false, true, false, effect);
            if (hotbarSlot != -1) {
               slotsToThrow.add(hotbarSlot);
               return;
            }

            int inventorySlot = InventoryUtil.findPotionSlotWithEffects(false, false, true, false, effect);
            if (inventorySlot != -1) {
               slotsToThrow.add(inventorySlot);
            }

            return;
         }
      }

   }
}
