package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.gui.screen.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CClickWindowPacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import sky.core.events.EventInventoryClose;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.component.impl.MoveComponent;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.MoveUtil;

public class GuiMove extends Module {
   private final List packet = new ArrayList();
   public boolean slow = false;
   BooleanSetting stop = new BooleanSetting("Обход кликов/свапов", false);
   public TimeUtil wait = new TimeUtil();

   private KeyBinding[] pressedKeys() {
      return new KeyBinding[]{mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump};
   }

   public GuiMove() {
      super("Gui Move", "Можна ходить с открытым инвентарем", Category.Movement);
      this.addSettings(new Setting[]{this.stop});
   }

   @EventTarget
   private void onUpdt(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         KeyBinding[] pressedKeys = this.pressedKeys();
         if (!this.wait.isReached(51L)) {
            KeyBinding[] var3 = pressedKeys;
            int var4 = pressedKeys.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               KeyBinding keyBinding = var3[var5];
               keyBinding.setPressed(false);
            }

            return;
         }

         if (mc.currentScreen instanceof ChatScreen || mc.currentScreen instanceof EditSignScreen || mc.currentScreen instanceof ChestScreen || mc.currentScreen instanceof ShulkerBoxScreen || mc.currentScreen instanceof CraftingScreen || mc.currentScreen instanceof AnvilScreen || mc.currentScreen instanceof FurnaceScreen || mc.currentScreen instanceof BlastFurnaceScreen || mc.currentScreen instanceof EnchantmentScreen) {
            return;
         }

         var10000 = mc;
         if (Minecraft.player.isElytraFlying()) {
            return;
         }

         this.updateKeyBindingState(pressedKeys);
      }

   }

   @EventTarget
   public void onPacket(EventPacket e) {
      IPacket var3 = e.getPacket();
      if (var3 instanceof CClickWindowPacket p) {
         if (MoveUtil.isMoving() && mc.currentScreen instanceof InventoryScreen) {
            this.packet.add(p);
            e.setCancelled(true);
         }
      }

   }

   @EventTarget
   public void onClose(EventInventoryClose e) {
      if (mc.currentScreen instanceof InventoryScreen && MoveUtil.isMoving()) {
         MoveComponent.stopTicks = 2;
         if ((Boolean)this.stop.get()) {
            MoveComponent.stop = true;
         }

         this.slow = true;
         (new Thread(() -> {
            this.wait.reset();

            try {
               Thread.sleep(51L);
            } catch (InterruptedException var3) {
               throw new RuntimeException(var3);
            }

            Iterator var1 = this.packet.iterator();

            Minecraft var10000;
            while(var1.hasNext()) {
               IPacket p = (IPacket)var1.next();
               var10000 = mc;
               Minecraft.player.connection.sendPacketWithoutEvent(p);
            }

            this.slow = false;
            this.packet.clear();
            var10000 = mc;
            Minecraft var10003 = mc;
            Minecraft.player.connection.sendPacket(new CCloseWindowPacket(Minecraft.player.openContainer.windowId));
         })).start();
         e.setCancelled(true);
      }

   }

   public void onDisable() {
      super.onDisable();
      if (mc.currentScreen != null) {
         KeyBinding[] var1 = this.pressedKeys();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            KeyBinding keyBinding = var1[var3];
            keyBinding.setPressed(false);
         }

      }
   }

   private void updateKeyBindingState(KeyBinding[] keyBindings) {
      KeyBinding[] var2 = keyBindings;
      int var3 = keyBindings.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         KeyBinding keyBinding = var2[var4];
         int keyCode = keyBinding.getDefault().getKeyCode();
         boolean isKeyPressed = keyCode >= 0 && InputMappings.isKeyDown(mc.getMainWindow().getHandle(), keyCode);
         keyBinding.setPressed(isKeyPressed);
      }

   }
}
