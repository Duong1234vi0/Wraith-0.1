package unique.core.features.modules.player;

import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3675;
import net.minecraft.class_3966;
import unique.core.core.Managers;
import unique.core.events.impl.EventSync;
import unique.core.features.cmd.Command;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.PlayerUtility;

public class ClickAction extends Module {
   private final Setting<Boolean> pearl = new Setting<>("Pearl", true);
   private final Setting<Boolean> xp = new Setting<>("XP", true);
   private final Setting<Boolean> friend = new Setting<>("Friend", false);
   private final Setting<Bind> pearlKey = new Setting<>("Pearl Key", new Bind(260, false, false), v -> this.pearl.getValue());
   private final Setting<Bind> xpKey = new Setting<>("XP Key", new Bind(96, false, false), v -> this.xp.getValue());
   private final Setting<Bind> friendKey = new Setting<>("Friend Key", new Bind(48, false, false), v -> this.friend.getValue());
   private final Setting<Boolean> feetXP = new Setting<>("FeetXP", true, v -> this.xp.getValue());
   private final Setting<Float> feetPitch = new Setting<>("FeetXP Pitch", 70.0F, 45.0F, 90.0F, v -> this.xp.getValue() && this.feetXP.getValue());
   private final Setting<Boolean> antiWaste = new Setting<>("AntiWaste", true, v -> this.xp.getValue());
   private final Setting<Integer> stopOn = new Setting<>("StopOn", 95, 0, 100, v -> this.xp.getValue() && this.antiWaste.getValue());
   private boolean pearlPressed;
   private boolean friendPressed;
   private boolean xpEnabled;
   private boolean xpKeyPressed;
   private long lastXpTime;
   private final Random random = new Random();

   public ClickAction() {
      super("ClickAction", Module.Category.PLAYER);
   }

   @EventHandler
   private void onSync(EventSync event) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         if (!this.pearl.getValue() || !this.isKeyPressed(this.pearlKey)) {
            this.pearlPressed = false;
         } else if (!this.pearlPressed) {
            this.throwPearl();
            this.pearlPressed = true;
         }

         if (this.xp.getValue()) {
            this.handleXpToggle();
            if (this.xpEnabled) {
               this.handleXP();
            }
         } else {
            this.xpEnabled = false;
         }

         if (!this.friend.getValue() || !this.isKeyPressed(this.friendKey)) {
            this.friendPressed = false;
         } else if (!this.friendPressed) {
            this.handleFriend();
            this.friendPressed = true;
         }
      }
   }

   private void handleXpToggle() {
      boolean pressed = this.isKeyPressed(this.xpKey);
      if (pressed && !this.xpKeyPressed) {
         this.xpEnabled = !this.xpEnabled;
      }

      this.xpKeyPressed = pressed;
   }

   private void handleXP() {
      if (this.antiWaste.getValue() && !this.needXp()) {
         this.xpEnabled = false;
      } else {
         int xpSlot = InventoryUtility.findItemInHotBar(class_1802.field_8287).slot();
         if (xpSlot == -1) {
            this.xpEnabled = false;
         } else {
            if (this.feetXP.getValue()) {
               mc.field_1724.method_36457(this.feetPitch.getValue());
            }

            long now = System.currentTimeMillis();
            int delay = 23 + this.random.nextInt(15);
            if (now - this.lastXpTime >= delay) {
               int old = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = xpSlot;
               mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
               mc.field_1724.method_6104(class_1268.field_5808);
               mc.field_1724.method_31548().field_7545 = old;
               this.lastXpTime = now;
            }
         }
      }
   }

   private boolean needXp() {
      for (class_1799 s : mc.field_1724.method_31548().field_7547) {
         if (!s.method_7960() && s.method_7963() && PlayerUtility.calculatePercentage(s) < this.stopOn.getValue().intValue()) {
            return true;
         }
      }

      for (class_1799 sx : mc.field_1724.method_31548().field_7548) {
         if (!sx.method_7960() && sx.method_7963() && PlayerUtility.calculatePercentage(sx) < this.stopOn.getValue().intValue()) {
            return true;
         }
      }

      return false;
   }

   private void throwPearl() {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         int oldSlot = mc.field_1724.method_31548().field_7545;
         int hotbarPearl = InventoryUtility.findItemInHotBar(class_1802.field_8634).slot();
         if (hotbarPearl != -1) {
            mc.field_1724.method_31548().field_7545 = hotbarPearl;
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            mc.field_1724.method_6104(class_1268.field_5808);
            mc.field_1724.method_31548().field_7545 = oldSlot;
         } else {
            int invSlot = this.findPearlInInventory();
            if (invSlot != -1) {
               int targetHotbar = oldSlot;
               if (!mc.field_1724.method_31548().method_5438(oldSlot).method_7960()) {
                  int empty = this.findEmptyHotbarSlot();
                  if (empty != -1) {
                     targetHotbar = empty;
                  }
               }

               int syncId = mc.field_1724.field_7512.field_7763;
               mc.field_1761.method_2906(syncId, invSlot, targetHotbar, class_1713.field_7791, mc.field_1724);
               mc.field_1724.method_31548().field_7545 = targetHotbar;
               mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
               mc.field_1724.method_6104(class_1268.field_5808);
               mc.field_1761.method_2906(syncId, invSlot, targetHotbar, class_1713.field_7791, mc.field_1724);
               mc.field_1724.method_31548().field_7545 = oldSlot;
            }
         }
      }
   }

   private int findPearlInInventory() {
      for (int i = 9; i < 36; i++) {
         class_1799 s = mc.field_1724.method_31548().method_5438(i);
         if (!s.method_7960() && s.method_7909() == class_1802.field_8634) {
            return i;
         }
      }

      return -1;
   }

   private int findEmptyHotbarSlot() {
      for (int i = 0; i < 9; i++) {
         class_1799 s = mc.field_1724.method_31548().method_5438(i);
         if (s.method_7960()) {
            return i;
         }
      }

      return -1;
   }

   private void handleFriend() {
      if (mc.field_1765 instanceof class_3966 ehr && ehr.method_17782() instanceof class_1657 p) {
         String name = p.method_5477().getString();
         if (Managers.FRIEND.isFriend(name)) {
            Managers.FRIEND.removeFriend(name);
            Command.sendMessage(ClientSettings.isRu() ? "§b" + name + "§r удален из друзей!" : "§b" + name + "§r Removed From Friends!");
         } else {
            Managers.FRIEND.addFriend(name);
            Command.sendMessage(ClientSettings.isRu() ? "Добавлен друг §b" + name : "Added Friend §b" + name);
         }
      }
   }

   @Override
   public boolean isKeyPressed(Setting<Bind> bind) {
      return bind.getValue().getKey() == -1 ? false : class_3675.method_15987(mc.method_22683().method_4490(), bind.getValue().getKey());
   }

   @Override
   public void onDisable() {
      this.xpEnabled = false;
      this.pearlPressed = false;
      this.friendPressed = false;
      super.onDisable();
   }
}
