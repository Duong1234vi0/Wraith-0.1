package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1701;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2604;
import net.minecraft.class_2626;
import net.minecraft.class_2815;
import net.minecraft.class_2846;
import net.minecraft.class_2868;
import net.minecraft.class_476;
import net.minecraft.class_2846.class_2847;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.movement.Blink;
import unique.core.setting.Setting;
import unique.core.setting.impl.Bind;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.math.PredictUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;
import unique.core.utility.world.ExplosionUtility;

public final class AutoTotem extends Module {
   private final Setting<AutoTotem.OffHand> offhand = new Setting<>("Item", AutoTotem.OffHand.Totem);
   private final Setting<BooleanSettingGroup> bindSwap = new Setting<>(
      "BindSwap", new BooleanSettingGroup(false), v -> this.offhand.is(AutoTotem.OffHand.Totem)
   );
   private final Setting<AutoTotem.Swap> swapMode = new Setting<>("Swap", AutoTotem.Swap.GappleShield).addToGroup(this.bindSwap);
   private final Setting<Bind> swapButton = new Setting<>("SwapButton", new Bind(280, false, false)).addToGroup(this.bindSwap);
   private final Setting<Float> health = new Setting<>("HP", 3.5F, 0.0F, 36.0F);
   private final Setting<Float> elytraHp = new Setting<>("ElytraHP", 10.0F, 0.0F, 36.0F);
   private final Setting<Float> shieldGappleHp = new Setting<>("ShieldGappleHP", 16.0F, 0.0F, 20.0F, v -> this.offhand.getValue() == AutoTotem.OffHand.Shield);
   private final Setting<Boolean> calcAbsorption = new Setting<>("Absorption", true);
   private final Setting<Boolean> legitStop = new Setting<>("LegitStop", false);
   private final Setting<Boolean> resetAttackCooldown = new Setting<>("ResetAttackCooldown", false);
   private final Setting<SettingGroup> safety = new Setting<>("Safety", new SettingGroup(false, 0));
   private final Setting<Boolean> hotbarFallback = new Setting<>("HotbarFallback", false).addToGroup(this.safety);
   private final Setting<Boolean> fallBackCalc = new Setting<>("FallBackCalc", false, v -> this.hotbarFallback.getValue()).addToGroup(this.safety);
   private final Setting<Boolean> onElytra = new Setting<>("OnElytra", false).addToGroup(this.safety);
   private final Setting<Boolean> onFall = new Setting<>("OnFall", false).addToGroup(this.safety);
   private final Setting<Boolean> onCrystal = new Setting<>("OnCrystal", false).addToGroup(this.safety);
   private final Setting<Boolean> onObsidianPlace = new Setting<>("OnObsidianPlace", false).addToGroup(this.safety);
   private final Setting<Boolean> onCrystalInHand = new Setting<>("OnCrystalInHand", false).addToGroup(this.safety);
   private final Setting<Boolean> onMinecartTnt = new Setting<>("OnMinecartTNT", true).addToGroup(this.safety);
   private final Setting<Boolean> onAnchor = new Setting<>("OnAnchor", false).addToGroup(this.safety);
   private final Setting<Boolean> onMace = new Setting<>("OnMace", false).addToGroup(this.safety);
   private int delay;
   private final Timer bindDelay = new Timer();
   private class_1792 prevItem;
   private boolean scheduledSwap;
   private int scheduledSlot;
   private long scheduledActionAt;

   public AutoTotem() {
      super("AutoTotem", Module.Category.COMBAT);
   }

   @EventHandler
   public void onSync(EventSync e) {
      this.handleScheduledSwap();
      this.swapTo(this.getItemSlot());
      this.delay--;
   }

