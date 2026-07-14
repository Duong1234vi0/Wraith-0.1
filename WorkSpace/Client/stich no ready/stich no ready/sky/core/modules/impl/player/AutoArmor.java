package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.math.TimeUtil;

public class AutoArmor extends Module {
   private final SliderSetting delay = new SliderSetting("Задержка", 150.0F, 0.0F, 1000.0F, 5.0F);
   private final TimeUtil timer = new TimeUtil();

   public AutoArmor() {
      super("AutoArmor", "Автоматически одевает лучшую броню, обеспечивая максимальную защиту", Category.Player);
      this.addSettings(new Setting[]{this.delay});
   }

   @EventTarget
   private void onUpdate(EventUpdate e) {
      if (mc.currentScreen == null) {
         if (this.timer.hasTimeElapsed(((Float)this.delay.get()).longValue())) {
            int[] bestIndexes = new int[]{-1, -1, -1, -1};
            int[] bestValues = new int[4];

            ArmorItem armorItem;
            int i;
            Minecraft var10000;
            for(i = 0; i < 4; ++i) {
               var10000 = mc;
               ItemStack currentArmor = Minecraft.player.inventory.armorItemInSlot(i);
               if (this.isValidArmor(currentArmor)) {
                  armorItem = (ArmorItem)currentArmor.getItem();
                  bestValues[i] = this.getArmorValue(armorItem, currentArmor);
               }
            }

            int slot;
            for(i = 0; i < 36; ++i) {
               var10000 = mc;
               ItemStack stack = Minecraft.player.inventory.getStackInSlot(i);
               Item item;
               if (stack != null && !stack.isEmpty() && (item = stack.getItem()) instanceof ArmorItem) {
                  armorItem = (ArmorItem)item;
                  slot = armorItem.getSlot().getIndex();
                  int value = this.getArmorValue(armorItem, stack);
                  if (value > bestValues[slot]) {
                     bestIndexes[slot] = i;
                     bestValues[slot] = value;
                  }
               }
            }

            List slots = new ArrayList(Arrays.asList(0, 1, 2, 3));
            Collections.shuffle(slots);
            Iterator var13 = slots.iterator();

            while(var13.hasNext()) {
               slot = (Integer)var13.next();
               var10000 = mc;
               ItemStack currentArmor = Minecraft.player.inventory.armorItemInSlot(slot);
               int bestIndex = bestIndexes[slot];
               if (bestIndex != -1) {
                  if (this.isValidArmor(currentArmor)) {
                     var10000 = mc;
                     if (Minecraft.player.inventory.getFirstEmptyStack() == -1) {
                        continue;
                     }
                  }

                  if (bestIndex < 9) {
                     bestIndex += 36;
                  }

                  Minecraft var10005;
                  if (this.isValidArmor(currentArmor)) {
                     int var10002 = 8 - slot;
                     var10005 = mc;
                     mc.playerController.windowClick(0, var10002, 0, ClickType.QUICK_MOVE, Minecraft.player);
                  }

                  var10005 = mc;
                  mc.playerController.windowClick(0, bestIndex, 0, ClickType.QUICK_MOVE, Minecraft.player);
                  this.timer.reset();
                  break;
               }
            }

         }
      }
   }

   private int getArmorValue(ArmorItem armor, ItemStack stack) {
      int protection = EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
      IArmorMaterial material = armor.getArmorMaterial();
      int damageReduction = material.getDamageReductionAmount(armor.getSlot());
      float toughness = armor.getToughness();
      return (int)(((float)(damageReduction * 20 + protection * 12) + toughness * 2.0F + (float)(damageReduction * 5)) / 8.0F);
   }

   private boolean isValidArmor(ItemStack stack) {
      return stack != null && !stack.isEmpty() && stack.getItem() instanceof ArmorItem;
   }
}
