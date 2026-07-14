package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import sky.core.SkyCore;
import sky.core.events.EventEntityHitBox;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.movement.ElytraTarget;

public class HitBox extends Module {
   public static final SliderSetting size = new SliderSetting("Размер", 0.2F, 0.0F, 1.0F, 0.05F);
   private final BooleanSetting showHitBox = new BooleanSetting("Отображать Размер", true);

   public HitBox() {
      super("HitBox", "Изменяет размер хитбокса", Category.Combat);
      this.addSettings(new Setting[]{size, this.showHitBox});
   }

   @EventTarget
   public void onUpdate(EventEntityHitBox event) {
      if (event.getEntity() instanceof LivingEntity) {
         ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
         AttackAura attackAura = SkyCore.getInstance().getModuleManager().getAttackAura();
         if (!this.shouldKeepVanillaHitbox(elytraTarget, attackAura, (LivingEntity)event.getEntity())) {
            event.setSize((Float)size.get());
         }
      }
   }

   public boolean shouldShowHitBox() {
      return this.isEnabled() && (Boolean)this.showHitBox.get();
   }

   private boolean shouldKeepVanillaHitbox(ElytraTarget elytraTarget, AttackAura attackAura, LivingEntity entity) {
      if (elytraTarget != null && attackAura != null && elytraTarget.isEnabled() && elytraTarget.isPedikPredict()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying() && entity.isElytraFlying()) {
               return entity == AttackAura.getTarget();
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
