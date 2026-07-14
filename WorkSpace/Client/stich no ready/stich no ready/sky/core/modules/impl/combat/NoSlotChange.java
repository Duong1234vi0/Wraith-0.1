package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.ShulkerBoxScreen;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.server.SHeldItemChangePacket;
import net.minecraft.util.math.MathHelper;
import sky.core.events.EventHandleMouseClick;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.PickItemFixHandler;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.utils.player.InventoryUtil;

public class NoSlotChange extends Module {
   private final MultiBooleanSetting modes = new MultiBooleanSetting("На что работать", new BooleanSetting[]{new BooleanSetting("Не перемещать слоты", true), new BooleanSetting("Не выкидывать элитру", false), new BooleanSetting("Не выкидывать нагрудник", false), new BooleanSetting("Не выкидывать шар", false), new BooleanSetting("Не выкидывать осколки", false)});

   public NoSlotChange() {
      super("NoSlotChange", "Исправляет ошибки, связанные со сменой предметов в инвентаре", Category.Combat);
      this.addSettings(new Setting[]{this.modes});
   }

   @EventTarget
   public void onEvent(EventHandleMouseClick event) {
      Slot clicked = event.getSlotIn();
      ClickType clickType = event.getType();
      if (clicked != null && clickType != ClickType.SWAP && clickType != ClickType.QUICK_MOVE && !(mc.currentScreen instanceof ChestScreen) && !(mc.currentScreen instanceof CreativeScreen) && !(mc.currentScreen instanceof ShulkerBoxScreen)) {
         Item item = clicked.getStack().getItem();
         if ((Boolean)this.modes.getIndex(1).get() && item == Items.ELYTRA) {
            event.setCancelled(true);
         }

         if ((Boolean)this.modes.getIndex(2).get() && item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem)item;
            if (armorItem.getEquipmentSlot() == EquipmentSlotType.CHEST) {
               event.setCancelled(true);
            }
         }

         if ((Boolean)this.modes.getIndex(3).get() && item == Items.PLAYER_HEAD) {
            event.setCancelled(true);
         }

         if (this.modes.is("Не выкидывать осколки") && this.hasBukkitDisplayAndCustomModel(clicked.getStack())) {
            event.setCancelled(true);
         }

      }
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (!(mc.currentScreen instanceof ChestScreen) && !(mc.currentScreen instanceof CreativeScreen) && !(mc.currentScreen instanceof ShulkerBoxScreen)) {
         Minecraft var10000 = mc;
         ItemStack itemStack = Minecraft.player.inventory.getItemStack();
         int n = InventoryUtil.findEmptyInventorySlot();
         boolean bl = n != -1;
         Minecraft var10005;
         if (this.modes.is("Не выкидывать элитру") && InventoryUtil.getSlot(Items.ELYTRA) == -1 && bl && itemStack.getItem() == Items.ELYTRA) {
            var10005 = mc;
            mc.playerController.windowClick(0, n, 1, ClickType.PICKUP, Minecraft.player);
         }

         if (this.modes.is("Не выкидывать нагрудник") && InventoryUtil.getChestPlateSlot() == -1 && bl) {
            Item var6 = itemStack.getItem();
            if (var6 instanceof ArmorItem) {
               ArmorItem armorItem = (ArmorItem)var6;
               if (armorItem.getEquipmentSlot() == EquipmentSlotType.CHEST) {
                  var10005 = mc;
                  mc.playerController.windowClick(0, n, 1, ClickType.PICKUP, Minecraft.player);
               }
            }
         }

         if (this.modes.is("Не выкидывать шар") && InventoryUtil.getSlot(Items.PLAYER_HEAD) == -1 && bl && itemStack.getItem() == Items.PLAYER_HEAD) {
            var10005 = mc;
            mc.playerController.windowClick(0, n, 1, ClickType.PICKUP, Minecraft.player);
         }

         if (this.modes.is("Не выкидывать осколки") && this.hasBukkitDisplayAndCustomModel(itemStack)) {
            var10005 = mc;
            mc.playerController.windowClick(0, n, 1, ClickType.PICKUP, Minecraft.player);
         }

      }
   }

   @EventTarget
   public void onEvent(EventPacket e) {
      if (this.modes.is("Не перемещать слоты") && !PickItemFixHandler.isFixActive()) {
         IPacket var3 = e.getPacket();
         if (var3 instanceof SHeldItemChangePacket) {
            SHeldItemChangePacket fix = (SHeldItemChangePacket)var3;
            if (e.isReceive()) {
               int var10000 = fix.getHeldItemHotbarIndex();
               Minecraft var10001 = mc;
               if (var10000 != Minecraft.player.inventory.currentItem) {
                  Minecraft var5 = mc;
                  if (Minecraft.player.inventory.currentItem >= 8) {
                     var5 = mc;
                     var10000 = Minecraft.player.inventory.currentItem - 1;
                  } else {
                     var5 = mc;
                     var10000 = Minecraft.player.inventory.currentItem + 1;
                  }

                  int newSlot = MathHelper.clamp(var10000, 0, 8);
                  var5 = mc;
                  Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(newSlot));
                  var5 = mc;
                  Minecraft var10003 = mc;
                  Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
                  e.setCancelled(true);
               }
            }
         }
      }

   }

   private boolean hasBukkitDisplayAndCustomModel(ItemStack stack) {
      if (stack != null && stack.hasTag()) {
         CompoundNBT tag = stack.getTag();
         if (tag == null) {
            return false;
         } else {
            boolean hasPBV = tag.contains("PublicBukkitValues", 10);
            boolean hasDisplay = tag.contains("display", 10);
            boolean hasCmd = tag.contains("CustomModelData", 3) || hasDisplay && tag.getCompound("display").contains("CustomModelData", 3);
            return hasPBV && hasDisplay && hasCmd;
         }
      } else {
         return false;
      }
   }
}
