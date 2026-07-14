package dev.client.modules.impl.player;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.mixins.other.IMinecraftClientMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

@Environment(EnvType.CLIENT)
public class FastUse extends Module implements ITickable, IUtil {
   private final FloatSetting delay = ((FloatSetting)(new FloatSetting()).name("Delay")).value(3.0F).minValue(0.0F).maxValue(5.0F).incriment(1.0F);
   private final MultiBoxSetting options = ((MultiBoxSetting)(new MultiBoxSetting()).name("Options")).booleanSettings(((BooleanSetting)(new BooleanSetting()).name("Blocks")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Crystals")).value(false), ((BooleanSetting)(new BooleanSetting()).name("Exp")).value(false));

   public FastUse() {
      super(new ModuleInfo("FastUse", Category.PLAYER, "Ускоряет использование выбранных предметов"));
      this.addSetting(new Setting[]{this.delay, this.options});
   }

   public void onTick(TickEvent event) {
      if (this.check(mc.player.getMainHandStack().getItem()) && (float)((IMinecraftClientMixin)mc).getUseCooldown() > this.delay.getValue()) {
         ((IMinecraftClientMixin)mc).setUseCooldown((int)this.delay.getValue());
      }

   }

   public boolean check(Item item) {
      return item instanceof BlockItem && this.options.getValueByName("Blocks") || item == Items.END_CRYSTAL && this.options.getValueByName("Crystals") || item == Items.EXPERIENCE_BOTTLE && this.options.getValueByName("Exp");
   }
}
