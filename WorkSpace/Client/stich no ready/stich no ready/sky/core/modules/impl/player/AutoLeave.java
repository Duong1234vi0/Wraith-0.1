package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.StaffHandler;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.misc.ServerUtil;

public class AutoLeave extends Module {
   private final MultiBooleanSetting leave = new MultiBooleanSetting("Ливать если", new BooleanSetting[]{new BooleanSetting("Мало здоровья", true), new BooleanSetting("Рядом игрок", true), new BooleanSetting("Администратор в ванише", true), new BooleanSetting("Лутание предметов", false)});
   private final ModeSetting typeleave = new ModeSetting("Тип ухода", "Спавн", () -> {
      return this.leave.is("Мало здоровья") || this.leave.is("Рядом игрок") || this.leave.is("Администратор в ванише") || this.leave.is("Лутание предметов");
   }, new String[]{"Спавн", "Домой", "Хаб"});
   private final BooleanSetting pvp = new BooleanSetting("Ливать только в конце пвп", false, () -> {
      return this.leave.is("Мало здоровья") || this.leave.is("Рядом игрок") || this.leave.is("Администратор в ванише") || this.leave.is("Лутание предметов");
   });
   private final SliderSetting hp = new SliderSetting("Здоровье", 15.0F, 1.0F, 20.0F, 1.0F, () -> {
      return this.leave.is("Мало здоровья");
   });
   private final SliderSetting radius = new SliderSetting("Радиус игрока", 10.0F, 1.0F, 50.0F, 1.0F, () -> {
      return this.leave.is("Рядом игрок");
   });
   private final SliderSetting minItems = new SliderSetting("Мин. предметов", 1.0F, 1.0F, 64.0F, 1.0F, () -> {
      return this.leave.is("Лутание предметов");
   });
   private final BooleanSetting baritone = new BooleanSetting("Отключать баритон", true, () -> {
      return this.leave.is("Мало здоровья") || this.leave.is("Рядом игрок") || this.leave.is("Администратор в ванише") || this.leave.is("Лутание предметов");
   });
   private int lastItemCount;

   public AutoLeave() {
      super("AutoLeave", "Избегает нежелательной встречи с игроками, выбранным способом, обеспечивая безопасность", Category.Player);
      this.addSettings(new Setting[]{this.leave, this.typeleave, this.pvp, this.hp, this.radius, this.minItems, this.baritone});
   }

   public void onEnable() {
      super.onEnable();
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         this.lastItemCount = this.getTotalItemCount();
      }

   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.leave.is("Мало здоровья")) {
            var10000 = mc;
            if (Minecraft.player.getHealth() <= (Float)this.hp.get()) {
               this.handleLeave();
               return;
            }
         }

         if (this.leave.is("Рядом игрок")) {
            Iterator var2 = mc.world.getPlayers().iterator();

            while(var2.hasNext()) {
               PlayerEntity player = (PlayerEntity)var2.next();
               Minecraft var10001 = mc;
               if (player != Minecraft.player && !SkyCore.getInstance().getFriendManager().isFriend(player.getGameProfile().getName())) {
                  var10000 = mc;
                  if (Minecraft.player.getDistance(player) <= (Float)this.radius.get()) {
                     this.handleLeave();
                     return;
                  }
               }
            }
         }

         if (this.leave.is("Администратор в ванише") && !StaffHandler.getVanishedStaff().isEmpty()) {
            this.handleLeave();
         } else {
            if (this.leave.is("Лутание предметов")) {
               int currentItemCount = this.getTotalItemCount();
               int pickedUp = currentItemCount - this.lastItemCount;
               if ((float)pickedUp >= (Float)this.minItems.get()) {
                  this.handleLeave();
                  return;
               }

               this.lastItemCount = currentItemCount;
            }

         }
      }
   }

   private int getTotalItemCount() {
      int count = 0;
      Minecraft var10000 = mc;
      Iterator var2 = Minecraft.player.inventory.mainInventory.iterator();

      ItemStack stack;
      while(var2.hasNext()) {
         stack = (ItemStack)var2.next();
         if (!stack.isEmpty()) {
            count += stack.getCount();
         }
      }

      var10000 = mc;
      var2 = Minecraft.player.inventory.armorInventory.iterator();

      while(var2.hasNext()) {
         stack = (ItemStack)var2.next();
         if (!stack.isEmpty()) {
            count += stack.getCount();
         }
      }

      var10000 = mc;
      var2 = Minecraft.player.inventory.offHandInventory.iterator();

      while(var2.hasNext()) {
         stack = (ItemStack)var2.next();
         if (!stack.isEmpty()) {
            count += stack.getCount();
         }
      }

      return count;
   }

   @Native
   private void handleLeave() {
      if (!(Boolean)this.pvp.get() || ServerUtil.isPvP()) {
         Minecraft var10000;
         if ((Boolean)this.baritone.get() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
            var10000 = mc;
            Minecraft.player.sendChatMessage((String)BaritoneAPI.getSettings().prefix.value + "stop");
         }

         switch ((String)this.typeleave.get()) {
            case "Спавн":
               var10000 = mc;
               Minecraft.player.sendChatMessage("/spawn");
               break;
            case "Домой":
               var10000 = mc;
               Minecraft.player.sendChatMessage("/home");
               break;
            case "Хаб":
               var10000 = mc;
               Minecraft.player.sendChatMessage("/hub");
         }

         this.toggle();
      }
   }
}