   private void handleScheduledSwap() {
      if (this.scheduledSwap) {
         if (System.currentTimeMillis() >= this.scheduledActionAt) {
            if (this.scheduledSlot != -1) {
               clickSlot(this.scheduledSlot);
               clickSlot(45);
               clickSlot(this.scheduledSlot);
               this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
            }

            this.scheduledSwap = false;
            this.scheduledSlot = -1;
            this.scheduledActionAt = -1L;
            this.delay = (int)(2.0F + Managers.SERVER.getPing() / 25.0F);
         }
      }
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2604 spawn && spawn.method_11169() == class_1299.field_6110) {
         class_243 pos = new class_243(spawn.method_11175(), spawn.method_11174(), spawn.method_11176());
         if (this.getPlayerPos().method_1025(pos) < 36.0) {
            if (this.hotbarFallback.getValue()) {
               if (this.fallBackCalc.getValue()) {
                  float dmg = ExplosionUtility.getExplosionDamageWPredict(
                     pos, mc.field_1724, PredictUtility.createBox(this.getPlayerPos(), mc.field_1724), false
                  );
                  if (dmg < this.getHealth() + 4.0F) {
                     return;
                  }
               }

               this.runInstant();
            }

            if (this.onCrystal.getValue()) {
               float dmg = ExplosionUtility.getExplosionDamageWPredict(pos, mc.field_1724, PredictUtility.createBox(this.getPlayerPos(), mc.field_1724), false);
               if (this.getHealth() - dmg < 0.5F) {
                  this.swapTo(this.findTotem());
               }
            }
         }
      }

