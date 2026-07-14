package dev.client.modules.impl.combat;

import dev.client.event.classes.AttackEvent;
import dev.client.event.interfaces.IAttackable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.other.SoundUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.metaculture.sdk.Compile;

@Environment(EnvType.CLIENT)
public class HitSound extends Module implements IAttackable {
   private final FloatSetting value = ((FloatSetting)(new FloatSetting()).name("Value")).value(15.0F).minValue(0.0F).maxValue(30.0F).incriment(1.0F);
   private final ModeSetting mode = ((ModeSetting)(new ModeSetting()).name("Mode")).value("Type1").modes("Type1", "Type2", "Type3", "Type4", "Type5", "Type6", "Type7");

   public HitSound() {
      super(new ModuleInfo("HitSound", Category.COMBAT, "Проигрывает звук при ударе по противнику"));
      this.addSetting(new Setting[]{this.value, this.mode});
   }

   @Compile
   public void onAttack(AttackEvent event) {
      SoundUtil.playHitSound(this.mode.getValue().toLowerCase() + ".wav", 65.0F + this.value.getValue(), false);
   }
}
