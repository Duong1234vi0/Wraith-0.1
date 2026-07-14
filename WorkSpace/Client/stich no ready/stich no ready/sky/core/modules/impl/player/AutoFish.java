package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.utils.math.TimeUtil;

public class AutoFish extends Module {
   private final BooleanSetting takeRod = new BooleanSetting("Автоматически брать удочку", true);
   private boolean isCached = false;
   private boolean needCached = false;
   private int rodHotbarSlot = -1;
   private final TimeUtil timer = new TimeUtil();

   public AutoFish() {
      super("AutoFish", "Автоматизирует процесс рыбалки", Category.Player);
      this.addSettings(new Setting[]{this.takeRod});
   }

   public void onDisable() {
      this.isCached = false;
      this.needCached = false;
      this.rodHotbarSlot = -1;
      this.timer.reset();
      super.onDisable();
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if ((Boolean)this.takeRod.get() && this.rodHotbarSlot == -1) {
            this.findBestFishingRodInHotbar();
         }

         if (this.rodHotbarSlot != -1) {
            var10000 = mc;
            if (Minecraft.player.inventory.currentItem != this.rodHotbarSlot) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(this.rodHotbarSlot));
               var10000 = mc;
               Minecraft.player.inventory.currentItem = this.rodHotbarSlot;
            }
         }

         if (this.isCached && this.timer.hasReached(600.0)) {
            this.useFishingRod();
            this.isCached = false;
            this.needCached = true;
            this.timer.reset();
         }

         if (this.needCached && this.timer.hasReached(300.0)) {
            this.useFishingRod();
            this.needCached = false;
            this.timer.reset();
         }

      }
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof SPlaySoundEffectPacket) {
            SPlaySoundEffectPacket packet = (SPlaySoundEffectPacket)var3;
            if (packet.getSound() == SoundEvents.ENTITY_FISHING_BOBBER_SPLASH) {
               this.isCached = true;
               this.timer.reset();
            }
         }

      }
   }

   private void useFishingRod() {
      if (this.rodHotbarSlot != -1) {
         Minecraft var10000 = mc;
         if (Minecraft.player.inventory.getStackInSlot(this.rodHotbarSlot).getItem() instanceof FishingRodItem) {
            var10000 = mc;
            if (Minecraft.player.inventory.currentItem != this.rodHotbarSlot) {
               var10000 = mc;
               Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(this.rodHotbarSlot));
               var10000 = mc;
               Minecraft.player.inventory.currentItem = this.rodHotbarSlot;
            }

            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.MAIN_HAND));
            var10000 = mc;
            Minecraft.player.swingArm(Hand.MAIN_HAND);
         }
      }

   }

   private void findBestFishingRodInHotbar() {
      int bestRodSlot = -1;
      int maxEnchantments = -1;

      for(int i = 0; i < 9; ++i) {
         Minecraft var10000 = mc;
         ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
         if (stack.getItem() instanceof FishingRodItem) {
            int enchantmentCount = EnchantmentHelper.getEnchantments(stack).size();
            if (enchantmentCount > maxEnchantments) {
               maxEnchantments = enchantmentCount;
               bestRodSlot = i;
            }
         }
      }

      if (bestRodSlot != -1) {
         this.rodHotbarSlot = bestRodSlot;
      }

   }
}
