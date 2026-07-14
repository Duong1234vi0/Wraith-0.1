package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.ui.Interface.elements.impl.NotificationRender;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.notificationmanager.NotificationManager;

public class AlertHandler implements Wrapper {
   private final Set previousActiveEffects = new HashSet();
   private final int[] lastArmorNotifyPercent = new int[4];
   private final Item[] lastArmorItem = new Item[4];

   @EventTarget
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if ((Boolean)NotificationRender.effects.get()) {
            Set currentEffects = new HashSet();
            var10000 = mc;
            Iterator it = Minecraft.player.getActivePotionEffects().iterator();

            while(it.hasNext()) {
               EffectInstance eff = (EffectInstance)it.next();
               currentEffects.add(eff.getPotion());
            }

            it = this.previousActiveEffects.iterator();

            while(it.hasNext()) {
               Effect prev = (Effect)it.next();
               if (!currentEffects.contains(prev)) {
                  ResourceLocation potionIcon = new ResourceLocation("minecraft", "textures/item/potion.png");
                  NotificationManager.addNotification((ResourceLocation)potionIcon, (ITextComponent)(new StringTextComponent("Effect '" + prev.getDisplayName().getString() + "' is no longer active!")), -1);
                  it.remove();
               }
            }

            this.previousActiveEffects.addAll(currentEffects);
         }

         if ((Boolean)NotificationRender.lowstrength.get()) {
            int idx = 0;

            while(true) {
               Minecraft var10001 = mc;
               if (idx >= Minecraft.player.inventory.armorInventory.size()) {
                  break;
               }

               var10000 = mc;
               ItemStack stack = (ItemStack)Minecraft.player.inventory.armorInventory.get(idx);
               if (!stack.isEmpty() && stack.getMaxDamage() > 0) {
                  Item item = stack.getItem();
                  if (this.lastArmorItem[idx] != item) {
                     this.lastArmorItem[idx] = item;
                     this.lastArmorNotifyPercent[idx] = 0;
                  }

                  int maxD = stack.getMaxDamage();
                  int used = stack.getDamage();
                  int remaining = Math.max(0, maxD - used);
                  int remainingPercent = (int)((double)remaining * 100.0 / (double)maxD);
                  if (remainingPercent >= 1 && remainingPercent <= 5 && this.lastArmorNotifyPercent[idx] != remainingPercent) {
                     ResourceLocation key = Registry.ITEM.getKey(item);
                     ResourceLocation image = new ResourceLocation(key.getNamespace(), "textures/item/" + key.getPath() + ".png");
                     ITextComponent message = (new StringTextComponent("")).append(stack.getDisplayName()).appendString(" почти сломан!");
                     NotificationManager.addNotification((ResourceLocation)image, message);
                     this.lastArmorNotifyPercent[idx] = remainingPercent;
                  }
               } else {
                  this.lastArmorItem[idx] = null;
                  this.lastArmorNotifyPercent[idx] = 0;
               }

               ++idx;
            }
         }

      }
   }

   @EventTarget
   public void onUpdate(EventPacket e) {
   }
}
