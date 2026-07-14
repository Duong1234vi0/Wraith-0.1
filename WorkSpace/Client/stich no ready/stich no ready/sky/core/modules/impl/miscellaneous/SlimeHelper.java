package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.network.play.server.SOpenWindowPacket;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
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
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.utils.misc.ChatUtil;

public class SlimeHelper extends Module {
   private final BooleanSetting closeMenu = new BooleanSetting("Закрывать меню", false);
   private final BooleanSetting chatFilter = new BooleanSetting("Фильтр чата", false);
   private final BindSetting antiFlyBind = new BindSetting("Анти Полет");
   private final BooleanSetting trapBind = new BooleanSetting("Бинд на трапку", false);
   private final BindSetting trapKey = new BindSetting("Кнопка трапки");
   private final BooleanSetting legitTrap = new BooleanSetting("Легитный", false);
   private final BooleanSetting grimBypass = new BooleanSetting("Обход Grim", true);
   private final BooleanSetting autoFix = new BooleanSetting("AutoFix", false);
   private final BooleanSetting autoGod = new BooleanSetting("AutoGod", false);
   private final BooleanSetting autoShift = new BooleanSetting("AutoShift", false);
   private final BindSetting cordsBind = new BindSetting("Выброс корд");
   private final BooleanSetting clan = new BooleanSetting("Клан", false);
   private final StringSetting discord = new StringSetting("Дискорд для клана");
   private long lastClanMsg = 0L;
   private static final Pattern PRIVATE_MSG_PATTERN = Pattern.compile("\\[([A-Za-z0-9_]{2,16})\\s*->\\s*я]\\s*(.*)");
   private boolean antiFlyActive = false;
   private int antiFlyDelay = 0;
   private int trapSlot = -1;
   private int trapDelay = 0;
   private boolean trapReady = false;
   private boolean wasPvP = false;
   private long pvpEndTime = 0L;
   private boolean wasGod = false;
   private long godEndTime = 0L;
   private long shiftLastTime = 0L;
   private static final long SHIFT_INTERVAL = 33000L;
   private int shiftTicks = 0;
   private Boolean currentPvP = null;
   private long lastPvPCheck = 0L;
   private static final long CHECK_INTERVAL = 500L;

   public SlimeHelper() {
      super("SlimeHelper", "Помощник для SlimeWorld", Category.Miscellaneous);
      this.antiFlyBind.set(-98);
      this.trapKey.set(-98);
      this.cordsBind.set(-1);
      this.addSettings(new Setting[]{this.closeMenu, this.chatFilter, this.antiFlyBind, this.trapBind, this.trapKey, this.legitTrap, this.grimBypass, this.autoFix, this.autoGod, this.autoShift, this.cordsBind, this.clan, this.discord});
   }

   public void onEnable() {
      super.onEnable();
      this.antiFlyActive = false;
      this.antiFlyDelay = 0;
      this.trapSlot = -1;
      this.trapDelay = 0;
      this.trapReady = false;
      this.wasPvP = false;
      this.pvpEndTime = 0L;
      this.wasGod = false;
      this.godEndTime = 0L;
      this.shiftLastTime = 0L;
      this.shiftTicks = 0;
      this.currentPvP = null;
      this.lastPvPCheck = 0L;
      this.lastClanMsg = 0L;
   }

   public void onDisable() {
      super.onDisable();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         mc.gameSettings.keyBindSneak.setPressed(false);
      }

