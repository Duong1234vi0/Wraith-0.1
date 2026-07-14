package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.api.constructors.impl.TextSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.component.impl.ElytraComponent;
import sky.core.utils.math.TimeUtil;

public class ElytraTarget extends Module {
   private final TextSetting info1 = new TextSetting("Перегон");
   public final BooleanSetting target = new BooleanSetting("Перегонять", false);
   public static SliderSetting pursuitdistance = new SliderSetting("Растояние преследования", 30.0F, 10.0F, 100.0F, 5.0F);
   public final ModeSetting predictMode;
   public final BooleanSetting predictCube;
   public final SliderSetting predictFillAlpha;
   public final BooleanSetting predictFromTheme;
   public final ModeSetting predictBoxMode;
   public final BooleanSetting visualReverse;
   public final SliderSetting distance;
   private final BooleanSetting box;
   boolean non;
   int lastslot;
   private final TimeUtil timerUtils;
   public boolean status;
   public boolean disableForward;
   private final TimeUtil hurtTimer;
   private boolean visualReverseWasActive;

   public ElytraTarget() {
      super("ElytraTarget", "Преследует таргета на элитре", Category.Combat);
      BooleanSetting var10005 = this.target;
      Objects.requireNonNull(var10005);
      this.predictMode = new ModeSetting("Режим предикта", "ReallyWorld", var10005::get, new String[]{"ReallyWorld", "ReallyWorld - 2", "Default"});
      this.predictCube = new BooleanSetting("Рисовать предикт", true, () -> {
         return (Boolean)this.target.get() && this.supportsPredictCube();
      });
      this.predictFillAlpha = new SliderSetting("Прозрачность", 40.0F, 0.0F, 255.0F, 1.0F, () -> {
         return (Boolean)this.target.get() && (Boolean)this.predictCube.get() && this.supportsPredictCube();
      });
      this.predictFromTheme = new BooleanSetting("От темы", true, () -> {
         return (Boolean)this.target.get() && (Boolean)this.predictCube.get() && this.supportsPredictCube();
      });
      this.predictBoxMode = new ModeSetting("Вид квадрата", "Обычный", () -> {
         return (Boolean)this.target.get() && (Boolean)this.predictCube.get() && this.supportsPredictCube();
      }, new String[]{"Обычный", "Пунктир", "Диагонали"});
      Boolean var10004 = false;
      var10005 = this.target;
      Objects.requireNonNull(var10005);
      this.visualReverse = new BooleanSetting("Разворот на 180", var10004, var10005::get);
      BooleanSetting var10008 = this.target;
      Objects.requireNonNull(var10008);
      this.distance = new SliderSetting("Сила предикта", 2.7F, 1.0F, 5.0F, 0.1F, var10008::get);
      this.box = new BooleanSetting("Рендерить Бокс", true);
      this.non = false;
      this.lastslot = 0;
      this.timerUtils = new TimeUtil();
      this.status = true;
      this.disableForward = false;
      this.hurtTimer = new TimeUtil();
      this.visualReverseWasActive = false;
      this.addSettings(new Setting[]{this.info1, this.predictMode, this.predictCube, this.predictFillAlpha, this.predictFromTheme, this.predictBoxMode, this.visualReverse, this.target, this.distance, pursuitdistance});
   }

   public boolean isReallyworldPredict() {
      return this.predictMode.is("ssdaasdasd") || this.predictMode.is("asdasd");
   }

   public boolean isTestPredict() {
      return this.predictMode.is("ReallyWorld");
   }

   public boolean isPedikPredict() {
      return this.predictMode.is("ReallyWorld - 2");
   }

   public boolean usesVisualReverseLogic() {
      return this.isTestPredict() || this.isPedikPredict();
   }

   public boolean supportsPredictCube() {
      return this.isReallyworldPredict() || this.isTestPredict() || this.isPedikPredict();
   }

   @EventTarget
   public void update(EventUpdate eventUpdate) {
      if (!this.non) {
         Minecraft var10001 = mc;
         this.lastslot = Minecraft.player.inventory.currentItem;
      }

      AttackAura aura = SkyCore.getInstance().getModuleManager().attackAura;
      this.status = (Boolean)this.target.get();
      if (AttackAura.getTarget() == null) {
         this.disableForward = false;
      } else {
         Minecraft var10000 = mc;
         if (Minecraft.player.hurtTime > 0 && !AttackAura.getTarget().lastSwing.finished(500L)) {
            this.disableForward = true;
            this.hurtTimer.reset();
         }

         if (this.hurtTimer.finished(500L)) {
            this.disableForward = false;
         }

      }
   }

   @EventTarget
   private void onRender(EventRender3D event) {
      ElytraComponent.renderPredictCube(event, this);
   }

   public boolean shouldTarget(LivingEntity livingEntity) {
      if (this.isEnabled() && livingEntity != null && !this.disableForward) {
         boolean isTargetValid = livingEntity.isElytraFlying();
         boolean var3;
         if (this.status) {
            Minecraft var10000 = mc;
            if (Minecraft.player.isElytraFlying() && isTargetValid) {
               var3 = true;
               return var3;
            }
         }

         var3 = false;
         return var3;
      } else {
         return false;
      }
   }

   public boolean isReverseActive() {
      if (this.usesVisualReverseLogic() && this.isEnabled() && (Boolean)this.target.get() && (Boolean)this.visualReverse.get() && !this.disableForward) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            AttackAura aura = SkyCore.getInstance().getModuleManager().attackAura;
            LivingEntity auraTarget = aura != null ? AttackAura.getTarget() : null;
            if (auraTarget != null && this.shouldTarget(auraTarget)) {
               var10000 = mc;
               float distanceToTarget = Minecraft.player.getDistance(auraTarget);
               float enableDistance = 2.5F;
               float predictDistance = Math.max(enableDistance, (Float)this.distance.get());
               float disableDistance = predictDistance + 3.0F;
               if (!this.visualReverseWasActive && distanceToTarget <= enableDistance) {
                  this.visualReverseWasActive = true;
               }

               if (this.visualReverseWasActive && distanceToTarget >= disableDistance) {
                  this.visualReverseWasActive = false;
               }

               return this.visualReverseWasActive;
            }

            this.visualReverseWasActive = false;
            return false;
         }
      }

      this.visualReverseWasActive = false;
      return false;
   }

   @EventTarget
   public void onDisable() {
      super.onDisable();
   }

   public TextSetting getInfo1() {
      return this.info1;
   }

   public BooleanSetting getTarget() {
      return this.target;
   }

   public ModeSetting getPredictMode() {
      return this.predictMode;
   }

   public BooleanSetting getPredictCube() {
      return this.predictCube;
   }

   public SliderSetting getPredictFillAlpha() {
      return this.predictFillAlpha;
   }

   public BooleanSetting getPredictFromTheme() {
      return this.predictFromTheme;
   }

   public ModeSetting getPredictBoxMode() {
      return this.predictBoxMode;
   }

   public BooleanSetting getVisualReverse() {
      return this.visualReverse;
   }

   public SliderSetting getDistance() {
      return this.distance;
   }

   public BooleanSetting getBox() {
      return this.box;
   }

   public boolean isNon() {
      return this.non;
   }

   public int getLastslot() {
      return this.lastslot;
   }

   public TimeUtil getTimerUtils() {
      return this.timerUtils;
   }

   public boolean isStatus() {
      return this.status;
   }

   public boolean isDisableForward() {
      return this.disableForward;
   }

   public TimeUtil getHurtTimer() {
      return this.hurtTimer;
   }

   public boolean isVisualReverseWasActive() {
      return this.visualReverseWasActive;
   }
}
