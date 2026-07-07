package unique.core.features.modules.misc;

import java.util.function.Consumer;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_239;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_9334;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventSync;
import unique.core.features.cmd.Command;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;

public class MiddleClick extends Module {
   private final Setting<MiddleClick.Action> onBlock = new Setting<>("OnBlock", MiddleClick.Action.Xp);
   private final Setting<MiddleClick.Action> onAir = new Setting<>("OnAir", MiddleClick.Action.Pearl);
   private final Setting<MiddleClick.Action> onEntity = new Setting<>("OnEntity", MiddleClick.Action.Friend);
   private final Setting<MiddleClick.Action> onFlying = new Setting<>("OnElytra", MiddleClick.Action.Firework);
   private final Setting<Boolean> silent = new Setting<>("Silent", true);
   private final Setting<Boolean> inventory = new Setting<>("Inventory", false);
   private final Setting<Integer> swapDelay = new Setting<>("SwapDelay", 100, 0, 1000, v -> !this.silent.getValue());
   public final Setting<Boolean> antiPickUp = new Setting<>("AntiPickUp", false);
   private final Setting<Boolean> feetExp = new Setting<>("FeetXP", false);
   private final Setting<MiddleClick.Mode> mode = new Setting<>("Mode", MiddleClick.Mode.Normal);
   private static final Timer timer = new Timer();
   private static final Timer xpTimer = new Timer();
   private static final Timer swapTimer = new Timer();
   private static final int LEGIT_DELAY = 150;
   private static final int NORMAL_DELAY = 110;
   private String state = "none";

   public MiddleClick() {
      super("MiddleClick", Module.Category.MISC);
   }

   @Override
   public String getDisplayInfo() {
      return this.state;
   }

   @EventHandler
   private void onSync(EventSync event) {
      if (mc.field_1755 == null) {
         class_239 target = mc.field_1765;
         if (mc.field_1724.method_6128()) {
            if (mc.field_1690.field_1871.method_1434()) {
               this.onFlying.getValue().doAction(event);
            }

            this.state = this.onFlying.getValue().toString();
         } else if (target instanceof class_3966 ehr && ehr.method_17782() instanceof class_1657) {
            if (mc.field_1690.field_1871.method_1434()) {
               this.onEntity.getValue().doAction(event);
            }

            this.state = this.onEntity.getValue().toString();
         } else if (target instanceof class_3965 bhr) {
            if (mc.field_1687.method_22347(bhr.method_17777())) {
               if (mc.field_1690.field_1871.method_1434()) {
                  this.onAir.getValue().doAction(event);
               }

               this.state = this.onAir.getValue().toString();
            } else {
               if (mc.field_1690.field_1871.method_1434()) {
                  this.onBlock.getValue().doAction(event);
               }

               this.state = this.onBlock.getValue().toString();
            }
         } else {
            if (mc.field_1690.field_1871.method_1434()) {
               this.onAir.getValue().doAction(event);
            }

            this.state = this.onAir.getValue().toString();
         }
      }
   }

   private static int getHpSlot() {
      for (int i = 0; i < 9; i++) {
         if (isStackPotion(mc.field_1724.method_31548().method_5438(i))) {
            return i;
         }
      }

      return -1;
   }

   private static int findHpInInventory() {
      for (int i = 36; i >= 0; i--) {
         if (isStackPotion(mc.field_1724.method_31548().method_5438(i))) {
            return i < 9 ? i + 36 : i;
         }
      }

      return -1;
   }

