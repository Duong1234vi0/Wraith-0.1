package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.network.play.server.SOpenWindowPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventInput;
import sky.core.events.EventKey;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.ServerUtil;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class ReallyWorldHelper extends Module {
   private final BooleanSetting close = new BooleanSetting("Закрывать меню", false);
   private final BooleanSetting filter = new BooleanSetting("Фильтр чата", false);
   private final BindSetting antiPoletKey = new BindSetting("Анти Полет");
   private final BooleanSetting bindTrap = new BooleanSetting("Бинд на трапку", false);
   private final BindSetting bindTrapKey;
   private final BooleanSetting bindTrapLegit;
   private final BooleanSetting grimBypass;
   private boolean swapRequested;
   private int tickSwap;
   private int queuedReturnSlot;
   private int returnDelayTicks;
   private boolean returnSwapRequested;
   private final Set bannedWords;

   public ReallyWorldHelper() {
      super("Really World Helper", "У данного модуля нет описания!", Category.Miscellaneous);
      BooleanSetting var10004 = this.bindTrap;
      Objects.requireNonNull(var10004);
      this.bindTrapKey = new BindSetting("Кнопка трапки", var10004::get);
      Boolean var1 = false;
      BooleanSetting var10005 = this.bindTrap;
      Objects.requireNonNull(var10005);
      this.bindTrapLegit = new BooleanSetting("Легитный", var1, var10005::get);
      this.grimBypass = new BooleanSetting("Обход Grim", true);
      this.swapRequested = false;
      this.tickSwap = 0;
      this.queuedReturnSlot = -1;
      this.returnDelayTicks = 0;
      this.returnSwapRequested = false;
      this.bannedWords = new HashSet(Arrays.asList("акриен(а|у|ом|е|чик)?", "рич(а|у|ом|ей|е)?", "ньюкод(ом|а|у|ами|ик|е)?", "экспенсив(ом|а|у|ами|е)?", "импакт(ом|а|у|ами|ик|е)?", "экселлент(ом|а|у|ами|ик|е)?", "экселент(ом|а|у|ами|ик)?", "катлаван(ом|а|у|ами|чик)?", "катлован(ом|а|у|ами|чик)?", "целестиал(ом|а|у|ами|е)?", "целк(ой|а|у|ами|очка|е)?", "матикс(ом|а|у|ами|е)?", "инерти(я|ей|ю|ями|е)?", "эксп(а|ой|ою|у|уличка|е)?", "флюгер(ом|а|у|ами)?", "рикер(а|у|ом|очек)?", "фанпе(й|ю|я|ем|е|йчик)?", "вексайд(ом|а|у|ами|ик|е)?", "нурсултан(а|у|е|ом|чик)?", "нурик(а|у|ом|е)?", "нурлан(а|у|ом|чик|е)?", "векс(ом|у|а|ами|ик|е)?", "релейк(ом|у|а|ами|е)?", "арбуз(ом|а|у|ами|ик|е)?", "вилд(ом|у|а|ами|ик|е)?", "фантайм(е|а|у)?", "холик(е|а|у)?", "холиворлд(а|у|е)?", "рокстар(ом|а|у|ами|чик|е)?", "рогалик(а|у|ом|е)?", "тандерхак(ом|у|и|ами|а|е)?", "ликвидбаунс(а|у|ами|е)?", "expensive", "celestial", "newcode", "arbuz", "akrien", "nursultan", "relake", "wild", "wurst", "catlovan", "excellent", "rockstar", "catlavan", "impact", "matix", "inertia", "wex", "wexside", "nurik", "nurlan", "rich", "funpay", "fluger", "riker", "funtime", "holyworld", "wwe", "hvh", "rogalik", "thunderhack", "liquidbounce"));
      this.bindTrapKey.set(-98);
      this.addSettings(new Setting[]{this.close, this.filter, this.antiPoletKey, this.bindTrap, this.bindTrapKey, this.bindTrapLegit, this.grimBypass});
   }

   @EventTarget
   private void onPacket(EventPacket e) {
      IPacket var3 = e.getPacket();
      if (var3 instanceof SOpenWindowPacket packet) {
         if ((Boolean)this.close.get() && packet.getTitle().getString().contains("ꈁꀀꈂꌁꈂꀁ§0ꈃꄀ")) {
            Minecraft var10000 = mc;
            if (Minecraft.player.ticksExisted < 100) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CCloseWindowPacket(packet.getWindowId()));
               e.setCancelled(true);
            }
         }
      }

      if (e.getPacket() instanceof CChatMessagePacket && (Boolean)this.filter.get()) {
         String message = ((CChatMessagePacket)e.getPacket()).getMessage().toLowerCase();
         boolean banwords = false;
         Iterator var4 = this.bannedWords.iterator();

         while(var4.hasNext()) {
            String pattern = (String)var4.next();
            if (message.matches(".*" + pattern + ".*")) {
               banwords = true;
               break;
            }
         }

         if (banwords) {
            e.setCancelled(true);
            ChatUtil.addText("В вашем сообщении было найдено запретное слово, отправка сообщения отменена!");
         }
      }

   }

   @EventTarget
   private void onKey(EventKey event) {
      if (event != null && event.isHold()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            if (event.getKey() == (Integer)this.antiPoletKey.get()) {
               this.tickSwap = 2;
               this.swapRequested = true;
            }

            if ((Boolean)this.bindTrap.get() && event.getKey() == (Integer)this.bindTrapKey.get()) {
               this.handleBindTrap();
            }

            return;
         }
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.connection != null) {
            if (this.tickSwap > 0) {
               --this.tickSwap;
            }

            if (this.returnDelayTicks > 0) {
               --this.returnDelayTicks;
               if (this.returnDelayTicks == 0 && this.queuedReturnSlot != -1) {
                  this.returnSwapRequested = true;
                  this.tickSwap = 2;
               }
            }

            if (this.swapRequested && (!(Boolean)this.grimBypass.get() || !MoveUtil.isMoving())) {
               this.executeAntiPolet();
               this.swapRequested = false;
            }

            if (this.returnSwapRequested && this.queuedReturnSlot != -1 && (!(Boolean)this.grimBypass.get() || !MoveUtil.isMoving())) {
               InventoryUtil.moveItem(45, this.queuedReturnSlot);
               this.returnSwapRequested = false;
               this.queuedReturnSlot = -1;
            }

            return;
         }
      }

   }

   @EventTarget
   private void onInput(EventInput eventInput) {
      if (eventInput != null) {
         if ((Boolean)this.grimBypass.get() && (this.tickSwap > 0 || this.returnSwapRequested)) {
            eventInput.setForward(0.0F);
            eventInput.setStrafe(0.0F);
            eventInput.setSneak(false);
            eventInput.setJump(false);
         }

      }
   }

   private void executeAntiPolet() {
      if (InventoryUtil.getSlot(Items.FIREWORK_STAR) == -1) {
         ChatUtil.addText("Нема анти полета падла!");
      } else {
         int slot = InventoryUtil.antipoletToOffhand(Items.FIREWORK_STAR);
         if (slot != -1) {
            this.queuedReturnSlot = slot;
            this.returnDelayTicks = 6;
         }
      }
   }

   private void handleBindTrap() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (!Minecraft.player.getCooldownTracker().hasCooldown(Items.HEART_OF_THE_SEA) && InventoryUtil.getItemSlot(Items.HEART_OF_THE_SEA) != -1) {
            if ((Boolean)this.bindTrapLegit.get()) {
               InventoryUtil.inventorySwapClick(Items.HEART_OF_THE_SEA);
            } else {
               if (!ServerUtil.isConnectedToServer("funtime") && !ServerUtil.isConnectedToServer("spooky")) {
                  InventoryUtil.inventorySwapClick(Items.HEART_OF_THE_SEA);
                  this.useItem(Hand.MAIN_HAND);
               } else {
                  int hbSlot = this.findItem(Items.HEART_OF_THE_SEA, true);
                  int invSlot = this.findItem(Items.HEART_OF_THE_SEA, false);
                  int slot = this.findAndThrowItem(hbSlot, invSlot);
                  if (slot > 8) {
                     mc.playerController.pickItem(slot);
                  }

                  this.useItem(Hand.MAIN_HAND);
               }

            }
         }
      }
   }

   private int findItem(Item item, boolean hotbarOnly) {
      for(int i = hotbarOnly ? 0 : 9; i < (hotbarOnly ? 9 : 36); ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() == item) {
            return i;
         }
      }

      return -1;
   }

   private int findAndThrowItem(int hbSlot, int invSlot) {
      Minecraft var10000;
      if (hbSlot != -1) {
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(hbSlot));
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
         var10000 = mc;
         Minecraft var10003 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
         return hbSlot;
      } else if (invSlot != -1) {
         mc.playerController.pickItem(invSlot);
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
         return invSlot;
      } else {
         return -1;
      }
   }

   private void useItem(Hand hand) {
      Minecraft var10000 = mc;
      Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(hand));
   }
}