      this.shiftTicks = 0;
   }

   @EventTarget
   private void onPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         IPacket packet = event.getPacket();
         if ((Boolean)this.closeMenu.get() && packet instanceof SOpenWindowPacket) {
            SOpenWindowPacket windowPacket = (SOpenWindowPacket)packet;
            String title = windowPacket.getTitle().getString();
            if (title.contains("??????§0??")) {
               var10000 = mc;
               if (Minecraft.player.ticksExisted < 100) {
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CCloseWindowPacket(windowPacket.getWindowId()));
                  event.setCancelled(true);
               }
            }
         }

         if ((Boolean)this.clan.get() && packet instanceof SChatPacket) {
            this.handleClanMessage((SChatPacket)packet);
         }

      }
   }

   @EventTarget
   private void onKey(EventKey event) {
      if (event != null && event.isHold()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            int key = event.getKey();
            if (key == (Integer)this.antiFlyBind.get()) {
               this.antiFlyDelay = 2;
               this.antiFlyActive = true;
            }

            if (key == (Integer)this.cordsBind.get()) {
               var10000 = mc;
               int x = (int)Minecraft.player.getPosX();
               var10000 = mc;
               int z = (int)Minecraft.player.getPosZ();
               var10000 = mc;
               Minecraft.player.sendChatMessage("!корды " + x + " " + z);
            }

            if ((Boolean)this.trapBind.get() && key == (Integer)this.trapKey.get()) {
               this.useTrap();
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
            if ((Boolean)this.autoFix.get()) {
               this.handleAutoFix();
            }

            if ((Boolean)this.autoGod.get()) {
               this.handleAutoGod();
            }

            if ((Boolean)this.autoShift.get()) {
               this.handleAutoShift();
            }

            if (this.antiFlyDelay > 0) {
               --this.antiFlyDelay;
            }

            if (this.trapDelay > 0) {
               --this.trapDelay;
               if (this.trapDelay == 0 && this.trapSlot != -1) {
                  this.trapReady = true;
                  this.antiFlyDelay = 2;
               }
            }

            if (this.antiFlyActive) {
               label44: {
                  if ((Boolean)this.grimBypass.get()) {
                     var10000 = mc;
                     if (Minecraft.player.isInWater()) {
                        break label44;
                     }
                  }

                  this.useAntiFly();
                  this.antiFlyActive = false;
               }
            }

            if (this.trapReady && this.trapSlot != -1) {
               if ((Boolean)this.grimBypass.get()) {
                  var10000 = mc;
                  if (Minecraft.player.isInWater()) {
                     return;
                  }
               }

               this.switchToSlot(this.trapSlot);
               this.trapReady = false;
               this.trapSlot = -1;
            }

            return;
         }
      }

   }

   @EventTarget
   private void onInput(EventInput event) {
      if (event != null) {
         if ((Boolean)this.grimBypass.get() && (this.antiFlyDelay > 0 || this.trapReady)) {
            event.setForward(0.0F);
            event.setStrafe(0.0F);
            event.setSneak(false);
            event.setJump(false);
         }

      }
   }

   private void handleAutoFix() {
      long now = System.currentTimeMillis();
      if (now - this.lastPvPCheck > 500L || this.currentPvP == null) {
         this.currentPvP = this.isPvPScoreboard();
         this.lastPvPCheck = now;
      }

      boolean isPvP = this.currentPvP;
      if (this.wasPvP && !isPvP) {
         this.pvpEndTime = now + 1100L;
      }

      this.wasPvP = isPvP;
      if (this.pvpEndTime != 0L && now >= this.pvpEndTime) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.connection != null) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CChatMessagePacket("/fix all"));
               ChatUtil.addText("§aAutoFix: §fпочинено!");
               this.pvpEndTime = 0L;
            }
         }
      }

   }

   private void handleAutoGod() {
      long now = System.currentTimeMillis();
      if (now - this.lastPvPCheck > 500L || this.currentPvP == null) {
         this.currentPvP = this.isPvPScoreboard();
         this.lastPvPCheck = now;
      }

      boolean isPvP = this.currentPvP;
      if (this.wasGod && !isPvP) {
         this.godEndTime = now + 500L;
      }

      this.wasGod = isPvP;
      if (this.godEndTime != 0L && now >= this.godEndTime) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.connection != null) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CChatMessagePacket("/essentials:god"));
               ChatUtil.addText("§aAutoGod: §fвключен!");
               this.godEndTime = 0L;
            }
         }
      }

   }

   private void handleAutoShift() {
      long now = System.currentTimeMillis();
      if (now - this.shiftLastTime >= 33000L) {
         mc.gameSettings.keyBindSneak.setPressed(true);
         this.shiftTicks = 2;
         this.shiftLastTime = now;
      }

      if (this.shiftTicks > 0) {
         --this.shiftTicks;
         if (this.shiftTicks == 0) {
            mc.gameSettings.keyBindSneak.setPressed(false);
         }
      }

   }

   private boolean isPvPScoreboard() {
      try {
         if (mc.world == null) {
            return false;
         }

         Scoreboard scoreboard = mc.world.getScoreboard();
         if (scoreboard == null) {
            return false;
         }

         ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
         if (objective != null) {
            String name = objective.getDisplayName().getString().toLowerCase();
            return name.contains("pvp");
         }
      } catch (Exception var4) {
      }

      return false;
   }

   private void useAntiFly() {
      int slot = this.findItem(Items.FIREWORK_STAR);
      if (slot == -1) {
         ChatUtil.addText("§cНема анти полета падла!");
      } else {
         Minecraft var10000 = mc;
         int currentSlot = Minecraft.player.inventory.currentItem;
         this.trapSlot = currentSlot;
         this.trapDelay = 6;
      }
   }

   private void useTrap() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (!Minecraft.player.getCooldownTracker().hasCooldown(Items.HEART_OF_THE_SEA)) {
            int slot = this.findItem(Items.HEART_OF_THE_SEA);
            if (slot != -1) {
               if ((Boolean)this.legitTrap.get()) {
                  this.switchToSlot(slot);
                  var10000 = mc;
                  Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
               } else {
                  int mainSlot = this.findItemInInventory(Items.HEART_OF_THE_SEA, true);
                  int hotbarSlot = this.findItemInInventory(Items.HEART_OF_THE_SEA, false);
                  if (mainSlot != -1) {
                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(mainSlot));
                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
                     var10000 = mc;
                     Minecraft var10003 = mc;
                     Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
                  } else if (hotbarSlot != -1) {
                     if (mc.playerController != null) {
                        mc.playerController.pickItem(hotbarSlot);
                     }

                     var10000 = mc;
                     Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
                  }

               }
            }
         }
      }
   }

   private int findItem(Item item) {
      for(int i = 0; i < 36; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() == item) {
            return i;
         }
      }

      return -1;
   }

   private int findItemInInventory(Item item, boolean mainInventory) {
      int start = mainInventory ? 0 : 9;
      int end = mainInventory ? 9 : 36;

      for(int i = start; i < end; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (!stack.isEmpty() && stack.getItem() == item) {
            return i;
         }
      }

      return -1;
   }

   private void switchToSlot(int slot) {
      if (slot >= 0 && slot < 9) {
         Minecraft var10000 = mc;
         Minecraft.player.inventory.currentItem = slot;
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(slot));
      } else if (slot >= 9 && slot < 36 && mc.playerController != null) {
         mc.playerController.pickItem(slot);
      }

   }

   private void handleClanMessage(SChatPacket packet) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         if (Minecraft.player.connection != null) {
            String message = packet.getChatComponent().getString();
            Matcher matcher = PRIVATE_MSG_PATTERN.matcher(message);
            if (!matcher.find()) {
               return;
            }

            String sender = matcher.group(1);
            Minecraft var10001 = mc;
            if (sender.equalsIgnoreCase(Minecraft.player.getGameProfile().getName())) {
               return;
            }

            String text = matcher.group(2) == null ? "" : matcher.group(2).trim().toLowerCase();
            text = text.replaceAll("[!?.,]+$", "").trim();
            boolean isClanRequest = text.equals("можно в клан") || text.equals("в клан можно") || text.equals("клан можно") || text.equals("можна в клан") || text.equals("в клан можна") || text.equals("клан можна");
            if (!isClanRequest) {
               return;
            }

            long now = System.currentTimeMillis();
            if (now - this.lastClanMsg < 1500L) {
               return;
            }

            this.lastClanMsg = now;
            String discordId = (String)this.discord.get();
            if (discordId == null || discordId.trim().isEmpty()) {
               discordId = "crohxdyt";
            }

            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CChatMessagePacket("/m " + sender + " хочешь в клан пиши в дс " + discordId.trim()));
            return;
         }
      }

   }
}
