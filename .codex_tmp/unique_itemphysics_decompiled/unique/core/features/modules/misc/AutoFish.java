package unique.core.features.modules.misc;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1536;
import net.minecraft.class_1787;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2767;
import net.minecraft.class_2879;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import unique.core.core.Managers;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.player.InventoryUtility;

public class AutoFish extends Module {
   private final Setting<AutoFish.DetectMode> detectMode = new Setting<>("DetectMode", AutoFish.DetectMode.DataTracker);
   private final Setting<Boolean> rodSave = new Setting<>("RodSave", true);
   private final Setting<Boolean> changeRod = new Setting<>("ChangeRod", false);
   private final Setting<Boolean> autoSell = new Setting<>("AutoSell", false);
   private boolean flag = false;
   private final Timer timeout = new Timer();
   private final Timer cooldown = new Timer();

   public AutoFish() {
      super("AutoFish", Module.Category.MISC);
   }

   @Override
   public void onEnable() {
      if (fullNullCheck()) {
         this.disable("NPE protection");
      }
   }

   @Override
   public void onDisable() {
      this.flag = false;
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2767 sound
         && this.detectMode.getValue() == AutoFish.DetectMode.Sound
         && ((class_3414)sound.method_11894().comp_349()).equals(class_3417.field_14660)
         && mc.field_1724.field_7513 != null
         && mc.field_1724.field_7513.method_5649(sound.method_11890(), sound.method_11889(), sound.method_11893()) < 4.0) {
         this.catchFish();
      }
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724.method_6047().method_7909() instanceof class_1787 && mc.field_1724.method_6047().method_7919() > 52) {
         if (this.rodSave.getValue() && !this.changeRod.getValue()) {
            this.disable(ClientSettings.isRu() ? "Удочка почти сломалась!" : "Saving the rod...");
         } else if (this.changeRod.getValue() && this.getRodSlot() != -1) {
            this.sendMessage(ClientSettings.isRu() ? "Свапнулся на новую удочку" : "Swapped to a new rod");
            InventoryUtility.switchTo(this.getRodSlot());
            this.cooldown.reset();
         } else {
            this.disable(ClientSettings.isRu() ? "Удочка почти сломалась!" : "Saving the rod...");
         }
      }

      if (this.cooldown.passedMs(1000L)) {
         if (this.timeout.passedMs(45000L) && mc.field_1724.method_6047().method_7909() instanceof class_1787) {
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            this.sendPacket(new class_2879(class_1268.field_5808));
            this.timeout.reset();
            this.cooldown.reset();
         }

         if (mc.field_1724.field_7513 != null && this.detectMode.getValue() == AutoFish.DetectMode.DataTracker) {
            boolean caughtFish = (Boolean)mc.field_1724.field_7513.method_5841().method_12789(class_1536.field_23234);
            if (!this.flag && caughtFish) {
               this.catchFish();
               this.flag = true;
            } else if (!caughtFish) {
               this.flag = false;
            }
         }
      }
   }

   private void catchFish() {
      Managers.ASYNC.run(() -> {
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         this.sendPacket(new class_2879(class_1268.field_5808));
         if (this.autoSell.getValue() && this.timeout.passedMs(1000L)) {
            mc.field_1724.field_3944.method_45730("sellfish");
         }

         try {
            Thread.sleep((int)MathUtility.random(899.0F, 1399.0F));
         } catch (InterruptedException var2) {
            throw new RuntimeException(var2);
         }

         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         this.sendPacket(new class_2879(class_1268.field_5808));
         this.timeout.reset();
      }, (int)MathUtility.random(199.0F, 349.0F));
   }

   private int getRodSlot() {
      for (int i = 0; i < 9; i++) {
         class_1799 item = mc.field_1724.method_31548().method_5438(i);
         if (item.method_7909() == class_1802.field_8378 && item.method_7919() < 52) {
            return i;
         }
      }

      return -1;
   }

   private static enum DetectMode {
      Sound,
      DataTracker;
   }
}