      if (e.getPacket() instanceof class_2626 update
         && update.method_11308().method_26204() == class_2246.field_10540
         && this.onObsidianPlace.getValue()
         && this.getPlayerPos().method_1025(update.method_11309().method_46558()) < 36.0
         && this.delay <= 0) {
         this.runInstant();
      }
   }

   private float getHealth() {
      return mc.field_1724.method_6032() + (this.calcAbsorption.getValue() ? mc.field_1724.method_6067() : 0.0F);
   }

   private void runInstant() {
      SearchInvResult hotbar = InventoryUtility.findItemInHotBar(class_1802.field_8288);
      SearchInvResult inv = InventoryUtility.findItemInInventory(class_1802.field_8288);
      if (hotbar.found()) {
         hotbar.switchTo();
         this.delay = 20;
      } else if (inv.found()) {
         int slot = inv.slot() >= 36 ? inv.slot() - 36 : inv.slot();
         if (!this.hotbarFallback.getValue()) {
            this.swapTo(slot);
         } else {
            mc.field_1761.method_2916(slot);
         }

         this.delay = 20;
      }
   }

   public void swapTo(int slot) {
      if (slot != -1 && this.delay <= 0) {
         if (!(mc.field_1755 instanceof class_476)) {
            int prev = mc.field_1724.method_31548().field_7545;
            if (slot >= 9) {
               if (this.legitStop.getValue()) {
                  this.scheduledSwap = true;
                  this.scheduledSlot = slot;
                  this.scheduledActionAt = System.currentTimeMillis() + 60L;
               } else {
                  clickSlot(slot);
                  clickSlot(45);
                  clickSlot(slot);
                  this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
               }
            } else {
               this.sendPacket(new class_2868(slot));
               mc.field_1724.method_31548().field_7545 = slot;
               this.sendPacket(new class_2846(class_2847.field_12969, class_2338.field_10980, class_2350.field_11033));
               this.sendPacket(new class_2868(prev));
               mc.field_1724.method_31548().field_7545 = prev;
               if (this.resetAttackCooldown.getValue()) {
                  mc.field_1724.method_7350();
               }
            }

            this.delay = (int)(2.0F + Managers.SERVER.getPing() / 25.0F);
         }
      }
   }

   private int findTotem() {
      for (int i = 0; i < 36; i++) {
         if (mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8288) {
            return i;
         }
      }

      return -1;
   }

   public int getItemSlot() {
      class_1792 offhandItem = mc.field_1724.method_6079().method_7909();
      class_1792 item = null;
      if (this.offhand.getValue() == AutoTotem.OffHand.Totem) {
         if (offhandItem != class_1802.field_8288 && !mc.field_1724.method_6079().method_7960()) {
            this.prevItem = offhandItem;
         }

         item = this.prevItem;
         if (this.bindSwap.getValue().isEnabled() && this.isKeyPressed(this.swapButton) && this.bindDelay.every(250L)) {
            switch ((AutoTotem.Swap)this.swapMode.getValue()) {
               case GappleShield:
                  item = offhandItem == class_1802.field_8255 ? class_1802.field_8463 : class_1802.field_8255;
                  break;
               case BallShield:
                  item = offhandItem == class_1802.field_8255 ? class_1802.field_8575 : class_1802.field_8255;
                  break;
               case GappleBall:
                  item = offhandItem == class_1802.field_8463 ? class_1802.field_8575 : class_1802.field_8463;
                  break;
               case BallTotem:
                  item = offhandItem == class_1802.field_8288 ? class_1802.field_8575 : class_1802.field_8288;
            }

            this.prevItem = item;
         }
      } else if (this.offhand.getValue() == AutoTotem.OffHand.Shield) {
         if (this.getHealth() > this.shieldGappleHp.getValue()) {
            item = class_1802.field_8255;
         } else {
            item = class_1802.field_8463;
         }
      }

      if (this.getHealth() <= this.health.getValue()) {
         item = class_1802.field_8288;
      }

      if (mc.field_1724.method_6128() && this.getHealth() <= this.elytraHp.getValue()) {
         item = class_1802.field_8288;
      }

      if (this.onFall.getValue()) {
         float fallDmg = (mc.field_1724.field_6017 - 3.0F) / 2.0F + 3.5F;
         if (this.getHealth() - fallDmg < 0.5F) {
            item = class_1802.field_8288;
         }
      }

      if (this.onElytra.getValue() && mc.field_1724.method_6128()) {
         item = class_1802.field_8288;
      }

      if (this.onCrystalInHand.getValue() || this.onMace.getValue()) {
         for (class_1657 pl : Managers.ASYNC.getAsyncPlayers()) {
            if (pl != mc.field_1724 && !Managers.FRIEND.isFriend(pl) && this.getPlayerPos().method_1025(pl.method_19538()) < 36.0) {
               if (this.onCrystalInHand.getValue()
                  && (pl.method_6047().method_7909() == class_1802.field_8301 || pl.method_6079().method_7909() == class_1802.field_8301)) {
                  item = class_1802.field_8288;
               }

               if (this.onMace.getValue() && pl.method_6047().method_7909() == class_1802.field_49814) {
                  item = class_1802.field_8288;
               }
            }
         }
      }

      for (class_1297 entity : mc.field_1687.method_18112()) {
         if (entity.method_5805() && !(this.getPlayerPos().method_1025(entity.method_19538()) > 36.0)) {
            if (this.onCrystal.getValue() && entity instanceof class_1511) {
               float dmg = ExplosionUtility.getExplosionDamageWPredict(
                  entity.method_19538(), mc.field_1724, PredictUtility.createBox(this.getPlayerPos(), mc.field_1724), false
               );
               if (this.getHealth() - dmg < 0.5F) {
                  item = class_1802.field_8288;
                  break;
               }
            }

            if (this.onMinecartTnt.getValue() && entity instanceof class_1701) {
               item = class_1802.field_8288;
               break;
            }
         }
      }

      if (this.onAnchor.getValue()) {
         class_2338 pos = mc.field_1724.method_24515();

         for (int x = -6; x <= 6; x++) {
            for (int y = -6; y <= 6; y++) {
               for (int z = -6; z <= 6; z++) {
                  if (mc.field_1687.method_8320(pos.method_10069(x, y, z)).method_26204() == class_2246.field_23152) {
                     item = class_1802.field_8288;
                  }
               }
            }
         }
      }

      if (item != null && mc.field_1724.method_6079().method_7909() != item) {
         for (int i = 0; i < 36; i++) {
            if (mc.field_1724.method_31548().method_5438(i).method_7909() == item) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   private class_243 getPlayerPos() {
      return ModuleManager.blink.isEnabled() ? Blink.lastPos : mc.field_1724.method_19538();
   }

   private static enum OffHand {
      Totem,
      Shield;
   }

   private static enum Swap {
      GappleShield,
      BallShield,
      GappleBall,
      BallTotem;
   }
}
