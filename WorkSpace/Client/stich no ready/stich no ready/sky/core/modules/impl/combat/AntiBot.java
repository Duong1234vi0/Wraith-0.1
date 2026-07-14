package sky.core.modules.impl.combat;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;

public class AntiBot extends Module {
   public static final List bot = new ArrayList();
   private final ModeSetting mode = new ModeSetting("Обход", "ReallyWorld", new String[]{"ReallyWorld", "Matrix"});

   public AntiBot() {
      super("Anti Bot", "Определяет и метит ботов, вызванных системой античита", Category.Combat);
      this.addSettings(new Setting[]{this.mode});
   }

   @EventTarget
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         Minecraft var10001;
         Iterator var2;
         PlayerEntity player;
         if (this.mode.is("Matrix")) {
            var2 = mc.world.getPlayers().iterator();

            while(var2.hasNext()) {
               player = (PlayerEntity)var2.next();
               var10001 = mc;
               if (!player.equals(Minecraft.player) && !player.getUniqueID().equals(PlayerEntity.getOfflineUUID(player.getName().getString())) && !bot.contains(player)) {
                  bot.add(player);
               }
            }

         } else {
            var2 = mc.world.getPlayers().iterator();

            while(true) {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  player = (PlayerEntity)var2.next();
                  var10001 = mc;
               } while(player.equals(Minecraft.player));

               boolean isBot = false;
               if (this.mode.is("ReallyWorld")) {
                  boolean hasValidArmor = player.inventory.armorInventory.stream().allMatch((armorItem) -> {
                     return armorItem.getItem() != Items.AIR && armorItem.isEnchantable() && !armorItem.isDamaged();
                  });
                  boolean hasValidEquipment = player.getHeldItemOffhand().getItem() == Items.AIR && player.inventory.armorInventory.stream().anyMatch((armorItem) -> {
                     return armorItem.getItem() == Items.LEATHER_BOOTS || armorItem.getItem() == Items.LEATHER_LEGGINGS || armorItem.getItem() == Items.LEATHER_CHESTPLATE || armorItem.getItem() == Items.LEATHER_HELMET || armorItem.getItem() == Items.IRON_BOOTS || armorItem.getItem() == Items.IRON_LEGGINGS || armorItem.getItem() == Items.IRON_CHESTPLATE || armorItem.getItem() == Items.IRON_HELMET;
                  });
                  boolean hasFullFood = player.getFoodStats().getFoodLevel() == 20;
                  isBot = hasValidArmor && hasValidEquipment && hasFullFood;
               }

               if (isBot) {
                  if (!bot.contains(player)) {
                     bot.add(player);
                  }
               } else {
                  bot.remove(player);
               }
            }
         }
      }
   }

   @EventTarget
   public void onEventWorldChanged(EventSwapWorld e) {
      bot.clear();
   }

   @Native
   public void onDisable() {
      bot.clear();
      super.onDisable();
   }
}
