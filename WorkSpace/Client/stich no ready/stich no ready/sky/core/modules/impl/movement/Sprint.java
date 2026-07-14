package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effects;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.player.MoveUtil;

public class Sprint extends Module {
   private final ModeSetting mode = new ModeSetting("Мод", "Обычный", new String[]{"Обычный", "Пакетный"});
   private final BooleanSetting weatetr = new BooleanSetting("Отжимать спринт в воде", false);

   public Sprint() {
      super("Sprint", "Автоматически активирует режим бега, когда игрок начинает двигаться", Category.Movement);
      this.addSettings(new Setting[]{this.mode, this.weatetr});
   }

   @EventTarget
   public void onUpdateganodn(EventUpdate eventUpdate) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.mode.is("Обычный")) {
            AttackAura aura = SkyCore.getInstance().getModuleManager().attackAura;
            boolean reset = aura.isEnabled() && AttackAura.getTarget() != null && (Boolean)aura.getOnlycrit().get();
            if (reset) {
               if (this.sprint()) {
                  mc.gameSettings.keyBindSprint.setPressed(true);
               } else {
                  mc.gameSettings.keyBindSprint.setPressed(false);
                  var10000 = mc;
                  Minecraft.player.setSprinting(false);
               }
            } else {
               mc.gameSettings.keyBindSprint.setPressed(true);
            }
         }

      }
   }

   public boolean sprint() {
      return this.canSprint();
   }

   public boolean RAGE() {
      Minecraft var10000 = mc;
      boolean var1;
      if (!Minecraft.player.isSneaking()) {
         var10000 = mc;
         if (!Minecraft.player.collidedHorizontally) {
            var10000 = mc;
            if (Minecraft.player.movementInput.moveForward > 0.0F) {
               var10000 = mc;
               if (!Minecraft.player.isCrouching()) {
                  var10000 = mc;
                  if (!Minecraft.player.isPotionActive(Effects.SLOWNESS)) {
                     var10000 = mc;
                     if (!Minecraft.player.isPotionActive(Effects.BLINDNESS)) {
                        var10000 = mc;
                        if (!Minecraft.player.isVisuallySwimming()) {
                           var10000 = mc;
                           if (!Minecraft.player.isHandActive()) {
                              var1 = true;
                              return var1;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var1 = false;
      return var1;
   }

   public boolean canSprint() {
      boolean var2;
      label33: {
         Minecraft var10000 = mc;
         if (!Minecraft.player.getBlockState().isIn(Blocks.COBWEB)) {
            var10000 = mc;
            if (!Minecraft.player.abilities.isFlying && !MoveUtil.isInLiquid()) {
               var10000 = mc;
               if (!Minecraft.player.isRidingHorse()) {
                  label32: {
                     var10000 = mc;
                     if (Minecraft.player.fallDistance <= 0.0F) {
                        var10000 = mc;
                        if (Minecraft.player.isOnGround()) {
                           break label32;
                        }
                     }

                     var2 = false;
                     break label33;
                  }
               }
            }
         }

         var2 = true;
      }

      boolean flag = var2;
      return flag;
   }

   public void onDisable() {
      mc.gameSettings.keyBindSprint.setPressed(false);
   }

   public ModeSetting getMode() {
      return this.mode;
   }

   public BooleanSetting getWeatetr() {
      return this.weatetr;
   }
}
