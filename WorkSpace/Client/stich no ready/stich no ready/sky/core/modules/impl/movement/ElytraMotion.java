package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import sky.core.SkyCore;
import sky.core.events.EventMoving;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.InventoryUtil;

public class ElytraMotion extends Module {
   public final SliderSetting attackDistance = new SliderSetting("Дистанция", 2.5F, 1.0F, 3.0F, 0.1F);
   private final BooleanSetting auto = new BooleanSetting("AutoFireworks", false);
   public boolean freeze;
   private final TimeUtil timer = new TimeUtil();
   public double lastSpeed = 0.0;

   public ElytraMotion() {
      super("ElytraMotion", "Зависает на элитрах перед противником", Category.Movement);
      this.addSettings(new Setting[]{this.attackDistance, this.auto});
   }

   @EventTarget
   public void update(EventUpdate eventUpdate) {
      Minecraft var10000 = mc;
      if (!Minecraft.player.isElytraFlying()) {
         this.freeze = false;
      } else {
         AttackAura killAura = SkyCore.getInstance().getModuleManager().getAttackAura();
         ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
         if (this.check(killAura, elytraTarget)) {
            mc.gameSettings.keyBindForward.setPressed(false);
            this.freeze = true;
         } else {
            mc.gameSettings.keyBindForward.setPressed(true);
            this.freeze = false;
         }

         if ((Boolean)this.auto.get() && AttackAura.getTarget() != null && this.timer.hasTimeElapsed(500L)) {
            InventoryUtil.inventorySwapClick(Items.FIREWORK_ROCKET);
            this.timer.reset();
         }

      }
   }

   @EventTarget
   private void onMotion(EventMoving eventMotion) {
      if (this.freeze) {
         eventMotion.getMotion().x = 0.0;
         eventMotion.getMotion().y = 0.0;
         eventMotion.getMotion().z = 0.0;
      }

   }

   public boolean check(AttackAura killAura, ElytraTarget elytraTarget) {
      LivingEntity target = AttackAura.getTarget();
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player.isElytraFlying()) {
            double dx = AttackAura.getTarget().getPosX() - AttackAura.getTarget().prevPosX;
            double dy = AttackAura.getTarget().getPosY() - AttackAura.getTarget().prevPosY;
            double dz = AttackAura.getTarget().getPosZ() - AttackAura.getTarget().prevPosZ;
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
            double speed = distance * 20.0;
            boolean flag = speed < 25.0;
            Minecraft var10001 = mc;
            boolean var15;
            if (target.getDistance(Minecraft.player) < (Float)this.attackDistance.get() + (target.isElytraFlying() ? 0.5F : 0.0F)) {
               var10000 = mc;
               if (Minecraft.player.isElytraFlying() && flag) {
                  var15 = true;
                  return var15;
               }
            }

            var15 = false;
            return var15;
         }
      }

      return false;
   }

   public void onDisable() {
      this.freeze = false;
      super.onDisable();
   }
}
