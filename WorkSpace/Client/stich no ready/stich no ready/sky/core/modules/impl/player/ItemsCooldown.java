package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sky.core.events.EventCooldownTracker;
import sky.core.events.EventRightClickItemCheck;
import sky.core.events.EventUseEnderPearl;
import sky.core.events.EventUseFinish;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ServerUtil;

public class ItemsCooldown extends Module {
   private static final MultiBooleanSetting items = new MultiBooleanSetting("Применять на", new BooleanSetting[]{new BooleanSetting("Золотое яблоко", true), new BooleanSetting("Золотое зачарованное яблоко", true), new BooleanSetting("Эндер жемчуг", true), new BooleanSetting("Хорус", true)});
   private static final SliderSetting gapple = new SliderSetting("Задержка золотого яблока", 4.5F, 0.5F, 15.0F, 0.05F, () -> {
      return (Boolean)items.getIndex(0).get();
   });
   private static final SliderSetting encgapple = new SliderSetting("Задержка золотого зачарованного яблока", 4.5F, 0.5F, 15.0F, 0.05F, () -> {
      return (Boolean)items.getIndex(3).get();
   });
   private static final SliderSetting pearl = new SliderSetting("Задержка эндер жемчуга", 14.05F, 0.5F, 15.0F, 0.05F, () -> {
      return (Boolean)items.getIndex(1).get();
   });
   private static final SliderSetting chorus = new SliderSetting("Задержка хоруса", 2.3F, 0.5F, 15.0F, 0.05F, () -> {
      return (Boolean)items.getIndex(2).get();
   });
   private static final BooleanSetting onlypvp = new BooleanSetting("Только при пвп", true);
   private final Map cooldowns = new HashMap();

   public ItemsCooldown() {
      super("Items Cooldown", "Отображает оставшееся время восстановления для предметов, имеющих перезарядку", Category.Player);
      this.addSettings(new Setting[]{items, gapple, pearl, chorus, encgapple, onlypvp});
   }

   @EventTarget
   public void onEvent(EventUseFinish event) {
      if (!(Boolean)onlypvp.get() || ServerUtil.isPvP()) {
         Item item = event.getItemStack().getItem();
         if (ItemsCooldown.ItemType.fromItem(item) != null) {
            this.cooldowns.put(item, new TimeUtil());
         }

      }
   }

   @EventTarget
   public void onEvent(EventUseEnderPearl event) {
      if (!(Boolean)onlypvp.get() || ServerUtil.isPvP()) {
         Item item = event.getItemStack().getItem();
         if (ItemsCooldown.ItemType.fromItem(item) != null) {
            this.cooldowns.put(item, new TimeUtil());
         }

      }
   }

   @EventTarget
   public void onEvent(EventCooldownTracker event) {
      if (!(Boolean)onlypvp.get() || ServerUtil.isPvP()) {
         Item item = event.getItem();
         TimeUtil timer = (TimeUtil)this.cooldowns.get(item);
         if (timer != null) {
            float cooldownMs = ItemsCooldown.ItemType.fromItem(item).getCooldownSeconds() * 1000.0F;
            long elapsed = timer.getTimePassed();
            if ((float)elapsed >= cooldownMs) {
               this.cooldowns.remove(item);
            } else {
               event.setPartialTicks((float)elapsed / cooldownMs);
            }
         }
      }
   }

   @EventTarget
   public void onEvent(EventRightClickItemCheck event) {
      if (!(Boolean)onlypvp.get() || ServerUtil.isPvP()) {
         Item item = event.getItemStack().getItem();
         ItemType type = ItemsCooldown.ItemType.fromItem(item);
         if (type != null && this.cooldowns.containsKey(type.getItem())) {
            Minecraft var10000 = mc;
            if (Minecraft.player.getCooldownTracker().hasCooldown(type.getItem())) {
               event.setCancelled(true);
            }
         }

      }
   }

   private static enum ItemType {
      GOLDEN_APPLE(Items.GOLDEN_APPLE, (Float)ItemsCooldown.gapple.get()),
      CHORUS(Items.CHORUS_FRUIT, (Float)ItemsCooldown.chorus.get()),
      PEARL(Items.ENDER_PEARL, (Float)ItemsCooldown.pearl.get()),
      ENC_GOLDEN_APPLE(Items.ENCHANTED_GOLDEN_APPLE, (Float)ItemsCooldown.encgapple.get());

      private final Item item;
      private final float cooldownSeconds;

      public static ItemType fromItem(Item item) {
         ItemType[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            ItemType type = var1[var3];
            if (type.item == item) {
               return type;
            }
         }

         return null;
      }

      public Item getItem() {
         return this.item;
      }

      public float getCooldownSeconds() {
         return this.cooldownSeconds;
      }

      private ItemType(Item item, float cooldownSeconds) {
         this.item = item;
         this.cooldownSeconds = cooldownSeconds;
      }

      // $FF: synthetic method
      private static ItemType[] $values() {
         return new ItemType[]{GOLDEN_APPLE, CHORUS, PEARL, ENC_GOLDEN_APPLE};
      }
   }
}