   private static boolean isStackPotion(class_1799 stack) {
      if (stack == null) {
         return false;
      } else {
         if (stack.method_7909() == class_1802.field_8436) {
            class_1844 pc = (class_1844)stack.method_57825(class_9334.field_49651, class_1844.field_49274);

            for (class_1293 e : pc.method_57397()) {
               if (e.method_5579().comp_349() == class_1294.field_5915.comp_349()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static enum Action {
      HealingPot(
         e -> {
            if (MiddleClick.timer.every(250L)) {
               int slot = MiddleClick.getHpSlot() != -1 ? MiddleClick.getHpSlot() : MiddleClick.findHpInInventory();
               if (slot != -1) {
                  int original = Module.mc.field_1724.method_31548().field_7545;
                  InventoryUtility.switchTo(slot);
                  InteractionUtility.sendSequencedPacket(
                     id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455())
                  );
                  InventoryUtility.switchTo(original);
               }
            }
         }
      ),
      Firework(
         e -> {
            if (MiddleClick.timer.every(250L)) {
               int slot = InventoryUtility.findItemInHotBar(class_1802.field_8639).slot();
               if (slot != -1) {
                  int old = Module.mc.field_1724.method_31548().field_7545;
                  Module.mc.field_1724.method_31548().field_7545 = slot;
                  Module.mc.field_1724.field_3944.method_52787(new class_2868(slot));
                  InteractionUtility.sendSequencedPacket(
                     id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455())
                  );
                  Module.mc.field_1724.field_3944.method_52787(new class_2879(class_1268.field_5808));
                  Module.mc.field_1724.method_31548().field_7545 = old;
                  Module.mc.field_1724.field_3944.method_52787(new class_2868(old));
               }
            }
         }
      ),
      Friend(e -> {
         if (MiddleClick.timer.every(800L) && Module.mc.field_1765 instanceof class_3966 ehr && ehr.method_17782() instanceof class_1657 p) {
            String name = p.method_5477().getString();
            if (Managers.FRIEND.isFriend(name)) {
               Managers.FRIEND.removeFriend(name);
               Command.sendMessage(ClientSettings.isRu() ? "§b" + name + "§r удален из друзей!" : "§b" + name + "§r Removed From Friends!");
            } else {
               Managers.FRIEND.addFriend(name);
               Command.sendMessage(ClientSettings.isRu() ? "Добавлен друг §b" + name : "Added Friend §b" + name);
            }
         }
      }),
      Pearl(
         e -> {
            if (MiddleClick.timer.every(500L)) {
               int slot = InventoryUtility.findItemInHotBar(class_1802.field_8634).slot();
               if (slot != -1) {
                  int old = Module.mc.field_1724.method_31548().field_7545;
                  Module.mc.field_1724.method_31548().field_7545 = slot;
                  Module.mc.field_1724.field_3944.method_52787(new class_2868(slot));
                  InteractionUtility.sendSequencedPacket(
                     id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455())
                  );
                  Module.mc.field_1724.field_3944.method_52787(new class_2879(class_1268.field_5808));
                  Module.mc.field_1724.method_31548().field_7545 = old;
                  Module.mc.field_1724.field_3944.method_52787(new class_2868(old));
               }
            }
         }
      ),
      Xp(
         e -> {
            if (ModuleManager.middleClick.feetExp.getValue()) {
               Module.mc.field_1724.method_36457(90.0F);
            }

            e.addPostAction(
               () -> {
                  if (Module.mc.field_1690.field_1871.method_1434()) {
                     int slot = InventoryUtility.findItemInHotBar(class_1802.field_8287).slot();
                     if (slot != -1) {
                        int delay = ModuleManager.middleClick.mode.getValue() == MiddleClick.Mode.Legit ? 150 : 110;
                        if (MiddleClick.xpTimer.every(delay)) {
                           if (MiddleClick.swapTimer.every(ModuleManager.middleClick.swapDelay.getValue().intValue())) {
                              int last = Module.mc.field_1724.method_31548().field_7545;
                              InventoryUtility.switchTo(slot);
                              InteractionUtility.sendSequencedPacket(
                                 id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455())
                              );
                              if (ModuleManager.middleClick.mode.getValue() == MiddleClick.Mode.Legit) {
                                 Module.mc.field_1724.field_3944.method_52787(new class_2879(class_1268.field_5808));
                              }

                              if (ModuleManager.middleClick.silent.getValue()) {
                                 InventoryUtility.switchTo(last);
                              }
                           }
                        }
                     }
                  }
               }
            );
         }
      ),
      None(e -> {});

      private final Consumer<EventSync> r;

      private Action(Consumer<EventSync> r) {
         this.r = r;
      }

      public void doAction(EventSync e) {
         this.r.accept(e);
      }
   }

   public static enum Mode {
      Normal,
      Legit;
   }
}
