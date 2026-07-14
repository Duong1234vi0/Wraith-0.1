package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effects;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.impl.combat.AttackAura;

public class AutoJump extends Module {
   public static MultiBooleanSetting jump = new MultiBooleanSetting("Прыгать если", new BooleanSetting[]{new BooleanSetting("Активна Attack Aura", false), new BooleanSetting("Активно зелье замедления", true)});

   public AutoJump() {
      super("Auto Jump", "Автоматически прыгает", Category.Movement);
      this.addSettings(new Setting[]{jump});
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player.isOnGround()) {
         boolean shouldJump = false;
         if (jump.is("Активна Attack Aura")) {
            AttackAura aura = SkyCore.getInstance().getModuleManager().attackAura;
            if (aura.isEnabled() && AttackAura.getTarget() != null) {
               shouldJump = true;
            }
         }

         if (jump.is("Активно зелье замедления")) {
            var10000 = mc;
            if (Minecraft.player.isPotionActive(Effects.SLOWNESS)) {
               shouldJump = true;
            }
         }

         if (shouldJump) {
            var10000 = mc;
            Minecraft.player.jump();
         }

      }
   }
}
